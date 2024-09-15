package com.example.kotlingptjetpack.ui.news

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlingptjetpack.ui.main.NewsAdapter
import com.example.kotlingptjetpack.data.remote.NewsApiService
import com.example.kotlingptjetpack.R
import com.example.kotlingptjetpack.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var viewModel: NewsViewModel
    private lateinit var progressBar: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_news,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NewsAdapter(mutableListOf())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        observeViewModel()
        fetchData()
    }

    private fun observeViewModel() {
        viewModel.articles.observe(viewLifecycleOwner, { articles ->
            adapter.updateArticles(articles)
            progressBar.visibility = View.GONE
        })
        viewModel.error.observe(viewLifecycleOwner, { error ->
            progressBar.visibility = View.GONE
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        })
    }

    private fun fetchData() {
        progressBar.visibility = View.VISIBLE
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NewsApiService::class.java)
        viewModel.fetchTopHeadlines(service)
    }
}
