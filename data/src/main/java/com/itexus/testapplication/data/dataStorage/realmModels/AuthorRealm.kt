package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.kotlin.types.RealmObject

class AuthorRealm : RealmObject {
    var name: String = ""
    var url: String = ""
}