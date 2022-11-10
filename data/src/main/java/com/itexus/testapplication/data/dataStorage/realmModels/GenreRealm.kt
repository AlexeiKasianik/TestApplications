package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.RealmObject

open class GenreRealm : RealmObject() {
    var genreId: String = ""
    var name: String = ""
    var url: String = ""
}