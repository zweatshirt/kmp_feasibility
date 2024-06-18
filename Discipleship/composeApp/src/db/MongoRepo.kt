package db
interface MongoRepo {
    fun configRealm()
    fun getData(): Flow<List<User>>
    fun filterData(name: String)
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
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