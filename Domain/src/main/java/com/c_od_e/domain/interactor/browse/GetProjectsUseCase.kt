package com.c_od_e.domain.interactor.browse

import com.c_od_e.domain.excuter.PostExecutionThread
import com.c_od_e.domain.interactor.ObservableUseCase
import com.c_od_e.domain.model.Project
import com.c_od_e.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjectsUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }

}