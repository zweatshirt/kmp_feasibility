package realm.data.remote

import co.touchlab.kermit.Logger
import global_consts.Constants
import io.realm.kotlin.RealmConfiguration
import realm.data.repository.UserDaoImpl
import realm.domain.model.DiscipleEntity
import realm.domain.model.DisciplerEntity
import realm.domain.model.UserEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App


class RealmApi {
    private val app = App.create(Constants.ATLAS_APP_ID)
    private val schemaClass = setOf(UserEntity::class, DiscipleEntity::class, DisciplerEntity::class)
    private val config = RealmConfiguration.create(
        schema = schemaClass,
    )
    private val realmInstance = Realm.open(config)

    suspend fun writeUser(userEntity: UserEntity) {
        UserDaoImpl(realmInstance).insert(userEntity)
    }

    suspend fun readUser(id: String): UserEntity? {
        return UserDaoImpl(realmInstance).findById(id)
    }

    fun getAppInstance(): App {
        return app
    }

}