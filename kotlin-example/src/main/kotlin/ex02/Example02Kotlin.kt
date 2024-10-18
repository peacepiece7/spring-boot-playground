package org.example.ex02

fun main() {

    /**
     * 엘비스 연산자, js의 Optional type과 같음, null 할당 가능
     *
     * default value 줄 수 있음 (default value 있으면 값 할당 안해도 됨)
     */
    var d : Int? = null;
    var _d = 20;

    fun callFunction(i : Int? = 10) {
        println("call function: $i")

        var temp = i?: "temp is null"

        var _temp = if(i == null) i else "_temp is null";

        // let => this scope 제공
        val temp2 = i?.let {
            it * 20
        }

        val temp3 = i?.run {
            println("this: $this")
        }

        println("temp2: $temp2, temp3: $temp3")
    }


    callFunction(_d)
    callFunction()
}