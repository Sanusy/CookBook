package com.gmail.ivan.morozyk.cookbook.redux.base

import com.gmail.ivan.morozyk.cookbook.redux.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface Middleware: CoroutineScope {

    fun apply(action: Action, store: Store)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}