package com.synergistic.claimms.service.claimcharge;

import com.synergistic.claimms.domain.Claim.ClaimCharge;

public interface ClaimChargeService {
    ClaimCharge createClaimCharge(ClaimCharge claimCharge);
    ClaimCharge updateClaimCharge(ClaimCharge claimCharge);
    ClaimCharge getClaimCharge(String claimChargeId);
    void deleteClaimCharge(String claimChargeId);
}
