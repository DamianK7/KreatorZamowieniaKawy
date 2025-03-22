package com.example.kreatorzamowieniakawy

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageView = findViewById<ImageView>(R.id.imageViewKawy)

        val radioGroup = findViewById<RadioGroup>(R.id.rodzajKawyRG)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val imageResource = when (checkedId) {
                R.id.espresso -> R.drawable.espresso
                R.id.cappucino -> R.drawable.capuccino
                R.id.latte -> R.drawable.latte
                else -> R.drawable.capuccino
            }
            imageView.setImageResource(imageResource)
        }

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val myTextView = findViewById<TextView>(R.id.myTextView)

        myTextView.text = "Ilość kaw: ${seekBar.progress}"

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                myTextView.text = "Ilość kaw: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

}