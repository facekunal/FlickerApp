package com.example.flickrcl.api

import com.example.flickrcl.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    companion object {
        const val BASE_URL = "https://api.flickr.com/services/rest/"
        const val KEY = BuildConfig.FLICKR_KEY
        const val FORMAT = "json"
        const val NO_JSON_CALLBACK = "1"
    }

    @GET("?method=flickr.photos.search&api_key=$KEY&format=$FORMAT&nojsoncallback=$NO_JSON_CALLBACK")
    suspend fun searchPhotos(
        @Query("text") text: String? = null,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response
}