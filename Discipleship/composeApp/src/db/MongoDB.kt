package db
object MongoDB: MongoRepo {
    private val app = App.create(APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm
}