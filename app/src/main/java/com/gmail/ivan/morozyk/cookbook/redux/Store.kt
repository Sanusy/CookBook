package com.gmail.ivan.morozyk.cookbook.redux

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.gmail.ivan.morozyk.cookbook.navigation.NavigationManager
import com.gmail.ivan.morozyk.cookbook.navigation.Routes
import com.gmail.ivan.morozyk.cookbook.redux.base.Action
import com.gmail.ivan.morozyk.cookbook.redux.base.Connector
import com.gmail.ivan.morozyk.cookbook.redux.base.Middleware
import com.gmail.ivan.morozyk.cookbook.redux.receiptlist.ReceiptListMiddleware
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import java.util.*

// TODO: 26.08.2021 choose better name to ConnectorExecutor
object Store : KoinComponent {

    var state: State = State()
        private set
    private val mainReducer: MainReducer = MainReducer()
    private val middlewares: List<Middleware> = listOf(ReceiptListMiddleware(get()))
    private val subscriptions: MutableList<ConnectorExecutor> = mutableListOf()
    private val navigationManager: NavigationManager by inject()

    fun dispatch(action: Action) {
        middlewares.forEach { middleware -> middleware.apply(action, state) }
        val newState = mainReducer.reduce(state, action)
        if (state != newState) {
            state = newState
        }
        subscriptions.forEach { it.connect() }
    }

    fun navigate(route: Routes) {
        navigationManager.navigate(route)
    }

    fun subscribe(connectorExecutor: ConnectorExecutor) {
        subscriptions.add(connectorExecutor)
    }

    fun unsubscribe(connectorExecutor: ConnectorExecutor) {
        subscriptions.remove(connectorExecutor)
    }
}

@Composable
fun <P, C : Connector<P>> ReduxScreen(connector: C, content: @Composable (P) -> Unit) {

    val propsAsState = remember { mutableStateOf(connector.connect()) }
    val lifecycleKey = rememberSaveable {
        UUID.randomUUID()
    }
    val connectorExecutor = ConnectorExecutor {
        propsAsState.value = connector.connect()
    }

    LaunchedEffect(lifecycleKey) {
        Store.subscribe(connectorExecutor)
    }

    DisposableEffect(lifecycleKey) {
        onDispose {
            Store.unsubscribe(connectorExecutor)
        }
    }

    content.invoke(propsAsState.value)
}

class ConnectorExecutor(private val action: () -> Unit) {
    fun connect() {
        action.invoke()
    }
}