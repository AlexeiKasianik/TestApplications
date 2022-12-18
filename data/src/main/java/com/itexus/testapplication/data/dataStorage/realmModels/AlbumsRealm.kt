package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.types.ObjectId

open class AlbumsRealm : RealmObject() {
    @PrimaryKey
    var id = 1
    var feed: FeedRealm? = FeedRealm()
}