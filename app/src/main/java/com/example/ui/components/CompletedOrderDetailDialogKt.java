package com.example.ui.components;

import android.content.Context;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.material3.TopAppBarScrollBehavior;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import com.example.data.model.CustomerJobEntity;
import com.example.util.WhatsAppHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CompletedOrderDetailDialog.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"CompletedOrderDetailDialog", "", "job", "Lcom/example/data/model/CustomerJobEntity;", "onDismiss", "Lkotlin/Function0;", "(Lcom/example/data/model/CustomerJobEntity;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "calculateTaskDuration", "", "startTimeMs", "", "endTimeMs", "(JLjava/lang/Long;)Ljava/lang/String;", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class CompletedOrderDetailDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$43(CustomerJobEntity customerJobEntity, Function0 function0, int i, Composer composer, int i2) {
        CompletedOrderDetailDialog(customerJobEntity, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void CompletedOrderDetailDialog(final CustomerJobEntity job, final Function0<Unit> function0, Composer $composer, final int $changed) {
        String str;
        final CustomerJobEntity customerJobEntity;
        Composer $composer2;
        final Function0<Unit> function02 = function0;
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(function02, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(1084077837);
        ComposerKt.sourceInformation($composer3, "C(CompletedOrderDetailDialog)70@3075L7,83@3680L19297,80@3525L19452:CompletedOrderDetailDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            customerJobEntity = job;
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1084077837, $dirty2, -1, "com.example.ui.components.CompletedOrderDetailDialog (CompletedOrderDetailDialog.kt:69)");
            }
            CompositionLocal localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) consume;
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
            final SimpleDateFormat timeOnlyFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            dateTimeFormat.format(new Date(job.getCreatedAt()));
            Long completedAt = job.getCompletedAt();
            if (completedAt == null || (str = dateTimeFormat.format(new Date(completedAt.longValue()))) == null) {
                str = "Recorded";
            }
            final String completedDateStr = str;
            final String durationText = calculateTaskDuration(job.getCreatedAt(), job.getCompletedAt());
            DialogProperties dialogProperties = new DialogProperties(false, false, (SecureFlagPolicy) null, false, false, 7, (DefaultConstructorMarker) null);
            Function2 function2 = new Function2() { // from class: com.example.ui.components.CompletedOrderDetailDialogKt$$ExternalSyntheticLambda12
                public final Object invoke(Object obj, Object obj2) {
                    return CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42(function0, job, durationText, completedDateStr, context, timeOnlyFormat, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            customerJobEntity = job;
            function02 = function0;
            $composer2 = $composer3;
            AndroidDialog_androidKt.Dialog(function02, dialogProperties, ComposableLambdaKt.rememberComposableLambda(448726294, true, function2, $composer3, 54), $composer2, (($dirty2 >> 3) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CompletedOrderDetailDialogKt$$ExternalSyntheticLambda13
                public final Object invoke(Object obj, Object obj2) {
                    return CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$43(CustomerJobEntity.this, function02, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$42(final Function0 $onDismiss, final CustomerJobEntity $job, final String $durationText, final String $completedDateStr, final Context $context, final SimpleDateFormat $timeOnlyFormat, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C84@3702L15,84@3690L27,87@3804L2193,132@6008L16963,85@3726L19245:CompletedOrderDetailDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(448726294, $changed, -1, "com.example.ui.components.CompletedOrderDetailDialog.<anonymous> (CompletedOrderDetailDialog.kt:84)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1729239653, "CC(remember):CompletedOrderDetailDialog.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CompletedOrderDetailDialogKt$$ExternalSyntheticLambda6
                    public final Object invoke() {
                        return CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$2$lambda$1($onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackHandlerKt.BackHandler(false, (Function0) obj, $composer, 0, 1);
            ScaffoldKt.Scaffold-TvnljyQ(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ComposableLambdaKt.rememberComposableLambda(1179294674, true, new Function2() { // from class: com.example.ui.components.CompletedOrderDetailDialogKt$$ExternalSyntheticLambda7
                public final Object invoke(Object obj2, Object obj3) {
                    return CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$7(CustomerJobEntity.this, $onDismiss, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, (Function2) null, 0, 0L, 0L, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(-1507852633, true, new Function3() { // from class: com.example.ui.components.CompletedOrderDetailDialogKt$$ExternalSyntheticLambda8
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$41(CustomerJobEntity.this, $durationText, $completedDateStr, $context, $timeOnlyFormat, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306422, 508);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$42$lambda$2$lambda$1(Function0 $onDismiss) {
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$42$lambda$7(final CustomerJobEntity $job, final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C89@3861L1675,122@5575L206,128@5902L11,127@5830L135,88@3822L2161:CompletedOrderDetailDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1179294674, $changed, -1, "com.example.ui.components.CompletedOrderDetailDialog.<anonymous>.<anonymous> (CompletedOrderDetailDialog.kt:88)");
            }
            AppBarKt.TopAppBar-GHTll3U(ComposableLambdaKt.rememberComposableLambda(1986081430, true, new Function2() { // from class: com.example.ui.components.CompletedOrderDetailDialogKt$$ExternalSyntheticLambda4
                public final Object invoke(Object obj, Object obj2) {
                    return CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$7$lambda$5(CustomerJobEntity.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-455376360, true, new Function2() { // from class: com.example.ui.components.CompletedOrderDetailDialogKt$$ExternalSyntheticLambda5
                public final Object invoke(Object obj, Object obj2) {
                    return CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$7$lambda$6($onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function3) null, 0.0f, (WindowInsets) null, TopAppBarDefaults.INSTANCE.topAppBarColors-zjMxDiM(ColorSchemeKt.surfaceColorAtElevation-3ABfNKs(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable), Dp.constructor-impl(3)), 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 15, 30), (TopAppBarScrollBehavior) null, $composer, 390, 186);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x020b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CompletedOrderDetailDialog$lambda$42$lambda$7$lambda$5(com.example.data.model.CustomerJobEntity r73, androidx.compose.runtime.Composer r74, int r75) {
        /*
            Method dump skipped, instructions count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$7$lambda$5(com.example.data.model.CustomerJobEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$42$lambda$7$lambda$6(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C123@5601L158:CompletedOrderDetailDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-455376360, $changed, -1, "com.example.ui.components.CompletedOrderDetailDialog.<anonymous>.<anonymous>.<anonymous> (CompletedOrderDetailDialog.kt:123)");
            }
            IconButtonKt.IconButton($onDismiss, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$CompletedOrderDetailDialogKt.INSTANCE.m39getLambda$1754410763$app(), $composer, 196608, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x04cd  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x04da  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x019d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CompletedOrderDetailDialog$lambda$42$lambda$41(final com.example.data.model.CustomerJobEntity r54, final java.lang.String r55, final java.lang.String r56, final android.content.Context r57, final java.text.SimpleDateFormat r58, androidx.compose.foundation.layout.PaddingValues r59, androidx.compose.runtime.Composer r60, int r61) {
        /*
            Method dump skipped, instructions count: 1334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$41(com.example.data.model.CustomerJobEntity, java.lang.String, java.lang.String, android.content.Context, java.text.SimpleDateFormat, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0161  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$10(boolean r76, int r77, int r78, androidx.compose.foundation.layout.ColumnScope r79, androidx.compose.runtime.Composer r80, int r81) {
        /*
            Method dump skipped, instructions count: 979
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$10(boolean, int, int, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0356, code lost:
    
        if (r2 == null) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x022b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$14(java.lang.String r74, java.text.SimpleDateFormat r75, com.example.data.model.CustomerJobEntity r76, androidx.compose.foundation.layout.ColumnScope r77, androidx.compose.runtime.Composer r78, int r79) {
        /*
            Method dump skipped, instructions count: 1015
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$14(java.lang.String, java.text.SimpleDateFormat, com.example.data.model.CustomerJobEntity, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0334 A[EDGE_INSN: B:47:0x0334->B:48:0x0334 BREAK  A[LOOP:0: B:38:0x02e6->B:44:0x0307], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0236  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$18(final com.example.data.model.CustomerJobEntity r82, androidx.compose.foundation.layout.ColumnScope r83, androidx.compose.runtime.Composer r84, int r85) {
        /*
            Method dump skipped, instructions count: 1094
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$18(com.example.data.model.CustomerJobEntity, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$18$lambda$17$lambda$16(CustomerJobEntity $job, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C286@13716L10,287@13797L11,283@13486L438:CompletedOrderDetailDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(944787251, $changed, -1, "com.example.ui.components.CompletedOrderDetailDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CompletedOrderDetailDialog.kt:283)");
            }
            String str = "“" + $job.getReviewFeedback() + "”";
            int i = FontStyle.Companion.getItalic-_-LCdwA();
            TextKt.Text--4IGK_g(str, PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface-0d7_KjU(), 0L, FontStyle.box-impl(i), (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 48, 0, 65512);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0575  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x05de  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x06a9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x05ed  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x058b  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0399 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0238  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$27(com.example.data.model.CustomerJobEntity r105, final android.content.Context r106, androidx.compose.foundation.layout.ColumnScope r107, androidx.compose.runtime.Composer r108, int r109) {
        /*
            Method dump skipped, instructions count: 1711
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$27(com.example.data.model.CustomerJobEntity, android.content.Context, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$27$lambda$26$lambda$25$lambda$24$lambda$23$lambda$22$lambda$21(Context $context, String $phone) {
        WhatsAppHelper.INSTANCE.openDialer($context, $phone);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0441 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x03bc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x02d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$34(final com.example.data.model.CustomerJobEntity r74, final android.content.Context r75, androidx.compose.foundation.layout.ColumnScope r76, androidx.compose.runtime.Composer r77, int r78) {
        /*
            Method dump skipped, instructions count: 1203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$34(com.example.data.model.CustomerJobEntity, android.content.Context, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$34$lambda$33$lambda$32$lambda$29$lambda$28(Context $context, CustomerJobEntity $job) {
        WhatsAppHelper.INSTANCE.openDialer($context, $job.getCustomerPhone());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$34$lambda$33$lambda$32$lambda$31$lambda$30(Context $context, CustomerJobEntity $job) {
        WhatsAppHelper.INSTANCE.openGoogleMaps($context, $job.getLatitude(), $job.getLongitude(), $job.getCustomerName());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$36(CustomerJobEntity $job, ColumnScope $this$Card, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C421@20386L1218:CompletedOrderDetailDialog.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(753776650, $changed, -1, "com.example.ui.components.CompletedOrderDetailDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CompletedOrderDetailDialog.kt:421)");
            }
            Modifier modifier = PaddingKt.padding-3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(14));
            Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(4));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(vertical, Alignment.Companion.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composer = Updater.constructor-impl($composer);
            Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -2003115299, "C430@20813L10,427@20648L212,435@21072L10,436@21145L11,432@20885L305:CompletedOrderDetailDialog.kt#qonjpd");
            TextKt.Text--4IGK_g("Work Details", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), $composer, 196614, 0, 65502);
            TextKt.Text--4IGK_g("🛠 Service: " + $job.getServiceType(), (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary-0d7_KjU(), 0L, (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 196608, 0, 65498);
            if (StringsKt.isBlank($job.getIssueDescription())) {
                $composer.startReplaceGroup(-2023628062);
            } else {
                $composer.startReplaceGroup(-2002532934);
                ComposerKt.sourceInformation($composer, "441@21422L10,442@21498L11,439@21284L272");
                TextKt.Text--4IGK_g("📝 Problem: " + $job.getIssueDescription(), (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 65530);
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x003e, code lost:
    
        if (r7 == null) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$39$lambda$38(com.example.data.model.CustomerJobEntity r11, java.lang.String r12, java.lang.String r13, android.content.Context r14) {
        /*
            long r0 = r11.getId()
            java.lang.String r2 = r11.getCustomerName()
            java.lang.String r3 = r11.getCustomerPhone()
            java.lang.String r4 = r11.getAddress()
            java.lang.String r5 = r11.getServiceType()
            java.lang.String r6 = r11.getAssignedExpertName()
            if (r6 != 0) goto L1c
            java.lang.String r6 = "Hurifix Expert"
        L1c:
            java.lang.Float r7 = r11.getRatingGiven()
            if (r7 == 0) goto L40
            java.lang.Number r7 = (java.lang.Number) r7
            float r7 = r7.floatValue()
            r8 = 0
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.StringBuilder r9 = r9.append(r7)
            java.lang.String r10 = "/5 Stars"
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r7 = r9.toString()
            if (r7 != 0) goto L42
        L40:
            java.lang.String r7 = "5/5"
        L42:
            java.lang.String r8 = r11.getReviewFeedback()
            if (r8 != 0) goto L4a
            java.lang.String r8 = "Satisfactory"
        L4a:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "\n✅ *HURIFIX WORK COMPLETION RECEIPT* ✅\n━━━━━━━━━━━━━━━━━━━━\n*Order ID:* #"
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r0 = r9.append(r0)
            java.lang.String r1 = "\n*Customer:* "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r1 = " ("
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r1 = ")\n*Address:* "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r1 = "\n*Service:* "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r1 = "\n*Expert Assigned:* "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r1 = "\n*Task Duration:* "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r12)
            java.lang.String r1 = "\n*Rating:* "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String r1 = "\n*Feedback:* "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r8)
            java.lang.String r1 = "\n*Completed On:* "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r13)
            java.lang.String r1 = "\n━━━━━━━━━━━━━━━━━━━━\nThank you for choosing Hurifix!\n_Many Problems | One Solution_\n                        "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = kotlin.text.StringsKt.trimIndent(r0)
            com.example.util.WhatsAppHelper r1 = com.example.util.WhatsAppHelper.INSTANCE
            java.lang.String r2 = "Work Receipt"
            r1.copyToClipboard(r14, r2, r0)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CompletedOrderDetailDialogKt.CompletedOrderDetailDialog$lambda$42$lambda$41$lambda$40$lambda$39$lambda$38(com.example.data.model.CustomerJobEntity, java.lang.String, java.lang.String, android.content.Context):kotlin.Unit");
    }

    public static final String calculateTaskDuration(long startTimeMs, Long endTimeMs) {
        if (endTimeMs == null || endTimeMs.longValue() <= startTimeMs) {
            return "45 minutes";
        }
        long diffMs = endTimeMs.longValue() - startTimeMs;
        long totalMinutes = diffMs / 60000;
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        long days = hours / 24;
        if (days > 0) {
            return days + " days, " + (hours % 24) + " hrs";
        }
        if (hours > 0 && minutes > 0) {
            return hours + " hr " + minutes + " mins";
        }
        if (hours > 0) {
            return hours + " hr" + (hours > 1 ? "s" : "");
        }
        return minutes > 0 ? minutes + " mins" : "Under 15 mins";
    }
}
