package com.bignerdranch.nyethack
import java.util.*

interface Fightable {
    var hp: Int
    val diceNum: Int
    val diceSides: Int
    val dmgRoll: Int
    get() = (0 until diceNum).map {
        Random().nextInt(diceSides + 1)
    }.sum()

    fun attack(opponent: Fightable): Int
}

abstract class Monster(val name: String,
                        val description: String,
                        override var hp: Int) : Fightable {

    override fun attack(opponent: Fightable): Int {
        val dmgDealt = dmgRoll
        opponent.hp -= dmgDealt
        return dmgDealt
    }
}

class Goblin(name: String = "Goblin",
             description: String = "A nasty-looking goblin",
             hp: Int = 30) : Monster(name, description, hp) {

    override val diceNum = 2
    override val diceSides = 8
}