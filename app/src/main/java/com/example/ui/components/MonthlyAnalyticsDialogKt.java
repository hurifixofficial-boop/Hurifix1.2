package com.example.ui.components;

import android.content.Context;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.CardColors;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardElevation;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
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
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MonthlyAnalyticsDialog.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\u001a)\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a'\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0003¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010²\u0006\n\u0010\u0011\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\nX\u008a\u008e\u0002²\u0006\f\u0010\u0013\u001a\u0004\u0018\u00010\nX\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\nX\u008a\u008e\u0002²\u0006\u0018\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\n0\n0\u0003X\u008a\u0084\u0002²\u0006\u0010\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0003X\u008a\u0084\u0002"}, d2 = {"MonthlyAnalyticsDialog", "", "jobs", "", "Lcom/example/data/model/CustomerJobEntity;", "onDismiss", "Lkotlin/Function0;", "(Ljava/util/List;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "StatItem", "label", "", "value", "color", "Landroidx/compose/ui/graphics/Color;", "StatItem-XO-JAsU", "(Ljava/lang/String;Ljava/lang/String;JLandroidx/compose/runtime/Composer;I)V", "app", "searchQuery", "selectedYearFilter", "expandedMonthKey", "orderStatusFilterInMonth", "availableYears", "kotlin.jvm.PlatformType", "groupedStats", "Lcom/example/ui/components/MonthOrderStat;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class MonthlyAnalyticsDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$54(List list, Function0 function0, int i, Composer composer, int i2) {
        MonthlyAnalyticsDialog(list, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit StatItem_XO_JAsU$lambda$56(String str, String str2, long j, int i, Composer composer, int i2) {
        m132StatItemXOJAsU(str, str2, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void MonthlyAnalyticsDialog(final List<CustomerJobEntity> list, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Object obj;
        Object obj2;
        SimpleDateFormat keyFormat;
        Object obj3;
        SimpleDateFormat monthFormat;
        Object obj4;
        Object obj5;
        MutableState selectedYearFilter$delegate;
        Object obj6;
        MutableState searchQuery$delegate;
        Object obj7;
        Object derivedStateOf;
        final MutableState selectedYearFilter$delegate2;
        final MutableState searchQuery$delegate2;
        final SimpleDateFormat keyFormat2;
        Object derivedStateOf2;
        final List<CustomerJobEntity> list2;
        final Function0<Unit> function02;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(list, "jobs");
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(-1333063909);
        ComposerKt.sourceInformation($composer3, "C(MonthlyAnalyticsDialog)90@3753L7,91@3783L63,92@3867L61,93@3950L58,95@4033L31,96@4095L34,97@4158L42,98@4237L34,101@4348L196,109@4597L1666,149@6424L19317,146@6269L19472:MonthlyAnalyticsDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(list) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            list2 = list;
            function02 = function0;
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1333063909, $dirty2, -1, "com.example.ui.components.MonthlyAnalyticsDialog (MonthlyAnalyticsDialog.kt:89)");
            }
            CompositionLocal localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer3, -1174156966, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            SimpleDateFormat monthFormat2 = (SimpleDateFormat) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1174154280, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                obj2 = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
                $composer3.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            SimpleDateFormat keyFormat3 = (SimpleDateFormat) obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1174151627, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            Object rememberedValue3 = $composer3.rememberedValue();
            if (rememberedValue3 == Composer.Companion.getEmpty()) {
                keyFormat = keyFormat3;
                obj3 = new SimpleDateFormat("yyyy", Locale.getDefault());
                $composer3.updateRememberedValue(obj3);
            } else {
                keyFormat = keyFormat3;
                obj3 = rememberedValue3;
            }
            final SimpleDateFormat yearFormat = (SimpleDateFormat) obj3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1174148998, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            Object rememberedValue4 = $composer3.rememberedValue();
            if (rememberedValue4 == Composer.Companion.getEmpty()) {
                monthFormat = monthFormat2;
                obj4 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj4);
            } else {
                monthFormat = monthFormat2;
                obj4 = rememberedValue4;
            }
            MutableState searchQuery$delegate3 = (MutableState) obj4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1174147011, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            Object rememberedValue5 = $composer3.rememberedValue();
            if (rememberedValue5 == Composer.Companion.getEmpty()) {
                obj5 = SnapshotStateKt.mutableStateOf$default("All", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj5);
            } else {
                obj5 = rememberedValue5;
            }
            MutableState selectedYearFilter$delegate3 = (MutableState) obj5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1174144987, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            Object rememberedValue6 = $composer3.rememberedValue();
            if (rememberedValue6 == Composer.Companion.getEmpty()) {
                selectedYearFilter$delegate = selectedYearFilter$delegate3;
                obj6 = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj6);
            } else {
                selectedYearFilter$delegate = selectedYearFilter$delegate3;
                obj6 = rememberedValue6;
            }
            final MutableState expandedMonthKey$delegate = (MutableState) obj6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1174142467, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            Object rememberedValue7 = $composer3.rememberedValue();
            if (rememberedValue7 == Composer.Companion.getEmpty()) {
                searchQuery$delegate = searchQuery$delegate3;
                obj7 = SnapshotStateKt.mutableStateOf$default("ALL", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj7);
            } else {
                searchQuery$delegate = searchQuery$delegate3;
                obj7 = rememberedValue7;
            }
            final MutableState orderStatusFilterInMonth$delegate = (MutableState) obj7;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1174138753, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            boolean changed = $composer3.changed(list);
            Object rememberedValue8 = $composer3.rememberedValue();
            if (changed || rememberedValue8 == Composer.Companion.getEmpty()) {
                derivedStateOf = SnapshotStateKt.derivedStateOf(new Function0() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda10
                    public final Object invoke() {
                        return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$17$lambda$16(list, yearFormat);
                    }
                });
                $composer3.updateRememberedValue(derivedStateOf);
            } else {
                derivedStateOf = rememberedValue8;
            }
            final State availableYears$delegate = (State) derivedStateOf;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String MonthlyAnalyticsDialog$lambda$7 = MonthlyAnalyticsDialog$lambda$7(selectedYearFilter$delegate);
            String MonthlyAnalyticsDialog$lambda$4 = MonthlyAnalyticsDialog$lambda$4(searchQuery$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, -1174129315, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            boolean changed2 = $composer3.changed(MonthlyAnalyticsDialog$lambda$7) | $composer3.changed(list) | $composer3.changed(MonthlyAnalyticsDialog$lambda$4);
            Object rememberedValue9 = $composer3.rememberedValue();
            if (changed2 || rememberedValue9 == Composer.Companion.getEmpty()) {
                selectedYearFilter$delegate2 = selectedYearFilter$delegate;
                searchQuery$delegate2 = searchQuery$delegate;
                keyFormat2 = keyFormat;
                final SimpleDateFormat monthFormat3 = monthFormat;
                derivedStateOf2 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda11
                    public final Object invoke() {
                        return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$29$lambda$28(list, selectedYearFilter$delegate2, yearFormat, keyFormat2, monthFormat3, searchQuery$delegate2);
                    }
                });
                $composer3.updateRememberedValue(derivedStateOf2);
            } else {
                searchQuery$delegate2 = searchQuery$delegate;
                derivedStateOf2 = rememberedValue9;
                keyFormat2 = keyFormat;
                selectedYearFilter$delegate2 = selectedYearFilter$delegate;
            }
            final State groupedStats$delegate = (State) derivedStateOf2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            DialogProperties dialogProperties = new DialogProperties(false, false, (SecureFlagPolicy) null, false, false, 7, (DefaultConstructorMarker) null);
            final MutableState selectedYearFilter$delegate4 = selectedYearFilter$delegate2;
            Function2 function2 = new Function2() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda12
                public final Object invoke(Object obj8, Object obj9) {
                    return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$53(function0, groupedStats$delegate, list, keyFormat2, context, searchQuery$delegate2, availableYears$delegate, selectedYearFilter$delegate4, expandedMonthKey$delegate, orderStatusFilterInMonth$delegate, (Composer) obj8, ((Integer) obj9).intValue());
                }
            };
            list2 = list;
            function02 = function0;
            $composer2 = $composer3;
            AndroidDialog_androidKt.Dialog(function02, dialogProperties, ComposableLambdaKt.rememberComposableLambda(1343417714, true, function2, $composer3, 54), $composer2, (($dirty2 >> 3) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda13
                public final Object invoke(Object obj8, Object obj9) {
                    return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$54(list2, function02, $changed, (Composer) obj8, ((Integer) obj9).intValue());
                }
            });
        }
    }

    private static final String MonthlyAnalyticsDialog$lambda$4(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String MonthlyAnalyticsDialog$lambda$7(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String MonthlyAnalyticsDialog$lambda$10(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String MonthlyAnalyticsDialog$lambda$13(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final List<String> MonthlyAnalyticsDialog$lambda$18(State<? extends List<String>> state) {
        return (List) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List MonthlyAnalyticsDialog$lambda$17$lambda$16(List $jobs, SimpleDateFormat $yearFormat) {
        List list = $jobs;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add($yearFormat.format(new Date(((CustomerJobEntity) it.next()).getCreatedAt())));
        }
        List years = CollectionsKt.sortedDescending(CollectionsKt.distinct((List) arrayList));
        return CollectionsKt.plus(CollectionsKt.listOf("All"), years);
    }

    private static final List<MonthOrderStat> MonthlyAnalyticsDialog$lambda$30(State<? extends List<MonthOrderStat>> state) {
        return (List) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List MonthlyAnalyticsDialog$lambda$29$lambda$28(List $jobs, MutableState $selectedYearFilter$delegate, SimpleDateFormat $yearFormat, SimpleDateFormat $keyFormat, SimpleDateFormat $monthFormat, MutableState $searchQuery$delegate) {
        List filteredJobs;
        int i;
        List filteredJobs2;
        int i2;
        int i3;
        int i4;
        ArrayList arrayList;
        if (Intrinsics.areEqual(MonthlyAnalyticsDialog$lambda$7($selectedYearFilter$delegate), "All")) {
            filteredJobs = $jobs;
        } else {
            Collection arrayList2 = new ArrayList();
            for (Object obj : $jobs) {
                if (Intrinsics.areEqual($yearFormat.format(new Date(((CustomerJobEntity) obj).getCreatedAt())), MonthlyAnalyticsDialog$lambda$7($selectedYearFilter$delegate))) {
                    arrayList2.add(obj);
                }
            }
            filteredJobs = (List) arrayList2;
        }
        Map linkedHashMap = new LinkedHashMap();
        for (Object obj2 : filteredJobs) {
            String format = $keyFormat.format(new Date(((CustomerJobEntity) obj2).getCreatedAt()));
            Object obj3 = linkedHashMap.get(format);
            if (obj3 == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(format, arrayList);
            } else {
                arrayList = obj3;
            }
            ((List) arrayList).add(obj2);
        }
        int i5 = 0;
        Collection arrayList3 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            String format2 = $monthFormat.format(new Date(((CustomerJobEntity) CollectionsKt.first(list)).getCreatedAt()));
            int size = list.size();
            List list2 = list;
            int i6 = i5;
            if ((list2 instanceof Collection) && list2.isEmpty()) {
                filteredJobs2 = filteredJobs;
                i = 0;
            } else {
                i = 0;
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    int i7 = i;
                    List filteredJobs3 = filteredJobs;
                    if (Intrinsics.areEqual(((CustomerJobEntity) it.next()).getStatus(), "COMPLETED")) {
                        i = i7 + 1;
                        if (i < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                        filteredJobs = filteredJobs3;
                    } else {
                        i = i7;
                        filteredJobs = filteredJobs3;
                    }
                }
                filteredJobs2 = filteredJobs;
            }
            List list3 = list;
            int i8 = i;
            if ((list3 instanceof Collection) && list3.isEmpty()) {
                i2 = 0;
            } else {
                i2 = 0;
                Iterator it2 = list3.iterator();
                while (it2.hasNext()) {
                    int i9 = i2;
                    Iterable iterable = list3;
                    if (Intrinsics.areEqual(((CustomerJobEntity) it2.next()).getStatus(), "CANCELLED")) {
                        i2 = i9 + 1;
                        if (i2 < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                        list3 = iterable;
                    } else {
                        i2 = i9;
                        list3 = iterable;
                    }
                }
            }
            List list4 = list;
            int i10 = i2;
            if ((list4 instanceof Collection) && list4.isEmpty()) {
                i3 = 0;
            } else {
                i3 = 0;
                Iterator it3 = list4.iterator();
                while (it3.hasNext()) {
                    int i11 = i3;
                    Iterable iterable2 = list4;
                    if (Intrinsics.areEqual(((CustomerJobEntity) it3.next()).getStatus(), "PROCESSING")) {
                        i3 = i11 + 1;
                        if (i3 < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                        list4 = iterable2;
                    } else {
                        i3 = i11;
                        list4 = iterable2;
                    }
                }
            }
            List list5 = list;
            int i12 = i3;
            if ((list5 instanceof Collection) && list5.isEmpty()) {
                i4 = 0;
            } else {
                int i13 = 0;
                Iterator it4 = list5.iterator();
                while (it4.hasNext()) {
                    int i14 = i13;
                    Iterable iterable3 = list5;
                    if (Intrinsics.areEqual(((CustomerJobEntity) it4.next()).getStatus(), "PENDING")) {
                        i13 = i14 + 1;
                        if (i13 < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                        list5 = iterable3;
                    } else {
                        i13 = i14;
                        list5 = iterable3;
                    }
                }
                i4 = i13;
            }
            Intrinsics.checkNotNull(str);
            Intrinsics.checkNotNull(format2);
            arrayList3.add(new MonthOrderStat(str, format2, size, i8, i10, i12, i4));
            i5 = i6;
            filteredJobs = filteredJobs2;
        }
        Collection arrayList4 = new ArrayList();
        for (Object obj4 : (List) arrayList3) {
            MonthOrderStat monthOrderStat = (MonthOrderStat) obj4;
            boolean z = true;
            if (!StringsKt.isBlank(MonthlyAnalyticsDialog$lambda$4($searchQuery$delegate)) && !StringsKt.contains(monthOrderStat.getDisplayMonth(), MonthlyAnalyticsDialog$lambda$4($searchQuery$delegate), true) && !StringsKt.contains(monthOrderStat.getMonthYearKey(), MonthlyAnalyticsDialog$lambda$4($searchQuery$delegate), true)) {
                z = false;
            }
            if (z) {
                arrayList4.add(obj4);
            }
        }
        return CollectionsKt.sortedWith((List) arrayList4, new Comparator() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$MonthlyAnalyticsDialog$lambda$29$lambda$28$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((MonthOrderStat) t2).getMonthYearKey(), ((MonthOrderStat) t).getMonthYearKey());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53(final Function0 $onDismiss, final State $groupedStats$delegate, final List $jobs, final SimpleDateFormat $keyFormat, final Context $context, final MutableState $searchQuery$delegate, final State $availableYears$delegate, final MutableState $selectedYearFilter$delegate, final MutableState $expandedMonthKey$delegate, final MutableState $orderStatusFilterInMonth$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C150@6446L15,150@6434L27,153@6548L1531,185@8090L17645,151@6470L19265:MonthlyAnalyticsDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1343417714, $changed, -1, "com.example.ui.components.MonthlyAnalyticsDialog.<anonymous> (MonthlyAnalyticsDialog.kt:150)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1760732223, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda7
                    public final Object invoke() {
                        return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$53$lambda$32$lambda$31($onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackHandlerKt.BackHandler(false, (Function0) obj, $composer, 0, 1);
            ScaffoldKt.Scaffold-TvnljyQ(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ComposableLambdaKt.rememberComposableLambda(-146597578, true, new Function2() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda8
                public final Object invoke(Object obj2, Object obj3) {
                    return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$53$lambda$34($onDismiss, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, (Function2) null, 0, 0L, 0L, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(-2077361343, true, new Function3() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda9
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$53$lambda$52($groupedStats$delegate, $jobs, $keyFormat, $context, $searchQuery$delegate, $availableYears$delegate, $selectedYearFilter$delegate, $expandedMonthKey$delegate, $orderStatusFilterInMonth$delegate, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306422, 508);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53$lambda$32$lambda$31(Function0 $onDismiss) {
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53$lambda$34(final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C175@7657L206,181@7984L11,180@7912L135,154@6566L1499:MonthlyAnalyticsDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-146597578, $changed, -1, "com.example.ui.components.MonthlyAnalyticsDialog.<anonymous>.<anonymous> (MonthlyAnalyticsDialog.kt:154)");
            }
            AppBarKt.TopAppBar-GHTll3U(ComposableSingletons$MonthlyAnalyticsDialogKt.INSTANCE.m91getLambda$62959630$app(), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-917499088, true, new Function2() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda6
                public final Object invoke(Object obj, Object obj2) {
                    return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$53$lambda$34$lambda$33($onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function3) null, 0.0f, (WindowInsets) null, TopAppBarDefaults.INSTANCE.topAppBarColors-zjMxDiM(ColorSchemeKt.surfaceColorAtElevation-3ABfNKs(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable), Dp.constructor-impl(3)), 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 15, 30), (TopAppBarScrollBehavior) null, $composer, 390, 186);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53$lambda$34$lambda$33(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C176@7683L158:MonthlyAnalyticsDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-917499088, $changed, -1, "com.example.ui.components.MonthlyAnalyticsDialog.<anonymous>.<anonymous>.<anonymous> (MonthlyAnalyticsDialog.kt:176)");
            }
            IconButtonKt.IconButton($onDismiss, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$MonthlyAnalyticsDialogKt.INSTANCE.m89getLambda$1594256333$app(), $composer, 196608, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x05b5  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0708  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x05d5  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit MonthlyAnalyticsDialog$lambda$53$lambda$52(final androidx.compose.runtime.State r92, final java.util.List r93, final java.text.SimpleDateFormat r94, final android.content.Context r95, final androidx.compose.runtime.MutableState r96, androidx.compose.runtime.State r97, final androidx.compose.runtime.MutableState r98, final androidx.compose.runtime.MutableState r99, final androidx.compose.runtime.MutableState r100, androidx.compose.foundation.layout.PaddingValues r101, androidx.compose.runtime.Composer r102, int r103) {
        /*
            Method dump skipped, instructions count: 1806
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$53$lambda$52(androidx.compose.runtime.State, java.util.List, java.text.SimpleDateFormat, android.content.Context, androidx.compose.runtime.MutableState, androidx.compose.runtime.State, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$39$lambda$38(MutableState $searchQuery$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $searchQuery$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$37(final MutableState $searchQuery$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C200@8846L20,200@8825L99:MonthlyAnalyticsDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1948331538, $changed, -1, "com.example.ui.components.MonthlyAnalyticsDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MonthlyAnalyticsDialog.kt:200)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 259133794, "CC(remember):MonthlyAnalyticsDialog.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda14
                    public final Object invoke() {
                        return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$37$lambda$36$lambda$35($searchQuery$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) obj, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$MonthlyAnalyticsDialogKt.INSTANCE.getLambda$1983008369$app(), $composer, 196614, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$37$lambda$36$lambda$35(MutableState $searchQuery$delegate) {
        $searchQuery$delegate.setValue("");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$44$lambda$43$lambda$41$lambda$40(String $yr, MutableState $selectedYearFilter$delegate) {
        Intrinsics.checkNotNull($yr);
        $selectedYearFilter$delegate.setValue($yr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$44$lambda$43$lambda$42(String $yr, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C218@9716L42:MonthlyAnalyticsDialog.kt#qonjpd");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1065705871, $changed, -1, "com.example.ui.components.MonthlyAnalyticsDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MonthlyAnalyticsDialog.kt:218)");
            }
            String str = Intrinsics.areEqual($yr, "All") ? "All Years" : $yr;
            Intrinsics.checkNotNull(str);
            TextKt.Text--4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$50$lambda$49(State $groupedStats$delegate, final List $jobs, final MutableState $expandedMonthKey$delegate, final SimpleDateFormat $keyFormat, final MutableState $orderStatusFilterInMonth$delegate, final Context $context, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        final List MonthlyAnalyticsDialog$lambda$30 = MonthlyAnalyticsDialog$lambda$30($groupedStats$delegate);
        final Function1 function1 = new Function1() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$$ExternalSyntheticLambda15
            public final Object invoke(Object obj) {
                return MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$50$lambda$49$lambda$46((MonthOrderStat) obj);
            }
        };
        final Function1 function12 = new Function1() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$50$lambda$49$$inlined$items$default$1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return m134invoke((MonthOrderStat) p1);
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final Void m134invoke(MonthOrderStat monthOrderStat) {
                return null;
            }
        };
        $this$LazyColumn.items(MonthlyAnalyticsDialog$lambda$30.size(), new Function1<Integer, Object>() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$50$lambda$49$$inlined$items$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke(MonthlyAnalyticsDialog$lambda$30.get(index));
            }
        }, new Function1<Integer, Object>() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$50$lambda$49$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            public final Object invoke(int index) {
                return function12.invoke(MonthlyAnalyticsDialog$lambda$30.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$50$lambda$49$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                int i;
                String MonthlyAnalyticsDialog$lambda$10;
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
                int i2 = $dirty & 14;
                final MonthOrderStat monthOrderStat = (MonthOrderStat) MonthlyAnalyticsDialog$lambda$30.get(it);
                LazyItemScope lazyItemScope = $this$items;
                $composer.startReplaceGroup(-232532766);
                ComposerKt.sourceInformation($composer, "C*258@11663L11,257@11584L159,261@11841L13804,255@11456L14189:MonthlyAnalyticsDialog.kt#qonjpd");
                if (monthOrderStat.getTotalOrders() > 0) {
                    i = (monthOrderStat.getCompletedOrders() * 100) / monthOrderStat.getTotalOrders();
                } else {
                    i = 0;
                }
                final int i3 = i;
                MonthlyAnalyticsDialog$lambda$10 = MonthlyAnalyticsDialogKt.MonthlyAnalyticsDialog$lambda$10($expandedMonthKey$delegate);
                final boolean areEqual = Intrinsics.areEqual(MonthlyAnalyticsDialog$lambda$10, monthOrderStat.getMonthYearKey());
                Iterable iterable = $jobs;
                Collection arrayList = new ArrayList();
                for (Object obj : iterable) {
                    int $dirty2 = $dirty;
                    LazyItemScope lazyItemScope2 = lazyItemScope;
                    if (Intrinsics.areEqual($keyFormat.format(new Date(((CustomerJobEntity) obj).getCreatedAt())), monthOrderStat.getMonthYearKey())) {
                        arrayList.add(obj);
                    }
                    $dirty = $dirty2;
                    lazyItemScope = lazyItemScope2;
                }
                final List list = (List) arrayList;
                Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                CardColors cardColors = CardDefaults.INSTANCE.cardColors-ro_MJ88(Color.copy-wmQWz5c$default(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurfaceVariant-0d7_KjU(), 0.45f, 0.0f, 0.0f, 0.0f, 14, (Object) null), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14);
                Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12));
                final MutableState mutableState = $expandedMonthKey$delegate;
                final MutableState mutableState2 = $orderStatusFilterInMonth$delegate;
                final Context context = $context;
                CardKt.Card(fillMaxWidth$default, shape, cardColors, (CardElevation) null, (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(1878734778, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.example.ui.components.MonthlyAnalyticsDialogKt$MonthlyAnalyticsDialog$1$3$1$5$1$2$1
                    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                        invoke((ColumnScope) p1, (Composer) p2, ((Number) p3).intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:107:0x0a9c  */
                    /* JADX WARN: Removed duplicated region for block: B:119:0x0b5b  */
                    /* JADX WARN: Removed duplicated region for block: B:122:0x0b67  */
                    /* JADX WARN: Removed duplicated region for block: B:125:0x0ba0  */
                    /* JADX WARN: Removed duplicated region for block: B:130:0x0c0b  */
                    /* JADX WARN: Removed duplicated region for block: B:134:0x0e57  */
                    /* JADX WARN: Removed duplicated region for block: B:137:0x0e63  */
                    /* JADX WARN: Removed duplicated region for block: B:140:0x0e9a  */
                    /* JADX WARN: Removed duplicated region for block: B:145:0x0f75  */
                    /* JADX WARN: Removed duplicated region for block: B:150:0x1012  */
                    /* JADX WARN: Removed duplicated region for block: B:155:0x108d  */
                    /* JADX WARN: Removed duplicated region for block: B:157:? A[RETURN, SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:159:0x101f A[ADDED_TO_REGION] */
                    /* JADX WARN: Removed duplicated region for block: B:161:0x0f83  */
                    /* JADX WARN: Removed duplicated region for block: B:163:0x0eb0 A[ADDED_TO_REGION] */
                    /* JADX WARN: Removed duplicated region for block: B:164:0x0e69  */
                    /* JADX WARN: Removed duplicated region for block: B:165:0x0c60  */
                    /* JADX WARN: Removed duplicated region for block: B:176:0x0bb6  */
                    /* JADX WARN: Removed duplicated region for block: B:177:0x0b6d  */
                    /* JADX WARN: Removed duplicated region for block: B:181:0x0dac  */
                    /* JADX WARN: Removed duplicated region for block: B:183:0x0754 A[ADDED_TO_REGION] */
                    /* JADX WARN: Removed duplicated region for block: B:184:0x070b  */
                    /* JADX WARN: Removed duplicated region for block: B:185:0x061a  */
                    /* JADX WARN: Removed duplicated region for block: B:186:0x05c6  */
                    /* JADX WARN: Removed duplicated region for block: B:188:0x0571 A[ADDED_TO_REGION] */
                    /* JADX WARN: Removed duplicated region for block: B:189:0x0528  */
                    /* JADX WARN: Removed duplicated region for block: B:191:0x03bd A[ADDED_TO_REGION] */
                    /* JADX WARN: Removed duplicated region for block: B:192:0x0374  */
                    /* JADX WARN: Removed duplicated region for block: B:194:0x02a3 A[ADDED_TO_REGION] */
                    /* JADX WARN: Removed duplicated region for block: B:195:0x025a  */
                    /* JADX WARN: Removed duplicated region for block: B:29:0x0248  */
                    /* JADX WARN: Removed duplicated region for block: B:32:0x0254  */
                    /* JADX WARN: Removed duplicated region for block: B:35:0x028d  */
                    /* JADX WARN: Removed duplicated region for block: B:40:0x0362  */
                    /* JADX WARN: Removed duplicated region for block: B:43:0x036e  */
                    /* JADX WARN: Removed duplicated region for block: B:46:0x03a7  */
                    /* JADX WARN: Removed duplicated region for block: B:51:0x0516  */
                    /* JADX WARN: Removed duplicated region for block: B:54:0x0522  */
                    /* JADX WARN: Removed duplicated region for block: B:57:0x055b  */
                    /* JADX WARN: Removed duplicated region for block: B:62:0x05c0  */
                    /* JADX WARN: Removed duplicated region for block: B:65:0x0615  */
                    /* JADX WARN: Removed duplicated region for block: B:68:0x06f9  */
                    /* JADX WARN: Removed duplicated region for block: B:71:0x0705  */
                    /* JADX WARN: Removed duplicated region for block: B:74:0x073e  */
                    /* JADX WARN: Removed duplicated region for block: B:79:0x0820  */
                    /* JADX WARN: Removed duplicated region for block: B:93:0x09c7  */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void invoke(androidx.compose.foundation.layout.ColumnScope r115, androidx.compose.runtime.Composer r116, int r117) {
                        /*
                            Method dump skipped, instructions count: 4241
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.MonthlyAnalyticsDialogKt$MonthlyAnalyticsDialog$1$3$1$5$1$2$1.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):void");
                    }
                }, $composer, 54), $composer, 196614, 24);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Object MonthlyAnalyticsDialog$lambda$53$lambda$52$lambda$51$lambda$50$lambda$49$lambda$46(MonthOrderStat it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getMonthYearKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0244  */
    /* renamed from: StatItem-XO-JAsU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m132StatItemXOJAsU(final java.lang.String r51, final java.lang.String r52, final long r53, androidx.compose.runtime.Composer r55, final int r56) {
        /*
            Method dump skipped, instructions count: 607
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.MonthlyAnalyticsDialogKt.m132StatItemXOJAsU(java.lang.String, java.lang.String, long, androidx.compose.runtime.Composer, int):void");
    }
}
