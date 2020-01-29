package com.example.models

data class Match(
    var penalty: Boolean = false, val playerOne: Player, val playerTwo: Player,
    var playerOneGoal: Int = -1, var playerTwoGoal: Int = -1, var playerOnePenalty: Int = -1,
    var playerTwoPenalty: Int = -1
) {

    fun retrieveWinner(): Player {
        return if (penalty) {
            if (playerOnePenalty > playerTwoPenalty) playerOne else playerTwo
        } else {
            if (playerOneGoal > playerTwoGoal) playerOne else playerTwo
        }
    }
}

