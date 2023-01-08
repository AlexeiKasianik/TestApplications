package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class ResultRealm : RealmObject {
    var artistId: String = ""
    var artistName: String = ""
    var artistUrl: String = ""
    var artworkUrl: String = ""
    var contentAdvisoryRating: String = ""
    var genres: RealmList<GenreRealm> = realmListOf()
    var id: String = ""
    var kind: String = ""
    var name: String = ""
    var releaseDate: String = ""
    var url: String = ""
}