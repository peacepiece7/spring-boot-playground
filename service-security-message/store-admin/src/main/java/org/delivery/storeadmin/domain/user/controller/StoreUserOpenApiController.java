package org.delivery.storeadmin.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.storeadmin.domain.user.business.StoreUserBusiness;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/store-user")
public class StoreUserOpenApiController {

    private final StoreUserBusiness storeUserBusiness;

    @GetMapping("")
    public ResponseEntity<String> hi() {
        return ResponseEntity.ok().body("Hello, open api");
    }

    @PostMapping("")
    public ResponseEntity<StoreUserResponse> register(
            @Valid
            @RequestBody StoreUserRegisterRequest request
    ) {
        var storeUserResponse = storeUserBusiness.register(request);
        return ResponseEntity.ok().body(storeUserResponse);
    }
}
