package com.example.models

import kotlin.random.Random


data class Championship(
    var phasesList: ArrayList<Phase>,
    var champion: Player?,
    var players: ArrayList<Player>
) {

    fun generateFirstPhase() {
        val phase = Phase(players)
        phase.generateMatchesList()
        phasesList.add(phase)
    }

    fun generateNextPhase() {
        val phase = Phase()
        phasesList.last().matchesList?.forEach {
            phase.playersList.add(it.retrieveWinner())
        }
        if (phasesList.last().hasNotPlayed !== null) {
            phase.hasNotPlayed = phasesList.last().hasNotPlayed
        }
    }

}