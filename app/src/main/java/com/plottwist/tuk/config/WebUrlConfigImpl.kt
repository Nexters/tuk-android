package com.plottwist.tuk.config

import com.plottwist.core.weburl.WebUrlConfig
import com.plottwist.tuk.BuildConfig
import javax.inject.Inject

class WebUrlConfigImpl @Inject constructor() : WebUrlConfig {
    override val sentProposalUrl: String
        get() = BuildConfig.TUK_SENT_PROPOSAL_URL
}
