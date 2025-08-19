package com.plottwist.feature.proposal_create.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectedGatheringParam(
    val id: Long?,
    val name: String?,
    val whenLabel: String,
    val whereLabel: String,
    val whatLabel: String
) : Parcelable
