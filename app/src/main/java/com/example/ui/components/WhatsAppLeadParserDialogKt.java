package com.example.ui.components;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.material3.TopAppBarScrollBehavior;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WhatsAppLeadParserDialog.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a/\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\u0006X\u008a\u008e\u0002"}, d2 = {"WhatsAppLeadParserDialog", "", "onDismiss", "Lkotlin/Function0;", "onParseText", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app", "inputText"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class WhatsAppLeadParserDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WhatsAppLeadParserDialog$lambda$19(Function0 function0, Function1 function1, int i, Composer composer, int i2) {
        WhatsAppLeadParserDialog(function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void WhatsAppLeadParserDialog(final Function0<Unit> function0, final Function1<? super String, Unit> function1, Composer $composer, final int $changed) {
        Object obj;
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Intrinsics.checkNotNullParameter(function1, "onParseText");
        Composer $composer2 = $composer.startRestartGroup(-228832252);
        ComposerKt.sourceInformation($composer2, "C(WhatsAppLeadParserDialog)57@2531L7,58@2560L31,63@2752L5401,60@2597L5556:WhatsAppLeadParserDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-228832252, $dirty2, -1, "com.example.ui.components.WhatsAppLeadParserDialog (WhatsAppLeadParserDialog.kt:56)");
            }
            CompositionLocal localClipboardManager = CompositionLocalsKt.getLocalClipboardManager();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localClipboardManager);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final ClipboardManager clipboardManager = (ClipboardManager) consume;
            ComposerKt.sourceInformationMarkerStart($composer2, -864454845, "CC(remember):WhatsAppLeadParserDialog.kt#9igjgp");
            Object rememberedValue = $composer2.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer2.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            final MutableState inputText$delegate = (MutableState) obj;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AndroidDialog_androidKt.Dialog(function0, new DialogProperties(false, false, (SecureFlagPolicy) null, false, false, 7, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(-1883005043, true, new Function2() { // from class: com.example.ui.components.WhatsAppLeadParserDialogKt$$ExternalSyntheticLambda0
                public final Object invoke(Object obj2, Object obj3) {
                    return WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$18(function0, function1, inputText$delegate, clipboardManager, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty2 & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.WhatsAppLeadParserDialogKt$$ExternalSyntheticLambda2
                public final Object invoke(Object obj2, Object obj3) {
                    return WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$19(function0, function1, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final String WhatsAppLeadParserDialog$lambda$1(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WhatsAppLeadParserDialog$lambda$18(final Function0 $onDismiss, final Function1 $onParseText, final MutableState $inputText$delegate, final ClipboardManager $clipboardManager, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C64@2774L15,64@2762L27,67@2876L1574,99@4476L1511,135@5998L2149,65@2798L5349:WhatsAppLeadParserDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1883005043, $changed, -1, "com.example.ui.components.WhatsAppLeadParserDialog.<anonymous> (WhatsAppLeadParserDialog.kt:64)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1386613988, "CC(remember):WhatsAppLeadParserDialog.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.WhatsAppLeadParserDialogKt$$ExternalSyntheticLambda7
                    public final Object invoke() {
                        return WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$18$lambda$4$lambda$3($onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackHandlerKt.BackHandler(false, (Function0) obj, $composer, 0, 1);
            ScaffoldKt.Scaffold-TvnljyQ(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ComposableLambdaKt.rememberComposableLambda(1557033033, true, new Function2() { // from class: com.example.ui.components.WhatsAppLeadParserDialogKt$$ExternalSyntheticLambda8
                public final Object invoke(Object obj2, Object obj3) {
                    return WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$18$lambda$6($onDismiss, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), ComposableLambdaKt.rememberComposableLambda(872802728, true, new Function2() { // from class: com.example.ui.components.WhatsAppLeadParserDialogKt$$ExternalSyntheticLambda9
                public final Object invoke(Object obj2, Object obj3) {
                    return WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$18$lambda$11($onDismiss, $onParseText, $inputText$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, 0, 0L, 0L, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(204057246, true, new Function3() { // from class: com.example.ui.components.WhatsAppLeadParserDialogKt$$ExternalSyntheticLambda10
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$18$lambda$17($clipboardManager, $inputText$delegate, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306806, 504);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WhatsAppLeadParserDialog$lambda$18$lambda$4$lambda$3(Function0 $onDismiss) {
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WhatsAppLeadParserDialog$lambda$18$lambda$6(final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C89@4028L206,95@4355L11,94@4283L135,68@2894L1542:WhatsAppLeadParserDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1557033033, $changed, -1, "com.example.ui.components.WhatsAppLeadParserDialog.<anonymous>.<anonymous> (WhatsAppLeadParserDialog.kt:68)");
            }
            AppBarKt.TopAppBar-GHTll3U(ComposableSingletons$WhatsAppLeadParserDialogKt.INSTANCE.getLambda$387845389$app(), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(386011023, true, new Function2() { // from class: com.example.ui.components.WhatsAppLeadParserDialogKt$$ExternalSyntheticLambda6
                public final Object invoke(Object obj, Object obj2) {
                    return WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$18$lambda$6$lambda$5($onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function3) null, 0.0f, (WindowInsets) null, TopAppBarDefaults.INSTANCE.topAppBarColors-zjMxDiM(ColorSchemeKt.surfaceColorAtElevation-3ABfNKs(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable), Dp.constructor-impl(3)), 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 15, 30), (TopAppBarScrollBehavior) null, $composer, 390, 186);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WhatsAppLeadParserDialog$lambda$18$lambda$6$lambda$5(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C90@4054L158:WhatsAppLeadParserDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(386011023, $changed, -1, "com.example.ui.components.WhatsAppLeadParserDialog.<anonymous>.<anonymous>.<anonymous> (WhatsAppLeadParserDialog.kt:90)");
            }
            IconButtonKt.IconButton($onDismiss, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$WhatsAppLeadParserDialogKt.INSTANCE.m122getLambda$1403649556$app(), $composer, 196608, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WhatsAppLeadParserDialog$lambda$18$lambda$11(final Function0 $onDismiss, final Function1 $onParseText, final MutableState $inputText$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C104@4663L1310,100@4494L1479:WhatsAppLeadParserDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(872802728, $changed, -1, "com.example.ui.components.WhatsAppLeadParserDialog.<anonymous>.<anonymous> (WhatsAppLeadParserDialog.kt:100)");
            }
            SurfaceKt.Surface-T9BRK9s(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), (Shape) null, 0L, 0L, Dp.constructor-impl(4), Dp.constructor-impl(8), (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(-1153169299, true, new Function2() { // from class: com.example.ui.components.WhatsAppLeadParserDialogKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj, Object obj2) {
                    return WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$18$lambda$11$lambda$10($onDismiss, $onParseText, $inputText$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12804102, 78);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x025d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit WhatsAppLeadParserDialog$lambda$18$lambda$11$lambda$10(final kotlin.jvm.functions.Function0 r49, final kotlin.jvm.functions.Function1 r50, final androidx.compose.runtime.MutableState r51, androidx.compose.runtime.Composer r52, int r53) {
        /*
            Method dump skipped, instructions count: 611
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$18$lambda$11$lambda$10(kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WhatsAppLeadParserDialog$lambda$18$lambda$11$lambda$10$lambda$9$lambda$8$lambda$7(Function1 $onParseText, Function0 $onDismiss, MutableState $inputText$delegate) {
        if (!StringsKt.isBlank(WhatsAppLeadParserDialog$lambda$1($inputText$delegate))) {
            $onParseText.invoke(WhatsAppLeadParserDialog$lambda$1($inputText$delegate));
            $onDismiss.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0212  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit WhatsAppLeadParserDialog$lambda$18$lambda$17(final androidx.compose.ui.platform.ClipboardManager r60, final androidx.compose.runtime.MutableState r61, androidx.compose.foundation.layout.PaddingValues r62, androidx.compose.runtime.Composer r63, int r64) {
        /*
            Method dump skipped, instructions count: 785
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.WhatsAppLeadParserDialogKt.WhatsAppLeadParserDialog$lambda$18$lambda$17(androidx.compose.ui.platform.ClipboardManager, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WhatsAppLeadParserDialog$lambda$18$lambda$17$lambda$16$lambda$13$lambda$12(ClipboardManager $clipboardManager, MutableState $inputText$delegate) {
        AnnotatedString text = $clipboardManager.getText();
        String clip = text != null ? text.getText() : null;
        String str = clip;
        if (!(str == null || StringsKt.isBlank(str))) {
            $inputText$delegate.setValue(clip);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WhatsAppLeadParserDialog$lambda$18$lambda$17$lambda$16$lambda$15$lambda$14(MutableState $inputText$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $inputText$delegate.setValue(it);
        return Unit.INSTANCE;
    }
}
