package android.konovalovk.exercise6exercise6.network

import androidx.annotation.NonNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor private constructor(private val apiKey: String) : Interceptor {
    companion object {
        private val PARAM_API_KEY = "api-key"

        fun create(@NonNull apiKey: String): Interceptor {
            return ApiKeyInterceptor(apiKey)
        }
    }

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val requestWithoutApiKey = chain.request()

        val url = requestWithoutApiKey.url
            .newBuilder()
            .addQueryParameter(PARAM_API_KEY, apiKey)
            .build()

        val requestWithAttachedApiKey = requestWithoutApiKey.newBuilder()
            .url(url)
            .build()

        return chain.proceed(requestWithAttachedApiKey)
    }


}
