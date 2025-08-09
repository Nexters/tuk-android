package com.plottwist.tuk.config

import com.plottwist.core.weburl.WebUrlConfig
import com.plottwist.tuk.BuildConfig
import javax.inject.Inject

class WebUrlConfigImpl @Inject constructor() : WebUrlConfig {
    override val sentProposalsUrl: String
        get() = BuildConfig.TUK_SENT_PROPOSAL_URL
    override val proposalsUrl: String
        get() = BuildConfig.TUK_PROPOSALS_URL
    override val inviteGatheringUrl: String
        get() = BuildConfig.TUK_INVITE_GATHERING_URL
}
