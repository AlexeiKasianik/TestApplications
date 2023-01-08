package com.itexus.testapplications.uiKit

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launch(
    exception: ((Throwable) -> Unit)? = null,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit,
): Job {
    val wrappedBlock: suspend CoroutineScope.() -> Unit = {
        block(this)
    }
    return launch(CoroutineExceptionHandler { _, throwable ->
        exception?.let { it(throwable) }
    }, start, wrappedBlock)
}