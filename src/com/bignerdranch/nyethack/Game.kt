package com.bignerdranch.nyethack

import java.lang.Exception
import java.lang.IllegalStateException

fun main(args: Array<String>) {
    Game.play()
}

object Game {
    private val player = Player("Matt")
    private var currentRoom: Room = TownSquare()
    private var stillPlaying = true

    private val worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room")))

    init {
        println("Welcome, adventurer!")
        player.castFireball(5)

    }

    fun play() {
        while (stillPlaying) {
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
            "move" -> move(argument)
            "quit" -> stillPlaying = false
            "map" -> showMap()
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

    private fun showMap() {
        worldMap.forEachIndexed { y, _ ->
            worldMap[y].forEachIndexed { x, _ ->
                if (worldMap[y][x] == currentRoom) {
                    print("X ")
                } else {
                    print("O ")
                }
            }
            print("\n")
        }
    }
}