package com.catnip.binarychallenge_chapter4.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.catnip.binarychallenge_chapter4.R
import com.catnip.binarychallenge_chapter4.classes.Computer
import com.catnip.binarychallenge_chapter4.classes.User
import com.catnip.binarychallenge_chapter4.contract.IGame
import com.catnip.binarychallenge_chapter4.databinding.ActivityMainBinding
import com.catnip.binarychallenge_chapter4.enum.Hero

class MainActivity : AppCompatActivity(), IGame {
    private val TAG = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding
    private lateinit var computer: Computer
    private lateinit var user: User
    private var isGameFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        computer = Computer()
        user = User()

        startGame()
    }

    override fun startGame() {
        initialHero()
        setHeroAssets()
        initialState()
        setOnClickListeners()
    }

    private fun initialHero() {
        user.heroes = arrayListOf(
            binding.ivRockLeft,
            binding.ivPaperLeft,
            binding.ivScissorsLeft
        )

        computer.heroes = arrayListOf(
            binding.ivRockRight,
            binding.ivPaperRight,
            binding.ivScissorsRight
        )
    }

    private fun setHeroAssets() {
        user.heroes[Hero.ROCK.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_rock
            )
        )
        user.heroes[Hero.PAPER.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_paper
            )
        )
        user.heroes[Hero.SCISSORS.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_scissors
            )
        )

        computer.heroes[Hero.ROCK.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_rock
            )
        )
        computer.heroes[Hero.PAPER.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_paper
            )
        )
        computer.heroes[Hero.SCISSORS.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_scissors
            )
        )

    }

    private fun initialState() {
        (user.heroes + computer.heroes).forEach { it.background = null }
        binding.versus.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.img_vs))
        isGameFinished = false
    }

    private fun setOnClickListeners() {
        user.heroes.forEachIndexed { index, hero ->
            hero.setOnClickListener {
                if (!isGameFinished) {
                    Log.i(TAG, "setOnClickListeners: ${Hero.getValueFromIndex(index)}")
                    user.choice = index
                    it.background = ContextCompat.getDrawable(this, R.drawable.selected_bg)
                    computer.choice = (0..2).random()
                    computer.heroes[computer.choice].background =
                        ContextCompat.getDrawable(this, R.drawable.selected_bg)
                    decideWinner()
                    isGameFinished = true
                }
            }
        }

        binding.restart.setOnClickListener {
            initialState()
        }
    }

    override fun decideWinner() {
        when {
            (user.choice + 1) % 3 == computer.choice -> {
                Log.i(TAG, "decideWinner: Player 2 Win")
                binding.versus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.img_player2_win
                    )
                )
            }
            user.choice == computer.choice -> {
                Log.i(TAG, "decideWinner: Draw")
                binding.versus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.img_draw
                    )
                )
            }
            else -> {
                Log.i(TAG, "decideWinner: Player 1 Win")
                binding.versus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.img_player1_win
                    )
                )
            }
        }
    }
}