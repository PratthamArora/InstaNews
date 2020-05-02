package com.pratthamarora.instanews.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.pratthamarora.instanews.repo.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
}