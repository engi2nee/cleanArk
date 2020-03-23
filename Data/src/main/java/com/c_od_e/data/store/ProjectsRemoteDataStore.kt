package com.c_od_e.data.store

import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.data.repository.ProjectsDataStore
import com.c_od_e.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject

open class ProjectsRemoteDataStore @Inject constructor(
    private val projectsRemote: ProjectsRemote)
    : ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported here...")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Getting bookmarked projects isn't supported here...")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }
}