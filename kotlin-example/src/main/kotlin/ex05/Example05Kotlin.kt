package org.example.ex05

import java.util.function.Predicate


fun main() {
    var numList = listOf<Int>(1,2,3,4,5);

    // 익명 함수를 만들 때 object 키워드를 사용

    var predicate1 = object : Predicate<Int> {
        override fun test(t : Int) : Boolean {
            return t % 2 == 0;
        }
    }
    var predicate2 = Predicate<Int> { t -> t % 2 == 0; }

    numList.filter { t -> t%2 == 0 }

    val add = {x : Int, y : Int -> x + y};
    add(10, 20)

    val _add = fun(x : Int, y:Int) : Int {
        return x + y;
    }

    var cadd = fun(x : Int): (Int) -> Int {
        return fun(y : Int) : Int {
            return x + y
        }
    }
    var _cadd = {x: Int -> { y: Int -> x + y}};

    var saveX = cadd(10)
    println(saveX(20)) // 30
    println(saveX(30)) // 40
}
