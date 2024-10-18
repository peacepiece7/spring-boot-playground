package org.example.ex08

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {

}

class Exam08(store: Store) {

    /**
     * 자바는 이렇게 만드는데 코틀린은 초기화 스코프를 제공함
     *
     * 생성자도 가능
     *
     *     constructor(store : Store) {
     *         val strLocalDateTime = toLocalDateTimeString(store.registeredAt)
     *         println(strLocalDateTime)
     *     }
     *
     * 자바도 {}, static {} 블록 있긴 함
     */
    init {
        val strLocalDateTime = toLocalDateTimeString(store.registeredAt)
        println(strLocalDateTime)
    }


    /**
     * 코틀린으로 자바처럼 만들려면 다음과 같이 가능 return 키워드 없어서 알아서 마지막 실행문 리턴
     *
     *          var temp = if(localDateTime == null) {
     *               LocalDateTime.now()
     *           } else {
     *               localDateTime
     *           }
     *         temp.format(DateTimeFormatter.ofPattern("yyyy MM dd"))
     */
    fun toLocalDateTimeString(localDateTime:LocalDateTime?): String {
        return (localDateTime ?: LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy MM dd"))
    }
}

data class Store(
    var registeredAt: LocalDateTime? = null
)
