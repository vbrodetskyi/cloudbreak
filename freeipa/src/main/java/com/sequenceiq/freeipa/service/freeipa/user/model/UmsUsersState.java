package com.sequenceiq.freeipa.service.freeipa.user.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cloudera.thunderhead.service.usermanagement.UserManagementProto.CloudIdentity;
import com.cloudera.thunderhead.service.usermanagement.UserManagementProto.ServicePrincipalCloudIdentities;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class UmsUsersState {

    private final UsersState usersState;

    private final ImmutableMap<String, WorkloadCredential> usersWorkloadCredentialMap;

    private final ImmutableSet<FmsUser> requestedWorkloadUsers;

    private final ImmutableSet<FmsGroup> workloadAdministrationGroups;

    private final ImmutableMap<String, List<CloudIdentity>> userToCloudIdentityMap;

    private final ImmutableList<ServicePrincipalCloudIdentities> servicePrincipalCloudIdentities;

    private UmsUsersState(UsersState usersState, Map<String, WorkloadCredential> usersWorkloadCredentialMap, Set<FmsUser> requestedWorkloadUsers,
        Collection<FmsGroup> workloadAdministrationGroups, Map<String, List<CloudIdentity>> userToCloudIdentityMap,
        List<ServicePrincipalCloudIdentities> servicePrincipalCloudIdentities) {
        this.usersState = requireNonNull(usersState, "UsersState is null");
        this.usersWorkloadCredentialMap = ImmutableMap.copyOf(requireNonNull(usersWorkloadCredentialMap, "workload credential map is null"));
        this.requestedWorkloadUsers = ImmutableSet.copyOf(requireNonNull(requestedWorkloadUsers, "requested workload users is null"));
        this.workloadAdministrationGroups = ImmutableSet.copyOf(requireNonNull(workloadAdministrationGroups, "workloadAdministrationGroups is null"));
        this.userToCloudIdentityMap = ImmutableMap.copyOf(requireNonNull(userToCloudIdentityMap, "userToCloudIdentityMap is null"));
        this.servicePrincipalCloudIdentities = ImmutableList.copyOf(requireNonNull(servicePrincipalCloudIdentities, "servicePrincipalCloudIdentities is null"));
    }

    public UsersState getUsersState() {
        return usersState;
    }

    public ImmutableMap<String, WorkloadCredential> getUsersWorkloadCredentialMap() {
        return usersWorkloadCredentialMap;
    }

    public ImmutableSet<FmsUser> getRequestedWorkloadUsers() {
        return requestedWorkloadUsers;
    }

    public ImmutableSet<FmsGroup> getWorkloadAdministrationGroups() {
        return workloadAdministrationGroups;
    }

    public ImmutableMap<String, List<CloudIdentity>> getUserToCloudIdentityMap() {
        return userToCloudIdentityMap;
    }

    public ImmutableList<ServicePrincipalCloudIdentities> getServicePrincipalCloudIdentities() {
        return servicePrincipalCloudIdentities;
    }

    public static class Builder {
        private UsersState usersState;

        private Map<String, WorkloadCredential> workloadCredentialMap = new HashMap<>();

        private Set<FmsUser> requestedWorkloadUsers = new HashSet<>();

        private Collection<FmsGroup> workloadAdministrationGroups = Set.of();

        private Map<String, List<CloudIdentity>> userToCloudIdentityMap = new HashMap<>();

        private List<ServicePrincipalCloudIdentities> servicePrincipalCloudIdentities = new ArrayList<>();

        public Builder setUsersState(UsersState usersState) {
            this.usersState = usersState;
            return this;
        }

        public Builder addWorkloadCredentials(String userName, WorkloadCredential creds) {
            workloadCredentialMap.put(userName, creds);
            return this;
        }

        public Builder addRequestedWorkloadUsers(FmsUser user) {
            requestedWorkloadUsers.add(user);
            return this;
        }

        public Builder setWorkloadAdministrationGroups(Collection<FmsGroup> workloadAdministrationGroups) {
            this.workloadAdministrationGroups = workloadAdministrationGroups;
            return this;
        }

        public Builder addUserCloudIdentities(String user, List<CloudIdentity> cloudIdentities) {
            userToCloudIdentityMap.put(user, cloudIdentities);
            return this;
        }

        public Builder addServicePrincipalCloudIdentities(List<ServicePrincipalCloudIdentities> cloudIdentities) {
            servicePrincipalCloudIdentities.addAll(cloudIdentities);
            return this;
        }

        public UmsUsersState build() {
            return new UmsUsersState(usersState, workloadCredentialMap, requestedWorkloadUsers, workloadAdministrationGroups, userToCloudIdentityMap,
                    servicePrincipalCloudIdentities);
        }
    }

}
