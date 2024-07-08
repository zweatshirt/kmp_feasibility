package realm.data.repository

import arrow.core.raise.catch
import co.touchlab.kermit.Logger
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App
import realm.data.remote.RealmApi
import realm.domain.model.DiscipleEntity
import realm.domain.model.DisciplerEntity
import realm.domain.model.UserEntity
import realm.domain.repository.RealmRepository

class RealmRepoImpl(private val realmApi: RealmApi): RealmRepository {
    // Apply exception handling in this class

    override suspend fun writeUser(userEntity: UserEntity) {
        Logger.i("RealmRepoImpl writeUser() reached successfully")

        try {
            realmApi.writeUser(userEntity)
        }
        catch(e: Exception) {
            Logger.e("Failed to write user to Realm")
            Logger.e(e.toString())
        }
    }

    override suspend fun readUser(id: String): UserEntity? {
        return realmApi.readUser(id)
    }

    override suspend fun writeDisciple(discipleEntity: DiscipleEntity) {
        Logger.i("RealmRepoImpl writeUser() reached successfully")

        try {
            realmApi.writeDisciple(discipleEntity)
        }
        catch(e: Exception) {
            Logger.e("Failed to write user to Realm")
            Logger.e(e.toString())
        }
    }

    override suspend fun readDisciple(id: String): DiscipleEntity? {
        return realmApi.readDisciple(id)
    }

    override suspend fun writeDiscipler(disciplerEntity: DisciplerEntity) {
        Logger.i("RealmRepoImpl writeUser() reached successfully")

        try {
            realmApi.writeDiscipler(disciplerEntity)
        }
        catch(e: Exception) {
            Logger.e("Failed to write user to Realm")
            Logger.e(e.toString())
        }
    }

    override suspend fun readDiscipler(id: String): DisciplerEntity? {
        return realmApi.readDiscipler(id)
    }

    override suspend fun initRealm(){
        return realmApi.initRealm()
    }
}