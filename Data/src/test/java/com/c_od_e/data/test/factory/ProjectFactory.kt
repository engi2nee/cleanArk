package com.c_od_e.data.test.factory

import com.c_od_e.data.test.factory.DataFactory
import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.domain.model.Project

object ProjectFactory {

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomBoolean())
    }

    fun makeProject(): Project {
        return Project(
            DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomBoolean())
    }

}