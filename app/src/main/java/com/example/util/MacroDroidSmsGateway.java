package com.example.util;

import com.example.BuildConfig;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import okhttp3.OkHttpClient;

/* compiled from: MacroDroidSmsGateway.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0086@¢\u0006\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/example/util/MacroDroidSmsGateway;", "", "<init>", "()V", "TAG", "", "BASE_WEBHOOK_URL", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "client$delegate", "Lkotlin/Lazy;", "sendOtpSms", "Lkotlin/Result;", "", "phone", "otpCode", "sendOtpSms-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes6.dex */
public final class MacroDroidSmsGateway {
    private static final String BASE_WEBHOOK_URL = "https://trigger.macrodroid.com/cac6ee5b-e2d8-4b7e-9050-5aa8f9cf8305/hurifix_otp";
    private static final String TAG = "MacroDroidSmsGateway";
    public static final MacroDroidSmsGateway INSTANCE = new MacroDroidSmsGateway();

    /* renamed from: client$delegate, reason: from kotlin metadata */
    private static final Lazy client = LazyKt.lazy(new Function0() { // from class: com.example.util.MacroDroidSmsGateway$$ExternalSyntheticLambda0
        public final Object invoke() {
            OkHttpClient build;
            build = new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(15L, TimeUnit.SECONDS).build();
            return build;
        }
    });
    public static final int $stable = 8;

    private MacroDroidSmsGateway() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getClient() {
        return (OkHttpClient) client.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: sendOtpSms-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m220sendOtpSms0E7RQCE(java.lang.String r7, java.lang.String r8, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.example.util.MacroDroidSmsGateway$sendOtpSms$1
            if (r0 == 0) goto L14
            r0 = r9
            com.example.util.MacroDroidSmsGateway$sendOtpSms$1 r0 = (com.example.util.MacroDroidSmsGateway$sendOtpSms$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.util.MacroDroidSmsGateway$sendOtpSms$1 r0 = new com.example.util.MacroDroidSmsGateway$sendOtpSms$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L3b;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            java.lang.Object r2 = r0.L$1
            r8 = r2
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r2 = r0.L$0
            r7 = r2
            java.lang.String r7 = (java.lang.String) r7
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L62
        L3b:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.CoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            com.example.util.MacroDroidSmsGateway$sendOtpSms$2 r4 = new com.example.util.MacroDroidSmsGateway$sendOtpSms$2
            r5 = 0
            r4.<init>(r7, r8, r5)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$0 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$1 = r5
            r5 = 1
            r0.label = r5
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r0)
            if (r3 != r2) goto L62
            return r2
        L62:
            kotlin.Result r3 = (kotlin.Result) r3
            java.lang.Object r2 = r3.unbox-impl()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.util.MacroDroidSmsGateway.m220sendOtpSms0E7RQCE(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
