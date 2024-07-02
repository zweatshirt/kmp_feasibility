package realm

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import global_consts.Constants
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import profile.domain.model.Disciple
import profile.domain.model.User
import profile.domain.model.Discipler

class RealmRepository {
    private val schemaClass = setOf(User::class, Disciple::class, Discipler::class)
    private val appService by lazy {
        val appConfiguration = AppConfiguration.Builder(appId = Constants.ATLAS_APP_ID).build()
        App.create(appConfiguration)
    }

//    private val realm by lazy {
////        val user = io.realm.kotlin.mongodb.User
//        val config = SyncConfiguration.Builder(user, schemaClass).name("users")
//    }
}
