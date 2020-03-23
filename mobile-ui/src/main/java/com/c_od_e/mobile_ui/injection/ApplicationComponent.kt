package com.c_od_e.mobile_ui.injection

import android.app.Application
import com.c_od_e.mobile_ui.CleanArkApplication
import com.c_od_e.mobile_ui.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, UiModule::class,
    PresentationModule::class, DataModule::class, CacheModule::class, RemoteModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: CleanArkApplication)

}