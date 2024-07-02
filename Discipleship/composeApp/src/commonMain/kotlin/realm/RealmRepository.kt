package realm

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.flow.Flow
import profile.domain.model.DiscipleEntity
import profile.domain.model.DisciplerEntity
import profile.domain.model.UserEntity
import kotlin.reflect.KClass

class RealmRepository {

    private val schemaClass = setOf(UserEntity::class, DiscipleEntity::class, DisciplerEntity::class)
    private val config = RealmConfiguration.create(
        schema = schemaClass
    )
    val realm = Realm.open(config)
}

interface RealmDao<T : RealmObject> {
    val realm: Realm
    val clazz: KClass<T>

    suspend fun insert(entity: T) {
        realm.write {
            copyToRealm(entity)
        }
    }

    suspend fun insertAll(entities: List<T>) {
        realm.write {
            for (entity in entities) {
                copyToRealm(entity)
            }
        }
    }

    suspend fun update(entity: T) {
        realm.write {
            copyToRealm(entity)
        }
    }

    suspend fun findAll(): RealmResults<T> {
        return realm.query(clazz).find()
    }

    suspend fun findById(id: String): T? {
        return realm.query(clazz, "_id == $0", id).first().find()
    }

    suspend fun delete(entity: T) {
        realm.write {
            delete(entity)
        }
    }

    suspend fun stream(): Flow<ResultsChange<T>> {
        return realm.query(clazz).asFlow()
    }

    suspend fun deleteAll() {
        realm.write {
            val all = this.query(clazz).find()
            delete(all)
        }
    }
}

interface UserDao : RealmDao<UserEntity>

class UserDaoImpl constructor(
    r: Realm,
) : UserDao {
    override val realm: Realm = r
    override val clazz: KClass<UserEntity> = UserEntity::class
}

interface DiscipleDao : RealmDao<DiscipleEntity>

class DiscipleDaoImpl constructor(
    r: Realm,
) : DiscipleDao {
    override val realm: Realm = r
    override val clazz: KClass<DiscipleEntity> = DiscipleEntity::class
}

interface DisciplerDao : RealmDao<DisciplerEntity>

class DisciplerDaoImpl constructor(
    r: Realm,
) : DisciplerDao {
    override val realm: Realm = r
    override val clazz: KClass<DisciplerEntity> = DisciplerEntity::class
}

