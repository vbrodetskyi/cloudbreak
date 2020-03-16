package com.sequenceiq.it.cloudbreak.mock.freeipa;

import org.springframework.stereotype.Component;

import com.sequenceiq.freeipa.client.model.DnsRecord;

import spark.Request;
import spark.Response;

@Component
public class DnsRecordShowResponse extends AbstractFreeIpaResponse<DnsRecord> {
    @Override
    public String method() {
        return "dnsrecord_show";
    }

    @Override
    protected DnsRecord handleInternal(Request request, Response response) {
        DnsRecord dnsRecord = new DnsRecord();
        dnsRecord.setIdnsname("localhost");
        return dnsRecord;
    }
}
