package com.example.ui.components;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.DialogProperties;
import com.example.data.model.CustomerJobEntity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ReviewDialog.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001ae\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u000728\u0010\b\u001a4\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\u0010¨\u0006\u0011²\u0006\n\u0010\r\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u000eX\u008a\u008e\u0002"}, d2 = {"ReviewDialog", "", "job", "Lcom/example/data/model/CustomerJobEntity;", "isCompletedAction", "", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "rating", "", "feedback", "(Lcom/example/data/model/CustomerJobEntity;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class ReviewDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ReviewDialog$lambda$23(CustomerJobEntity customerJobEntity, boolean z, Function0 function0, Function2 function2, int i, Composer composer, int i2) {
        ReviewDialog(customerJobEntity, z, function0, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ReviewDialog(final CustomerJobEntity job, final boolean isCompletedAction, final Function0<Unit> function0, final Function2<? super Float, ? super String, Unit> function2, Composer $composer, final int $changed) {
        Object obj;
        Object obj2;
        long actionColor;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Intrinsics.checkNotNullParameter(function2, "onConfirm");
        Composer $composer3 = $composer.startRestartGroup(1987473527);
        ComposerKt.sourceInformation($composer3, "C(ReviewDialog)P(1!1,3)44@1795L67,45@1883L31,125@5286L394,136@5706L186,52@2179L516,67@2712L2548,50@2112L3786:ReviewDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(isCompletedAction) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1987473527, $dirty2, -1, "com.example.ui.components.ReviewDialog (ReviewDialog.kt:43)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1298380902, "CC(remember):ReviewDialog.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = PrimitiveSnapshotStateKt.mutableFloatStateOf(isCompletedAction ? 5.0f : 3.0f);
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            final MutableFloatState rating$delegate = (MutableFloatState) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1298378122, "CC(remember):ReviewDialog.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                obj2 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            final MutableState feedback$delegate = (MutableState) obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final String actionTitle = isCompletedAction ? "Mark Order as Completed" : "Cancel Order";
            if (isCompletedAction) {
                $composer3.startReplaceGroup(-1298372728);
                $composer3.endReplaceGroup();
                actionColor = ColorKt.Color(4279673674L);
            } else {
                $composer3.startReplaceGroup(-1298371172);
                ComposerKt.sourceInformation($composer3, "48@2089L11");
                actionColor = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getError-0d7_KjU();
                $composer3.endReplaceGroup();
            }
            final long actionColor2 = actionColor;
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function0, ComposableLambdaKt.rememberComposableLambda(-469215553, true, new Function2() { // from class: com.example.ui.components.ReviewDialogKt$$ExternalSyntheticLambda7
                public final Object invoke(Object obj3, Object obj4) {
                    return ReviewDialogKt.ReviewDialog$lambda$10(actionColor2, function2, rating$delegate, feedback$delegate, isCompletedAction, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-399567679, true, new Function2() { // from class: com.example.ui.components.ReviewDialogKt$$ExternalSyntheticLambda8
                public final Object invoke(Object obj3, Object obj4) {
                    return ReviewDialogKt.ReviewDialog$lambda$11(function0, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableLambdaKt.rememberComposableLambda(-329919805, true, new Function2() { // from class: com.example.ui.components.ReviewDialogKt$$ExternalSyntheticLambda9
                public final Object invoke(Object obj3, Object obj4) {
                    return ReviewDialogKt.ReviewDialog$lambda$13(actionTitle, actionColor2, job, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-295095868, true, new Function2() { // from class: com.example.ui.components.ReviewDialogKt$$ExternalSyntheticLambda10
                public final Object invoke(Object obj3, Object obj4) {
                    return ReviewDialogKt.ReviewDialog$lambda$22(CustomerJobEntity.this, rating$delegate, feedback$delegate, isCompletedAction, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty2 >> 6) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.ReviewDialogKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj3, Object obj4) {
                    return ReviewDialogKt.ReviewDialog$lambda$23(CustomerJobEntity.this, isCompletedAction, function0, function2, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final float ReviewDialog$lambda$1(MutableFloatState $rating$delegate) {
        return ((FloatState) $rating$delegate).getFloatValue();
    }

    private static final String ReviewDialog$lambda$4(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ReviewDialog$lambda$13(java.lang.String r50, long r51, com.example.data.model.CustomerJobEntity r53, androidx.compose.runtime.Composer r54, int r55) {
        /*
            Method dump skipped, instructions count: 464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ReviewDialogKt.ReviewDialog$lambda$13(java.lang.String, long, com.example.data.model.CustomerJobEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x041e A[EDGE_INSN: B:61:0x041e->B:62:0x041e BREAK  A[LOOP:0: B:38:0x0340->B:54:0x03db], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x04d2  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x056c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ReviewDialog$lambda$22(com.example.data.model.CustomerJobEntity r70, final androidx.compose.runtime.MutableFloatState r71, final androidx.compose.runtime.MutableState r72, final boolean r73, androidx.compose.runtime.Composer r74, int r75) {
        /*
            Method dump skipped, instructions count: 1394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ReviewDialogKt.ReviewDialog$lambda$22(com.example.data.model.CustomerJobEntity, androidx.compose.runtime.MutableFloatState, androidx.compose.runtime.MutableState, boolean, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ReviewDialog$lambda$22$lambda$21$lambda$16$lambda$15$lambda$14(int $i, MutableFloatState $rating$delegate) {
        $rating$delegate.setFloatValue($i);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ReviewDialog$lambda$22$lambda$21$lambda$18$lambda$17(MutableState $feedback$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $feedback$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ReviewDialog$lambda$22$lambda$21$lambda$19(boolean $isCompletedAction, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C116@4802L98:ReviewDialog.kt#qonjpd");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1924954028, $changed, -1, "com.example.ui.components.ReviewDialog.<anonymous>.<anonymous>.<anonymous> (ReviewDialog.kt:116)");
            }
            TextKt.Text--4IGK_g($isCompletedAction ? "Customer Feedback / Notes (Optional)" : "Reason for Cancellation", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ReviewDialog$lambda$22$lambda$21$lambda$20(boolean $isCompletedAction, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C117@4940L98:ReviewDialog.kt#qonjpd");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-753745357, $changed, -1, "com.example.ui.components.ReviewDialog.<anonymous>.<anonymous>.<anonymous> (ReviewDialog.kt:117)");
            }
            TextKt.Text--4IGK_g($isCompletedAction ? "e.g. Excellent service, punctual" : "e.g. Customer not available", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ReviewDialog$lambda$10(long $actionColor, final Function2 $onConfirm, final MutableFloatState $rating$delegate, final MutableState $feedback$delegate, final boolean $isCompletedAction, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C128@5424L80,127@5334L48,132@5569L101,126@5300L370:ReviewDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-469215553, $changed, -1, "com.example.ui.components.ReviewDialog.<anonymous> (ReviewDialog.kt:126)");
            }
            ButtonColors buttonColors = ButtonDefaults.INSTANCE.buttonColors-ro_MJ88($actionColor, 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14);
            Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8));
            ComposerKt.sourceInformationMarkerStart($composer, 55421231, "CC(remember):ReviewDialog.kt#9igjgp");
            boolean changed = $composer.changed($onConfirm);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.ReviewDialogKt$$ExternalSyntheticLambda0
                    public final Object invoke() {
                        return ReviewDialogKt.ReviewDialog$lambda$10$lambda$8$lambda$7($onConfirm, $rating$delegate, $feedback$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, shape, buttonColors, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-1305988433, true, new Function3() { // from class: com.example.ui.components.ReviewDialogKt$$ExternalSyntheticLambda2
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return ReviewDialogKt.ReviewDialog$lambda$10$lambda$9($isCompletedAction, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, 486);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ReviewDialog$lambda$10$lambda$8$lambda$7(Function2 $onConfirm, MutableFloatState $rating$delegate, MutableState $feedback$delegate) {
        Float valueOf = Float.valueOf(ReviewDialog$lambda$1($rating$delegate));
        String ReviewDialog$lambda$4 = ReviewDialog$lambda$4($feedback$delegate);
        if (StringsKt.isBlank(ReviewDialog$lambda$4)) {
            ReviewDialog$lambda$4 = null;
        }
        $onConfirm.invoke(valueOf, ReviewDialog$lambda$4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ReviewDialog$lambda$10$lambda$9(boolean $isCompletedAction, RowScope $this$Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C133@5587L69:ReviewDialog.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1305988433, $changed, -1, "com.example.ui.components.ReviewDialog.<anonymous>.<anonymous> (ReviewDialog.kt:133)");
            }
            TextKt.Text--4IGK_g($isCompletedAction ? "Confirm Complete" : "Confirm Cancel", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ReviewDialog$lambda$11(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C137@5720L162:ReviewDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-399567679, $changed, -1, "com.example.ui.components.ReviewDialog.<anonymous> (ReviewDialog.kt:137)");
            }
            ButtonKt.OutlinedButton($onDismiss, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$ReviewDialogKt.INSTANCE.m104getLambda$1747126861$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
