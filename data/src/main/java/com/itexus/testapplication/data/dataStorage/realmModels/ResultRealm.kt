package com.itexus.testapplication.data.dataStorage.realmModels

import io.realm.RealmList
import io.realm.RealmObject

open class ResultRealm : RealmObject() {
    var artistId: String = ""
    var artistName: String = ""
    var artistUrl: String = ""
    var artworkUrl: String = ""
    var contentAdvisoryRating: String = ""
    var genres: RealmList<GenreRealm> = RealmList()
    var id: String = ""
    var kind: String = ""
    var name: String = ""
    var releaseDate: String = ""
    var url: String = ""
}