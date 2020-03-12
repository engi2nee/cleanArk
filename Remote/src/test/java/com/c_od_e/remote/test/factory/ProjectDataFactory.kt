package com.c_od_e.remote.test.factory

import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.remote.model.OwnerModel
import com.c_od_e.remote.model.ProjectModel
import com.c_od_e.remote.model.ProjectsResponseModel

object ProjectDataFactory {

    fun makeOwner(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

    fun makeProject(): ProjectModel {
        return ProjectModel(
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomInt(),
            DataFactory.randomUuid(), makeOwner()
        )
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid()
        )
    }

    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }

}