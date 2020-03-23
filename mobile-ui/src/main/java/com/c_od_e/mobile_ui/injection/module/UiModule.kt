package com.c_od_e.mobile_ui.injection.module

import com.c_od_e.mobile_ui.UiThread
import com.c_od_e.mobile_ui.bookmarked.BookmarkedActivity
import com.c_od_e.mobile_ui.browse.BrowseActivity
import com.c_od_e.domain.excuter.PostExecutionThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity
}