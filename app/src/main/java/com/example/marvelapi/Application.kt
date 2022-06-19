package com.example.marvelapi

import android.app.Application
import com.example.marvelapi.adapters.characters.CharactersPagerInterface
import com.example.marvelapi.adapters.characters.CharactersPagerRepository
import com.example.marvelapi.viewmodels.characters.CharactersViewModel
import com.example.marvelapi.adapters.comics.ComicsPagerInterface
import com.example.marvelapi.adapters.comics.ComicsPagerRepository
import com.example.marvelapi.viewmodels.comics.ComicsViewModel
import com.example.marvelapi.network.AuthInterceptor
import com.example.marvelapi.network.MarvelCharactersInterface
import com.example.marvelapi.network.MarvelComicsInterface
import com.example.marvelapi.network.repositories.NetworkCharactersInterface
import com.example.marvelapi.network.repositories.NetworkCharactersRepository
import com.example.marvelapi.network.repositories.NetworkComicsInterface
import com.example.marvelapi.network.repositories.NetworkComicsRepository
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
                factory {provideMarvelCharactersInterface(get())}
                factory {provideMarvelComicsInterface(get())}
                factory { provideMoshi() }

                single{
                    NetworkCharactersRepository(get() as MarvelCharactersInterface) as NetworkCharactersInterface
                }
                single{
                    NetworkComicsRepository(get() as MarvelComicsInterface) as NetworkComicsInterface
                }

                single{
                    CharactersPagerRepository(get() as NetworkCharactersInterface) as CharactersPagerInterface
                }

                single{
                    ComicsPagerRepository(get() as NetworkComicsInterface) as ComicsPagerInterface
                }

                viewModel {
                    CharactersViewModel(get() as CharactersPagerInterface)
                }

                viewModel {
                    ComicsViewModel(get() as ComicsPagerInterface)
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

    private fun provideMarvelCharactersInterface(retrofit: Retrofit) : MarvelCharactersInterface{
        return retrofit.create(MarvelCharactersInterface::class.java)
    }

    private fun provideMarvelComicsInterface(retrofit: Retrofit) : MarvelComicsInterface{
        return retrofit.create(MarvelComicsInterface::class.java)
    }
}