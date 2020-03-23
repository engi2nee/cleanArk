package com.c_od_e.mobile_ui.mapper

interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V

}