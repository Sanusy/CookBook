package com.gmail.ivan.morozyk.cookbook.di

import com.gmail.ivan.morozyk.cookbook.data.service.ReceiptService
import com.gmail.ivan.morozyk.cookbook.data.service.ReceiptServiceImpl
import com.gmail.ivan.morozyk.cookbook.navigation.NavigationManager
import org.koin.dsl.module

val serviceModule = module {
    single<ReceiptService> { ReceiptServiceImpl() }
}

val navigationModule = module {
    single { NavigationManager() }
}