package com.example.ui.components;

import android.content.Context;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
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
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.WarningKt;
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
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
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
import com.example.data.model.ExpertEntity;
import com.example.data.model.RankedExpert;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NearestExpertsDialog.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001aE\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\nH\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f²\u0006\n\u0010\r\u001a\u00020\u000eX\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\u0014X\u008a\u008e\u0002²\u0006\u0010\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\bX\u008a\u008e\u0002"}, d2 = {"NearestExpertsDialog", "", "job", "Lcom/example/data/model/CustomerJobEntity;", "rankedExperts", "", "Lcom/example/data/model/RankedExpert;", "onDismiss", "Lkotlin/Function0;", "onAssignExpert", "Lkotlin/Function1;", "(Lcom/example/data/model/CustomerJobEntity;Ljava/util/List;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app", "showConfirmDialog", "", "confirmTitle", "", "confirmMessage", "confirmButtonText", "confirmIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "confirmAction"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class NearestExpertsDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertsDialog$lambda$36(CustomerJobEntity customerJobEntity, List list, Function0 function0, Function1 function1, int i, Composer composer, int i2) {
        NearestExpertsDialog(customerJobEntity, list, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void NearestExpertsDialog(final CustomerJobEntity job, final List<RankedExpert> list, final Function0<Unit> function0, final Function1<? super RankedExpert, Unit> function1, Composer $composer, final int $changed) {
        Object obj;
        Object obj2;
        MutableState confirmTitle$delegate;
        Object obj3;
        Object obj4;
        Object obj5;
        MutableState confirmIcon$delegate;
        Context context;
        Object obj6;
        Composer $composer2;
        Object obj7;
        Object obj8;
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(list, "rankedExperts");
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Intrinsics.checkNotNullParameter(function1, "onAssignExpert");
        Composer $composer3 = $composer.startRestartGroup(-1430193138);
        ComposerKt.sourceInformation($composer3, "C(NearestExpertsDialog)P(!1,3,2)72@3147L7,74@3185L34,75@3244L31,76@3302L31,77@3363L38,78@3425L50,79@3501L43,113@4496L18185,110@4341L18340:NearestExpertsDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(list) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1430193138, $dirty, -1, "com.example.ui.components.NearestExpertsDialog (NearestExpertsDialog.kt:71)");
            }
            CompositionLocal localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context2 = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer3, -26061648, "CC(remember):NearestExpertsDialog.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            final MutableState showConfirmDialog$delegate = (MutableState) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -26059763, "CC(remember):NearestExpertsDialog.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                obj2 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            MutableState confirmTitle$delegate2 = (MutableState) obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -26057907, "CC(remember):NearestExpertsDialog.kt#9igjgp");
            Object rememberedValue3 = $composer3.rememberedValue();
            if (rememberedValue3 == Composer.Companion.getEmpty()) {
                confirmTitle$delegate = confirmTitle$delegate2;
                obj3 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj3);
            } else {
                confirmTitle$delegate = confirmTitle$delegate2;
                obj3 = rememberedValue3;
            }
            final MutableState confirmMessage$delegate = (MutableState) obj3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -26055948, "CC(remember):NearestExpertsDialog.kt#9igjgp");
            Object rememberedValue4 = $composer3.rememberedValue();
            if (rememberedValue4 == Composer.Companion.getEmpty()) {
                obj4 = SnapshotStateKt.mutableStateOf$default("Confirm", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj4);
            } else {
                obj4 = rememberedValue4;
            }
            final MutableState confirmButtonText$delegate = (MutableState) obj4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -26053952, "CC(remember):NearestExpertsDialog.kt#9igjgp");
            Object rememberedValue5 = $composer3.rememberedValue();
            if (rememberedValue5 == Composer.Companion.getEmpty()) {
                obj5 = SnapshotStateKt.mutableStateOf$default(WarningKt.getWarning(Icons.INSTANCE.getDefault()), (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj5);
            } else {
                obj5 = rememberedValue5;
            }
            MutableState confirmIcon$delegate2 = (MutableState) obj5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -26051527, "CC(remember):NearestExpertsDialog.kt#9igjgp");
            Object rememberedValue6 = $composer3.rememberedValue();
            if (rememberedValue6 == Composer.Companion.getEmpty()) {
                confirmIcon$delegate = confirmIcon$delegate2;
                context = context2;
                obj6 = SnapshotStateKt.mutableStateOf$default(new Function0() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda7
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                }, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj6);
            } else {
                confirmIcon$delegate = confirmIcon$delegate2;
                context = context2;
                obj6 = rememberedValue6;
            }
            final MutableState confirmAction$delegate = (MutableState) obj6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (NearestExpertsDialog$lambda$1(showConfirmDialog$delegate)) {
                $composer3.startReplaceGroup(-807122105);
                ComposerKt.sourceInformation($composer3, "102@4175L89,106@4290L29,97@3980L349");
                String NearestExpertsDialog$lambda$4 = NearestExpertsDialog$lambda$4(confirmTitle$delegate);
                String NearestExpertsDialog$lambda$7 = NearestExpertsDialog$lambda$7(confirmMessage$delegate);
                String NearestExpertsDialog$lambda$10 = NearestExpertsDialog$lambda$10(confirmButtonText$delegate);
                ImageVector NearestExpertsDialog$lambda$13 = NearestExpertsDialog$lambda$13(confirmIcon$delegate);
                ComposerKt.sourceInformationMarkerStart($composer3, -26029913, "CC(remember):NearestExpertsDialog.kt#9igjgp");
                Object rememberedValue7 = $composer3.rememberedValue();
                if (rememberedValue7 == Composer.Companion.getEmpty()) {
                    obj7 = new Function0() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda8
                        public final Object invoke() {
                            return NearestExpertsDialogKt.NearestExpertsDialog$lambda$20$lambda$19(showConfirmDialog$delegate, confirmAction$delegate);
                        }
                    };
                    $composer3.updateRememberedValue(obj7);
                } else {
                    obj7 = rememberedValue7;
                }
                Function0 function02 = (Function0) obj7;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -26026293, "CC(remember):NearestExpertsDialog.kt#9igjgp");
                Object rememberedValue8 = $composer3.rememberedValue();
                if (rememberedValue8 == Composer.Companion.getEmpty()) {
                    obj8 = new Function0() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda9
                        public final Object invoke() {
                            return NearestExpertsDialogKt.NearestExpertsDialog$lambda$22$lambda$21(showConfirmDialog$delegate);
                        }
                    };
                    $composer3.updateRememberedValue(obj8);
                } else {
                    obj8 = rememberedValue8;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                HurifixConfirmDialogKt.HurifixConfirmDialog(NearestExpertsDialog$lambda$4, NearestExpertsDialog$lambda$7, NearestExpertsDialog$lambda$10, null, NearestExpertsDialog$lambda$13, false, function02, (Function0) obj8, $composer3, 14155776, 40);
                $composer2 = $composer3;
            } else {
                $composer2 = $composer3;
                $composer2.startReplaceGroup(-811072652);
            }
            $composer2.endReplaceGroup();
            final Context context3 = context;
            int $dirty2 = $dirty;
            final MutableState confirmTitle$delegate3 = confirmTitle$delegate;
            final MutableState confirmIcon$delegate3 = confirmIcon$delegate;
            AndroidDialog_androidKt.Dialog(function0, new DialogProperties(false, false, (SecureFlagPolicy) null, false, false, 7, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(-1267137179, true, new Function2() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda10
                public final Object invoke(Object obj9, Object obj10) {
                    return NearestExpertsDialogKt.NearestExpertsDialog$lambda$35(function0, job, list, function1, context3, confirmTitle$delegate3, confirmMessage$delegate, confirmButtonText$delegate, confirmIcon$delegate3, confirmAction$delegate, showConfirmDialog$delegate, (Composer) obj9, ((Integer) obj10).intValue());
                }
            }, $composer2, 54), $composer2, (($dirty2 >> 6) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj9, Object obj10) {
                    return NearestExpertsDialogKt.NearestExpertsDialog$lambda$36(CustomerJobEntity.this, list, function0, function1, $changed, (Composer) obj9, ((Integer) obj10).intValue());
                }
            });
        }
    }

    private static final boolean NearestExpertsDialog$lambda$1(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void NearestExpertsDialog$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String NearestExpertsDialog$lambda$4(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String NearestExpertsDialog$lambda$7(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String NearestExpertsDialog$lambda$10(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final ImageVector NearestExpertsDialog$lambda$13(MutableState<ImageVector> mutableState) {
        return (ImageVector) ((State) mutableState).getValue();
    }

    private static final Function0<Unit> NearestExpertsDialog$lambda$17(MutableState<Function0<Unit>> mutableState) {
        return (Function0) ((State) mutableState).getValue();
    }

    static /* synthetic */ void NearestExpertsDialog$requestConfirm$default(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, String str, String str2, String str3, ImageVector imageVector, Function0 function0, int i, Object obj) {
        String str4;
        ImageVector imageVector2;
        if ((i & 256) == 0) {
            str4 = str3;
        } else {
            str4 = "Confirm";
        }
        if ((i & 512) == 0) {
            imageVector2 = imageVector;
        } else {
            imageVector2 = WarningKt.getWarning(Icons.INSTANCE.getDefault());
        }
        NearestExpertsDialog$requestConfirm(mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, str, str2, str4, imageVector2, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NearestExpertsDialog$requestConfirm(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<ImageVector> mutableState4, MutableState<Function0<Unit>> mutableState5, MutableState<Boolean> mutableState6, String title, String message, String buttonText, ImageVector icon, Function0<Unit> function0) {
        mutableState.setValue(title);
        mutableState2.setValue(message);
        mutableState3.setValue(buttonText);
        mutableState4.setValue(icon);
        mutableState5.setValue(function0);
        NearestExpertsDialog$lambda$2(mutableState6, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertsDialog$lambda$20$lambda$19(MutableState $showConfirmDialog$delegate, MutableState $confirmAction$delegate) {
        NearestExpertsDialog$lambda$2($showConfirmDialog$delegate, false);
        NearestExpertsDialog$lambda$17($confirmAction$delegate).invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertsDialog$lambda$22$lambda$21(MutableState $showConfirmDialog$delegate) {
        NearestExpertsDialog$lambda$2($showConfirmDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertsDialog$lambda$35(final Function0 $onDismiss, final CustomerJobEntity $job, final List $rankedExperts, final Function1 $onAssignExpert, final Context $context, final MutableState $confirmTitle$delegate, final MutableState $confirmMessage$delegate, final MutableState $confirmButtonText$delegate, final MutableState $confirmIcon$delegate, final MutableState $confirmAction$delegate, final MutableState $showConfirmDialog$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C114@4518L15,114@4506L27,117@4620L1588,150@6219L16456,115@4542L18133:NearestExpertsDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1267137179, $changed, -1, "com.example.ui.components.NearestExpertsDialog.<anonymous> (NearestExpertsDialog.kt:114)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 2051883764, "CC(remember):NearestExpertsDialog.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda0
                    public final Object invoke() {
                        return NearestExpertsDialogKt.NearestExpertsDialog$lambda$35$lambda$24$lambda$23($onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackHandlerKt.BackHandler(false, (Function0) obj, $composer, 0, 1);
            ScaffoldKt.Scaffold-TvnljyQ(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ComposableLambdaKt.rememberComposableLambda(496182569, true, new Function2() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda2
                public final Object invoke(Object obj2, Object obj3) {
                    return NearestExpertsDialogKt.NearestExpertsDialog$lambda$35$lambda$29(CustomerJobEntity.this, $onDismiss, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, (Function2) null, 0, 0L, 0L, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(1586189876, true, new Function3() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return NearestExpertsDialogKt.NearestExpertsDialog$lambda$35$lambda$34($rankedExperts, $job, $onAssignExpert, $context, $confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306422, 508);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertsDialog$lambda$35$lambda$24$lambda$23(Function0 $onDismiss) {
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertsDialog$lambda$35$lambda$29(final CustomerJobEntity $job, final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C119@4677L1070,140@5786L206,146@6113L11,145@6041L135,118@4638L1556:NearestExpertsDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(496182569, $changed, -1, "com.example.ui.components.NearestExpertsDialog.<anonymous>.<anonymous> (NearestExpertsDialog.kt:118)");
            }
            AppBarKt.TopAppBar-GHTll3U(ComposableLambdaKt.rememberComposableLambda(843442149, true, new Function2() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda4
                public final Object invoke(Object obj, Object obj2) {
                    return NearestExpertsDialogKt.NearestExpertsDialog$lambda$35$lambda$29$lambda$27(CustomerJobEntity.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-1649389277, true, new Function2() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda5
                public final Object invoke(Object obj, Object obj2) {
                    return NearestExpertsDialogKt.NearestExpertsDialog$lambda$35$lambda$29$lambda$28($onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function3) null, 0.0f, (WindowInsets) null, TopAppBarDefaults.INSTANCE.topAppBarColors-zjMxDiM(ColorSchemeKt.surfaceColorAtElevation-3ABfNKs(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable), Dp.constructor-impl(3)), 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 15, 30), (TopAppBarScrollBehavior) null, $composer, 390, 186);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit NearestExpertsDialog$lambda$35$lambda$29$lambda$27(com.example.data.model.CustomerJobEntity r82, androidx.compose.runtime.Composer r83, int r84) {
        /*
            Method dump skipped, instructions count: 886
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.NearestExpertsDialogKt.NearestExpertsDialog$lambda$35$lambda$29$lambda$27(com.example.data.model.CustomerJobEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertsDialog$lambda$35$lambda$29$lambda$28(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C141@5812L158:NearestExpertsDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1649389277, $changed, -1, "com.example.ui.components.NearestExpertsDialog.<anonymous>.<anonymous>.<anonymous> (NearestExpertsDialog.kt:141)");
            }
            IconButtonKt.IconButton($onDismiss, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$NearestExpertsDialogKt.INSTANCE.getLambda$767015206$app(), $composer, 196608, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertsDialog$lambda$35$lambda$34(final List $rankedExperts, final CustomerJobEntity $job, final Function1 $onAssignExpert, final Context $context, final MutableState $confirmTitle$delegate, final MutableState $confirmMessage$delegate, final MutableState $confirmButtonText$delegate, final MutableState $confirmIcon$delegate, final MutableState $confirmAction$delegate, final MutableState $showConfirmDialog$delegate, PaddingValues paddingValues, Composer $composer, int $changed) {
        Object obj;
        Composer composer;
        int i;
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        ComposerKt.sourceInformation($composer, "C:NearestExpertsDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(paddingValues) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1586189876, $dirty2, -1, "com.example.ui.components.NearestExpertsDialog.<anonymous>.<anonymous> (NearestExpertsDialog.kt:151)");
            }
            if ($rankedExperts.isEmpty()) {
                $composer.startReplaceGroup(952477556);
                ComposerKt.sourceInformation($composer, "152@6297L378");
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
                    $composer.createNode(constructor);
                } else {
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
                        ComposerKt.sourceInformationMarkerStart(composer3, -1399265163, "C159@6628L11,159@6566L91:NearestExpertsDialog.kt#qonjpd");
                        TextKt.Text--4IGK_g("No experts currently available.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer3, 6, 0, 131066);
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
                ComposerKt.sourceInformationMarkerStart(composer32, -1399265163, "C159@6628L11,159@6566L91:NearestExpertsDialog.kt#qonjpd");
                TextKt.Text--4IGK_g("No experts currently available.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer32, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer32, 6, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd(composer32);
                ComposerKt.sourceInformationMarkerEnd(composer32);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(953372588);
                ComposerKt.sourceInformation($composer, "168@7000L15651,162@6713L15938");
                Modifier padding = PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), paddingValues);
                Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(10));
                PaddingValues paddingValues2 = PaddingKt.PaddingValues-0680j_4(Dp.constructor-impl(16));
                Arrangement.Vertical vertical2 = vertical;
                ComposerKt.sourceInformationMarkerStart($composer, -384878601, "CC(remember):NearestExpertsDialog.kt#9igjgp");
                boolean changedInstance = $composer.changedInstance($rankedExperts) | $composer.changed($job) | $composer.changed($onAssignExpert) | $composer.changedInstance($context);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                    obj = new Function1() { // from class: com.example.ui.components.NearestExpertsDialogKt$$ExternalSyntheticLambda6
                        public final Object invoke(Object obj2) {
                            return NearestExpertsDialogKt.NearestExpertsDialog$lambda$35$lambda$34$lambda$33$lambda$32($rankedExperts, $job, $onAssignExpert, $context, $confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, (LazyListScope) obj2);
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
    public static final Unit NearestExpertsDialog$lambda$35$lambda$34$lambda$33$lambda$32(final List $rankedExperts, final CustomerJobEntity $job, final Function1 $onAssignExpert, final Context $context, final MutableState $confirmTitle$delegate, final MutableState $confirmMessage$delegate, final MutableState $confirmButtonText$delegate, final MutableState $confirmIcon$delegate, final MutableState $confirmAction$delegate, final MutableState $showConfirmDialog$delegate, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        final Function1 function1 = new Function1() { // from class: com.example.ui.components.NearestExpertsDialogKt$NearestExpertsDialog$lambda$35$lambda$34$lambda$33$lambda$32$$inlined$items$default$1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return m139invoke((RankedExpert) p1);
            }

            /* renamed from: invoke, reason: collision with other method in class */
            public final Void m139invoke(RankedExpert rankedExpert) {
                return null;
            }
        };
        $this$LazyColumn.items($rankedExperts.size(), (Function1) null, new Function1<Integer, Object>() { // from class: com.example.ui.components.NearestExpertsDialogKt$NearestExpertsDialog$lambda$35$lambda$34$lambda$33$lambda$32$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke(((Number) p1).intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($rankedExperts.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.components.NearestExpertsDialogKt$NearestExpertsDialog$lambda$35$lambda$34$lambda$33$lambda$32$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
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
                final RankedExpert rankedExpert = (RankedExpert) $rankedExperts.get(it);
                $composer.startReplaceGroup(388252802);
                ComposerKt.sourceInformation($composer, "C*175@7368L11,175@7326L62,176@7462L11,177@7544L38,179@7672L14939,173@7206L15405:NearestExpertsDialog.kt#qonjpd");
                final ExpertEntity expert = rankedExpert.getExpert();
                Long assignedExpertId = $job.getAssignedExpertId();
                final boolean z = assignedExpertId != null && assignedExpertId.longValue() == expert.getId();
                Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                CardColors cardColors = CardDefaults.INSTANCE.cardColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface-0d7_KjU(), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14);
                BorderStroke borderStroke = BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl((float) 1.5d), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant-0d7_KjU());
                CardElevation cardElevation = CardDefaults.INSTANCE.cardElevation-aqJV_2Y(Dp.constructor-impl(2), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer, (CardDefaults.$stable << 18) | 6, 62);
                Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12));
                final CustomerJobEntity customerJobEntity = $job;
                final Function1 function12 = $onAssignExpert;
                final Context context = $context;
                final MutableState mutableState = $confirmTitle$delegate;
                final MutableState mutableState2 = $confirmMessage$delegate;
                final MutableState mutableState3 = $confirmButtonText$delegate;
                final MutableState mutableState4 = $confirmIcon$delegate;
                final MutableState mutableState5 = $confirmAction$delegate;
                final MutableState mutableState6 = $showConfirmDialog$delegate;
                CardKt.Card(fillMaxWidth$default, shape, cardColors, cardElevation, borderStroke, ComposableLambdaKt.rememberComposableLambda(-812974041, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.example.ui.components.NearestExpertsDialogKt$NearestExpertsDialog$3$3$2$1$1$1
                    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                        invoke((ColumnScope) p1, (Composer) p2, ((Number) p3).intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:102:0x0bbc  */
                    /* JADX WARN: Removed duplicated region for block: B:104:0x0ab9  */
                    /* JADX WARN: Removed duplicated region for block: B:106:0x09b3  */
                    /* JADX WARN: Removed duplicated region for block: B:107:0x096c  */
                    /* JADX WARN: Removed duplicated region for block: B:108:0x08de  */
                    /* JADX WARN: Removed duplicated region for block: B:110:0x0791  */
                    /* JADX WARN: Removed duplicated region for block: B:111:0x0748  */
                    /* JADX WARN: Removed duplicated region for block: B:113:0x0514 A[ADDED_TO_REGION] */
                    /* JADX WARN: Removed duplicated region for block: B:114:0x04cb  */
                    /* JADX WARN: Removed duplicated region for block: B:116:0x03af A[ADDED_TO_REGION] */
                    /* JADX WARN: Removed duplicated region for block: B:117:0x0366  */
                    /* JADX WARN: Removed duplicated region for block: B:120:0x0224  */
                    /* JADX WARN: Removed duplicated region for block: B:24:0x0212  */
                    /* JADX WARN: Removed duplicated region for block: B:27:0x021e  */
                    /* JADX WARN: Removed duplicated region for block: B:35:0x0354  */
                    /* JADX WARN: Removed duplicated region for block: B:38:0x0360  */
                    /* JADX WARN: Removed duplicated region for block: B:41:0x0399  */
                    /* JADX WARN: Removed duplicated region for block: B:46:0x04b9  */
                    /* JADX WARN: Removed duplicated region for block: B:49:0x04c5  */
                    /* JADX WARN: Removed duplicated region for block: B:52:0x04fe  */
                    /* JADX WARN: Removed duplicated region for block: B:57:0x0736  */
                    /* JADX WARN: Removed duplicated region for block: B:60:0x0742  */
                    /* JADX WARN: Removed duplicated region for block: B:63:0x077b  */
                    /* JADX WARN: Removed duplicated region for block: B:68:0x0897  */
                    /* JADX WARN: Removed duplicated region for block: B:71:0x095a  */
                    /* JADX WARN: Removed duplicated region for block: B:74:0x0966  */
                    /* JADX WARN: Removed duplicated region for block: B:77:0x099d  */
                    /* JADX WARN: Removed duplicated region for block: B:82:0x0a92  */
                    /* JADX WARN: Removed duplicated region for block: B:87:0x0b9c  */
                    /* JADX WARN: Removed duplicated region for block: B:92:0x0c79  */
                    /* JADX WARN: Removed duplicated region for block: B:97:0x0d0a  */
                    /* JADX WARN: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void invoke(androidx.compose.foundation.layout.ColumnScope r140, androidx.compose.runtime.Composer r141, int r142) {
                        /*
                            Method dump skipped, instructions count: 3342
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.NearestExpertsDialogKt$NearestExpertsDialog$3$3$2$1$1$1.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):void");
                    }
                }, $composer, 54), $composer, 196614, 0);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }
}
