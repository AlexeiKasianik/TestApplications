package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class FeedRealm : RealmObject {
    var id: String = ""
    var author: AuthorRealm? = AuthorRealm()
    var copyright: String = ""
    var country: String = ""
    var icon: String = ""
    var links: RealmList<LinkRealm> = realmListOf()
    var results: RealmList<ResultRealm> = realmListOf()
    var title: String = ""
    var updated: String = ""
}
