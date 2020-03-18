package com.c_od_e.cache.mapper

import com.c_od_e.cache.model.CachedProject
import com.c_od_e.data.model.ProjectEntity

class CachedProjectMapper: CacheMapper<CachedProject, ProjectEntity> {

    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return ProjectEntity(type.id, type.name, type.fullName, type.starCount,
                type.dateCreated, type.ownerName, type.ownerAvatar,
                type.isBookmarked)
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return CachedProject(type.id, type.name, type.fullName, type.starCount,
                type.dateCreated, type.ownerName, type.ownerAvatar,
                type.isBookmarked)
    }

}