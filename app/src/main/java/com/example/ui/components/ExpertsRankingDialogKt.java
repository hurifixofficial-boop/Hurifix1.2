package com.example.ui.components;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.CardColors;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
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
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
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
import com.example.BuildConfig;
import com.example.data.firebase.FirestoreSyncManager;
import com.example.data.model.ExpertEntity;
import java.util.Comparator;
import java.util.List;
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

/* compiled from: ExpertsRankingDialog.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a)\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"ExpertsRankingDialog", "", FirestoreSyncManager.EXPERTS_COLLECTION, "", "Lcom/example/data/model/ExpertEntity;", "onDismiss", "Lkotlin/Function0;", "(Ljava/util/List;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class ExpertsRankingDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsRankingDialog$lambda$12(List list, Function0 function0, int i, Composer composer, int i2) {
        ExpertsRankingDialog(list, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ExpertsRankingDialog(final List<ExpertEntity> list, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(list, FirestoreSyncManager.EXPERTS_COLLECTION);
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Composer $composer2 = $composer.startRestartGroup(-1777115405);
        ComposerKt.sourceInformation($composer2, "C(ExpertsRankingDialog)62@2650L7692,59@2495L7847:ExpertsRankingDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1777115405, $dirty2, -1, "com.example.ui.components.ExpertsRankingDialog (ExpertsRankingDialog.kt:52)");
            }
            final Comparator comparator = new Comparator() { // from class: com.example.ui.components.ExpertsRankingDialogKt$ExpertsRankingDialog$$inlined$compareByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Float.valueOf(((ExpertEntity) t2).getRating()), Float.valueOf(((ExpertEntity) t).getRating()));
                }
            };
            final List rankedExperts = CollectionsKt.sortedWith(list, new Comparator() { // from class: com.example.ui.components.ExpertsRankingDialogKt$ExpertsRankingDialog$$inlined$thenByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator.compare(t, t2);
                    return previousCompare != 0 ? previousCompare : ComparisonsKt.compareValues(Integer.valueOf(((ExpertEntity) t2).getCompletedJobsCount()), Integer.valueOf(((ExpertEntity) t).getCompletedJobsCount()));
                }
            });
            AndroidDialog_androidKt.Dialog(function0, new DialogProperties(false, false, (SecureFlagPolicy) null, false, false, 7, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(1375600842, true, new Function2() { // from class: com.example.ui.components.ExpertsRankingDialogKt$$ExternalSyntheticLambda5
                public final Object invoke(Object obj, Object obj2) {
                    return ExpertsRankingDialogKt.ExpertsRankingDialog$lambda$11(function0, rankedExperts, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, (($dirty2 >> 3) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.ExpertsRankingDialogKt$$ExternalSyntheticLambda6
                public final Object invoke(Object obj, Object obj2) {
                    return ExpertsRankingDialogKt.ExpertsRankingDialog$lambda$12(list, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsRankingDialog$lambda$11(final Function0 $onDismiss, final List $rankedExperts, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C63@2672L15,63@2660L27,66@2774L1628,101@4413L5923,64@2696L7640:ExpertsRankingDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1375600842, $changed, -1, "com.example.ui.components.ExpertsRankingDialog.<anonymous> (ExpertsRankingDialog.kt:63)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -897517287, "CC(remember):ExpertsRankingDialog.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.ExpertsRankingDialogKt$$ExternalSyntheticLambda2
                    public final Object invoke() {
                        return ExpertsRankingDialogKt.ExpertsRankingDialog$lambda$11$lambda$3$lambda$2($onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackHandlerKt.BackHandler(false, (Function0) obj, $composer, 0, 1);
            ScaffoldKt.Scaffold-TvnljyQ(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ComposableLambdaKt.rememberComposableLambda(1250078350, true, new Function2() { // from class: com.example.ui.components.ExpertsRankingDialogKt$$ExternalSyntheticLambda3
                public final Object invoke(Object obj2, Object obj3) {
                    return ExpertsRankingDialogKt.ExpertsRankingDialog$lambda$11$lambda$5($onDismiss, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, (Function2) null, 0, 0L, 0L, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(481185305, true, new Function3() { // from class: com.example.ui.components.ExpertsRankingDialogKt$$ExternalSyntheticLambda4
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return ExpertsRankingDialogKt.ExpertsRankingDialog$lambda$11$lambda$10($rankedExperts, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306422, 508);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsRankingDialog$lambda$11$lambda$3$lambda$2(Function0 $onDismiss) {
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsRankingDialog$lambda$11$lambda$5(final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C91@3980L206,97@4307L11,96@4235L135,67@2792L1596:ExpertsRankingDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1250078350, $changed, -1, "com.example.ui.components.ExpertsRankingDialog.<anonymous>.<anonymous> (ExpertsRankingDialog.kt:67)");
            }
            AppBarKt.TopAppBar-GHTll3U(ComposableSingletons$ExpertsRankingDialogKt.INSTANCE.getLambda$1421845322$app(), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(549434760, true, new Function2() { // from class: com.example.ui.components.ExpertsRankingDialogKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj, Object obj2) {
                    return ExpertsRankingDialogKt.ExpertsRankingDialog$lambda$11$lambda$5$lambda$4($onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function3) null, 0.0f, (WindowInsets) null, TopAppBarDefaults.INSTANCE.topAppBarColors-zjMxDiM(ColorSchemeKt.surfaceColorAtElevation-3ABfNKs(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable), Dp.constructor-impl(3)), 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 15, 30), (TopAppBarScrollBehavior) null, $composer, 390, 186);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsRankingDialog$lambda$11$lambda$5$lambda$4(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C92@4006L158:ExpertsRankingDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(549434760, $changed, -1, "com.example.ui.components.ExpertsRankingDialog.<anonymous>.<anonymous>.<anonymous> (ExpertsRankingDialog.kt:92)");
            }
            IconButtonKt.IconButton($onDismiss, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$ExpertsRankingDialogKt.INSTANCE.m82getLambda$637521909$app(), $composer, 196608, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsRankingDialog$lambda$11$lambda$10(final List $rankedExperts, PaddingValues paddingValues, Composer $composer, int $changed) {
        Object obj;
        Function0 function0;
        Composer composer;
        int i;
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        ComposerKt.sourceInformation($composer, "C:ExpertsRankingDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(paddingValues) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(481185305, $dirty2, -1, "com.example.ui.components.ExpertsRankingDialog.<anonymous>.<anonymous> (ExpertsRankingDialog.kt:102)");
            }
            if ($rankedExperts.isEmpty()) {
                $composer.startReplaceGroup(-1609340778);
                ComposerKt.sourceInformation($composer, "103@4491L371");
                Modifier modifier = PaddingKt.padding-3ABfNKs(PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), paddingValues), Dp.constructor-impl(24));
                Alignment center = Alignment.Companion.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0 constructor = ComposeUiNode.Companion.getConstructor();
                int i2 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer composer2 = Updater.constructor-impl($composer);
                Updater.set-impl(composer2, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer2, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer2.getInserting()) {
                    composer = $composer;
                    i = 48;
                } else {
                    composer = $composer;
                    i = 48;
                    if (Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        Updater.set-impl(composer2, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                        int i3 = (i2 >> 6) & 14;
                        Composer composer3 = composer;
                        ComposerKt.sourceInformationMarkerStart(composer3, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScope boxScope = BoxScopeInstance.INSTANCE;
                        int i4 = ((i >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, -367651561, "C110@4815L11,110@4760L84:ExpertsRankingDialog.kt#qonjpd");
                        TextKt.Text--4IGK_g("No expert records found.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer3, 6, 0, 131066);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        $composer.endReplaceGroup();
                    }
                }
                composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer2.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                Updater.set-impl(composer2, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                int i32 = (i2 >> 6) & 14;
                Composer composer32 = composer;
                ComposerKt.sourceInformationMarkerStart(composer32, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
                int i42 = ((i >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer32, -367651561, "C110@4815L11,110@4760L84:ExpertsRankingDialog.kt#qonjpd");
                TextKt.Text--4IGK_g("No expert records found.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer32, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer32, 6, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd(composer32);
                ComposerKt.sourceInformationMarkerEnd(composer32);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1608778779);
                ComposerKt.sourceInformation($composer, "119@5187L5125,113@4900L5412");
                Modifier padding = PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), paddingValues);
                Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(10));
                PaddingValues paddingValues2 = PaddingKt.PaddingValues-0680j_4(Dp.constructor-impl(16));
                Arrangement.Vertical vertical2 = vertical;
                ComposerKt.sourceInformationMarkerStart($composer, -1437359970, "CC(remember):ExpertsRankingDialog.kt#9igjgp");
                boolean changedInstance = $composer.changedInstance($rankedExperts);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                    obj = new Function1() { // from class: com.example.ui.components.ExpertsRankingDialogKt$$ExternalSyntheticLambda0
                        public final Object invoke(Object obj2) {
                            return ExpertsRankingDialogKt.ExpertsRankingDialog$lambda$11$lambda$10$lambda$9$lambda$8($rankedExperts, (LazyListScope) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                LazyDslKt.LazyColumn(padding, (LazyListState) null, paddingValues2, false, vertical2, (Alignment.Horizontal) null, (FlingBehavior) null, false, (Function1) obj, $composer, 24960, 234);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsRankingDialog$lambda$11$lambda$10$lambda$9$lambda$8(final List $rankedExperts, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        $this$LazyColumn.items($rankedExperts.size(), (Function1) null, new Function1<Integer, Object>() { // from class: com.example.ui.components.ExpertsRankingDialogKt$ExpertsRankingDialog$lambda$11$lambda$10$lambda$9$lambda$8$$inlined$itemsIndexed$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            public final Object invoke(int index) {
                $rankedExperts.get(index);
                return null;
            }
        }, ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.components.ExpertsRankingDialogKt$ExpertsRankingDialog$lambda$11$lambda$10$lambda$9$lambda$8$$inlined$itemsIndexed$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                final long Color;
                final long j;
                long j2;
                Modifier modifier;
                float f;
                ComposerKt.sourceInformation($composer, "C188@8866L26:LazyDsl.kt#428nma");
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
                    ComposerKt.traceEventStart(-1091073711, $dirty, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:188)");
                }
                int i = ($dirty & 14) | ($dirty & 112);
                final ExpertEntity expertEntity = (ExpertEntity) $rankedExperts.get(it);
                $composer.startReplaceGroup(1277118359);
                ComposerKt.sourceInformation($composer, "CP(1)*135@6000L162,138@6217L63,140@6370L3902,133@5880L4392:ExpertsRankingDialog.kt#qonjpd");
                final int i2 = it + 1;
                switch (i2) {
                    case BuildConfig.VERSION_CODE /* 1 */:
                        $composer.startReplaceGroup(1842311197);
                        $composer.endReplaceGroup();
                        Color = ColorKt.Color(4293571336L);
                        break;
                    case 2:
                        $composer.startReplaceGroup(1842313085);
                        $composer.endReplaceGroup();
                        Color = ColorKt.Color(4287931320L);
                        break;
                    case 3:
                        $composer.startReplaceGroup(1842315037);
                        $composer.endReplaceGroup();
                        Color = ColorKt.Color(4292441862L);
                        break;
                    default:
                        $composer.startReplaceGroup(1842317914);
                        ComposerKt.sourceInformation($composer, "126@5587L11");
                        Color = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurfaceVariant-0d7_KjU();
                        $composer.endReplaceGroup();
                        break;
                }
                switch (i2) {
                    case BuildConfig.VERSION_CODE /* 1 */:
                    case 2:
                    case 3:
                        $composer.startReplaceGroup(1842322545);
                        $composer.endReplaceGroup();
                        j = Color.Companion.getWhite-0d7_KjU();
                        break;
                    default:
                        $composer.startReplaceGroup(1842324732);
                        ComposerKt.sourceInformation($composer, "130@5800L11");
                        j = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU();
                        $composer.endReplaceGroup();
                        break;
                }
                Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                CardDefaults cardDefaults = CardDefaults.INSTANCE;
                if (i2 == 1) {
                    $composer.startReplaceGroup(1842333181);
                    $composer.endReplaceGroup();
                    j2 = ColorKt.Color(4294900968L);
                } else {
                    $composer.startReplaceGroup(1842334739);
                    ComposerKt.sourceInformation($composer, "136@6113L11");
                    j2 = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface-0d7_KjU();
                    $composer.endReplaceGroup();
                }
                CardColors cardColors = cardDefaults.cardColors-ro_MJ88(j2, 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14);
                CardDefaults cardDefaults2 = CardDefaults.INSTANCE;
                if (i2 > 3) {
                    modifier = fillMaxWidth$default;
                    f = Dp.constructor-impl(0);
                } else {
                    modifier = fillMaxWidth$default;
                    f = Dp.constructor-impl(2);
                }
                CardKt.Card(modifier, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12)), cardColors, cardDefaults2.cardElevation-aqJV_2Y(f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer, CardDefaults.$stable << 18, 62), (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(844315994, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.example.ui.components.ExpertsRankingDialogKt$ExpertsRankingDialog$1$3$2$1$1$1
                    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                        invoke((ColumnScope) p1, (Composer) p2, ((Number) p3).intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:24:0x0239  */
                    /* JADX WARN: Removed duplicated region for block: B:27:0x0245  */
                    /* JADX WARN: Removed duplicated region for block: B:30:0x027c  */
                    /* JADX WARN: Removed duplicated region for block: B:35:0x0426  */
                    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:39:0x0292  */
                    /* JADX WARN: Removed duplicated region for block: B:40:0x024b  */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void invoke(androidx.compose.foundation.layout.ColumnScope r79, androidx.compose.runtime.Composer r80, int r81) {
                        /*
                            Method dump skipped, instructions count: 1066
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertsRankingDialogKt$ExpertsRankingDialog$1$3$2$1$1$1.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):void");
                    }
                }, $composer, 54), $composer, 196614, 16);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }
}
