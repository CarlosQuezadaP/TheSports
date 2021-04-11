package com.condor.data.di

import com.condor.data.converters.Converter
import com.condor.data.converters.IConverter
import org.koin.dsl.module

val converterModule = module {
    single<IConverter> { Converter()}
}