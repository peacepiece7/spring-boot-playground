package org.delivery.storeadmin.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    
}
