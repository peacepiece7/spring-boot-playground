package org.example.ex11_standard_let

import java.time.LocalDateTime


fun ex11 (){
    val now = LocalDateTime.now()
    val localDateTime : LocalDateTime?=null

    val kst = now?.let { it ->
        println(it)
    } ?: LocalDateTime.now()


    // 배열이라면 map, 객체라면 let
    val userDtoList = listOf(UserDto("홍길동"), UserDto("유관순"))

    val responseList = userDtoList.stream()
        .map { it ->
            UserResponse(
                userName = it.name
            )
        }.toList()
}

fun logic(userDto: UserDto?) : UserResponse? {
    return userDto?.let {
        println("userDto: $it")
        UserEntity(it.name)
    }?.let { userEntity ->
        UserResponse(
            userEntity.name
        )
    }


}

data class UserDto(
    var name: String?=null,
)

data class UserEntity(
    var name: String?=null
)

data class UserResponse(
    var userName: String?=null
)