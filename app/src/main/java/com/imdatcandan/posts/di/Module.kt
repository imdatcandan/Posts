package com.imdatcandan.posts.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imdatcandan.posts.BuildConfig
import com.imdatcandan.posts.data.*
import com.imdatcandan.posts.ui.viewmodel.PostDetailViewModel
import com.imdatcandan.posts.ui.viewmodel.PostListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule: Module = module {
    single<PostRepository> { PostRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    viewModel { PostListViewModel(get()) }
    viewModel { PostDetailViewModel(get(), get()) }
}

val networkModule: Module = module {
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideGson() }
    single { providePostService(get()) }
    single { provideUserService(get()) }
    single { provideApolloClient(get()) }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun providePostService(retrofit: Retrofit): PostService =
    retrofit.create(PostService::class.java)

private fun provideUserService(retrofit: Retrofit): UserService =
    retrofit.create(UserService::class.java)

private fun provideGson(): Gson = GsonBuilder().create()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl(BuildConfig.BASE_URL)
        .okHttpClient(okHttpClient)
        .build()
}

