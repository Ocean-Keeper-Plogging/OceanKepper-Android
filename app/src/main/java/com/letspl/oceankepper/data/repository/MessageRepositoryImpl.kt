package com.letspl.oceankepper.data.repository

import com.letspl.oceankepper.data.dto.MessageDto
import com.letspl.oceankepper.data.network.ApiService
import com.letspl.oceankepper.di.module.ApiModule
import retrofit2.Response
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(@ApiModule.OceanService private val service: ApiService): MessageRepository {
    override suspend fun getMessage(
        token: String,
        messageId: Long?,
        size: Int,
        type: String?,
        user: String
    ): Response<MessageDto> {
        return service.getMessage(token, messageId, size, type, user)
    }
}