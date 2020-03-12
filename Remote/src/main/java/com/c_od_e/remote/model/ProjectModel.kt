package com.c_od_e.remote.model

import com.c_od_e.remote.model.OwnerModel
import com.google.gson.annotations.SerializedName

class ProjectModel(val id: String, val name: String,
                   @SerializedName("full_name") val fullName: String,
                   @SerializedName("stargazers_count") val starCount: Int,
                   @SerializedName("created_at") val dateCreated: String,
                   val owner: OwnerModel
)