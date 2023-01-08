package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.kotlin.types.RealmObject

class AlbumsRealm : RealmObject {
    var feed: FeedRealm? = FeedRealm()
}