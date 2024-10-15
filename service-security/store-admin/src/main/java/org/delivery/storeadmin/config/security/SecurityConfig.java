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