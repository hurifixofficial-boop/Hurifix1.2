package com.example.util;

import android.content.Context;
import android.util.Log;
import androidx.work.Constraints;
import androidx.work.CoroutineWorker;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.example.BuildConfig;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AutoBackupWorker.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH\u0096@¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Lcom/example/util/AutoBackupWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes6.dex */
public final class AutoBackupWorker extends CoroutineWorker {
    private static final String TAG = "AutoBackupWorker";
    private static final String UNIQUE_ONETIME_WORK_NAME = "HurifixImmediateAutoBackup";
    private static final String UNIQUE_PERIODIC_WORK_NAME = "HurifixScheduledGoogleDriveAutoBackup";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoBackupWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object doWork(kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.example.util.AutoBackupWorker$doWork$1
            if (r0 == 0) goto L14
            r0 = r7
            com.example.util.AutoBackupWorker$doWork$1 r0 = (com.example.util.AutoBackupWorker$doWork$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.example.util.AutoBackupWorker$doWork$1 r0 = new com.example.util.AutoBackupWorker$doWork$1
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L31;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L4c
        L31:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlinx.coroutines.CoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            com.example.util.AutoBackupWorker$doWork$2 r4 = new com.example.util.AutoBackupWorker$doWork$2
            r5 = 0
            r4.<init>(r6, r5)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5 = 1
            r0.label = r5
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r0)
            if (r3 != r2) goto L4c
            return r2
        L4c:
            java.lang.String r2 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.util.AutoBackupWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: AutoBackupWorker.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/example/util/AutoBackupWorker$Companion;", "", "<init>", "()V", "TAG", "", "UNIQUE_PERIODIC_WORK_NAME", "UNIQUE_ONETIME_WORK_NAME", "scheduleAutoBackup", "", "context", "Landroid/content/Context;", "isEnabled", "", "frequency", "runImmediateBackup", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes6.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void scheduleAutoBackup(Context context, boolean isEnabled, String frequency) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(frequency, "frequency");
            try {
                WorkManager workManager = WorkManager.Companion.getInstance(context);
                if (!isEnabled) {
                    workManager.cancelUniqueWork(AutoBackupWorker.UNIQUE_PERIODIC_WORK_NAME);
                    Log.i(AutoBackupWorker.TAG, "Auto-backup schedule cancelled");
                    return;
                }
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String upperCase = frequency.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                Pair pair = Intrinsics.areEqual(upperCase, "WEEKLY") ? TuplesKt.to(7L, TimeUnit.DAYS) : TuplesKt.to(24L, TimeUnit.HOURS);
                long interval = ((Number) pair.component1()).longValue();
                TimeUnit timeUnit = (TimeUnit) pair.component2();
                Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED).build();
                PeriodicWorkRequest backupRequest = new PeriodicWorkRequest.Builder(AutoBackupWorker.class, interval, timeUnit).setConstraints(constraints).build();
                workManager.enqueueUniquePeriodicWork(AutoBackupWorker.UNIQUE_PERIODIC_WORK_NAME, ExistingPeriodicWorkPolicy.UPDATE, backupRequest);
                Log.i(AutoBackupWorker.TAG, "Auto-backup periodic work scheduled: " + interval + " " + timeUnit);
            } catch (Throwable e) {
                Log.w(AutoBackupWorker.TAG, "Failed to schedule AutoBackup: " + e.getMessage());
            }
        }

        public final void runImmediateBackup(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(AutoBackupWorker.class).build();
                WorkManager.Companion.getInstance(context).enqueueUniqueWork(AutoBackupWorker.UNIQUE_ONETIME_WORK_NAME, ExistingWorkPolicy.REPLACE, workRequest);
                Log.i(AutoBackupWorker.TAG, "Immediate AutoBackup work enqueued");
            } catch (Throwable e) {
                Log.w(AutoBackupWorker.TAG, "Failed to trigger immediate AutoBackup: " + e.getMessage());
            }
        }
    }
}
