package com.example.marvelapi

import android.app.Application
import com.example.marvelapi.adapters.CharactersPagerInterface
import com.example.marvelapi.adapters.CharactersPagerRepository
import com.example.marvelapi.network.AuthInterceptor
import com.example.marvelapi.network.MarvelCharactersInterface
import com.example.marvelapi.network.repositories.NetworkCharactersInterface
import com.example.marvelapi.network.repositories.NetworkCharactersRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class Application : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(
                module{

                androidContext(this@Application)
                single{
                    provideRetrofit(get(), get())
                }

                factory { AuthInterceptor() }
                factory { provideOkHttpClient(get()) }
                factory {
                    provideMarvelApi(get())
                }

                factory { provideMoshi() }

                single{
                    NetworkCharactersRepository(get() as MarvelCharactersInterface) as NetworkCharactersInterface
                }
                single{
                    CharactersPagerRepository(get() as NetworkCharactersInterface) as CharactersPagerInterface
                }

                viewModel {
                    CharactersViewModel(get() as CharactersPagerInterface)
                }
            })
        }
    }

    private fun provideOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient{
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }

    private fun provideMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private fun provideMarvelApi(retrofit: Retrofit) : MarvelCharactersInterface{
        return retrofit.create(MarvelCharactersInterface::class.java)
    }
}