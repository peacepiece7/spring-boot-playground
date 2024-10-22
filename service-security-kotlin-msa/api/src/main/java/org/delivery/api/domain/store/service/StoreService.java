package org.delivery.api.domain.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreEntity register(StoreEntity storeEntity) {
        return Optional.ofNullable(storeEntity)
                .map(it -> {
                    it.setStar(0);
                    it.setStatus(StoreStatus.REGISTERED);
                    log.info("@ STORE REQUEST: {}", it.toString());
                    return storeRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreEntity> searchByCategory(StoreCategory storeCategory) {
        return storeRepository.findAllByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, storeCategory);
    }

    public StoreEntity getStoreWithThrow(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

}