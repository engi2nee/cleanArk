package com.c_od_e.mobile_ui.factory

import com.c_od_e.mobile_ui.factory.DataFactory
import com.c_od_e.presentation.model.ProjectView

object ProjectFactory {

    fun makeProjectView(): ProjectView {
        return ProjectView(
            DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomBoolean())
    }

}