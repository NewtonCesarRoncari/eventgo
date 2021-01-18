package com.newton.eventgo.di.modules

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.newton.eventgo.repository.EventRepository
import com.newton.eventgo.repository.LoginRepository
import com.newton.eventgo.repository.UserDataRepository
import com.newton.eventgo.retrofit.ConnectionRetrofit
import com.newton.eventgo.retrofit.service.EventService
import com.newton.eventgo.view.viewmodel.EventDetailViewModel
import com.newton.eventgo.view.viewmodel.ListEventViewModel
import com.newton.eventgo.view.viewmodel.LoginViewModel
import com.newton.eventgo.view.viewmodel.UserDataViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceModule = module {
    single<EventService> { ConnectionRetrofit().eventService }
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }
}

val repositoryModule = module {
    single<EventRepository> { EventRepository(service = get()) }
    single<UserDataRepository> { UserDataRepository(preferences = get()) }
    single<LoginRepository> { LoginRepository(preferences = get()) }
}

val viewModelModule = module {
    viewModel<ListEventViewModel> { ListEventViewModel(repository = get()) }
    viewModel<UserDataViewModel> { UserDataViewModel(repository = get()) }
    viewModel<LoginViewModel> { LoginViewModel(repository = get()) }
    viewModel<EventDetailViewModel> { (eventId: Long) ->
        EventDetailViewModel(
            eventId,
            repository = get()
        )
    }
}