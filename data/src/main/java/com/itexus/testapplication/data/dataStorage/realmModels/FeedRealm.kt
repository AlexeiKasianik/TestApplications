package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FeedRealm : RealmObject() {
    @PrimaryKey
    var id: String = ""
    var author: AuthorRealm? = AuthorRealm()
    var copyright: String = ""
    var country: String = ""
    var icon: String = ""
    var links: RealmList<LinkRealm> = RealmList()
    var results: RealmList<ResultRealm> = RealmList()
    var title: String = ""
    var updated: String = ""
}