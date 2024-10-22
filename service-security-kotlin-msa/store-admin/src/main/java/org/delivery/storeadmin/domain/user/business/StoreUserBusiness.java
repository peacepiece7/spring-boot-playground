package org.delivery.storeadmin.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserConverter storeUserConverter;
    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    public StoreUserResponse register(StoreUserRegisterRequest request) {

        var storeEntity = Optional.ofNullable(storeRepository.findFirstByIdAndStatusOrderByIdDesc(request.getStoreId(),
                StoreStatus.REGISTERED)).orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST, "조회된 스토어가 없습니다."));

        var storeUserEntity = storeUserConverter.toEntity(request, storeEntity);

        var registeredStoreUserEntity = storeUserService.register(storeUserEntity);

        return storeUserConverter.toResponse(registeredStoreUserEntity, storeEntity);
    }

}
