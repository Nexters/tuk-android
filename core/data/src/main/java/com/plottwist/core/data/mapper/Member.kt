package com.plottwist.core.data.mapper

import com.plottwist.core.domain.model.MemberInfo
import com.plottwist.core.network.model.onboarding.MemberInfoData

fun MemberInfoData.toDomainModel() : MemberInfo {
    return MemberInfo(
        memberId = this.memberId,
        email = this.email,
        name = this.name
    )
}
