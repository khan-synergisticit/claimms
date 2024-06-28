package com.synergistic.claimms.repository;

import com.synergistic.claimms.domain.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends MongoRepository<Provider, String > {
    Provider findByEmail(String email);
}
