package com.bignerdranch.nyethack

class LootBox<T : Loot>(vararg item: T) {
    var open = false
    private var loot: Array<out T> = item

    operator fun get(index: Int): T? = loot[index].takeIf { open }

    fun fetch(item: Int): T? {
        return loot[item].takeIf { open }
    }

    fun <R> fetch(item: Int, lootModFunction: (T) -> R): R? {
        return lootModFunction(loot[item]).takeIf { open }
    }
}

open class Loot(val value: Int)

class Sweatshirt(val name: String, value: Int) : Loot(value)

class Coin(value: Int) : Loot(value)

fun main(args: Array<String>) {
    val lootBoxOne: LootBox<Sweatshirt> = LootBox(Sweatshirt("a navy blue track sweatshirt", 15),
        Sweatshirt("a red sweatshirt with white drawstrings", 25)
    )
    val lootBoxTwo: LootBox<Coin> = LootBox(Coin(15))

    lootBoxOne.open = true
    lootBoxOne.fetch(1)?.run {
        println("You retrieve $name from the box!")
    }

    val coin = lootBoxOne.fetch(0) {
        Coin(it.value * 3)
    }
    coin?.let { println(it.value) }

    val sweatshirt = lootBoxOne[1]
    sweatshirt?.let { println(it.name) }
}