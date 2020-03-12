package com.c_od_e.remote.mapper

interface ModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

}