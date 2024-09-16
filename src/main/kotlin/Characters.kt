package org.example

data class CharacterStats(var health: Int, var armor: Int, var attackPower: Int)

interface CharacterActions {
    fun attack(opponent: Character)
    fun defend()
    fun heal()
}

abstract class Character(val name: String, val stats: CharacterStats) : CharacterActions {
    var defenseTurnsLeft: Int = 0
    val defaultArmor: Int = stats.armor

    abstract override fun attack(opponent: Character)
    abstract override fun defend()
    abstract override fun heal()

    fun isAlive(): Boolean {
        return stats.health > 0
    }
}

class Hero(name: String, stats: CharacterStats) : Character(name, stats) {
    override fun attack(opponent: Character) {
        val damage = stats.attackPower - opponent.stats.armor
        if (damage > 0) {
            opponent.stats.health -= damage
            println("$name attacks ${opponent.name} for $damage damage!")
        } else {
            println("$name attacks ${opponent.name}, but it's not effective!")
        }
        if (opponent.defenseTurnsLeft > 0) {
            opponent.defenseTurnsLeft--
            if (opponent.defenseTurnsLeft == 0) {
                opponent.stats.armor = opponent.defaultArmor
                println("${opponent.name}'s defense boost has worn off! DEF is now ${opponent.stats.armor}")
            }
        }

    }

    override fun defend() {
        stats.armor += 5
        defenseTurnsLeft = (1..5).random()
        println("$name is defending! DEF increased to ${stats.armor} for $defenseTurnsLeft turns")
    }

    override fun heal() {
        val healAmount = 10
        stats.health = minOf(stats.health + healAmount, CharacterConfig.maxHealth)
        println("$name heals for $healAmount HP!")
    }
}

class Enemy(name: String, stats: CharacterStats) : Character(name, stats) {
    override fun attack(opponent: Character) {
        val damage = stats.attackPower - opponent.stats.armor
        if (damage > 0) {
            opponent.stats.health -= damage
            println("$name attacks ${opponent.name} for $damage damage!")
        } else {
            println("$name attacks ${opponent.name}, but it's not effective!")
        }
        if (opponent.defenseTurnsLeft > 0) {
            opponent.defenseTurnsLeft--
            if (opponent.defenseTurnsLeft == 0) {
                opponent.stats.armor = opponent.defaultArmor
                println("${opponent.name}'s defense boost has worn off! DEF is now ${opponent.stats.armor}")
            }
        }
    }

    override fun defend() {
        stats.armor += 5
        defenseTurnsLeft = (1..5).random()
        println("$name is defending! DEF increased to ${stats.armor} for $defenseTurnsLeft turns")

    }

    override fun heal() {
        val healAmount = 10
        stats.health = minOf(stats.health + healAmount, CharacterConfig.maxHealth)
        println("$name heals for $healAmount HP!")
    }
}
