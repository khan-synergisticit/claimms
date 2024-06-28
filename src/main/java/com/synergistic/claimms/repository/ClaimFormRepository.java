package com.synergistic.claimms.repository;

import com.synergistic.claimms.domain.Claim.ClaimForm;
import com.synergistic.claimms.domain.Claim.ClaimStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimFormRepository extends MongoRepository<ClaimForm, String> {
    List<ClaimForm> findByPolicyId(String policyId);
    Page<ClaimForm> findByClaimStatus(ClaimStatus claimStatus, Pageable pageable);
}
