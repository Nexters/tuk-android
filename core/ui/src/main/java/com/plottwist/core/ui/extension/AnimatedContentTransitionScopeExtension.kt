package com.plottwist.core.ui.extension

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween

fun <S> AnimatedContentTransitionScope<S>.slideIn(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Start,
        animationSpec = tween(ANIMATION_TWEEN_MILLIS)
    )
}

fun <S> AnimatedContentTransitionScope<S>.slideOut(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Start,
        animationSpec = tween(ANIMATION_TWEEN_MILLIS)
    )
}

fun <S> AnimatedContentTransitionScope<S>.popSlideIn(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.End,
        animationSpec = tween(ANIMATION_TWEEN_MILLIS)
    )
}

fun <S> AnimatedContentTransitionScope<S>.popSlideOut(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.End,
        animationSpec = tween(ANIMATION_TWEEN_MILLIS)
    )
}

fun <S> AnimatedContentTransitionScope<S>.slideInVertically(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(ANIMATION_TWEEN_MILLIS)
    )
}

fun <S> AnimatedContentTransitionScope<S>.slideOutVertically(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(ANIMATION_TWEEN_MILLIS)
    )
}

fun <S> AnimatedContentTransitionScope<S>.popSlideInVertically(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(ANIMATION_TWEEN_MILLIS)
    )
}

fun <S> AnimatedContentTransitionScope<S>.popSlideOutVertically(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(ANIMATION_TWEEN_MILLIS)
    )
}

private const val ANIMATION_TWEEN_MILLIS = 300
