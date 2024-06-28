package com.synergistic.claimms.repository;

import com.synergistic.claimms.domain.Claim.ClaimCharge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimChargeRepository extends MongoRepository<ClaimCharge, String> {
}
