package com.itexus.testapplication.data.networkStorage

import com.itexus.testapplication.data.networkStorage.contracts.Albums

interface NetworkMusicApi {

    suspend fun getAlbums() : Albums

}