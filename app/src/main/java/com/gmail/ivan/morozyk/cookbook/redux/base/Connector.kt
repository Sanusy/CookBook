package com.gmail.ivan.morozyk.cookbook.redux.base

interface Connector<P> {

    fun connect(): P
}