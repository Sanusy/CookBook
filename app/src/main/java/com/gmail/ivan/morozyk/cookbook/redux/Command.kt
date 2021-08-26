package com.gmail.ivan.morozyk.cookbook.redux

class Command(private val command: () -> Unit) {
    fun invoke() {
        command.invoke()
    }

    class With<T>(private val command: (T) -> Unit) {
        fun invoke(argument: T) {
            command.invoke(argument)
        }
    }
}