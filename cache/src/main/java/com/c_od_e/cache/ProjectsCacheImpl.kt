package com.c_od_e.cache

import com.c_od_e.cache.db.ProjectsDatabase
import com.c_od_e.cache.mapper.CachedProjectMapper
import com.c_od_e.cache.model.Config
import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(
    private val projectsDatabase: ProjectsDatabase,
    private val mapper: CachedProjectMapper
)
    : ProjectsCache {

    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertProjects(
                    projects.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getProjects()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return  projectsDatabase.cachedProjectsDao()
            .getProjects()
            .first(emptyList())
            .map { !it.isEmpty()
            }
        }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        return projectsDatabase.configDao().getConfig()
                .toSingle(Config(lastCacheTime = 0))
                .map {
                    System.currentTimeMillis() - it.lastCacheTime > (60 * 10 * 1000).toLong()
                }
    }

}