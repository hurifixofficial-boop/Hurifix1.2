package com.example.ui.components;

import android.content.Context;
import android.widget.Toast;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ChatKt;
import androidx.compose.material.icons.automirrored.filled.SendKt;
import androidx.compose.material.icons.filled.CallKt;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material.icons.filled.EditKt;
import androidx.compose.material.icons.filled.LockKt;
import androidx.compose.material.icons.filled.WarningKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.data.model.ExpertEntity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: ExpertCard.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a±\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00040\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\fH\u0007¢\u0006\u0002\u0010\u0016\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006\u0017²\u0006\n\u0010\u0018\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0019\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010\u001c\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u001e\u001a\u00020\u001fX\u008a\u008e\u0002²\u0006\u0010\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\fX\u008a\u008e\u0002²\u0006\n\u0010!\u001a\u00020\nX\u008a\u008e\u0002"}, d2 = {"WhatsAppDarkGreen", "Landroidx/compose/ui/graphics/Color;", "J", "ExpertCard", "", "expert", "Lcom/example/data/model/ExpertEntity;", "modifier", "Landroidx/compose/ui/Modifier;", "isAdmin", "", "onCall", "Lkotlin/Function0;", "onWhatsApp", "onViewMap", "onEdit", "onDelete", "onToggleAvailability", "Lkotlin/Function1;", "onViewWorkHistory", "onSendWelcome", "onLongPress", "(Lcom/example/data/model/ExpertEntity;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;III)V", "app", "showConfirmDialog", "confirmTitle", "", "confirmMessage", "confirmButtonText", "confirmIsDestructive", "confirmIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "confirmAction", "showFullScreenImage"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class ExpertCardKt {
    private static final long WhatsAppDarkGreen = ColorKt.Color(4279599165L);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$84(ExpertEntity expertEntity, Modifier modifier, boolean z, Function0 function0, Function0 function02, Function0 function03, Function0 function04, Function0 function05, Function1 function1, Function0 function06, Function0 function07, Function0 function08, int i, int i2, int i3, Composer composer, int i4) {
        ExpertCard(expertEntity, modifier, z, function0, function02, function03, function04, function05, function1, function06, function07, function08, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:149:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0607  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0741  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x065f  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x055f  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0587  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x05b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ExpertCard(final com.example.data.model.ExpertEntity r47, androidx.compose.ui.Modifier r48, boolean r49, final kotlin.jvm.functions.Function0<kotlin.Unit> r50, final kotlin.jvm.functions.Function0<kotlin.Unit> r51, final kotlin.jvm.functions.Function0<kotlin.Unit> r52, final kotlin.jvm.functions.Function0<kotlin.Unit> r53, final kotlin.jvm.functions.Function0<kotlin.Unit> r54, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r55, final kotlin.jvm.functions.Function0<kotlin.Unit> r56, final kotlin.jvm.functions.Function0<kotlin.Unit> r57, kotlin.jvm.functions.Function0<kotlin.Unit> r58, androidx.compose.runtime.Composer r59, final int r60, final int r61, final int r62) {
        /*
            Method dump skipped, instructions count: 1928
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertCardKt.ExpertCard(com.example.data.model.ExpertEntity, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final boolean ExpertCard$lambda$1(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void ExpertCard$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String ExpertCard$lambda$4(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String ExpertCard$lambda$7(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String ExpertCard$lambda$10(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean ExpertCard$lambda$13(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void ExpertCard$lambda$14(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final ImageVector ExpertCard$lambda$16(MutableState<ImageVector> mutableState) {
        return (ImageVector) ((State) mutableState).getValue();
    }

    private static final Function0<Unit> ExpertCard$lambda$20(MutableState<Function0<Unit>> mutableState) {
        return (Function0) ((State) mutableState).getValue();
    }

    private static final boolean ExpertCard$lambda$23(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void ExpertCard$lambda$24(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static /* synthetic */ void ExpertCard$requestConfirm$default(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, String str, String str2, String str3, boolean z, ImageVector imageVector, Function0 function0, int i, Object obj) {
        ExpertCard$requestConfirm(mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, str, str2, (i & 512) != 0 ? "Confirm" : str3, (i & 1024) != 0 ? false : z, (i & 2048) != 0 ? WarningKt.getWarning(Icons.INSTANCE.getDefault()) : imageVector, function0);
    }

    private static final void ExpertCard$requestConfirm(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<Boolean> mutableState4, MutableState<ImageVector> mutableState5, MutableState<Function0<Unit>> mutableState6, MutableState<Boolean> mutableState7, String title, String message, String buttonText, boolean isDestructive, ImageVector icon, Function0<Unit> function0) {
        mutableState.setValue(title);
        mutableState2.setValue(message);
        mutableState3.setValue(buttonText);
        ExpertCard$lambda$14(mutableState4, isDestructive);
        mutableState5.setValue(icon);
        mutableState6.setValue(function0);
        ExpertCard$lambda$2(mutableState7, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$26$lambda$25(MutableState $showConfirmDialog$delegate, MutableState $confirmAction$delegate) {
        ExpertCard$lambda$2($showConfirmDialog$delegate, false);
        ExpertCard$lambda$20($confirmAction$delegate).invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$28$lambda$27(MutableState $showConfirmDialog$delegate) {
        ExpertCard$lambda$2($showConfirmDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$30$lambda$29(MutableState $showFullScreenImage$delegate) {
        ExpertCard$lambda$24($showFullScreenImage$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0688  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x06b5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x072a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0736  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x076d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x088b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0783  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x073c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x06c0  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0693  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x04db A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03b3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0227  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertCard$lambda$44(final androidx.compose.runtime.MutableState r129, com.example.data.model.ExpertEntity r130, android.content.Context r131, androidx.compose.runtime.Composer r132, int r133) {
        /*
            Method dump skipped, instructions count: 2193
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertCardKt.ExpertCard$lambda$44(androidx.compose.runtime.MutableState, com.example.data.model.ExpertEntity, android.content.Context, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$44$lambda$32$lambda$31(MutableState $showFullScreenImage$delegate) {
        ExpertCard$lambda$24($showFullScreenImage$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$44$lambda$43$lambda$42$lambda$37$lambda$36(final MutableState $showFullScreenImage$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C189@8094L31,188@8040L557:ExpertCard.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-404687238, $changed, -1, "com.example.ui.components.ExpertCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ExpertCard.kt:188)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1937590937, "CC(remember):ExpertCard.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.ExpertCardKt$$ExternalSyntheticLambda17
                    public final Object invoke() {
                        return ExpertCardKt.ExpertCard$lambda$44$lambda$43$lambda$42$lambda$37$lambda$36$lambda$35$lambda$34($showFullScreenImage$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) obj, SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$ExpertCardKt.INSTANCE.m78getLambda$244621865$app(), $composer, 196662, 28);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$44$lambda$43$lambda$42$lambda$37$lambda$36$lambda$35$lambda$34(MutableState $showFullScreenImage$delegate) {
        ExpertCard$lambda$24($showFullScreenImage$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0b51  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0b5d  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0b96  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0c94  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0ca0  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0cd9  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0e3e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0e68  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0f45  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0f4e  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x103b  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x1047  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x1079  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x1120  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x11ee  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x128b  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x12df A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x13aa  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x1318  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x1148  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x108d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x104b  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0e6e  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0e44  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0cef A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0ca6  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0bac  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0b63  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x08f3  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x08aa  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0768 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x071f  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0638 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0411 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x05dd  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x05e9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0622  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x070d  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0719  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0752  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0898  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x08a4  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x08dd  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0a47  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertCard$lambda$83(final com.example.data.model.ExpertEntity r136, kotlin.jvm.functions.Function0 r137, final kotlin.jvm.functions.Function1 r138, final androidx.compose.runtime.MutableState r139, final android.content.Context r140, final long r141, long r143, final androidx.compose.runtime.MutableState r145, final androidx.compose.runtime.MutableState r146, final androidx.compose.runtime.MutableState r147, final androidx.compose.runtime.MutableState r148, final androidx.compose.runtime.MutableState r149, final androidx.compose.runtime.MutableState r150, final androidx.compose.runtime.MutableState r151, final kotlin.jvm.functions.Function0 r152, final boolean r153, final kotlin.jvm.functions.Function0 r154, final kotlin.jvm.functions.Function0 r155, kotlin.jvm.functions.Function0 r156, final kotlin.jvm.functions.Function0 r157, boolean r158, final kotlin.jvm.functions.Function0 r159, androidx.compose.foundation.layout.ColumnScope r160, androidx.compose.runtime.Composer r161, int r162) {
        /*
            Method dump skipped, instructions count: 5040
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertCardKt.ExpertCard$lambda$83(com.example.data.model.ExpertEntity, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.runtime.MutableState, android.content.Context, long, long, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, kotlin.jvm.functions.Function0, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, boolean, kotlin.jvm.functions.Function0, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$57$lambda$48$lambda$47(MutableState $showFullScreenImage$delegate) {
        ExpertCard$lambda$24($showFullScreenImage$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$57$lambda$53$lambda$52$lambda$51(ExpertEntity $expert, long $catFg, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C328@14276L355:ExpertCard.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-936090394, $changed, -1, "com.example.ui.components.ExpertCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ExpertCard.kt:328)");
            }
            TextKt.Text--4IGK_g("🛠️ " + $expert.getCategory(), PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(6), Dp.constructor-impl(2)), $catFg, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$57$lambda$56$lambda$55(ExpertEntity $expert, final Function1 $onToggleAvailability, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate, final boolean newAvail) {
        ExpertCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Change Status?", "Change " + $expert.getName() + "'s status to " + (newAvail ? "Available" : "Busy / Unavailable") + "?", "Update Status", false, null, new Function0() { // from class: com.example.ui.components.ExpertCardKt$$ExternalSyntheticLambda16
            public final Object invoke() {
                return ExpertCardKt.ExpertCard$lambda$83$lambda$82$lambda$57$lambda$56$lambda$55$lambda$54($onToggleAvailability, newAvail);
            }
        }, 3072, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$57$lambda$56$lambda$55$lambda$54(Function1 $onToggleAvailability, boolean $newAvail) {
        $onToggleAvailability.invoke(Boolean.valueOf($newAvail));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertCard$lambda$83$lambda$82$lambda$61$lambda$60(com.example.data.model.ExpertEntity r49, androidx.compose.runtime.Composer r50, int r51) {
        /*
            Method dump skipped, instructions count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertCardKt.ExpertCard$lambda$83$lambda$82$lambda$61$lambda$60(com.example.data.model.ExpertEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0499  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertCard$lambda$83$lambda$82$lambda$68(final com.example.data.model.ExpertEntity r75, final kotlin.jvm.functions.Function0 r76, final androidx.compose.runtime.MutableState r77, final androidx.compose.runtime.MutableState r78, final androidx.compose.runtime.MutableState r79, final androidx.compose.runtime.MutableState r80, final androidx.compose.runtime.MutableState r81, final androidx.compose.runtime.MutableState r82, final androidx.compose.runtime.MutableState r83, androidx.compose.runtime.Composer r84, int r85) {
        /*
            Method dump skipped, instructions count: 1183
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertCardKt.ExpertCard$lambda$83$lambda$82$lambda$68(com.example.data.model.ExpertEntity, kotlin.jvm.functions.Function0, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$68$lambda$67$lambda$64$lambda$63(ExpertEntity $expert, Function0 $onSendWelcome, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        ExpertCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Send Welcome Message?", "Send welcome WhatsApp message to " + $expert.getName() + " (" + $expert.getPhone() + ")?", "Send Now", false, SendKt.getSend(Icons.AutoMirrored.Filled.INSTANCE), $onSendWelcome, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$68$lambda$67$lambda$66$lambda$65(ExpertEntity $expert, Function0 $onSendWelcome, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        ExpertCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Send Welcome Again?", "Send welcome WhatsApp message again to " + $expert.getName() + " (" + $expert.getPhone() + ")?", "Send Again", false, SendKt.getSend(Icons.AutoMirrored.Filled.INSTANCE), $onSendWelcome, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertCard$lambda$83$lambda$82$lambda$71(java.lang.String r49, java.lang.String r50, boolean r51, androidx.compose.runtime.Composer r52, int r53) {
        /*
            Method dump skipped, instructions count: 539
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.ExpertCardKt.ExpertCard$lambda$83$lambda$82$lambda$71(java.lang.String, java.lang.String, boolean, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$81$lambda$73$lambda$72(ExpertEntity $expert, Function0 $onCall, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        ExpertCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Call Expert?", "Call expert " + $expert.getName() + " at " + $expert.getPhone() + "?", "Call Now", false, CallKt.getCall(Icons.INSTANCE.getDefault()), $onCall, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$81$lambda$75$lambda$74(ExpertEntity $expert, Function0 $onWhatsApp, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        ExpertCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Open WhatsApp?", "Chat with expert " + $expert.getName() + " (" + $expert.getPhone() + ") on WhatsApp?", "Open WhatsApp", false, ChatKt.getChat(Icons.AutoMirrored.Filled.INSTANCE), $onWhatsApp, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$81$lambda$77$lambda$76(boolean $isTimeLocked, Context $context, Function0 $onEdit) {
        if ($isTimeLocked) {
            Toast.makeText($context, "Editing locked after 24 hours. Contact Admin to make changes.", 1).show();
        }
        $onEdit.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$81$lambda$78(boolean $isTimeLocked, Composer $composer, int $changed) {
        long j;
        ComposerKt.sourceInformation($composer, "C593@27045L325:ExpertCard.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(443174470, $changed, -1, "com.example.ui.components.ExpertCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ExpertCard.kt:593)");
            }
            Icons.Filled filled = Icons.INSTANCE.getDefault();
            ImageVector lock = $isTimeLocked ? LockKt.getLock(filled) : EditKt.getEdit(filled);
            String str = $isTimeLocked ? "Locked (View Only)" : "Edit";
            if ($isTimeLocked) {
                $composer.startReplaceGroup(688911703);
                $composer.endReplaceGroup();
                j = ColorKt.Color(4290321436L);
            } else {
                $composer.startReplaceGroup(688913270);
                ComposerKt.sourceInformation($composer, "596@27320L11");
                j = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU();
                $composer.endReplaceGroup();
            }
            IconKt.Icon-ww6aTOc(lock, str, (Modifier) null, j, $composer, 0, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertCard$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79(ExpertEntity $expert, Function0 $onDelete, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        ExpertCard$requestConfirm($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Move to Recycle Bin?", "Are you sure you want to move expert " + $expert.getName() + " (" + $expert.getCategory() + ") to the recycle bin?", "Yes, Move to Bin", true, DeleteKt.getDelete(Icons.INSTANCE.getDefault()), $onDelete);
        return Unit.INSTANCE;
    }
}
