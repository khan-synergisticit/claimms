package com.synergistic.claimms.service.provider;

import com.synergistic.claimms.domain.Provider;

public interface ProviderService {
    Provider getProvider(String providerId);
    Provider saveProvider(Provider provider);
    Provider updateProvider(Provider provider);
    void deleteProvider(String providerId);
    Provider findByEmail(String email);
}
