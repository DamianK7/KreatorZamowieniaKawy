package com.example.kreatorzamowieniakawy

import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val myTextView = findViewById<TextView>(R.id.myTextView)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                myTextView.text = "Ilość kaw: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(this@MainActivity, "Zacząłeś przesuwać suwak", Toast.LENGTH_SHORT).show()
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(this@MainActivity, "Zakończyłeś przesuwanie suwaka", Toast.LENGTH_SHORT).show()
            }
        })
    }

}