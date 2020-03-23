package com.c_od_e.remote.mapper

import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.remote.model.ProjectModel
import javax.inject.Inject

open class ProjectsResponseModelMapper @Inject constructor(): ModelMapper<ProjectModel, ProjectEntity> {
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName, model.starCount.toString(),
            model.dateCreated, model.owner.ownerName, model.owner.ownerAvatar)
    }

}