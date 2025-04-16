package com.example.nit3213app.api

import java.io.Serializable

data class Entity(
    val properties: Map<String, String>
) : Serializable

data class DashboardResponse(
    val entities: List<Map<String, Any>>,
    val entityTotal: Int
)
