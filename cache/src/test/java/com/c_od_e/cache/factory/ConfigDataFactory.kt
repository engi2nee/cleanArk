package com.c_od_e.cache.factory

import com.c_od_e.cache.model.Config

object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }

}