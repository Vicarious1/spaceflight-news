package com.example.spaceflightnews

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {

}