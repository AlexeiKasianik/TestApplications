package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.kotlin.types.RealmObject

class GenreRealm : RealmObject {
    var genreId: String = ""
    var name: String = ""
    var url: String = ""
}