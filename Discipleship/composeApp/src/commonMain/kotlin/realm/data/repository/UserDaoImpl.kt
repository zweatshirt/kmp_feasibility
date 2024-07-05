package realm.data.repository

import io.realm.kotlin.Realm
import realm.domain.model.UserEntity
import realm.domain.repository.UserDao
import kotlin.reflect.KClass

class UserDaoImpl constructor(
    r: Realm,
) : UserDao {
    override val realm: Realm = r
    override val clazz: KClass<UserEntity> = UserEntity::class
}