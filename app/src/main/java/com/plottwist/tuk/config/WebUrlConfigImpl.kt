package com.plottwist.tuk.config

import com.plottwist.core.weburl.WebUrlConfig
import com.plottwist.tuk.BuildConfig
import javax.inject.Inject

class WebUrlConfigImpl @Inject constructor() : WebUrlConfig {
    override val sentProposalsUrl: String
        get() = "https://www.tuk.kr/gathering/{gatheringId}/invites"
    override val proposalsUrl: String
        get() = "https://www.tuk.kr/gathering/{gatheringId}/invites"
    override val inviteGatheringUrl: String
        get() = "https://www.tuk.kr/invite/gathering/{gatheringId}"
    override val completeProposalUrl: String
        get() = "https://www.tuk.kr/invite/meet/{meetId}"
}
