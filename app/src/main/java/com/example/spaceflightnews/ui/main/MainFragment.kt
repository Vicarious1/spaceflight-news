package com.example.spaceflightnews.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightnews.R
import com.example.spaceflightnews.SpaceflightNewsApplication
import com.example.spaceflightnews.ViewModelFactory
import com.example.spaceflightnews.service.ArticleResponse

class MainFragment : Fragment(), ArticleCardListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels() { ViewModelFactory(requireActivity().application as SpaceflightNewsApplication) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.startRetrievingArticles()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val inflatedView = inflater.inflate(R.layout.main_fragment, container, false)


        val recyclerViewAdapter = ArticlesRecyclerViewAdapter(this)
        val recyclerView = inflatedView.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = recyclerViewAdapter

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        viewModel.getArticles().observe(
                viewLifecycleOwner,
                Observer {
                    recyclerViewAdapter.articles = it
        })

        return inflatedView
    }

    override fun onReadLaterTapped(articleId: String) {
        viewModel.onReadLaterTapped(articleId)
    }

    override fun onShareTapped(articleUrl: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, articleUrl)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}