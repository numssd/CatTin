package com.fakhrulasa.realmdb

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Cat : RealmObject() {
    @PrimaryKey
    var id: String? = null
    var url: String? = null
    var height: Int? = null
    var width: Int? = null
}
