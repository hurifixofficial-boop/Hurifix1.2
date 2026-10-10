package com.example.ui.viewmodel;

import com.example.BuildConfig;
import com.example.data.model.HurifixUser;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AuthViewModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b&\b\u0087\b\u0018\u00002\u00020\u0001B\u009b\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0006HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\t\u0010-\u001a\u00020\u0006HÆ\u0003J\t\u0010.\u001a\u00020\u000eHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00101\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u00102\u001a\u0004\u0018\u00010\u0013HÆ\u0003J¢\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÆ\u0001¢\u0006\u0002\u00104J\u0013\u00105\u001a\u00020\u00062\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u00020\u000eHÖ\u0001J\t\u00108\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0019R\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b\u0011\u0010!R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00069"}, d2 = {"Lcom/example/ui/viewmodel/AuthUiState;", "", "phone", "", "password", "isOtpLoginMode", "", "isOtpSent", "loginOtpInput", "activeVerificationId", "activeOtpCodeHint", "isSendingOtp", "isLoading", "resendTimerSeconds", "", "errorMessage", "successMessage", "isPhoneRegistered", "user", "Lcom/example/data/model/HurifixUser;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZILjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lcom/example/data/model/HurifixUser;)V", "getPhone", "()Ljava/lang/String;", "getPassword", "()Z", "getLoginOtpInput", "getActiveVerificationId", "getActiveOtpCodeHint", "getResendTimerSeconds", "()I", "getErrorMessage", "getSuccessMessage", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getUser", "()Lcom/example/data/model/HurifixUser;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZILjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lcom/example/data/model/HurifixUser;)Lcom/example/ui/viewmodel/AuthUiState;", "equals", "other", "hashCode", "toString", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final /* data */ class AuthUiState {
    public static final int $stable = 0;
    private final String activeOtpCodeHint;
    private final String activeVerificationId;
    private final String errorMessage;
    private final boolean isLoading;
    private final boolean isOtpLoginMode;
    private final boolean isOtpSent;
    private final Boolean isPhoneRegistered;
    private final boolean isSendingOtp;
    private final String loginOtpInput;
    private final String password;
    private final String phone;
    private final int resendTimerSeconds;
    private final String successMessage;
    private final HurifixUser user;

    public AuthUiState() {
        this(null, null, false, false, null, null, null, false, false, 0, null, null, null, null, 16383, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* renamed from: component10, reason: from getter */
    public final int getResendTimerSeconds() {
        return this.resendTimerSeconds;
    }

    /* renamed from: component11, reason: from getter */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    /* renamed from: component12, reason: from getter */
    public final String getSuccessMessage() {
        return this.successMessage;
    }

    /* renamed from: component13, reason: from getter */
    public final Boolean getIsPhoneRegistered() {
        return this.isPhoneRegistered;
    }

    /* renamed from: component14, reason: from getter */
    public final HurifixUser getUser() {
        return this.user;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsOtpLoginMode() {
        return this.isOtpLoginMode;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsOtpSent() {
        return this.isOtpSent;
    }

    /* renamed from: component5, reason: from getter */
    public final String getLoginOtpInput() {
        return this.loginOtpInput;
    }

    /* renamed from: component6, reason: from getter */
    public final String getActiveVerificationId() {
        return this.activeVerificationId;
    }

    /* renamed from: component7, reason: from getter */
    public final String getActiveOtpCodeHint() {
        return this.activeOtpCodeHint;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getIsSendingOtp() {
        return this.isSendingOtp;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    public final AuthUiState copy(String phone, String password, boolean isOtpLoginMode, boolean isOtpSent, String loginOtpInput, String activeVerificationId, String activeOtpCodeHint, boolean isSendingOtp, boolean isLoading, int resendTimerSeconds, String errorMessage, String successMessage, Boolean isPhoneRegistered, HurifixUser user) {
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(loginOtpInput, "loginOtpInput");
        Intrinsics.checkNotNullParameter(activeVerificationId, "activeVerificationId");
        Intrinsics.checkNotNullParameter(activeOtpCodeHint, "activeOtpCodeHint");
        return new AuthUiState(phone, password, isOtpLoginMode, isOtpSent, loginOtpInput, activeVerificationId, activeOtpCodeHint, isSendingOtp, isLoading, resendTimerSeconds, errorMessage, successMessage, isPhoneRegistered, user);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthUiState)) {
            return false;
        }
        AuthUiState authUiState = (AuthUiState) other;
        return Intrinsics.areEqual(this.phone, authUiState.phone) && Intrinsics.areEqual(this.password, authUiState.password) && this.isOtpLoginMode == authUiState.isOtpLoginMode && this.isOtpSent == authUiState.isOtpSent && Intrinsics.areEqual(this.loginOtpInput, authUiState.loginOtpInput) && Intrinsics.areEqual(this.activeVerificationId, authUiState.activeVerificationId) && Intrinsics.areEqual(this.activeOtpCodeHint, authUiState.activeOtpCodeHint) && this.isSendingOtp == authUiState.isSendingOtp && this.isLoading == authUiState.isLoading && this.resendTimerSeconds == authUiState.resendTimerSeconds && Intrinsics.areEqual(this.errorMessage, authUiState.errorMessage) && Intrinsics.areEqual(this.successMessage, authUiState.successMessage) && Intrinsics.areEqual(this.isPhoneRegistered, authUiState.isPhoneRegistered) && Intrinsics.areEqual(this.user, authUiState.user);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.phone.hashCode() * 31) + this.password.hashCode()) * 31) + Boolean.hashCode(this.isOtpLoginMode)) * 31) + Boolean.hashCode(this.isOtpSent)) * 31) + this.loginOtpInput.hashCode()) * 31) + this.activeVerificationId.hashCode()) * 31) + this.activeOtpCodeHint.hashCode()) * 31) + Boolean.hashCode(this.isSendingOtp)) * 31) + Boolean.hashCode(this.isLoading)) * 31) + Integer.hashCode(this.resendTimerSeconds)) * 31) + (this.errorMessage == null ? 0 : this.errorMessage.hashCode())) * 31) + (this.successMessage == null ? 0 : this.successMessage.hashCode())) * 31) + (this.isPhoneRegistered == null ? 0 : this.isPhoneRegistered.hashCode())) * 31) + (this.user != null ? this.user.hashCode() : 0);
    }

    public String toString() {
        return "AuthUiState(phone=" + this.phone + ", password=" + this.password + ", isOtpLoginMode=" + this.isOtpLoginMode + ", isOtpSent=" + this.isOtpSent + ", loginOtpInput=" + this.loginOtpInput + ", activeVerificationId=" + this.activeVerificationId + ", activeOtpCodeHint=" + this.activeOtpCodeHint + ", isSendingOtp=" + this.isSendingOtp + ", isLoading=" + this.isLoading + ", resendTimerSeconds=" + this.resendTimerSeconds + ", errorMessage=" + this.errorMessage + ", successMessage=" + this.successMessage + ", isPhoneRegistered=" + this.isPhoneRegistered + ", user=" + this.user + ")";
    }

    public AuthUiState(String phone, String password, boolean isOtpLoginMode, boolean isOtpSent, String loginOtpInput, String activeVerificationId, String activeOtpCodeHint, boolean isSendingOtp, boolean isLoading, int resendTimerSeconds, String errorMessage, String successMessage, Boolean isPhoneRegistered, HurifixUser user) {
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(loginOtpInput, "loginOtpInput");
        Intrinsics.checkNotNullParameter(activeVerificationId, "activeVerificationId");
        Intrinsics.checkNotNullParameter(activeOtpCodeHint, "activeOtpCodeHint");
        this.phone = phone;
        this.password = password;
        this.isOtpLoginMode = isOtpLoginMode;
        this.isOtpSent = isOtpSent;
        this.loginOtpInput = loginOtpInput;
        this.activeVerificationId = activeVerificationId;
        this.activeOtpCodeHint = activeOtpCodeHint;
        this.isSendingOtp = isSendingOtp;
        this.isLoading = isLoading;
        this.resendTimerSeconds = resendTimerSeconds;
        this.errorMessage = errorMessage;
        this.successMessage = successMessage;
        this.isPhoneRegistered = isPhoneRegistered;
        this.user = user;
    }

    public /* synthetic */ AuthUiState(String str, String str2, boolean z, boolean z2, String str3, String str4, String str5, boolean z3, boolean z4, int i, String str6, String str7, Boolean bool, HurifixUser hurifixUser, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? false : z2, (i2 & 16) != 0 ? "" : str3, (i2 & 32) != 0 ? "" : str4, (i2 & 64) == 0 ? str5 : "", (i2 & 128) != 0 ? false : z3, (i2 & 256) != 0 ? false : z4, (i2 & 512) == 0 ? i : 0, (i2 & 1024) != 0 ? null : str6, (i2 & 2048) != 0 ? null : str7, (i2 & 4096) != 0 ? null : bool, (i2 & 8192) == 0 ? hurifixUser : null);
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getPassword() {
        return this.password;
    }

    public final boolean isOtpLoginMode() {
        return this.isOtpLoginMode;
    }

    public final boolean isOtpSent() {
        return this.isOtpSent;
    }

    public final String getLoginOtpInput() {
        return this.loginOtpInput;
    }

    public final String getActiveVerificationId() {
        return this.activeVerificationId;
    }

    public final String getActiveOtpCodeHint() {
        return this.activeOtpCodeHint;
    }

    public final boolean isSendingOtp() {
        return this.isSendingOtp;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final int getResendTimerSeconds() {
        return this.resendTimerSeconds;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final String getSuccessMessage() {
        return this.successMessage;
    }

    public final Boolean isPhoneRegistered() {
        return this.isPhoneRegistered;
    }

    public final HurifixUser getUser() {
        return this.user;
    }
}
