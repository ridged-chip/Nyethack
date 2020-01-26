package com.bignerdranch.nyethack
val String.numVowels
get() = count { "aeiouy".contains(it) }

fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun <T> T.easyPrint() : T {
    println(this)
    return this
}

infix fun String?.printWtihDefault(default: String) = print(this ?: default)

fun main(args: Array<String>) {
    "Matt has left the building".easyPrint().addEnthusiasm().easyPrint()
    42.easyPrint()
    "How many vowels?".numVowels.easyPrint()

    val nullableString: String? = null
    nullableString printWtihDefault "Default string"
}