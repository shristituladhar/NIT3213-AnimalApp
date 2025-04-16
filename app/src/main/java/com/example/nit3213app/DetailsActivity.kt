package com.example.nit3213app

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        val container = findViewById<LinearLayout>(R.id.detailsContainer)

        if (entity?.properties != null) {
            val entries = entity.properties.entries.toList()
            
            // Add title (first property)
            if (entries.isNotEmpty()) {
                val titleTv = TextView(this)
                titleTv.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                titleTv.text = entries[0].value.toString()
                titleTv.textSize = 26f
                titleTv.setTextColor(resources.getColor(android.R.color.system_accent3_700, theme))
                titleTv.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                titleTv.setTypeface(titleTv.typeface, android.graphics.Typeface.BOLD)
                titleTv.setPadding(0, 32, 0, 16)
                container.addView(titleTv, container.childCount - 1)
            }
            
            // Add subtitle (second property)
            if (entries.size > 1) {
                val subtitleTv = TextView(this)
                subtitleTv.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                subtitleTv.text = entries[1].value.toString()
                subtitleTv.textSize = 18f
                subtitleTv.setTextColor(resources.getColor(android.R.color.system_accent3_600, theme))
                subtitleTv.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                subtitleTv.setTypeface(subtitleTv.typeface, android.graphics.Typeface.BOLD)
                subtitleTv.setPadding(0, 0, 0, 16)
                container.addView(subtitleTv, container.childCount - 1)
            }
            
            // Add remaining properties
            for (i in 2 until entries.size) {
                val tv = TextView(this)
                tv.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                tv.text = "${entries[i].key.replaceFirstChar { it.uppercase() }}: ${entries[i].value}"
                tv.textSize = 16f
                tv.setPadding(0, 8, 0, 8)
                container.addView(tv, container.childCount - 1)
            }
        } else {
            val tv = TextView(this)
            tv.text = "No data available"
            tv.textSize = 18f
            tv.setPadding(0, 16, 0, 0)
            container.addView(tv, container.childCount - 1)
        }

        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnBacktodashboard).setOnClickListener {
            finish()
        }
    }
}
