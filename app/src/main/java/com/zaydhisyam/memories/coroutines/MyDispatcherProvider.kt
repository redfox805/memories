package com.zaydhisyam.memories.coroutines

import kotlinx.coroutines.CoroutineDispatcher

/**
 * originally from:
 * https://craigrussell.io/2019/11/unit-testing-coroutine-suspend-functions-using-testcoroutinedispatcher/
 */

interface MyDispatcherProvider {

    val Default: CoroutineDispatcher

    val Main: CoroutineDispatcher

    val IO: CoroutineDispatcher
}