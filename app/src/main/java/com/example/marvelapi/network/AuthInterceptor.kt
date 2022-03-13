package com.example.marvelapi.network

import com.example.marvelapi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()

        val publicKey = BuildConfig.MARVEL_PUBLIC_KEY
        val privateKey = BuildConfig.MARVEL_PRIVATE_KEY
        val timestamp = (System.currentTimeMillis() / 1000).toString()

        val digest = MessageDigest
            .getInstance("MD5")
        val input = timestamp.plus(privateKey).plus(publicKey)
        val hash = BigInteger(1, digest.digest(input.toByteArray())).toString(16).padStart(32,'0')

        val url = req.url()
            .newBuilder()
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("apiKey", publicKey)
            .addQueryParameter("hash", hash)
            .build()

        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}