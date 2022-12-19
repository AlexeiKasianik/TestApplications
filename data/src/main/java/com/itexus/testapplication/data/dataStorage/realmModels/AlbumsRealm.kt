package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class AlbumsRealm : RealmObject {
    @PrimaryKey
    var id = 1
    var feed: FeedRealm? = FeedRealm()
}