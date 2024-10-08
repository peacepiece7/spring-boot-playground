package org.delivery.api.domain.storemenu.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public StoreMenuResponse register(StoreMenuRegisterRequest request) {
        var entity = storeMenuConverter.toEntity(request);
        var response = storeMenuService.register(entity);
        return storeMenuConverter.toResponse(response);
    }

    public List<StoreMenuResponse> search(Long storeId) {
        var list = storeMenuService.getStoreMenuByStoreId(storeId);
        return list.stream()
                .map(storeMenuConverter::toResponse)
                .toList();
    }
}
