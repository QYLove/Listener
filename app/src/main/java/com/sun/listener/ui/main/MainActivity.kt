package com.sun.listener.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import com.sun.listener.R
import com.sun.listener.databinding.ActivityMainBinding
import com.sun.listener.ui.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    @get:VisibleForTesting
    internal val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding {
            adapter = PokemonAdapter()
            vm = viewModel
        }
    }
}