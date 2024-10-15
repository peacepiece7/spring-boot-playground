# Spring security 설정하기

## 설치

gradle.build 파일에 spring security 를 추가한다.

```txt
    implementation 'org.springframework.boot:spring-boot-starter-security'
    testImplementation 'org.springframework.security:spring-security-test'
```

## security web configuration 설정

`@EnableWebSecurity` 어노테이션을 추가하면 security 설정 파일로 동작한다.

다음과 같이 설정했다.

```java
package org.delivery.storeadmin.config.security;

import java.util.List;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private List<String> SWAGGER = List.of(
            "/swagger-ui/index.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /**
         * @see https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#disable-csrf
         */
        httpSecurity.csrf((csrf) -> csrf.disable()); // csrf(AbstractHttpConfigurer::disable)
        httpSecurity.authorizeHttpRequests(authorize ->
                authorize
                        // resource(assets)에 대해서 모든 요청 승인
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations()
                        )
                        .permitAll()
                        // swagger 인증 없이 통과
                        .requestMatchers(SWAGGER.toArray(new String[0]))
                        .permitAll()
                        // open-api / ** 하위 모든 주소는 인증 없이 통과
                        .requestMatchers(
                                "/open-api/**"
                        ).permitAll()
                        // 그 외 모든 요청은 인증 사용
                        .anyRequest().authenticated());
        // 어떤 경로로 접속하던지 security 제공하는 default login form 출력
        httpSecurity.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // hash 암호화
        return new BCryptPasswordEncoder();
    }

}
```

## security service 설정

`UserDetailsService`인터페이스를 제공한다.\
이를 사용하여 서비스를 구현하면 된다.

```java
package org.delivery.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

        var storeEntity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(
                storeUserEntity.get().getStoreId(),
                StoreStatus.REGISTERED
        );

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
                .storeId(storeEntity.get().getId())
                .storeName(storeEntity.get().getName())
                .build()).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
```


## UserSession model 설정

security 에서 `UserDetails` 인터페이스를 제공한다.\
이를 상속하는 모델을 만들면 된다.
```java
package org.delivery.storeadmin.domain.authorization.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.storeuser.enums.StoreUserRole;
import org.delivery.db.storeuser.enums.StoreUserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSession implements UserDetails {
    // user
    private Long userId;

    private String email;

    private String password;

    private StoreUserStatus status;

    private StoreUserRole role;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

    // store
    private Long storeId;
    private String storeName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == StoreUserStatus.REGISTERED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == StoreUserStatus.REGISTERED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == StoreUserStatus.REGISTERED;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```

## Controller 에서 session 정보 가져오기

`service-api` 프로젝트에서는 security 가 없어서 직접 `@UserSession` 어노테이션을 만들어서 사용했는데\
security 는  `@AuthenticationPrincipal` 어노테이션을 제공한다.

```java
package org.delivery.storeadmin.domain.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store-user")
@RequiredArgsConstructor
public class StoreUserApiController {
    private final StoreUserConverter storeUserConverter;

    @GetMapping("/me")
    public StoreUserResponse me(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            UserSession usersession
    ) {
        return storeUserConverter.toResponse(usersession);
    }
}
```

## thymeleaf

gradle.build 파일에 thymeleaf 패키지 추가

```text
    // thymeleaf
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    // https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-springsecurity5
    implementation group: 'org.thymeleaf.extras', name: 'thymeleaf-extras-springsecurity5', version: '3.1.2.RELEASE'
```



### thymeleaf 설정

1. resources/templates 하위에 html 파일을 만든다.
2. presentation/PageController 에서 정적 html 바인딩 한다.

### thymeleaf 사용 방법

컨트롤러는 `@Controller` 어노테이션을 붙여줘야하고, `model.set` 메서드로 데이터를 주입할 수 있다.

```java
package org.delivery.storeadmin.domain.presentation;

import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller // @RestController 아님!
@RequestMapping("")
public class PageController {

    @RequestMapping(path = {"/", "/main"})
    public ModelAndView main(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {}\n", authentication);
        log.info("Authentication: {}\n", authentication.getPrincipal());
        if (authentication.getPrincipal() instanceof UserSession userSession) {
            model.addAttribute("email", userSession.getEmail());
            model.addAttribute("storeName", userSession.getStoreName());
            model.addAttribute("role", userSession.getRole());
        } else {
            model.addAttribute("email", "No Email");
            model.addAttribute("storeName", "no store name");
            model.addAttribute("role", "no role");
        }
        return new ModelAndView("main"); // resources/templates/main.html 와 바인딩 됩니다.
    }

    @RequestMapping(path = {"/order"})
    public ModelAndView order() {
        return new ModelAndView("order/order"); // resources/templates/order/order.html 와 바인딩 됩니다.
    }
}
```

정적 html 파일에서는 다음과 같이 데이터를 출력한다.

```html
<!DOCTYPE html>
<html lang="kor" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>MAIN PAGE</h1>
<ul>
    <li>
        <p th:text="${storeName}"></p>
    </li>
    <li>
        <p th:text="${role}"></p>
    </li>
    <li>
        <p th:text="${email}"></p>
    </li>
</ul>
</body>
</html>
```