package com.pratthamarora.instanews.repo

import com.pratthamarora.instanews.api.RetrofitInstance
import com.pratthamarora.instanews.data.db.ArticleDatabase

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}