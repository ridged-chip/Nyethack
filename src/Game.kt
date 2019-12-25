fun main(args: Array<String>) {
    val name = "Matt"
    var hp = 70
    val isBlessed = true
    val isImmortal = false

    // Aura code
    val auraVisible = isBlessed && hp > 50 || isImmortal
    val auraColor = if (auraVisible) "GREEN" else "NONE"

    //Status
    val healthStatus = when (hp) {
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

    //Output
    println("(Aura: $auraColor) " +
    " (Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}