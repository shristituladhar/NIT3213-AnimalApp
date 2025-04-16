package com.example.nit3213app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213app.api.ApiService
import com.example.nit3213app.api.Entity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.entityRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val entityAdapter = EntityAdapter(this, emptyList()) { selectedEntity ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("entity", selectedEntity)
            startActivity(intent)
        }
        recyclerView.adapter = entityAdapter

        val logoutButton = findViewById<com.google.android.material.button.MaterialButton>(R.id.btnLogout)
        logoutButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val keypass = intent.getStringExtra("keypass")
        val apiService: ApiService by inject()

        if (keypass != null) {
            lifecycleScope.launch {
                try {
                    val response = apiService.getDashboard(keypass)
                    if (response.isSuccessful && response.body() != null) {
                        val entityList = response.body()!!.entities.map { raw ->
                            // Convert all values to strings
                            val properties = raw.mapValues { it.value.toString() }
                            Entity(properties)
                        }
                        entityAdapter.updateData(entityList)
                    } else {
                        Toast.makeText(this@DashboardActivity, "Failed to load dashboard", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@DashboardActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "No keypass received from Login", Toast.LENGTH_SHORT).show()
        }
    }
}
