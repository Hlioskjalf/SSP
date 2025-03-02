package com.example.ssp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var playerChoice: String
    private val choices = listOf("Stone", "Scissors", "Paper", "Lizard", "Spock")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttons = mapOf(
            "Stone" to findViewById(R.id.btnRock),
            "Scissors" to findViewById(R.id.btnScissors),
            "Paper" to findViewById(R.id.btnPaper),
            "Lizard" to findViewById(R.id.btnLizard),
            "Spock" to findViewById<Button>(R.id.btnSpock)
        )

        val resultText = findViewById<TextView>(R.id.resultText)
        val playButton = findViewById<Button>(R.id.btnPlay)
        val playerImage = findViewById<ImageView>(R.id.playerChoiceImage)
        val computerImage = findViewById<ImageView>(R.id.computerChoiceImage)
        playerChoice = ""

        val choiceImages = mapOf(
            "Stone" to R.drawable.rock,
            "Scissors" to R.drawable.scissors,
            "Paper" to R.drawable.paper,
            "Lizard" to R.drawable.lizard,
            "Spock" to R.drawable.spock
        )

        buttons.forEach { (choice, button) ->
            button.setOnClickListener {
                playerChoice = choice
                buttons.values.forEach { it.alpha = 1.0f }
                button.alpha = 0.5f
                playerImage.setImageResource(choiceImages[choice]!!)
            }
        }

        playButton.setOnClickListener {
            if (playerChoice.isEmpty()) {
                Toast.makeText(this, "Make a chose:", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val computerChoice = choices.random()
            computerImage.setImageResource(choiceImages[computerChoice]!!)
            val winner = determineWinner(playerChoice, computerChoice)
            resultText.text = "Player: $playerChoice\nAI: $computerChoice\nResult: $winner"
        }
    }

    private fun determineWinner(player: String, computer: String): String {
        if (player == computer) return "Draw!"

        return when {
            (player == "Scissors" && (computer == "Paper" || computer == "Lizard")) ||
                    (player == "Paper" && (computer == "Stone" || computer == "Spoke")) ||
                    (player == "Stone" && (computer == "Lizard" || computer == "Scissors")) ||
                    (player == "Lizard" && (computer == "Spoke" || computer == "Paper")) ||
                    (player == "Spoke" && (computer == "Scissors" || computer == "Stone"))
                -> "You win!"
            else -> "AI win!"
        }
    }
}
