package com.example.nit3213app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213app.api.ApiClient
import com.example.nit3213app.api.Entity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import com.example.nit3213app.api.ApiService



class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Setup RecyclerView (from Session 6)
        val recyclerView = findViewById<RecyclerView>(R.id.entityRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val entityAdapter = EntityAdapter(this, emptyList()) { selectedEntity ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("entity", selectedEntity)
            startActivity(intent)
        }

        // Set adapter to the RecyclerView
        recyclerView.adapter = entityAdapter

        // Receive keypass from Login screen
        val keypass = intent.getStringExtra("keypass")

        if (keypass != null) {
            lifecycleScope.launch {
                try {
                    val apiService: ApiService by inject()
                    val response = apiService.getDashboard(keypass)
                    if (response.isSuccessful && response.body() != null) {
                        val entityList: List<Entity> = response.body()!!.entities
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