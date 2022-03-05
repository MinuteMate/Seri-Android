package com.hackaton.seriandroid.ui.game.model

data class GameMessage(
    val message: String,
    var name: String,
    var profileUrl: String,
    var viewType: Int,
)