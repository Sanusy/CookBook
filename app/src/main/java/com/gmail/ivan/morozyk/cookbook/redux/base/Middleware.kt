package com.gmail.ivan.morozyk.cookbook.redux.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface Middleware : CoroutineScope {

    fun apply(action: Action)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}