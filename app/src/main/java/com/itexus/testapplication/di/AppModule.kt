package com.itexus.testapplication.di

import com.itexus.testapplication.di.core.coreModule
import com.itexus.testapplication.di.features.featuresModule

val app = coreModule + featuresModule