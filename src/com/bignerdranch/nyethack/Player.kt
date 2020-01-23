package com.bignerdranch.nyethack

class Player {
    var name = "matt"
    get() = field.capitalize()
    private set(value) {
        field = value.trim()
    }

    var hp = 70
    val isBlessed = true
    private val isImmortal = false


    fun castFireball(numFireballs: Int = 2) {
        println("A glass of Fireball springs into existence. (x$numFireballs)")
    }

    fun auraColor(): String {
        val auraVisible = isBlessed && hp > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus() =
        when (hp) {
            100 -> "is in excellent condition!"
            in 75..99 -> "has a few minor cuts and bruises here and there."
            in 50..74 -> if (isBlessed) {
                "has some lacerations, but is rapidly recovering"
            } else {
                "has some lacerations and potentially internal bleeding."
            }
            in 25..49 -> "is hemorrhaging blood and has multiple fractures. Seek medical attention immediately!"
            else -> "has ruptured organs and is bordering on shock. I hope you wrote your will..."
        }
}