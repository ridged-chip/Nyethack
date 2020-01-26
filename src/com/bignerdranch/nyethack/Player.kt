package com.bignerdranch.nyethack

import java.io.File
import com.bignerdranch.nyethack.extensions.random as randomizer



class Player(_name: String,
             override var hp: Int = 100,
             var isBlessed: Boolean,
             private val isImmortal: Boolean) : Fightable {

    override val diceNum = 3
    override val diceSides = 6
    override fun attack(opponent: Fightable): Int {
        val dmgDealt = if (isBlessed) {
            dmgRoll*2
        } else {
            dmgRoll
        }
        opponent.hp -= dmgDealt
        return dmgDealt
    }

    var name = "matt"
    get() = "${field.capitalize()} of $hometown"
    private set(value) {
        field = value.trim()
    }

    val hometown = selectHometown()
    var currentPosition = Coordinate(0, 0)

    init {
        require(hp > 0, { "HP must be above zero!" })
        require(name.isNotBlank(), { "Our hero must have a name assigned!" })
    }

    constructor(name: String) : this(name,
        hp = 100,
        isBlessed = true,
        isImmortal = false) {
        if (name.toLowerCase() == "kar") hp = 40
    }


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

    private fun selectHometown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .randomizer()
}