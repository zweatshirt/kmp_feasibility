package realm.data.remote

import global_consts.Constants
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.annotations.ExperimentalFlexibleSyncApi
import io.realm.kotlin.mongodb.ext.subscribe
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.query.RealmQuery
import realm.data.repository.UserDaoImpl
import realm.domain.model.DiscipleEntity
import realm.domain.model.DisciplerEntity
import realm.domain.model.UserEntity

/* Author: Zach */
class RealmApi {
    object AtlasApp {
        val app = App.create(Constants.ATLAS_APP_ID)
    }
    private val schemaClass = setOf(UserEntity::class, DiscipleEntity::class, DisciplerEntity::class)
    object RealmInstance {
        var realm: Realm? = null
        val closeRealm = {
            realm?.close()
        }
    }

    @OptIn(ExperimentalFlexibleSyncApi::class)
    suspend fun initRealm() {
        if (AtlasApp.app.currentUser == null) return
        val config = SyncConfiguration.Builder(
            user = AtlasApp.app.currentUser!!,
            schema = schemaClass,
        ).build()
        RealmInstance.realm = Realm.open(config)
        RealmInstance.realm!!.query(UserEntity::class).subscribe()
        RealmInstance.realm!!.query(DisciplerEntity::class).subscribe()
        RealmInstance.realm!!.query(DiscipleEntity::class).subscribe()
    }

    suspend fun writeUser(userEntity: UserEntity) {
        RealmInstance.realm?.let { UserDaoImpl(it).insert(userEntity) }
    }

    suspend fun readUser(id: String): UserEntity? {
        return RealmInstance.realm?.let { UserDaoImpl(it).findById(id) }
    }
}