package com.example.marvelapi.network.repositories

import android.util.Log
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.network.MarvelEventsInterface
import com.example.marvelapi.exceptions.MarvelException
import com.example.marvelapi.models.ErrorResponse
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.HttpException

interface NetworkEventsInterface{
    suspend fun getAll(offset: Int, limit: Int) : MarvelApiResult<*>
}

class NetworkEventsRepository(
    private val marvelEventsInterface: MarvelEventsInterface
) : NetworkEventsInterface{
    override suspend fun getAll(offset: Int, limit: Int): MarvelApiResult<*> {
        return try {
            val response = marvelEventsInterface.getEvents(offset, limit)
            if(response.isSuccessful){
                MarvelApiResult.Success(response.body()!!)
            }
            else{
                val errorResponse = convertErrorBody(response.errorBody())
                throw MarvelException(errorResponse.message, errorResponse.code.toInt())
            }
        }catch (exception: HttpException){
            MarvelApiResult.Error(exception)
        }
        catch(exception: NullPointerException){
            MarvelApiResult.Error(exception)
        }catch (exception: Exception){
            MarvelApiResult.Error(exception)
        }
    }

    private fun convertErrorBody(body: ResponseBody?): ErrorResponse {
        return body?.source()?.let {
            Moshi.Builder().build().adapter(ErrorResponse::class.java).fromJson(
                it
            ) ?: ErrorResponse("Unable to cast exception thrown by marvel api", "")
        }!!
    }
}