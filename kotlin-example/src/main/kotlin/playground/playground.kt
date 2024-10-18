package org.example.playground


fun main() {
 aboutLet()
}


fun aboutLet() {
    // let 에 대해서 알아보자..
    // https://kotlinlang.org/docs/scope-functions.html#let

    val nums = mutableListOf("1", "two", "three", "four", null ,"2")
    val numsWithNull = nums.map{it + 1}.filterNotNull(); // null + 1 되니까 null 필터링 안됨
    println("not null nums : $numsWithNull")

    var str : String? = "Hello";
    var length = str?.let {  it.length ?: -1 }
}


fun coroutine() {


    suspend fun doSomething () {
    }
}