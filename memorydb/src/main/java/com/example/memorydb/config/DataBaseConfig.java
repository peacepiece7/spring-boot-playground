package com.example.memorydb.config;


import com.example.memorydb.user.db.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// new Exception 처럼 @Bean 을 달아줄 수 없으면 직접 이렇게 설정해주면 UserRepository 를 서비스에 주입할 수 있음
//@Configuration
//public class DataBaseConfig {
//
//    @Bean
//    public UserRepository userRepository() {
//        return new UserRepository();
//    }
//
//}
