package com.itexus.testapplication.presentation.logic.navigation

interface INavigator<S> {
    fun attach(subject: S)
}
