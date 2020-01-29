package com.example.models

import kotlin.random.Random

data class Phase(
    var playersList: ArrayList<Player> = arrayListOf(),
    var matchesList: ArrayList<Match>? = arrayListOf(),
    var hasNotPlayed: Player? = null
) {

    fun generateMatchesList() {
        if (hasNotPlayed !== null) {
            val random = Random.nextInt(0, playersList.lastIndex)
            matchesList?.add(Match(playerOne = hasNotPlayed!!, playerTwo = playersList[random]))
            playersList.remove(playersList[random])
            playersList.add(matchesList?.last()!!.retrieveWinner())
            generateMatchesList()
        } else {
            if (playersListIsOdd()) {
                val random = Random.nextInt(0, playersList.lastIndex)
                hasNotPlayed = playersList[random]
                playersList.removeAt(random)
            }
            while (playersList.isNotEmpty()) {
                val firstRandomNumber = Random.nextInt(0, playersList.lastIndex)
                var secondRandomNumber = firstRandomNumber
                while (secondRandomNumber == firstRandomNumber) {
                    secondRandomNumber = Random.nextInt(0, playersList.lastIndex)
                }
                matchesList?.add(
                    Match(
                        playerOne = playersList[firstRandomNumber],
                        playerTwo = playersList[secondRandomNumber]
                    )
                )
            }
        }
    }
    private fun playersListIsOdd(): Boolean {
        return playersList.size % 2 != 0
    }
}