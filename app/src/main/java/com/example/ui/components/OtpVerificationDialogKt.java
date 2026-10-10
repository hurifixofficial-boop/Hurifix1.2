package com.example.ui.components;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.IntState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.util.OtpRateLimiter;
import com.example.util.PhoneAuthManager;
import com.example.util.SoundHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: OtpVerificationDialog.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\u001aE\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\t¨\u0006\n²\u0006\n\u0010\u000b\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\r\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\f\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\u0014X\u008a\u008e\u0002"}, d2 = {"OtpVerificationDialog", "", "phone", "", "purposeTitle", "purposeSubtitle", "onSuccess", "Lkotlin/Function0;", "onDismiss", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "app", "otpInput", "activeVerificationId", "activeOtpCodeHint", "errorMessage", "isVerifying", "", "isSendingOtp", "isVerifiedSuccess", "resendTimerSeconds", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class OtpVerificationDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$lambda$56(String str, String str2, String str3, Function0 function0, Function0 function02, int i, int i2, Composer composer, int i3) {
        OtpVerificationDialog(str, str2, str3, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void OtpVerificationDialog(final java.lang.String r39, java.lang.String r40, java.lang.String r41, final kotlin.jvm.functions.Function0<kotlin.Unit> r42, final kotlin.jvm.functions.Function0<kotlin.Unit> r43, androidx.compose.runtime.Composer r44, final int r45, final int r46) {
        /*
            Method dump skipped, instructions count: 1263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.OtpVerificationDialogKt.OtpVerificationDialog(java.lang.String, java.lang.String, java.lang.String, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final String OtpVerificationDialog$lambda$3(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String OtpVerificationDialog$lambda$6(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String OtpVerificationDialog$lambda$9(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String OtpVerificationDialog$lambda$12(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean OtpVerificationDialog$lambda$15(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void OtpVerificationDialog$lambda$16(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean OtpVerificationDialog$lambda$18(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void OtpVerificationDialog$lambda$19(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean OtpVerificationDialog$lambda$21(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void OtpVerificationDialog$lambda$22(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int OtpVerificationDialog$lambda$24(MutableIntState $resendTimerSeconds$delegate) {
        return ((IntState) $resendTimerSeconds$delegate).getIntValue();
    }

    private static final void OtpVerificationDialog$performVerifyOtp(Context context, String cleanPhone, MutableState<String> mutableState, final MutableState<Boolean> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, final MutableState<Boolean> mutableState5) {
        if (OtpVerificationDialog$lambda$3(mutableState).length() < 6) {
            return;
        }
        OtpVerificationDialog$lambda$16(mutableState2, true);
        PhoneAuthManager phoneAuthManager = PhoneAuthManager.INSTANCE;
        String OtpVerificationDialog$lambda$6 = OtpVerificationDialog$lambda$6(mutableState3);
        if (StringsKt.isBlank(OtpVerificationDialog$lambda$6)) {
            OtpVerificationDialog$lambda$6 = "FALLBACK:" + OtpVerificationDialog$lambda$9(mutableState4);
        }
        phoneAuthManager.verifyOtp(context, cleanPhone, OtpVerificationDialog$lambda$6, OtpVerificationDialog$lambda$3(mutableState), new Function0() { // from class: com.example.ui.components.OtpVerificationDialogKt$$ExternalSyntheticLambda2
            public final Object invoke() {
                return OtpVerificationDialogKt.OtpVerificationDialog$performVerifyOtp$lambda$27(mutableState2, mutableState5);
            }
        }, new Function1() { // from class: com.example.ui.components.OtpVerificationDialogKt$$ExternalSyntheticLambda3
            public final Object invoke(Object obj) {
                return OtpVerificationDialogKt.OtpVerificationDialog$performVerifyOtp$lambda$28(mutableState2, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$performVerifyOtp$lambda$27(MutableState $isVerifying$delegate, MutableState $isVerifiedSuccess$delegate) {
        OtpVerificationDialog$lambda$16($isVerifying$delegate, false);
        OtpVerificationDialog$lambda$22($isVerifiedSuccess$delegate, true);
        SoundHelper.INSTANCE.playSFX("success");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$performVerifyOtp$lambda$28(MutableState $isVerifying$delegate, String str) {
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        OtpVerificationDialog$lambda$16($isVerifying$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void OtpVerificationDialog$sendOtpCode(OtpRateLimiter rateLimiter, String cleanPhone, final Context context, Activity activity, final MutableState<Boolean> mutableState, MutableState<String> mutableState2, final MutableState<String> mutableState3, final MutableIntState resendTimerSeconds$delegate) {
        OtpVerificationDialog$lambda$19(mutableState, true);
        mutableState2.setValue(null);
        boolean canReq = ((Boolean) rateLimiter.checkCanRequestOtp(cleanPhone).component1()).booleanValue();
        if (!canReq) {
            OtpVerificationDialog$lambda$19(mutableState, false);
        } else {
            PhoneAuthManager.INSTANCE.sendOtp(context, activity, cleanPhone, new Function2() { // from class: com.example.ui.components.OtpVerificationDialogKt$$ExternalSyntheticLambda14
                public final Object invoke(Object obj, Object obj2) {
                    return OtpVerificationDialogKt.OtpVerificationDialog$sendOtpCode$lambda$29(context, mutableState, mutableState3, resendTimerSeconds$delegate, (String) obj, (String) obj2);
                }
            }, new Function1() { // from class: com.example.ui.components.OtpVerificationDialogKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj) {
                    return OtpVerificationDialogKt.OtpVerificationDialog$sendOtpCode$lambda$30(mutableState, (String) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$sendOtpCode$lambda$29(Context $context, MutableState $isSendingOtp$delegate, MutableState $activeVerificationId$delegate, MutableIntState $resendTimerSeconds$delegate, String verId, String str) {
        Intrinsics.checkNotNullParameter(verId, "verId");
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        OtpVerificationDialog$lambda$19($isSendingOtp$delegate, false);
        $activeVerificationId$delegate.setValue(verId);
        $resendTimerSeconds$delegate.setIntValue(60);
        Toast.makeText($context, "OTP Sent", 0).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$sendOtpCode$lambda$30(MutableState $isSendingOtp$delegate, String str) {
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        OtpVerificationDialog$lambda$19($isSendingOtp$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0218  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OtpVerificationDialog$lambda$41(java.lang.String r71, java.lang.String r72, androidx.compose.runtime.Composer r73, int r74) {
        /*
            Method dump skipped, instructions count: 846
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.OtpVerificationDialogKt.OtpVerificationDialog$lambda$41(java.lang.String, java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x07c8  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0475  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OtpVerificationDialog$lambda$55(androidx.compose.runtime.MutableState r99, final java.lang.String r100, final androidx.compose.runtime.MutableState r101, final com.example.util.OtpRateLimiter r102, final android.content.Context r103, final android.app.Activity r104, final androidx.compose.runtime.MutableIntState r105, final androidx.compose.runtime.MutableState r106, final androidx.compose.runtime.MutableState r107, final androidx.compose.runtime.MutableState r108, androidx.compose.runtime.Composer r109, int r110) {
        /*
            Method dump skipped, instructions count: 2313
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.OtpVerificationDialogKt.OtpVerificationDialog$lambda$55(androidx.compose.runtime.MutableState, java.lang.String, androidx.compose.runtime.MutableState, com.example.util.OtpRateLimiter, android.content.Context, android.app.Activity, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OtpVerificationDialog$lambda$55$lambda$54$lambda$45(java.lang.String r73, androidx.compose.foundation.layout.ColumnScope r74, androidx.compose.runtime.Composer r75, int r76) {
        /*
            Method dump skipped, instructions count: 874
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.OtpVerificationDialogKt.OtpVerificationDialog$lambda$55$lambda$54$lambda$45(java.lang.String, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$lambda$55$lambda$54$lambda$48$lambda$47(MutableState $otpInput$delegate, String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        String str = input;
        Appendable sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isDigit(charAt)) {
                sb.append(charAt);
            }
        }
        $otpInput$delegate.setValue(StringsKt.take(((StringBuilder) sb).toString(), 6));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$lambda$55$lambda$54$lambda$53$lambda$51$lambda$50(OtpRateLimiter $rateLimiter, String $cleanPhone, Context $context, Activity $activity, MutableState $isSendingOtp$delegate, MutableState $errorMessage$delegate, MutableState $activeVerificationId$delegate, MutableIntState $resendTimerSeconds$delegate) {
        OtpVerificationDialog$sendOtpCode($rateLimiter, $cleanPhone, $context, $activity, $isSendingOtp$delegate, $errorMessage$delegate, $activeVerificationId$delegate, $resendTimerSeconds$delegate);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$lambda$55$lambda$54$lambda$53$lambda$52(MutableState $isSendingOtp$delegate, RowScope $this$TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C:OtpVerificationDialog.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-250595254, $changed, -1, "com.example.ui.components.OtpVerificationDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (OtpVerificationDialog.kt:299)");
            }
            if (OtpVerificationDialog$lambda$18($isSendingOtp$delegate)) {
                $composer.startReplaceGroup(-829990890);
                ComposerKt.sourceInformation($composer, "300@12588L80,301@12705L28,302@12770L36");
                ProgressIndicatorKt.CircularProgressIndicator-LxG7B9w(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), 0L, Dp.constructor-impl((float) 1.5d), 0L, 0, $composer, 390, 26);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer, 6);
                TextKt.Text--4IGK_g("Sending...", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3078, 0, 131062);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-829701722);
                ComposerKt.sourceInformation($composer, "304@12884L74");
                TextKt.Text--4IGK_g("🔄 Resend OTP Code", (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199686, 0, 131030);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$lambda$37(final Context $context, final String $cleanPhone, final MutableState $isVerifiedSuccess$delegate, final MutableState $otpInput$delegate, final MutableState $isVerifying$delegate, final MutableState $activeVerificationId$delegate, final MutableState $activeOtpCodeHint$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C:OtpVerificationDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-726906450, $changed, -1, "com.example.ui.components.OtpVerificationDialog.<anonymous> (OtpVerificationDialog.kt:319)");
            }
            if (!OtpVerificationDialog$lambda$21($isVerifiedSuccess$delegate)) {
                $composer.startReplaceGroup(-44887019);
                ComposerKt.sourceInformation($composer, "321@13487L22,324@13650L438,320@13449L639");
                ComposerKt.sourceInformationMarkerStart($composer, 1522573828, "CC(remember):OtpVerificationDialog.kt#9igjgp");
                boolean changedInstance = $composer.changedInstance($context) | $composer.changed($cleanPhone);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                    obj = new Function0() { // from class: com.example.ui.components.OtpVerificationDialogKt$$ExternalSyntheticLambda4
                        public final Object invoke() {
                            return OtpVerificationDialogKt.OtpVerificationDialog$lambda$37$lambda$35$lambda$34($context, $cleanPhone, $otpInput$delegate, $isVerifying$delegate, $activeVerificationId$delegate, $activeOtpCodeHint$delegate, $isVerifiedSuccess$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = rememberedValue;
                }
                Function0 function0 = (Function0) obj;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.Button(function0, (Modifier) null, !OtpVerificationDialog$lambda$15($isVerifying$delegate) && OtpVerificationDialog$lambda$3($otpInput$delegate).length() == 6, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(72657539, true, new Function3() { // from class: com.example.ui.components.OtpVerificationDialogKt$$ExternalSyntheticLambda5
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return OtpVerificationDialogKt.OtpVerificationDialog$lambda$37$lambda$36($isVerifying$delegate, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, $composer, 54), $composer, 805306368, 498);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-44234500);
                ComposerKt.sourceInformation($composer, "334@14126L24");
                BoxKt.Box(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(0)), $composer, 6);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$lambda$37$lambda$35$lambda$34(Context $context, String $cleanPhone, MutableState $otpInput$delegate, MutableState $isVerifying$delegate, MutableState $activeVerificationId$delegate, MutableState $activeOtpCodeHint$delegate, MutableState $isVerifiedSuccess$delegate) {
        OtpVerificationDialog$performVerifyOtp($context, $cleanPhone, $otpInput$delegate, $isVerifying$delegate, $activeVerificationId$delegate, $activeOtpCodeHint$delegate, $isVerifiedSuccess$delegate);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$lambda$37$lambda$36(MutableState $isVerifying$delegate, RowScope $this$Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C:OtpVerificationDialog.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(72657539, $changed, -1, "com.example.ui.components.OtpVerificationDialog.<anonymous>.<anonymous> (OtpVerificationDialog.kt:325)");
            }
            if (OtpVerificationDialog$lambda$15($isVerifying$delegate)) {
                $composer.startReplaceGroup(-122725806);
                ComposerKt.sourceInformation($composer, "326@13816L11,326@13715L123,327@13863L28,328@13916L20");
                ProgressIndicatorKt.CircularProgressIndicator-LxG7B9w(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimary-0d7_KjU(), Dp.constructor-impl(2), 0L, 0, $composer, 390, 24);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer, 6);
                TextKt.Text--4IGK_g("Verifying...", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 6, 0, 131070);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-122458059);
                ComposerKt.sourceInformation($composer, "330@13990L58");
                TextKt.Text--4IGK_g("Verify OTP & Proceed", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196614, 0, 131038);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OtpVerificationDialog$lambda$38(Function0 $onDismiss, MutableState $isVerifiedSuccess$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:OtpVerificationDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1650073876, $changed, -1, "com.example.ui.components.OtpVerificationDialog.<anonymous> (OtpVerificationDialog.kt:338)");
            }
            if (!OtpVerificationDialog$lambda$21($isVerifiedSuccess$delegate)) {
                $composer.startReplaceGroup(-1450092864);
                ComposerKt.sourceInformation($composer, "339@14256L182");
                ButtonKt.OutlinedButton($onDismiss, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$OtpVerificationDialogKt.INSTANCE.getLambda$198031487$app(), $composer, 805306368, 502);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1449879522);
                ComposerKt.sourceInformation($composer, "346@14476L24");
                BoxKt.Box(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(0)), $composer, 6);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
