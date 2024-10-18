package org.example.ex07

// data => @Data 임 와우;;
data class User (var name: String?=null, var age: Int?=null, var email:String?=null)


fun main() {
    var user = User();
    user.name = "홍길동"
    user.age = 10
    user.email = "gmail"

    // 매개변수의 순서가 생관 없음
    // 복사 개꿀
    val user2 = user.copy(name = "김길동")
    
    // fill parameter extension 받으면 매개변수 자동으로 채워줌
}