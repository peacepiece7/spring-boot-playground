package org.example.exam10


fun exam10(examUser : ExamUser) {


    if(examUser.isNotNull() && examUser.name.isNotNullOrBlank()) {
        println(examUser.name);
    }

}

class ExamUser(
    var name : String? = null
)


fun String?.isNotNullOrBlank() : Boolean {
    return !this.isNotNullOrBlank()
}

fun Any?.isNotNull() : Boolean {
    return this != null
}