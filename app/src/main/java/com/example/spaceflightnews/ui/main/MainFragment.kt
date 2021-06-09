package com.example.spaceflightnews.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.spaceflightnews.R
import com.example.spaceflightnews.SpaceflightNewsApplication
import com.example.spaceflightnews.ViewModelFactory
import com.example.spaceflightnews.data.ArticleEntity
import com.example.spaceflightnews.data.ArticleRepository
import com.example.spaceflightnews.service.ArticleResponse

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels() { ViewModelFactory(requireActivity().application as SpaceflightNewsApplication) }

    private lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val inflatedView = inflater.inflate(R.layout.main_fragment, container, false)

        title = inflatedView.findViewById(R.id.message)

        viewModel.getArticles().observe(
                viewLifecycleOwner,
                Observer { articles: List<ArticleResponse> ->
            title.text = articles[0].title
        })

        return inflatedView
    }

}