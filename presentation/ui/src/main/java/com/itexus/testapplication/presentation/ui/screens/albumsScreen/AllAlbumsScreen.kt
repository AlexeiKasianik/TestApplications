package com.itexus.testapplication.presentation.ui.screens.albumsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.itexus.testapplication.presentation.ui.compose.AlbumsScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllAlbumsScreen : Fragment() {

    private val viewModel by viewModel<BaseAllAlbumsScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                SideEffect {
                    WindowCompat.setDecorFitsSystemWindows(
                        requireActivity().window,
                        false
                    )
                }
                AlbumsScreen(viewModel)
            }
        }
    }
}
