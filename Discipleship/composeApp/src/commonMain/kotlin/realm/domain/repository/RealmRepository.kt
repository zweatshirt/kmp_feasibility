package realm.domain.repository

import io.realm.kotlin.types.RealmList
import realm.domain.model.DiscipleEntity
import realm.domain.model.DisciplerEntity
import realm.domain.model.UserEntity

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
//    suspend fun updateDiscipleToDoList(_id: String, toolList: RealmList<String>)
//    suspend fun updateDiscipleCompletedList(_id: String, toolList: RealmList<String>)
}



