package com.itexus.testapplication.di.features

import com.itexus.testapplication.di.features.mainActivity.activityModule
import com.itexus.testapplication.di.features.musicFeature.dataModule
import com.itexus.testapplication.di.features.musicFeature.presentationModule
import com.itexus.testapplication.di.features.musicFeature.useCases

val featuresModule = dataModule + useCases + presentationModule + activityModule
