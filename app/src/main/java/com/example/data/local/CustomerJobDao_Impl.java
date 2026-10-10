package com.example.data.local;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.example.BuildConfig;
import com.example.data.firebase.FirestoreSyncManager;
import com.example.data.model.CustomerJobEntity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.Flow;

/* compiled from: CustomerJobDao_Impl.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\b\u0007\u0018\u0000 V2\u00020\u0001:\u0001VB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0018H\u0016J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u001c\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u00182\u0006\u0010\u001d\u001a\u00020\rH\u0016J\u0014\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0018H\u0016J\u0018\u0010\u001f\u001a\u0004\u0018\u00010\b2\u0006\u0010 \u001a\u00020\rH\u0096@¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020#H\u0096@¢\u0006\u0002\u0010$J \u0010%\u001a\u0004\u0018\u00010\b2\u0006\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010(J\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010$J\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010$J\u001e\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010.J\u001e\u0010/\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u00100\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010.J\u0016\u00101\u001a\u00020\u00112\u0006\u00102\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010!J\u000e\u00103\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010$J\u0016\u00104\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\rH\u0096@¢\u0006\u0002\u0010!JF\u00105\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010;J8\u0010<\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u0010=\u001a\u00020\u001b2\u0006\u0010>\u001a\u00020\u001b2\b\u0010?\u001a\u0004\u0018\u00010\u001b2\u0006\u0010@\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010AJ\u001e\u0010B\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010CJ\u0016\u0010D\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010!J8\u0010E\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010\u001b2\u0006\u0010I\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010JJ\u001e\u0010K\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u0010L\u001a\u00020MH\u0096@¢\u0006\u0002\u0010NJ\u001e\u0010O\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u0010L\u001a\u00020MH\u0096@¢\u0006\u0002\u0010NJ\u001e\u0010P\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u0010L\u001a\u00020MH\u0096@¢\u0006\u0002\u0010NJ \u0010Q\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\b\u0010R\u001a\u0004\u0018\u00010\rH\u0096@¢\u0006\u0002\u0010SJ\u000e\u0010T\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010$J\u0016\u0010U\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lcom/example/data/local/CustomerJobDao_Impl;", "Lcom/example/data/local/CustomerJobDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfCustomerJobEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/model/CustomerJobEntity;", "__deleteAdapterOfCustomerJobEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfCustomerJobEntity", "insertJob", "", "job", "(Lcom/example/data/model/CustomerJobEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertJobs", "", "jobs", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteJob", "updateJob", "getAllJobs", "Lkotlinx/coroutines/flow/Flow;", "getJobsByStatus", "status", "", "getJobsForExpert", "expertId", "getDeletedJobs", "getJobById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getJobCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findRecentOrderByPhone", "phone", "sinceTimestamp", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsyncedJobs", "getAllJobsDirectList", "moveToRecycleBin", "jobId", "deletedAt", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreJobFromRecycleBin", "restoredAt", "purgeJobsOlderThan", "cutoffTimestamp", "clearRecycleBin", "deleteJobById", "updateJobDispatch", "expertName", "expertPhone", "distanceKm", "", "assignedAt", "(JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;DJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateJobManager", "userId", "userName", "userDesignation", "now", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateJobStatus", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unassignExpertFromJob", "completeOrCancelJobWithReview", "rating", "", "feedback", "completedAt", "(JLjava/lang/String;FLjava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateExpertNotified", "sent", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCustomerNotifiedOnAssign", "updateCustomerNotifiedOnCompletion", "updateMessageDismissedAt", "time", "(JLjava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAllJobs", "markJobSynced", "Companion", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes7.dex */
public final class CustomerJobDao_Impl implements CustomerJobDao {
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<CustomerJobEntity> __deleteAdapterOfCustomerJobEntity;
    private final EntityInsertAdapter<CustomerJobEntity> __insertAdapterOfCustomerJobEntity;
    private final EntityDeleteOrUpdateAdapter<CustomerJobEntity> __updateAdapterOfCustomerJobEntity;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public CustomerJobDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfCustomerJobEntity = new EntityInsertAdapter<CustomerJobEntity>() { // from class: com.example.data.local.CustomerJobDao_Impl.1
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `customer_jobs` (`id`,`customerName`,`customerPhone`,`serviceType`,`issueDescription`,`address`,`latitude`,`longitude`,`status`,`assignedExpertId`,`assignedExpertName`,`assignedExpertPhone`,`distanceKmAtDispatch`,`ratingGiven`,`reviewFeedback`,`createdAt`,`completedAt`,`isExpertNotified`,`isCustomerNotifiedOnAssign`,`isCustomerNotifiedOnCompletion`,`assignMessageLaterDismissedAt`,`isDeleted`,`deletedAt`,`last_updated`,`is_synced`,`created_by_user_id`,`created_by_user_name`,`created_by_designation`,`managed_by_user_id`,`managed_by_user_name`,`managed_by_designation`,`assigned_technician_id`,`assigned_technician_name`,`assigned_at_timestamp`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, CustomerJobEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
                statement.bindText(2, entity.getCustomerName());
                statement.bindText(3, entity.getCustomerPhone());
                statement.bindText(4, entity.getServiceType());
                statement.bindText(5, entity.getIssueDescription());
                statement.bindText(6, entity.getAddress());
                statement.bindDouble(7, entity.getLatitude());
                statement.bindDouble(8, entity.getLongitude());
                statement.bindText(9, entity.getStatus());
                Long assignedExpertId = entity.getAssignedExpertId();
                if (assignedExpertId != null) {
                    statement.bindLong(10, assignedExpertId.longValue());
                } else {
                    statement.bindNull(10);
                }
                String assignedExpertName = entity.getAssignedExpertName();
                if (assignedExpertName == null) {
                    statement.bindNull(11);
                } else {
                    statement.bindText(11, assignedExpertName);
                }
                String assignedExpertPhone = entity.getAssignedExpertPhone();
                if (assignedExpertPhone == null) {
                    statement.bindNull(12);
                } else {
                    statement.bindText(12, assignedExpertPhone);
                }
                Double distanceKmAtDispatch = entity.getDistanceKmAtDispatch();
                if (distanceKmAtDispatch != null) {
                    statement.bindDouble(13, distanceKmAtDispatch.doubleValue());
                } else {
                    statement.bindNull(13);
                }
                if (entity.getRatingGiven() != null) {
                    statement.bindDouble(14, r6.floatValue());
                } else {
                    statement.bindNull(14);
                }
                String reviewFeedback = entity.getReviewFeedback();
                if (reviewFeedback == null) {
                    statement.bindNull(15);
                } else {
                    statement.bindText(15, reviewFeedback);
                }
                statement.bindLong(16, entity.getCreatedAt());
                Long completedAt = entity.getCompletedAt();
                if (completedAt != null) {
                    statement.bindLong(17, completedAt.longValue());
                } else {
                    statement.bindNull(17);
                }
                statement.bindLong(18, entity.isExpertNotified() ? 1L : 0L);
                statement.bindLong(19, entity.isCustomerNotifiedOnAssign() ? 1L : 0L);
                statement.bindLong(20, entity.isCustomerNotifiedOnCompletion() ? 1L : 0L);
                Long assignMessageLaterDismissedAt = entity.getAssignMessageLaterDismissedAt();
                if (assignMessageLaterDismissedAt != null) {
                    statement.bindLong(21, assignMessageLaterDismissedAt.longValue());
                } else {
                    statement.bindNull(21);
                }
                statement.bindLong(22, entity.isDeleted() ? 1L : 0L);
                Long deletedAt = entity.getDeletedAt();
                if (deletedAt != null) {
                    statement.bindLong(23, deletedAt.longValue());
                } else {
                    statement.bindNull(23);
                }
                statement.bindLong(24, entity.getLast_updated());
                statement.bindLong(25, entity.is_synced() ? 1L : 0L);
                String created_by_user_id = entity.getCreated_by_user_id();
                if (created_by_user_id == null) {
                    statement.bindNull(26);
                } else {
                    statement.bindText(26, created_by_user_id);
                }
                String created_by_user_name = entity.getCreated_by_user_name();
                if (created_by_user_name == null) {
                    statement.bindNull(27);
                } else {
                    statement.bindText(27, created_by_user_name);
                }
                String created_by_designation = entity.getCreated_by_designation();
                if (created_by_designation == null) {
                    statement.bindNull(28);
                } else {
                    statement.bindText(28, created_by_designation);
                }
                String managed_by_user_id = entity.getManaged_by_user_id();
                if (managed_by_user_id == null) {
                    statement.bindNull(29);
                } else {
                    statement.bindText(29, managed_by_user_id);
                }
                String managed_by_user_name = entity.getManaged_by_user_name();
                if (managed_by_user_name == null) {
                    statement.bindNull(30);
                } else {
                    statement.bindText(30, managed_by_user_name);
                }
                String managed_by_designation = entity.getManaged_by_designation();
                if (managed_by_designation == null) {
                    statement.bindNull(31);
                } else {
                    statement.bindText(31, managed_by_designation);
                }
                Long assigned_technician_id = entity.getAssigned_technician_id();
                if (assigned_technician_id == null) {
                    statement.bindNull(32);
                } else {
                    statement.bindLong(32, assigned_technician_id.longValue());
                }
                String assigned_technician_name = entity.getAssigned_technician_name();
                if (assigned_technician_name == null) {
                    statement.bindNull(33);
                } else {
                    statement.bindText(33, assigned_technician_name);
                }
                Long assigned_at_timestamp = entity.getAssigned_at_timestamp();
                if (assigned_at_timestamp != null) {
                    statement.bindLong(34, assigned_at_timestamp.longValue());
                } else {
                    statement.bindNull(34);
                }
            }
        };
        this.__deleteAdapterOfCustomerJobEntity = new EntityDeleteOrUpdateAdapter<CustomerJobEntity>() { // from class: com.example.data.local.CustomerJobDao_Impl.2
            protected String createQuery() {
                return "DELETE FROM `customer_jobs` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, CustomerJobEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfCustomerJobEntity = new EntityDeleteOrUpdateAdapter<CustomerJobEntity>() { // from class: com.example.data.local.CustomerJobDao_Impl.3
            protected String createQuery() {
                return "UPDATE OR ABORT `customer_jobs` SET `id` = ?,`customerName` = ?,`customerPhone` = ?,`serviceType` = ?,`issueDescription` = ?,`address` = ?,`latitude` = ?,`longitude` = ?,`status` = ?,`assignedExpertId` = ?,`assignedExpertName` = ?,`assignedExpertPhone` = ?,`distanceKmAtDispatch` = ?,`ratingGiven` = ?,`reviewFeedback` = ?,`createdAt` = ?,`completedAt` = ?,`isExpertNotified` = ?,`isCustomerNotifiedOnAssign` = ?,`isCustomerNotifiedOnCompletion` = ?,`assignMessageLaterDismissedAt` = ?,`isDeleted` = ?,`deletedAt` = ?,`last_updated` = ?,`is_synced` = ?,`created_by_user_id` = ?,`created_by_user_name` = ?,`created_by_designation` = ?,`managed_by_user_id` = ?,`managed_by_user_name` = ?,`managed_by_designation` = ?,`assigned_technician_id` = ?,`assigned_technician_name` = ?,`assigned_at_timestamp` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, CustomerJobEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
                statement.bindText(2, entity.getCustomerName());
                statement.bindText(3, entity.getCustomerPhone());
                statement.bindText(4, entity.getServiceType());
                statement.bindText(5, entity.getIssueDescription());
                statement.bindText(6, entity.getAddress());
                statement.bindDouble(7, entity.getLatitude());
                statement.bindDouble(8, entity.getLongitude());
                statement.bindText(9, entity.getStatus());
                Long assignedExpertId = entity.getAssignedExpertId();
                if (assignedExpertId != null) {
                    statement.bindLong(10, assignedExpertId.longValue());
                } else {
                    statement.bindNull(10);
                }
                String assignedExpertName = entity.getAssignedExpertName();
                if (assignedExpertName == null) {
                    statement.bindNull(11);
                } else {
                    statement.bindText(11, assignedExpertName);
                }
                String assignedExpertPhone = entity.getAssignedExpertPhone();
                if (assignedExpertPhone == null) {
                    statement.bindNull(12);
                } else {
                    statement.bindText(12, assignedExpertPhone);
                }
                Double distanceKmAtDispatch = entity.getDistanceKmAtDispatch();
                if (distanceKmAtDispatch != null) {
                    statement.bindDouble(13, distanceKmAtDispatch.doubleValue());
                } else {
                    statement.bindNull(13);
                }
                if (entity.getRatingGiven() != null) {
                    statement.bindDouble(14, r6.floatValue());
                } else {
                    statement.bindNull(14);
                }
                String reviewFeedback = entity.getReviewFeedback();
                if (reviewFeedback == null) {
                    statement.bindNull(15);
                } else {
                    statement.bindText(15, reviewFeedback);
                }
                statement.bindLong(16, entity.getCreatedAt());
                Long completedAt = entity.getCompletedAt();
                if (completedAt != null) {
                    statement.bindLong(17, completedAt.longValue());
                } else {
                    statement.bindNull(17);
                }
                statement.bindLong(18, entity.isExpertNotified() ? 1L : 0L);
                statement.bindLong(19, entity.isCustomerNotifiedOnAssign() ? 1L : 0L);
                statement.bindLong(20, entity.isCustomerNotifiedOnCompletion() ? 1L : 0L);
                Long assignMessageLaterDismissedAt = entity.getAssignMessageLaterDismissedAt();
                if (assignMessageLaterDismissedAt != null) {
                    statement.bindLong(21, assignMessageLaterDismissedAt.longValue());
                } else {
                    statement.bindNull(21);
                }
                statement.bindLong(22, entity.isDeleted() ? 1L : 0L);
                Long deletedAt = entity.getDeletedAt();
                if (deletedAt != null) {
                    statement.bindLong(23, deletedAt.longValue());
                } else {
                    statement.bindNull(23);
                }
                statement.bindLong(24, entity.getLast_updated());
                statement.bindLong(25, entity.is_synced() ? 1L : 0L);
                String created_by_user_id = entity.getCreated_by_user_id();
                if (created_by_user_id == null) {
                    statement.bindNull(26);
                } else {
                    statement.bindText(26, created_by_user_id);
                }
                String created_by_user_name = entity.getCreated_by_user_name();
                if (created_by_user_name == null) {
                    statement.bindNull(27);
                } else {
                    statement.bindText(27, created_by_user_name);
                }
                String created_by_designation = entity.getCreated_by_designation();
                if (created_by_designation == null) {
                    statement.bindNull(28);
                } else {
                    statement.bindText(28, created_by_designation);
                }
                String managed_by_user_id = entity.getManaged_by_user_id();
                if (managed_by_user_id == null) {
                    statement.bindNull(29);
                } else {
                    statement.bindText(29, managed_by_user_id);
                }
                String managed_by_user_name = entity.getManaged_by_user_name();
                if (managed_by_user_name == null) {
                    statement.bindNull(30);
                } else {
                    statement.bindText(30, managed_by_user_name);
                }
                String managed_by_designation = entity.getManaged_by_designation();
                if (managed_by_designation == null) {
                    statement.bindNull(31);
                } else {
                    statement.bindText(31, managed_by_designation);
                }
                Long assigned_technician_id = entity.getAssigned_technician_id();
                if (assigned_technician_id == null) {
                    statement.bindNull(32);
                } else {
                    statement.bindLong(32, assigned_technician_id.longValue());
                }
                String assigned_technician_name = entity.getAssigned_technician_name();
                if (assigned_technician_name == null) {
                    statement.bindNull(33);
                } else {
                    statement.bindText(33, assigned_technician_name);
                }
                Long assigned_at_timestamp = entity.getAssigned_at_timestamp();
                if (assigned_at_timestamp != null) {
                    statement.bindLong(34, assigned_at_timestamp.longValue());
                } else {
                    statement.bindNull(34);
                }
                statement.bindLong(35, entity.getId());
            }
        };
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object insertJob(final CustomerJobEntity job, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda25
            public final Object invoke(Object obj) {
                return Long.valueOf(CustomerJobDao_Impl.insertJob$lambda$0(CustomerJobDao_Impl.this, job, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final long insertJob$lambda$0(CustomerJobDao_Impl this$0, CustomerJobEntity $job, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfCustomerJobEntity.insertAndReturnId(_connection, $job);
        return _result;
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object insertJobs(final List<CustomerJobEntity> list, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda3
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.insertJobs$lambda$1(CustomerJobDao_Impl.this, list, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit insertJobs$lambda$1(CustomerJobDao_Impl this$0, List $jobs, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfCustomerJobEntity.insert(_connection, $jobs);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object deleteJob(final CustomerJobEntity job, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda6
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.deleteJob$lambda$2(CustomerJobDao_Impl.this, job, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteJob$lambda$2(CustomerJobDao_Impl this$0, CustomerJobEntity $job, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfCustomerJobEntity.handle(_connection, $job);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object updateJob(final CustomerJobEntity job, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda9
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.updateJob$lambda$3(CustomerJobDao_Impl.this, job, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateJob$lambda$3(CustomerJobDao_Impl this$0, CustomerJobEntity $job, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfCustomerJobEntity.handle(_connection, $job);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.CustomerJobDao
    public Flow<List<CustomerJobEntity>> getAllJobs() {
        final String _sql = "SELECT * FROM customer_jobs WHERE isDeleted = 0 ORDER BY createdAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{FirestoreSyncManager.JOBS_COLLECTION}, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda21
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.getAllJobs$lambda$4(_sql, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAllJobs$lambda$4(String $_sql, SQLiteConnection _connection) {
        Long _tmpAssignedExpertId;
        String _tmpAssignedExpertName;
        String _tmpAssignedExpertPhone;
        Double _tmpDistanceKmAtDispatch;
        int _columnIndexOfCustomerName;
        int _columnIndexOfCustomerPhone;
        Float _tmpRatingGiven;
        String _tmpReviewFeedback;
        Long _tmpCompletedAt;
        Long _tmpAssignMessageLaterDismissedAt;
        Long _tmpDeletedAt;
        String _tmpCreated_by_user_id;
        String _tmpCreated_by_user_name;
        String _tmpCreated_by_designation;
        String _tmpManaged_by_user_id;
        String _tmpManaged_by_user_name;
        String _tmpManaged_by_designation;
        Long _tmpAssigned_technician_id;
        String _tmpAssigned_technician_name;
        Long _tmpAssigned_at_timestamp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfAssignedTechnicianName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _tmp_4 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerName");
            int _columnIndexOfCustomerPhone2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerPhone");
            int _columnIndexOfServiceType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceType");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
            int _columnIndexOfAssignedExpertId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertId");
            int _columnIndexOfAssignedExpertName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertName");
            int _columnIndexOfAssignedExpertPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertPhone");
            int _columnIndexOfDistanceKmAtDispatch = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "distanceKmAtDispatch");
            int _columnIndexOfRatingGiven = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingGiven");
            int _columnIndexOfReviewFeedback = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reviewFeedback");
            int _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfReviewFeedback;
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedAt");
            int _columnIndexOfIsExpertNotified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isExpertNotified");
            int _columnIndexOfReviewFeedback2 = _columnIndexOfIsExpertNotified;
            int _columnIndexOfRatingGiven2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnAssign");
            int _columnIndexOfIsCustomerNotifiedOnCompletion2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnCompletion");
            int _tmp = _columnIndexOfIsCustomerNotifiedOnCompletion2;
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignMessageLaterDismissedAt");
            int _columnIndexOfIsDeleted2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_2 = _columnIndexOfIsDeleted2;
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfDeletedAt2 = _columnIndexOfDeletedAt;
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfLastUpdated2 = _columnIndexOfLastUpdated;
            int _columnIndexOfIsSynced2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            int _tmp_3 = _columnIndexOfIsSynced2;
            int _columnIndexOfCreatedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_id");
            int _columnIndexOfCreatedByUserId2 = _columnIndexOfCreatedByUserId;
            int _columnIndexOfCreatedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_name");
            int _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserName;
            int _columnIndexOfCreatedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_designation");
            int _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByDesignation;
            int _columnIndexOfManagedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_id");
            int _columnIndexOfManagedByUserId2 = _columnIndexOfManagedByUserId;
            int _columnIndexOfManagedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_name");
            int _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserName;
            int _columnIndexOfManagedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_designation");
            int _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByDesignation;
            int _columnIndexOfAssignedTechnicianId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_id");
            int _columnIndexOfAssignedTechnicianId2 = _columnIndexOfAssignedTechnicianId;
            int _columnIndexOfAssignedTechnicianName2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_name");
            int _columnIndexOfAssignedTechnicianName3 = _columnIndexOfAssignedTechnicianName2;
            int _columnIndexOfAssignedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_at_timestamp");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfAssignedTechnicianName);
                String _tmpCustomerName = _stmt.getText(_tmp_4);
                String _tmpCustomerPhone = _stmt.getText(_columnIndexOfCustomerPhone2);
                String _tmpServiceType = _stmt.getText(_columnIndexOfServiceType);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                if (_stmt.isNull(_columnIndexOfAssignedExpertId)) {
                    _tmpAssignedExpertId = null;
                } else {
                    Long _tmpAssignedExpertId2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedExpertId));
                    _tmpAssignedExpertId = _tmpAssignedExpertId2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertName)) {
                    _tmpAssignedExpertName = null;
                } else {
                    String _tmpAssignedExpertName2 = _stmt.getText(_columnIndexOfAssignedExpertName);
                    _tmpAssignedExpertName = _tmpAssignedExpertName2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertPhone)) {
                    _tmpAssignedExpertPhone = null;
                } else {
                    String _tmpAssignedExpertPhone2 = _stmt.getText(_columnIndexOfAssignedExpertPhone);
                    _tmpAssignedExpertPhone = _tmpAssignedExpertPhone2;
                }
                if (_stmt.isNull(_columnIndexOfDistanceKmAtDispatch)) {
                    _tmpDistanceKmAtDispatch = null;
                } else {
                    Double _tmpDistanceKmAtDispatch2 = Double.valueOf(_stmt.getDouble(_columnIndexOfDistanceKmAtDispatch));
                    _tmpDistanceKmAtDispatch = _tmpDistanceKmAtDispatch2;
                }
                if (_stmt.isNull(_columnIndexOfRatingGiven)) {
                    _columnIndexOfCustomerName = _tmp_4;
                    _columnIndexOfCustomerPhone = _columnIndexOfCustomerPhone2;
                    _tmpRatingGiven = null;
                } else {
                    _columnIndexOfCustomerName = _tmp_4;
                    _columnIndexOfCustomerPhone = _columnIndexOfCustomerPhone2;
                    Float _tmpRatingGiven2 = Float.valueOf((float) _stmt.getDouble(_columnIndexOfRatingGiven));
                    _tmpRatingGiven = _tmpRatingGiven2;
                }
                int _columnIndexOfReviewFeedback3 = _columnIndexOfIsCustomerNotifiedOnCompletion;
                if (_stmt.isNull(_columnIndexOfReviewFeedback3)) {
                    _tmpReviewFeedback = null;
                } else {
                    String _tmpReviewFeedback2 = _stmt.getText(_columnIndexOfReviewFeedback3);
                    _tmpReviewFeedback = _tmpReviewFeedback2;
                }
                int _columnIndexOfCreatedAt = _columnIndexOfId;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                int _columnIndexOfId2 = _columnIndexOfAssignedTechnicianName;
                int _columnIndexOfId3 = _columnIndexOfIsSynced;
                if (_stmt.isNull(_columnIndexOfId3)) {
                    _tmpCompletedAt = null;
                } else {
                    Long _tmpCompletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfId3));
                    _tmpCompletedAt = _tmpCompletedAt2;
                }
                int _columnIndexOfCompletedAt = _columnIndexOfReviewFeedback2;
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfCompletedAt);
                boolean _tmpIsExpertNotified = _tmp2 != 0;
                int _columnIndexOfIsCustomerNotifiedOnAssign = _columnIndexOfRatingGiven2;
                int _columnIndexOfIsCustomerNotifiedOnAssign2 = _columnIndexOfRatingGiven;
                boolean _tmpIsCustomerNotifiedOnAssign = ((int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnAssign)) != 0;
                int _tmp_1 = _tmp;
                int _tmp_22 = (int) _stmt.getLong(_tmp_1);
                boolean _tmpIsCustomerNotifiedOnCompletion = _tmp_22 != 0;
                int _columnIndexOfIsCustomerNotifiedOnCompletion3 = _columnIndexOfIsDeleted;
                if (_stmt.isNull(_columnIndexOfIsCustomerNotifiedOnCompletion3)) {
                    _tmpAssignMessageLaterDismissedAt = null;
                } else {
                    Long _tmpAssignMessageLaterDismissedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsCustomerNotifiedOnCompletion3));
                    _tmpAssignMessageLaterDismissedAt = _tmpAssignMessageLaterDismissedAt2;
                }
                int _columnIndexOfAssignMessageLaterDismissedAt = _tmp_2;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfAssignMessageLaterDismissedAt);
                boolean _tmpIsDeleted = _tmp_32 != 0;
                int _columnIndexOfIsDeleted3 = _columnIndexOfDeletedAt2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsDeleted3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfLastUpdated3 = _columnIndexOfLastUpdated2;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated3);
                int _columnIndexOfDeletedAt3 = _tmp_3;
                int _tmp_42 = (int) _stmt.getLong(_columnIndexOfDeletedAt3);
                boolean _tmpIs_synced = _tmp_42 != 0;
                int _columnIndexOfIsSynced3 = _columnIndexOfCreatedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsSynced3)) {
                    _tmpCreated_by_user_id = null;
                } else {
                    String _tmpCreated_by_user_id2 = _stmt.getText(_columnIndexOfIsSynced3);
                    _tmpCreated_by_user_id = _tmpCreated_by_user_id2;
                }
                _columnIndexOfCreatedByUserId2 = _columnIndexOfIsSynced3;
                int _columnIndexOfCreatedByUserId3 = _columnIndexOfCreatedByUserName2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserId3)) {
                    _tmpCreated_by_user_name = null;
                } else {
                    String _tmpCreated_by_user_name2 = _stmt.getText(_columnIndexOfCreatedByUserId3);
                    _tmpCreated_by_user_name = _tmpCreated_by_user_name2;
                }
                _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserId3;
                int _columnIndexOfCreatedByUserName3 = _columnIndexOfCreatedByDesignation2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserName3)) {
                    _tmpCreated_by_designation = null;
                } else {
                    String _tmpCreated_by_designation2 = _stmt.getText(_columnIndexOfCreatedByUserName3);
                    _tmpCreated_by_designation = _tmpCreated_by_designation2;
                }
                _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByUserName3;
                int _columnIndexOfCreatedByDesignation3 = _columnIndexOfManagedByUserId2;
                if (_stmt.isNull(_columnIndexOfCreatedByDesignation3)) {
                    _tmpManaged_by_user_id = null;
                } else {
                    String _tmpManaged_by_user_id2 = _stmt.getText(_columnIndexOfCreatedByDesignation3);
                    _tmpManaged_by_user_id = _tmpManaged_by_user_id2;
                }
                _columnIndexOfManagedByUserId2 = _columnIndexOfCreatedByDesignation3;
                int _columnIndexOfManagedByUserId3 = _columnIndexOfManagedByUserName2;
                if (_stmt.isNull(_columnIndexOfManagedByUserId3)) {
                    _tmpManaged_by_user_name = null;
                } else {
                    String _tmpManaged_by_user_name2 = _stmt.getText(_columnIndexOfManagedByUserId3);
                    _tmpManaged_by_user_name = _tmpManaged_by_user_name2;
                }
                _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserId3;
                int _columnIndexOfManagedByUserName3 = _columnIndexOfManagedByDesignation2;
                if (_stmt.isNull(_columnIndexOfManagedByUserName3)) {
                    _tmpManaged_by_designation = null;
                } else {
                    String _tmpManaged_by_designation2 = _stmt.getText(_columnIndexOfManagedByUserName3);
                    _tmpManaged_by_designation = _tmpManaged_by_designation2;
                }
                _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByUserName3;
                int _columnIndexOfManagedByDesignation3 = _columnIndexOfAssignedTechnicianId2;
                if (_stmt.isNull(_columnIndexOfManagedByDesignation3)) {
                    _tmpAssigned_technician_id = null;
                } else {
                    Long _tmpAssigned_technician_id2 = Long.valueOf(_stmt.getLong(_columnIndexOfManagedByDesignation3));
                    _tmpAssigned_technician_id = _tmpAssigned_technician_id2;
                }
                _columnIndexOfAssignedTechnicianId2 = _columnIndexOfManagedByDesignation3;
                int _columnIndexOfAssignedTechnicianId3 = _columnIndexOfAssignedTechnicianName3;
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianId3)) {
                    _tmpAssigned_technician_name = null;
                } else {
                    String _tmpAssigned_technician_name2 = _stmt.getText(_columnIndexOfAssignedTechnicianId3);
                    _tmpAssigned_technician_name = _tmpAssigned_technician_name2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedAtTimestamp)) {
                    _tmpAssigned_at_timestamp = null;
                } else {
                    Long _tmpAssigned_at_timestamp2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedAtTimestamp));
                    _tmpAssigned_at_timestamp = _tmpAssigned_at_timestamp2;
                }
                CustomerJobEntity _item = new CustomerJobEntity(_tmpId, _tmpCustomerName, _tmpCustomerPhone, _tmpServiceType, _tmpIssueDescription, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpStatus, _tmpAssignedExpertId, _tmpAssignedExpertName, _tmpAssignedExpertPhone, _tmpDistanceKmAtDispatch, _tmpRatingGiven, _tmpReviewFeedback, _tmpCreatedAt, _tmpCompletedAt, _tmpIsExpertNotified, _tmpIsCustomerNotifiedOnAssign, _tmpIsCustomerNotifiedOnCompletion, _tmpAssignMessageLaterDismissedAt, _tmpIsDeleted, _tmpDeletedAt, _tmpLast_updated, _tmpIs_synced, _tmpCreated_by_user_id, _tmpCreated_by_user_name, _tmpCreated_by_designation, _tmpManaged_by_user_id, _tmpManaged_by_user_name, _tmpManaged_by_designation, _tmpAssigned_technician_id, _tmpAssigned_technician_name, _tmpAssigned_at_timestamp);
                List _result2 = _result;
                _result2.add(_item);
                _columnIndexOfAssignedTechnicianName3 = _columnIndexOfAssignedTechnicianId3;
                _result = _result2;
                _tmp = _tmp_1;
                _columnIndexOfAssignedTechnicianName = _columnIndexOfId2;
                _tmp_3 = _columnIndexOfDeletedAt3;
                _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfReviewFeedback3;
                _columnIndexOfRatingGiven = _columnIndexOfIsCustomerNotifiedOnAssign2;
                _tmp_2 = _columnIndexOfAssignMessageLaterDismissedAt;
                _columnIndexOfIsSynced = _columnIndexOfId3;
                _tmp_4 = _columnIndexOfCustomerName;
                _columnIndexOfCustomerPhone2 = _columnIndexOfCustomerPhone;
                _columnIndexOfId = _columnIndexOfCreatedAt;
                _columnIndexOfReviewFeedback2 = _columnIndexOfCompletedAt;
                _columnIndexOfRatingGiven2 = _columnIndexOfIsCustomerNotifiedOnAssign;
                _columnIndexOfIsDeleted = _columnIndexOfIsCustomerNotifiedOnCompletion3;
                _columnIndexOfDeletedAt2 = _columnIndexOfIsDeleted3;
                _columnIndexOfLastUpdated2 = _columnIndexOfLastUpdated3;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Flow<List<CustomerJobEntity>> getJobsByStatus(final String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        final String _sql = "SELECT * FROM customer_jobs WHERE status = ? AND isDeleted = 0 ORDER BY createdAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{FirestoreSyncManager.JOBS_COLLECTION}, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda8
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.getJobsByStatus$lambda$5(_sql, status, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getJobsByStatus$lambda$5(String $_sql, String $status, SQLiteConnection _connection) {
        Long _tmpAssignedExpertId;
        String _tmpAssignedExpertName;
        String _tmpAssignedExpertPhone;
        Double _tmpDistanceKmAtDispatch;
        int _columnIndexOfAssignedExpertPhone;
        int _columnIndexOfDistanceKmAtDispatch;
        Float _tmpRatingGiven;
        String _tmpReviewFeedback;
        Long _tmpCompletedAt;
        Long _tmpAssignMessageLaterDismissedAt;
        Long _tmpDeletedAt;
        String _tmpCreated_by_user_id;
        String _tmpCreated_by_user_name;
        String _tmpCreated_by_designation;
        String _tmpManaged_by_user_id;
        String _tmpManaged_by_user_name;
        String _tmpManaged_by_designation;
        Long _tmpAssigned_technician_id;
        String _tmpAssigned_technician_name;
        Long _tmpAssigned_at_timestamp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $status);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfCustomerName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerName");
            int _columnIndexOfCustomerPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerPhone");
            int _columnIndexOfServiceType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceType");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
            int _columnIndexOfAssignedExpertId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertId");
            int _columnIndexOfAssignedExpertName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertName");
            int _columnIndexOfAssignedExpertPhone2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertPhone");
            int _tmp_4 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "distanceKmAtDispatch");
            int _columnIndexOfRatingGiven = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingGiven");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reviewFeedback");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfCreatedAt2 = _columnIndexOfCreatedAt;
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedAt");
            int _columnIndexOfIsExpertNotified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isExpertNotified");
            int _columnIndexOfRatingGiven2 = _columnIndexOfIsExpertNotified;
            int _columnIndexOfIsCustomerNotifiedOnAssign = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnAssign");
            int _columnIndexOfIsExpertNotified2 = _columnIndexOfIsCustomerNotifiedOnAssign;
            int _columnIndexOfIsCustomerNotifiedOnAssign2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnCompletion");
            int _columnIndexOfAssignMessageLaterDismissedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignMessageLaterDismissedAt");
            int _columnIndexOfAssignMessageLaterDismissedAt2 = _columnIndexOfAssignMessageLaterDismissedAt;
            int _columnIndexOfIsDeleted2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_2 = _columnIndexOfIsDeleted2;
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfDeletedAt2 = _columnIndexOfDeletedAt;
            int _columnIndexOfDeletedAt3 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            int _tmp_3 = _columnIndexOfIsSynced2;
            int _columnIndexOfCreatedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_id");
            int _columnIndexOfCreatedByUserId2 = _columnIndexOfCreatedByUserId;
            int _columnIndexOfCreatedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_name");
            int _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserName;
            int _columnIndexOfCreatedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_designation");
            int _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByDesignation;
            int _columnIndexOfManagedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_id");
            int _columnIndexOfManagedByUserId2 = _columnIndexOfManagedByUserId;
            int _columnIndexOfManagedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_name");
            int _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserName;
            int _columnIndexOfManagedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_designation");
            int _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByDesignation;
            int _columnIndexOfAssignedTechnicianId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_id");
            int _columnIndexOfAssignedTechnicianId2 = _columnIndexOfAssignedTechnicianId;
            int _columnIndexOfAssignedTechnicianName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_name");
            int _columnIndexOfAssignedTechnicianName2 = _columnIndexOfAssignedTechnicianName;
            int _columnIndexOfAssignedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_at_timestamp");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpCustomerName = _stmt.getText(_columnIndexOfCustomerName);
                String _tmpCustomerPhone = _stmt.getText(_columnIndexOfCustomerPhone);
                String _tmpServiceType = _stmt.getText(_columnIndexOfServiceType);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                if (_stmt.isNull(_columnIndexOfAssignedExpertId)) {
                    _tmpAssignedExpertId = null;
                } else {
                    Long _tmpAssignedExpertId2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedExpertId));
                    _tmpAssignedExpertId = _tmpAssignedExpertId2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertName)) {
                    _tmpAssignedExpertName = null;
                } else {
                    String _tmpAssignedExpertName2 = _stmt.getText(_columnIndexOfAssignedExpertName);
                    _tmpAssignedExpertName = _tmpAssignedExpertName2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertPhone2)) {
                    _tmpAssignedExpertPhone = null;
                } else {
                    String _tmpAssignedExpertPhone2 = _stmt.getText(_columnIndexOfAssignedExpertPhone2);
                    _tmpAssignedExpertPhone = _tmpAssignedExpertPhone2;
                }
                if (_stmt.isNull(_tmp_4)) {
                    _tmpDistanceKmAtDispatch = null;
                } else {
                    Double _tmpDistanceKmAtDispatch2 = Double.valueOf(_stmt.getDouble(_tmp_4));
                    _tmpDistanceKmAtDispatch = _tmpDistanceKmAtDispatch2;
                }
                if (_stmt.isNull(_columnIndexOfRatingGiven)) {
                    _columnIndexOfAssignedExpertPhone = _columnIndexOfAssignedExpertPhone2;
                    _columnIndexOfDistanceKmAtDispatch = _tmp_4;
                    _tmpRatingGiven = null;
                } else {
                    _columnIndexOfAssignedExpertPhone = _columnIndexOfAssignedExpertPhone2;
                    _columnIndexOfDistanceKmAtDispatch = _tmp_4;
                    Float _tmpRatingGiven2 = Float.valueOf((float) _stmt.getDouble(_columnIndexOfRatingGiven));
                    _tmpRatingGiven = _tmpRatingGiven2;
                }
                int _columnIndexOfReviewFeedback = _columnIndexOfIsDeleted;
                if (_stmt.isNull(_columnIndexOfReviewFeedback)) {
                    _tmpReviewFeedback = null;
                } else {
                    String _tmpReviewFeedback2 = _stmt.getText(_columnIndexOfReviewFeedback);
                    _tmpReviewFeedback = _tmpReviewFeedback2;
                }
                int _columnIndexOfCreatedAt3 = _columnIndexOfCreatedAt2;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt3);
                _columnIndexOfCreatedAt2 = _columnIndexOfCreatedAt3;
                int _columnIndexOfCreatedAt4 = _columnIndexOfIsSynced;
                if (_stmt.isNull(_columnIndexOfCreatedAt4)) {
                    _tmpCompletedAt = null;
                } else {
                    Long _tmpCompletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfCreatedAt4));
                    _tmpCompletedAt = _tmpCompletedAt2;
                }
                int _columnIndexOfCompletedAt = _columnIndexOfRatingGiven2;
                int _columnIndexOfIsExpertNotified3 = _columnIndexOfRatingGiven;
                int _tmp = (int) _stmt.getLong(_columnIndexOfCompletedAt);
                boolean _tmpIsExpertNotified = _tmp != 0;
                int _columnIndexOfIsCustomerNotifiedOnAssign3 = _columnIndexOfIsExpertNotified2;
                boolean _tmpIsCustomerNotifiedOnAssign = ((int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnAssign3)) != 0;
                boolean _tmpIsCustomerNotifiedOnAssign2 = _tmpIsCustomerNotifiedOnAssign;
                int _tmp_1 = _columnIndexOfIsCustomerNotifiedOnAssign2;
                int _tmp_22 = (int) _stmt.getLong(_tmp_1);
                boolean _tmpIsCustomerNotifiedOnCompletion = _tmp_22 != 0;
                int _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfAssignMessageLaterDismissedAt2;
                if (_stmt.isNull(_columnIndexOfIsCustomerNotifiedOnCompletion)) {
                    _tmpAssignMessageLaterDismissedAt = null;
                } else {
                    Long _tmpAssignMessageLaterDismissedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsCustomerNotifiedOnCompletion));
                    _tmpAssignMessageLaterDismissedAt = _tmpAssignMessageLaterDismissedAt2;
                }
                boolean _tmpIsCustomerNotifiedOnCompletion2 = _tmpIsCustomerNotifiedOnCompletion;
                int _columnIndexOfAssignMessageLaterDismissedAt3 = _tmp_2;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfAssignMessageLaterDismissedAt3);
                boolean _tmpIsDeleted = _tmp_32 != 0;
                int _columnIndexOfIsDeleted3 = _columnIndexOfDeletedAt2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsDeleted3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfLastUpdated = _columnIndexOfDeletedAt3;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _columnIndexOfDeletedAt4 = _tmp_3;
                int _tmp_42 = (int) _stmt.getLong(_columnIndexOfDeletedAt4);
                boolean _tmpIs_synced = _tmp_42 != 0;
                int _columnIndexOfIsSynced3 = _columnIndexOfCreatedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsSynced3)) {
                    _tmpCreated_by_user_id = null;
                } else {
                    String _tmpCreated_by_user_id2 = _stmt.getText(_columnIndexOfIsSynced3);
                    _tmpCreated_by_user_id = _tmpCreated_by_user_id2;
                }
                _columnIndexOfCreatedByUserId2 = _columnIndexOfIsSynced3;
                int _columnIndexOfCreatedByUserId3 = _columnIndexOfCreatedByUserName2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserId3)) {
                    _tmpCreated_by_user_name = null;
                } else {
                    String _tmpCreated_by_user_name2 = _stmt.getText(_columnIndexOfCreatedByUserId3);
                    _tmpCreated_by_user_name = _tmpCreated_by_user_name2;
                }
                _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserId3;
                int _columnIndexOfCreatedByUserName3 = _columnIndexOfCreatedByDesignation2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserName3)) {
                    _tmpCreated_by_designation = null;
                } else {
                    String _tmpCreated_by_designation2 = _stmt.getText(_columnIndexOfCreatedByUserName3);
                    _tmpCreated_by_designation = _tmpCreated_by_designation2;
                }
                _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByUserName3;
                int _columnIndexOfCreatedByDesignation3 = _columnIndexOfManagedByUserId2;
                if (_stmt.isNull(_columnIndexOfCreatedByDesignation3)) {
                    _tmpManaged_by_user_id = null;
                } else {
                    String _tmpManaged_by_user_id2 = _stmt.getText(_columnIndexOfCreatedByDesignation3);
                    _tmpManaged_by_user_id = _tmpManaged_by_user_id2;
                }
                _columnIndexOfManagedByUserId2 = _columnIndexOfCreatedByDesignation3;
                int _columnIndexOfManagedByUserId3 = _columnIndexOfManagedByUserName2;
                if (_stmt.isNull(_columnIndexOfManagedByUserId3)) {
                    _tmpManaged_by_user_name = null;
                } else {
                    String _tmpManaged_by_user_name2 = _stmt.getText(_columnIndexOfManagedByUserId3);
                    _tmpManaged_by_user_name = _tmpManaged_by_user_name2;
                }
                _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserId3;
                int _columnIndexOfManagedByUserName3 = _columnIndexOfManagedByDesignation2;
                if (_stmt.isNull(_columnIndexOfManagedByUserName3)) {
                    _tmpManaged_by_designation = null;
                } else {
                    String _tmpManaged_by_designation2 = _stmt.getText(_columnIndexOfManagedByUserName3);
                    _tmpManaged_by_designation = _tmpManaged_by_designation2;
                }
                _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByUserName3;
                int _columnIndexOfManagedByDesignation3 = _columnIndexOfAssignedTechnicianId2;
                if (_stmt.isNull(_columnIndexOfManagedByDesignation3)) {
                    _tmpAssigned_technician_id = null;
                } else {
                    Long _tmpAssigned_technician_id2 = Long.valueOf(_stmt.getLong(_columnIndexOfManagedByDesignation3));
                    _tmpAssigned_technician_id = _tmpAssigned_technician_id2;
                }
                _columnIndexOfAssignedTechnicianId2 = _columnIndexOfManagedByDesignation3;
                int _columnIndexOfAssignedTechnicianId3 = _columnIndexOfAssignedTechnicianName2;
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianId3)) {
                    _tmpAssigned_technician_name = null;
                } else {
                    String _tmpAssigned_technician_name2 = _stmt.getText(_columnIndexOfAssignedTechnicianId3);
                    _tmpAssigned_technician_name = _tmpAssigned_technician_name2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedAtTimestamp)) {
                    _tmpAssigned_at_timestamp = null;
                } else {
                    Long _tmpAssigned_at_timestamp2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedAtTimestamp));
                    _tmpAssigned_at_timestamp = _tmpAssigned_at_timestamp2;
                }
                CustomerJobEntity _item = new CustomerJobEntity(_tmpId, _tmpCustomerName, _tmpCustomerPhone, _tmpServiceType, _tmpIssueDescription, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpStatus, _tmpAssignedExpertId, _tmpAssignedExpertName, _tmpAssignedExpertPhone, _tmpDistanceKmAtDispatch, _tmpRatingGiven, _tmpReviewFeedback, _tmpCreatedAt, _tmpCompletedAt, _tmpIsExpertNotified, _tmpIsCustomerNotifiedOnAssign2, _tmpIsCustomerNotifiedOnCompletion2, _tmpAssignMessageLaterDismissedAt, _tmpIsDeleted, _tmpDeletedAt, _tmpLast_updated, _tmpIs_synced, _tmpCreated_by_user_id, _tmpCreated_by_user_name, _tmpCreated_by_designation, _tmpManaged_by_user_id, _tmpManaged_by_user_name, _tmpManaged_by_designation, _tmpAssigned_technician_id, _tmpAssigned_technician_name, _tmpAssigned_at_timestamp);
                _columnIndexOfAssignedTechnicianName2 = _columnIndexOfAssignedTechnicianId3;
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _tmp_2 = _columnIndexOfAssignMessageLaterDismissedAt3;
                _tmp_3 = _columnIndexOfDeletedAt4;
                _columnIndexOfRatingGiven = _columnIndexOfIsExpertNotified3;
                _columnIndexOfRatingGiven2 = _columnIndexOfCompletedAt;
                _columnIndexOfIsExpertNotified2 = _columnIndexOfIsCustomerNotifiedOnAssign3;
                _columnIndexOfDeletedAt2 = _columnIndexOfIsDeleted3;
                _columnIndexOfIsSynced = _columnIndexOfCreatedAt4;
                _columnIndexOfAssignedExpertPhone2 = _columnIndexOfAssignedExpertPhone;
                _tmp_4 = _columnIndexOfDistanceKmAtDispatch;
                _columnIndexOfIsDeleted = _columnIndexOfReviewFeedback;
                _columnIndexOfIsCustomerNotifiedOnAssign2 = _tmp_1;
                _columnIndexOfAssignMessageLaterDismissedAt2 = _columnIndexOfIsCustomerNotifiedOnCompletion;
                _columnIndexOfDeletedAt3 = _columnIndexOfLastUpdated;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Flow<List<CustomerJobEntity>> getJobsForExpert(final long expertId) {
        final String _sql = "SELECT * FROM customer_jobs WHERE assignedExpertId = ? AND isDeleted = 0 ORDER BY createdAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{FirestoreSyncManager.JOBS_COLLECTION}, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda18
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.getJobsForExpert$lambda$6(_sql, expertId, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getJobsForExpert$lambda$6(String $_sql, long $expertId, SQLiteConnection _connection) {
        Long _tmpAssignedExpertId;
        String _tmpAssignedExpertName;
        String _tmpAssignedExpertPhone;
        Double _tmpDistanceKmAtDispatch;
        int _columnIndexOfAssignedExpertName;
        int _columnIndexOfAssignedExpertPhone;
        Float _tmpRatingGiven;
        String _tmpReviewFeedback;
        Long _tmpCompletedAt;
        Long _tmpAssignMessageLaterDismissedAt;
        Long _tmpDeletedAt;
        String _tmpCreated_by_user_id;
        String _tmpCreated_by_user_name;
        String _tmpCreated_by_designation;
        String _tmpManaged_by_user_id;
        String _tmpManaged_by_user_name;
        String _tmpManaged_by_designation;
        Long _tmpAssigned_technician_id;
        String _tmpAssigned_technician_name;
        Long _tmpAssigned_at_timestamp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $expertId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfCustomerName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerName");
            int _columnIndexOfCustomerPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerPhone");
            int _columnIndexOfServiceType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceType");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
            int _columnIndexOfAssignedExpertId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertId");
            int _columnIndexOfAssignedExpertName2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertName");
            int _tmp_4 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertPhone");
            int _columnIndexOfDistanceKmAtDispatch = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "distanceKmAtDispatch");
            int _columnIndexOfRatingGiven = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingGiven");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reviewFeedback");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfCreatedAt2 = _columnIndexOfCreatedAt;
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedAt");
            int _columnIndexOfIsExpertNotified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isExpertNotified");
            int _columnIndexOfDistanceKmAtDispatch2 = _columnIndexOfIsExpertNotified;
            int _columnIndexOfIsCustomerNotifiedOnAssign = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnAssign");
            int _columnIndexOfIsExpertNotified2 = _columnIndexOfIsCustomerNotifiedOnAssign;
            int _columnIndexOfIsCustomerNotifiedOnAssign2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnCompletion");
            int _columnIndexOfAssignMessageLaterDismissedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignMessageLaterDismissedAt");
            int _columnIndexOfAssignMessageLaterDismissedAt2 = _columnIndexOfAssignMessageLaterDismissedAt;
            int _columnIndexOfIsDeleted2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_2 = _columnIndexOfIsDeleted2;
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfDeletedAt2 = _columnIndexOfDeletedAt;
            int _columnIndexOfDeletedAt3 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            int _tmp_3 = _columnIndexOfIsSynced2;
            int _columnIndexOfCreatedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_id");
            int _columnIndexOfCreatedByUserId2 = _columnIndexOfCreatedByUserId;
            int _columnIndexOfCreatedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_name");
            int _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserName;
            int _columnIndexOfCreatedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_designation");
            int _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByDesignation;
            int _columnIndexOfManagedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_id");
            int _columnIndexOfManagedByUserId2 = _columnIndexOfManagedByUserId;
            int _columnIndexOfManagedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_name");
            int _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserName;
            int _columnIndexOfManagedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_designation");
            int _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByDesignation;
            int _columnIndexOfAssignedTechnicianId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_id");
            int _columnIndexOfAssignedTechnicianId2 = _columnIndexOfAssignedTechnicianId;
            int _columnIndexOfAssignedTechnicianName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_name");
            int _columnIndexOfAssignedTechnicianName2 = _columnIndexOfAssignedTechnicianName;
            int _columnIndexOfAssignedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_at_timestamp");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpCustomerName = _stmt.getText(_columnIndexOfCustomerName);
                String _tmpCustomerPhone = _stmt.getText(_columnIndexOfCustomerPhone);
                String _tmpServiceType = _stmt.getText(_columnIndexOfServiceType);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                if (_stmt.isNull(_columnIndexOfAssignedExpertId)) {
                    _tmpAssignedExpertId = null;
                } else {
                    Long _tmpAssignedExpertId2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedExpertId));
                    _tmpAssignedExpertId = _tmpAssignedExpertId2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertName2)) {
                    _tmpAssignedExpertName = null;
                } else {
                    String _tmpAssignedExpertName2 = _stmt.getText(_columnIndexOfAssignedExpertName2);
                    _tmpAssignedExpertName = _tmpAssignedExpertName2;
                }
                if (_stmt.isNull(_tmp_4)) {
                    _tmpAssignedExpertPhone = null;
                } else {
                    String _tmpAssignedExpertPhone2 = _stmt.getText(_tmp_4);
                    _tmpAssignedExpertPhone = _tmpAssignedExpertPhone2;
                }
                if (_stmt.isNull(_columnIndexOfDistanceKmAtDispatch)) {
                    _tmpDistanceKmAtDispatch = null;
                } else {
                    Double _tmpDistanceKmAtDispatch2 = Double.valueOf(_stmt.getDouble(_columnIndexOfDistanceKmAtDispatch));
                    _tmpDistanceKmAtDispatch = _tmpDistanceKmAtDispatch2;
                }
                if (_stmt.isNull(_columnIndexOfRatingGiven)) {
                    _columnIndexOfAssignedExpertName = _columnIndexOfAssignedExpertName2;
                    _columnIndexOfAssignedExpertPhone = _tmp_4;
                    _tmpRatingGiven = null;
                } else {
                    _columnIndexOfAssignedExpertName = _columnIndexOfAssignedExpertName2;
                    _columnIndexOfAssignedExpertPhone = _tmp_4;
                    Float _tmpRatingGiven2 = Float.valueOf((float) _stmt.getDouble(_columnIndexOfRatingGiven));
                    _tmpRatingGiven = _tmpRatingGiven2;
                }
                int _columnIndexOfReviewFeedback = _columnIndexOfIsDeleted;
                if (_stmt.isNull(_columnIndexOfReviewFeedback)) {
                    _tmpReviewFeedback = null;
                } else {
                    String _tmpReviewFeedback2 = _stmt.getText(_columnIndexOfReviewFeedback);
                    _tmpReviewFeedback = _tmpReviewFeedback2;
                }
                int _columnIndexOfCreatedAt3 = _columnIndexOfCreatedAt2;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt3);
                _columnIndexOfCreatedAt2 = _columnIndexOfCreatedAt3;
                int _columnIndexOfCreatedAt4 = _columnIndexOfIsSynced;
                if (_stmt.isNull(_columnIndexOfCreatedAt4)) {
                    _tmpCompletedAt = null;
                } else {
                    Long _tmpCompletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfCreatedAt4));
                    _tmpCompletedAt = _tmpCompletedAt2;
                }
                int _columnIndexOfCompletedAt = _columnIndexOfDistanceKmAtDispatch2;
                int _columnIndexOfIsExpertNotified3 = _columnIndexOfDistanceKmAtDispatch;
                int _tmp = (int) _stmt.getLong(_columnIndexOfCompletedAt);
                boolean _tmpIsExpertNotified = _tmp != 0;
                int _columnIndexOfIsCustomerNotifiedOnAssign3 = _columnIndexOfIsExpertNotified2;
                boolean _tmpIsCustomerNotifiedOnAssign = ((int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnAssign3)) != 0;
                boolean _tmpIsCustomerNotifiedOnAssign2 = _tmpIsCustomerNotifiedOnAssign;
                int _tmp_1 = _columnIndexOfIsCustomerNotifiedOnAssign2;
                int _tmp_22 = (int) _stmt.getLong(_tmp_1);
                boolean _tmpIsCustomerNotifiedOnCompletion = _tmp_22 != 0;
                int _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfAssignMessageLaterDismissedAt2;
                if (_stmt.isNull(_columnIndexOfIsCustomerNotifiedOnCompletion)) {
                    _tmpAssignMessageLaterDismissedAt = null;
                } else {
                    Long _tmpAssignMessageLaterDismissedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsCustomerNotifiedOnCompletion));
                    _tmpAssignMessageLaterDismissedAt = _tmpAssignMessageLaterDismissedAt2;
                }
                boolean _tmpIsCustomerNotifiedOnCompletion2 = _tmpIsCustomerNotifiedOnCompletion;
                int _columnIndexOfAssignMessageLaterDismissedAt3 = _tmp_2;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfAssignMessageLaterDismissedAt3);
                boolean _tmpIsDeleted = _tmp_32 != 0;
                int _columnIndexOfIsDeleted3 = _columnIndexOfDeletedAt2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsDeleted3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfLastUpdated = _columnIndexOfDeletedAt3;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _columnIndexOfDeletedAt4 = _tmp_3;
                int _tmp_42 = (int) _stmt.getLong(_columnIndexOfDeletedAt4);
                boolean _tmpIs_synced = _tmp_42 != 0;
                int _columnIndexOfIsSynced3 = _columnIndexOfCreatedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsSynced3)) {
                    _tmpCreated_by_user_id = null;
                } else {
                    String _tmpCreated_by_user_id2 = _stmt.getText(_columnIndexOfIsSynced3);
                    _tmpCreated_by_user_id = _tmpCreated_by_user_id2;
                }
                _columnIndexOfCreatedByUserId2 = _columnIndexOfIsSynced3;
                int _columnIndexOfCreatedByUserId3 = _columnIndexOfCreatedByUserName2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserId3)) {
                    _tmpCreated_by_user_name = null;
                } else {
                    String _tmpCreated_by_user_name2 = _stmt.getText(_columnIndexOfCreatedByUserId3);
                    _tmpCreated_by_user_name = _tmpCreated_by_user_name2;
                }
                _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserId3;
                int _columnIndexOfCreatedByUserName3 = _columnIndexOfCreatedByDesignation2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserName3)) {
                    _tmpCreated_by_designation = null;
                } else {
                    String _tmpCreated_by_designation2 = _stmt.getText(_columnIndexOfCreatedByUserName3);
                    _tmpCreated_by_designation = _tmpCreated_by_designation2;
                }
                _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByUserName3;
                int _columnIndexOfCreatedByDesignation3 = _columnIndexOfManagedByUserId2;
                if (_stmt.isNull(_columnIndexOfCreatedByDesignation3)) {
                    _tmpManaged_by_user_id = null;
                } else {
                    String _tmpManaged_by_user_id2 = _stmt.getText(_columnIndexOfCreatedByDesignation3);
                    _tmpManaged_by_user_id = _tmpManaged_by_user_id2;
                }
                _columnIndexOfManagedByUserId2 = _columnIndexOfCreatedByDesignation3;
                int _columnIndexOfManagedByUserId3 = _columnIndexOfManagedByUserName2;
                if (_stmt.isNull(_columnIndexOfManagedByUserId3)) {
                    _tmpManaged_by_user_name = null;
                } else {
                    String _tmpManaged_by_user_name2 = _stmt.getText(_columnIndexOfManagedByUserId3);
                    _tmpManaged_by_user_name = _tmpManaged_by_user_name2;
                }
                _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserId3;
                int _columnIndexOfManagedByUserName3 = _columnIndexOfManagedByDesignation2;
                if (_stmt.isNull(_columnIndexOfManagedByUserName3)) {
                    _tmpManaged_by_designation = null;
                } else {
                    String _tmpManaged_by_designation2 = _stmt.getText(_columnIndexOfManagedByUserName3);
                    _tmpManaged_by_designation = _tmpManaged_by_designation2;
                }
                _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByUserName3;
                int _columnIndexOfManagedByDesignation3 = _columnIndexOfAssignedTechnicianId2;
                if (_stmt.isNull(_columnIndexOfManagedByDesignation3)) {
                    _tmpAssigned_technician_id = null;
                } else {
                    Long _tmpAssigned_technician_id2 = Long.valueOf(_stmt.getLong(_columnIndexOfManagedByDesignation3));
                    _tmpAssigned_technician_id = _tmpAssigned_technician_id2;
                }
                _columnIndexOfAssignedTechnicianId2 = _columnIndexOfManagedByDesignation3;
                int _columnIndexOfAssignedTechnicianId3 = _columnIndexOfAssignedTechnicianName2;
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianId3)) {
                    _tmpAssigned_technician_name = null;
                } else {
                    String _tmpAssigned_technician_name2 = _stmt.getText(_columnIndexOfAssignedTechnicianId3);
                    _tmpAssigned_technician_name = _tmpAssigned_technician_name2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedAtTimestamp)) {
                    _tmpAssigned_at_timestamp = null;
                } else {
                    Long _tmpAssigned_at_timestamp2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedAtTimestamp));
                    _tmpAssigned_at_timestamp = _tmpAssigned_at_timestamp2;
                }
                CustomerJobEntity _item = new CustomerJobEntity(_tmpId, _tmpCustomerName, _tmpCustomerPhone, _tmpServiceType, _tmpIssueDescription, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpStatus, _tmpAssignedExpertId, _tmpAssignedExpertName, _tmpAssignedExpertPhone, _tmpDistanceKmAtDispatch, _tmpRatingGiven, _tmpReviewFeedback, _tmpCreatedAt, _tmpCompletedAt, _tmpIsExpertNotified, _tmpIsCustomerNotifiedOnAssign2, _tmpIsCustomerNotifiedOnCompletion2, _tmpAssignMessageLaterDismissedAt, _tmpIsDeleted, _tmpDeletedAt, _tmpLast_updated, _tmpIs_synced, _tmpCreated_by_user_id, _tmpCreated_by_user_name, _tmpCreated_by_designation, _tmpManaged_by_user_id, _tmpManaged_by_user_name, _tmpManaged_by_designation, _tmpAssigned_technician_id, _tmpAssigned_technician_name, _tmpAssigned_at_timestamp);
                _columnIndexOfAssignedTechnicianName2 = _columnIndexOfAssignedTechnicianId3;
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _tmp_2 = _columnIndexOfAssignMessageLaterDismissedAt3;
                _tmp_3 = _columnIndexOfDeletedAt4;
                _columnIndexOfDistanceKmAtDispatch = _columnIndexOfIsExpertNotified3;
                _columnIndexOfDistanceKmAtDispatch2 = _columnIndexOfCompletedAt;
                _columnIndexOfIsExpertNotified2 = _columnIndexOfIsCustomerNotifiedOnAssign3;
                _columnIndexOfDeletedAt2 = _columnIndexOfIsDeleted3;
                _columnIndexOfIsSynced = _columnIndexOfCreatedAt4;
                _columnIndexOfAssignedExpertName2 = _columnIndexOfAssignedExpertName;
                _tmp_4 = _columnIndexOfAssignedExpertPhone;
                _columnIndexOfIsDeleted = _columnIndexOfReviewFeedback;
                _columnIndexOfIsCustomerNotifiedOnAssign2 = _tmp_1;
                _columnIndexOfAssignMessageLaterDismissedAt2 = _columnIndexOfIsCustomerNotifiedOnCompletion;
                _columnIndexOfDeletedAt3 = _columnIndexOfLastUpdated;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Flow<List<CustomerJobEntity>> getDeletedJobs() {
        final String _sql = "SELECT * FROM customer_jobs WHERE isDeleted = 1 ORDER BY deletedAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{FirestoreSyncManager.JOBS_COLLECTION}, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda2
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.getDeletedJobs$lambda$7(_sql, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getDeletedJobs$lambda$7(String $_sql, SQLiteConnection _connection) {
        Long _tmpAssignedExpertId;
        String _tmpAssignedExpertName;
        String _tmpAssignedExpertPhone;
        Double _tmpDistanceKmAtDispatch;
        int _columnIndexOfCustomerName;
        int _columnIndexOfCustomerPhone;
        Float _tmpRatingGiven;
        String _tmpReviewFeedback;
        Long _tmpCompletedAt;
        Long _tmpAssignMessageLaterDismissedAt;
        Long _tmpDeletedAt;
        String _tmpCreated_by_user_id;
        String _tmpCreated_by_user_name;
        String _tmpCreated_by_designation;
        String _tmpManaged_by_user_id;
        String _tmpManaged_by_user_name;
        String _tmpManaged_by_designation;
        Long _tmpAssigned_technician_id;
        String _tmpAssigned_technician_name;
        Long _tmpAssigned_at_timestamp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfAssignedTechnicianName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _tmp_4 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerName");
            int _columnIndexOfCustomerPhone2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerPhone");
            int _columnIndexOfServiceType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceType");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
            int _columnIndexOfAssignedExpertId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertId");
            int _columnIndexOfAssignedExpertName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertName");
            int _columnIndexOfAssignedExpertPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertPhone");
            int _columnIndexOfDistanceKmAtDispatch = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "distanceKmAtDispatch");
            int _columnIndexOfRatingGiven = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingGiven");
            int _columnIndexOfReviewFeedback = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reviewFeedback");
            int _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfReviewFeedback;
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedAt");
            int _columnIndexOfIsExpertNotified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isExpertNotified");
            int _columnIndexOfReviewFeedback2 = _columnIndexOfIsExpertNotified;
            int _columnIndexOfRatingGiven2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnAssign");
            int _columnIndexOfIsCustomerNotifiedOnCompletion2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnCompletion");
            int _tmp = _columnIndexOfIsCustomerNotifiedOnCompletion2;
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignMessageLaterDismissedAt");
            int _columnIndexOfIsDeleted2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_2 = _columnIndexOfIsDeleted2;
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfDeletedAt2 = _columnIndexOfDeletedAt;
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfLastUpdated2 = _columnIndexOfLastUpdated;
            int _columnIndexOfIsSynced2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            int _tmp_3 = _columnIndexOfIsSynced2;
            int _columnIndexOfCreatedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_id");
            int _columnIndexOfCreatedByUserId2 = _columnIndexOfCreatedByUserId;
            int _columnIndexOfCreatedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_name");
            int _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserName;
            int _columnIndexOfCreatedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_designation");
            int _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByDesignation;
            int _columnIndexOfManagedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_id");
            int _columnIndexOfManagedByUserId2 = _columnIndexOfManagedByUserId;
            int _columnIndexOfManagedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_name");
            int _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserName;
            int _columnIndexOfManagedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_designation");
            int _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByDesignation;
            int _columnIndexOfAssignedTechnicianId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_id");
            int _columnIndexOfAssignedTechnicianId2 = _columnIndexOfAssignedTechnicianId;
            int _columnIndexOfAssignedTechnicianName2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_name");
            int _columnIndexOfAssignedTechnicianName3 = _columnIndexOfAssignedTechnicianName2;
            int _columnIndexOfAssignedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_at_timestamp");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfAssignedTechnicianName);
                String _tmpCustomerName = _stmt.getText(_tmp_4);
                String _tmpCustomerPhone = _stmt.getText(_columnIndexOfCustomerPhone2);
                String _tmpServiceType = _stmt.getText(_columnIndexOfServiceType);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                if (_stmt.isNull(_columnIndexOfAssignedExpertId)) {
                    _tmpAssignedExpertId = null;
                } else {
                    Long _tmpAssignedExpertId2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedExpertId));
                    _tmpAssignedExpertId = _tmpAssignedExpertId2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertName)) {
                    _tmpAssignedExpertName = null;
                } else {
                    String _tmpAssignedExpertName2 = _stmt.getText(_columnIndexOfAssignedExpertName);
                    _tmpAssignedExpertName = _tmpAssignedExpertName2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertPhone)) {
                    _tmpAssignedExpertPhone = null;
                } else {
                    String _tmpAssignedExpertPhone2 = _stmt.getText(_columnIndexOfAssignedExpertPhone);
                    _tmpAssignedExpertPhone = _tmpAssignedExpertPhone2;
                }
                if (_stmt.isNull(_columnIndexOfDistanceKmAtDispatch)) {
                    _tmpDistanceKmAtDispatch = null;
                } else {
                    Double _tmpDistanceKmAtDispatch2 = Double.valueOf(_stmt.getDouble(_columnIndexOfDistanceKmAtDispatch));
                    _tmpDistanceKmAtDispatch = _tmpDistanceKmAtDispatch2;
                }
                if (_stmt.isNull(_columnIndexOfRatingGiven)) {
                    _columnIndexOfCustomerName = _tmp_4;
                    _columnIndexOfCustomerPhone = _columnIndexOfCustomerPhone2;
                    _tmpRatingGiven = null;
                } else {
                    _columnIndexOfCustomerName = _tmp_4;
                    _columnIndexOfCustomerPhone = _columnIndexOfCustomerPhone2;
                    Float _tmpRatingGiven2 = Float.valueOf((float) _stmt.getDouble(_columnIndexOfRatingGiven));
                    _tmpRatingGiven = _tmpRatingGiven2;
                }
                int _columnIndexOfReviewFeedback3 = _columnIndexOfIsCustomerNotifiedOnCompletion;
                if (_stmt.isNull(_columnIndexOfReviewFeedback3)) {
                    _tmpReviewFeedback = null;
                } else {
                    String _tmpReviewFeedback2 = _stmt.getText(_columnIndexOfReviewFeedback3);
                    _tmpReviewFeedback = _tmpReviewFeedback2;
                }
                int _columnIndexOfCreatedAt = _columnIndexOfId;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                int _columnIndexOfId2 = _columnIndexOfAssignedTechnicianName;
                int _columnIndexOfId3 = _columnIndexOfIsSynced;
                if (_stmt.isNull(_columnIndexOfId3)) {
                    _tmpCompletedAt = null;
                } else {
                    Long _tmpCompletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfId3));
                    _tmpCompletedAt = _tmpCompletedAt2;
                }
                int _columnIndexOfCompletedAt = _columnIndexOfReviewFeedback2;
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfCompletedAt);
                boolean _tmpIsExpertNotified = _tmp2 != 0;
                int _columnIndexOfIsCustomerNotifiedOnAssign = _columnIndexOfRatingGiven2;
                int _columnIndexOfIsCustomerNotifiedOnAssign2 = _columnIndexOfRatingGiven;
                boolean _tmpIsCustomerNotifiedOnAssign = ((int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnAssign)) != 0;
                int _tmp_1 = _tmp;
                int _tmp_22 = (int) _stmt.getLong(_tmp_1);
                boolean _tmpIsCustomerNotifiedOnCompletion = _tmp_22 != 0;
                int _columnIndexOfIsCustomerNotifiedOnCompletion3 = _columnIndexOfIsDeleted;
                if (_stmt.isNull(_columnIndexOfIsCustomerNotifiedOnCompletion3)) {
                    _tmpAssignMessageLaterDismissedAt = null;
                } else {
                    Long _tmpAssignMessageLaterDismissedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsCustomerNotifiedOnCompletion3));
                    _tmpAssignMessageLaterDismissedAt = _tmpAssignMessageLaterDismissedAt2;
                }
                int _columnIndexOfAssignMessageLaterDismissedAt = _tmp_2;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfAssignMessageLaterDismissedAt);
                boolean _tmpIsDeleted = _tmp_32 != 0;
                int _columnIndexOfIsDeleted3 = _columnIndexOfDeletedAt2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsDeleted3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfLastUpdated3 = _columnIndexOfLastUpdated2;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated3);
                int _columnIndexOfDeletedAt3 = _tmp_3;
                int _tmp_42 = (int) _stmt.getLong(_columnIndexOfDeletedAt3);
                boolean _tmpIs_synced = _tmp_42 != 0;
                int _columnIndexOfIsSynced3 = _columnIndexOfCreatedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsSynced3)) {
                    _tmpCreated_by_user_id = null;
                } else {
                    String _tmpCreated_by_user_id2 = _stmt.getText(_columnIndexOfIsSynced3);
                    _tmpCreated_by_user_id = _tmpCreated_by_user_id2;
                }
                _columnIndexOfCreatedByUserId2 = _columnIndexOfIsSynced3;
                int _columnIndexOfCreatedByUserId3 = _columnIndexOfCreatedByUserName2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserId3)) {
                    _tmpCreated_by_user_name = null;
                } else {
                    String _tmpCreated_by_user_name2 = _stmt.getText(_columnIndexOfCreatedByUserId3);
                    _tmpCreated_by_user_name = _tmpCreated_by_user_name2;
                }
                _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserId3;
                int _columnIndexOfCreatedByUserName3 = _columnIndexOfCreatedByDesignation2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserName3)) {
                    _tmpCreated_by_designation = null;
                } else {
                    String _tmpCreated_by_designation2 = _stmt.getText(_columnIndexOfCreatedByUserName3);
                    _tmpCreated_by_designation = _tmpCreated_by_designation2;
                }
                _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByUserName3;
                int _columnIndexOfCreatedByDesignation3 = _columnIndexOfManagedByUserId2;
                if (_stmt.isNull(_columnIndexOfCreatedByDesignation3)) {
                    _tmpManaged_by_user_id = null;
                } else {
                    String _tmpManaged_by_user_id2 = _stmt.getText(_columnIndexOfCreatedByDesignation3);
                    _tmpManaged_by_user_id = _tmpManaged_by_user_id2;
                }
                _columnIndexOfManagedByUserId2 = _columnIndexOfCreatedByDesignation3;
                int _columnIndexOfManagedByUserId3 = _columnIndexOfManagedByUserName2;
                if (_stmt.isNull(_columnIndexOfManagedByUserId3)) {
                    _tmpManaged_by_user_name = null;
                } else {
                    String _tmpManaged_by_user_name2 = _stmt.getText(_columnIndexOfManagedByUserId3);
                    _tmpManaged_by_user_name = _tmpManaged_by_user_name2;
                }
                _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserId3;
                int _columnIndexOfManagedByUserName3 = _columnIndexOfManagedByDesignation2;
                if (_stmt.isNull(_columnIndexOfManagedByUserName3)) {
                    _tmpManaged_by_designation = null;
                } else {
                    String _tmpManaged_by_designation2 = _stmt.getText(_columnIndexOfManagedByUserName3);
                    _tmpManaged_by_designation = _tmpManaged_by_designation2;
                }
                _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByUserName3;
                int _columnIndexOfManagedByDesignation3 = _columnIndexOfAssignedTechnicianId2;
                if (_stmt.isNull(_columnIndexOfManagedByDesignation3)) {
                    _tmpAssigned_technician_id = null;
                } else {
                    Long _tmpAssigned_technician_id2 = Long.valueOf(_stmt.getLong(_columnIndexOfManagedByDesignation3));
                    _tmpAssigned_technician_id = _tmpAssigned_technician_id2;
                }
                _columnIndexOfAssignedTechnicianId2 = _columnIndexOfManagedByDesignation3;
                int _columnIndexOfAssignedTechnicianId3 = _columnIndexOfAssignedTechnicianName3;
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianId3)) {
                    _tmpAssigned_technician_name = null;
                } else {
                    String _tmpAssigned_technician_name2 = _stmt.getText(_columnIndexOfAssignedTechnicianId3);
                    _tmpAssigned_technician_name = _tmpAssigned_technician_name2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedAtTimestamp)) {
                    _tmpAssigned_at_timestamp = null;
                } else {
                    Long _tmpAssigned_at_timestamp2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedAtTimestamp));
                    _tmpAssigned_at_timestamp = _tmpAssigned_at_timestamp2;
                }
                CustomerJobEntity _item = new CustomerJobEntity(_tmpId, _tmpCustomerName, _tmpCustomerPhone, _tmpServiceType, _tmpIssueDescription, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpStatus, _tmpAssignedExpertId, _tmpAssignedExpertName, _tmpAssignedExpertPhone, _tmpDistanceKmAtDispatch, _tmpRatingGiven, _tmpReviewFeedback, _tmpCreatedAt, _tmpCompletedAt, _tmpIsExpertNotified, _tmpIsCustomerNotifiedOnAssign, _tmpIsCustomerNotifiedOnCompletion, _tmpAssignMessageLaterDismissedAt, _tmpIsDeleted, _tmpDeletedAt, _tmpLast_updated, _tmpIs_synced, _tmpCreated_by_user_id, _tmpCreated_by_user_name, _tmpCreated_by_designation, _tmpManaged_by_user_id, _tmpManaged_by_user_name, _tmpManaged_by_designation, _tmpAssigned_technician_id, _tmpAssigned_technician_name, _tmpAssigned_at_timestamp);
                List _result2 = _result;
                _result2.add(_item);
                _columnIndexOfAssignedTechnicianName3 = _columnIndexOfAssignedTechnicianId3;
                _result = _result2;
                _tmp = _tmp_1;
                _columnIndexOfAssignedTechnicianName = _columnIndexOfId2;
                _tmp_3 = _columnIndexOfDeletedAt3;
                _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfReviewFeedback3;
                _columnIndexOfRatingGiven = _columnIndexOfIsCustomerNotifiedOnAssign2;
                _tmp_2 = _columnIndexOfAssignMessageLaterDismissedAt;
                _columnIndexOfIsSynced = _columnIndexOfId3;
                _tmp_4 = _columnIndexOfCustomerName;
                _columnIndexOfCustomerPhone2 = _columnIndexOfCustomerPhone;
                _columnIndexOfId = _columnIndexOfCreatedAt;
                _columnIndexOfReviewFeedback2 = _columnIndexOfCompletedAt;
                _columnIndexOfRatingGiven2 = _columnIndexOfIsCustomerNotifiedOnAssign;
                _columnIndexOfIsDeleted = _columnIndexOfIsCustomerNotifiedOnCompletion3;
                _columnIndexOfDeletedAt2 = _columnIndexOfIsDeleted3;
                _columnIndexOfLastUpdated2 = _columnIndexOfLastUpdated3;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object getJobById(final long id, Continuation<? super CustomerJobEntity> continuation) {
        final String _sql = "SELECT * FROM customer_jobs WHERE id = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda11
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.getJobById$lambda$8(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final CustomerJobEntity getJobById$lambda$8(String $_sql, long $id, SQLiteConnection _connection) {
        CustomerJobEntity _result;
        Long _tmpAssignedExpertId;
        String _tmpAssignedExpertName;
        String _tmpAssignedExpertPhone;
        Double _tmpDistanceKmAtDispatch;
        Float _tmpRatingGiven;
        String _tmpReviewFeedback;
        Long _tmpCompletedAt;
        Long _tmpAssignMessageLaterDismissedAt;
        Long _tmpDeletedAt;
        String _tmpCreated_by_user_id;
        String _tmpCreated_by_user_name;
        String _tmpCreated_by_designation;
        String _tmpManaged_by_user_id;
        String _tmpManaged_by_user_name;
        String _tmpManaged_by_designation;
        Long _tmpAssigned_technician_id;
        String _tmpAssigned_technician_name;
        Long _tmpAssigned_at_timestamp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $id);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfCustomerName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerName");
            int _columnIndexOfCustomerPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerPhone");
            int _columnIndexOfServiceType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceType");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
            int _columnIndexOfAssignedExpertId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertId");
            int _columnIndexOfAssignedExpertName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertName");
            int _columnIndexOfAssignedExpertPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertPhone");
            int _columnIndexOfDistanceKmAtDispatch = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "distanceKmAtDispatch");
            int _columnIndexOfRatingGiven = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingGiven");
            int _columnIndexOfReviewFeedback = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reviewFeedback");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfCompletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedAt");
            int _columnIndexOfIsExpertNotified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isExpertNotified");
            int _columnIndexOfIsCustomerNotifiedOnAssign = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnAssign");
            int _columnIndexOfIsCustomerNotifiedOnCompletion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnCompletion");
            int _columnIndexOfAssignMessageLaterDismissedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignMessageLaterDismissedAt");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            int _columnIndexOfCreatedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_id");
            int _columnIndexOfCreatedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_name");
            int _columnIndexOfCreatedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_designation");
            int _columnIndexOfManagedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_id");
            int _columnIndexOfManagedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_name");
            int _columnIndexOfManagedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_designation");
            int _columnIndexOfAssignedTechnicianId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_id");
            int _columnIndexOfAssignedTechnicianName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_name");
            int _columnIndexOfAssignedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_at_timestamp");
            if (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpCustomerName = _stmt.getText(_columnIndexOfCustomerName);
                String _tmpCustomerPhone = _stmt.getText(_columnIndexOfCustomerPhone);
                String _tmpServiceType = _stmt.getText(_columnIndexOfServiceType);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                if (_stmt.isNull(_columnIndexOfAssignedExpertId)) {
                    _tmpAssignedExpertId = null;
                } else {
                    Long _tmpAssignedExpertId2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedExpertId));
                    _tmpAssignedExpertId = _tmpAssignedExpertId2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertName)) {
                    _tmpAssignedExpertName = null;
                } else {
                    String _tmpAssignedExpertName2 = _stmt.getText(_columnIndexOfAssignedExpertName);
                    _tmpAssignedExpertName = _tmpAssignedExpertName2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertPhone)) {
                    _tmpAssignedExpertPhone = null;
                } else {
                    String _tmpAssignedExpertPhone2 = _stmt.getText(_columnIndexOfAssignedExpertPhone);
                    _tmpAssignedExpertPhone = _tmpAssignedExpertPhone2;
                }
                if (_stmt.isNull(_columnIndexOfDistanceKmAtDispatch)) {
                    _tmpDistanceKmAtDispatch = null;
                } else {
                    Double _tmpDistanceKmAtDispatch2 = Double.valueOf(_stmt.getDouble(_columnIndexOfDistanceKmAtDispatch));
                    _tmpDistanceKmAtDispatch = _tmpDistanceKmAtDispatch2;
                }
                if (_stmt.isNull(_columnIndexOfRatingGiven)) {
                    _tmpRatingGiven = null;
                } else {
                    Float _tmpRatingGiven2 = Float.valueOf((float) _stmt.getDouble(_columnIndexOfRatingGiven));
                    _tmpRatingGiven = _tmpRatingGiven2;
                }
                if (_stmt.isNull(_columnIndexOfReviewFeedback)) {
                    _tmpReviewFeedback = null;
                } else {
                    String _tmpReviewFeedback2 = _stmt.getText(_columnIndexOfReviewFeedback);
                    _tmpReviewFeedback = _tmpReviewFeedback2;
                }
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                if (_stmt.isNull(_columnIndexOfCompletedAt)) {
                    _tmpCompletedAt = null;
                } else {
                    Long _tmpCompletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfCompletedAt));
                    _tmpCompletedAt = _tmpCompletedAt2;
                }
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsExpertNotified);
                boolean _tmpIsExpertNotified = _tmp != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnAssign);
                boolean _tmpIsCustomerNotifiedOnAssign = _tmp_1 != 0;
                boolean _tmpIsCustomerNotifiedOnAssign2 = _tmpIsCustomerNotifiedOnAssign;
                int _tmp_2 = (int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnCompletion);
                boolean _tmpIsCustomerNotifiedOnCompletion = _tmp_2 != 0;
                if (_stmt.isNull(_columnIndexOfAssignMessageLaterDismissedAt)) {
                    _tmpAssignMessageLaterDismissedAt = null;
                } else {
                    Long _tmpAssignMessageLaterDismissedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignMessageLaterDismissedAt));
                    _tmpAssignMessageLaterDismissedAt = _tmpAssignMessageLaterDismissedAt2;
                }
                boolean _tmpIsCustomerNotifiedOnCompletion2 = _tmpIsCustomerNotifiedOnCompletion;
                int _tmp_3 = (int) _stmt.getLong(_columnIndexOfIsDeleted);
                boolean _tmpIsDeleted = _tmp_3 != 0;
                if (_stmt.isNull(_columnIndexOfDeletedAt)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfDeletedAt));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_4 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_4 != 0;
                if (_stmt.isNull(_columnIndexOfCreatedByUserId)) {
                    _tmpCreated_by_user_id = null;
                } else {
                    String _tmpCreated_by_user_id2 = _stmt.getText(_columnIndexOfCreatedByUserId);
                    _tmpCreated_by_user_id = _tmpCreated_by_user_id2;
                }
                if (_stmt.isNull(_columnIndexOfCreatedByUserName)) {
                    _tmpCreated_by_user_name = null;
                } else {
                    String _tmpCreated_by_user_name2 = _stmt.getText(_columnIndexOfCreatedByUserName);
                    _tmpCreated_by_user_name = _tmpCreated_by_user_name2;
                }
                if (_stmt.isNull(_columnIndexOfCreatedByDesignation)) {
                    _tmpCreated_by_designation = null;
                } else {
                    String _tmpCreated_by_designation2 = _stmt.getText(_columnIndexOfCreatedByDesignation);
                    _tmpCreated_by_designation = _tmpCreated_by_designation2;
                }
                if (_stmt.isNull(_columnIndexOfManagedByUserId)) {
                    _tmpManaged_by_user_id = null;
                } else {
                    String _tmpManaged_by_user_id2 = _stmt.getText(_columnIndexOfManagedByUserId);
                    _tmpManaged_by_user_id = _tmpManaged_by_user_id2;
                }
                if (_stmt.isNull(_columnIndexOfManagedByUserName)) {
                    _tmpManaged_by_user_name = null;
                } else {
                    String _tmpManaged_by_user_name2 = _stmt.getText(_columnIndexOfManagedByUserName);
                    _tmpManaged_by_user_name = _tmpManaged_by_user_name2;
                }
                if (_stmt.isNull(_columnIndexOfManagedByDesignation)) {
                    _tmpManaged_by_designation = null;
                } else {
                    String _tmpManaged_by_designation2 = _stmt.getText(_columnIndexOfManagedByDesignation);
                    _tmpManaged_by_designation = _tmpManaged_by_designation2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianId)) {
                    _tmpAssigned_technician_id = null;
                } else {
                    Long _tmpAssigned_technician_id2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedTechnicianId));
                    _tmpAssigned_technician_id = _tmpAssigned_technician_id2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianName)) {
                    _tmpAssigned_technician_name = null;
                } else {
                    String _tmpAssigned_technician_name2 = _stmt.getText(_columnIndexOfAssignedTechnicianName);
                    _tmpAssigned_technician_name = _tmpAssigned_technician_name2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedAtTimestamp)) {
                    _tmpAssigned_at_timestamp = null;
                } else {
                    Long _tmpAssigned_at_timestamp2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedAtTimestamp));
                    _tmpAssigned_at_timestamp = _tmpAssigned_at_timestamp2;
                }
                _result = new CustomerJobEntity(_tmpId, _tmpCustomerName, _tmpCustomerPhone, _tmpServiceType, _tmpIssueDescription, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpStatus, _tmpAssignedExpertId, _tmpAssignedExpertName, _tmpAssignedExpertPhone, _tmpDistanceKmAtDispatch, _tmpRatingGiven, _tmpReviewFeedback, _tmpCreatedAt, _tmpCompletedAt, _tmpIsExpertNotified, _tmpIsCustomerNotifiedOnAssign2, _tmpIsCustomerNotifiedOnCompletion2, _tmpAssignMessageLaterDismissedAt, _tmpIsDeleted, _tmpDeletedAt, _tmpLast_updated, _tmpIs_synced, _tmpCreated_by_user_id, _tmpCreated_by_user_name, _tmpCreated_by_designation, _tmpManaged_by_user_id, _tmpManaged_by_user_name, _tmpManaged_by_designation, _tmpAssigned_technician_id, _tmpAssigned_technician_name, _tmpAssigned_at_timestamp);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object getJobCount(Continuation<? super Integer> continuation) {
        final String _sql = "SELECT COUNT(*) FROM customer_jobs";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda20
            public final Object invoke(Object obj) {
                return Integer.valueOf(CustomerJobDao_Impl.getJobCount$lambda$9(_sql, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int getJobCount$lambda$9(String $_sql, SQLiteConnection _connection) {
        int _tmp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            if (_stmt.step()) {
                _tmp = (int) _stmt.getLong(0);
            } else {
                _tmp = 0;
            }
            return _tmp;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object findRecentOrderByPhone(final String phone, final long sinceTimestamp, Continuation<? super CustomerJobEntity> continuation) {
        final String _sql = "SELECT * FROM customer_jobs WHERE customerPhone = ? AND createdAt >= ? AND isDeleted = 0 LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda14
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.findRecentOrderByPhone$lambda$10(_sql, phone, sinceTimestamp, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final CustomerJobEntity findRecentOrderByPhone$lambda$10(String $_sql, String $phone, long $sinceTimestamp, SQLiteConnection _connection) {
        CustomerJobEntity _result;
        Long _tmpAssignedExpertId;
        String _tmpAssignedExpertName;
        String _tmpAssignedExpertPhone;
        Double _tmpDistanceKmAtDispatch;
        Float _tmpRatingGiven;
        String _tmpReviewFeedback;
        Long _tmpCompletedAt;
        Long _tmpAssignMessageLaterDismissedAt;
        Long _tmpDeletedAt;
        String _tmpCreated_by_user_id;
        String _tmpCreated_by_user_name;
        String _tmpCreated_by_designation;
        String _tmpManaged_by_user_id;
        String _tmpManaged_by_user_name;
        String _tmpManaged_by_designation;
        Long _tmpAssigned_technician_id;
        String _tmpAssigned_technician_name;
        Long _tmpAssigned_at_timestamp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $phone);
            _stmt.bindLong(2, $sinceTimestamp);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfCustomerName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerName");
            int _columnIndexOfCustomerPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerPhone");
            int _columnIndexOfServiceType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceType");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
            int _columnIndexOfAssignedExpertId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertId");
            int _columnIndexOfAssignedExpertName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertName");
            int _columnIndexOfAssignedExpertPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertPhone");
            int _columnIndexOfDistanceKmAtDispatch = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "distanceKmAtDispatch");
            int _columnIndexOfRatingGiven = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingGiven");
            int _columnIndexOfReviewFeedback = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reviewFeedback");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfCompletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedAt");
            int _columnIndexOfIsExpertNotified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isExpertNotified");
            int _columnIndexOfIsCustomerNotifiedOnAssign = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnAssign");
            int _columnIndexOfIsCustomerNotifiedOnCompletion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnCompletion");
            int _columnIndexOfAssignMessageLaterDismissedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignMessageLaterDismissedAt");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            int _columnIndexOfCreatedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_id");
            int _columnIndexOfCreatedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_name");
            int _columnIndexOfCreatedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_designation");
            int _columnIndexOfManagedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_id");
            int _columnIndexOfManagedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_name");
            int _columnIndexOfManagedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_designation");
            int _columnIndexOfAssignedTechnicianId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_id");
            int _columnIndexOfAssignedTechnicianName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_name");
            int _columnIndexOfAssignedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_at_timestamp");
            if (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpCustomerName = _stmt.getText(_columnIndexOfCustomerName);
                String _tmpCustomerPhone = _stmt.getText(_columnIndexOfCustomerPhone);
                String _tmpServiceType = _stmt.getText(_columnIndexOfServiceType);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                if (_stmt.isNull(_columnIndexOfAssignedExpertId)) {
                    _tmpAssignedExpertId = null;
                } else {
                    Long _tmpAssignedExpertId2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedExpertId));
                    _tmpAssignedExpertId = _tmpAssignedExpertId2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertName)) {
                    _tmpAssignedExpertName = null;
                } else {
                    String _tmpAssignedExpertName2 = _stmt.getText(_columnIndexOfAssignedExpertName);
                    _tmpAssignedExpertName = _tmpAssignedExpertName2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertPhone)) {
                    _tmpAssignedExpertPhone = null;
                } else {
                    String _tmpAssignedExpertPhone2 = _stmt.getText(_columnIndexOfAssignedExpertPhone);
                    _tmpAssignedExpertPhone = _tmpAssignedExpertPhone2;
                }
                if (_stmt.isNull(_columnIndexOfDistanceKmAtDispatch)) {
                    _tmpDistanceKmAtDispatch = null;
                } else {
                    Double _tmpDistanceKmAtDispatch2 = Double.valueOf(_stmt.getDouble(_columnIndexOfDistanceKmAtDispatch));
                    _tmpDistanceKmAtDispatch = _tmpDistanceKmAtDispatch2;
                }
                if (_stmt.isNull(_columnIndexOfRatingGiven)) {
                    _tmpRatingGiven = null;
                } else {
                    Float _tmpRatingGiven2 = Float.valueOf((float) _stmt.getDouble(_columnIndexOfRatingGiven));
                    _tmpRatingGiven = _tmpRatingGiven2;
                }
                if (_stmt.isNull(_columnIndexOfReviewFeedback)) {
                    _tmpReviewFeedback = null;
                } else {
                    String _tmpReviewFeedback2 = _stmt.getText(_columnIndexOfReviewFeedback);
                    _tmpReviewFeedback = _tmpReviewFeedback2;
                }
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                if (_stmt.isNull(_columnIndexOfCompletedAt)) {
                    _tmpCompletedAt = null;
                } else {
                    Long _tmpCompletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfCompletedAt));
                    _tmpCompletedAt = _tmpCompletedAt2;
                }
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsExpertNotified);
                boolean _tmpIsExpertNotified = _tmp != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnAssign);
                boolean _tmpIsCustomerNotifiedOnAssign = _tmp_1 != 0;
                boolean _tmpIsCustomerNotifiedOnAssign2 = _tmpIsCustomerNotifiedOnAssign;
                int _tmp_2 = (int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnCompletion);
                boolean _tmpIsCustomerNotifiedOnCompletion = _tmp_2 != 0;
                if (_stmt.isNull(_columnIndexOfAssignMessageLaterDismissedAt)) {
                    _tmpAssignMessageLaterDismissedAt = null;
                } else {
                    Long _tmpAssignMessageLaterDismissedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignMessageLaterDismissedAt));
                    _tmpAssignMessageLaterDismissedAt = _tmpAssignMessageLaterDismissedAt2;
                }
                boolean _tmpIsCustomerNotifiedOnCompletion2 = _tmpIsCustomerNotifiedOnCompletion;
                int _tmp_3 = (int) _stmt.getLong(_columnIndexOfIsDeleted);
                boolean _tmpIsDeleted = _tmp_3 != 0;
                if (_stmt.isNull(_columnIndexOfDeletedAt)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfDeletedAt));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_4 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_4 != 0;
                if (_stmt.isNull(_columnIndexOfCreatedByUserId)) {
                    _tmpCreated_by_user_id = null;
                } else {
                    String _tmpCreated_by_user_id2 = _stmt.getText(_columnIndexOfCreatedByUserId);
                    _tmpCreated_by_user_id = _tmpCreated_by_user_id2;
                }
                if (_stmt.isNull(_columnIndexOfCreatedByUserName)) {
                    _tmpCreated_by_user_name = null;
                } else {
                    String _tmpCreated_by_user_name2 = _stmt.getText(_columnIndexOfCreatedByUserName);
                    _tmpCreated_by_user_name = _tmpCreated_by_user_name2;
                }
                if (_stmt.isNull(_columnIndexOfCreatedByDesignation)) {
                    _tmpCreated_by_designation = null;
                } else {
                    String _tmpCreated_by_designation2 = _stmt.getText(_columnIndexOfCreatedByDesignation);
                    _tmpCreated_by_designation = _tmpCreated_by_designation2;
                }
                if (_stmt.isNull(_columnIndexOfManagedByUserId)) {
                    _tmpManaged_by_user_id = null;
                } else {
                    String _tmpManaged_by_user_id2 = _stmt.getText(_columnIndexOfManagedByUserId);
                    _tmpManaged_by_user_id = _tmpManaged_by_user_id2;
                }
                if (_stmt.isNull(_columnIndexOfManagedByUserName)) {
                    _tmpManaged_by_user_name = null;
                } else {
                    String _tmpManaged_by_user_name2 = _stmt.getText(_columnIndexOfManagedByUserName);
                    _tmpManaged_by_user_name = _tmpManaged_by_user_name2;
                }
                if (_stmt.isNull(_columnIndexOfManagedByDesignation)) {
                    _tmpManaged_by_designation = null;
                } else {
                    String _tmpManaged_by_designation2 = _stmt.getText(_columnIndexOfManagedByDesignation);
                    _tmpManaged_by_designation = _tmpManaged_by_designation2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianId)) {
                    _tmpAssigned_technician_id = null;
                } else {
                    Long _tmpAssigned_technician_id2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedTechnicianId));
                    _tmpAssigned_technician_id = _tmpAssigned_technician_id2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianName)) {
                    _tmpAssigned_technician_name = null;
                } else {
                    String _tmpAssigned_technician_name2 = _stmt.getText(_columnIndexOfAssignedTechnicianName);
                    _tmpAssigned_technician_name = _tmpAssigned_technician_name2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedAtTimestamp)) {
                    _tmpAssigned_at_timestamp = null;
                } else {
                    Long _tmpAssigned_at_timestamp2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedAtTimestamp));
                    _tmpAssigned_at_timestamp = _tmpAssigned_at_timestamp2;
                }
                _result = new CustomerJobEntity(_tmpId, _tmpCustomerName, _tmpCustomerPhone, _tmpServiceType, _tmpIssueDescription, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpStatus, _tmpAssignedExpertId, _tmpAssignedExpertName, _tmpAssignedExpertPhone, _tmpDistanceKmAtDispatch, _tmpRatingGiven, _tmpReviewFeedback, _tmpCreatedAt, _tmpCompletedAt, _tmpIsExpertNotified, _tmpIsCustomerNotifiedOnAssign2, _tmpIsCustomerNotifiedOnCompletion2, _tmpAssignMessageLaterDismissedAt, _tmpIsDeleted, _tmpDeletedAt, _tmpLast_updated, _tmpIs_synced, _tmpCreated_by_user_id, _tmpCreated_by_user_name, _tmpCreated_by_designation, _tmpManaged_by_user_id, _tmpManaged_by_user_name, _tmpManaged_by_designation, _tmpAssigned_technician_id, _tmpAssigned_technician_name, _tmpAssigned_at_timestamp);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object getUnsyncedJobs(Continuation<? super List<CustomerJobEntity>> continuation) {
        final String _sql = "SELECT * FROM customer_jobs WHERE is_synced = 0";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.getUnsyncedJobs$lambda$11(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getUnsyncedJobs$lambda$11(String $_sql, SQLiteConnection _connection) {
        Long _tmpAssignedExpertId;
        String _tmpAssignedExpertName;
        String _tmpAssignedExpertPhone;
        Double _tmpDistanceKmAtDispatch;
        int _columnIndexOfCustomerName;
        int _columnIndexOfCustomerPhone;
        Float _tmpRatingGiven;
        String _tmpReviewFeedback;
        Long _tmpCompletedAt;
        Long _tmpAssignMessageLaterDismissedAt;
        Long _tmpDeletedAt;
        String _tmpCreated_by_user_id;
        String _tmpCreated_by_user_name;
        String _tmpCreated_by_designation;
        String _tmpManaged_by_user_id;
        String _tmpManaged_by_user_name;
        String _tmpManaged_by_designation;
        Long _tmpAssigned_technician_id;
        String _tmpAssigned_technician_name;
        Long _tmpAssigned_at_timestamp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfAssignedTechnicianName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _tmp_4 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerName");
            int _columnIndexOfCustomerPhone2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerPhone");
            int _columnIndexOfServiceType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceType");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
            int _columnIndexOfAssignedExpertId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertId");
            int _columnIndexOfAssignedExpertName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertName");
            int _columnIndexOfAssignedExpertPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertPhone");
            int _columnIndexOfDistanceKmAtDispatch = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "distanceKmAtDispatch");
            int _columnIndexOfRatingGiven = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingGiven");
            int _columnIndexOfReviewFeedback = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reviewFeedback");
            int _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfReviewFeedback;
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedAt");
            int _columnIndexOfIsExpertNotified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isExpertNotified");
            int _columnIndexOfReviewFeedback2 = _columnIndexOfIsExpertNotified;
            int _columnIndexOfRatingGiven2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnAssign");
            int _columnIndexOfIsCustomerNotifiedOnCompletion2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnCompletion");
            int _tmp = _columnIndexOfIsCustomerNotifiedOnCompletion2;
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignMessageLaterDismissedAt");
            int _columnIndexOfIsDeleted2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_2 = _columnIndexOfIsDeleted2;
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfDeletedAt2 = _columnIndexOfDeletedAt;
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfLastUpdated2 = _columnIndexOfLastUpdated;
            int _columnIndexOfIsSynced2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            int _tmp_3 = _columnIndexOfIsSynced2;
            int _columnIndexOfCreatedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_id");
            int _columnIndexOfCreatedByUserId2 = _columnIndexOfCreatedByUserId;
            int _columnIndexOfCreatedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_name");
            int _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserName;
            int _columnIndexOfCreatedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_designation");
            int _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByDesignation;
            int _columnIndexOfManagedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_id");
            int _columnIndexOfManagedByUserId2 = _columnIndexOfManagedByUserId;
            int _columnIndexOfManagedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_name");
            int _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserName;
            int _columnIndexOfManagedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_designation");
            int _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByDesignation;
            int _columnIndexOfAssignedTechnicianId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_id");
            int _columnIndexOfAssignedTechnicianId2 = _columnIndexOfAssignedTechnicianId;
            int _columnIndexOfAssignedTechnicianName2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_name");
            int _columnIndexOfAssignedTechnicianName3 = _columnIndexOfAssignedTechnicianName2;
            int _columnIndexOfAssignedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_at_timestamp");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfAssignedTechnicianName);
                String _tmpCustomerName = _stmt.getText(_tmp_4);
                String _tmpCustomerPhone = _stmt.getText(_columnIndexOfCustomerPhone2);
                String _tmpServiceType = _stmt.getText(_columnIndexOfServiceType);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                if (_stmt.isNull(_columnIndexOfAssignedExpertId)) {
                    _tmpAssignedExpertId = null;
                } else {
                    Long _tmpAssignedExpertId2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedExpertId));
                    _tmpAssignedExpertId = _tmpAssignedExpertId2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertName)) {
                    _tmpAssignedExpertName = null;
                } else {
                    String _tmpAssignedExpertName2 = _stmt.getText(_columnIndexOfAssignedExpertName);
                    _tmpAssignedExpertName = _tmpAssignedExpertName2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertPhone)) {
                    _tmpAssignedExpertPhone = null;
                } else {
                    String _tmpAssignedExpertPhone2 = _stmt.getText(_columnIndexOfAssignedExpertPhone);
                    _tmpAssignedExpertPhone = _tmpAssignedExpertPhone2;
                }
                if (_stmt.isNull(_columnIndexOfDistanceKmAtDispatch)) {
                    _tmpDistanceKmAtDispatch = null;
                } else {
                    Double _tmpDistanceKmAtDispatch2 = Double.valueOf(_stmt.getDouble(_columnIndexOfDistanceKmAtDispatch));
                    _tmpDistanceKmAtDispatch = _tmpDistanceKmAtDispatch2;
                }
                if (_stmt.isNull(_columnIndexOfRatingGiven)) {
                    _columnIndexOfCustomerName = _tmp_4;
                    _columnIndexOfCustomerPhone = _columnIndexOfCustomerPhone2;
                    _tmpRatingGiven = null;
                } else {
                    _columnIndexOfCustomerName = _tmp_4;
                    _columnIndexOfCustomerPhone = _columnIndexOfCustomerPhone2;
                    Float _tmpRatingGiven2 = Float.valueOf((float) _stmt.getDouble(_columnIndexOfRatingGiven));
                    _tmpRatingGiven = _tmpRatingGiven2;
                }
                int _columnIndexOfReviewFeedback3 = _columnIndexOfIsCustomerNotifiedOnCompletion;
                if (_stmt.isNull(_columnIndexOfReviewFeedback3)) {
                    _tmpReviewFeedback = null;
                } else {
                    String _tmpReviewFeedback2 = _stmt.getText(_columnIndexOfReviewFeedback3);
                    _tmpReviewFeedback = _tmpReviewFeedback2;
                }
                int _columnIndexOfCreatedAt = _columnIndexOfId;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                int _columnIndexOfId2 = _columnIndexOfAssignedTechnicianName;
                int _columnIndexOfId3 = _columnIndexOfIsSynced;
                if (_stmt.isNull(_columnIndexOfId3)) {
                    _tmpCompletedAt = null;
                } else {
                    Long _tmpCompletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfId3));
                    _tmpCompletedAt = _tmpCompletedAt2;
                }
                int _columnIndexOfCompletedAt = _columnIndexOfReviewFeedback2;
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfCompletedAt);
                boolean _tmpIsExpertNotified = _tmp2 != 0;
                int _columnIndexOfIsCustomerNotifiedOnAssign = _columnIndexOfRatingGiven2;
                int _columnIndexOfIsCustomerNotifiedOnAssign2 = _columnIndexOfRatingGiven;
                boolean _tmpIsCustomerNotifiedOnAssign = ((int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnAssign)) != 0;
                int _tmp_1 = _tmp;
                int _tmp_22 = (int) _stmt.getLong(_tmp_1);
                boolean _tmpIsCustomerNotifiedOnCompletion = _tmp_22 != 0;
                int _columnIndexOfIsCustomerNotifiedOnCompletion3 = _columnIndexOfIsDeleted;
                if (_stmt.isNull(_columnIndexOfIsCustomerNotifiedOnCompletion3)) {
                    _tmpAssignMessageLaterDismissedAt = null;
                } else {
                    Long _tmpAssignMessageLaterDismissedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsCustomerNotifiedOnCompletion3));
                    _tmpAssignMessageLaterDismissedAt = _tmpAssignMessageLaterDismissedAt2;
                }
                int _columnIndexOfAssignMessageLaterDismissedAt = _tmp_2;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfAssignMessageLaterDismissedAt);
                boolean _tmpIsDeleted = _tmp_32 != 0;
                int _columnIndexOfIsDeleted3 = _columnIndexOfDeletedAt2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsDeleted3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfLastUpdated3 = _columnIndexOfLastUpdated2;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated3);
                int _columnIndexOfDeletedAt3 = _tmp_3;
                int _tmp_42 = (int) _stmt.getLong(_columnIndexOfDeletedAt3);
                boolean _tmpIs_synced = _tmp_42 != 0;
                int _columnIndexOfIsSynced3 = _columnIndexOfCreatedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsSynced3)) {
                    _tmpCreated_by_user_id = null;
                } else {
                    String _tmpCreated_by_user_id2 = _stmt.getText(_columnIndexOfIsSynced3);
                    _tmpCreated_by_user_id = _tmpCreated_by_user_id2;
                }
                _columnIndexOfCreatedByUserId2 = _columnIndexOfIsSynced3;
                int _columnIndexOfCreatedByUserId3 = _columnIndexOfCreatedByUserName2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserId3)) {
                    _tmpCreated_by_user_name = null;
                } else {
                    String _tmpCreated_by_user_name2 = _stmt.getText(_columnIndexOfCreatedByUserId3);
                    _tmpCreated_by_user_name = _tmpCreated_by_user_name2;
                }
                _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserId3;
                int _columnIndexOfCreatedByUserName3 = _columnIndexOfCreatedByDesignation2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserName3)) {
                    _tmpCreated_by_designation = null;
                } else {
                    String _tmpCreated_by_designation2 = _stmt.getText(_columnIndexOfCreatedByUserName3);
                    _tmpCreated_by_designation = _tmpCreated_by_designation2;
                }
                _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByUserName3;
                int _columnIndexOfCreatedByDesignation3 = _columnIndexOfManagedByUserId2;
                if (_stmt.isNull(_columnIndexOfCreatedByDesignation3)) {
                    _tmpManaged_by_user_id = null;
                } else {
                    String _tmpManaged_by_user_id2 = _stmt.getText(_columnIndexOfCreatedByDesignation3);
                    _tmpManaged_by_user_id = _tmpManaged_by_user_id2;
                }
                _columnIndexOfManagedByUserId2 = _columnIndexOfCreatedByDesignation3;
                int _columnIndexOfManagedByUserId3 = _columnIndexOfManagedByUserName2;
                if (_stmt.isNull(_columnIndexOfManagedByUserId3)) {
                    _tmpManaged_by_user_name = null;
                } else {
                    String _tmpManaged_by_user_name2 = _stmt.getText(_columnIndexOfManagedByUserId3);
                    _tmpManaged_by_user_name = _tmpManaged_by_user_name2;
                }
                _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserId3;
                int _columnIndexOfManagedByUserName3 = _columnIndexOfManagedByDesignation2;
                if (_stmt.isNull(_columnIndexOfManagedByUserName3)) {
                    _tmpManaged_by_designation = null;
                } else {
                    String _tmpManaged_by_designation2 = _stmt.getText(_columnIndexOfManagedByUserName3);
                    _tmpManaged_by_designation = _tmpManaged_by_designation2;
                }
                _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByUserName3;
                int _columnIndexOfManagedByDesignation3 = _columnIndexOfAssignedTechnicianId2;
                if (_stmt.isNull(_columnIndexOfManagedByDesignation3)) {
                    _tmpAssigned_technician_id = null;
                } else {
                    Long _tmpAssigned_technician_id2 = Long.valueOf(_stmt.getLong(_columnIndexOfManagedByDesignation3));
                    _tmpAssigned_technician_id = _tmpAssigned_technician_id2;
                }
                _columnIndexOfAssignedTechnicianId2 = _columnIndexOfManagedByDesignation3;
                int _columnIndexOfAssignedTechnicianId3 = _columnIndexOfAssignedTechnicianName3;
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianId3)) {
                    _tmpAssigned_technician_name = null;
                } else {
                    String _tmpAssigned_technician_name2 = _stmt.getText(_columnIndexOfAssignedTechnicianId3);
                    _tmpAssigned_technician_name = _tmpAssigned_technician_name2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedAtTimestamp)) {
                    _tmpAssigned_at_timestamp = null;
                } else {
                    Long _tmpAssigned_at_timestamp2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedAtTimestamp));
                    _tmpAssigned_at_timestamp = _tmpAssigned_at_timestamp2;
                }
                CustomerJobEntity _item = new CustomerJobEntity(_tmpId, _tmpCustomerName, _tmpCustomerPhone, _tmpServiceType, _tmpIssueDescription, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpStatus, _tmpAssignedExpertId, _tmpAssignedExpertName, _tmpAssignedExpertPhone, _tmpDistanceKmAtDispatch, _tmpRatingGiven, _tmpReviewFeedback, _tmpCreatedAt, _tmpCompletedAt, _tmpIsExpertNotified, _tmpIsCustomerNotifiedOnAssign, _tmpIsCustomerNotifiedOnCompletion, _tmpAssignMessageLaterDismissedAt, _tmpIsDeleted, _tmpDeletedAt, _tmpLast_updated, _tmpIs_synced, _tmpCreated_by_user_id, _tmpCreated_by_user_name, _tmpCreated_by_designation, _tmpManaged_by_user_id, _tmpManaged_by_user_name, _tmpManaged_by_designation, _tmpAssigned_technician_id, _tmpAssigned_technician_name, _tmpAssigned_at_timestamp);
                List _result2 = _result;
                _result2.add(_item);
                _columnIndexOfAssignedTechnicianName3 = _columnIndexOfAssignedTechnicianId3;
                _result = _result2;
                _tmp = _tmp_1;
                _columnIndexOfAssignedTechnicianName = _columnIndexOfId2;
                _tmp_3 = _columnIndexOfDeletedAt3;
                _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfReviewFeedback3;
                _columnIndexOfRatingGiven = _columnIndexOfIsCustomerNotifiedOnAssign2;
                _tmp_2 = _columnIndexOfAssignMessageLaterDismissedAt;
                _columnIndexOfIsSynced = _columnIndexOfId3;
                _tmp_4 = _columnIndexOfCustomerName;
                _columnIndexOfCustomerPhone2 = _columnIndexOfCustomerPhone;
                _columnIndexOfId = _columnIndexOfCreatedAt;
                _columnIndexOfReviewFeedback2 = _columnIndexOfCompletedAt;
                _columnIndexOfRatingGiven2 = _columnIndexOfIsCustomerNotifiedOnAssign;
                _columnIndexOfIsDeleted = _columnIndexOfIsCustomerNotifiedOnCompletion3;
                _columnIndexOfDeletedAt2 = _columnIndexOfIsDeleted3;
                _columnIndexOfLastUpdated2 = _columnIndexOfLastUpdated3;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object getAllJobsDirectList(Continuation<? super List<CustomerJobEntity>> continuation) {
        final String _sql = "SELECT * FROM customer_jobs";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda10
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.getAllJobsDirectList$lambda$12(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAllJobsDirectList$lambda$12(String $_sql, SQLiteConnection _connection) {
        Long _tmpAssignedExpertId;
        String _tmpAssignedExpertName;
        String _tmpAssignedExpertPhone;
        Double _tmpDistanceKmAtDispatch;
        int _columnIndexOfCustomerName;
        int _columnIndexOfCustomerPhone;
        Float _tmpRatingGiven;
        String _tmpReviewFeedback;
        Long _tmpCompletedAt;
        Long _tmpAssignMessageLaterDismissedAt;
        Long _tmpDeletedAt;
        String _tmpCreated_by_user_id;
        String _tmpCreated_by_user_name;
        String _tmpCreated_by_designation;
        String _tmpManaged_by_user_id;
        String _tmpManaged_by_user_name;
        String _tmpManaged_by_designation;
        Long _tmpAssigned_technician_id;
        String _tmpAssigned_technician_name;
        Long _tmpAssigned_at_timestamp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfAssignedTechnicianName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _tmp_4 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerName");
            int _columnIndexOfCustomerPhone2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "customerPhone");
            int _columnIndexOfServiceType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceType");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
            int _columnIndexOfAssignedExpertId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertId");
            int _columnIndexOfAssignedExpertName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertName");
            int _columnIndexOfAssignedExpertPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedExpertPhone");
            int _columnIndexOfDistanceKmAtDispatch = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "distanceKmAtDispatch");
            int _columnIndexOfRatingGiven = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingGiven");
            int _columnIndexOfReviewFeedback = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reviewFeedback");
            int _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfReviewFeedback;
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedAt");
            int _columnIndexOfIsExpertNotified = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isExpertNotified");
            int _columnIndexOfReviewFeedback2 = _columnIndexOfIsExpertNotified;
            int _columnIndexOfRatingGiven2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnAssign");
            int _columnIndexOfIsCustomerNotifiedOnCompletion2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isCustomerNotifiedOnCompletion");
            int _tmp = _columnIndexOfIsCustomerNotifiedOnCompletion2;
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignMessageLaterDismissedAt");
            int _columnIndexOfIsDeleted2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_2 = _columnIndexOfIsDeleted2;
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfDeletedAt2 = _columnIndexOfDeletedAt;
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfLastUpdated2 = _columnIndexOfLastUpdated;
            int _columnIndexOfIsSynced2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            int _tmp_3 = _columnIndexOfIsSynced2;
            int _columnIndexOfCreatedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_id");
            int _columnIndexOfCreatedByUserId2 = _columnIndexOfCreatedByUserId;
            int _columnIndexOfCreatedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_user_name");
            int _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserName;
            int _columnIndexOfCreatedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_by_designation");
            int _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByDesignation;
            int _columnIndexOfManagedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_id");
            int _columnIndexOfManagedByUserId2 = _columnIndexOfManagedByUserId;
            int _columnIndexOfManagedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_user_name");
            int _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserName;
            int _columnIndexOfManagedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "managed_by_designation");
            int _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByDesignation;
            int _columnIndexOfAssignedTechnicianId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_id");
            int _columnIndexOfAssignedTechnicianId2 = _columnIndexOfAssignedTechnicianId;
            int _columnIndexOfAssignedTechnicianName2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_technician_name");
            int _columnIndexOfAssignedTechnicianName3 = _columnIndexOfAssignedTechnicianName2;
            int _columnIndexOfAssignedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assigned_at_timestamp");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfAssignedTechnicianName);
                String _tmpCustomerName = _stmt.getText(_tmp_4);
                String _tmpCustomerPhone = _stmt.getText(_columnIndexOfCustomerPhone2);
                String _tmpServiceType = _stmt.getText(_columnIndexOfServiceType);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpStatus = _stmt.getText(_columnIndexOfStatus);
                if (_stmt.isNull(_columnIndexOfAssignedExpertId)) {
                    _tmpAssignedExpertId = null;
                } else {
                    Long _tmpAssignedExpertId2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedExpertId));
                    _tmpAssignedExpertId = _tmpAssignedExpertId2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertName)) {
                    _tmpAssignedExpertName = null;
                } else {
                    String _tmpAssignedExpertName2 = _stmt.getText(_columnIndexOfAssignedExpertName);
                    _tmpAssignedExpertName = _tmpAssignedExpertName2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedExpertPhone)) {
                    _tmpAssignedExpertPhone = null;
                } else {
                    String _tmpAssignedExpertPhone2 = _stmt.getText(_columnIndexOfAssignedExpertPhone);
                    _tmpAssignedExpertPhone = _tmpAssignedExpertPhone2;
                }
                if (_stmt.isNull(_columnIndexOfDistanceKmAtDispatch)) {
                    _tmpDistanceKmAtDispatch = null;
                } else {
                    Double _tmpDistanceKmAtDispatch2 = Double.valueOf(_stmt.getDouble(_columnIndexOfDistanceKmAtDispatch));
                    _tmpDistanceKmAtDispatch = _tmpDistanceKmAtDispatch2;
                }
                if (_stmt.isNull(_columnIndexOfRatingGiven)) {
                    _columnIndexOfCustomerName = _tmp_4;
                    _columnIndexOfCustomerPhone = _columnIndexOfCustomerPhone2;
                    _tmpRatingGiven = null;
                } else {
                    _columnIndexOfCustomerName = _tmp_4;
                    _columnIndexOfCustomerPhone = _columnIndexOfCustomerPhone2;
                    Float _tmpRatingGiven2 = Float.valueOf((float) _stmt.getDouble(_columnIndexOfRatingGiven));
                    _tmpRatingGiven = _tmpRatingGiven2;
                }
                int _columnIndexOfReviewFeedback3 = _columnIndexOfIsCustomerNotifiedOnCompletion;
                if (_stmt.isNull(_columnIndexOfReviewFeedback3)) {
                    _tmpReviewFeedback = null;
                } else {
                    String _tmpReviewFeedback2 = _stmt.getText(_columnIndexOfReviewFeedback3);
                    _tmpReviewFeedback = _tmpReviewFeedback2;
                }
                int _columnIndexOfCreatedAt = _columnIndexOfId;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                int _columnIndexOfId2 = _columnIndexOfAssignedTechnicianName;
                int _columnIndexOfId3 = _columnIndexOfIsSynced;
                if (_stmt.isNull(_columnIndexOfId3)) {
                    _tmpCompletedAt = null;
                } else {
                    Long _tmpCompletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfId3));
                    _tmpCompletedAt = _tmpCompletedAt2;
                }
                int _columnIndexOfCompletedAt = _columnIndexOfReviewFeedback2;
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfCompletedAt);
                boolean _tmpIsExpertNotified = _tmp2 != 0;
                int _columnIndexOfIsCustomerNotifiedOnAssign = _columnIndexOfRatingGiven2;
                int _columnIndexOfIsCustomerNotifiedOnAssign2 = _columnIndexOfRatingGiven;
                boolean _tmpIsCustomerNotifiedOnAssign = ((int) _stmt.getLong(_columnIndexOfIsCustomerNotifiedOnAssign)) != 0;
                int _tmp_1 = _tmp;
                int _tmp_22 = (int) _stmt.getLong(_tmp_1);
                boolean _tmpIsCustomerNotifiedOnCompletion = _tmp_22 != 0;
                int _columnIndexOfIsCustomerNotifiedOnCompletion3 = _columnIndexOfIsDeleted;
                if (_stmt.isNull(_columnIndexOfIsCustomerNotifiedOnCompletion3)) {
                    _tmpAssignMessageLaterDismissedAt = null;
                } else {
                    Long _tmpAssignMessageLaterDismissedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsCustomerNotifiedOnCompletion3));
                    _tmpAssignMessageLaterDismissedAt = _tmpAssignMessageLaterDismissedAt2;
                }
                int _columnIndexOfAssignMessageLaterDismissedAt = _tmp_2;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfAssignMessageLaterDismissedAt);
                boolean _tmpIsDeleted = _tmp_32 != 0;
                int _columnIndexOfIsDeleted3 = _columnIndexOfDeletedAt2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfIsDeleted3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfLastUpdated3 = _columnIndexOfLastUpdated2;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated3);
                int _columnIndexOfDeletedAt3 = _tmp_3;
                int _tmp_42 = (int) _stmt.getLong(_columnIndexOfDeletedAt3);
                boolean _tmpIs_synced = _tmp_42 != 0;
                int _columnIndexOfIsSynced3 = _columnIndexOfCreatedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsSynced3)) {
                    _tmpCreated_by_user_id = null;
                } else {
                    String _tmpCreated_by_user_id2 = _stmt.getText(_columnIndexOfIsSynced3);
                    _tmpCreated_by_user_id = _tmpCreated_by_user_id2;
                }
                _columnIndexOfCreatedByUserId2 = _columnIndexOfIsSynced3;
                int _columnIndexOfCreatedByUserId3 = _columnIndexOfCreatedByUserName2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserId3)) {
                    _tmpCreated_by_user_name = null;
                } else {
                    String _tmpCreated_by_user_name2 = _stmt.getText(_columnIndexOfCreatedByUserId3);
                    _tmpCreated_by_user_name = _tmpCreated_by_user_name2;
                }
                _columnIndexOfCreatedByUserName2 = _columnIndexOfCreatedByUserId3;
                int _columnIndexOfCreatedByUserName3 = _columnIndexOfCreatedByDesignation2;
                if (_stmt.isNull(_columnIndexOfCreatedByUserName3)) {
                    _tmpCreated_by_designation = null;
                } else {
                    String _tmpCreated_by_designation2 = _stmt.getText(_columnIndexOfCreatedByUserName3);
                    _tmpCreated_by_designation = _tmpCreated_by_designation2;
                }
                _columnIndexOfCreatedByDesignation2 = _columnIndexOfCreatedByUserName3;
                int _columnIndexOfCreatedByDesignation3 = _columnIndexOfManagedByUserId2;
                if (_stmt.isNull(_columnIndexOfCreatedByDesignation3)) {
                    _tmpManaged_by_user_id = null;
                } else {
                    String _tmpManaged_by_user_id2 = _stmt.getText(_columnIndexOfCreatedByDesignation3);
                    _tmpManaged_by_user_id = _tmpManaged_by_user_id2;
                }
                _columnIndexOfManagedByUserId2 = _columnIndexOfCreatedByDesignation3;
                int _columnIndexOfManagedByUserId3 = _columnIndexOfManagedByUserName2;
                if (_stmt.isNull(_columnIndexOfManagedByUserId3)) {
                    _tmpManaged_by_user_name = null;
                } else {
                    String _tmpManaged_by_user_name2 = _stmt.getText(_columnIndexOfManagedByUserId3);
                    _tmpManaged_by_user_name = _tmpManaged_by_user_name2;
                }
                _columnIndexOfManagedByUserName2 = _columnIndexOfManagedByUserId3;
                int _columnIndexOfManagedByUserName3 = _columnIndexOfManagedByDesignation2;
                if (_stmt.isNull(_columnIndexOfManagedByUserName3)) {
                    _tmpManaged_by_designation = null;
                } else {
                    String _tmpManaged_by_designation2 = _stmt.getText(_columnIndexOfManagedByUserName3);
                    _tmpManaged_by_designation = _tmpManaged_by_designation2;
                }
                _columnIndexOfManagedByDesignation2 = _columnIndexOfManagedByUserName3;
                int _columnIndexOfManagedByDesignation3 = _columnIndexOfAssignedTechnicianId2;
                if (_stmt.isNull(_columnIndexOfManagedByDesignation3)) {
                    _tmpAssigned_technician_id = null;
                } else {
                    Long _tmpAssigned_technician_id2 = Long.valueOf(_stmt.getLong(_columnIndexOfManagedByDesignation3));
                    _tmpAssigned_technician_id = _tmpAssigned_technician_id2;
                }
                _columnIndexOfAssignedTechnicianId2 = _columnIndexOfManagedByDesignation3;
                int _columnIndexOfAssignedTechnicianId3 = _columnIndexOfAssignedTechnicianName3;
                if (_stmt.isNull(_columnIndexOfAssignedTechnicianId3)) {
                    _tmpAssigned_technician_name = null;
                } else {
                    String _tmpAssigned_technician_name2 = _stmt.getText(_columnIndexOfAssignedTechnicianId3);
                    _tmpAssigned_technician_name = _tmpAssigned_technician_name2;
                }
                if (_stmt.isNull(_columnIndexOfAssignedAtTimestamp)) {
                    _tmpAssigned_at_timestamp = null;
                } else {
                    Long _tmpAssigned_at_timestamp2 = Long.valueOf(_stmt.getLong(_columnIndexOfAssignedAtTimestamp));
                    _tmpAssigned_at_timestamp = _tmpAssigned_at_timestamp2;
                }
                CustomerJobEntity _item = new CustomerJobEntity(_tmpId, _tmpCustomerName, _tmpCustomerPhone, _tmpServiceType, _tmpIssueDescription, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpStatus, _tmpAssignedExpertId, _tmpAssignedExpertName, _tmpAssignedExpertPhone, _tmpDistanceKmAtDispatch, _tmpRatingGiven, _tmpReviewFeedback, _tmpCreatedAt, _tmpCompletedAt, _tmpIsExpertNotified, _tmpIsCustomerNotifiedOnAssign, _tmpIsCustomerNotifiedOnCompletion, _tmpAssignMessageLaterDismissedAt, _tmpIsDeleted, _tmpDeletedAt, _tmpLast_updated, _tmpIs_synced, _tmpCreated_by_user_id, _tmpCreated_by_user_name, _tmpCreated_by_designation, _tmpManaged_by_user_id, _tmpManaged_by_user_name, _tmpManaged_by_designation, _tmpAssigned_technician_id, _tmpAssigned_technician_name, _tmpAssigned_at_timestamp);
                List _result2 = _result;
                _result2.add(_item);
                _columnIndexOfAssignedTechnicianName3 = _columnIndexOfAssignedTechnicianId3;
                _result = _result2;
                _tmp = _tmp_1;
                _columnIndexOfAssignedTechnicianName = _columnIndexOfId2;
                _tmp_3 = _columnIndexOfDeletedAt3;
                _columnIndexOfIsCustomerNotifiedOnCompletion = _columnIndexOfReviewFeedback3;
                _columnIndexOfRatingGiven = _columnIndexOfIsCustomerNotifiedOnAssign2;
                _tmp_2 = _columnIndexOfAssignMessageLaterDismissedAt;
                _columnIndexOfIsSynced = _columnIndexOfId3;
                _tmp_4 = _columnIndexOfCustomerName;
                _columnIndexOfCustomerPhone2 = _columnIndexOfCustomerPhone;
                _columnIndexOfId = _columnIndexOfCreatedAt;
                _columnIndexOfReviewFeedback2 = _columnIndexOfCompletedAt;
                _columnIndexOfRatingGiven2 = _columnIndexOfIsCustomerNotifiedOnAssign;
                _columnIndexOfIsDeleted = _columnIndexOfIsCustomerNotifiedOnCompletion3;
                _columnIndexOfDeletedAt2 = _columnIndexOfIsDeleted3;
                _columnIndexOfLastUpdated2 = _columnIndexOfLastUpdated3;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object moveToRecycleBin(final long jobId, final long deletedAt, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET isDeleted = 1, deletedAt = ?, last_updated = ?, is_synced = 0 WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda23
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.moveToRecycleBin$lambda$13(_sql, deletedAt, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit moveToRecycleBin$lambda$13(String $_sql, long $deletedAt, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $deletedAt);
            _stmt.bindLong(2, $deletedAt);
            _stmt.bindLong(3, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object restoreJobFromRecycleBin(final long jobId, final long restoredAt, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET isDeleted = 0, deletedAt = NULL, last_updated = ?, is_synced = 0 WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda22
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.restoreJobFromRecycleBin$lambda$14(_sql, restoredAt, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit restoreJobFromRecycleBin$lambda$14(String $_sql, long $restoredAt, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $restoredAt);
            _stmt.bindLong(2, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object purgeJobsOlderThan(final long cutoffTimestamp, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM customer_jobs WHERE isDeleted = 1 AND deletedAt <= ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda28
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.purgeJobsOlderThan$lambda$15(_sql, cutoffTimestamp, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit purgeJobsOlderThan$lambda$15(String $_sql, long $cutoffTimestamp, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $cutoffTimestamp);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object clearRecycleBin(Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM customer_jobs WHERE isDeleted = 1";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda27
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.clearRecycleBin$lambda$16(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit clearRecycleBin$lambda$16(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object deleteJobById(final long id, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM customer_jobs WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda12
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.deleteJobById$lambda$17(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteJobById$lambda$17(String $_sql, long $id, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $id);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object updateJobDispatch(final long jobId, final String status, final long expertId, final String expertName, final String expertPhone, final double distanceKm, final long assignedAt, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET status = ?, assignedExpertId = ?, assignedExpertName = ?, assignedExpertPhone = ?, distanceKmAtDispatch = ?, assigned_technician_id = ?, assigned_technician_name = ?, assigned_at_timestamp = ?, last_updated = ? WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda17
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.updateJobDispatch$lambda$18(_sql, status, expertId, expertName, expertPhone, distanceKm, assignedAt, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateJobDispatch$lambda$18(String $_sql, String $status, long $expertId, String $expertName, String $expertPhone, double $distanceKm, long $assignedAt, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $status);
            _stmt.bindLong(2, $expertId);
            _stmt.bindText(3, $expertName);
            _stmt.bindText(4, $expertPhone);
            _stmt.bindDouble(5, $distanceKm);
            _stmt.bindLong(6, $expertId);
            _stmt.bindText(7, $expertName);
            _stmt.bindLong(8, $assignedAt);
            _stmt.bindLong(9, $assignedAt);
            _stmt.bindLong(10, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object updateJobManager(final long jobId, final String userId, final String userName, final String userDesignation, final long now, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET managed_by_user_id = ?, managed_by_user_name = ?, managed_by_designation = ?, last_updated = ? WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda19
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.updateJobManager$lambda$19(_sql, userId, userName, userDesignation, now, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateJobManager$lambda$19(String $_sql, String $userId, String $userName, String $userDesignation, long $now, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $userId);
            _stmt.bindText(2, $userName);
            if ($userDesignation == null) {
                _stmt.bindNull(3);
            } else {
                _stmt.bindText(3, $userDesignation);
            }
            _stmt.bindLong(4, $now);
            _stmt.bindLong(5, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object updateJobStatus(final long jobId, final String status, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET status = ? WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda26
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.updateJobStatus$lambda$20(_sql, status, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateJobStatus$lambda$20(String $_sql, String $status, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $status);
            _stmt.bindLong(2, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object unassignExpertFromJob(final long jobId, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET status = 'PENDING', assignedExpertId = NULL, assignedExpertName = NULL, assignedExpertPhone = NULL, distanceKmAtDispatch = NULL, isExpertNotified = 0, isCustomerNotifiedOnAssign = 0 WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda15
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.unassignExpertFromJob$lambda$21(_sql, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit unassignExpertFromJob$lambda$21(String $_sql, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object completeOrCancelJobWithReview(final long jobId, final String status, final float rating, final String feedback, final long completedAt, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET status = ?, ratingGiven = ?, reviewFeedback = ?, completedAt = ? WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda4
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.completeOrCancelJobWithReview$lambda$22(_sql, status, rating, feedback, completedAt, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit completeOrCancelJobWithReview$lambda$22(String $_sql, String $status, float $rating, String $feedback, long $completedAt, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $status);
            _stmt.bindDouble(2, $rating);
            if ($feedback == null) {
                _stmt.bindNull(3);
            } else {
                _stmt.bindText(3, $feedback);
            }
            _stmt.bindLong(4, $completedAt);
            _stmt.bindLong(5, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object updateExpertNotified(final long jobId, final boolean sent, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET isExpertNotified = ? WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda5
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.updateExpertNotified$lambda$23(_sql, sent, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateExpertNotified$lambda$23(String $_sql, boolean $sent, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _tmp = $sent ? 1 : 0;
        try {
            _stmt.bindLong(1, _tmp);
            _stmt.bindLong(2, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object updateCustomerNotifiedOnAssign(final long jobId, final boolean sent, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET isCustomerNotifiedOnAssign = ? WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda7
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.updateCustomerNotifiedOnAssign$lambda$24(_sql, sent, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateCustomerNotifiedOnAssign$lambda$24(String $_sql, boolean $sent, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _tmp = $sent ? 1 : 0;
        try {
            _stmt.bindLong(1, _tmp);
            _stmt.bindLong(2, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object updateCustomerNotifiedOnCompletion(final long jobId, final boolean sent, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET isCustomerNotifiedOnCompletion = ? WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda16
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.updateCustomerNotifiedOnCompletion$lambda$25(_sql, sent, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateCustomerNotifiedOnCompletion$lambda$25(String $_sql, boolean $sent, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _tmp = $sent ? 1 : 0;
        try {
            _stmt.bindLong(1, _tmp);
            _stmt.bindLong(2, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object updateMessageDismissedAt(final long jobId, final Long time, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET assignMessageLaterDismissedAt = ? WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda24
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.updateMessageDismissedAt$lambda$26(_sql, time, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateMessageDismissedAt$lambda$26(String $_sql, Long $time, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            if ($time != null) {
                _stmt.bindLong(1, $time.longValue());
            } else {
                _stmt.bindNull(1);
            }
            _stmt.bindLong(2, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object clearAllJobs(Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM customer_jobs";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda1
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.clearAllJobs$lambda$27(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit clearAllJobs$lambda$27(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.CustomerJobDao
    public Object markJobSynced(final long jobId, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE customer_jobs SET is_synced = 1 WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerJobDao_Impl$$ExternalSyntheticLambda13
            public final Object invoke(Object obj) {
                return CustomerJobDao_Impl.markJobSynced$lambda$28(_sql, jobId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit markJobSynced$lambda$28(String $_sql, long $jobId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $jobId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    /* compiled from: CustomerJobDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/local/CustomerJobDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes7.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }
}
