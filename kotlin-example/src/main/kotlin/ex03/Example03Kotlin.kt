package org.example.ex03

fun main() {

    // 배열도 가변, 불변이 있음
    // 불변은 ListOf
    // 가변은 mutableListOf 사용
    val userListImmutable = listOf<User>(User("foo", 11))  // userListImmutable.add 불변은 아예 이런 메서드가 사라짐
    val userList = mutableListOf<User>(); // 객체 자체는 참조라서 불변임
    userList.add(User("bar", 22))

    // 동일
    userList.forEachIndexed(fun (idx, user) {
        println("$idx $user")
    })
    userList.forEachIndexed { idx, user ->
        println("$idx $user")
    }

    for(el in userList) {
        println("$el")
    }
    for((idx, el) in userList.withIndex()) {
        println("$idx $el")
    }
}


class User (var name : String, var age : Int)
