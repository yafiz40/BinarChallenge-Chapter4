package com.catnip.binarychallenge_chapter4.enum

enum class Hero(val index: Int) {
    ROCK(0),
    PAPER(1),
    SCISSORS(2);
    companion object {
        fun getValueFromIndex(index: Int) = values()[index]
    }
}