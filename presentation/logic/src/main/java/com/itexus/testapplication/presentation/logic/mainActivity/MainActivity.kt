package com.itexus.testapplication.presentation.logic.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itexus.testapplication.presentation.logic.R
import com.itexus.testapplication.presentation.logic.navigation.INavigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator by inject<INavigator<AppCompatActivity>> { parametersOf(R.id.nav_host_container) }
    private val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.attach(this@MainActivity)
        viewModel.setup()
    }

    private fun MainViewModel.setup() {}
}
