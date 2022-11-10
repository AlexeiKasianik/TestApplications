package com.mbanq.sky.presentation.logic.navigation.navigators

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.github.terrakok.cicerone.androidx.TransactionInfo
import com.itexus.testapplication.presentation.logic.navigation.screen.AnimatedFragmentScreen

open class CustomSupportAppNavigator : AppNavigator {

    private val appActivity: FragmentActivity
    private val appContainerId: Int

    constructor(
        appActivity: FragmentActivity,
        appContainerId: Int,
    ) : super(
        appActivity,
        appContainerId,
    ) {
        this.appActivity = appActivity
        this.appContainerId = appContainerId
    }

    constructor(
        fragment: Fragment,
        appContainerId: Int,
    ) : super(
        fragment.requireActivity(),
        appContainerId,
        fragment.childFragmentManager,
    ) {
        this.appActivity = fragment.requireActivity()
        this.appContainerId = appContainerId
    }

    override fun commitNewFragmentScreen(
        screen: FragmentScreen,
        type: TransactionInfo.Type,
        addToBackStack: Boolean,
    ) {
        val fragment = screen.createFragment(fragmentFactory)
        val transaction = fragmentManager.beginTransaction()
        transaction.setReorderingAllowed(true)
        setupFragmentTransaction(
            transaction,
            fragmentManager.findFragmentById(containerId),
            fragment
        )
        (screen as? AnimatedFragmentScreen)?.apply {
            transaction.setCustomAnimations(
                this.enterAnimation,
                this.exitAnimation,
                this.popEnterAnimation,
                this.popExitAnimation
            )
        }
        when (type) {
            TransactionInfo.Type.ADD -> transaction.add(containerId, fragment, screen.screenKey)
            TransactionInfo.Type.REPLACE -> transaction.replace(
                containerId,
                fragment,
                screen.screenKey
            )
        }
        if (addToBackStack) {
            val transactionInfo = TransactionInfo(screen.screenKey, type)
            transaction.addToBackStack(transactionInfo.toString())
            localStackCopy.add(transactionInfo)
        }
        transaction.commit()
    }

    override fun applyCommands(commands: Array<out Command>) {
        with(appActivity) {
            runOnUiThread {
                currentFocus?.let {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                }
                super.applyCommands(commands)
            }
        }
    }

    override fun applyCommand(command: Command) {
        super.applyCommand(command)
    }

    override fun back() {
        val latestFragment = fragmentManager.fragments.lastOrNull()
        (latestFragment as? DialogFragment)?.dismiss() ?: run {
            if (localStackCopy.isNotEmpty()) {
                fragmentManager.popBackStack()
                localStackCopy.removeAt(localStackCopy.lastIndex)
            } else {
                activityBack()
            }
        }
    }
}
