package com.example.ui;

import com.example.BuildConfig;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* compiled from: DispatchViewModel.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/example/ui/OrderStatusTab;", "", "statusName", "", "label", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getStatusName", "()Ljava/lang/String;", "getLabel", "PENDING", "PROCESSING", "COMPLETED", "CANCELLED", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public enum OrderStatusTab {
    PENDING("PENDING", "Pending"),
    PROCESSING("PROCESSING", "Processing"),
    COMPLETED("COMPLETED", "Completed"),
    CANCELLED("CANCELLED", "Cancelled");

    private final String label;
    private final String statusName;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<OrderStatusTab> getEntries() {
        return $ENTRIES;
    }

    OrderStatusTab(String statusName, String label) {
        this.statusName = statusName;
        this.label = label;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getStatusName() {
        return this.statusName;
    }
}
