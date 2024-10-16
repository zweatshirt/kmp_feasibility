package realm.domain.repository

import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.annotations.ExperimentalFlexibleSyncApi
import io.realm.kotlin.mongodb.ext.subscribe
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.flow.Flow
import realm.domain.model.DiscipleEntity
import kotlin.reflect.KClass

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

    @OptIn(ExperimentalFlexibleSyncApi::class)
    suspend fun findAll(): RealmResults<T> {
        return realm.query(clazz).find().subscribe()
    }

    suspend fun findById(id: String): T? {
        return realm.query(clazz, "_id == $0", id).first().find()
    }

    suspend fun findBy(userId: String): T? {
        return realm.query(clazz, "userId == $0", userId).first().find()
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