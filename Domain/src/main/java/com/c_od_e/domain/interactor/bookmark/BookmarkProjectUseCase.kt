package com.c_od_e.domain.interactor.bookmark

import com.c_od_e.domain.excuter.PostExecutionThread
import com.c_od_e.domain.interactor.CompletableUseCase
import com.c_od_e.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject


open class BookmarkProjectUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
)
    : CompletableUseCase<BookmarkProjectUseCase.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return projectsRepository.bookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}