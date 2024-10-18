package org.example.ex11_standard_let


fun main(){

    // apply , 생성자 패턴
    // this 가지고 있는 스코프를 제공함
    val userDto = UserDto().apply {
        name = "홍길동"

    }

    // 많은 데이터가 들어갈때, name arguments 씀
    UserDto(
//        name = null, age = null, email = null, registeredAt = null
    )

    println(userDto)

}
/*

fun UserDto.myUserDto(){
    println(this.name)
}*/
