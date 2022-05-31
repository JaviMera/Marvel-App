package com.example.marvelapi.network.repositories

import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.exceptions.MarvelException
import com.example.marvelapi.models.ErrorResponse
import com.example.marvelapi.network.MarvelComicsInterface
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.HttpException

interface NetworkComicsInterface{
    suspend fun getAll(offset: Int) : MarvelApiResult<*>
}

class NetworkComicsRepository(
    private val marvelComicsInterface: MarvelComicsInterface
) : NetworkComicsInterface{
    override suspend fun getAll(offset: Int): MarvelApiResult<*> {
        return try {
            val response = marvelComicsInterface.getComics(offset)

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