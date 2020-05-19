package com.example.mvvmarchitecture

import android.app.Application
import com.example.mvvmarchitecture.data.database.AppDatabase
import com.example.mvvmarchitecture.data.network.ApiServices
import com.example.mvvmarchitecture.data.network.NetworkConnectionInterceptor
import com.example.mvvmarchitecture.data.preferences.PreferenceProvider
import com.example.mvvmarchitecture.data.repositories.QuotesRepository
import com.example.mvvmarchitecture.data.repositories.UserRepository
import com.example.mvvmarchitecture.ui.auth.AuthViewModelFactory
import com.example.mvvmarchitecture.ui.fragments.profile.ProfileViewModelFactory
import com.example.mvvmarchitecture.ui.fragments.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppKodein : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppKodein))

        bind() from singleton { NetworkConnectionInterceptor(instance()) } //NetworkConnectionInterceptor(this)
        bind() from singleton { ApiServices(instance()) }  //ApiServices(networkConnectionInterceptor)
        bind() from singleton { AppDatabase(instance()) }  //AppDatabase(this)
        bind() from singleton { PreferenceProvider(instance()) }  //PreferenceProvider(this)
        bind() from singleton { UserRepository(instance(), instance()) }  //UserRepository(apiServices, appDatabase)
        bind() from singleton { QuotesRepository(instance(), instance(), instance()) }  //QuotesRepository(apiServices, appDatabase, preferenceProvider)
        bind() from provider { AuthViewModelFactory(instance()) }  //AuthViewModelFactory(userRepository)
        bind() from provider { ProfileViewModelFactory(instance()) }  //ProfileViewModelFactory(userRepository)
        bind() from provider { QuotesViewModelFactory(instance()) }  //QuotesViewModelFactory(quotesRepository)
    }
}