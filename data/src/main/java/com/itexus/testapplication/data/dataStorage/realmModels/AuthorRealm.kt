package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.RealmObject

open class AuthorRealm : RealmObject() {
    var name: String = ""
    var url: String = ""
}