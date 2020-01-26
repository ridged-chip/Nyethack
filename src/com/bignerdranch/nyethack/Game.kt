package com.bignerdranch.nyethack

import java.lang.Exception
import java.lang.IllegalStateException

fun main(args: Array<String>) {
    Game.play()
}

object Game {
    private val player = Player("Matt")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room")))

    init {
        println("Matt".frame(5))
        player.castFireball(5)

    }

    private fun String.frame(padding: Int, formatChar: String = "*"): String {
        val greeting = "$this!"
        val middle = formatChar.padEnd(padding)
            .plus(greeting)
            .plus(formatChar.padStart(padding))
        val end = (0 until middle.length).joinToString("") { formatChar }
        return "$end\n$middle\n$end"
    }

    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())

            //Player status
            printPlayerStatus(player)

            print("> Enter command: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(player: Player) {
        println(
            "(Aura: ${player.auraColor()}) " +
                    "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
        )
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.toLowerCase()) {
            "fight" -> fight()
            "move" -> move(argument)
            else -> commandNotFound()
        }
        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    private fun move(directionInput: String) {
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction is out of bounds.")
            }

            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        } catch (e: Exception) {
            "Invalid direction: $directionInput"
        }
    }

    private fun fight() = currentRoom.monster?.let {
        while (player.hp > 0 && it.hp > 0) {
            slay(it)
            Thread.sleep(1000)
        }

        "Combat complete."
    } ?: "There's nothing left to fight."

    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")

        if (player.hp <= 0) {
            println(">>>> You have been defeated. <<<<")
        }

        if (monster.hp <= 0) {
            println(">>>> ${monster.name} has been defeated. <<<<")
        }
    }
}