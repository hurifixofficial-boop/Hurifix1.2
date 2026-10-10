package com.example.ui;

import com.example.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DispatchViewModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\fHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003Jc\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020\f2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011¨\u0006+"}, d2 = {"Lcom/example/ui/CustomerFormState;", "", "name", "", "phone", "serviceType", "issueDescription", "address", "latitude", "", "longitude", "hasValidLocation", "", "rawLocationInput", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDZLjava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPhone", "getServiceType", "getIssueDescription", "getAddress", "getLatitude", "()D", "getLongitude", "getHasValidLocation", "()Z", "getRawLocationInput", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final /* data */ class CustomerFormState {
    public static final int $stable = 0;
    private final String address;
    private final boolean hasValidLocation;
    private final String issueDescription;
    private final double latitude;
    private final double longitude;
    private final String name;
    private final String phone;
    private final String rawLocationInput;
    private final String serviceType;

    public CustomerFormState() {
        this(null, null, null, null, null, 0.0d, 0.0d, false, null, 511, null);
    }

    public static /* synthetic */ CustomerFormState copy$default(CustomerFormState customerFormState, String str, String str2, String str3, String str4, String str5, double d, double d2, boolean z, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = customerFormState.name;
        }
        if ((i & 2) != 0) {
            str2 = customerFormState.phone;
        }
        if ((i & 4) != 0) {
            str3 = customerFormState.serviceType;
        }
        if ((i & 8) != 0) {
            str4 = customerFormState.issueDescription;
        }
        if ((i & 16) != 0) {
            str5 = customerFormState.address;
        }
        if ((i & 32) != 0) {
            d = customerFormState.latitude;
        }
        if ((i & 64) != 0) {
            d2 = customerFormState.longitude;
        }
        if ((i & 128) != 0) {
            z = customerFormState.hasValidLocation;
        }
        if ((i & 256) != 0) {
            str6 = customerFormState.rawLocationInput;
        }
        double d3 = d2;
        double d4 = d;
        String str7 = str4;
        String str8 = str5;
        String str9 = str3;
        return customerFormState.copy(str, str2, str9, str7, str8, d4, d3, z, str6);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* renamed from: component3, reason: from getter */
    public final String getServiceType() {
        return this.serviceType;
    }

    /* renamed from: component4, reason: from getter */
    public final String getIssueDescription() {
        return this.issueDescription;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component6, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component7, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getHasValidLocation() {
        return this.hasValidLocation;
    }

    /* renamed from: component9, reason: from getter */
    public final String getRawLocationInput() {
        return this.rawLocationInput;
    }

    public final CustomerFormState copy(String name, String phone, String serviceType, String issueDescription, String address, double latitude, double longitude, boolean hasValidLocation, String rawLocationInput) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(issueDescription, "issueDescription");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(rawLocationInput, "rawLocationInput");
        return new CustomerFormState(name, phone, serviceType, issueDescription, address, latitude, longitude, hasValidLocation, rawLocationInput);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerFormState)) {
            return false;
        }
        CustomerFormState customerFormState = (CustomerFormState) other;
        return Intrinsics.areEqual(this.name, customerFormState.name) && Intrinsics.areEqual(this.phone, customerFormState.phone) && Intrinsics.areEqual(this.serviceType, customerFormState.serviceType) && Intrinsics.areEqual(this.issueDescription, customerFormState.issueDescription) && Intrinsics.areEqual(this.address, customerFormState.address) && Double.compare(this.latitude, customerFormState.latitude) == 0 && Double.compare(this.longitude, customerFormState.longitude) == 0 && this.hasValidLocation == customerFormState.hasValidLocation && Intrinsics.areEqual(this.rawLocationInput, customerFormState.rawLocationInput);
    }

    public int hashCode() {
        return (((((((((((((((this.name.hashCode() * 31) + this.phone.hashCode()) * 31) + this.serviceType.hashCode()) * 31) + this.issueDescription.hashCode()) * 31) + this.address.hashCode()) * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude)) * 31) + Boolean.hashCode(this.hasValidLocation)) * 31) + this.rawLocationInput.hashCode();
    }

    public String toString() {
        return "CustomerFormState(name=" + this.name + ", phone=" + this.phone + ", serviceType=" + this.serviceType + ", issueDescription=" + this.issueDescription + ", address=" + this.address + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", hasValidLocation=" + this.hasValidLocation + ", rawLocationInput=" + this.rawLocationInput + ")";
    }

    public CustomerFormState(String name, String phone, String serviceType, String issueDescription, String address, double latitude, double longitude, boolean hasValidLocation, String rawLocationInput) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(issueDescription, "issueDescription");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(rawLocationInput, "rawLocationInput");
        this.name = name;
        this.phone = phone;
        this.serviceType = serviceType;
        this.issueDescription = issueDescription;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hasValidLocation = hasValidLocation;
        this.rawLocationInput = rawLocationInput;
    }

    public /* synthetic */ CustomerFormState(String str, String str2, String str3, String str4, String str5, double d, double d2, boolean z, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? 0.0d : d, (i & 64) == 0 ? d2 : 0.0d, (i & 128) != 0 ? true : z, (i & 256) == 0 ? str6 : "");
    }

    public final String getName() {
        return this.name;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getServiceType() {
        return this.serviceType;
    }

    public final String getIssueDescription() {
        return this.issueDescription;
    }

    public final String getAddress() {
        return this.address;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final boolean getHasValidLocation() {
        return this.hasValidLocation;
    }

    public final String getRawLocationInput() {
        return this.rawLocationInput;
    }
}
