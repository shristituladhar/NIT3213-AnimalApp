package com.example.nit3213app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import com.example.nit3213app.api.Entity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val entity = intent.getSerializableExtra("entity") as? Entity
        if (entity != null) {
            findViewById<TextView>(R.id.tvSpecies).text = entity.species
            findViewById<TextView>(R.id.tvScientific).text = entity.scientificName
            findViewById<TextView>(R.id.tvHabitat).text = "Habitat: ${entity.habitat}"
            findViewById<TextView>(R.id.tvDiet).text = "Diet: ${entity.diet}"
            findViewById<TextView>(R.id.tvStatus).text = "Conservation Status: ${entity.conservationStatus}"
            findViewById<TextView>(R.id.tvLifespan).text = "Lifespan: ${entity.averageLifespan} years"
            findViewById<TextView>(R.id.tvDescription).text = entity.description
        }

        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnBacktodashboard).setOnClickListener {
            finish()  // takes you back to Dashboard
        }

    }
}