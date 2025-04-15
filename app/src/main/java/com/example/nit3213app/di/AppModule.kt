package com.example.nit3213app.di

import com.example.nit3213app.api.ApiClient
import com.example.nit3213app.api.ApiService
import org.koin.dsl.module

val appModule = module {
    single<ApiService> { ApiClient.apiService }
}
