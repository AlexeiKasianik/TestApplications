package com.itexus.testapplication.presentation.logic.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itexus.testapplication.data.networkStorage.NetworkMusicApi
import com.itexus.testapplication.presentation.logic.R
import com.itexus.testapplication.presentation.logic.databinding.ActivityMainBinding
import com.itexus.testapplication.presentation.logic.navigation.INavigator
import io.github.aakira.napier.Napier
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator by inject<INavigator<AppCompatActivity>> { parametersOf(R.id.nav_host_container) }
    private val api by inject<NetworkMusicApi>()
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.attach(this@MainActivity)
        viewModel.setup()
    }

    private fun MainViewModel.setup() {}
}
