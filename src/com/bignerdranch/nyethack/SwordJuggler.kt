package com.bignerdranch.nyethack

import java.lang.Exception

fun main(args: Array<String>) {
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient) {
        swordsJuggling = 2
    }

    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }

    println("You juggle $swordsJuggling swords!")
}

fun proficiencyCheck(swordsJuggling: Int?) {
    checkNotNull(swordsJuggling, {"com.bignerdranch.nyethack.Player cannot juggle swords"})
}

fun juggleSwords(swordsJuggling: Int) {
    require(swordsJuggling >= 3, { "Juggle at least 3 swords to be exciting." })
}