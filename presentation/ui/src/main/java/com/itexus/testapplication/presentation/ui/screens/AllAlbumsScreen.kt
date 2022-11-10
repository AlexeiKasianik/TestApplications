package com.itexus.testapplication.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itexus.testapplication.presentation.ui.R
import com.itexus.testapplication.presentation.ui.databinding.AllAlbumsScreenBinding
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllAlbumsScreen : Fragment(R.layout.all_albums_screen) {

    private val binding by viewBinding(AllAlbumsScreenBinding::bind)
    private val viewModel by viewModel<BaseAllAlbumsScreenViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.albums.collect {
                Napier.e { it.toString() }
            }
        }

    }

}