package com.synergistic.claimms.service.claimform;

import com.synergistic.claimms.domain.Claim.ClaimForm;
import com.synergistic.claimms.domain.Claim.ClaimStatus;
import com.synergistic.claimms.domain.PageInfo;
import com.synergistic.claimms.repository.ClaimFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimFormServiceImpl implements ClaimFormService {
    @Autowired private ClaimFormRepository claimFormRepository;

    @Override
    public List<ClaimForm> getClaimFormByPolicy(String policyId) {
        return claimFormRepository.findByPolicyId(policyId);
    }

    @Override
    public ClaimForm createClaimForm(ClaimForm claimForm) {
        return claimFormRepository.save(claimForm);
    }

    @Override
    public ClaimForm updateClaimForm(ClaimForm claimForm) {
        return claimFormRepository.save(claimForm);
    }

    @Override
    public void deleteClaimForm(String claimFormId) {
        claimFormRepository.deleteById(claimFormId);
    }

    @Override
    public Page<ClaimForm> findByStatus(ClaimStatus status, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNo(),pageInfo.getPageSize() ,pageInfo.getSortOrder().equalsIgnoreCase("asc") ? Sort.by(pageInfo.getSortBy()).ascending() : Sort.by(pageInfo.getSortBy()).descending());
        Page<ClaimForm> claimForms = claimFormRepository.findByClaimStatus(status, pageable);
        return claimForms;
    }

    @Override
    public ClaimForm getClaimFormById(String claimFormId) {
        return claimFormRepository.findById(claimFormId).orElse(null);
    }
}
