package com.example.ui.components;

import android.content.Context;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.data.model.CustomerJobEntity;
import com.example.data.model.ExpertEntity;
import com.example.util.WhatsAppHelper;
import java.text.SimpleDateFormat;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: ExpertWorkHistoryDialog.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007¢\u0006\u0002\u0010\t\u001aA\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0003¢\u0006\u0004\b\u0014\u0010\u0015\u001a9\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003¢\u0006\u0002\u0010\u001c¨\u0006\u001d²\u0006\n\u0010\u001e\u001a\u00020\fX\u008a\u008e\u0002²\u0006\n\u0010\u001f\u001a\u00020\fX\u008a\u008e\u0002²\u0006\n\u0010 \u001a\u00020!X\u008a\u0084\u0002"}, d2 = {"ExpertWorkHistoryDialog", "", "expert", "Lcom/example/data/model/ExpertEntity;", "allJobs", "", "Lcom/example/data/model/CustomerJobEntity;", "onDismiss", "Lkotlin/Function0;", "(Lcom/example/data/model/ExpertEntity;Ljava/util/List;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "StatCard", "title", "", "value", "subtitle", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "modifier", "Landroidx/compose/ui/Modifier;", "StatCard-jB83MbM", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "WorkHistoryJobCard", "job", "dateFormat", "Ljava/text/SimpleDateFormat;", "onCallCustomer", "onWhatsAppCustomer", "(Lcom/example/data/model/CustomerJobEntity;Ljava/text/SimpleDateFormat;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app", "searchQuery", "selectedStatusFilter", "showScrollToTop", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class ExpertWorkHistoryDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertWorkHistoryDialog$lambda$51(ExpertEntity expertEntity, List list, Function0 function0, int i, Composer composer, int i2) {
        ExpertWorkHistoryDialog(expertEntity, list, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit StatCard_jB83MbM$lambda$54(String str, String str2, String str3, long j, long j2, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m127StatCardjB83MbM(str, str2, str3, j, j2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WorkHistoryJobCard$lambda$69(CustomerJobEntity customerJobEntity, SimpleDateFormat simpleDateFormat, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        WorkHistoryJobCard(customerJobEntity, simpleDateFormat, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:78:0x0382. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02d3 A[LOOP:1: B:122:0x02cd->B:124:0x02d3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02ab A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x045d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ExpertWorkHistoryDialog(final com.example.data.model.ExpertEntity r35, final java.util.List<com.example.data.model.CustomerJobEntity> r36, final kotlin.jvm.functions.Function0<kotlin.Unit> r37, androidx.compose.runtime.Composer r38, final int r39) {
        /*
            Method dump skipped, instructions count: 1324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertWorkHistoryDialogKt.ExpertWorkHistoryDialog(com.example.data.model.ExpertEntity, java.util.List, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    private static final String ExpertWorkHistoryDialog$lambda$1(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String ExpertWorkHistoryDialog$lambda$4(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertWorkHistoryDialog$lambda$50(final List $filteredJobs, final Function0 $onDismiss, final ExpertEntity $expert, final int $totalUniqueCustomers, final int $totalCompleted, final int $totalActive, final MutableState $searchQuery$delegate, final List $expertJobs, final MutableState $selectedStatusFilter$delegate, final SimpleDateFormat $dateFormat, final Context $context, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C128@5446L11,129@5523L11,130@5561L10637,123@5248L10950:ExpertWorkHistoryDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-280672503, $changed, -1, "com.example.ui.components.ExpertWorkHistoryDialog.<anonymous> (ExpertWorkHistoryDialog.kt:123)");
            }
            SurfaceKt.Surface-T9BRK9s(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth(Modifier.Companion, 0.96f), 0.92f), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(20)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface-0d7_KjU(), 0L, 0.0f, 0.0f, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl((float) 1.5d), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant-0d7_KjU()), ComposableLambdaKt.rememberComposableLambda(1938941006, true, new Function2() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$$ExternalSyntheticLambda12
                public final Object invoke(Object obj, Object obj2) {
                    return ExpertWorkHistoryDialogKt.ExpertWorkHistoryDialog$lambda$50$lambda$49($filteredJobs, $onDismiss, $expert, $totalUniqueCustomers, $totalCompleted, $totalActive, $searchQuery$delegate, $expertJobs, $selectedStatusFilter$delegate, $dateFormat, $context, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12582918, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0c14  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0c19  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0c86  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0d60  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0d6c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0da5  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0eae  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0ff8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x11b2  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x11be  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x1290  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x162e  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x12b2  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x11c4  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x1331  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x15b3  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0dbb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0d72  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0c94  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0c3f  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0c16  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0bb7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0b6e  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x09e1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0998  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0786 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x073d  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0625 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x05dc  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x04de A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03ac A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0483  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x05ca  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x05d6  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x072b  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0737  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0986  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0992  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x09cb  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0b5c  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0b68  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0ba1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertWorkHistoryDialog$lambda$50$lambda$49(final java.util.List r160, kotlin.jvm.functions.Function0 r161, com.example.data.model.ExpertEntity r162, int r163, int r164, int r165, final androidx.compose.runtime.MutableState r166, java.util.List r167, final androidx.compose.runtime.MutableState r168, final java.text.SimpleDateFormat r169, final android.content.Context r170, androidx.compose.runtime.Composer r171, int r172) {
        /*
            Method dump skipped, instructions count: 5684
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertWorkHistoryDialogKt.ExpertWorkHistoryDialog$lambda$50$lambda$49(java.util.List, kotlin.jvm.functions.Function0, com.example.data.model.ExpertEntity, int, int, int, androidx.compose.runtime.MutableState, java.util.List, androidx.compose.runtime.MutableState, java.text.SimpleDateFormat, android.content.Context, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$33$lambda$27$lambda$26(MutableState $searchQuery$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $searchQuery$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$33$lambda$25(final MutableState $searchQuery$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C235@10527L20,235@10506L170:ExpertWorkHistoryDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(260943799, $changed, -1, "com.example.ui.components.ExpertWorkHistoryDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ExpertWorkHistoryDialog.kt:235)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1658889451, "CC(remember):ExpertWorkHistoryDialog.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$$ExternalSyntheticLambda15
                    public final Object invoke() {
                        return ExpertWorkHistoryDialogKt.ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$33$lambda$25$lambda$24$lambda$23($searchQuery$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) obj, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$ExpertWorkHistoryDialogKt.INSTANCE.getLambda$545357588$app(), $composer, 196614, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$33$lambda$25$lambda$24$lambda$23(MutableState $searchQuery$delegate) {
        $searchQuery$delegate.setValue("");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$33$lambda$32$lambda$31$lambda$29$lambda$28(String $statusKey, MutableState $selectedStatusFilter$delegate) {
        $selectedStatusFilter$delegate.setValue($statusKey);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$33$lambda$32$lambda$31$lambda$30(String $label, boolean $isSelected, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C260@11833L100:ExpertWorkHistoryDialog.kt#qonjpd");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-430314396, $changed, -1, "com.example.ui.components.ExpertWorkHistoryDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ExpertWorkHistoryDialog.kt:260)");
            }
            long sp = TextUnitKt.getSp(11.5d);
            FontWeight.Companion companion = FontWeight.Companion;
            TextKt.Text--4IGK_g($label, (Modifier) null, 0L, sp, (FontStyle) null, $isSelected ? companion.getBold() : companion.getNormal(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    private static final boolean ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$38(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$37$lambda$36(LazyListState $listState) {
        return $listState.getFirstVisibleItemIndex() > 0 || $listState.getFirstVisibleItemScrollOffset() > 20;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$47$lambda$44$lambda$43(final List $filteredJobs, final SimpleDateFormat $dateFormat, final Context $context, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        final Function1 function1 = new Function1() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$$ExternalSyntheticLambda18
            public final Object invoke(Object obj) {
                return ExpertWorkHistoryDialogKt.ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$47$lambda$44$lambda$43$lambda$39((CustomerJobEntity) obj);
            }
        };
        final Function1 function12 = new Function1() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$47$lambda$44$lambda$43$$inlined$items$default$1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return m128invoke((CustomerJobEntity) p1);
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final Void m128invoke(CustomerJobEntity customerJobEntity) {
                return null;
            }
        };
        $this$LazyColumn.items($filteredJobs.size(), new Function1<Integer, Object>() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$47$lambda$44$lambda$43$$inlined$items$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($filteredJobs.get(index));
            }
        }, new Function1<Integer, Object>() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$47$lambda$44$lambda$43$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            public final Object invoke(int index) {
                return function12.invoke($filteredJobs.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$47$lambda$44$lambda$43$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                Object obj;
                Object obj2;
                ComposerKt.sourceInformation($composer, "C152@7074L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if (($dirty & 147) == 146 && $composer.getSkipping()) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-632812321, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                }
                int i = $dirty & 14;
                final CustomerJobEntity customerJobEntity = (CustomerJobEntity) $filteredJobs.get(it);
                $composer.startReplaceGroup(1259065356);
                ComposerKt.sourceInformation($composer, "C*321@14891L133,324@15083L450,318@14710L857:ExpertWorkHistoryDialog.kt#qonjpd");
                SimpleDateFormat simpleDateFormat = $dateFormat;
                ComposerKt.sourceInformationMarkerStart($composer, -375021917, "CC(remember):ExpertWorkHistoryDialog.kt#9igjgp");
                boolean changedInstance = $composer.changedInstance($context) | ((((i & 112) ^ 48) > 32 && $composer.changed(customerJobEntity)) || (i & 48) == 32);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                    final Context context = $context;
                    obj = (Function0) new Function0<Unit>() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$ExpertWorkHistoryDialog$1$1$1$5$1$1$2$1$1
                        public /* bridge */ /* synthetic */ Object invoke() {
                            m129invoke();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: collision with other method in class */
                        public final void m129invoke() {
                            WhatsAppHelper.INSTANCE.openDialer(context, customerJobEntity.getCustomerPhone());
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = rememberedValue;
                }
                Function0 function0 = (Function0) obj;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, -375015456, "CC(remember):ExpertWorkHistoryDialog.kt#9igjgp");
                boolean changedInstance2 = $composer.changedInstance($context) | ((((i & 112) ^ 48) > 32 && $composer.changed(customerJobEntity)) || (i & 48) == 32);
                Object rememberedValue2 = $composer.rememberedValue();
                if (changedInstance2 || rememberedValue2 == Composer.Companion.getEmpty()) {
                    final Context context2 = $context;
                    obj2 = (Function0) new Function0<Unit>() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$ExpertWorkHistoryDialog$1$1$1$5$1$1$2$2$1
                        public /* bridge */ /* synthetic */ Object invoke() {
                            m130invoke();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: collision with other method in class */
                        public final void m130invoke() {
                            WhatsAppHelper.INSTANCE.sendWhatsAppDirectMessage(context2, customerJobEntity.getCustomerPhone(), "Hello " + customerJobEntity.getCustomerName() + ", this is regarding your " + customerJobEntity.getServiceType() + " service with Hurifix.");
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                } else {
                    obj2 = rememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ExpertWorkHistoryDialogKt.WorkHistoryJobCard(customerJobEntity, simpleDateFormat, function0, (Function0) obj2, $composer, (i >> 3) & 14);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Object ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$47$lambda$44$lambda$43$lambda$39(CustomerJobEntity it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertWorkHistoryDialog$lambda$50$lambda$49$lambda$48$lambda$47$lambda$46$lambda$45(CoroutineScope $coroutineScope, LazyListState $listState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ExpertWorkHistoryDialogKt$ExpertWorkHistoryDialog$1$1$1$5$2$1$1($listState, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* renamed from: StatCard-jB83MbM, reason: not valid java name */
    private static final void m127StatCardjB83MbM(final String title, final String value, final String subtitle, final long containerColor, final long contentColor, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final String str;
        final String str2;
        final String str3;
        long j;
        long j2;
        final Modifier modifier2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1983289990);
        ComposerKt.sourceInformation($composer3, "C(StatCard)P(4,5,3,0:c#ui.graphics.Color,1:c#ui.graphics.Color)367@16585L457,362@16396L646:ExpertWorkHistoryDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            str = title;
            $dirty |= $composer3.changed(str) ? 4 : 2;
        } else {
            str = title;
        }
        if (($changed & 48) == 0) {
            str2 = value;
            $dirty |= $composer3.changed(str2) ? 32 : 16;
        } else {
            str2 = value;
        }
        if (($changed & 384) == 0) {
            str3 = subtitle;
            $dirty |= $composer3.changed(str3) ? 256 : 128;
        } else {
            str3 = subtitle;
        }
        if (($changed & 3072) == 0) {
            j = containerColor;
            $dirty |= $composer3.changed(j) ? 2048 : 1024;
        } else {
            j = containerColor;
        }
        if (($changed & 24576) == 0) {
            j2 = contentColor;
            $dirty |= $composer3.changed(j2) ? 16384 : 8192;
        } else {
            j2 = contentColor;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty |= 196608;
            modifier2 = modifier;
        } else if ((196608 & $changed) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        if ((74899 & $dirty) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (i2 != 0) {
                modifier2 = (Modifier) Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1983289990, $dirty, -1, "com.example.ui.components.StatCard (ExpertWorkHistoryDialog.kt:361)");
            }
            $composer2 = $composer3;
            Modifier modifier3 = modifier2;
            SurfaceKt.Surface-T9BRK9s(modifier3, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12)), j, 0L, 0.0f, 0.0f, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1), Color.copy-wmQWz5c$default(j2, 0.25f, 0.0f, 0.0f, 0.0f, 14, (Object) null)), ComposableLambdaKt.rememberComposableLambda(-1112852575, true, new Function2() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$$ExternalSyntheticLambda13
                public final Object invoke(Object obj, Object obj2) {
                    return ExpertWorkHistoryDialogKt.StatCard_jB83MbM$lambda$53(str2, contentColor, str, str3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty >> 15) & 14) | 12582912 | (($dirty >> 3) & 896), 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$$ExternalSyntheticLambda14
                public final Object invoke(Object obj, Object obj2) {
                    return ExpertWorkHistoryDialogKt.StatCard_jB83MbM$lambda$54(title, value, subtitle, containerColor, contentColor, modifier2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit StatCard_jB83MbM$lambda$53(java.lang.String r49, long r50, java.lang.String r52, java.lang.String r53, androidx.compose.runtime.Composer r54, int r55) {
        /*
            Method dump skipped, instructions count: 461
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertWorkHistoryDialogKt.StatCard_jB83MbM$lambda$53(java.lang.String, long, java.lang.String, java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WorkHistoryJobCard(final CustomerJobEntity job, final SimpleDateFormat dateFormat, final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1629396853);
        ComposerKt.sourceInformation($composer3, "C(WorkHistoryJobCard)P(1)389@17392L11,389@17350L62,390@17466L11,391@17528L38,392@17573L7334,386@17227L7680:ExpertWorkHistoryDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(dateFormat) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1629396853, $dirty, -1, "com.example.ui.components.WorkHistoryJobCard (ExpertWorkHistoryDialog.kt:385)");
            }
            $composer2 = $composer3;
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14)), CardDefaults.INSTANCE.cardColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface-0d7_KjU(), 0L, 0L, 0L, $composer3, CardDefaults.$stable << 12, 14), CardDefaults.INSTANCE.cardElevation-aqJV_2Y(Dp.constructor-impl(1), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, (CardDefaults.$stable << 18) | 6, 62), BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl((float) 1.2d), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOutlineVariant-0d7_KjU()), ComposableLambdaKt.rememberComposableLambda(-696478503, true, new Function3() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ExpertWorkHistoryDialogKt.WorkHistoryJobCard$lambda$68(CustomerJobEntity.this, dateFormat, function0, function02, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 196614, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.ExpertWorkHistoryDialogKt$$ExternalSyntheticLambda2
                public final Object invoke(Object obj, Object obj2) {
                    return ExpertWorkHistoryDialogKt.WorkHistoryJobCard$lambda$69(CustomerJobEntity.this, dateFormat, function0, function02, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0c32  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0d3d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0c48 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0c01  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0af0  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0a77  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0904 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x08bb  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0737  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0762  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x078d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0602 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x05b9  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x049d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0361 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0442  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x05a7  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x05ec  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x08a9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x08b5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x08ee  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0a11  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0a89  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0afc  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0bef  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0bfb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit WorkHistoryJobCard$lambda$68(final com.example.data.model.CustomerJobEntity r128, java.text.SimpleDateFormat r129, kotlin.jvm.functions.Function0 r130, kotlin.jvm.functions.Function0 r131, androidx.compose.foundation.layout.ColumnScope r132, androidx.compose.runtime.Composer r133, int r134) {
        /*
            Method dump skipped, instructions count: 3410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertWorkHistoryDialogKt.WorkHistoryJobCard$lambda$68(com.example.data.model.CustomerJobEntity, java.text.SimpleDateFormat, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WorkHistoryJobCard$lambda$68$lambda$67$lambda$59$lambda$58(String $statusText, long $statusFg, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C450@20149L296:ExpertWorkHistoryDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-28202428, $changed, -1, "com.example.ui.components.WorkHistoryJobCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ExpertWorkHistoryDialog.kt:450)");
            }
            TextKt.Text--4IGK_g($statusText, PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(8), Dp.constructor-impl(3)), $statusFg, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit WorkHistoryJobCard$lambda$68$lambda$67$lambda$61$lambda$60(CustomerJobEntity $job, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C469@20849L272:ExpertWorkHistoryDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1508258989, $changed, -1, "com.example.ui.components.WorkHistoryJobCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ExpertWorkHistoryDialog.kt:469)");
            }
            TextKt.Text--4IGK_g("🛠️ " + $job.getServiceType(), PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(7), Dp.constructor-impl(3)), 0L, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131028);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit WorkHistoryJobCard$lambda$68$lambda$67$lambda$65(com.example.data.model.CustomerJobEntity r82, androidx.compose.runtime.Composer r83, int r84) {
        /*
            Method dump skipped, instructions count: 981
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertWorkHistoryDialogKt.WorkHistoryJobCard$lambda$68$lambda$67$lambda$65(com.example.data.model.CustomerJobEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }
}
