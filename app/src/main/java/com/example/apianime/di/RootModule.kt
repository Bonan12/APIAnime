package com.example.apianime.di

import com.example.apianime.data.network.mapper.ITitleDbMapper
import com.example.apianime.data.network.mapper.TitleDbMapper
import com.example.apianime.data.network.repo.TitleRepository
import com.example.apianime.data.storage.dao.ITitleDao
import com.example.apianime.data.storage.dao.TitleDao
import com.example.apianime.data.storage.mapper.ITitleMapper
import com.example.apianime.data.storage.mapper.TitleMapper
import com.example.apianime.domain.repo.ITitleRepository
import com.example.apianime.domain.usecase.ITitleUseCase
import com.example.apianime.domain.usecase.TitleUseCase
import com.example.apianime.presentation.mapper.ITitleItemMapper
import com.example.apianime.presentation.mapper.TitleItemMapper
import com.example.apianime.presentation.viewModel.ListPageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single<ITitleRepository> { TitleRepository(get(), get(), get(), get()) }
    single<ITitleUseCase> { TitleUseCase(get()) }
    single<ITitleDao> { TitleDao() }

    factory<ITitleDbMapper>{ TitleDbMapper() }
    factory<ITitleItemMapper> { TitleItemMapper() }
    factory<ITitleMapper> { TitleMapper() }

    single<ListPageViewModel> { ListPageViewModel(get(), get()) }
}