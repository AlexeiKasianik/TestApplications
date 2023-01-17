package com.itexus.testapplications.uiKit

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    exception: ((Throwable) -> Unit)? = null,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit,
): Job {
    val wrappedBlock: suspend CoroutineScope.() -> Unit = {
        block(this)
    }
    return launch(context + CoroutineExceptionHandler { _, throwable ->
        exception?.let { it(throwable) }
    }, start, wrappedBlock)
}