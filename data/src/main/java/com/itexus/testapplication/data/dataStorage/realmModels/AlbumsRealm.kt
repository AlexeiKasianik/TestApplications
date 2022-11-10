package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.types.ObjectId

open class AlbumsRealm : RealmObject() {
    var feed: FeedRealm? = FeedRealm()
}