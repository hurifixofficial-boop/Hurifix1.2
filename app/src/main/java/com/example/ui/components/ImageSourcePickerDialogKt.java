package com.example.ui.components;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
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
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageSourcePickerDialog.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aA\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\b\u001aE\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"ImageSourcePickerDialog", "", "title", "", "onSelectCamera", "Lkotlin/Function0;", "onSelectGallery", "onDismiss", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "PhotoSourceOptionRow", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "subtitle", "iconContainerColor", "Landroidx/compose/ui/graphics/Color;", "iconColor", "onClick", "PhotoSourceOptionRow-jA1GFJw", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;JJLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class ImageSourcePickerDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ImageSourcePickerDialog$lambda$8(String str, Function0 function0, Function0 function02, Function0 function03, int i, int i2, Composer composer, int i3) {
        ImageSourcePickerDialog(str, function0, function02, function03, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit PhotoSourceOptionRow_jA1GFJw$lambda$14(ImageVector imageVector, String str, String str2, long j, long j2, Function0 function0, int i, Composer composer, int i2) {
        m131PhotoSourceOptionRowjA1GFJw(imageVector, str, str2, j, j2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ImageSourcePickerDialog(String title, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03, Composer $composer, final int $changed, final int i) {
        String str;
        final String title2;
        final String title3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(function0, "onSelectCamera");
        Intrinsics.checkNotNullParameter(function02, "onSelectGallery");
        Intrinsics.checkNotNullParameter(function03, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(1990091594);
        ComposerKt.sourceInformation($composer3, "C(ImageSourcePickerDialog)P(3,1,2)85@3364L102,41@1647L180,48@1844L1466,39@1580L1892:ImageSourcePickerDialog.kt#qonjpd");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            str = title;
        } else if (($changed & 6) == 0) {
            str = title;
            $dirty |= $composer3.changed(str) ? 4 : 2;
        } else {
            str = title;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function03) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            title3 = str;
        } else {
            if (i2 != 0) {
                title2 = "Select Photo Source";
            } else {
                title2 = str;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1990091594, $dirty, -1, "com.example.ui.components.ImageSourcePickerDialog (ImageSourcePickerDialog.kt:38)");
            }
            title3 = title2;
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function03, ComposableSingletons$ImageSourcePickerDialogKt.INSTANCE.getLambda$640872962$app(), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-282294464, true, new Function2() { // from class: com.example.ui.components.ImageSourcePickerDialogKt$$ExternalSyntheticLambda2
                public final Object invoke(Object obj, Object obj2) {
                    return ImageSourcePickerDialogKt.ImageSourcePickerDialog$lambda$0(function03, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableLambdaKt.rememberComposableLambda(-1205461890, true, new Function2() { // from class: com.example.ui.components.ImageSourcePickerDialogKt$$ExternalSyntheticLambda3
                public final Object invoke(Object obj, Object obj2) {
                    return ImageSourcePickerDialogKt.ImageSourcePickerDialog$lambda$1(title2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(480438045, true, new Function2() { // from class: com.example.ui.components.ImageSourcePickerDialogKt$$ExternalSyntheticLambda4
                public final Object invoke(Object obj, Object obj2) {
                    return ImageSourcePickerDialogKt.ImageSourcePickerDialog$lambda$7(function03, function0, function02, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty >> 9) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.ImageSourcePickerDialogKt$$ExternalSyntheticLambda5
                public final Object invoke(Object obj, Object obj2) {
                    return ImageSourcePickerDialogKt.ImageSourcePickerDialog$lambda$8(title3, function0, function02, function03, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ImageSourcePickerDialog$lambda$1(String $title, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C44@1735L10,42@1661L156:ImageSourcePickerDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1205461890, $changed, -1, "com.example.ui.components.ImageSourcePickerDialog.<anonymous> (ImageSourcePickerDialog.kt:42)");
            }
            TextKt.Text--4IGK_g($title, (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), $composer, 196608, 0, 65502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x025d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ImageSourcePickerDialog$lambda$7(final kotlin.jvm.functions.Function0 r52, final kotlin.jvm.functions.Function0 r53, final kotlin.jvm.functions.Function0 r54, androidx.compose.runtime.Composer r55, int r56) {
        /*
            Method dump skipped, instructions count: 675
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ImageSourcePickerDialogKt.ImageSourcePickerDialog$lambda$7(kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ImageSourcePickerDialog$lambda$7$lambda$6$lambda$3$lambda$2(Function0 $onDismiss, Function0 $onSelectCamera) {
        $onDismiss.invoke();
        $onSelectCamera.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ImageSourcePickerDialog$lambda$7$lambda$6$lambda$5$lambda$4(Function0 $onDismiss, Function0 $onSelectGallery) {
        $onDismiss.invoke();
        $onSelectGallery.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ImageSourcePickerDialog$lambda$0(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C86@3378L78:ImageSourcePickerDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-282294464, $changed, -1, "com.example.ui.components.ImageSourcePickerDialog.<anonymous> (ImageSourcePickerDialog.kt:86)");
            }
            ButtonKt.TextButton($onDismiss, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$ImageSourcePickerDialogKt.INSTANCE.m83getLambda$1899183133$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* renamed from: PhotoSourceOptionRow-jA1GFJw, reason: not valid java name */
    private static final void m131PhotoSourceOptionRowjA1GFJw(final ImageVector icon, final String title, final String subtitle, final long iconContainerColor, final long iconColor, final Function0<Unit> function0, Composer $composer, final int $changed) {
        final ImageVector imageVector;
        String str;
        String str2;
        final long j;
        long j2;
        Function0<Unit> function02;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1738299763);
        ComposerKt.sourceInformation($composer3, "C(PhotoSourceOptionRow)P(!1,5,4,2:c#ui.graphics.Color,1:c#ui.graphics.Color)105@3780L11,107@3876L1340,102@3671L1545:ImageSourcePickerDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            imageVector = icon;
            $dirty |= $composer3.changed(imageVector) ? 4 : 2;
        } else {
            imageVector = icon;
        }
        if (($changed & 48) == 0) {
            str = title;
            $dirty |= $composer3.changed(str) ? 32 : 16;
        } else {
            str = title;
        }
        if (($changed & 384) == 0) {
            str2 = subtitle;
            $dirty |= $composer3.changed(str2) ? 256 : 128;
        } else {
            str2 = subtitle;
        }
        if (($changed & 3072) == 0) {
            j = iconContainerColor;
            $dirty |= $composer3.changed(j) ? 2048 : 1024;
        } else {
            j = iconContainerColor;
        }
        if (($changed & 24576) == 0) {
            j2 = iconColor;
            $dirty |= $composer3.changed(j2) ? 16384 : 8192;
        } else {
            j2 = iconColor;
        }
        if ((196608 & $changed) == 0) {
            function02 = function0;
            $dirty |= $composer3.changedInstance(function02) ? 131072 : 65536;
        } else {
            function02 = function0;
        }
        if ((74899 & $dirty) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1738299763, $dirty, -1, "com.example.ui.components.PhotoSourceOptionRow (ImageSourcePickerDialog.kt:101)");
            }
            final String str3 = str;
            final String str4 = str2;
            final long j3 = j2;
            $composer2 = $composer3;
            SurfaceKt.Surface-o_FOJdg(function02, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12)), Color.copy-wmQWz5c$default(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurfaceVariant-0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, (Object) null), 0L, 0.0f, 0.0f, (BorderStroke) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(1569849214, true, new Function2() { // from class: com.example.ui.components.ImageSourcePickerDialogKt$$ExternalSyntheticLambda6
                public final Object invoke(Object obj, Object obj2) {
                    return ImageSourcePickerDialogKt.PhotoSourceOptionRow_jA1GFJw$lambda$13(j, imageVector, j3, str3, str4, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty >> 15) & 14) | 48, 6, 996);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.ImageSourcePickerDialogKt$$ExternalSyntheticLambda7
                public final Object invoke(Object obj, Object obj2) {
                    return ImageSourcePickerDialogKt.PhotoSourceOptionRow_jA1GFJw$lambda$14(icon, title, subtitle, iconContainerColor, iconColor, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0240  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit PhotoSourceOptionRow_jA1GFJw$lambda$13(long r72, final androidx.compose.ui.graphics.vector.ImageVector r74, final long r75, java.lang.String r77, java.lang.String r78, androidx.compose.runtime.Composer r79, int r80) {
        /*
            Method dump skipped, instructions count: 895
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ImageSourcePickerDialogKt.PhotoSourceOptionRow_jA1GFJw$lambda$13(long, androidx.compose.ui.graphics.vector.ImageVector, long, java.lang.String, java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0169  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit PhotoSourceOptionRow_jA1GFJw$lambda$13$lambda$12$lambda$10(androidx.compose.ui.graphics.vector.ImageVector r32, long r33, androidx.compose.runtime.Composer r35, int r36) {
        /*
            Method dump skipped, instructions count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ImageSourcePickerDialogKt.PhotoSourceOptionRow_jA1GFJw$lambda$13$lambda$12$lambda$10(androidx.compose.ui.graphics.vector.ImageVector, long, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }
}
