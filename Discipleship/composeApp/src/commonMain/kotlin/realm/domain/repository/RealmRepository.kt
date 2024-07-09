package realm.domain.repository

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.flow.Flow
import realm.data.remote.RealmApi.RealmInstance
import realm.data.repository.UserDaoImpl
import realm.domain.model.DiscipleEntity
import realm.domain.model.DisciplerEntity
import realm.domain.model.UserEntity
import kotlin.reflect.KClass

interface RealmRepository {
    suspend fun writeUser(userEntity: UserEntity)
    suspend fun readUser(id: String): UserEntity?
    suspend fun writeDisciple(userEntity: DiscipleEntity)
    suspend fun readDisciple(id: String): DiscipleEntity?
    suspend fun writeDiscipler(userEntity: DisciplerEntity)
    suspend fun readDiscipler(id: String): DisciplerEntity?
    suspend fun userFindBy(userId: String): UserEntity?
    suspend fun discipleFindBy(userId: String): UserEntity?
    suspend fun disciplerFindBy(userId: String): UserEntity?
    suspend fun updateUser(user: UserEntity)
    suspend fun userFindById(_id: String): UserEntity?
    suspend fun initRealm()
    suspend fun updateDiscipleStatus(userId: String, bool: Boolean)
}



