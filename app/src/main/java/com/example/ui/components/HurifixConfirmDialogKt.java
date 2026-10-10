package com.example.ui.components;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.WarningKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.DialogProperties;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HurifixConfirmDialog.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aa\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0007¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"HurifixConfirmDialog", "", "title", "", "message", "confirmText", "dismissText", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "isDestructive", "", "onConfirm", "Lkotlin/Function0;", "onDismiss", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class HurifixConfirmDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HurifixConfirmDialog$lambda$9(String str, String str2, String str3, String str4, ImageVector imageVector, boolean z, Function0 function0, Function0 function02, int i, int i2, Composer composer, int i3) {
        HurifixConfirmDialog(str, str2, str3, str4, imageVector, z, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void HurifixConfirmDialog(final String title, final String message, String confirmText, String dismissText, ImageVector icon, boolean isDestructive, final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed, final int i) {
        String confirmText2;
        String dismissText2;
        ImageVector icon2;
        boolean z;
        int $dirty;
        final String confirmText3;
        final String dismissText3;
        final ImageVector icon3;
        final boolean isDestructive2;
        Composer $composer2;
        final String dismissText4;
        final ImageVector icon4;
        final boolean isDestructive3;
        int i2;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(function0, "onConfirm");
        Intrinsics.checkNotNullParameter(function02, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(493151377);
        ComposerKt.sourceInformation($composer3, "C(HurifixConfirmDialog)P(7,4)55@1861L573,71@2460L185,34@1183L235,41@1436L179,48@1632L203,32@1117L1577:HurifixConfirmDialog.kt#qonjpd");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(title) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(message) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            confirmText2 = confirmText;
        } else if (($changed & 384) == 0) {
            confirmText2 = confirmText;
            $dirty2 |= $composer3.changed(confirmText2) ? 256 : 128;
        } else {
            confirmText2 = confirmText;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            dismissText2 = dismissText;
        } else if (($changed & 3072) == 0) {
            dismissText2 = dismissText;
            $dirty2 |= $composer3.changed(dismissText2) ? 2048 : 1024;
        } else {
            dismissText2 = dismissText;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                icon2 = icon;
                if ($composer3.changed(icon2)) {
                    i2 = 16384;
                    $dirty2 |= i2;
                }
            } else {
                icon2 = icon;
            }
            i2 = 8192;
            $dirty2 |= i2;
        } else {
            icon2 = icon;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= 196608;
            z = isDestructive;
        } else if ((196608 & $changed) == 0) {
            z = isDestructive;
            $dirty2 |= $composer3.changed(z) ? 131072 : 65536;
        } else {
            z = isDestructive;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(function02) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty2) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            confirmText3 = confirmText2;
            dismissText4 = dismissText2;
            isDestructive3 = z;
            icon4 = icon2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                $dirty = $dirty2;
                confirmText3 = confirmText2;
                dismissText3 = dismissText2;
                icon3 = icon2;
                isDestructive2 = z;
            } else {
                if (i3 != 0) {
                    confirmText2 = "Confirm";
                }
                if (i4 != 0) {
                    dismissText2 = "Cancel";
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    icon2 = WarningKt.getWarning(Icons.INSTANCE.getDefault());
                }
                if (i5 == 0) {
                    $dirty = $dirty2;
                    confirmText3 = confirmText2;
                    dismissText3 = dismissText2;
                    icon3 = icon2;
                    isDestructive2 = z;
                } else {
                    String str = confirmText2;
                    isDestructive2 = false;
                    confirmText3 = str;
                    $dirty = $dirty2;
                    dismissText3 = dismissText2;
                    icon3 = icon2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(493151377, $dirty, -1, "com.example.ui.components.HurifixConfirmDialog (HurifixConfirmDialog.kt:31)");
            }
            $composer2 = $composer3;
            boolean isDestructive4 = isDestructive2;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function02, ComposableLambdaKt.rememberComposableLambda(1017107673, true, new Function2() { // from class: com.example.ui.components.HurifixConfirmDialogKt$$ExternalSyntheticLambda0
                public final Object invoke(Object obj, Object obj2) {
                    return HurifixConfirmDialogKt.HurifixConfirmDialog$lambda$3(isDestructive2, function0, function02, confirmText3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(1036648795, true, new Function2() { // from class: com.example.ui.components.HurifixConfirmDialogKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj, Object obj2) {
                    return HurifixConfirmDialogKt.HurifixConfirmDialog$lambda$5(function02, dismissText3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(1046419356, true, new Function2() { // from class: com.example.ui.components.HurifixConfirmDialogKt$$ExternalSyntheticLambda2
                public final Object invoke(Object obj, Object obj2) {
                    return HurifixConfirmDialogKt.HurifixConfirmDialog$lambda$6(icon3, isDestructive2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(1056189917, true, new Function2() { // from class: com.example.ui.components.HurifixConfirmDialogKt$$ExternalSyntheticLambda3
                public final Object invoke(Object obj, Object obj2) {
                    return HurifixConfirmDialogKt.HurifixConfirmDialog$lambda$7(title, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(1065960478, true, new Function2() { // from class: com.example.ui.components.HurifixConfirmDialogKt$$ExternalSyntheticLambda4
                public final Object invoke(Object obj, Object obj2) {
                    return HurifixConfirmDialogKt.HurifixConfirmDialog$lambda$8(message, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(16)), 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty >> 21) & 14) | 1797168, 0, 16132);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            dismissText4 = dismissText3;
            icon4 = icon3;
            isDestructive3 = isDestructive4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.HurifixConfirmDialogKt$$ExternalSyntheticLambda5
                public final Object invoke(Object obj, Object obj2) {
                    return HurifixConfirmDialogKt.HurifixConfirmDialog$lambda$9(title, message, confirmText3, dismissText4, icon4, isDestructive3, function0, function02, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HurifixConfirmDialog$lambda$6(ImageVector $icon, boolean $isDestructive, Composer $composer, int $changed) {
        long j;
        ComposerKt.sourceInformation($composer, "C35@1197L211:HurifixConfirmDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1046419356, $changed, -1, "com.example.ui.components.HurifixConfirmDialog.<anonymous> (HurifixConfirmDialog.kt:35)");
            }
            if ($isDestructive) {
                $composer.startReplaceGroup(586659201);
                ComposerKt.sourceInformation($composer, "38@1338L11");
                j = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError-0d7_KjU();
            } else {
                $composer.startReplaceGroup(586660387);
                ComposerKt.sourceInformation($composer, "38@1375L11");
                j = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary-0d7_KjU();
            }
            $composer.endReplaceGroup();
            IconKt.Icon-ww6aTOc($icon, (String) null, (Modifier) null, j, $composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HurifixConfirmDialog$lambda$7(String $title, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C44@1524L10,42@1450L155:HurifixConfirmDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1056189917, $changed, -1, "com.example.ui.components.HurifixConfirmDialog.<anonymous> (HurifixConfirmDialog.kt:42)");
            }
            TextKt.Text--4IGK_g($title, (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleLarge(), $composer, 196608, 0, 65502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HurifixConfirmDialog$lambda$8(String $message, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C51@1722L10,52@1783L11,49@1646L179:HurifixConfirmDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1065960478, $changed, -1, "com.example.ui.components.HurifixConfirmDialog.<anonymous> (HurifixConfirmDialog.kt:49)");
            }
            TextKt.Text--4IGK_g($message, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 0, 0, 65530);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HurifixConfirmDialog$lambda$3(boolean $isDestructive, final Function0 $onConfirm, final Function0 $onDismiss, final String $confirmText, Composer $composer, int $changed) {
        ButtonColors buttonColors;
        Object obj;
        ComposerKt.sourceInformation($composer, "C57@1909L83,67@2345L79,56@1875L549:HurifixConfirmDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1017107673, $changed, -1, "com.example.ui.components.HurifixConfirmDialog.<anonymous> (HurifixConfirmDialog.kt:56)");
            }
            if ($isDestructive) {
                $composer.startReplaceGroup(-339407724);
                ComposerKt.sourceInformation($composer, "62@2119L11,62@2075L62");
                ButtonColors buttonColors2 = ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14);
                $composer.endReplaceGroup();
                buttonColors = buttonColors2;
            } else {
                $composer.startReplaceGroup(-339285646);
                ComposerKt.sourceInformation($composer, "64@2242L11,64@2198L64");
                ButtonColors buttonColors3 = ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary-0d7_KjU(), 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14);
                $composer.endReplaceGroup();
                buttonColors = buttonColors3;
            }
            Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8));
            ComposerKt.sourceInformationMarkerStart($composer, -1812068116, "CC(remember):HurifixConfirmDialog.kt#9igjgp");
            boolean changed = $composer.changed($onConfirm) | $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.HurifixConfirmDialogKt$$ExternalSyntheticLambda6
                    public final Object invoke() {
                        return HurifixConfirmDialogKt.HurifixConfirmDialog$lambda$3$lambda$1$lambda$0($onConfirm, $onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, shape, buttonColors, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-1075102519, true, new Function3() { // from class: com.example.ui.components.HurifixConfirmDialogKt$$ExternalSyntheticLambda7
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return HurifixConfirmDialogKt.HurifixConfirmDialog$lambda$3$lambda$2($confirmText, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, 486);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HurifixConfirmDialog$lambda$3$lambda$1$lambda$0(Function0 $onConfirm, Function0 $onDismiss) {
        $onConfirm.invoke();
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HurifixConfirmDialog$lambda$3$lambda$2(String $confirmText, RowScope $this$Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C68@2363L47:HurifixConfirmDialog.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1075102519, $changed, -1, "com.example.ui.components.HurifixConfirmDialog.<anonymous>.<anonymous> (HurifixConfirmDialog.kt:68)");
            }
            TextKt.Text--4IGK_g($confirmText, (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196608, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HurifixConfirmDialog$lambda$5(Function0 $onDismiss, final String $dismissText, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C75@2586L49,72@2474L161:HurifixConfirmDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1036648795, $changed, -1, "com.example.ui.components.HurifixConfirmDialog.<anonymous> (HurifixConfirmDialog.kt:72)");
            }
            ButtonKt.TextButton($onDismiss, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-1895484904, true, new Function3() { // from class: com.example.ui.components.HurifixConfirmDialogKt$$ExternalSyntheticLambda8
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HurifixConfirmDialogKt.HurifixConfirmDialog$lambda$5$lambda$4($dismissText, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HurifixConfirmDialog$lambda$5$lambda$4(String $dismissText, RowScope $this$TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C76@2604L17:HurifixConfirmDialog.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1895484904, $changed, -1, "com.example.ui.components.HurifixConfirmDialog.<anonymous>.<anonymous> (HurifixConfirmDialog.kt:76)");
            }
            TextKt.Text--4IGK_g($dismissText, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
