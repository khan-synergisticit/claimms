package com.synergistic.claimms.service.claimcharge;

import com.synergistic.claimms.domain.Claim.ClaimCharge;
import com.synergistic.claimms.repository.ClaimChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimChargeServiceImpl implements ClaimChargeService {
    @Autowired private ClaimChargeRepository claimChargeRepository;
    @Override
    public ClaimCharge createClaimCharge(ClaimCharge claimCharge) {
        return claimChargeRepository.save(claimCharge);
    }

    @Override
    public ClaimCharge updateClaimCharge(ClaimCharge claimCharge) {
        return claimChargeRepository.save(claimCharge);
    }

    @Override
    public ClaimCharge getClaimCharge(String claimChargeId) {
        return claimChargeRepository.findById(claimChargeId).orElse(null);
    }

    @Override
    public void deleteClaimCharge(String claimChargeId) {
        claimChargeRepository.deleteById(claimChargeId);
    }
}
