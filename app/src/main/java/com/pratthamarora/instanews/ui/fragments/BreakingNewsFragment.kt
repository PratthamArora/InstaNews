package com.pratthamarora.instanews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratthamarora.instanews.R
import com.pratthamarora.instanews.ui.adapters.NewsAdapter
import com.pratthamarora.instanews.ui.activity.NewsActivity
import com.pratthamarora.instanews.viewmodel.NewsViewModel
import com.pratthamarora.instanews.utils.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        setupRecyclerView()
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressbar()
                    response.message?.let {
                        Log.e(TAG, "Error occurred: $it")
                    }
                }
                is Resource.Loading -> {
                    showProgressbar()
                }
            }

        })

    }

    private fun hideProgressbar() {
        paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressbar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}