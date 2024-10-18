package org.example.ex04

fun main() {
    /**
     * Map도 mutable, immutable이 있음
     */
    
    val map = mapOf<String, Any>(
        Pair("", ""),
        "keyFoo" to "valueFoo",
    )
    val mutableMap = mutableMapOf<String, Any>(Pair("", ""))

    val hashMap = hashMapOf<String,Any>()
    val triple = Triple<String, String, String>(
        first = "fir",
        second = "sec",
        third = "thi"
    )
}