package com.c_od_e.cache.factory

import com.c_od_e.cache.model.CachedProject
import com.c_od_e.data.model.ProjectEntity

object ProjectDataFactory {

    fun makeCachedProject(): CachedProject {
        return CachedProject(
            DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                false)
    }

    fun makeBookmarkedCachedProject(): CachedProject {
        return CachedProject(
            DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                true)
    }

    fun makeNonBookmarkedProjectEntity(): CachedProject {
        return CachedProject(
            DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                false)
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
            false)
    }

    fun makeBookmarkedProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                true)
    }
}