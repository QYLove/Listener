package com.sun.listener.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.sun.listener.R
import com.sun.listener.databinding.ActivityDetailBinding
import com.sun.listener.model.Pokemon
import javax.inject.Inject

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    @set:Inject
    internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

    @get:VisibleForTesting
    internal val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(detailViewModelFactory, pokemonItem.name)
    }

    private val pokemonItem: Pokemon by bundleNonNull(EXTRA_POKEMON)

    override fun onCreate(savedInstanceState: Bundle?) {
        TransformationCompat.onTransformationEndContainerApplyParams(this)
        super.onCreate(savedInstanceState)
        binding {
            pokemon = pokemonItem
            vm = viewModel
        }
    }

    companion object {
        @VisibleForTesting
        internal const val EXTRA_POKEMON = "EXTRA_POKEMON"

        fun startActivity(transformationLayout: TransformationLayout, pokemon: Pokemon) =
            transformationLayout.context.intentOf<DetailActivity> {
                putExtra(EXTRA_POKEMON to pokemon)
                TransformationCompat.startActivity(transformationLayout, intent)
            }
    }

}