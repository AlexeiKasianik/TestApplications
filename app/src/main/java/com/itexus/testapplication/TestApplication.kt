package com.itexus.testapplication

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.itexus.testapplication.app.BuildConfig
import com.itexus.testapplication.di.app
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@TestApplication)
            modules(app)
        }

        if (BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            Napier.base(DebugAntilog())
        }

        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)

        val config = RealmConfiguration
            .Builder()
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
    }
}
