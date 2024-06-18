package user

import org.jetbrains.compose.resources.DrawableResource

open class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: DrawableResource?,
    val bio: String?
) {

}

/*
* Concept schema
* import io.realm.RealmObject

open class User(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var image: DrawableResource? = null,
    var bio: String? = null
) : RealmObject()

open class Disciple(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var image: DrawableResource? = null,
    var bio: String? = null
) : User()

open class Discipler(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var image: DrawableResource? = null,
    var bio: String? = null
) : User()

* */