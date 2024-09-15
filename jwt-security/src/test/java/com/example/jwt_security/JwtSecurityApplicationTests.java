package com.example.jwt_security;

import com.example.jwt_security.serivce.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class JwtSecurityApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void createJwtTest() {
		var jwtService = new JwtService();

		var userData = new HashMap<String, Object>();
		userData.put("name", "john");
		userData.put("password" , "1111");

		Calendar calendar = Calendar.getInstance();

		// 1분을 더함
		calendar.add(Calendar.MINUTE, 1);

		// 1분 후의 시간으로 Date 객체 생성
		Date date = calendar.getTime();

		LocalDateTime expireDate = date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		var compact = jwtService.create(userData, expireDate);
		System.out.println("COMPACT: "+ compact);
	}
}
