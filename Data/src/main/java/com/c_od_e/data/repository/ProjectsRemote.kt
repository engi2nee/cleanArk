package com.c_od_e.data.repository

import com.c_od_e.data.model.ProjectEntity
import io.reactivex.Flowable
import io.reactivex.Observable

interface ProjectsRemote {
    fun getProjects(): Observable<List<ProjectEntity>>
}