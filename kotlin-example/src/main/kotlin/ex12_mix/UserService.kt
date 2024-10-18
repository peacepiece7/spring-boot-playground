package org.example.ex12_mix

import org.example.user.UserDtoMg
import java.time.LocalDateTime

fun main() {
    val userService = UserService()
    userService.logic()
}

class UserService {
    fun logic () {
        // java 가져오면 named parameter 안됨 ㅜㅜ
        val userDto = UserDtoMg(
           null,
            "foo@bar.com",
            11,
            "REGISTERED",
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now()
        )
        userDto.name?.let {println(it.length)} ?: println("null".length)
    }
}