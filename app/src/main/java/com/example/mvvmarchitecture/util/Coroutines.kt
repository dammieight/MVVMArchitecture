package com.example.mvvmarchitecture.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    fun main(job: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.Main).launch {
            job()
        }
    }

    fun io(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }
}