package com.bignerdranch.nyethack

class Barrel<in T>(item: T)

fun main(args: Array<String>) {
    var sweatshirtBarrel: Barrel<Sweatshirt> = Barrel(Sweatshirt("a navy blue track sweatshirt", 15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(15))

    sweatshirtBarrel = lootBarrel
}