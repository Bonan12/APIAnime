package com.example.apianime.di

import com.example.apianime.data.network.mapper.ITitleDbMapper
import com.example.apianime.data.network.mapper.TitleDbMapper
import com.example.apianime.data.network.repo.TitleRepository
import com.example.apianime.data.storage.mapper.ITitleMapper
import com.example.apianime.data.storage.mapper.TitleMapper
import com.example.apianime.data.storage.repo.DataStoreRepository
import com.example.apianime.domain.repo.IDataStoreRepository
import com.example.apianime.domain.repo.ITitleRepository
import com.example.apianime.domain.usecase.DataStoreSavesUseCase
import com.example.apianime.domain.usecase.DatabaseUseCase
import com.example.apianime.domain.usecase.IDataStoreSavesUseCase
import com.example.apianime.domain.usecase.IDatabaseUseCase
import com.example.apianime.domain.usecase.ITitleUseCase
import com.example.apianime.domain.usecase.TitleUseCase
import com.example.apianime.presentation.mapper.ITitleItemMapper
import com.example.apianime.presentation.mapper.TitleItemMapper
import com.example.apianime.presentation.viewModel.FavouritesPageViewModel
import com.example.apianime.presentation.viewModel.ListPageViewModel
import com.example.apianime.presentation.viewModel.NavigationGraphBarViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val rootModule = module {
    singleOf(::TitleRepository) { bind<ITitleRepository>() }
    singleOf(::DataStoreRepository) { bind<IDataStoreRepository>() }
    single<ITitleUseCase> { TitleUseCase(get()) }
    single<IDatabaseUseCase> { DatabaseUseCase(get()) }
    single<IDataStoreSavesUseCase> { DataStoreSavesUseCase(get()) }

    factory<ITitleDbMapper> { TitleDbMapper() }
    factory<ITitleItemMapper> { TitleItemMapper() }
    factory<ITitleMapper> { TitleMapper() }

    single<ListPageViewModel> { ListPageViewModel(get(), get(), get(), get()) }
    single<FavouritesPageViewModel> { FavouritesPageViewModel(get(), get()) }
    single<NavigationGraphBarViewModel> {NavigationGraphBarViewModel(get())}
}