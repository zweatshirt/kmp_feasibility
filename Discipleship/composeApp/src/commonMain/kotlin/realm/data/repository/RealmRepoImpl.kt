package realm.data.repository

import co.touchlab.kermit.Logger
import io.realm.kotlin.types.RealmList
import realm.data.remote.RealmApi
import realm.data.remote.RealmApi.RealmInstance
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

    override suspend fun discipleFindBy(userId: String): UserEntity? {
        return realmApi.userFindBy(userId)
    }
    override suspend fun userFindBy(userId: String): UserEntity? {
        return realmApi.userFindBy(userId)
    }
    override suspend fun disciplerFindBy(userId: String): UserEntity? {
        return realmApi.userFindBy(userId)
    }

    override suspend fun updateUser(user: UserEntity) {
        return realmApi.updateUser(user)
    }

    override suspend fun userFindById(_id: String): UserEntity? {
        return realmApi.userFindById(_id)
    }

    override suspend fun updateDiscipleStatus(userId: String, bool: Boolean) {
        return realmApi.updateDiscipleStatus(userId, bool)
    }

//    override suspend fun updateDiscipleToDoList(_id: String, toolList: RealmList<String>) {
//        realmApi.updateDiscipleToDoList(_id, toolList)
//    }
//    override suspend fun updateDiscipleCompletedList(_id: String, toolList: RealmList<String>) {
//        realmApi.updateDiscipleCompletedList(_id, toolList)
//    }
}