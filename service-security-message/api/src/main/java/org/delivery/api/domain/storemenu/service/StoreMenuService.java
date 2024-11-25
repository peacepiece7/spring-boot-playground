package org.delivery.api.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreMenuService {
    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity register(StoreMenuEntity storeMenuEntity) {
        return Optional.ofNullable(storeMenuEntity)
                .map(it -> {
                    it.setStatus(StoreMenuStatus.REGISTERED);
                    return storeMenuRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreMenuEntity getStoreMenuWithThrow(Long id) {
        var entity = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED);
        return entity.orElseThrow(() -> new ApiException((ErrorCode.NULL_POINT)));
    }

    public StoreMenuEntity getStoreMenuByStoreId(Long storeId) {
        var entity = storeMenuRepository.findFirstByStoreIdAndStatusOrderByIdDesc(storeId, StoreMenuStatus.REGISTERED);
        return entity.orElseThrow(() -> new ApiException((ErrorCode.NULL_POINT)));
    }

    // 스토머 메뉴를 검색할 때 사용한다. M : 1
    public List<StoreMenuEntity> getStoreMenuListByStoreId(Long storeId) {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED);
    }

    // 스토머 메뉴를 검색할 때 사용한다. N : M (이게 필요할지 고민해보자)
    public List<StoreMenuEntity> getStoreMenuListByIds(List<Long> ids) {
        return storeMenuRepository.findByIdIn(ids);
    }

}
