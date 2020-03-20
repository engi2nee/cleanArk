package com.c_od_e.presentation.mapper

interface Mapper<out V, in D> {

    fun mapToView(type: D): V

}