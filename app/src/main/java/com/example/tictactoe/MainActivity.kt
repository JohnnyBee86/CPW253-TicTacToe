package com.example.tictactoe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: Array<Button>
    private lateinit var messageBox: TextView
    private var currentPlayerX = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        messageBox = findViewById(R.id.textView)
        buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)
        currentPlayerX = true
        newGame(buttons)

        val buttonNewGame = findViewById<Button>(R.id.button10)
        buttonNewGame.setOnClickListener{
            newGame(buttons)
        }
    }

    private fun displayMessage(){
        if (currentPlayerX) {
            messageBox.text = getString(R.string.playerX)
        }
        else {
            messageBox.text = getString(R.string.playerO)
        }
    }

    private fun newGame(buttons : Array<Button> ) {
        for (button in buttons) {
            button.text = ""
        }

        currentPlayerX = true
        displayMessage()
    }

    fun btnClick(view: View) {
        val buttonSelected = view as Button
        playGame(buttonSelected)
    }

    private fun playGame(buttonSelected: Button) {
        if (buttonSelected.text.equals("")) {
            if (currentPlayerX) {
                buttonSelected.text = "X"
                currentPlayerX = false
            }
            else {
                buttonSelected.text = "O"
                currentPlayerX = true
            }
            displayMessage()
        }
    }
}