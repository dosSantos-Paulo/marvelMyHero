package com.example.marvelmyhero.db.repository

import com.example.marvelmyhero.db.entity.CardEntity
import com.example.marvelmyhero.db.dao.CardDao

class CardRepository(private val cardDao: CardDao) {

    suspend fun addCard(card: CardEntity) = cardDao.addCard(card)

    suspend fun count () = cardDao.count()

    suspend fun getCard(id: Int) = cardDao.getCard(id)

    suspend fun getAllCards() = cardDao.getAllCards()

}