package realm.domain.repository

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.flow.Flow
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
    suspend fun initRealm()

    // Potentially implement later,
    // as it stands this code is done directly in the API
    // and maybe isn't necessary here:
//    private val schemaClass: Set<KClass<out RealmObject>>
//        get() = setOf(UserEntity::class, DiscipleEntity::class, DisciplerEntity::class)
//    private val config: RealmConfiguration
//        get() = RealmConfiguration.create(
//            schema = schemaClass
//        )
//    val realm: Realm
//        get() = Realm.open(config)
}



