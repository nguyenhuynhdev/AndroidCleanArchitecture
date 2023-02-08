package io.nguyenhuynhdev.architecture.app.data.network

import io.nguyenhuynhdev.architecture.app.domain.models.openai.Request
import io.nguyenhuynhdev.architecture.app.domain.models.openai.Response
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface OpenAIService {
    @Headers("Content-Type: application/json")
    @POST("/v1/completions")
    fun generateResponse(
        @Header("Authorization") apiKey: String,
        @Body request: Request
    ): Observable<Response>
}