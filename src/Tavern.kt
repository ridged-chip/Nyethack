import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"
var playerGold = 10
var playerSilver = 10

fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
}

fun performPurchase(price: Double) {
    displayCoins()
    val balance = playerGold + (playerSilver / 100.0)
    println("Gross Balance: $$balance")
    println("Purchasing item for $price")
    val remainingBalance = balance - price
    println("Net Balance: ${"%.2f".format(remainingBalance)}") //Format string
    
    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayCoins()
}

private fun displayCoins() {
    println("Player's balance: playerGold: $playerGold, playerSilver: $playerSilver")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price"
    println(message)

    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "Madrigal says: Thanks for the $name"
    }
    println(phrase)
}