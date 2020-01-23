package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val player = Player("Matt")

    printPlayerStatus(player)
    player.castFireball(5)
}

private fun printPlayerStatus(player: Player) {
    println(
        "(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
    )
    println("${player.name} ${player.formatHealthStatus()}")
}