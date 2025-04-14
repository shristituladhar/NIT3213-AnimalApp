package com.example.nit3213app.api

import java.io.Serializable

data class Entity(
    val species: String,
    val scientificName: String,
    val habitat: String,
    val diet: String,
    val conservationStatus: String,
    val averageLifespan: Int,
    val description: String
) : Serializable

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)
