package com.example.ui.screens;

import com.example.BuildConfig;
import com.example.data.model.CustomerJobEntity;
import com.example.data.model.ExpertCategoryEntity;
import com.example.data.model.ExpertEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeScreen.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/example/ui/screens/DeleteTarget;", "", "<init>", "()V", "Job", "Expert", "Category", "Lcom/example/ui/screens/DeleteTarget$Category;", "Lcom/example/ui/screens/DeleteTarget$Expert;", "Lcom/example/ui/screens/DeleteTarget$Job;", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes4.dex */
public abstract class DeleteTarget {
    public static final int $stable = 0;

    public /* synthetic */ DeleteTarget(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: HomeScreen.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ui/screens/DeleteTarget$Job;", "Lcom/example/ui/screens/DeleteTarget;", "job", "Lcom/example/data/model/CustomerJobEntity;", "<init>", "(Lcom/example/data/model/CustomerJobEntity;)V", "getJob", "()Lcom/example/data/model/CustomerJobEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes4.dex */
    public static final /* data */ class Job extends DeleteTarget {
        public static final int $stable = 0;
        private final CustomerJobEntity job;

        public static /* synthetic */ Job copy$default(Job job, CustomerJobEntity customerJobEntity, int i, Object obj) {
            if ((i & 1) != 0) {
                customerJobEntity = job.job;
            }
            return job.copy(customerJobEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final CustomerJobEntity getJob() {
            return this.job;
        }

        public final Job copy(CustomerJobEntity job) {
            Intrinsics.checkNotNullParameter(job, "job");
            return new Job(job);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Job) && Intrinsics.areEqual(this.job, ((Job) other).job);
        }

        public int hashCode() {
            return this.job.hashCode();
        }

        public String toString() {
            return "Job(job=" + this.job + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Job(CustomerJobEntity job) {
            super(null);
            Intrinsics.checkNotNullParameter(job, "job");
            this.job = job;
        }

        public final CustomerJobEntity getJob() {
            return this.job;
        }
    }

    private DeleteTarget() {
    }

    /* compiled from: HomeScreen.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ui/screens/DeleteTarget$Expert;", "Lcom/example/ui/screens/DeleteTarget;", "expert", "Lcom/example/data/model/ExpertEntity;", "<init>", "(Lcom/example/data/model/ExpertEntity;)V", "getExpert", "()Lcom/example/data/model/ExpertEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes4.dex */
    public static final /* data */ class Expert extends DeleteTarget {
        public static final int $stable = 0;
        private final ExpertEntity expert;

        public static /* synthetic */ Expert copy$default(Expert expert, ExpertEntity expertEntity, int i, Object obj) {
            if ((i & 1) != 0) {
                expertEntity = expert.expert;
            }
            return expert.copy(expertEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final ExpertEntity getExpert() {
            return this.expert;
        }

        public final Expert copy(ExpertEntity expert) {
            Intrinsics.checkNotNullParameter(expert, "expert");
            return new Expert(expert);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Expert) && Intrinsics.areEqual(this.expert, ((Expert) other).expert);
        }

        public int hashCode() {
            return this.expert.hashCode();
        }

        public String toString() {
            return "Expert(expert=" + this.expert + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Expert(ExpertEntity expert) {
            super(null);
            Intrinsics.checkNotNullParameter(expert, "expert");
            this.expert = expert;
        }

        public final ExpertEntity getExpert() {
            return this.expert;
        }
    }

    /* compiled from: HomeScreen.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/ui/screens/DeleteTarget$Category;", "Lcom/example/ui/screens/DeleteTarget;", "category", "Lcom/example/data/model/ExpertCategoryEntity;", "<init>", "(Lcom/example/data/model/ExpertCategoryEntity;)V", "getCategory", "()Lcom/example/data/model/ExpertCategoryEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes4.dex */
    public static final /* data */ class Category extends DeleteTarget {
        public static final int $stable = 0;
        private final ExpertCategoryEntity category;

        public static /* synthetic */ Category copy$default(Category category, ExpertCategoryEntity expertCategoryEntity, int i, Object obj) {
            if ((i & 1) != 0) {
                expertCategoryEntity = category.category;
            }
            return category.copy(expertCategoryEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final ExpertCategoryEntity getCategory() {
            return this.category;
        }

        public final Category copy(ExpertCategoryEntity category) {
            Intrinsics.checkNotNullParameter(category, "category");
            return new Category(category);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Category) && Intrinsics.areEqual(this.category, ((Category) other).category);
        }

        public int hashCode() {
            return this.category.hashCode();
        }

        public String toString() {
            return "Category(category=" + this.category + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Category(ExpertCategoryEntity category) {
            super(null);
            Intrinsics.checkNotNullParameter(category, "category");
            this.category = category;
        }

        public final ExpertCategoryEntity getCategory() {
            return this.category;
        }
    }
}
