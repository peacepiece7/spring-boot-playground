package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountApiController {
    private final AccountRepository accountRepository;


    @GetMapping("/me")
    public AccountMeResponse me() {
        return AccountMeResponse.builder()
                .name("홀길동")
                .email("foo@bar.com")
                .registeredAt(LocalDateTime.now()) // ISO 8601 spec YYYY-MM-DDThh.mm.ss
                .build();
    }

}
