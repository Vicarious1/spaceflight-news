package com.example.spaceflightnews

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.spaceflightnews.ui.main.MainFragment
import com.example.spaceflightnews.ui.main.MainViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels() { ViewModelFactory(application as SpaceflightNewsApplication) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //todo handle onPause
        viewModel.startRetrievingArticles()
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}