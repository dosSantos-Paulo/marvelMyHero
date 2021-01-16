package com.example.marvelmyhero.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Card")
data class CardEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo
    val heroName: String,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val imageUrl: String,

    @ColumnInfo
    val durability: Int,

    @ColumnInfo
    val energy: Int,

    @ColumnInfo
    val fightingSkills: Int,

    @ColumnInfo
    val inteligence: Int,

    @ColumnInfo
    val speed: Int,

    @ColumnInfo
    val strength: Int,

    @ColumnInfo
    val description: String,
)