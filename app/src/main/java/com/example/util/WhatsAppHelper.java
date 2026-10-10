package com.example.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.example.BuildConfig;
import com.example.data.model.CustomerJobEntity;
import com.example.data.model.ExpertEntity;
import java.net.URLEncoder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: WhatsAppHelper.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ.\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005J\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u0016\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005J\u001e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0016\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0006\u001a\u00020\u0005J\u0016\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005J(\u0010 \u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\u0005J\u000e\u0010$\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J\u001e\u0010%\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0005¨\u0006'"}, d2 = {"Lcom/example/util/WhatsAppHelper;", "", "<init>", "()V", "formatPhoneNumberForWhatsApp", "", "phone", "calculateEstimatedArrivalTimeWithBuffer", "distanceKm", "", "createCustomerAssignmentNotificationMessage", "customerName", "expertName", "expertPhone", "serviceType", "estimatedTimeText", "createCompletionCustomerMessage", "createDispatchMessage", "expert", "Lcom/example/data/model/ExpertEntity;", "customer", "Lcom/example/data/model/CustomerJobEntity;", "sendWhatsAppDirectMessage", "", "context", "Landroid/content/Context;", "phoneNumber", "message", "sendWhatsAppMessageToExpert", "sanitizePhoneNumberForDialer", "openDialer", "openWhatsAppChatWithoutMessage", "openGoogleMaps", "latitude", "longitude", "label", "createNewExpertWelcomeMessage", "copyToClipboard", "text", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes6.dex */
public final class WhatsAppHelper {
    public static final int $stable = 0;
    public static final WhatsAppHelper INSTANCE = new WhatsAppHelper();

    private WhatsAppHelper() {
    }

    public final String formatPhoneNumberForWhatsApp(String phone) {
        Intrinsics.checkNotNullParameter(phone, "phone");
        String digitsOnly = new Regex("[^0-9]").replace(phone, "");
        if (digitsOnly.length() == 10) {
            return "91" + digitsOnly;
        }
        if (digitsOnly.length() == 11 && StringsKt.startsWith$default(digitsOnly, "0", false, 2, (Object) null)) {
            String substring = digitsOnly.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return "91" + substring;
        }
        if (digitsOnly.length() != 12 || StringsKt.startsWith$default(digitsOnly, "91", false, 2, (Object) null)) {
        }
        return digitsOnly;
    }

    public final String calculateEstimatedArrivalTimeWithBuffer(double distanceKm) {
        int baseTravelMinutes = LocationHelper.INSTANCE.estimateTravelTimeMinutes(distanceKm);
        int totalWithBuffer = baseTravelMinutes + 30;
        if (totalWithBuffer >= 60) {
            int hours = totalWithBuffer / 60;
            int mins = totalWithBuffer % 60;
            return mins == 0 ? hours + " Hour (Approx)" : hours + " Hr " + mins + " Mins (Approx)";
        }
        return totalWithBuffer + " Minutes (Approx)";
    }

    public final String createCustomerAssignmentNotificationMessage(String customerName, String expertName, String expertPhone, String serviceType, String estimatedTimeText) {
        Intrinsics.checkNotNullParameter(customerName, "customerName");
        Intrinsics.checkNotNullParameter(expertName, "expertName");
        Intrinsics.checkNotNullParameter(expertPhone, "expertPhone");
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(estimatedTimeText, "estimatedTimeText");
        String obj = StringsKt.trim(customerName).toString();
        if (StringsKt.isBlank(obj)) {
            obj = "Customer";
        }
        String displayName = obj;
        return StringsKt.trimIndent("\n🛠 *HURIFIX SERVICE UPDATE* 🛠\n━━━━━━━━━━━━━━━━━━━━\nDear *" + displayName + "* ✨,\n\nAn expert technician has been assigned to your service request:\n\n👨\u200d🔧 *Expert Name:* " + expertName + "\n📞 *Contact Number:* " + expertPhone + "\n⏳ *Estimated Time of Arrival:* " + estimatedTimeText + "\n🛠 *Service Required:* " + serviceType + "\n\nOur technician will arrive at your address shortly. Please keep your phone reachable.\n━━━━━━━━━━━━━━━━━━━━\n- *Team Hurifix*\n_Many Problems | One Solution_\n        ");
    }

    public final String createCompletionCustomerMessage(String customerName) {
        Intrinsics.checkNotNullParameter(customerName, "customerName");
        String obj = StringsKt.trim(customerName).toString();
        if (StringsKt.isBlank(obj)) {
            obj = "Customer";
        }
        String displayName = obj;
        return StringsKt.trimIndent("\nDear " + displayName + " ✨,\n\nYour work has been successfully completed! 🎉 We hope we met your expectations and provided a great experience. 💯\n\nOne of our representatives will call you shortly for your valuable feedback—please do share your experience with us! 📞\n\nIf you have any questions or need assistance, feel free to reach out to us anytime. 📞💬\n\nStay Connected! 🚀\nFollow us on Instagram for future updates, offers, and exclusive services:\n👇\nhttps://www.instagram.com/hurifix_official?stkn=MzUzMW9xOTh2eTJ4\n\nThank you for choosing us! Have a great day ahead! 😊🙏\n        ");
    }

    public final String createDispatchMessage(ExpertEntity expert, CustomerJobEntity customer) {
        Intrinsics.checkNotNullParameter(expert, "expert");
        Intrinsics.checkNotNullParameter(customer, "customer");
        String mapsUrl = LocationHelper.INSTANCE.createGoogleMapsUrl(customer.getLatitude(), customer.getLongitude());
        return StringsKt.trimIndent("\n🔧 *HURIFIX DISPATCH ORDER* 🔧\n━━━━━━━━━━━━━━━━━━━━\nHello *" + expert.getName() + "*, a new service task has been dispatched to you:\n\n👤 *Customer Name:* " + customer.getCustomerName() + "\n📞 *Customer Phone:* " + customer.getCustomerPhone() + "\n🛠 *Service Required:* " + customer.getServiceType() + "\n📝 *Problem Description:* " + customer.getIssueDescription() + "\n📍 *Address:* " + customer.getAddress() + "\n\n🗺 *Google Maps Location:*\n" + mapsUrl + "\n━━━━━━━━━━━━━━━━━━━━\n- *Hurifix Operations Team*\n        ");
    }

    public final void sendWhatsAppDirectMessage(Context context, String phoneNumber, String message) {
        String encodedMessage;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        Intrinsics.checkNotNullParameter(message, "message");
        String formattedNumber = formatPhoneNumberForWhatsApp(phoneNumber);
        try {
            encodedMessage = URLEncoder.encode(message, "UTF-8");
        } catch (Exception e) {
            encodedMessage = message;
        }
        String url = "https://api.whatsapp.com/send?phone=" + formattedNumber + "&text=" + encodedMessage;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (Exception e2) {
            try {
                Intent fallbackIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                fallbackIntent.addFlags(268435456);
                context.startActivity(fallbackIntent);
            } catch (Exception e3) {
                Toast.makeText(context, "WhatsApp is not installed. Text copied to clipboard!", 1).show();
                copyToClipboard(context, "Hurifix Message", message);
            }
        }
    }

    public final void sendWhatsAppMessageToExpert(Context context, ExpertEntity expert, CustomerJobEntity customer) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(expert, "expert");
        Intrinsics.checkNotNullParameter(customer, "customer");
        String message = createDispatchMessage(expert, customer);
        sendWhatsAppDirectMessage(context, expert.getPhone(), message);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String sanitizePhoneNumberForDialer(java.lang.String r12) {
        /*
            r11 = this;
            java.lang.String r0 = "phone"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            r0 = r12
            r1 = 0
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.Appendable r3 = (java.lang.Appendable) r3
            r4 = 0
            r5 = 0
            int r6 = r2.length()
        L17:
            if (r5 >= r6) goto L2b
            char r7 = r2.charAt(r5)
            r8 = r7
            r9 = 0
            boolean r10 = java.lang.Character.isDigit(r8)
            if (r10 == 0) goto L28
            r3.append(r7)
        L28:
            int r5 = r5 + 1
            goto L17
        L2b:
            r2 = r3
            java.lang.StringBuilder r2 = (java.lang.StringBuilder) r2
            java.lang.String r0 = r2.toString()
            int r1 = r0.length()
            java.lang.String r2 = "91"
            r3 = 10
            r4 = 0
            r5 = 0
            r6 = 2
            if (r1 != r3) goto L43
            goto L75
        L43:
            int r1 = r0.length()
            r7 = 12
            java.lang.String r8 = "substring(...)"
            if (r1 != r7) goto L5b
            boolean r1 = kotlin.text.StringsKt.startsWith$default(r0, r2, r5, r6, r4)
            if (r1 == 0) goto L5b
            java.lang.String r1 = r0.substring(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            goto L76
        L5b:
            int r1 = r0.length()
            r7 = 11
            if (r1 != r7) goto L74
            java.lang.String r1 = "0"
            boolean r1 = kotlin.text.StringsKt.startsWith$default(r0, r1, r5, r6, r4)
            if (r1 == 0) goto L74
            r1 = 1
            java.lang.String r1 = r0.substring(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            goto L76
        L74:
        L75:
            r1 = r0
        L76:
            int r7 = r1.length()
            java.lang.String r8 = "+91"
            if (r7 != r3) goto L91
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r8)
            java.lang.StringBuilder r2 = r2.append(r1)
        L8c:
            java.lang.String r2 = r2.toString()
            goto Lb1
        L91:
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r0, r2, r5, r6, r4)
            if (r2 == 0) goto La3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "+"
            java.lang.StringBuilder r2 = r2.append(r3)
            goto Lac
        La3:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r8)
        Lac:
            java.lang.StringBuilder r2 = r2.append(r0)
            goto L8c
        Lb1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.util.WhatsAppHelper.sanitizePhoneNumberForDialer(java.lang.String):java.lang.String");
    }

    public final void openDialer(Context context, String phone) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(phone, "phone");
        String finalDialerUri = sanitizePhoneNumberForDialer(phone);
        String str = phone;
        Appendable sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isDigit(charAt)) {
                sb.append(charAt);
            }
        }
        String sb2 = ((StringBuilder) sb).toString();
        String clean10 = sb2.length() == 10 ? sb2 : StringsKt.takeLast(sb2, 10);
        try {
            Object systemService = context.getSystemService("clipboard");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
            ClipboardManager clipboard = (ClipboardManager) systemService;
            ClipData clip = ClipData.newPlainText("Phone Number", clean10);
            clipboard.setPrimaryClip(clip);
        } catch (Exception e) {
        }
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + finalDialerUri));
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (Exception e2) {
            Toast.makeText(context, "Could not open dialer: " + e2.getLocalizedMessage(), 0).show();
        }
    }

    public final void openWhatsAppChatWithoutMessage(Context context, String phoneNumber) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        String formattedNumber = formatPhoneNumberForWhatsApp(phoneNumber);
        String url = "https://api.whatsapp.com/send?phone=" + formattedNumber;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            try {
                Intent fallbackIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://wa.me/" + formattedNumber));
                fallbackIntent.addFlags(268435456);
                context.startActivity(fallbackIntent);
            } catch (Exception e2) {
                Toast.makeText(context, "Could not open WhatsApp: " + e2.getLocalizedMessage(), 0).show();
            }
        }
    }

    public static /* synthetic */ void openGoogleMaps$default(WhatsAppHelper whatsAppHelper, Context context, double d, double d2, String str, int i, Object obj) {
        if ((i & 8) != 0) {
            str = "Location";
        }
        whatsAppHelper.openGoogleMaps(context, d, d2, str);
    }

    public final void openGoogleMaps(Context context, double latitude, double longitude, String label) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(label, "label");
        Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + label + ")");
        Intent mapIntent = new Intent("android.intent.action.VIEW", gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        mapIntent.addFlags(268435456);
        try {
            context.startActivity(mapIntent);
        } catch (Exception e) {
            Intent webIntent = new Intent("android.intent.action.VIEW", Uri.parse(LocationHelper.INSTANCE.createGoogleMapsUrl(latitude, longitude)));
            webIntent.addFlags(268435456);
            context.startActivity(webIntent);
        }
    }

    public final String createNewExpertWelcomeMessage(String expertName) {
        Intrinsics.checkNotNullParameter(expertName, "expertName");
        return StringsKt.trimIndent("\nHello " + expertName + ", 👋\nWelcome to Hurifix! 🎉\nAap ab hamare Official Business Partner ban chuke hain. Hum aapke sath kaam karne ke liye bohot excited hain! 🛠️🚀\nHum aapko jald hi WhatsApp par ek message bhejenge jisme aapko hamara poora work process aur guidelines achhe se samjha di jayengi. 📲\nHurifix family se judne ke liye aapka bohot dhanyawad! Have a great day! 😊🙏\n        ");
    }

    public final void copyToClipboard(Context context, String label, String text) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(text, "text");
        Object systemService = context.getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipboardManager clipboard = (ClipboardManager) systemService;
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Copied to clipboard!", 0).show();
    }
}
