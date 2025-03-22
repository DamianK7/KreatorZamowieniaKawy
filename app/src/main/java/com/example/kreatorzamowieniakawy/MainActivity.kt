package com.example.kreatorzamowieniakawy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.*
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
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val myTextView = findViewById<TextView>(R.id.myTextView)
        val zamowButton = findViewById<Button>(R.id.zamowButton)
        val checkMleko = findViewById<CheckBox>(R.id.checkMleko)
        val checkCukier = findViewById<CheckBox>(R.id.checkCukier)

        myTextView.text = "Ilość kaw: ${seekBar.progress}"

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val imageResource = when (checkedId) {
                R.id.espresso -> R.drawable.espresso
                R.id.cappucino -> R.drawable.capuccino
                R.id.latte -> R.drawable.latte
                else -> R.drawable.capuccino
            }
            imageView.setImageResource(imageResource)
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                myTextView.text = "Ilość kaw: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })


        zamowButton.setOnClickListener {
            val selectedCoffeeId = radioGroup.checkedRadioButtonId
            val selectedCoffee = when (selectedCoffeeId) {
                R.id.espresso -> "Espresso"
                R.id.cappucino -> "Cappuccino"
                R.id.latte -> "Latte"
                else -> "Nie wybrano kawy"
            }


            val dodatki = mutableListOf<String>()
            if (checkMleko.isChecked) dodatki.add("Mleko")
            if (checkCukier.isChecked) dodatki.add("Cukier")

            val dodatkiText = if (dodatki.isNotEmpty()) dodatki.joinToString(", ") else "Brak dodatków"

            val iloscKawy = seekBar.progress

            val zamowienie = "Zamówienie:\nRodzaj kawy: $selectedCoffee\nDodatki: $dodatkiText\nIlość kaw: $iloscKawy"

            Log.d("Zamówienie", zamowienie)
        }
    }
}
