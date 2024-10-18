package org.example.ex01


fun main() {
    /**
     * 특징
     * 1. var(mutable), val(constant)
     * 2. 전부 reference type (int, char 없음)
     * 3. 모든 것은 객체로 관리 됨
     * 4. type inference 가능
     */
    var name: String = "hong gil dong"
    name = "kim gil dong"
    val _name: String = "lee gil dong"
    var __name = "choi gil dong"

    var i = 0;
    var _i : Int = 10;

    var d = 10.0;
    var _d : Double = 20.00;

    var f = 0.221;
    var _f: Float;

    var l : Long;
    var _l : Long = 1111L;

    var b: Boolean;

    println("사용자의 이름은 $name")
}