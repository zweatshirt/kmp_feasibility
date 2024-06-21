package profile.domain.repository

import arrow.core.Either
import error_handling.NetworkError
import profile.domain.model.User

interface UserRepository {
    suspend fun getUserInfo(): Either<NetworkError, User>


}