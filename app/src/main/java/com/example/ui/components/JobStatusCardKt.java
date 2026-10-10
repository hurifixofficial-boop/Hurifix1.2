package com.example.ui.components;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.ExposedDropdownMenuBoxScope;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.MenuItemColors;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.data.model.CustomerJobEntity;
import com.example.data.model.JobStatus;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JobStatusCard.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\u001ay\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010\u000f¨\u0006\u0010²\u0006\n\u0010\u0011\u001a\u00020\u0012X\u008a\u008e\u0002"}, d2 = {"JobStatusCard", "", "job", "Lcom/example/data/model/CustomerJobEntity;", "onStatusChange", "Lkotlin/Function1;", "Lcom/example/data/model/JobStatus;", "onCallCustomer", "Lkotlin/Function0;", "onCallExpert", "onReDispatch", "onDelete", "onViewCustomerMap", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/example/data/model/CustomerJobEntity;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app", "isStatusExpanded", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class JobStatusCardKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit JobStatusCard$lambda$31(CustomerJobEntity customerJobEntity, Function1 function1, Function0 function0, Function0 function02, Function0 function03, Function0 function04, Function0 function05, Modifier modifier, int i, int i2, Composer composer, int i3) {
        JobStatusCard(customerJobEntity, function1, function0, function02, function03, function04, function05, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static final void JobStatusCard(final CustomerJobEntity job, final Function1<? super JobStatus, Unit> function1, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03, final Function0<Unit> function04, final Function0<Unit> function05, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Object obj;
        long Color;
        long statusTextColor;
        Composer $composer2;
        final Modifier modifier3;
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(function1, "onStatusChange");
        Intrinsics.checkNotNullParameter(function0, "onCallCustomer");
        Intrinsics.checkNotNullParameter(function02, "onCallExpert");
        Intrinsics.checkNotNullParameter(function03, "onReDispatch");
        Intrinsics.checkNotNullParameter(function04, "onDelete");
        Intrinsics.checkNotNullParameter(function05, "onViewCustomerMap");
        Composer $composer3 = $composer.startRestartGroup(-1464540217);
        ComposerKt.sourceInformation($composer3, "C(JobStatusCard)P(!1,6,2,3,5,4,7)62@2595L34,85@3456L11,85@3414L62,86@3511L38,87@3556L11220,80@3235L11541:JobStatusCard.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function03) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function04) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function05) ? 1048576 : 524288;
        }
        int i2 = i & 128;
        if (i2 != 0) {
            $dirty |= 12582912;
            modifier2 = modifier;
        } else if ((12582912 & $changed) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 8388608 : 4194304;
        } else {
            modifier2 = modifier;
        }
        if ((4793491 & $dirty) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i2 != 0 ? (Modifier) Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1464540217, $dirty, -1, "com.example.ui.components.JobStatusCard (JobStatusCard.kt:61)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1326136073, "CC(remember):JobStatusCard.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            final MutableState isStatusExpanded$delegate = (MutableState) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String status = job.getStatus();
            switch (status.hashCode()) {
                case -1031784143:
                    if (status.equals("CANCELLED")) {
                        Color = ColorKt.Color(4294894306L);
                        break;
                    }
                    Color = ColorKt.Color(4294047225L);
                    break;
                case 35394935:
                    if (status.equals("PENDING")) {
                        Color = ColorKt.Color(4294898631L);
                        break;
                    }
                    Color = ColorKt.Color(4294047225L);
                    break;
                case 907287315:
                    if (status.equals("PROCESSING")) {
                        Color = ColorKt.Color(4292602622L);
                        break;
                    }
                    Color = ColorKt.Color(4294047225L);
                    break;
                case 1383663147:
                    if (status.equals("COMPLETED")) {
                        Color = ColorKt.Color(4292672743L);
                        break;
                    }
                    Color = ColorKt.Color(4294047225L);
                    break;
                default:
                    Color = ColorKt.Color(4294047225L);
                    break;
            }
            final long statusColor = Color;
            String status2 = job.getStatus();
            switch (status2.hashCode()) {
                case -1031784143:
                    if (status2.equals("CANCELLED")) {
                        statusTextColor = ColorKt.Color(4288224027L);
                        break;
                    }
                    statusTextColor = ColorKt.Color(4282865001L);
                    break;
                case 35394935:
                    if (status2.equals("PENDING")) {
                        statusTextColor = ColorKt.Color(4287774734L);
                        break;
                    }
                    statusTextColor = ColorKt.Color(4282865001L);
                    break;
                case 907287315:
                    if (status2.equals("PROCESSING")) {
                        statusTextColor = ColorKt.Color(4280172719L);
                        break;
                    }
                    statusTextColor = ColorKt.Color(4282865001L);
                    break;
                case 1383663147:
                    if (status2.equals("COMPLETED")) {
                        statusTextColor = ColorKt.Color(4279657780L);
                        break;
                    }
                    statusTextColor = ColorKt.Color(4282865001L);
                    break;
                default:
                    statusTextColor = ColorKt.Color(4282865001L);
                    break;
            }
            final long statusTextColor2 = statusTextColor;
            Modifier modifier5 = modifier4;
            CardKt.Card(TestTagKt.testTag(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, (Object) null), "job_card_" + job.getId()), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(16)), CardDefaults.INSTANCE.cardColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface-0d7_KjU(), 0L, 0L, 0L, $composer3, CardDefaults.$stable << 12, 14), CardDefaults.INSTANCE.cardElevation-aqJV_2Y(Dp.constructor-impl(1), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, (CardDefaults.$stable << 18) | 6, 62), (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(-1059384007, true, new Function3() { // from class: com.example.ui.components.JobStatusCardKt$$ExternalSyntheticLambda6
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return JobStatusCardKt.JobStatusCard$lambda$30(CustomerJobEntity.this, isStatusExpanded$delegate, statusColor, statusTextColor2, function1, function05, function02, function0, function03, function04, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), $composer3, 196608, 16);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.JobStatusCardKt$$ExternalSyntheticLambda7
                public final Object invoke(Object obj2, Object obj3) {
                    return JobStatusCardKt.JobStatusCard$lambda$31(CustomerJobEntity.this, function1, function0, function02, function03, function04, function05, modifier3, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final boolean JobStatusCard$lambda$1(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void JobStatusCard$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0acf  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0c1d  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0ae5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0a9e  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x091b  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0776 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x072d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x054f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0426 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x04f4  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0539  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x071b  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0727  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0760  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x08b0  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x092e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0936  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x095e  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0a8c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0a98  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit JobStatusCard$lambda$30(final com.example.data.model.CustomerJobEntity r125, androidx.compose.runtime.MutableState r126, final long r127, final long r129, final kotlin.jvm.functions.Function1 r131, kotlin.jvm.functions.Function0 r132, final kotlin.jvm.functions.Function0 r133, kotlin.jvm.functions.Function0 r134, kotlin.jvm.functions.Function0 r135, kotlin.jvm.functions.Function0 r136, androidx.compose.foundation.layout.ColumnScope r137, androidx.compose.runtime.Composer r138, int r139) {
        /*
            Method dump skipped, instructions count: 3107
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.JobStatusCardKt.JobStatusCard$lambda$30(com.example.data.model.CustomerJobEntity, androidx.compose.runtime.MutableState, long, long, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$3(CustomerJobEntity $job, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C106@4298L11,104@4198L337:JobStatusCard.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1104403666, $changed, -1, "com.example.ui.components.JobStatusCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (JobStatusCard.kt:104)");
            }
            TextKt.Text--4IGK_g($job.getServiceType(), PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(8), Dp.constructor-impl(4)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryContainer-0d7_KjU(), TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$5$lambda$4(MutableState $isStatusExpanded$delegate, boolean it) {
        JobStatusCard$lambda$2($isStatusExpanded$delegate, !JobStatusCard$lambda$1($isStatusExpanded$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15(long $statusColor, final CustomerJobEntity $job, final long $statusTextColor, final MutableState $isStatusExpanded$delegate, final Function1 $onStatusChange, ExposedDropdownMenuBoxScope $this$ExposedDropdownMenuBox, Composer $composer, int $changed) {
        Object obj;
        Intrinsics.checkNotNullParameter($this$ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
        ComposerKt.sourceInformation($composer, "C122@4991L670,118@4799L862,139@5800L28,140@5851L746,137@5683L914:JobStatusCard.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer.changed($this$ExposedDropdownMenuBox) : $composer.changedInstance($this$ExposedDropdownMenuBox) ? 4 : 2;
        }
        if (($dirty & 19) == 18 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1610460841, $dirty, -1, "com.example.ui.components.JobStatusCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (JobStatusCard.kt:118)");
            }
            int $dirty2 = $dirty;
            SurfaceKt.Surface-T9BRK9s($this$ExposedDropdownMenuBox.menuAnchor(Modifier.Companion), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12)), $statusColor, 0L, 0.0f, 0.0f, (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(1156142532, true, new Function2() { // from class: com.example.ui.components.JobStatusCardKt$$ExternalSyntheticLambda0
                public final Object invoke(Object obj2, Object obj3) {
                    return JobStatusCardKt.JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$7(CustomerJobEntity.this, $statusTextColor, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 12582912, 120);
            boolean JobStatusCard$lambda$1 = JobStatusCard$lambda$1($isStatusExpanded$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 1464674405, "CC(remember):JobStatusCard.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.JobStatusCardKt$$ExternalSyntheticLambda4
                    public final Object invoke() {
                        return JobStatusCardKt.JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$9$lambda$8($isStatusExpanded$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            $this$ExposedDropdownMenuBox.ExposedDropdownMenu-vNxi1II(JobStatusCard$lambda$1, (Function0) obj, (Modifier) null, (ScrollState) null, false, (Shape) null, 0L, 0.0f, 0.0f, (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(500838759, true, new Function3() { // from class: com.example.ui.components.JobStatusCardKt$$ExternalSyntheticLambda5
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return JobStatusCardKt.JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$14($onStatusChange, $job, $isStatusExpanded$delegate, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 48, (ExposedDropdownMenuBoxScope.$stable << 3) | 6 | (($dirty2 << 3) & 112), 1020);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$7(com.example.data.model.CustomerJobEntity r49, long r50, androidx.compose.runtime.Composer r52, int r53) {
        /*
            Method dump skipped, instructions count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.JobStatusCardKt.JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$7(com.example.data.model.CustomerJobEntity, long, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$9$lambda$8(MutableState $isStatusExpanded$delegate) {
        JobStatusCard$lambda$2($isStatusExpanded$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$14(final Function1 $onStatusChange, final CustomerJobEntity $job, final MutableState $isStatusExpanded$delegate, ColumnScope $this$ExposedDropdownMenu, Composer $composer, int $changed) {
        Object obj;
        Composer composer = $composer;
        Intrinsics.checkNotNullParameter($this$ExposedDropdownMenu, "$this$ExposedDropdownMenu");
        ComposerKt.sourceInformation(composer, "C*143@6000L320,149@6364L155,142@5943L606:JobStatusCard.kt#qonjpd");
        if (($changed & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(500838759, $changed, -1, "com.example.ui.components.JobStatusCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (JobStatusCard.kt:141)");
            }
            for (final JobStatus jobStatus : JobStatus.getEntries()) {
                Function2 rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1235973969, true, new Function2() { // from class: com.example.ui.components.JobStatusCardKt$$ExternalSyntheticLambda9
                    public final Object invoke(Object obj2, Object obj3) {
                        return JobStatusCardKt.JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$14$lambda$13$lambda$10(JobStatus.this, $job, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer, 54);
                ComposerKt.sourceInformationMarkerStart(composer, 838954842, "CC(remember):JobStatusCard.kt#9igjgp");
                boolean changed = composer.changed($onStatusChange) | composer.changed(jobStatus.ordinal());
                Object rememberedValue = $composer.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                    obj = new Function0() { // from class: com.example.ui.components.JobStatusCardKt$$ExternalSyntheticLambda10
                        public final Object invoke() {
                            return JobStatusCardKt.JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$14$lambda$13$lambda$12$lambda$11($onStatusChange, jobStatus, $isStatusExpanded$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                AndroidMenu_androidKt.DropdownMenuItem(rememberComposableLambda, (Function0) obj, (Modifier) null, (Function2) null, (Function2) null, false, (MenuItemColors) null, (PaddingValues) null, (MutableInteractionSource) null, composer, 6, 508);
                composer = $composer;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$14$lambda$13$lambda$10(JobStatus $status, CustomerJobEntity $job, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C144@6038L248:JobStatusCard.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1235973969, $changed, -1, "com.example.ui.components.JobStatusCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (JobStatusCard.kt:144)");
            }
            TextKt.Text--4IGK_g($status.getLabel() + " (" + $status.getHindiLabel() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, Intrinsics.areEqual($job.getStatus(), $status.name()) ? FontWeight.Companion.getBold() : FontWeight.Companion.getNormal(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit JobStatusCard$lambda$30$lambda$29$lambda$16$lambda$15$lambda$14$lambda$13$lambda$12$lambda$11(Function1 $onStatusChange, JobStatus $status, MutableState $isStatusExpanded$delegate) {
        $onStatusChange.invoke($status);
        JobStatusCard$lambda$2($isStatusExpanded$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit JobStatusCard$lambda$30$lambda$29$lambda$22(com.example.data.model.CustomerJobEntity r72, kotlin.jvm.functions.Function0 r73, androidx.compose.runtime.Composer r74, int r75) {
        /*
            Method dump skipped, instructions count: 972
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.JobStatusCardKt.JobStatusCard$lambda$30$lambda$29$lambda$22(com.example.data.model.CustomerJobEntity, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x021a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit JobStatusCard$lambda$30$lambda$29$lambda$27(java.lang.String r50, java.lang.String r51, final java.lang.String r52, androidx.compose.runtime.Composer r53, int r54) {
        /*
            Method dump skipped, instructions count: 584
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.JobStatusCardKt.JobStatusCard$lambda$30$lambda$29$lambda$27(java.lang.String, java.lang.String, java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit JobStatusCard$lambda$30$lambda$29$lambda$27$lambda$26$lambda$25(String $managerText, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C284@12810L10,285@12887L11,282@12692L414:JobStatusCard.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(489209776, $changed, -1, "com.example.ui.components.JobStatusCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (JobStatusCard.kt:282)");
            }
            TextKt.Text--4IGK_g("💼 " + $managerText, PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(6), Dp.constructor-impl(2)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryContainer-0d7_KjU(), 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 196656, 0, 65496);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
