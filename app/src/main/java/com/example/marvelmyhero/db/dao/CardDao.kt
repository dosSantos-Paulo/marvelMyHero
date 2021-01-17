package com.example.marvelmyhero.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.marvelmyhero.db.entity.CardEntity

@Dao
interface CardDao {

    @Insert
    suspend fun addCard(card: CardEntity)

}