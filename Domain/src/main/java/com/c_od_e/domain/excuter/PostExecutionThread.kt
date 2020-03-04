package com.c_od_e.domain.excuter

import io.reactivex.Scheduler


interface PostExecutionThread {
    val scheduler: Scheduler
}