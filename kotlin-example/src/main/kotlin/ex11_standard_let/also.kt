package org.example.ex11_standard_let


// Unit(Kotlin) == void(java)
// Any(Kotlin) == Object(java)
fun main(){


    val hongGilDong = UserDto(name = "홍길동")
        .also {
            // let 과 다르게 넘오온 객체를 그대로 넘김
            var kimGilDong = UserDto(name = "김길동")
            
            // it.name = "최길동" 이렇게 setter 사용하면 바뀜
        }

}


