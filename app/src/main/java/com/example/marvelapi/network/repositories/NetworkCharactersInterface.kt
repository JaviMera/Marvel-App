package com.example.marvelapi.network.repositories

import android.content.res.Resources
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.exceptions.MarvelException
import com.example.marvelapi.models.ErrorResponse
import com.example.marvelapi.network.MarvelCharactersInterface
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.HttpException

interface NetworkCharactersInterface {
    suspend fun getAll(offset: Int): MarvelApiResult<*>
    suspend fun getSingle(characterId: Int) : MarvelApiResult<*>
}

class NetworkCharactersRepository(
    private val marvelCharactersInterface: MarvelCharactersInterface
) : NetworkCharactersInterface{

    override suspend fun getAll(offset: Int): MarvelApiResult<*> {

        return try {
            val response = marvelCharactersInterface.getCharacters(offset)

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

    override suspend fun getSingle(characterId: Int): MarvelApiResult<*> {
        return try {
            val response = marvelCharactersInterface.getCharacter(characterId)

            if(response.isSuccessful){
                MarvelApiResult.Success(response.body()!!)
            }else{
                val errorResponse = convertErrorBody(response.errorBody())
                throw MarvelException(errorResponse.message, errorResponse.code.toInt())
            }
        }catch (exception: HttpException){
            MarvelApiResult.Error(exception)
        }
        catch (exception: Resources.NotFoundException){
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