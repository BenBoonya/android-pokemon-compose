package com.benboonya.pokemoninfo.common.di

import com.benboonya.pokemoninfo.BuildConfig
import com.benboonya.pokemoninfo.common.BASE_URL
import com.benboonya.pokemoninfo.common.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideConverterFactory() = GsonConverterFactory.create()

    @Provides
    fun providePokemonApi(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): PokemonApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()
        .create(PokemonApi::class.java)
}