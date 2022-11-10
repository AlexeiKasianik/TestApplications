package com.itexus.testapplication.di.core

import com.itexus.testapplication.di.core.navigation.navigationModule
import com.itexus.testapplication.di.core.network.serversModule
import com.itexus.testapplication.di.core.persistence.persistenceModule

val coreModule = navigationModule + serversModule + persistenceModule