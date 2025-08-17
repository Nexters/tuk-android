package com.plottwist.feature.gathering_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.gathering_detail.alarm.GatheringDetailAlarmSettingScreen

fun NavController.navigateToGatheringDetailAlarmSetting(
    gatheringId: Long,
    selectedIntervalDays: Long,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = Route.GatheringAlarmSetting(gatheringId, selectedIntervalDays),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.gatheringDetailAlarmSettingNavGraph(
    onBack: () -> Unit,
    onNavigateGatheringDetail : (Long) -> Unit
) {
    composable<Route.GatheringAlarmSetting> {
        GatheringDetailAlarmSettingScreen(
            onNavigateBack = onBack,
            onNavigateGatheringDetail = onNavigateGatheringDetail
        )
    }
}
