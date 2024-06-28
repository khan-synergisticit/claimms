package com.synergistic.claimms.service.claimform;

import com.synergistic.claimms.domain.Claim.ClaimForm;
import com.synergistic.claimms.domain.Claim.ClaimStatus;
import com.synergistic.claimms.domain.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClaimFormService {
    List<ClaimForm> getClaimFormByPolicy(String policyId);
    ClaimForm createClaimForm(ClaimForm claimForm);
    ClaimForm updateClaimForm(ClaimForm claimForm);
    void deleteClaimForm(String claimFormId);
    Page<ClaimForm> findByStatus(ClaimStatus status, PageInfo pageInfo);
    ClaimForm getClaimFormById(String claimFormId);
}
