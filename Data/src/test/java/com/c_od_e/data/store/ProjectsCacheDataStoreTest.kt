package com.c_od_e.data.store

import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.data.repository.ProjectsCache
import com.c_od_e.data.test.factory.DataFactory
import com.c_od_e.data.test.factory.ProjectFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsCacheDataStoreTest {

    private val cache = mock<ProjectsCache>()
    private val store = ProjectsCacheDataStore(cache)

    @Test
    fun getProjectsCompletes() {
        stubProjectsCacheGetProjects(Flowable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetProjects(Flowable.just(data))
        val testObserver = store.getProjects().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getProjectsCallsCacheSource() {
        stubProjectsCacheGetProjects(Flowable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects().test()
        verify(cache).getProjects()
    }

    @Test
    fun saveProjectsCompletes() {
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())
        val testObserver = store.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveProjectsCallsCacheStore() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())
        store.saveProjects(data).test()
        verify(cache).saveProjects(data)
    }

    @Test
    fun clearProjectsCompletes() {
        stubProjectsClearProjects(Completable.complete())
        val testObserver = store.clearProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearProjectsCallsCacheStore() {
        stubProjectsClearProjects(Completable.complete())
        store.clearProjects().test()
        verify(cache).clearProjects()
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubProjectsCacheGetBookmarkedProjects(Flowable.just(listOf(
                ProjectFactory.makeProjectEntity())))
        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsCallsCacheStore() {
        stubProjectsCacheGetBookmarkedProjects(Flowable.just(listOf(
                ProjectFactory.makeProjectEntity())))
        store.getBookmarkedProjects().test()
        verify(cache).getBookmarkedProjects()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetBookmarkedProjects(Flowable.just(data))
        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertValue(data)
    }

    @Test
    fun setProjectAsBookmarkedCompletes() {
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())
        val testObserver = store.setProjectAsBookmarked(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsBookmarkedCallsCacheStore() {
        val projectId = DataFactory.randomString()
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())
        store.setProjectAsBookmarked(projectId).test()
        verify(cache).setProjectAsBookmarked(projectId)
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        val testObserver = store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCallsCacheStore() {
        val projectId = DataFactory.randomString()
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        store.setProjectAsNotBookmarked(projectId).test()
        verify(cache).setProjectAsNotBookmarked(projectId)
    }

    private fun stubProjectsCacheGetProjects(observable: Flowable<List<ProjectEntity>>) {
        whenever(cache.getProjects())
                .thenReturn(observable)
    }

    private fun stubProjectsCacheSaveProjects(completable: Completable) {
        whenever(cache.saveProjects(any()))
                .thenReturn(completable)
    }

    private fun stubProjectsCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
                .thenReturn(completable)
    }

    private fun stubProjectsClearProjects(completable: Completable) {
        whenever(cache.clearProjects())
                .thenReturn(completable)
    }

    private fun stubProjectsCacheGetBookmarkedProjects(observable: Flowable<List<ProjectEntity>>) {
        whenever(cache.getBookmarkedProjects())
                .thenReturn(observable)
    }

    private fun stubProjectsCacheSetProjectAsBookmarked(completable: Completable) {
        whenever(cache.setProjectAsBookmarked(any()))
                .thenReturn(completable)
    }

    private fun stubProjectsCacheSetProjectAsNotBookmarked(completable: Completable) {
        whenever(cache.setProjectAsNotBookmarked(any()))
                .thenReturn(completable)
    }

}