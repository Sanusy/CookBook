package com.gmail.ivan.morozyk.cookbook.redux.base

interface Reducer<S> {

    fun reduce(state: S, action: Action): S
}