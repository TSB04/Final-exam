package com.example.finalexamapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room Database Setup
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)