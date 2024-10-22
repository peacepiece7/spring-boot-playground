package org.delivery.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    /**
     * @formatter:off
     * 1. 사용자가 email, password 입력 후 로그인 요청한다.
     * 2. 사용자가 입력한 username 으로 DB 에서 storeUserEntity 를 가져온다.
     * 3. spring security 에서 제공하는 User 클래스에 사용자가 입력한 email, password, role 담아서 리턴한다. (셋 다 필수) 
     * 4. spring security 가 UserDetailsService 안에서 사용자가 입력한 password, db 에서 가져온 password(해시값)을 비교한다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var storeUserEntity = storeUserService.getRegisterUser(username);

        var storeEntity = Optional.ofNullable(storeRepository.findFirstByIdAndStatusOrderByIdDesc(
                storeUserEntity.get().getStoreId(),
                StoreStatus.REGISTERED
        )).orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST, "스토어 정보를 찾을 수 없습니다."));

        return storeUserEntity.map(it -> UserSession.builder()
                .userId(it.getId())
                .email(it.getEmail())
                .password(it.getPassword())
                .status(it.getStatus())
                .role(it.getRole())
                .registeredAt(it.getRegisteredAt())
                .lastLoginAt(it.getLastLoginAt())
                .unregisteredAt(it.getUnregisteredAt())
                //
                .storeId(storeEntity.getId())
                .storeName(storeEntity.getName())
                .build()).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
