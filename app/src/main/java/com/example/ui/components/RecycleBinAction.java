package com.example.ui.components;

import com.example.BuildConfig;
import com.example.data.model.CustomerJobEntity;
import com.example.data.model.ExpertEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RecycleBinDialog.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/example/ui/components/RecycleBinAction;", "", "<init>", "()V", "RestoreJob", "DeleteJobForever", "RestoreExpert", "DeleteExpertForever", "Lcom/example/ui/components/RecycleBinAction$DeleteExpertForever;", "Lcom/example/ui/components/RecycleBinAction$DeleteJobForever;", "Lcom/example/ui/components/RecycleBinAction$RestoreExpert;", "Lcom/example/ui/components/RecycleBinAction$RestoreJob;", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public abstract class RecycleBinAction {
    public /* synthetic */ RecycleBinAction(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: RecycleBinDialog.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ui/components/RecycleBinAction$RestoreJob;", "Lcom/example/ui/components/RecycleBinAction;", "job", "Lcom/example/data/model/CustomerJobEntity;", "<init>", "(Lcom/example/data/model/CustomerJobEntity;)V", "getJob", "()Lcom/example/data/model/CustomerJobEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes8.dex */
    public static final /* data */ class RestoreJob extends RecycleBinAction {
        public static final int $stable = 0;
        private final CustomerJobEntity job;

        public static /* synthetic */ RestoreJob copy$default(RestoreJob restoreJob, CustomerJobEntity customerJobEntity, int i, Object obj) {
            if ((i & 1) != 0) {
                customerJobEntity = restoreJob.job;
            }
            return restoreJob.copy(customerJobEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final CustomerJobEntity getJob() {
            return this.job;
        }

        public final RestoreJob copy(CustomerJobEntity job) {
            Intrinsics.checkNotNullParameter(job, "job");
            return new RestoreJob(job);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof RestoreJob) && Intrinsics.areEqual(this.job, ((RestoreJob) other).job);
        }

        public int hashCode() {
            return this.job.hashCode();
        }

        public String toString() {
            return "RestoreJob(job=" + this.job + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RestoreJob(CustomerJobEntity job) {
            super(null);
            Intrinsics.checkNotNullParameter(job, "job");
            this.job = job;
        }

        public final CustomerJobEntity getJob() {
            return this.job;
        }
    }

    private RecycleBinAction() {
    }

    /* compiled from: RecycleBinDialog.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ui/components/RecycleBinAction$DeleteJobForever;", "Lcom/example/ui/components/RecycleBinAction;", "job", "Lcom/example/data/model/CustomerJobEntity;", "<init>", "(Lcom/example/data/model/CustomerJobEntity;)V", "getJob", "()Lcom/example/data/model/CustomerJobEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes8.dex */
    public static final /* data */ class DeleteJobForever extends RecycleBinAction {
        public static final int $stable = 0;
        private final CustomerJobEntity job;

        public static /* synthetic */ DeleteJobForever copy$default(DeleteJobForever deleteJobForever, CustomerJobEntity customerJobEntity, int i, Object obj) {
            if ((i & 1) != 0) {
                customerJobEntity = deleteJobForever.job;
            }
            return deleteJobForever.copy(customerJobEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final CustomerJobEntity getJob() {
            return this.job;
        }

        public final DeleteJobForever copy(CustomerJobEntity job) {
            Intrinsics.checkNotNullParameter(job, "job");
            return new DeleteJobForever(job);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DeleteJobForever) && Intrinsics.areEqual(this.job, ((DeleteJobForever) other).job);
        }

        public int hashCode() {
            return this.job.hashCode();
        }

        public String toString() {
            return "DeleteJobForever(job=" + this.job + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DeleteJobForever(CustomerJobEntity job) {
            super(null);
            Intrinsics.checkNotNullParameter(job, "job");
            this.job = job;
        }

        public final CustomerJobEntity getJob() {
            return this.job;
        }
    }

    /* compiled from: RecycleBinDialog.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ui/components/RecycleBinAction$RestoreExpert;", "Lcom/example/ui/components/RecycleBinAction;", "expert", "Lcom/example/data/model/ExpertEntity;", "<init>", "(Lcom/example/data/model/ExpertEntity;)V", "getExpert", "()Lcom/example/data/model/ExpertEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes8.dex */
    public static final /* data */ class RestoreExpert extends RecycleBinAction {
        public static final int $stable = 0;
        private final ExpertEntity expert;

        public static /* synthetic */ RestoreExpert copy$default(RestoreExpert restoreExpert, ExpertEntity expertEntity, int i, Object obj) {
            if ((i & 1) != 0) {
                expertEntity = restoreExpert.expert;
            }
            return restoreExpert.copy(expertEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final ExpertEntity getExpert() {
            return this.expert;
        }

        public final RestoreExpert copy(ExpertEntity expert) {
            Intrinsics.checkNotNullParameter(expert, "expert");
            return new RestoreExpert(expert);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof RestoreExpert) && Intrinsics.areEqual(this.expert, ((RestoreExpert) other).expert);
        }

        public int hashCode() {
            return this.expert.hashCode();
        }

        public String toString() {
            return "RestoreExpert(expert=" + this.expert + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RestoreExpert(ExpertEntity expert) {
            super(null);
            Intrinsics.checkNotNullParameter(expert, "expert");
            this.expert = expert;
        }

        public final ExpertEntity getExpert() {
            return this.expert;
        }
    }

    /* compiled from: RecycleBinDialog.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ui/components/RecycleBinAction$DeleteExpertForever;", "Lcom/example/ui/components/RecycleBinAction;", "expert", "Lcom/example/data/model/ExpertEntity;", "<init>", "(Lcom/example/data/model/ExpertEntity;)V", "getExpert", "()Lcom/example/data/model/ExpertEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes8.dex */
    public static final /* data */ class DeleteExpertForever extends RecycleBinAction {
        public static final int $stable = 0;
        private final ExpertEntity expert;

        public static /* synthetic */ DeleteExpertForever copy$default(DeleteExpertForever deleteExpertForever, ExpertEntity expertEntity, int i, Object obj) {
            if ((i & 1) != 0) {
                expertEntity = deleteExpertForever.expert;
            }
            return deleteExpertForever.copy(expertEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final ExpertEntity getExpert() {
            return this.expert;
        }

        public final DeleteExpertForever copy(ExpertEntity expert) {
            Intrinsics.checkNotNullParameter(expert, "expert");
            return new DeleteExpertForever(expert);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DeleteExpertForever) && Intrinsics.areEqual(this.expert, ((DeleteExpertForever) other).expert);
        }

        public int hashCode() {
            return this.expert.hashCode();
        }

        public String toString() {
            return "DeleteExpertForever(expert=" + this.expert + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DeleteExpertForever(ExpertEntity expert) {
            super(null);
            Intrinsics.checkNotNullParameter(expert, "expert");
            this.expert = expert;
        }

        public final ExpertEntity getExpert() {
            return this.expert;
        }
    }
}
