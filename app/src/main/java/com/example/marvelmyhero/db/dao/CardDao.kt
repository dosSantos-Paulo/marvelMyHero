package com.example.marvelmyhero.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelmyhero.db.entity.CardEntity

@Dao
interface CardDao {

    @Insert
    suspend fun addCard(card: CardEntity)

    @Query("SELECT COUNT(*) FROM Card")
    suspend fun count(): Int

    @Query("SELECT * FROM Card WHERE id = :id")
    suspend fun getCard(id: Int): CardEntity

    @Query("SELECT * FROM Card")
    suspend fun getAllCards(): List<CardEntity>

}