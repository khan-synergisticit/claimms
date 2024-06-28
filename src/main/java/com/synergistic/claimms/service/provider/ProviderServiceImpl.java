package com.synergistic.claimms.service.provider;

import com.synergistic.claimms.domain.Provider;
import com.synergistic.claimms.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired private ProviderRepository providerRepository;
    @Override
    public Provider getProvider(String providerId) {
        return providerRepository.findById(providerId).orElse(null);
    }

    @Override
    public Provider saveProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public Provider updateProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public void deleteProvider(String providerId) {
        providerRepository.deleteById(providerId);
    }

    @Override
    public Provider findByEmail(String email) {
        return providerRepository.findByEmail(email);
    }
}
