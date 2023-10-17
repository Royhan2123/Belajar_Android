package com.example.tugassubmission.data.repository
import com.example.tugassubmission.data.source.AuthDataSource
import com.example.tugassubmission.data.remote.ApiResponse
import com.example.tugassubmission.data.remote.auth.AuthBody
import com.example.tugassubmission.data.remote.auth.AuthResponse
import com.example.tugassubmission.data.remote.auth.LoginBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authDataSource: AuthDataSource) {

    suspend fun registerUser(authBody: AuthBody): Flow<ApiResponse<Response<AuthResponse>>> {
        return authDataSource.registerUser(authBody).flowOn(Dispatchers.IO)
    }

    suspend fun loginUser(loginBody: LoginBody): Flow<ApiResponse<AuthResponse>> {
        return authDataSource.loginUser(loginBody).flowOn(Dispatchers.IO)
    }

}