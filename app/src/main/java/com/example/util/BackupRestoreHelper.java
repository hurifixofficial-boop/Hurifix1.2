package com.example.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.example.BuildConfig;
import com.example.data.firebase.FirestoreSyncManager;
import com.example.data.model.CustomerJobEntity;
import com.example.data.model.ExpertCategoryEntity;
import com.example.data.model.ExpertEntity;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BackupRestoreHelper.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007J8\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014¨\u0006\u0016"}, d2 = {"Lcom/example/util/BackupRestoreHelper;", "", "<init>", "()V", "createBackupJson", "", "jobs", "", "Lcom/example/data/model/CustomerJobEntity;", FirestoreSyncManager.EXPERTS_COLLECTION, "Lcom/example/data/model/ExpertEntity;", "categories", "Lcom/example/data/model/ExpertCategoryEntity;", "exportAndShareBackup", "", "context", "Landroid/content/Context;", "parseBackupJson", "Lcom/example/util/BackupRestoreHelper$BackupData;", "inputStream", "Ljava/io/InputStream;", "BackupData", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes6.dex */
public final class BackupRestoreHelper {
    public static final int $stable = 0;
    public static final BackupRestoreHelper INSTANCE = new BackupRestoreHelper();

    private BackupRestoreHelper() {
    }

    /* compiled from: BackupRestoreHelper.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J9\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/example/util/BackupRestoreHelper$BackupData;", "", "jobs", "", "Lcom/example/data/model/CustomerJobEntity;", FirestoreSyncManager.EXPERTS_COLLECTION, "Lcom/example/data/model/ExpertEntity;", "categories", "Lcom/example/data/model/ExpertCategoryEntity;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getJobs", "()Ljava/util/List;", "getExperts", "getCategories", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes6.dex */
    public static final /* data */ class BackupData {
        public static final int $stable = 8;
        private final List<ExpertCategoryEntity> categories;
        private final List<ExpertEntity> experts;
        private final List<CustomerJobEntity> jobs;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BackupData copy$default(BackupData backupData, List list, List list2, List list3, int i, Object obj) {
            if ((i & 1) != 0) {
                list = backupData.jobs;
            }
            if ((i & 2) != 0) {
                list2 = backupData.experts;
            }
            if ((i & 4) != 0) {
                list3 = backupData.categories;
            }
            return backupData.copy(list, list2, list3);
        }

        public final List<CustomerJobEntity> component1() {
            return this.jobs;
        }

        public final List<ExpertEntity> component2() {
            return this.experts;
        }

        public final List<ExpertCategoryEntity> component3() {
            return this.categories;
        }

        public final BackupData copy(List<CustomerJobEntity> jobs, List<ExpertEntity> experts, List<ExpertCategoryEntity> categories) {
            Intrinsics.checkNotNullParameter(jobs, "jobs");
            Intrinsics.checkNotNullParameter(experts, FirestoreSyncManager.EXPERTS_COLLECTION);
            Intrinsics.checkNotNullParameter(categories, "categories");
            return new BackupData(jobs, experts, categories);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BackupData)) {
                return false;
            }
            BackupData backupData = (BackupData) other;
            return Intrinsics.areEqual(this.jobs, backupData.jobs) && Intrinsics.areEqual(this.experts, backupData.experts) && Intrinsics.areEqual(this.categories, backupData.categories);
        }

        public int hashCode() {
            return (((this.jobs.hashCode() * 31) + this.experts.hashCode()) * 31) + this.categories.hashCode();
        }

        public String toString() {
            return "BackupData(jobs=" + this.jobs + ", experts=" + this.experts + ", categories=" + this.categories + ")";
        }

        public BackupData(List<CustomerJobEntity> list, List<ExpertEntity> list2, List<ExpertCategoryEntity> list3) {
            Intrinsics.checkNotNullParameter(list, "jobs");
            Intrinsics.checkNotNullParameter(list2, FirestoreSyncManager.EXPERTS_COLLECTION);
            Intrinsics.checkNotNullParameter(list3, "categories");
            this.jobs = list;
            this.experts = list2;
            this.categories = list3;
        }

        public final List<CustomerJobEntity> getJobs() {
            return this.jobs;
        }

        public final List<ExpertEntity> getExperts() {
            return this.experts;
        }

        public final List<ExpertCategoryEntity> getCategories() {
            return this.categories;
        }
    }

    public final String createBackupJson(List<CustomerJobEntity> jobs, List<ExpertEntity> experts, List<ExpertCategoryEntity> categories) {
        Intrinsics.checkNotNullParameter(jobs, "jobs");
        Intrinsics.checkNotNullParameter(experts, FirestoreSyncManager.EXPERTS_COLLECTION);
        Intrinsics.checkNotNullParameter(categories, "categories");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("app", "Hurifix");
        jSONObject.put("version", 4);
        jSONObject.put("timestamp", System.currentTimeMillis());
        JSONArray categoriesArray = new JSONArray();
        for (ExpertCategoryEntity expertCategoryEntity : categories) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", expertCategoryEntity.getId());
            jSONObject2.put("name", expertCategoryEntity.getName());
            jSONObject2.put("isDefault", expertCategoryEntity.isDefault());
            jSONObject2.put("createdAt", expertCategoryEntity.getCreatedAt());
            categoriesArray.put(jSONObject2);
        }
        jSONObject.put("categories", categoriesArray);
        JSONArray expertsArray = new JSONArray();
        List<ExpertEntity> list = experts;
        int i = 0;
        for (Iterator it = list.iterator(); it.hasNext(); it = it) {
            ExpertEntity expertEntity = (ExpertEntity) it.next();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("id", expertEntity.getId());
            jSONObject3.put("name", expertEntity.getName());
            jSONObject3.put("phone", expertEntity.getPhone());
            jSONObject3.put("category", expertEntity.getCategory());
            jSONObject3.put("address", expertEntity.getAddress());
            jSONObject3.put("latitude", expertEntity.getLatitude());
            jSONObject3.put("longitude", expertEntity.getLongitude());
            jSONObject3.put("isAvailable", expertEntity.isAvailable());
            jSONObject3.put("rating", expertEntity.getRating());
            jSONObject3.put("ratingSum", expertEntity.getRatingSum());
            jSONObject3.put("totalRatingsCount", expertEntity.getTotalRatingsCount());
            jSONObject3.put("completedJobsCount", expertEntity.getCompletedJobsCount());
            jSONObject3.put("cancelledJobsCount", expertEntity.getCancelledJobsCount());
            jSONObject3.put("createdAt", expertEntity.getCreatedAt());
            jSONObject3.put("last_updated", expertEntity.getLast_updated());
            expertsArray.put(jSONObject3);
            list = list;
            i = i;
        }
        jSONObject.put(FirestoreSyncManager.EXPERTS_COLLECTION, expertsArray);
        JSONArray jobsArray = new JSONArray();
        int i2 = 0;
        Iterator it2 = jobs.iterator();
        while (it2.hasNext()) {
            CustomerJobEntity customerJobEntity = (CustomerJobEntity) it2.next();
            JSONObject jSONObject4 = new JSONObject();
            int i3 = i2;
            Iterator it3 = it2;
            JSONArray expertsArray2 = expertsArray;
            jSONObject4.put("id", customerJobEntity.getId());
            jSONObject4.put("customerName", customerJobEntity.getCustomerName());
            jSONObject4.put("customerPhone", customerJobEntity.getCustomerPhone());
            jSONObject4.put("serviceType", customerJobEntity.getServiceType());
            jSONObject4.put("issueDescription", customerJobEntity.getIssueDescription());
            jSONObject4.put("address", customerJobEntity.getAddress());
            jSONObject4.put("latitude", customerJobEntity.getLatitude());
            jSONObject4.put("longitude", customerJobEntity.getLongitude());
            jSONObject4.put("status", customerJobEntity.getStatus());
            Object assignedExpertId = customerJobEntity.getAssignedExpertId();
            if (assignedExpertId == null) {
                assignedExpertId = JSONObject.NULL;
            }
            jSONObject4.put("assignedExpertId", assignedExpertId);
            Object assignedExpertName = customerJobEntity.getAssignedExpertName();
            if (assignedExpertName == null) {
                assignedExpertName = JSONObject.NULL;
            }
            jSONObject4.put("assignedExpertName", assignedExpertName);
            Object assignedExpertPhone = customerJobEntity.getAssignedExpertPhone();
            if (assignedExpertPhone == null) {
                assignedExpertPhone = JSONObject.NULL;
            }
            jSONObject4.put("assignedExpertPhone", assignedExpertPhone);
            Object distanceKmAtDispatch = customerJobEntity.getDistanceKmAtDispatch();
            if (distanceKmAtDispatch == null) {
                distanceKmAtDispatch = JSONObject.NULL;
            }
            jSONObject4.put("distanceKmAtDispatch", distanceKmAtDispatch);
            jSONObject4.put("ratingGiven", customerJobEntity.getRatingGiven() != null ? Double.valueOf(r4.floatValue()) : JSONObject.NULL);
            Object reviewFeedback = customerJobEntity.getReviewFeedback();
            if (reviewFeedback == null) {
                reviewFeedback = JSONObject.NULL;
            }
            jSONObject4.put("reviewFeedback", reviewFeedback);
            jSONObject4.put("createdAt", customerJobEntity.getCreatedAt());
            jSONObject4.put("last_updated", customerJobEntity.getLast_updated());
            Object completedAt = customerJobEntity.getCompletedAt();
            if (completedAt == null) {
                completedAt = JSONObject.NULL;
            }
            jSONObject4.put("completedAt", completedAt);
            jSONObject4.put("isExpertNotified", customerJobEntity.isExpertNotified());
            jSONObject4.put("isCustomerNotifiedOnAssign", customerJobEntity.isCustomerNotifiedOnAssign());
            jSONObject4.put("isCustomerNotifiedOnCompletion", customerJobEntity.isCustomerNotifiedOnCompletion());
            Object assignMessageLaterDismissedAt = customerJobEntity.getAssignMessageLaterDismissedAt();
            if (assignMessageLaterDismissedAt == null) {
                assignMessageLaterDismissedAt = JSONObject.NULL;
            }
            jSONObject4.put("assignMessageLaterDismissedAt", assignMessageLaterDismissedAt);
            jobsArray.put(jSONObject4);
            i2 = i3;
            it2 = it3;
            expertsArray = expertsArray2;
        }
        jSONObject.put("jobs", jobsArray);
        String jSONObject5 = jSONObject.toString(2);
        Intrinsics.checkNotNullExpressionValue(jSONObject5, "toString(...)");
        return jSONObject5;
    }

    public final void exportAndShareBackup(Context context, List<CustomerJobEntity> jobs, List<ExpertEntity> experts, List<ExpertCategoryEntity> categories) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(jobs, "jobs");
        Intrinsics.checkNotNullParameter(experts, FirestoreSyncManager.EXPERTS_COLLECTION);
        Intrinsics.checkNotNullParameter(categories, "categories");
        SessionManager sessionManager = new SessionManager(context);
        if (!sessionManager.isAdmin()) {
            Toast.makeText(context, "🔒 Access Denied: Backup & Database export is strictly restricted to Master Administrators.", 1).show();
            return;
        }
        String jsonString = createBackupJson(jobs, experts, categories);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm", Locale.getDefault()).format(new Date());
        String fileName = "Hurifix_DriveBackup_" + timeStamp + ".json";
        File file = new File(context.getCacheDir(), "backups");
        file.mkdirs();
        File backupFile = new File(file, fileName);
        FilesKt.writeText$default(backupFile, jsonString, (Charset) null, 2, (Object) null);
        Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", backupFile);
        Intrinsics.checkNotNullExpressionValue(uri, "getUriForFile(...)");
        Intent shareIntent = new Intent("android.intent.action.SEND");
        shareIntent.setType("application/json");
        shareIntent.putExtra("android.intent.extra.STREAM", uri);
        shareIntent.putExtra("android.intent.extra.SUBJECT", "Hurifix Full Database Backup - " + timeStamp);
        shareIntent.putExtra("android.intent.extra.TEXT", "Hurifix complete system backup file. You can upload this directly to Google Drive to save your data.");
        shareIntent.addFlags(1);
        Intent chooser = Intent.createChooser(shareIntent, "Backup to Google Drive / Files");
        chooser.addFlags(268435456);
        context.startActivity(chooser);
    }

    public final BackupData parseBackupJson(InputStream inputStream) {
        List categoriesList;
        String str;
        Float valueOf;
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String jsonString = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, (Throwable) null);
            JSONObject root = new JSONObject(jsonString);
            List categoriesList2 = new ArrayList();
            long j = 0;
            if (root.has("categories")) {
                JSONArray arr = root.getJSONArray("categories");
                int i = 0;
                int length = arr.length();
                while (i < length) {
                    JSONObject obj = arr.getJSONObject(i);
                    long optLong = obj.optLong("id", j);
                    String string = obj.getString("name");
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    categoriesList2.add(new ExpertCategoryEntity(optLong, string, obj.optBoolean("isDefault", false), obj.optLong("createdAt", System.currentTimeMillis()), 0L, false, 48, null));
                    i++;
                    j = 0;
                }
            }
            List expertsList = new ArrayList();
            if (!root.has(FirestoreSyncManager.EXPERTS_COLLECTION)) {
                categoriesList = categoriesList2;
            } else {
                JSONArray arr2 = root.getJSONArray(FirestoreSyncManager.EXPERTS_COLLECTION);
                int i2 = 0;
                int length2 = arr2.length();
                while (i2 < length2) {
                    int i3 = length2;
                    JSONObject obj2 = arr2.getJSONObject(i2);
                    String jsonString2 = jsonString;
                    JSONArray arr3 = arr2;
                    long optLong2 = obj2.optLong("id", 0L);
                    String string2 = obj2.getString("name");
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    String string3 = obj2.getString("phone");
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String string4 = obj2.getString("category");
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    String optString = obj2.optString("address", "");
                    Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                    expertsList.add(new ExpertEntity(optLong2, string2, string3, string4, optString, obj2.optDouble("latitude", 28.57d), obj2.optDouble("longitude", 77.32d), obj2.optBoolean("isAvailable", true), (float) obj2.optDouble("rating", 4.8d), (float) obj2.optDouble("ratingSum", 4.8d), obj2.optInt("totalRatingsCount", 1), obj2.optInt("completedJobsCount", 0), obj2.optInt("cancelledJobsCount", 0), false, false, null, null, null, null, null, 0L, obj2.optLong("createdAt", System.currentTimeMillis()), 0L, false, 14671872, null));
                    i2++;
                    length2 = i3;
                    jsonString = jsonString2;
                    arr2 = arr3;
                    categoriesList2 = categoriesList2;
                }
                categoriesList = categoriesList2;
            }
            List jobsList = new ArrayList();
            if (root.has("jobs")) {
                JSONArray arr4 = root.getJSONArray("jobs");
                int i4 = 0;
                int length3 = arr4.length();
                while (i4 < length3) {
                    JSONObject obj3 = arr4.getJSONObject(i4);
                    JSONObject root2 = root;
                    JSONArray arr5 = arr4;
                    long optLong3 = obj3.optLong("id", 0L);
                    String string5 = obj3.getString("customerName");
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    String string6 = obj3.getString("customerPhone");
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                    String string7 = obj3.getString("serviceType");
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                    String optString2 = obj3.optString("issueDescription", "");
                    Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                    String optString3 = obj3.optString("address", "");
                    Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                    double optDouble = obj3.optDouble("latitude", 28.57d);
                    double optDouble2 = obj3.optDouble("longitude", 77.32d);
                    String optString4 = obj3.optString("status", "PENDING");
                    Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
                    Long valueOf2 = obj3.isNull("assignedExpertId") ? null : Long.valueOf(obj3.optLong("assignedExpertId"));
                    String optString5 = obj3.isNull("assignedExpertName") ? null : obj3.optString("assignedExpertName");
                    String optString6 = obj3.isNull("assignedExpertPhone") ? null : obj3.optString("assignedExpertPhone");
                    Double valueOf3 = obj3.isNull("distanceKmAtDispatch") ? null : Double.valueOf(obj3.optDouble("distanceKmAtDispatch"));
                    if (obj3.isNull("ratingGiven")) {
                        str = optString4;
                        valueOf = null;
                    } else {
                        str = optString4;
                        valueOf = Float.valueOf((float) obj3.optDouble("ratingGiven"));
                    }
                    jobsList.add(new CustomerJobEntity(optLong3, string5, string6, string7, optString2, optString3, optDouble, optDouble2, str, valueOf2, optString5, optString6, valueOf3, valueOf, obj3.isNull("reviewFeedback") ? null : obj3.optString("reviewFeedback"), obj3.optLong("createdAt", System.currentTimeMillis()), obj3.isNull("completedAt") ? null : Long.valueOf(obj3.optLong("completedAt")), obj3.optBoolean("isExpertNotified", false), obj3.optBoolean("isCustomerNotifiedOnAssign", false), obj3.optBoolean("isCustomerNotifiedOnCompletion", false), obj3.isNull("assignMessageLaterDismissedAt") ? null : Long.valueOf(obj3.optLong("assignMessageLaterDismissedAt")), false, null, 0L, false, null, null, null, null, null, null, null, null, null, -2097152, 3, null));
                    i4++;
                    root = root2;
                    arr4 = arr5;
                }
            }
            return new BackupData(jobsList, expertsList, categoriesList);
        } finally {
        }
    }
}
