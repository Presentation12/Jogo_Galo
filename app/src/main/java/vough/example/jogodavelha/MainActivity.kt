package vough.example.jogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var isPlayer1 = true
    var gameEnd = false



    private lateinit var center: ImageView
    private lateinit var centerStart: ImageView
    private lateinit var centerEnd: ImageView

    private lateinit var top: ImageView
    private lateinit var topStart: ImageView
    private lateinit var topEnd: ImageView

    private lateinit var bottom: ImageView
    private lateinit var bottomStart: ImageView
    private lateinit var bottomEnd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reset: Button = findViewById(R.id.button_reset)
        reset.setOnClickListener {
            resetBox(center)
            resetBox(centerStart)
            resetBox(centerEnd)

            resetBox(top)
            resetBox(topStart)
            resetBox(topEnd)

            resetBox(bottom)
            resetBox(bottomStart)
            resetBox(bottomEnd)
        }

        center = findViewById(R.id.center)
        centerStart = findViewById(R.id.centerStart)
        centerEnd = findViewById(R.id.centerEnd)

        top = findViewById(R.id.top)
        topStart = findViewById(R.id.topStart)
        topEnd = findViewById(R.id.topEnd)

        bottom = findViewById(R.id.bottom)
        bottomStart = findViewById(R.id.bottomStart)
        bottomEnd = findViewById(R.id.bottomEnd)

        configureBox(center)
        configureBox(centerStart)
        configureBox(centerEnd)

        configureBox(top)
        configureBox(topStart)
        configureBox(topEnd)

        configureBox(bottom)
        configureBox(bottomStart)
        configureBox(bottomEnd)

    }

    private fun resetBox(box: ImageView)
    {
        box.setImageDrawable(null)
        box.tag = null
        isPlayer1 = true
        gameEnd = false
    }

    // função que insere imagem de x ou o no quadrado
    private fun configureBox(box: ImageView)
    {
        box.setOnClickListener {
            if (box.tag == null && gameEnd == false) {
                if (isPlayer1) {
                    box.setImageResource(R.drawable.ic_baseline_circle_24)
                    isPlayer1 = false
                    box.tag = 1
                } else {
                    box.setImageResource(R.drawable.ic_baseline_close_24)
                    isPlayer1 = true
                    box.tag = 2
                }
                if (playerWin(1))
                {
                    Toast.makeText(this@MainActivity, "Player 1 win", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }
                else if (playerWin(2))
                {
                    Toast.makeText(this@MainActivity, "Player 2 win", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }
            }
        }
    }

    private fun playerWin(value: Int): Boolean
    {
        if((top.tag == value && center.tag == value && bottom.tag == value) ||
            (topStart.tag == value && centerStart.tag == value && bottomStart.tag == value) ||
            (topEnd.tag == value && centerEnd.tag == value && bottomEnd.tag == value) ||
            (topStart.tag == value && top.tag == value && topEnd.tag == value) ||
            (centerStart.tag == value && center.tag == value && centerEnd.tag == value) ||
            (bottomStart.tag == value && bottom.tag == value && bottomEnd.tag == value) ||
            (topStart.tag == value && center.tag == value && bottomEnd.tag == value) ||
            (topEnd.tag == value && center.tag == value && bottomStart.tag == value)
        ){
            return true
        }
        else return false
    }
}