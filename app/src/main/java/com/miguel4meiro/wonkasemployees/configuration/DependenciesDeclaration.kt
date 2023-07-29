package com.miguel4meiro.wonkasemployees.configuration

import com.google.gson.GsonBuilder
import com.miguel4meiro.wonkasemployees.BuildConfig
import com.miguel4meiro.wonkasemployees.classes.modules.detail.viewmodel.DetailViewModel
import com.miguel4meiro.wonkasemployees.classes.modules.detail.viewmodel.DetailViewModelInterface
import com.miguel4meiro.wonkasemployees.classes.modules.list.viewmodel.ListViewModel
import com.miguel4meiro.wonkasemployees.classes.modules.list.viewmodel.ListViewModelInterface
import com.miguel4meiro.wonkasemployees.classes.repositories.loompas.LoompaRepository
import com.miguel4meiro.wonkasemployees.classes.repositories.loompas.LoompaRepositoryInterface
import com.miguel4meiro.wonkasemployees.classes.services.ApiServiceInterface
import com.miguel4meiro.wonkasemployees.classes.services.api.loompas.LoompaService
import com.miguel4meiro.wonkasemployees.classes.services.api.loompas.LoompaServiceInterface
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {

    factory { provideOkHttpClient() }
    factory { provideApiService(get()) }
    single { provideRetrofit(get()) }

    // API Services
    factory<LoompaServiceInterface> { LoompaService(get()) }

    // Repositories
    factory<LoompaRepositoryInterface> { LoompaRepository(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)

val listModule = module {
    viewModel<ListViewModelInterface> { ListViewModel(get()) }
}

val detailModule = module {
    viewModel<DetailViewModelInterface> { DetailViewModel(get()) }
}