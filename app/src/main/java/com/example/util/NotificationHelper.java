package com.example.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.example.BuildConfig;
import com.example.MainActivity;
import com.example.R;
import com.example.data.model.CustomerJobEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationHelper.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u001c\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ0\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/example/util/NotificationHelper;", "", "<init>", "()V", "CHANNEL_ORDERS", "", "CHANNEL_MESSAGES", "createNotificationChannels", "", "context", "Landroid/content/Context;", "checkAndTriggerReminders", "jobs", "", "Lcom/example/data/model/CustomerJobEntity;", "sendNotification", "id", "", "channelId", "title", "message", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes6.dex */
public final class NotificationHelper {
    public static final int $stable = 0;
    private static final String CHANNEL_MESSAGES = "hurifix_message_reminders";
    private static final String CHANNEL_ORDERS = "hurifix_order_alerts";
    public static final NotificationHelper INSTANCE = new NotificationHelper();

    private NotificationHelper() {
    }

    public final void createNotificationChannels(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel orderChannel = new NotificationChannel(CHANNEL_ORDERS, "Order Alerts & Reminders", 4);
            orderChannel.setDescription("Alerts for orders pending or processing for over 1 hour");
            orderChannel.enableVibration(true);
            NotificationChannel messageChannel = new NotificationChannel(CHANNEL_MESSAGES, "WhatsApp Message Reminders", 3);
            messageChannel.setDescription("Reminders for pending WhatsApp messages");
            Object systemService = context.getSystemService("notification");
            NotificationManager manager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
            if (manager != null) {
                manager.createNotificationChannel(orderChannel);
            }
            if (manager != null) {
                manager.createNotificationChannel(messageChannel);
            }
        }
    }

    public final void checkAndTriggerReminders(Context context, List<CustomerJobEntity> jobs) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(jobs, "jobs");
        createNotificationChannels(context);
        if (Build.VERSION.SDK_INT >= 33 && ContextCompat.checkSelfPermission(context, "android.permission.POST_NOTIFICATIONS") != 0) {
            return;
        }
        long now = System.currentTimeMillis();
        for (CustomerJobEntity customerJobEntity : jobs) {
            long createdAt = now - customerJobEntity.getCreatedAt();
            if (!Intrinsics.areEqual(customerJobEntity.getStatus(), "PENDING") || createdAt < 3600000) {
                str = ")";
            } else {
                str = ")";
                INSTANCE.sendNotification(context, (int) ((customerJobEntity.getId() * 10) + 1), CHANNEL_ORDERS, "⏳ Pending Order Alert (#" + customerJobEntity.getId() + ")", "Order for " + customerJobEntity.getCustomerName() + " has been pending for " + ((int) (createdAt / 3600000)) + " hr(s). Please assign a technician.");
            }
            if (Intrinsics.areEqual(customerJobEntity.getStatus(), "PROCESSING") && createdAt >= 3600000) {
                int i = (int) (createdAt / 3600000);
                NotificationHelper notificationHelper = INSTANCE;
                int id = (int) ((customerJobEntity.getId() * 10) + 2);
                String str2 = "⚙️ Processing Order Follow-up (#" + customerJobEntity.getId() + str;
                String assignedExpertName = customerJobEntity.getAssignedExpertName();
                if (assignedExpertName == null) {
                    assignedExpertName = "Expert";
                }
                notificationHelper.sendNotification(context, id, CHANNEL_ORDERS, str2, "Work assigned to " + assignedExpertName + " has been in progress for " + i + " hr(s). Check status.");
            }
            Long assignMessageLaterDismissedAt = customerJobEntity.getAssignMessageLaterDismissedAt();
            if (assignMessageLaterDismissedAt != null && now - assignMessageLaterDismissedAt.longValue() >= 3600000 && (!customerJobEntity.isExpertNotified() || !customerJobEntity.isCustomerNotifiedOnAssign())) {
                INSTANCE.sendNotification(context, (int) ((customerJobEntity.getId() * 10) + 3), CHANNEL_MESSAGES, "📲 WhatsApp Message Reminder (#" + customerJobEntity.getId() + str, "WhatsApp notification for order #" + customerJobEntity.getId() + " (" + customerJobEntity.getCustomerName() + ") is still pending to be sent!");
            }
        }
    }

    private final void sendNotification(Context context, int id, String channelId, String title, String message) {
        Intent intent = new Intent(context, (Class<?>) MainActivity.class);
        intent.setFlags(335544320);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, 201326592);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setContentText(message).setStyle(new NotificationCompat.BigTextStyle().bigText(message)).setPriority(1).setAutoCancel(true).setContentIntent(pendingIntent);
        Intrinsics.checkNotNullExpressionValue(builder, "setContentIntent(...)");
        try {
            NotificationManagerCompat.from(context).notify(id, builder.build());
        } catch (SecurityException e) {
        }
    }
}
