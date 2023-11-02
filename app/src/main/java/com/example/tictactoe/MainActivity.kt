package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: Array<Button> // contains the buttons that make up the play grid
    private lateinit var messageBox: TextView   // the text view where messages will be displayed
    private var currentPlayerX = true           // used to track if X is the current player
    private var continuePlay = true             // used to track if the game is still playable


    // on initial load:
    //  assigns button values to the button array,
    //  calls newGame() to start game,
    //  and sets buttonNewGame onClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button)     // top left grid button
        val button2 = findViewById<Button>(R.id.button2)    // top middle grid button
        val button3 = findViewById<Button>(R.id.button3)    // top right grid button
        val button4 = findViewById<Button>(R.id.button4)    // middle left grid button
        val button5 = findViewById<Button>(R.id.button5)    // center grid button
        val button6 = findViewById<Button>(R.id.button6)    // middle right grid button
        val button7 = findViewById<Button>(R.id.button7)    // bottom left grid button
        val button8 = findViewById<Button>(R.id.button8)    // bottom middle grid button
        val button9 = findViewById<Button>(R.id.button9)    // bottom right grid button
        messageBox = findViewById(R.id.textView)
        buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)
        newGame(buttons)

        // assigns actions to the 'New Game' button
        val buttonNewGame = findViewById<Button>(R.id.button10)
        buttonNewGame.setOnClickListener{
            newGame(buttons)
            continuePlay = true
        }
    }

    // changes the message displayed based on the current turn
    private fun displayMessage(){
        if (currentPlayerX) {
            messageBox.text = getString(R.string.playerX)
        }
        else {
            messageBox.text = getString(R.string.playerO)
        }
    }

    // displays a message when play will no longer continue
    private fun displayEndMessage(endState:String){
        if (endState == "X") {
            messageBox.text = getString(R.string.playerXWins)
        }
        else if (endState == "O") {
            messageBox.text = getString(R.string.playerOWins)
        }
        else{
            messageBox.text = getString(R.string.tieGame)
        }
    }

    // resets the game
    private fun newGame(buttons : Array<Button> ) {
        for (button in buttons) {
            button.text = ""
        }

        currentPlayerX = true
        displayMessage()
    }

    // executes when a player selects a button
    fun btnClick(view: View) {
        if (continuePlay) {
            val buttonSelected = view as Button
            playGame(buttonSelected)
        }
    }

    // marks a button based on the current player and checks if play will continue
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
            checkWinOrTie()
            if (continuePlay) {
                displayMessage()
            }
        }
    }

    // checks for end game conditions and if game is over sets continuePlay to false
    private fun checkWinOrTie() {
        if (buttons[0].text == "X" && buttons[1].text == "X" && buttons[2].text == "X" ||
            buttons[3].text == "X" && buttons[4].text == "X" && buttons[5].text == "X" ||
            buttons[6].text == "X" && buttons[7].text == "X" && buttons[8].text == "X" ||
            buttons[0].text == "X" && buttons[3].text == "X" && buttons[6].text == "X" ||
            buttons[1].text == "X" && buttons[4].text == "X" && buttons[7].text == "X" ||
            buttons[2].text == "X" && buttons[5].text == "X" && buttons[8].text == "X" ||
            buttons[0].text == "X" && buttons[4].text == "X" && buttons[8].text == "X" ||
            buttons[2].text == "X" && buttons[4].text == "X" && buttons[6].text == "X") {
                displayEndMessage("X")
                continuePlay = false
        }
        else if (buttons[0].text == "O" && buttons[1].text == "O" && buttons[2].text == "O" ||
            buttons[3].text == "O" && buttons[4].text == "O" && buttons[5].text == "O" ||
            buttons[6].text == "O" && buttons[7].text == "O" && buttons[8].text == "O" ||
            buttons[0].text == "O" && buttons[3].text == "O" && buttons[6].text == "O" ||
            buttons[1].text == "O" && buttons[4].text == "O" && buttons[7].text == "O" ||
            buttons[2].text == "O" && buttons[5].text == "O" && buttons[8].text == "O" ||
            buttons[0].text == "O" && buttons[4].text == "O" && buttons[8].text == "O" ||
            buttons[2].text == "O" && buttons[4].text == "O" && buttons[6].text == "O") {
                displayEndMessage("O")
                continuePlay = false
        }
        else if (buttons[0].text != "" && buttons[1].text != "" && buttons[2].text != "" &&
            buttons[3].text != "" && buttons[4].text != "" && buttons[5].text != "" &&
            buttons[6].text != "" && buttons[7].text != "" && buttons[8].text != "") {
                displayEndMessage("Tie")
                continuePlay = false
        }
    }
}