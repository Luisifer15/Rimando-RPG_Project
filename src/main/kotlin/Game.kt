package org.example

interface GameInterface {
    fun startGame()
    fun heroTurn(hero: Hero, enemy: Enemy)
    fun enemyTurn(hero: Hero, enemy: Enemy)
}

class Game(private val hero: Hero, private val enemy: Enemy) : GameInterface {
    private var turnCounter: Int = 0

    override fun startGame() {
        while (hero.isAlive() && enemy.isAlive()) {
            heroTurn(hero, enemy)
            if (enemy.isAlive()) {
                enemyTurn(hero, enemy)
            }
            turnCounter++
        }

        if (hero.isAlive()) {
            println("Hero ${hero.name} wins!")
        } else {
            println("Enemy ${enemy.name} wins!")
        }
        println("The game ended in $turnCounter turns.")
    }

    override fun heroTurn(hero: Hero, enemy: Enemy) {
        println("\nHero's Turn:")
        println("Hero: HP = ${hero.stats.health}, DEF = ${hero.stats.armor}")
        println("Enemy: HP = ${enemy.stats.health}, DEF = ${enemy.stats.armor}")
        when ((1..3).random()) {
            1 -> hero.attack(enemy)
            2 -> hero.defend()
            3 -> hero.heal()
        }
    }

    override fun enemyTurn(hero: Hero, enemy: Enemy) {
        println("\nEnemy's Turn:")
        println("Hero: HP = ${hero.stats.health}, DEF = ${hero.stats.armor}")
        println("Enemy: HP = ${enemy.stats.health}, DEF = ${enemy.stats.armor}")
        when ((1..3).random()) {
            1 -> enemy.attack(hero)
            2 -> enemy.defend()
            3 -> enemy.heal()
        }
    }
}