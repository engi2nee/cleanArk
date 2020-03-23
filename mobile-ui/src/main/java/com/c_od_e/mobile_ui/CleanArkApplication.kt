package com.c_od_e.mobile_ui

import android.app.Activity
import android.app.Application
import com.c_od_e.mobile_ui.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class CleanArkApplication: Application(), HasActivityInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

}