package com.example.ui.screens;

import com.example.BuildConfig;
import com.example.data.model.CustomerJobEntity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeScreen.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/example/ui/screens/OrderCollisionTarget;", "", "job", "Lcom/example/data/model/CustomerJobEntity;", "managerName", "", "managerDesignation", "onProceed", "Lkotlin/Function0;", "", "<init>", "(Lcom/example/data/model/CustomerJobEntity;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "getJob", "()Lcom/example/data/model/CustomerJobEntity;", "getManagerName", "()Ljava/lang/String;", "getManagerDesignation", "getOnProceed", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes4.dex */
public final /* data */ class OrderCollisionTarget {
    public static final int $stable = 0;
    private final CustomerJobEntity job;
    private final String managerDesignation;
    private final String managerName;
    private final Function0<Unit> onProceed;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ OrderCollisionTarget copy$default(OrderCollisionTarget orderCollisionTarget, CustomerJobEntity customerJobEntity, String str, String str2, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            customerJobEntity = orderCollisionTarget.job;
        }
        if ((i & 2) != 0) {
            str = orderCollisionTarget.managerName;
        }
        if ((i & 4) != 0) {
            str2 = orderCollisionTarget.managerDesignation;
        }
        if ((i & 8) != 0) {
            function0 = orderCollisionTarget.onProceed;
        }
        return orderCollisionTarget.copy(customerJobEntity, str, str2, function0);
    }

    /* renamed from: component1, reason: from getter */
    public final CustomerJobEntity getJob() {
        return this.job;
    }

    /* renamed from: component2, reason: from getter */
    public final String getManagerName() {
        return this.managerName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getManagerDesignation() {
        return this.managerDesignation;
    }

    public final Function0<Unit> component4() {
        return this.onProceed;
    }

    public final OrderCollisionTarget copy(CustomerJobEntity job, String managerName, String managerDesignation, Function0<Unit> onProceed) {
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(managerName, "managerName");
        Intrinsics.checkNotNullParameter(managerDesignation, "managerDesignation");
        Intrinsics.checkNotNullParameter(onProceed, "onProceed");
        return new OrderCollisionTarget(job, managerName, managerDesignation, onProceed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrderCollisionTarget)) {
            return false;
        }
        OrderCollisionTarget orderCollisionTarget = (OrderCollisionTarget) other;
        return Intrinsics.areEqual(this.job, orderCollisionTarget.job) && Intrinsics.areEqual(this.managerName, orderCollisionTarget.managerName) && Intrinsics.areEqual(this.managerDesignation, orderCollisionTarget.managerDesignation) && Intrinsics.areEqual(this.onProceed, orderCollisionTarget.onProceed);
    }

    public int hashCode() {
        return (((((this.job.hashCode() * 31) + this.managerName.hashCode()) * 31) + this.managerDesignation.hashCode()) * 31) + this.onProceed.hashCode();
    }

    public String toString() {
        return "OrderCollisionTarget(job=" + this.job + ", managerName=" + this.managerName + ", managerDesignation=" + this.managerDesignation + ", onProceed=" + this.onProceed + ")";
    }

    public OrderCollisionTarget(CustomerJobEntity job, String managerName, String managerDesignation, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(managerName, "managerName");
        Intrinsics.checkNotNullParameter(managerDesignation, "managerDesignation");
        Intrinsics.checkNotNullParameter(function0, "onProceed");
        this.job = job;
        this.managerName = managerName;
        this.managerDesignation = managerDesignation;
        this.onProceed = function0;
    }

    public final CustomerJobEntity getJob() {
        return this.job;
    }

    public final String getManagerName() {
        return this.managerName;
    }

    public final String getManagerDesignation() {
        return this.managerDesignation;
    }

    public final Function0<Unit> getOnProceed() {
        return this.onProceed;
    }
}
