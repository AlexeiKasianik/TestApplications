package com.itexus.testapplication.data.networkStorage.apiImpl

import com.itexus.testapplication.data.BuildConfig
import com.itexus.testapplication.data.dataStorage.realmModels.FeedRealm
import com.itexus.testapplication.data.mapping.toRealmAlbums
import com.itexus.testapplication.data.networkStorage.NetworkMusicApi
import com.itexus.testapplication.data.networkStorage.contracts.Albums
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where

class NetworkMusicApiIml(private val ktorClient: HttpClient) : NetworkMusicApi {

    override suspend fun getAlbums(): Albums {
        return ktorClient.get(BuildConfig.SERVER_URL).body()
    }

}
