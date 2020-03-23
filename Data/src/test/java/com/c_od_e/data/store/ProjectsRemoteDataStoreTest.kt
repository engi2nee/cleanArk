package com.c_od_e.data.store

import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.data.repository.ProjectsRemote
import com.c_od_e.data.test.factory.DataFactory
import com.c_od_e.data.test.factory.ProjectFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsRemoteDataStoreTest {

    private val remote = mock<ProjectsRemote>()
    private val store = ProjectsRemoteDataStore(remote)

    @Test
    fun getProjectsCompletes() {
        stubRemoteGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val response = listOf(ProjectFactory.makeProjectEntity())
        stubRemoteGetProjects(Observable.just(response))
        val testObserver = store.getProjects().test()
        testObserver.assertValue(response)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveProjectsThrowsException() {
        store.saveProjects(listOf()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowsException() {
        store.clearProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedProjectsThrowsException() {
        store.getBookmarkedProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsBookmarkedThrowsException() {
        store.setProjectAsBookmarked(DataFactory.randomString()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsNotBookmarkedThrowsException() {
        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
    }

    private fun stubRemoteGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(remote.getProjects())
                .thenReturn(observable)
    }
}
