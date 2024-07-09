package realm.data.remote

import co.touchlab.kermit.Logger
import global_consts.Constants
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.annotations.ExperimentalFlexibleSyncApi
import io.realm.kotlin.mongodb.ext.subscribe
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import realm.data.repository.DiscipleDaoImpl
import realm.data.repository.DisciplerDaoImpl
import realm.data.repository.UserDaoImpl
import realm.domain.model.DiscipleEntity
import realm.domain.model.DisciplerEntity
import realm.domain.model.UserEntity

/* Author: Zach */
class RealmApi {
    // static singleton to access the Atlas app whenever
    object AtlasApp {
        val app = App.create(Constants.ATLAS_APP_ID)
    }

    // Need to add Meetings at some point
    private val schemaClass = setOf(UserEntity::class, DiscipleEntity::class, DisciplerEntity::class)
    // static singleton for Realm accessing
    object RealmInstance {
        var realm: Realm? = null
//        val closeRealm = {
//            realm?.close()
//        }
    }

    // Initialize sync for the remote database
    @OptIn(ExperimentalFlexibleSyncApi::class)
    suspend fun initRealm() {
        if (AtlasApp.app.currentUser == null) return
        val config = SyncConfiguration.Builder(
            user = AtlasApp.app.currentUser!!,
            schema = schemaClass,
        ).build()
        RealmInstance.realm = Realm.open(config)
        if (RealmInstance.realm == null) {
            Logger.e("Failed to open Realm in RealmApi.initRealm()")
            return
        } // change to handle exception
        Logger.i("Realm successfully opened in RealmApi.initRealm()")
        RealmInstance.realm!!.query(UserEntity::class).subscribe()
        RealmInstance.realm!!.query(DiscipleEntity::class).subscribe()
        RealmInstance.realm!!.query(DisciplerEntity::class).subscribe()
    }

    suspend fun userFindBy(userId: String): UserEntity? {
       return RealmInstance.realm?.let { UserDaoImpl(it).findBy(userId) }
    }
    suspend fun userFindById(_id: String): UserEntity? {
        return RealmInstance.realm?.let { UserDaoImpl(it).findById(_id) }
    }

    suspend fun discipleFindBy(userId: String): DiscipleEntity? {
        return RealmInstance.realm?.let { DiscipleDaoImpl(it).findBy(userId) }
    }
    suspend fun disciplerFindBy(userId: String): DisciplerEntity? {
        return RealmInstance.realm?.let { DisciplerDaoImpl(it).findBy(userId) }
    }


    suspend fun writeUser(userEntity: UserEntity) {
        RealmInstance.realm?.let { UserDaoImpl(it).insert(userEntity) }
    }

    suspend fun readUser(id: String): UserEntity? {
        return RealmInstance.realm?.let { UserDaoImpl(it).findById(id) }
    }

    suspend fun writeDisciple(userEntity: DiscipleEntity) {
        RealmInstance.realm?.let { DiscipleDaoImpl(it).insert(userEntity) }
    }

    suspend fun readDisciple(id: String): DiscipleEntity? {
        return RealmInstance.realm?.let { DiscipleDaoImpl(it).findById(id) }
    }

    suspend fun writeDiscipler(disciplerEntity: DisciplerEntity) {
        RealmInstance.realm?.let { DisciplerDaoImpl(it).insert(disciplerEntity) }
    }

    suspend fun readDiscipler(id: String): DisciplerEntity? {
        return RealmInstance.realm?.let { DisciplerDaoImpl(it).findById(id) }
    }

    suspend fun updateUser(user: UserEntity) {
        RealmInstance.realm?.let { UserDaoImpl(it).update(user) }
    }

    // shouldn't be here, needs to be implemented in the Dao class
    suspend fun updateDiscipleStatus(userId: String, bool: Boolean) {
        RealmInstance.realm?.write {
            val user = this.query(UserEntity::class,"_id == $0", userId).find().first()
            user.isDisciple = bool
        }
    }
}