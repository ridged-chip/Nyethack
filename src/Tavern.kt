const val TAVERN_NAME = "Taernyl's Folly"

fun main(args: Array<String>) {
    var beverage = readLine()

    if (beverage != null) {
        beverage = beverage.capitalize()
    } else {
        println("I can't capitalize a null value!")
    }

    val beverageServed: String = beverage ?: "Buttered Ale"
    println(beverageServed)
}