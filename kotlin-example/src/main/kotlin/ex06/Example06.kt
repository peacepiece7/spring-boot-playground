package org.example.ex06

fun main() {
    // new keyword 필요없음
    var dog = Dog("해피")
}


interface Bark {
    fun bark()
}

interface Temp {
    fun hi()
}

abstract class Animal (
    private val name: String? = ""
) : Bark // implements 가 여기로 옴, 상속받는 extends, implements가 여기에 따로 구분없이 쓰임
{
    fun eat() {
        println("$name 식사 시작 합니다.")
    }
}


class Dog (
    private val name: String? = null
) : Animal(name) , Temp // Dog 의 파라메터를 Animal 로 넘김 (super 역할)
{
    var age: Int? = 0
        get() = field
        set(value) {
           field = value?.let {it + 1} ?: value
        }

    override fun bark() {
        println("$name 멍멍")
    }

    override fun hi() {}
}
