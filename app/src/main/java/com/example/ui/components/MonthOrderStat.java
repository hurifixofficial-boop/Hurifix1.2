package com.example.ui.components;

import com.example.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MonthlyAnalyticsDialog.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0006HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011¨\u0006#"}, d2 = {"Lcom/example/ui/components/MonthOrderStat;", "", "monthYearKey", "", "displayMonth", "totalOrders", "", "completedOrders", "cancelledOrders", "processingOrders", "pendingOrders", "<init>", "(Ljava/lang/String;Ljava/lang/String;IIIII)V", "getMonthYearKey", "()Ljava/lang/String;", "getDisplayMonth", "getTotalOrders", "()I", "getCompletedOrders", "getCancelledOrders", "getProcessingOrders", "getPendingOrders", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final /* data */ class MonthOrderStat {
    public static final int $stable = 0;
    private final int cancelledOrders;
    private final int completedOrders;
    private final String displayMonth;
    private final String monthYearKey;
    private final int pendingOrders;
    private final int processingOrders;
    private final int totalOrders;

    public static /* synthetic */ MonthOrderStat copy$default(MonthOrderStat monthOrderStat, String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            str = monthOrderStat.monthYearKey;
        }
        if ((i6 & 2) != 0) {
            str2 = monthOrderStat.displayMonth;
        }
        if ((i6 & 4) != 0) {
            i = monthOrderStat.totalOrders;
        }
        if ((i6 & 8) != 0) {
            i2 = monthOrderStat.completedOrders;
        }
        if ((i6 & 16) != 0) {
            i3 = monthOrderStat.cancelledOrders;
        }
        if ((i6 & 32) != 0) {
            i4 = monthOrderStat.processingOrders;
        }
        if ((i6 & 64) != 0) {
            i5 = monthOrderStat.pendingOrders;
        }
        int i7 = i4;
        int i8 = i5;
        int i9 = i3;
        int i10 = i;
        return monthOrderStat.copy(str, str2, i10, i2, i9, i7, i8);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMonthYearKey() {
        return this.monthYearKey;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDisplayMonth() {
        return this.displayMonth;
    }

    /* renamed from: component3, reason: from getter */
    public final int getTotalOrders() {
        return this.totalOrders;
    }

    /* renamed from: component4, reason: from getter */
    public final int getCompletedOrders() {
        return this.completedOrders;
    }

    /* renamed from: component5, reason: from getter */
    public final int getCancelledOrders() {
        return this.cancelledOrders;
    }

    /* renamed from: component6, reason: from getter */
    public final int getProcessingOrders() {
        return this.processingOrders;
    }

    /* renamed from: component7, reason: from getter */
    public final int getPendingOrders() {
        return this.pendingOrders;
    }

    public final MonthOrderStat copy(String monthYearKey, String displayMonth, int totalOrders, int completedOrders, int cancelledOrders, int processingOrders, int pendingOrders) {
        Intrinsics.checkNotNullParameter(monthYearKey, "monthYearKey");
        Intrinsics.checkNotNullParameter(displayMonth, "displayMonth");
        return new MonthOrderStat(monthYearKey, displayMonth, totalOrders, completedOrders, cancelledOrders, processingOrders, pendingOrders);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MonthOrderStat)) {
            return false;
        }
        MonthOrderStat monthOrderStat = (MonthOrderStat) other;
        return Intrinsics.areEqual(this.monthYearKey, monthOrderStat.monthYearKey) && Intrinsics.areEqual(this.displayMonth, monthOrderStat.displayMonth) && this.totalOrders == monthOrderStat.totalOrders && this.completedOrders == monthOrderStat.completedOrders && this.cancelledOrders == monthOrderStat.cancelledOrders && this.processingOrders == monthOrderStat.processingOrders && this.pendingOrders == monthOrderStat.pendingOrders;
    }

    public int hashCode() {
        return (((((((((((this.monthYearKey.hashCode() * 31) + this.displayMonth.hashCode()) * 31) + Integer.hashCode(this.totalOrders)) * 31) + Integer.hashCode(this.completedOrders)) * 31) + Integer.hashCode(this.cancelledOrders)) * 31) + Integer.hashCode(this.processingOrders)) * 31) + Integer.hashCode(this.pendingOrders);
    }

    public String toString() {
        return "MonthOrderStat(monthYearKey=" + this.monthYearKey + ", displayMonth=" + this.displayMonth + ", totalOrders=" + this.totalOrders + ", completedOrders=" + this.completedOrders + ", cancelledOrders=" + this.cancelledOrders + ", processingOrders=" + this.processingOrders + ", pendingOrders=" + this.pendingOrders + ")";
    }

    public MonthOrderStat(String monthYearKey, String displayMonth, int totalOrders, int completedOrders, int cancelledOrders, int processingOrders, int pendingOrders) {
        Intrinsics.checkNotNullParameter(monthYearKey, "monthYearKey");
        Intrinsics.checkNotNullParameter(displayMonth, "displayMonth");
        this.monthYearKey = monthYearKey;
        this.displayMonth = displayMonth;
        this.totalOrders = totalOrders;
        this.completedOrders = completedOrders;
        this.cancelledOrders = cancelledOrders;
        this.processingOrders = processingOrders;
        this.pendingOrders = pendingOrders;
    }

    public final String getMonthYearKey() {
        return this.monthYearKey;
    }

    public final String getDisplayMonth() {
        return this.displayMonth;
    }

    public final int getTotalOrders() {
        return this.totalOrders;
    }

    public final int getCompletedOrders() {
        return this.completedOrders;
    }

    public final int getCancelledOrders() {
        return this.cancelledOrders;
    }

    public final int getProcessingOrders() {
        return this.processingOrders;
    }

    public final int getPendingOrders() {
        return this.pendingOrders;
    }
}
