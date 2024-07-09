package acct_creation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import kotlinx.coroutines.launch
import realm.data.remote.RealmApi
import realm.domain.model.DiscipleEntity
import realm.domain.model.DisciplerEntity
import realm.domain.repository.RealmRepository

/* Author: Zach */
class DorDScreenViewModel(val realmRepository: RealmRepository): ViewModel() {
    private val scope = viewModelScope

    fun initEntity(isDisciple: Boolean): RealmObject {
        val entity: RealmObject
        if (isDisciple) {
            entity = DiscipleEntity().apply {
                _id = RealmUUID.random().toString()
                mentorId = null
                userId = RealmApi.AtlasApp.app.currentUser!!.id
            }
        }
        else {
            entity = DisciplerEntity().apply {
                _id = RealmUUID.random().toString()
                disciples = realmListOf("")
                userId = RealmApi.AtlasApp.app.currentUser!!.id
            }
        }
        scope.launch {
            if (isDisciple) {
                realmRepository.writeDisciple(entity as DiscipleEntity)
            }
            else {
                realmRepository.writeDiscipler(entity as DisciplerEntity)
            }
        }
        return entity
    }

    fun updateUserEntity(entity: RealmObject) {
        scope.launch {
            if (entity is DiscipleEntity) {
                realmRepository.updateDiscipleStatus(entity.userId!!,true)
            }
            if (entity is DisciplerEntity) {
                realmRepository.updateDiscipleStatus(entity.userId!!, false)
            }
        }
    }
}