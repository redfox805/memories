package com.zaydhisyam.memories.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * originally from:
 * https://craigrussell.io/2019/11/unit-testing-coroutine-suspend-functions-using-testcoroutinedispatcher/
 */
class MyDefaultDispatcherProvider : MyDispatcherProvider {

    override val Default: CoroutineDispatcher
        get() = Dispatchers.Default

    override val Main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val IO: CoroutineDispatcher
        get() = Dispatchers.IO
}