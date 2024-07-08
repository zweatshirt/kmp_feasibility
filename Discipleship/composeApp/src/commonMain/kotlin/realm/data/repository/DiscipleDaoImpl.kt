package realm.data.repository

import io.realm.kotlin.Realm
import realm.domain.model.DiscipleEntity
import realm.domain.repository.DiscipleDao
import kotlin.reflect.KClass

class DiscipleDaoImpl constructor(
    r: Realm,
) : DiscipleDao {
    override val realm: Realm = r
    override val clazz: KClass<DiscipleEntity> = DiscipleEntity::class
}