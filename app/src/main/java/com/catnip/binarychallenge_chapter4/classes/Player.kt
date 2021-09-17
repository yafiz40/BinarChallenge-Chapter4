package com.catnip.binarychallenge_chapter4.classes

import android.widget.ImageView

abstract class Player {
    lateinit var name: String
    var choice: Int = -1
    lateinit var heroes: ArrayList<ImageView>
}