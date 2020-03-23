package com.c_od_e.data.repository

import com.c_od_e.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface ProjectsCache {
    fun clearProjects(): Completable
    fun saveProjects(projects: List<ProjectEntity>): Completable
    fun getProjects(): Observable<List<ProjectEntity>>
    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>
    fun setProjectAsBookmarked(projectId: String): Completable
    fun setProjectAsNotBookmarked(projectId: String): Completable
    fun areProjectsCached(): Single<Boolean>
    fun setLastCacheTime(lastCache: Long): Completable
    fun isProjectsCacheExpired(): Single<Boolean>
}