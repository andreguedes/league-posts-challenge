package life.league.challenge.kotlin.di

import life.league.challenge.kotlin.data.remote.RetrofitClient
import life.league.challenge.kotlin.data.repository.Repository
import life.league.challenge.kotlin.data.service.Service
import life.league.challenge.kotlin.ui.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModules = module {
    factory { Repository(get()) }
    factory { RetrofitClient }
    single { Service(get()) }
    viewModel { PostsViewModel(get()) }
}