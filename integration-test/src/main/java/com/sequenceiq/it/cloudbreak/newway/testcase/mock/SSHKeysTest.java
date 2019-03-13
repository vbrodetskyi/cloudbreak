package com.sequenceiq.it.cloudbreak.newway.testcase.mock;

import static com.sequenceiq.it.cloudbreak.newway.context.RunningParameter.key;
import static com.sequenceiq.it.cloudbreak.newway.context.TestCaseDescription.TestCaseDescriptionBuilder.createWithGiven;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sequenceiq.it.cloudbreak.newway.action.sshkeys.PlatformSshKeysTestAction;
import com.sequenceiq.it.cloudbreak.newway.client.CredentialTestClient;
import com.sequenceiq.it.cloudbreak.newway.context.Description;
import com.sequenceiq.it.cloudbreak.newway.context.MockedTestContext;
import com.sequenceiq.it.cloudbreak.newway.context.TestCaseDescription;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.credential.CredentialTestDto;
import com.sequenceiq.it.cloudbreak.newway.entity.sshkeys.PlatformSshKeysTestDto;
import com.sequenceiq.it.cloudbreak.newway.testcase.AbstractIntegrationTest;

public class SSHKeysTest extends AbstractIntegrationTest {

    @BeforeMethod
    public void beforeMethod(Object[] data) {
        createDefaultUser((TestContext) data[0]);
    }

    @Test(dataProvider = TEST_CONTEXT_WITH_MOCK)
    @Description(given = "a valid MOCK credential",
            when = "get ssh keys",
            then = "getting back the MOCK related ssh keys")
    public void testGetSSHKeysByCredentialName(MockedTestContext testContext) {
        String credentialName = getNameGenerator().getRandomNameForResource();
        testContext
                .given(CredentialTestDto.class)
                .withName(credentialName)
                .when(CredentialTestClient::create)
                .given(PlatformSshKeysTestDto.class)
                .withCredentialName(credentialName)
                .when(PlatformSshKeysTestAction::getSSHKeys)
                .validate();
    }

    @Test(dataProvider = "contextWithCredentialNameAndException")
    public void testGetSSHKeysByCredentialNameWhenCredentialIsInvalid(
            MockedTestContext testContext,
            String credentialName,
            String exceptionKey,
            Class<Exception> exception,
            @Description TestCaseDescription testCaseDescription) {
        testContext
                .given(PlatformSshKeysTestDto.class)
                .withCredentialName(credentialName)
                .when(PlatformSshKeysTestAction::getSSHKeys, key(exceptionKey))
                .expect(exception, key(exceptionKey))
                .validate();
    }

    @DataProvider(name = "contextWithCredentialNameAndException")
    public Object[][] provideInvalidAttributes() {
        return new Object[][]{
                {getBean(MockedTestContext.class), "", "badRequest", BadRequestException.class,
                        createWithGiven("platform ssh keys")
                                .when("credential name is empty")
                                .then("get bad request ecxeption")},
                {getBean(MockedTestContext.class), null, "badRequest", BadRequestException.class,
                        createWithGiven("platform ssh keys")
                                .when("credential name is null")
                                .then("get bad request ecxeption")},
                {getBean(MockedTestContext.class), "andNowForSomethingCompletelyDifferent", "forbidden",
                        ForbiddenException.class, createWithGiven("platform ssh keys")
                        .when("credential name is not exists")
                        .then("get forbidden ecxeption")}
        };
    }
}
