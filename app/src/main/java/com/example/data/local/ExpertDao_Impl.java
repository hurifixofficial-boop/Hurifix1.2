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
import com.example.data.model.ExpertEntity;
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

/* compiled from: ExpertDao_Impl.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u0000 32\u00020\u0001:\u00013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0018H\u0016J\u0014\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0018H\u0016J\u0014\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0018H\u0016J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001c\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001dJ\u000e\u0010\u001e\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010 J\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010 J\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010 J\u001e\u0010#\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010$\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010%J\u001e\u0010&\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010'\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010%J\u0016\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001dJ\u000e\u0010*\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010 J\u001e\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020.H\u0096@¢\u0006\u0002\u0010/J\u0016\u00100\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001dJ\u000e\u00101\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010 J\u0016\u00102\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/example/data/local/ExpertDao_Impl;", "Lcom/example/data/local/ExpertDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfExpertEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/model/ExpertEntity;", "__deleteAdapterOfExpertEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfExpertEntity", "insertExpert", "", "expert", "(Lcom/example/data/model/ExpertEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertExperts", "", FirestoreSyncManager.EXPERTS_COLLECTION, "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExpert", "updateExpert", "getAllExperts", "Lkotlinx/coroutines/flow/Flow;", "getAvailableExperts", "getDeletedExperts", "getExpertById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExpertCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsyncedExperts", "getAllExpertsDirectList", "moveToRecycleBin", "deletedAt", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreExpertFromRecycleBin", "restoredAt", "purgeExpertsOlderThan", "cutoffTimestamp", "clearRecycleBin", "updateWelcomeMessageSent", "expertId", "sent", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExpertById", "clearAllExperts", "markExpertSynced", "Companion", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes7.dex */
public final class ExpertDao_Impl implements ExpertDao {
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<ExpertEntity> __deleteAdapterOfExpertEntity;
    private final EntityInsertAdapter<ExpertEntity> __insertAdapterOfExpertEntity;
    private final EntityDeleteOrUpdateAdapter<ExpertEntity> __updateAdapterOfExpertEntity;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public ExpertDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfExpertEntity = new EntityInsertAdapter<ExpertEntity>() { // from class: com.example.data.local.ExpertDao_Impl.1
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `experts` (`id`,`name`,`phone`,`category`,`address`,`latitude`,`longitude`,`isAvailable`,`rating`,`ratingSum`,`totalRatingsCount`,`completedJobsCount`,`cancelledJobsCount`,`isWelcomeMessageSent`,`isDeleted`,`deletedAt`,`added_by_user_id`,`added_by_user_name`,`added_by_designation`,`profilePicUrl`,`created_at_timestamp`,`createdAt`,`last_updated`,`is_synced`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, ExpertEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
                statement.bindText(2, entity.getName());
                statement.bindText(3, entity.getPhone());
                statement.bindText(4, entity.getCategory());
                statement.bindText(5, entity.getAddress());
                statement.bindDouble(6, entity.getLatitude());
                statement.bindDouble(7, entity.getLongitude());
                statement.bindLong(8, entity.isAvailable() ? 1L : 0L);
                statement.bindDouble(9, entity.getRating());
                statement.bindDouble(10, entity.getRatingSum());
                statement.bindLong(11, entity.getTotalRatingsCount());
                statement.bindLong(12, entity.getCompletedJobsCount());
                statement.bindLong(13, entity.getCancelledJobsCount());
                statement.bindLong(14, entity.isWelcomeMessageSent() ? 1L : 0L);
                statement.bindLong(15, entity.isDeleted() ? 1L : 0L);
                Long deletedAt = entity.getDeletedAt();
                if (deletedAt != null) {
                    statement.bindLong(16, deletedAt.longValue());
                } else {
                    statement.bindNull(16);
                }
                String added_by_user_id = entity.getAdded_by_user_id();
                if (added_by_user_id == null) {
                    statement.bindNull(17);
                } else {
                    statement.bindText(17, added_by_user_id);
                }
                String added_by_user_name = entity.getAdded_by_user_name();
                if (added_by_user_name == null) {
                    statement.bindNull(18);
                } else {
                    statement.bindText(18, added_by_user_name);
                }
                String added_by_designation = entity.getAdded_by_designation();
                if (added_by_designation == null) {
                    statement.bindNull(19);
                } else {
                    statement.bindText(19, added_by_designation);
                }
                String profilePicUrl = entity.getProfilePicUrl();
                if (profilePicUrl == null) {
                    statement.bindNull(20);
                } else {
                    statement.bindText(20, profilePicUrl);
                }
                statement.bindLong(21, entity.getCreated_at_timestamp());
                statement.bindLong(22, entity.getCreatedAt());
                statement.bindLong(23, entity.getLast_updated());
                statement.bindLong(24, entity.is_synced() ? 1L : 0L);
            }
        };
        this.__deleteAdapterOfExpertEntity = new EntityDeleteOrUpdateAdapter<ExpertEntity>() { // from class: com.example.data.local.ExpertDao_Impl.2
            protected String createQuery() {
                return "DELETE FROM `experts` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, ExpertEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfExpertEntity = new EntityDeleteOrUpdateAdapter<ExpertEntity>() { // from class: com.example.data.local.ExpertDao_Impl.3
            protected String createQuery() {
                return "UPDATE OR ABORT `experts` SET `id` = ?,`name` = ?,`phone` = ?,`category` = ?,`address` = ?,`latitude` = ?,`longitude` = ?,`isAvailable` = ?,`rating` = ?,`ratingSum` = ?,`totalRatingsCount` = ?,`completedJobsCount` = ?,`cancelledJobsCount` = ?,`isWelcomeMessageSent` = ?,`isDeleted` = ?,`deletedAt` = ?,`added_by_user_id` = ?,`added_by_user_name` = ?,`added_by_designation` = ?,`profilePicUrl` = ?,`created_at_timestamp` = ?,`createdAt` = ?,`last_updated` = ?,`is_synced` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, ExpertEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
                statement.bindText(2, entity.getName());
                statement.bindText(3, entity.getPhone());
                statement.bindText(4, entity.getCategory());
                statement.bindText(5, entity.getAddress());
                statement.bindDouble(6, entity.getLatitude());
                statement.bindDouble(7, entity.getLongitude());
                statement.bindLong(8, entity.isAvailable() ? 1L : 0L);
                statement.bindDouble(9, entity.getRating());
                statement.bindDouble(10, entity.getRatingSum());
                statement.bindLong(11, entity.getTotalRatingsCount());
                statement.bindLong(12, entity.getCompletedJobsCount());
                statement.bindLong(13, entity.getCancelledJobsCount());
                statement.bindLong(14, entity.isWelcomeMessageSent() ? 1L : 0L);
                statement.bindLong(15, entity.isDeleted() ? 1L : 0L);
                Long deletedAt = entity.getDeletedAt();
                if (deletedAt != null) {
                    statement.bindLong(16, deletedAt.longValue());
                } else {
                    statement.bindNull(16);
                }
                String added_by_user_id = entity.getAdded_by_user_id();
                if (added_by_user_id == null) {
                    statement.bindNull(17);
                } else {
                    statement.bindText(17, added_by_user_id);
                }
                String added_by_user_name = entity.getAdded_by_user_name();
                if (added_by_user_name == null) {
                    statement.bindNull(18);
                } else {
                    statement.bindText(18, added_by_user_name);
                }
                String added_by_designation = entity.getAdded_by_designation();
                if (added_by_designation == null) {
                    statement.bindNull(19);
                } else {
                    statement.bindText(19, added_by_designation);
                }
                String profilePicUrl = entity.getProfilePicUrl();
                if (profilePicUrl == null) {
                    statement.bindNull(20);
                } else {
                    statement.bindText(20, profilePicUrl);
                }
                statement.bindLong(21, entity.getCreated_at_timestamp());
                statement.bindLong(22, entity.getCreatedAt());
                statement.bindLong(23, entity.getLast_updated());
                statement.bindLong(24, entity.is_synced() ? 1L : 0L);
                statement.bindLong(25, entity.getId());
            }
        };
    }

    @Override // com.example.data.local.ExpertDao
    public Object insertExpert(final ExpertEntity expert, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                return Long.valueOf(ExpertDao_Impl.insertExpert$lambda$0(ExpertDao_Impl.this, expert, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final long insertExpert$lambda$0(ExpertDao_Impl this$0, ExpertEntity $expert, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfExpertEntity.insertAndReturnId(_connection, $expert);
        return _result;
    }

    @Override // com.example.data.local.ExpertDao
    public Object insertExperts(final List<ExpertEntity> list, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda5
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.insertExperts$lambda$1(ExpertDao_Impl.this, list, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit insertExperts$lambda$1(ExpertDao_Impl this$0, List $experts, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfExpertEntity.insert(_connection, $experts);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.ExpertDao
    public Object deleteExpert(final ExpertEntity expert, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda1
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.deleteExpert$lambda$2(ExpertDao_Impl.this, expert, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteExpert$lambda$2(ExpertDao_Impl this$0, ExpertEntity $expert, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfExpertEntity.handle(_connection, $expert);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.ExpertDao
    public Object updateExpert(final ExpertEntity expert, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda3
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.updateExpert$lambda$3(ExpertDao_Impl.this, expert, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateExpert$lambda$3(ExpertDao_Impl this$0, ExpertEntity $expert, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfExpertEntity.handle(_connection, $expert);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.ExpertDao
    public Flow<List<ExpertEntity>> getAllExperts() {
        final String _sql = "SELECT * FROM experts WHERE isDeleted = 0 ORDER BY name ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{FirestoreSyncManager.EXPERTS_COLLECTION}, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda4
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.getAllExperts$lambda$4(_sql, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAllExperts$lambda$4(String $_sql, SQLiteConnection _connection) {
        Long _tmpDeletedAt;
        String _tmpAdded_by_user_id;
        String _tmpAdded_by_user_name;
        String _tmpAdded_by_designation;
        String _tmpProfilePicUrl;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _tmp_2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfCreatedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfRatingSum = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingSum");
            int _columnIndexOfTotalRatingsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalRatingsCount");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCancelledJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cancelledJobsCount");
            int _columnIndexOfIsWelcomeMessageSent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isWelcomeMessageSent");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_1 = _columnIndexOfIsDeleted;
            int _columnIndexOfDeletedAt2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt2;
            int _columnIndexOfAddedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_id");
            int _columnIndexOfAddedByUserId2 = _columnIndexOfAddedByUserId;
            int _columnIndexOfAddedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_name");
            int _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserName;
            int _columnIndexOfAddedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_designation");
            int _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByDesignation;
            int _tmp_3 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePicUrl");
            int _columnIndexOfCreatedAtTimestamp2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at_timestamp");
            int _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp2;
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfCreatedAt2 = _columnIndexOfCreatedAt;
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfLastUpdated;
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_tmp_2);
                String _tmpPhone = _stmt.getText(_columnIndexOfCreatedAtTimestamp);
                String _tmpCategory = _stmt.getText(_columnIndexOfDeletedAt);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _columnIndexOfName = _tmp_2;
                int _columnIndexOfPhone = _columnIndexOfCreatedAtTimestamp;
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                int _columnIndexOfCategory = _columnIndexOfDeletedAt;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                float _tmpRatingSum = (float) _stmt.getDouble(_columnIndexOfRatingSum);
                int _tmpTotalRatingsCount = (int) _stmt.getLong(_columnIndexOfTotalRatingsCount);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                int _tmpCancelledJobsCount = (int) _stmt.getLong(_columnIndexOfCancelledJobsCount);
                int _tmp_12 = (int) _stmt.getLong(_columnIndexOfIsWelcomeMessageSent);
                boolean _tmpIsWelcomeMessageSent = _tmp_12 != 0;
                int _columnIndexOfId2 = _columnIndexOfId;
                int _columnIndexOfId3 = _tmp_1;
                int _tmp_22 = (int) _stmt.getLong(_columnIndexOfId3);
                boolean _tmpIsDeleted = _tmp_22 != 0;
                int _columnIndexOfDeletedAt3 = _columnIndexOfIsDeleted2;
                if (_stmt.isNull(_columnIndexOfDeletedAt3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfDeletedAt3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfIsDeleted3 = _columnIndexOfAddedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpAdded_by_user_id = null;
                } else {
                    String _tmpAdded_by_user_id2 = _stmt.getText(_columnIndexOfIsDeleted3);
                    _tmpAdded_by_user_id = _tmpAdded_by_user_id2;
                }
                _columnIndexOfAddedByUserId2 = _columnIndexOfIsDeleted3;
                int _columnIndexOfAddedByUserId3 = _columnIndexOfAddedByUserName2;
                if (_stmt.isNull(_columnIndexOfAddedByUserId3)) {
                    _tmpAdded_by_user_name = null;
                } else {
                    String _tmpAdded_by_user_name2 = _stmt.getText(_columnIndexOfAddedByUserId3);
                    _tmpAdded_by_user_name = _tmpAdded_by_user_name2;
                }
                _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserId3;
                int _columnIndexOfAddedByUserName3 = _columnIndexOfAddedByDesignation2;
                if (_stmt.isNull(_columnIndexOfAddedByUserName3)) {
                    _tmpAdded_by_designation = null;
                } else {
                    String _tmpAdded_by_designation2 = _stmt.getText(_columnIndexOfAddedByUserName3);
                    _tmpAdded_by_designation = _tmpAdded_by_designation2;
                }
                _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByUserName3;
                int _columnIndexOfAddedByDesignation3 = _tmp_3;
                if (_stmt.isNull(_columnIndexOfAddedByDesignation3)) {
                    _tmpProfilePicUrl = null;
                } else {
                    String _tmpProfilePicUrl2 = _stmt.getText(_columnIndexOfAddedByDesignation3);
                    _tmpProfilePicUrl = _tmpProfilePicUrl2;
                }
                int _columnIndexOfCreatedAtTimestamp4 = _columnIndexOfCreatedAtTimestamp3;
                long _tmpCreated_at_timestamp = _stmt.getLong(_columnIndexOfCreatedAtTimestamp4);
                int _columnIndexOfProfilePicUrl = _columnIndexOfCreatedAt2;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfProfilePicUrl);
                _columnIndexOfCreatedAt2 = _columnIndexOfProfilePicUrl;
                int _columnIndexOfCreatedAt3 = _columnIndexOfIsWelcomeMessageSent2;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfCreatedAt3);
                int _columnIndexOfIsWelcomeMessageSent3 = _columnIndexOfIsWelcomeMessageSent;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_32 != 0;
                ExpertEntity _item = new ExpertEntity(_tmpId, _tmpName, _tmpPhone, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpRatingSum, _tmpTotalRatingsCount, _tmpCompletedJobsCount, _tmpCancelledJobsCount, _tmpIsWelcomeMessageSent, _tmpIsDeleted, _tmpDeletedAt, _tmpAdded_by_user_id, _tmpAdded_by_user_name, _tmpAdded_by_designation, _tmpProfilePicUrl, _tmpCreated_at_timestamp, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp4;
                _tmp_1 = _columnIndexOfId3;
                _columnIndexOfIsWelcomeMessageSent = _columnIndexOfIsWelcomeMessageSent3;
                _tmp_2 = _columnIndexOfName;
                _columnIndexOfCreatedAtTimestamp = _columnIndexOfPhone;
                _columnIndexOfId = _columnIndexOfId2;
                _tmp_3 = _columnIndexOfAddedByDesignation3;
                _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfCreatedAt3;
                _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt3;
                _columnIndexOfDeletedAt = _columnIndexOfCategory;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.ExpertDao
    public Flow<List<ExpertEntity>> getAvailableExperts() {
        final String _sql = "SELECT * FROM experts WHERE isAvailable = 1 AND isDeleted = 0 ORDER BY name ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{FirestoreSyncManager.EXPERTS_COLLECTION}, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda6
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.getAvailableExperts$lambda$5(_sql, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAvailableExperts$lambda$5(String $_sql, SQLiteConnection _connection) {
        Long _tmpDeletedAt;
        String _tmpAdded_by_user_id;
        String _tmpAdded_by_user_name;
        String _tmpAdded_by_designation;
        String _tmpProfilePicUrl;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _tmp_2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfCreatedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfRatingSum = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingSum");
            int _columnIndexOfTotalRatingsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalRatingsCount");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCancelledJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cancelledJobsCount");
            int _columnIndexOfIsWelcomeMessageSent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isWelcomeMessageSent");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_1 = _columnIndexOfIsDeleted;
            int _columnIndexOfDeletedAt2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt2;
            int _columnIndexOfAddedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_id");
            int _columnIndexOfAddedByUserId2 = _columnIndexOfAddedByUserId;
            int _columnIndexOfAddedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_name");
            int _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserName;
            int _columnIndexOfAddedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_designation");
            int _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByDesignation;
            int _tmp_3 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePicUrl");
            int _columnIndexOfCreatedAtTimestamp2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at_timestamp");
            int _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp2;
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfCreatedAt2 = _columnIndexOfCreatedAt;
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfLastUpdated;
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_tmp_2);
                String _tmpPhone = _stmt.getText(_columnIndexOfCreatedAtTimestamp);
                String _tmpCategory = _stmt.getText(_columnIndexOfDeletedAt);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _columnIndexOfName = _tmp_2;
                int _columnIndexOfPhone = _columnIndexOfCreatedAtTimestamp;
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                int _columnIndexOfCategory = _columnIndexOfDeletedAt;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                float _tmpRatingSum = (float) _stmt.getDouble(_columnIndexOfRatingSum);
                int _tmpTotalRatingsCount = (int) _stmt.getLong(_columnIndexOfTotalRatingsCount);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                int _tmpCancelledJobsCount = (int) _stmt.getLong(_columnIndexOfCancelledJobsCount);
                int _tmp_12 = (int) _stmt.getLong(_columnIndexOfIsWelcomeMessageSent);
                boolean _tmpIsWelcomeMessageSent = _tmp_12 != 0;
                int _columnIndexOfId2 = _columnIndexOfId;
                int _columnIndexOfId3 = _tmp_1;
                int _tmp_22 = (int) _stmt.getLong(_columnIndexOfId3);
                boolean _tmpIsDeleted = _tmp_22 != 0;
                int _columnIndexOfDeletedAt3 = _columnIndexOfIsDeleted2;
                if (_stmt.isNull(_columnIndexOfDeletedAt3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfDeletedAt3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfIsDeleted3 = _columnIndexOfAddedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpAdded_by_user_id = null;
                } else {
                    String _tmpAdded_by_user_id2 = _stmt.getText(_columnIndexOfIsDeleted3);
                    _tmpAdded_by_user_id = _tmpAdded_by_user_id2;
                }
                _columnIndexOfAddedByUserId2 = _columnIndexOfIsDeleted3;
                int _columnIndexOfAddedByUserId3 = _columnIndexOfAddedByUserName2;
                if (_stmt.isNull(_columnIndexOfAddedByUserId3)) {
                    _tmpAdded_by_user_name = null;
                } else {
                    String _tmpAdded_by_user_name2 = _stmt.getText(_columnIndexOfAddedByUserId3);
                    _tmpAdded_by_user_name = _tmpAdded_by_user_name2;
                }
                _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserId3;
                int _columnIndexOfAddedByUserName3 = _columnIndexOfAddedByDesignation2;
                if (_stmt.isNull(_columnIndexOfAddedByUserName3)) {
                    _tmpAdded_by_designation = null;
                } else {
                    String _tmpAdded_by_designation2 = _stmt.getText(_columnIndexOfAddedByUserName3);
                    _tmpAdded_by_designation = _tmpAdded_by_designation2;
                }
                _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByUserName3;
                int _columnIndexOfAddedByDesignation3 = _tmp_3;
                if (_stmt.isNull(_columnIndexOfAddedByDesignation3)) {
                    _tmpProfilePicUrl = null;
                } else {
                    String _tmpProfilePicUrl2 = _stmt.getText(_columnIndexOfAddedByDesignation3);
                    _tmpProfilePicUrl = _tmpProfilePicUrl2;
                }
                int _columnIndexOfCreatedAtTimestamp4 = _columnIndexOfCreatedAtTimestamp3;
                long _tmpCreated_at_timestamp = _stmt.getLong(_columnIndexOfCreatedAtTimestamp4);
                int _columnIndexOfProfilePicUrl = _columnIndexOfCreatedAt2;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfProfilePicUrl);
                _columnIndexOfCreatedAt2 = _columnIndexOfProfilePicUrl;
                int _columnIndexOfCreatedAt3 = _columnIndexOfIsWelcomeMessageSent2;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfCreatedAt3);
                int _columnIndexOfIsWelcomeMessageSent3 = _columnIndexOfIsWelcomeMessageSent;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_32 != 0;
                ExpertEntity _item = new ExpertEntity(_tmpId, _tmpName, _tmpPhone, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpRatingSum, _tmpTotalRatingsCount, _tmpCompletedJobsCount, _tmpCancelledJobsCount, _tmpIsWelcomeMessageSent, _tmpIsDeleted, _tmpDeletedAt, _tmpAdded_by_user_id, _tmpAdded_by_user_name, _tmpAdded_by_designation, _tmpProfilePicUrl, _tmpCreated_at_timestamp, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp4;
                _tmp_1 = _columnIndexOfId3;
                _columnIndexOfIsWelcomeMessageSent = _columnIndexOfIsWelcomeMessageSent3;
                _tmp_2 = _columnIndexOfName;
                _columnIndexOfCreatedAtTimestamp = _columnIndexOfPhone;
                _columnIndexOfId = _columnIndexOfId2;
                _tmp_3 = _columnIndexOfAddedByDesignation3;
                _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfCreatedAt3;
                _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt3;
                _columnIndexOfDeletedAt = _columnIndexOfCategory;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.ExpertDao
    public Flow<List<ExpertEntity>> getDeletedExperts() {
        final String _sql = "SELECT * FROM experts WHERE isDeleted = 1 ORDER BY deletedAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{FirestoreSyncManager.EXPERTS_COLLECTION}, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda17
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.getDeletedExperts$lambda$6(_sql, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getDeletedExperts$lambda$6(String $_sql, SQLiteConnection _connection) {
        Long _tmpDeletedAt;
        String _tmpAdded_by_user_id;
        String _tmpAdded_by_user_name;
        String _tmpAdded_by_designation;
        String _tmpProfilePicUrl;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _tmp_2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfCreatedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfRatingSum = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingSum");
            int _columnIndexOfTotalRatingsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalRatingsCount");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCancelledJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cancelledJobsCount");
            int _columnIndexOfIsWelcomeMessageSent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isWelcomeMessageSent");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_1 = _columnIndexOfIsDeleted;
            int _columnIndexOfDeletedAt2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt2;
            int _columnIndexOfAddedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_id");
            int _columnIndexOfAddedByUserId2 = _columnIndexOfAddedByUserId;
            int _columnIndexOfAddedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_name");
            int _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserName;
            int _columnIndexOfAddedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_designation");
            int _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByDesignation;
            int _tmp_3 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePicUrl");
            int _columnIndexOfCreatedAtTimestamp2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at_timestamp");
            int _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp2;
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfCreatedAt2 = _columnIndexOfCreatedAt;
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfLastUpdated;
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_tmp_2);
                String _tmpPhone = _stmt.getText(_columnIndexOfCreatedAtTimestamp);
                String _tmpCategory = _stmt.getText(_columnIndexOfDeletedAt);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _columnIndexOfName = _tmp_2;
                int _columnIndexOfPhone = _columnIndexOfCreatedAtTimestamp;
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                int _columnIndexOfCategory = _columnIndexOfDeletedAt;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                float _tmpRatingSum = (float) _stmt.getDouble(_columnIndexOfRatingSum);
                int _tmpTotalRatingsCount = (int) _stmt.getLong(_columnIndexOfTotalRatingsCount);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                int _tmpCancelledJobsCount = (int) _stmt.getLong(_columnIndexOfCancelledJobsCount);
                int _tmp_12 = (int) _stmt.getLong(_columnIndexOfIsWelcomeMessageSent);
                boolean _tmpIsWelcomeMessageSent = _tmp_12 != 0;
                int _columnIndexOfId2 = _columnIndexOfId;
                int _columnIndexOfId3 = _tmp_1;
                int _tmp_22 = (int) _stmt.getLong(_columnIndexOfId3);
                boolean _tmpIsDeleted = _tmp_22 != 0;
                int _columnIndexOfDeletedAt3 = _columnIndexOfIsDeleted2;
                if (_stmt.isNull(_columnIndexOfDeletedAt3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfDeletedAt3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfIsDeleted3 = _columnIndexOfAddedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpAdded_by_user_id = null;
                } else {
                    String _tmpAdded_by_user_id2 = _stmt.getText(_columnIndexOfIsDeleted3);
                    _tmpAdded_by_user_id = _tmpAdded_by_user_id2;
                }
                _columnIndexOfAddedByUserId2 = _columnIndexOfIsDeleted3;
                int _columnIndexOfAddedByUserId3 = _columnIndexOfAddedByUserName2;
                if (_stmt.isNull(_columnIndexOfAddedByUserId3)) {
                    _tmpAdded_by_user_name = null;
                } else {
                    String _tmpAdded_by_user_name2 = _stmt.getText(_columnIndexOfAddedByUserId3);
                    _tmpAdded_by_user_name = _tmpAdded_by_user_name2;
                }
                _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserId3;
                int _columnIndexOfAddedByUserName3 = _columnIndexOfAddedByDesignation2;
                if (_stmt.isNull(_columnIndexOfAddedByUserName3)) {
                    _tmpAdded_by_designation = null;
                } else {
                    String _tmpAdded_by_designation2 = _stmt.getText(_columnIndexOfAddedByUserName3);
                    _tmpAdded_by_designation = _tmpAdded_by_designation2;
                }
                _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByUserName3;
                int _columnIndexOfAddedByDesignation3 = _tmp_3;
                if (_stmt.isNull(_columnIndexOfAddedByDesignation3)) {
                    _tmpProfilePicUrl = null;
                } else {
                    String _tmpProfilePicUrl2 = _stmt.getText(_columnIndexOfAddedByDesignation3);
                    _tmpProfilePicUrl = _tmpProfilePicUrl2;
                }
                int _columnIndexOfCreatedAtTimestamp4 = _columnIndexOfCreatedAtTimestamp3;
                long _tmpCreated_at_timestamp = _stmt.getLong(_columnIndexOfCreatedAtTimestamp4);
                int _columnIndexOfProfilePicUrl = _columnIndexOfCreatedAt2;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfProfilePicUrl);
                _columnIndexOfCreatedAt2 = _columnIndexOfProfilePicUrl;
                int _columnIndexOfCreatedAt3 = _columnIndexOfIsWelcomeMessageSent2;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfCreatedAt3);
                int _columnIndexOfIsWelcomeMessageSent3 = _columnIndexOfIsWelcomeMessageSent;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_32 != 0;
                ExpertEntity _item = new ExpertEntity(_tmpId, _tmpName, _tmpPhone, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpRatingSum, _tmpTotalRatingsCount, _tmpCompletedJobsCount, _tmpCancelledJobsCount, _tmpIsWelcomeMessageSent, _tmpIsDeleted, _tmpDeletedAt, _tmpAdded_by_user_id, _tmpAdded_by_user_name, _tmpAdded_by_designation, _tmpProfilePicUrl, _tmpCreated_at_timestamp, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp4;
                _tmp_1 = _columnIndexOfId3;
                _columnIndexOfIsWelcomeMessageSent = _columnIndexOfIsWelcomeMessageSent3;
                _tmp_2 = _columnIndexOfName;
                _columnIndexOfCreatedAtTimestamp = _columnIndexOfPhone;
                _columnIndexOfId = _columnIndexOfId2;
                _tmp_3 = _columnIndexOfAddedByDesignation3;
                _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfCreatedAt3;
                _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt3;
                _columnIndexOfDeletedAt = _columnIndexOfCategory;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.ExpertDao
    public Object getExpertById(final long id, Continuation<? super ExpertEntity> continuation) {
        final String _sql = "SELECT * FROM experts WHERE id = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda15
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.getExpertById$lambda$7(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final ExpertEntity getExpertById$lambda$7(String $_sql, long $id, SQLiteConnection _connection) {
        ExpertEntity _result;
        Long _tmpDeletedAt;
        String _tmpAdded_by_user_id;
        String _tmpAdded_by_user_name;
        String _tmpAdded_by_designation;
        String _tmpProfilePicUrl;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $id);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
            int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfRatingSum = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingSum");
            int _columnIndexOfTotalRatingsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalRatingsCount");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCancelledJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cancelledJobsCount");
            int _columnIndexOfIsWelcomeMessageSent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isWelcomeMessageSent");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfAddedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_id");
            int _columnIndexOfAddedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_name");
            int _columnIndexOfAddedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_designation");
            int _columnIndexOfProfilePicUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePicUrl");
            int _columnIndexOfCreatedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at_timestamp");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            if (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpPhone = _stmt.getText(_columnIndexOfPhone);
                String _tmpCategory = _stmt.getText(_columnIndexOfCategory);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                float _tmpRatingSum = (float) _stmt.getDouble(_columnIndexOfRatingSum);
                int _tmpTotalRatingsCount = (int) _stmt.getLong(_columnIndexOfTotalRatingsCount);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                int _tmpCancelledJobsCount = (int) _stmt.getLong(_columnIndexOfCancelledJobsCount);
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsWelcomeMessageSent);
                boolean _tmpIsWelcomeMessageSent = _tmp_1 != 0;
                int _tmp_2 = (int) _stmt.getLong(_columnIndexOfIsDeleted);
                boolean _tmpIsDeleted = _tmp_2 != 0;
                if (_stmt.isNull(_columnIndexOfDeletedAt)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfDeletedAt));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                if (_stmt.isNull(_columnIndexOfAddedByUserId)) {
                    _tmpAdded_by_user_id = null;
                } else {
                    String _tmpAdded_by_user_id2 = _stmt.getText(_columnIndexOfAddedByUserId);
                    _tmpAdded_by_user_id = _tmpAdded_by_user_id2;
                }
                if (_stmt.isNull(_columnIndexOfAddedByUserName)) {
                    _tmpAdded_by_user_name = null;
                } else {
                    String _tmpAdded_by_user_name2 = _stmt.getText(_columnIndexOfAddedByUserName);
                    _tmpAdded_by_user_name = _tmpAdded_by_user_name2;
                }
                if (_stmt.isNull(_columnIndexOfAddedByDesignation)) {
                    _tmpAdded_by_designation = null;
                } else {
                    String _tmpAdded_by_designation2 = _stmt.getText(_columnIndexOfAddedByDesignation);
                    _tmpAdded_by_designation = _tmpAdded_by_designation2;
                }
                if (_stmt.isNull(_columnIndexOfProfilePicUrl)) {
                    _tmpProfilePicUrl = null;
                } else {
                    String _tmpProfilePicUrl2 = _stmt.getText(_columnIndexOfProfilePicUrl);
                    _tmpProfilePicUrl = _tmpProfilePicUrl2;
                }
                long _tmpCreated_at_timestamp = _stmt.getLong(_columnIndexOfCreatedAtTimestamp);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_3 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_3 != 0;
                _result = new ExpertEntity(_tmpId, _tmpName, _tmpPhone, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpRatingSum, _tmpTotalRatingsCount, _tmpCompletedJobsCount, _tmpCancelledJobsCount, _tmpIsWelcomeMessageSent, _tmpIsDeleted, _tmpDeletedAt, _tmpAdded_by_user_id, _tmpAdded_by_user_name, _tmpAdded_by_designation, _tmpProfilePicUrl, _tmpCreated_at_timestamp, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.ExpertDao
    public Object getExpertCount(Continuation<? super Integer> continuation) {
        final String _sql = "SELECT COUNT(*) FROM experts";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda2
            public final Object invoke(Object obj) {
                return Integer.valueOf(ExpertDao_Impl.getExpertCount$lambda$8(_sql, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int getExpertCount$lambda$8(String $_sql, SQLiteConnection _connection) {
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

    @Override // com.example.data.local.ExpertDao
    public Object getUnsyncedExperts(Continuation<? super List<ExpertEntity>> continuation) {
        final String _sql = "SELECT * FROM experts WHERE is_synced = 0";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda7
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.getUnsyncedExperts$lambda$9(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getUnsyncedExperts$lambda$9(String $_sql, SQLiteConnection _connection) {
        Long _tmpDeletedAt;
        String _tmpAdded_by_user_id;
        String _tmpAdded_by_user_name;
        String _tmpAdded_by_designation;
        String _tmpProfilePicUrl;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _tmp_2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfCreatedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfRatingSum = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingSum");
            int _columnIndexOfTotalRatingsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalRatingsCount");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCancelledJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cancelledJobsCount");
            int _columnIndexOfIsWelcomeMessageSent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isWelcomeMessageSent");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_1 = _columnIndexOfIsDeleted;
            int _columnIndexOfDeletedAt2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt2;
            int _columnIndexOfAddedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_id");
            int _columnIndexOfAddedByUserId2 = _columnIndexOfAddedByUserId;
            int _columnIndexOfAddedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_name");
            int _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserName;
            int _columnIndexOfAddedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_designation");
            int _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByDesignation;
            int _tmp_3 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePicUrl");
            int _columnIndexOfCreatedAtTimestamp2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at_timestamp");
            int _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp2;
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfCreatedAt2 = _columnIndexOfCreatedAt;
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfLastUpdated;
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_tmp_2);
                String _tmpPhone = _stmt.getText(_columnIndexOfCreatedAtTimestamp);
                String _tmpCategory = _stmt.getText(_columnIndexOfDeletedAt);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _columnIndexOfName = _tmp_2;
                int _columnIndexOfPhone = _columnIndexOfCreatedAtTimestamp;
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                int _columnIndexOfCategory = _columnIndexOfDeletedAt;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                float _tmpRatingSum = (float) _stmt.getDouble(_columnIndexOfRatingSum);
                int _tmpTotalRatingsCount = (int) _stmt.getLong(_columnIndexOfTotalRatingsCount);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                int _tmpCancelledJobsCount = (int) _stmt.getLong(_columnIndexOfCancelledJobsCount);
                int _tmp_12 = (int) _stmt.getLong(_columnIndexOfIsWelcomeMessageSent);
                boolean _tmpIsWelcomeMessageSent = _tmp_12 != 0;
                int _columnIndexOfId2 = _columnIndexOfId;
                int _columnIndexOfId3 = _tmp_1;
                int _tmp_22 = (int) _stmt.getLong(_columnIndexOfId3);
                boolean _tmpIsDeleted = _tmp_22 != 0;
                int _columnIndexOfDeletedAt3 = _columnIndexOfIsDeleted2;
                if (_stmt.isNull(_columnIndexOfDeletedAt3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfDeletedAt3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfIsDeleted3 = _columnIndexOfAddedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpAdded_by_user_id = null;
                } else {
                    String _tmpAdded_by_user_id2 = _stmt.getText(_columnIndexOfIsDeleted3);
                    _tmpAdded_by_user_id = _tmpAdded_by_user_id2;
                }
                _columnIndexOfAddedByUserId2 = _columnIndexOfIsDeleted3;
                int _columnIndexOfAddedByUserId3 = _columnIndexOfAddedByUserName2;
                if (_stmt.isNull(_columnIndexOfAddedByUserId3)) {
                    _tmpAdded_by_user_name = null;
                } else {
                    String _tmpAdded_by_user_name2 = _stmt.getText(_columnIndexOfAddedByUserId3);
                    _tmpAdded_by_user_name = _tmpAdded_by_user_name2;
                }
                _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserId3;
                int _columnIndexOfAddedByUserName3 = _columnIndexOfAddedByDesignation2;
                if (_stmt.isNull(_columnIndexOfAddedByUserName3)) {
                    _tmpAdded_by_designation = null;
                } else {
                    String _tmpAdded_by_designation2 = _stmt.getText(_columnIndexOfAddedByUserName3);
                    _tmpAdded_by_designation = _tmpAdded_by_designation2;
                }
                _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByUserName3;
                int _columnIndexOfAddedByDesignation3 = _tmp_3;
                if (_stmt.isNull(_columnIndexOfAddedByDesignation3)) {
                    _tmpProfilePicUrl = null;
                } else {
                    String _tmpProfilePicUrl2 = _stmt.getText(_columnIndexOfAddedByDesignation3);
                    _tmpProfilePicUrl = _tmpProfilePicUrl2;
                }
                int _columnIndexOfCreatedAtTimestamp4 = _columnIndexOfCreatedAtTimestamp3;
                long _tmpCreated_at_timestamp = _stmt.getLong(_columnIndexOfCreatedAtTimestamp4);
                int _columnIndexOfProfilePicUrl = _columnIndexOfCreatedAt2;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfProfilePicUrl);
                _columnIndexOfCreatedAt2 = _columnIndexOfProfilePicUrl;
                int _columnIndexOfCreatedAt3 = _columnIndexOfIsWelcomeMessageSent2;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfCreatedAt3);
                int _columnIndexOfIsWelcomeMessageSent3 = _columnIndexOfIsWelcomeMessageSent;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_32 != 0;
                ExpertEntity _item = new ExpertEntity(_tmpId, _tmpName, _tmpPhone, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpRatingSum, _tmpTotalRatingsCount, _tmpCompletedJobsCount, _tmpCancelledJobsCount, _tmpIsWelcomeMessageSent, _tmpIsDeleted, _tmpDeletedAt, _tmpAdded_by_user_id, _tmpAdded_by_user_name, _tmpAdded_by_designation, _tmpProfilePicUrl, _tmpCreated_at_timestamp, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp4;
                _tmp_1 = _columnIndexOfId3;
                _columnIndexOfIsWelcomeMessageSent = _columnIndexOfIsWelcomeMessageSent3;
                _tmp_2 = _columnIndexOfName;
                _columnIndexOfCreatedAtTimestamp = _columnIndexOfPhone;
                _columnIndexOfId = _columnIndexOfId2;
                _tmp_3 = _columnIndexOfAddedByDesignation3;
                _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfCreatedAt3;
                _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt3;
                _columnIndexOfDeletedAt = _columnIndexOfCategory;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.ExpertDao
    public Object getAllExpertsDirectList(Continuation<? super List<ExpertEntity>> continuation) {
        final String _sql = "SELECT * FROM experts";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda14
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.getAllExpertsDirectList$lambda$10(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAllExpertsDirectList$lambda$10(String $_sql, SQLiteConnection _connection) {
        Long _tmpDeletedAt;
        String _tmpAdded_by_user_id;
        String _tmpAdded_by_user_name;
        String _tmpAdded_by_designation;
        String _tmpProfilePicUrl;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _tmp_2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfCreatedAtTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
            int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfRatingSum = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ratingSum");
            int _columnIndexOfTotalRatingsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalRatingsCount");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCancelledJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cancelledJobsCount");
            int _columnIndexOfIsWelcomeMessageSent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isWelcomeMessageSent");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp_1 = _columnIndexOfIsDeleted;
            int _columnIndexOfDeletedAt2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
            int _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt2;
            int _columnIndexOfAddedByUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_id");
            int _columnIndexOfAddedByUserId2 = _columnIndexOfAddedByUserId;
            int _columnIndexOfAddedByUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_user_name");
            int _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserName;
            int _columnIndexOfAddedByDesignation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "added_by_designation");
            int _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByDesignation;
            int _tmp_3 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePicUrl");
            int _columnIndexOfCreatedAtTimestamp2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at_timestamp");
            int _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp2;
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfCreatedAt2 = _columnIndexOfCreatedAt;
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfLastUpdated;
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_tmp_2);
                String _tmpPhone = _stmt.getText(_columnIndexOfCreatedAtTimestamp);
                String _tmpCategory = _stmt.getText(_columnIndexOfDeletedAt);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _columnIndexOfName = _tmp_2;
                int _columnIndexOfPhone = _columnIndexOfCreatedAtTimestamp;
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                int _columnIndexOfCategory = _columnIndexOfDeletedAt;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                float _tmpRatingSum = (float) _stmt.getDouble(_columnIndexOfRatingSum);
                int _tmpTotalRatingsCount = (int) _stmt.getLong(_columnIndexOfTotalRatingsCount);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                int _tmpCancelledJobsCount = (int) _stmt.getLong(_columnIndexOfCancelledJobsCount);
                int _tmp_12 = (int) _stmt.getLong(_columnIndexOfIsWelcomeMessageSent);
                boolean _tmpIsWelcomeMessageSent = _tmp_12 != 0;
                int _columnIndexOfId2 = _columnIndexOfId;
                int _columnIndexOfId3 = _tmp_1;
                int _tmp_22 = (int) _stmt.getLong(_columnIndexOfId3);
                boolean _tmpIsDeleted = _tmp_22 != 0;
                int _columnIndexOfDeletedAt3 = _columnIndexOfIsDeleted2;
                if (_stmt.isNull(_columnIndexOfDeletedAt3)) {
                    _tmpDeletedAt = null;
                } else {
                    Long _tmpDeletedAt2 = Long.valueOf(_stmt.getLong(_columnIndexOfDeletedAt3));
                    _tmpDeletedAt = _tmpDeletedAt2;
                }
                int _columnIndexOfIsDeleted3 = _columnIndexOfAddedByUserId2;
                if (_stmt.isNull(_columnIndexOfIsDeleted3)) {
                    _tmpAdded_by_user_id = null;
                } else {
                    String _tmpAdded_by_user_id2 = _stmt.getText(_columnIndexOfIsDeleted3);
                    _tmpAdded_by_user_id = _tmpAdded_by_user_id2;
                }
                _columnIndexOfAddedByUserId2 = _columnIndexOfIsDeleted3;
                int _columnIndexOfAddedByUserId3 = _columnIndexOfAddedByUserName2;
                if (_stmt.isNull(_columnIndexOfAddedByUserId3)) {
                    _tmpAdded_by_user_name = null;
                } else {
                    String _tmpAdded_by_user_name2 = _stmt.getText(_columnIndexOfAddedByUserId3);
                    _tmpAdded_by_user_name = _tmpAdded_by_user_name2;
                }
                _columnIndexOfAddedByUserName2 = _columnIndexOfAddedByUserId3;
                int _columnIndexOfAddedByUserName3 = _columnIndexOfAddedByDesignation2;
                if (_stmt.isNull(_columnIndexOfAddedByUserName3)) {
                    _tmpAdded_by_designation = null;
                } else {
                    String _tmpAdded_by_designation2 = _stmt.getText(_columnIndexOfAddedByUserName3);
                    _tmpAdded_by_designation = _tmpAdded_by_designation2;
                }
                _columnIndexOfAddedByDesignation2 = _columnIndexOfAddedByUserName3;
                int _columnIndexOfAddedByDesignation3 = _tmp_3;
                if (_stmt.isNull(_columnIndexOfAddedByDesignation3)) {
                    _tmpProfilePicUrl = null;
                } else {
                    String _tmpProfilePicUrl2 = _stmt.getText(_columnIndexOfAddedByDesignation3);
                    _tmpProfilePicUrl = _tmpProfilePicUrl2;
                }
                int _columnIndexOfCreatedAtTimestamp4 = _columnIndexOfCreatedAtTimestamp3;
                long _tmpCreated_at_timestamp = _stmt.getLong(_columnIndexOfCreatedAtTimestamp4);
                int _columnIndexOfProfilePicUrl = _columnIndexOfCreatedAt2;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfProfilePicUrl);
                _columnIndexOfCreatedAt2 = _columnIndexOfProfilePicUrl;
                int _columnIndexOfCreatedAt3 = _columnIndexOfIsWelcomeMessageSent2;
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfCreatedAt3);
                int _columnIndexOfIsWelcomeMessageSent3 = _columnIndexOfIsWelcomeMessageSent;
                int _tmp_32 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_32 != 0;
                ExpertEntity _item = new ExpertEntity(_tmpId, _tmpName, _tmpPhone, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpRatingSum, _tmpTotalRatingsCount, _tmpCompletedJobsCount, _tmpCancelledJobsCount, _tmpIsWelcomeMessageSent, _tmpIsDeleted, _tmpDeletedAt, _tmpAdded_by_user_id, _tmpAdded_by_user_name, _tmpAdded_by_designation, _tmpProfilePicUrl, _tmpCreated_at_timestamp, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _columnIndexOfCreatedAtTimestamp3 = _columnIndexOfCreatedAtTimestamp4;
                _tmp_1 = _columnIndexOfId3;
                _columnIndexOfIsWelcomeMessageSent = _columnIndexOfIsWelcomeMessageSent3;
                _tmp_2 = _columnIndexOfName;
                _columnIndexOfCreatedAtTimestamp = _columnIndexOfPhone;
                _columnIndexOfId = _columnIndexOfId2;
                _tmp_3 = _columnIndexOfAddedByDesignation3;
                _columnIndexOfIsWelcomeMessageSent2 = _columnIndexOfCreatedAt3;
                _columnIndexOfIsDeleted2 = _columnIndexOfDeletedAt3;
                _columnIndexOfDeletedAt = _columnIndexOfCategory;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.ExpertDao
    public Object moveToRecycleBin(final long id, final long deletedAt, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE experts SET isDeleted = 1, deletedAt = ?, last_updated = ?, is_synced = 0 WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda10
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.moveToRecycleBin$lambda$11(_sql, deletedAt, id, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit moveToRecycleBin$lambda$11(String $_sql, long $deletedAt, long $id, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $deletedAt);
            _stmt.bindLong(2, $deletedAt);
            _stmt.bindLong(3, $id);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.ExpertDao
    public Object restoreExpertFromRecycleBin(final long id, final long restoredAt, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE experts SET isDeleted = 0, deletedAt = NULL, last_updated = ?, is_synced = 0 WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda18
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.restoreExpertFromRecycleBin$lambda$12(_sql, restoredAt, id, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit restoreExpertFromRecycleBin$lambda$12(String $_sql, long $restoredAt, long $id, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $restoredAt);
            _stmt.bindLong(2, $id);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.ExpertDao
    public Object purgeExpertsOlderThan(final long cutoffTimestamp, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM experts WHERE isDeleted = 1 AND deletedAt <= ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda8
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.purgeExpertsOlderThan$lambda$13(_sql, cutoffTimestamp, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit purgeExpertsOlderThan$lambda$13(String $_sql, long $cutoffTimestamp, SQLiteConnection _connection) {
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

    @Override // com.example.data.local.ExpertDao
    public Object clearRecycleBin(Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM experts WHERE isDeleted = 1";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda13
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.clearRecycleBin$lambda$14(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit clearRecycleBin$lambda$14(String $_sql, SQLiteConnection _connection) {
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

    @Override // com.example.data.local.ExpertDao
    public Object updateWelcomeMessageSent(final long expertId, final boolean sent, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE experts SET isWelcomeMessageSent = ? WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda12
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.updateWelcomeMessageSent$lambda$15(_sql, sent, expertId, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateWelcomeMessageSent$lambda$15(String $_sql, boolean $sent, long $expertId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _tmp = $sent ? 1 : 0;
        try {
            _stmt.bindLong(1, _tmp);
            _stmt.bindLong(2, $expertId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.ExpertDao
    public Object deleteExpertById(final long id, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM experts WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda11
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.deleteExpertById$lambda$16(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteExpertById$lambda$16(String $_sql, long $id, SQLiteConnection _connection) {
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

    @Override // com.example.data.local.ExpertDao
    public Object clearAllExperts(Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM experts";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda9
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.clearAllExperts$lambda$17(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit clearAllExperts$lambda$17(String $_sql, SQLiteConnection _connection) {
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

    @Override // com.example.data.local.ExpertDao
    public Object markExpertSynced(final long id, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE experts SET is_synced = 1 WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertDao_Impl$$ExternalSyntheticLambda16
            public final Object invoke(Object obj) {
                return ExpertDao_Impl.markExpertSynced$lambda$18(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit markExpertSynced$lambda$18(String $_sql, long $id, SQLiteConnection _connection) {
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

    /* compiled from: ExpertDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/local/ExpertDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
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
