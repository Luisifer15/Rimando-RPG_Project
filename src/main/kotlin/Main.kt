package org.example

fun main() {

    //Modify stats and names in the config.kt file

    val hero = Hero(CharacterConfig.heroName, CharacterConfig.heroStats)
    val enemy = Enemy(CharacterConfig.enemyName, CharacterConfig.enemyStats)

    val game = Game(hero, enemy)
    game.startGame()
}