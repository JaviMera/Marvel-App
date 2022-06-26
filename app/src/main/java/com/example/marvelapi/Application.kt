package com.example.marvelapi

import android.app.Application
import com.example.marvelapi.adapters.characters.CharactersPagerInterface
import com.example.marvelapi.adapters.characters.CharactersPagerRepository
import com.example.marvelapi.viewmodels.characters.CharactersViewModel
import com.example.marvelapi.adapters.comics.ComicsPagerInterface
import com.example.marvelapi.adapters.comics.ComicsPagerRepository
import com.example.marvelapi.adapters.events.EventsPagerInterface
import com.example.marvelapi.adapters.events.EventsPagerRepository
import com.example.marvelapi.adapters.series.SeriesPagerInterface
import com.example.marvelapi.adapters.series.SeriesPagerRepository
import com.example.marvelapi.network.MarvelEventsInterface
import com.example.marvelapi.viewmodels.comics.ComicsViewModel
import com.example.marvelapi.network.AuthInterceptor
import com.example.marvelapi.network.MarvelCharactersInterface
import com.example.marvelapi.network.MarvelComicsInterface
import com.example.marvelapi.network.MarvelSeriesInterface
import com.example.marvelapi.network.repositories.*
import com.example.marvelapi.viewmodels.events.EventsViewModel
import com.example.marvelapi.viewmodels.series.SeriesViewModel
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
import java.util.concurrent.TimeUnit

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
                factory {provideMarvelSeriesInterface(get())}
                factory {provideMarvelEventsInterface(get())}
                factory { provideMoshi() }

                single{
                    NetworkCharactersRepository(get() as MarvelCharactersInterface)
                }
                single{
                    NetworkComicsRepository(get() as MarvelComicsInterface)
                }

                single{
                    NetworkSeriesRepository(get() as MarvelSeriesInterface)
                }
                single{
                    NetworkEventsRepository(get() as MarvelEventsInterface)
                }

                single{
                    CharactersPagerRepository(get() as NetworkCharactersInterface)
                }

                single{
                    ComicsPagerRepository(get() as NetworkComicsInterface)
                }

                single{
                    SeriesPagerRepository(get() as NetworkSeriesInterface)
                }

                single{
                    EventsPagerRepository(get() as NetworkEventsInterface)
                }

                viewModel {
                    CharactersViewModel(get() as CharactersPagerInterface)
                }

                viewModel {
                    ComicsViewModel(get() as ComicsPagerInterface)
                }

                viewModel {
                    SeriesViewModel(get() as SeriesPagerInterface)
                }

                viewModel {
                    EventsViewModel(get() as EventsPagerInterface)
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

    private fun provideMarvelSeriesInterface(retrofit: Retrofit) : MarvelSeriesInterface{
        return retrofit.create(MarvelSeriesInterface::class.java)
    }

    private fun provideMarvelEventsInterface(retrofit: Retrofit) : MarvelEventsInterface {
        return retrofit.create(MarvelEventsInterface::class.java)
    }
}