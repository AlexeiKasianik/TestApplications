package com.itexus.testapplication.di.features

import com.itexus.testapplication.di.features.mainActivity.activityModule
import com.itexus.testapplication.di.features.musicFeature.dataModule
import com.itexus.testapplication.di.features.musicFeature.domainModule
import com.itexus.testapplication.di.features.musicFeature.presentationModule

val featuresModule = dataModule + domainModule + presentationModule + activityModule