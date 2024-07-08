package acct_creation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import realm.data.remote.RealmApi
import realm.domain.model.DiscipleEntity
import realm.domain.model.DisciplerEntity
import realm.domain.repository.RealmRepository

/* Author: Zach */
class DorDScreenViewModel(val realmRepository: RealmRepository): ViewModel() {
//    private val scope = viewModelScope

    suspend fun initEntity(isDisciple: Boolean): RealmObject {
        if (isDisciple) {
            val discipleEntity = DiscipleEntity().apply {
                _id = RealmUUID.random().toString()
                mentorId = null
                userId = RealmApi.AtlasApp.app.currentUser!!.id
            }
            realmRepository.writeDisciple(discipleEntity)
            return discipleEntity
        }
        val disciplerEntity = DisciplerEntity().apply {
            _id = RealmUUID.random().toString()
            disciples = realmListOf("")
            userId = RealmApi.AtlasApp.app.currentUser!!.id
        }
        realmRepository.writeDiscipler(disciplerEntity)
        return disciplerEntity

    }
}