package com.saavatech.jetpackauthentication.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.saavatech.jetpackauthentication.data.local.AuthPreferences
import com.saavatech.jetpackauthentication.data.models.ApiService
import com.saavatech.jetpackauthentication.data.repository.AuthRespositoryImpl
import com.saavatech.jetpackauthentication.data.repository.PostRepositoryImpl
import com.saavatech.jetpackauthentication.domain.repository.AuthRepository
import com.saavatech.jetpackauthentication.domain.repository.PostRepository
import com.saavatech.jetpackauthentication.domain.use_case.LoginUseCase
import com.saavatech.jetpackauthentication.domain.use_case.PostUseCase
import com.saavatech.jetpackauthentication.util.Constants.AUTH_PREFERENCES
import com.saavatech.jetpackauthentication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context) : DataStore<Preferences> =
    PreferenceDataStoreFactory.create(
        produceFile = {
            context.preferencesDataStoreFile(AUTH_PREFERENCES)
        }
    )

    @Provides
    @Singleton
    fun provideAuthPreferences(dataStore: DataStore<Preferences>)=AuthPreferences(dataStore)

    @Provides
    @Singleton
    fun providesApiService(authInterceptor: AuthInterceptor):ApiService{
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(
        apiService: ApiService,
        preferences: AuthPreferences,
    ): AuthRepository{
        return AuthRespositoryImpl(apiService=apiService,preferences=preferences)
    }

    @Provides
    @Singleton
    fun providesPostsRepository(
        apiService: ApiService,
        preferences: AuthPreferences,
    ): PostRepository{
        return PostRepositoryImpl(apiService=apiService,preferences=preferences)
    }

    @Provides
    @Singleton
    fun providesLoginUseCase(repository: AuthRepository): LoginUseCase{
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesPostUseCase(repository: PostRepository): PostUseCase{
        return PostUseCase(repository)
    }


}