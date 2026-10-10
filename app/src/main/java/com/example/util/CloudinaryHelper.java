package com.example.util;

import android.content.Context;
import android.net.Uri;
import com.example.BuildConfig;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.OkHttpClient;

/* compiled from: CloudinaryHelper.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00192\u0006\u0010\u001a\u001a\u00020\u0011H\u0086@¢\u0006\u0004\b\u001b\u0010\u001cJ&\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u00192\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\""}, d2 = {"Lcom/example/util/CloudinaryHelper;", "", "<init>", "()V", "TAG", "", "CLOUD_NAME", "API_KEY", "API_SECRET", "UPLOAD_URL", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "client$delegate", "Lkotlin/Lazy;", "compressUriToWebp", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "maxDimension", "", "uploadImage", "Lkotlin/Result;", "webpBytes", "uploadImage-gIAlu-s", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "compressAndUpload", "compressAndUpload-0E7RQCE", "(Landroid/content/Context;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sha1Hex", "input", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes6.dex */
public final class CloudinaryHelper {
    private static final String API_KEY = "397241468624234";
    private static final String API_SECRET = "tiC43p-Ai0HWnWxnymBNVAtBe8Q";
    private static final String CLOUD_NAME = "qgfxr96m";
    private static final String TAG = "CloudinaryHelper";
    private static final String UPLOAD_URL = "https://api.cloudinary.com/v1_1/qgfxr96m/image/upload";
    public static final CloudinaryHelper INSTANCE = new CloudinaryHelper();

    /* renamed from: client$delegate, reason: from kotlin metadata */
    private static final Lazy client = LazyKt.lazy(new Function0() { // from class: com.example.util.CloudinaryHelper$$ExternalSyntheticLambda0
        public final Object invoke() {
            OkHttpClient build;
            build = new OkHttpClient.Builder().connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(45L, TimeUnit.SECONDS).readTimeout(45L, TimeUnit.SECONDS).build();
            return build;
        }
    });
    public static final int $stable = 8;

    private CloudinaryHelper() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getClient() {
        return (OkHttpClient) client.getValue();
    }

    public static /* synthetic */ byte[] compressUriToWebp$default(CloudinaryHelper cloudinaryHelper, Context context, Uri uri, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 500;
        }
        return cloudinaryHelper.compressUriToWebp(context, uri, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0074 A[Catch: Exception -> 0x018d, TRY_LEAVE, TryCatch #1 {Exception -> 0x018d, blocks: (B:3:0x0014, B:5:0x0029, B:8:0x0033, B:16:0x003b, B:17:0x003e, B:19:0x003f, B:21:0x0045, B:25:0x0060, B:27:0x0074, B:30:0x007f, B:54:0x00fd, B:55:0x011a, B:76:0x00da, B:113:0x004c, B:114:0x0054, B:116:0x0058, B:118:0x005c, B:7:0x002c, B:13:0x0039), top: B:2:0x0014, inners: #4, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00fd A[Catch: Exception -> 0x018d, TryCatch #1 {Exception -> 0x018d, blocks: (B:3:0x0014, B:5:0x0029, B:8:0x0033, B:16:0x003b, B:17:0x003e, B:19:0x003f, B:21:0x0045, B:25:0x0060, B:27:0x0074, B:30:0x007f, B:54:0x00fd, B:55:0x011a, B:76:0x00da, B:113:0x004c, B:114:0x0054, B:116:0x0058, B:118:0x005c, B:7:0x002c, B:13:0x0039), top: B:2:0x0014, inners: #4, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0127 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x014d A[Catch: Exception -> 0x0188, TryCatch #5 {Exception -> 0x0188, blocks: (B:59:0x0138, B:61:0x014d, B:62:0x0153, B:64:0x0165, B:65:0x0178, B:68:0x016f, B:71:0x0132, B:109:0x0184, B:110:0x0187, B:106:0x0182, B:29:0x0077), top: B:28:0x0077, inners: #0, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0165 A[Catch: Exception -> 0x0188, TryCatch #5 {Exception -> 0x0188, blocks: (B:59:0x0138, B:61:0x014d, B:62:0x0153, B:64:0x0165, B:65:0x0178, B:68:0x016f, B:71:0x0132, B:109:0x0184, B:110:0x0187, B:106:0x0182, B:29:0x0077), top: B:28:0x0077, inners: #0, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x016f A[Catch: Exception -> 0x0188, TryCatch #5 {Exception -> 0x0188, blocks: (B:59:0x0138, B:61:0x014d, B:62:0x0153, B:64:0x0165, B:65:0x0178, B:68:0x016f, B:71:0x0132, B:109:0x0184, B:110:0x0187, B:106:0x0182, B:29:0x0077), top: B:28:0x0077, inners: #0, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] compressUriToWebp(android.content.Context r21, android.net.Uri r22, int r23) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.util.CloudinaryHelper.compressUriToWebp(android.content.Context, android.net.Uri, int):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: uploadImage-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m219uploadImagegIAlus(byte[] r7, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.String>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.example.util.CloudinaryHelper$uploadImage$1
            if (r0 == 0) goto L14
            r0 = r8
            com.example.util.CloudinaryHelper$uploadImage$1 r0 = (com.example.util.CloudinaryHelper$uploadImage$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.util.CloudinaryHelper$uploadImage$1 r0 = new com.example.util.CloudinaryHelper$uploadImage$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L36;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            java.lang.Object r2 = r0.L$0
            r7 = r2
            byte[] r7 = (byte[]) r7
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L57
        L36:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.CoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            com.example.util.CloudinaryHelper$uploadImage$2 r4 = new com.example.util.CloudinaryHelper$uploadImage$2
            r5 = 0
            r4.<init>(r7, r5)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$0 = r5
            r5 = 1
            r0.label = r5
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r0)
            if (r3 != r2) goto L57
            return r2
        L57:
            kotlin.Result r3 = (kotlin.Result) r3
            java.lang.Object r2 = r3.unbox-impl()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.util.CloudinaryHelper.m219uploadImagegIAlus(byte[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: compressAndUpload-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m218compressAndUpload0E7RQCE(android.content.Context r7, android.net.Uri r8, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.String>> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.example.util.CloudinaryHelper$compressAndUpload$1
            if (r0 == 0) goto L14
            r0 = r9
            com.example.util.CloudinaryHelper$compressAndUpload$1 r0 = (com.example.util.CloudinaryHelper$compressAndUpload$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.util.CloudinaryHelper$compressAndUpload$1 r0 = new com.example.util.CloudinaryHelper$compressAndUpload$1
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
            android.net.Uri r8 = (android.net.Uri) r8
            java.lang.Object r2 = r0.L$0
            r7 = r2
            android.content.Context r7 = (android.content.Context) r7
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L60
        L3b:
            kotlin.ResultKt.throwOnFailure(r1)
            com.example.util.GlobalLoadingManager r3 = com.example.util.GlobalLoadingManager.INSTANCE
            com.example.util.CloudinaryHelper$compressAndUpload$2 r4 = new com.example.util.CloudinaryHelper$compressAndUpload$2
            r5 = 0
            r4.<init>(r7, r8, r5)
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$0 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$1 = r5
            r5 = 1
            r0.label = r5
            java.lang.String r5 = "Loading..."
            java.lang.Object r3 = r3.withLoading(r5, r4, r0)
            if (r3 != r2) goto L60
            return r2
        L60:
            kotlin.Result r3 = (kotlin.Result) r3
            java.lang.Object r2 = r3.unbox-impl()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.util.CloudinaryHelper.m218compressAndUpload0E7RQCE(android.content.Context, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String sha1Hex(String input) {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] bytes = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bytes2 = md.digest(bytes);
        Intrinsics.checkNotNull(bytes2);
        return ArraysKt.joinToString$default(bytes2, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.example.util.CloudinaryHelper$$ExternalSyntheticLambda1
            public final Object invoke(Object obj) {
                return CloudinaryHelper.sha1Hex$lambda$7(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final CharSequence sha1Hex$lambda$7(byte it) {
        String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
