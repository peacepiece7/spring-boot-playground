package org.example.ex09_when



fun main(){


    // break 키워드 노 필요
    val result = when(""){
        "" -> {
            ""
        }
        "MASTER","ADMIN" -> {
            "master"
        }
        "USER" -> {
            "user"
        }
        else -> {
            "default"
        }
    }

    // 인스턴스는 is 키워드가 앞에 붙어야하나봄
    var any : Any = "";
    var exception = RuntimeException()
    when(exception){
        is NullPointerException -> {

        }
        is RuntimeException -> {

        }
    }

    var number = 13


    when(val n = number % 2){
        0 -> {
            println(n)
        }
        else ->{
            println(n)
        }
    }

    val r = when{
        number % 2 == 0 ->{
            100
        }
        else -> {
            200
        }
    }
    println(r)

}