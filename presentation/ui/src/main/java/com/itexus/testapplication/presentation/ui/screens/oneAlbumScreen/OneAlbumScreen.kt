package com.itexus.testapplication.presentation.ui.screens.oneAlbumScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.itexus.testapplication.presentation.ui.compose.OneAlbumScreen
import com.itexus.testapplication.presentation.ui.compose.TransparentSystemBars
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OneAlbumScreen : Fragment() {

    private val viewModel by viewModel<BaseOneAlbumScreenViewModel> { parametersOf(arguments) }

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
                TransparentSystemBars()
                OneAlbumScreen(viewModel = viewModel)
            }
        }
    }

}
