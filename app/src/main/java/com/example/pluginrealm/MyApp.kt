package com.example.pluginrealm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()

        // init realm

        Realm.init(this)

        val configuration = RealmConfiguration.Builder()
            .name("database.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()

        Realm.setDefaultConfiguration(configuration)
    }
}