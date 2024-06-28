package com.synergistic.claimms.domain.Claim;

import org.bson.Document;
import org.springframework.core.convert.converter.Converter;


public class MemberConverter implements Converter<Document, ClaimForm> {
    @Override
    public ClaimForm convert(Document source) {
        ClaimForm claimForm = new ClaimForm();
        claimForm.setMemberId(String.valueOf((String) source.get("_id")));
        return claimForm;
    }
}
