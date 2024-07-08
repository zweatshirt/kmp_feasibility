package realm.data.repository

import io.realm.kotlin.Realm
import realm.domain.model.DisciplerEntity
import realm.domain.repository.DisciplerDao
import kotlin.reflect.KClass

class DisciplerDaoImpl constructor(
    r: Realm,
) : DisciplerDao {
    override val realm: Realm = r
    override val clazz: KClass<DisciplerEntity> = DisciplerEntity::class
}