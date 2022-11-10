package com.itexus.testapplication.presentation.logic.navigation.screen

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.itexus.testapplication.presentation.logic.R

open class AnimatedFragmentScreen @JvmOverloads constructor(
    @AnimatorRes @AnimRes val enterAnimation: Int = R.anim.empty,
    @AnimatorRes @AnimRes val exitAnimation: Int = R.anim.empty,
    @AnimatorRes @AnimRes val popEnterAnimation: Int = R.anim.empty,
    @AnimatorRes @AnimRes val popExitAnimation: Int = R.anim.empty,
    key: String? = null,
    fragmentCreator: Creator<FragmentFactory, Fragment>
) : FragmentScreen(key, fragmentCreator)
