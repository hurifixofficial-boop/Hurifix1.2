package com.example.ui.components;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.Easing;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.FloatingActionButtonDefaults;
import androidx.compose.material3.FloatingActionButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScrollToTopButton.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"ScrollToTopButton", "", "visible", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class ScrollToTopButtonKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ScrollToTopButton$lambda$1(boolean z, Function0 function0, Modifier modifier, int i, int i2, Composer composer, int i3) {
        ScrollToTopButton(z, function0, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void ScrollToTopButton(final boolean visible, final Function0<Unit> function0, Modifier modifier, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        Modifier modifier3;
        final Modifier modifier4;
        Intrinsics.checkNotNullParameter(function0, "onClick");
        Composer $composer2 = $composer.startRestartGroup(1837067760);
        ComposerKt.sourceInformation($composer2, "C(ScrollToTopButton)P(2,1)37@1378L627,32@1180L825:ScrollToTopButton.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = visible;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
        } else {
            if (i2 != 0) {
                modifier3 = (Modifier) Modifier.Companion;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1837067760, $dirty2, -1, "com.example.ui.components.ScrollToTopButton (ScrollToTopButton.kt:31)");
            }
            AnimatedVisibilityKt.AnimatedVisibility(z, modifier3, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(250, 0, (Easing) null, 6, (Object) null), 0.0f, 2, (Object) null).plus(EnterExitTransitionKt.scaleIn-L8ZKh-E$default(AnimationSpecKt.tween$default(250, 0, (Easing) null, 6, (Object) null), 0.0f, 0L, 6, (Object) null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, (Easing) null, 6, (Object) null), 0.0f, 2, (Object) null).plus(EnterExitTransitionKt.scaleOut-L8ZKh-E$default(AnimationSpecKt.tween$default(200, 0, (Easing) null, 6, (Object) null), 0.0f, 0L, 6, (Object) null)), (String) null, ComposableLambdaKt.rememberComposableLambda(468545224, true, new Function3() { // from class: com.example.ui.components.ScrollToTopButtonKt$$ExternalSyntheticLambda0
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ScrollToTopButtonKt.ScrollToTopButton$lambda$0(function0, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty2 & 14) | 200064 | (($dirty2 >> 3) & 112), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.ScrollToTopButtonKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj, Object obj2) {
                    return ScrollToTopButtonKt.ScrollToTopButton$lambda$1(visible, function0, modifier4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ScrollToTopButton$lambda$0(Function0 $onClick, AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C40@1484L11,43@1631L106,38@1388L611:ScrollToTopButton.kt#qonjpd");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(468545224, $changed, -1, "com.example.ui.components.ScrollToTopButton.<anonymous> (ScrollToTopButton.kt:38)");
        }
        FloatingActionButtonKt.FloatingActionButton-X-z6DiA($onClick, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(54)), RoundedCornerShapeKt.getCircleShape(), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary-0d7_KjU(), Color.Companion.getWhite-0d7_KjU(), FloatingActionButtonDefaults.INSTANCE.elevation-xZ9-QkE(Dp.constructor-impl(6), Dp.constructor-impl(10), 0.0f, 0.0f, $composer, (FloatingActionButtonDefaults.$stable << 12) | 54, 12), (MutableInteractionSource) null, ComposableSingletons$ScrollToTopButtonKt.INSTANCE.m105getLambda$1598086326$app(), $composer, 12607536, 64);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }
}
