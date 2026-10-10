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
import com.example.data.model.TechnicianEntity;
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

/* compiled from: TechnicianDao_Impl.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0018H\u0016J\u0014\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0018H\u0016J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001b\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001cJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010 J\u001c\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u00182\u0006\u0010\"\u001a\u00020\u001fH\u0016J\u001c\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u00182\u0006\u0010$\u001a\u00020\u001fH\u0016J\u000e\u0010%\u001a\u00020&H\u0096@¢\u0006\u0002\u0010'J\u0016\u0010(\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/example/data/local/TechnicianDao_Impl;", "Lcom/example/data/local/TechnicianDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfTechnicianEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/model/TechnicianEntity;", "__deleteAdapterOfTechnicianEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfTechnicianEntity", "insertTechnician", "", "technician", "(Lcom/example/data/model/TechnicianEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTechnicians", "", "technicians", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTechnician", "updateTechnician", "getAllTechnicians", "Lkotlinx/coroutines/flow/Flow;", "getAvailableTechnicians", "getTechnicianById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTechnicianByContact", "contact", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTechniciansByCategory", "category", "searchTechnicians", "query", "getTechnicianCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTechnicianById", "Companion", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes7.dex */
public final class TechnicianDao_Impl implements TechnicianDao {
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<TechnicianEntity> __deleteAdapterOfTechnicianEntity;
    private final EntityInsertAdapter<TechnicianEntity> __insertAdapterOfTechnicianEntity;
    private final EntityDeleteOrUpdateAdapter<TechnicianEntity> __updateAdapterOfTechnicianEntity;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public TechnicianDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfTechnicianEntity = new EntityInsertAdapter<TechnicianEntity>() { // from class: com.example.data.local.TechnicianDao_Impl.1
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `technicians` (`id`,`name`,`contact`,`category`,`address`,`latitude`,`longitude`,`isAvailable`,`rating`,`completedJobsCount`,`createdAt`,`last_updated`,`is_synced`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, TechnicianEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
                statement.bindText(2, entity.getName());
                statement.bindText(3, entity.getContact());
                statement.bindText(4, entity.getCategory());
                statement.bindText(5, entity.getAddress());
                statement.bindDouble(6, entity.getLatitude());
                statement.bindDouble(7, entity.getLongitude());
                statement.bindLong(8, entity.isAvailable() ? 1L : 0L);
                statement.bindDouble(9, entity.getRating());
                statement.bindLong(10, entity.getCompletedJobsCount());
                statement.bindLong(11, entity.getCreatedAt());
                statement.bindLong(12, entity.getLast_updated());
                statement.bindLong(13, entity.is_synced() ? 1L : 0L);
            }
        };
        this.__deleteAdapterOfTechnicianEntity = new EntityDeleteOrUpdateAdapter<TechnicianEntity>() { // from class: com.example.data.local.TechnicianDao_Impl.2
            protected String createQuery() {
                return "DELETE FROM `technicians` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, TechnicianEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfTechnicianEntity = new EntityDeleteOrUpdateAdapter<TechnicianEntity>() { // from class: com.example.data.local.TechnicianDao_Impl.3
            protected String createQuery() {
                return "UPDATE OR ABORT `technicians` SET `id` = ?,`name` = ?,`contact` = ?,`category` = ?,`address` = ?,`latitude` = ?,`longitude` = ?,`isAvailable` = ?,`rating` = ?,`completedJobsCount` = ?,`createdAt` = ?,`last_updated` = ?,`is_synced` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, TechnicianEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
                statement.bindText(2, entity.getName());
                statement.bindText(3, entity.getContact());
                statement.bindText(4, entity.getCategory());
                statement.bindText(5, entity.getAddress());
                statement.bindDouble(6, entity.getLatitude());
                statement.bindDouble(7, entity.getLongitude());
                statement.bindLong(8, entity.isAvailable() ? 1L : 0L);
                statement.bindDouble(9, entity.getRating());
                statement.bindLong(10, entity.getCompletedJobsCount());
                statement.bindLong(11, entity.getCreatedAt());
                statement.bindLong(12, entity.getLast_updated());
                statement.bindLong(13, entity.is_synced() ? 1L : 0L);
                statement.bindLong(14, entity.getId());
            }
        };
    }

    @Override // com.example.data.local.TechnicianDao
    public Object insertTechnician(final TechnicianEntity technician, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda4
            public final Object invoke(Object obj) {
                return Long.valueOf(TechnicianDao_Impl.insertTechnician$lambda$0(TechnicianDao_Impl.this, technician, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final long insertTechnician$lambda$0(TechnicianDao_Impl this$0, TechnicianEntity $technician, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfTechnicianEntity.insertAndReturnId(_connection, $technician);
        return _result;
    }

    @Override // com.example.data.local.TechnicianDao
    public Object insertTechnicians(final List<TechnicianEntity> list, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda6
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.insertTechnicians$lambda$1(TechnicianDao_Impl.this, list, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit insertTechnicians$lambda$1(TechnicianDao_Impl this$0, List $technicians, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfTechnicianEntity.insert(_connection, $technicians);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.TechnicianDao
    public Object deleteTechnician(final TechnicianEntity technician, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda3
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.deleteTechnician$lambda$2(TechnicianDao_Impl.this, technician, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteTechnician$lambda$2(TechnicianDao_Impl this$0, TechnicianEntity $technician, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfTechnicianEntity.handle(_connection, $technician);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.TechnicianDao
    public Object updateTechnician(final TechnicianEntity technician, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda2
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.updateTechnician$lambda$3(TechnicianDao_Impl.this, technician, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateTechnician$lambda$3(TechnicianDao_Impl this$0, TechnicianEntity $technician, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfTechnicianEntity.handle(_connection, $technician);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.TechnicianDao
    public Flow<List<TechnicianEntity>> getAllTechnicians() {
        final String _sql = "SELECT * FROM technicians ORDER BY name ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"technicians"}, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda1
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.getAllTechnicians$lambda$4(_sql, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAllTechnicians$lambda$4(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _tmp_1 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_tmp_1);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpCategory = _stmt.getText(_columnIndexOfCategory);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _columnIndexOfId = _tmp_1;
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_12 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_12 != 0;
                TechnicianEntity _item = new TechnicianEntity(_tmpId, _tmpName, _tmpContact, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpCompletedJobsCount, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _tmp_1 = _columnIndexOfId;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.TechnicianDao
    public Flow<List<TechnicianEntity>> getAvailableTechnicians() {
        final String _sql = "SELECT * FROM technicians WHERE isAvailable = 1 ORDER BY name ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"technicians"}, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda9
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.getAvailableTechnicians$lambda$5(_sql, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAvailableTechnicians$lambda$5(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _tmp_1 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_tmp_1);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpCategory = _stmt.getText(_columnIndexOfCategory);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _columnIndexOfId = _tmp_1;
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_12 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_12 != 0;
                TechnicianEntity _item = new TechnicianEntity(_tmpId, _tmpName, _tmpContact, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpCompletedJobsCount, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _tmp_1 = _columnIndexOfId;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.TechnicianDao
    public Object getTechnicianById(final long id, Continuation<? super TechnicianEntity> continuation) {
        final String _sql = "SELECT * FROM technicians WHERE id = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.getTechnicianById$lambda$6(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final TechnicianEntity getTechnicianById$lambda$6(String $_sql, long $id, SQLiteConnection _connection) {
        TechnicianEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $id);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            if (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpCategory = _stmt.getText(_columnIndexOfCategory);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_1 != 0;
                _result = new TechnicianEntity(_tmpId, _tmpName, _tmpContact, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpCompletedJobsCount, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.TechnicianDao
    public Object getTechnicianByContact(final String contact, Continuation<? super TechnicianEntity> continuation) {
        final String _sql = "SELECT * FROM technicians WHERE contact = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda10
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.getTechnicianByContact$lambda$7(_sql, contact, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final TechnicianEntity getTechnicianByContact$lambda$7(String $_sql, String $contact, SQLiteConnection _connection) {
        TechnicianEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $contact);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            if (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpCategory = _stmt.getText(_columnIndexOfCategory);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_1 != 0;
                _result = new TechnicianEntity(_tmpId, _tmpName, _tmpContact, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpCompletedJobsCount, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.TechnicianDao
    public Flow<List<TechnicianEntity>> getTechniciansByCategory(final String category) {
        Intrinsics.checkNotNullParameter(category, "category");
        final String _sql = "SELECT * FROM technicians WHERE category = ? ORDER BY name ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"technicians"}, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda5
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.getTechniciansByCategory$lambda$8(_sql, category, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getTechniciansByCategory$lambda$8(String $_sql, String $category, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $category);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpCategory = _stmt.getText(_columnIndexOfCategory);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _columnIndexOfId2 = _columnIndexOfId;
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                int _columnIndexOfName2 = _columnIndexOfName;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_1 != 0;
                TechnicianEntity _item = new TechnicianEntity(_tmpId, _tmpName, _tmpContact, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpCompletedJobsCount, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _columnIndexOfId = _columnIndexOfId2;
                _columnIndexOfName = _columnIndexOfName2;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.TechnicianDao
    public Flow<List<TechnicianEntity>> searchTechnicians(final String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        final String _sql = "SELECT * FROM technicians WHERE name LIKE '%' || ? || '%' OR contact LIKE '%' || ? || '%' OR address LIKE '%' || ? || '%'";
        return FlowUtil.createFlow(this.__db, false, new String[]{"technicians"}, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda11
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.searchTechnicians$lambda$9(_sql, query, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List searchTechnicians$lambda$9(String $_sql, String $query, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $query);
            _stmt.bindText(2, $query);
            _stmt.bindText(3, $query);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
            int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            int _columnIndexOfCompletedJobsCount = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completedJobsCount");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpCategory = _stmt.getText(_columnIndexOfCategory);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsAvailable);
                boolean _tmpIsAvailable = _tmp != 0;
                float _tmpRating = (float) _stmt.getDouble(_columnIndexOfRating);
                int _tmpCompletedJobsCount = (int) _stmt.getLong(_columnIndexOfCompletedJobsCount);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_1 != 0;
                TechnicianEntity _item = new TechnicianEntity(_tmpId, _tmpName, _tmpContact, _tmpCategory, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpIsAvailable, _tmpRating, _tmpCompletedJobsCount, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                int _columnIndexOfLastUpdated2 = _columnIndexOfLastUpdated;
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _columnIndexOfLastUpdated = _columnIndexOfLastUpdated2;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.TechnicianDao
    public Object getTechnicianCount(Continuation<? super Integer> continuation) {
        final String _sql = "SELECT COUNT(*) FROM technicians";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda7
            public final Object invoke(Object obj) {
                return Integer.valueOf(TechnicianDao_Impl.getTechnicianCount$lambda$10(_sql, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int getTechnicianCount$lambda$10(String $_sql, SQLiteConnection _connection) {
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

    @Override // com.example.data.local.TechnicianDao
    public Object deleteTechnicianById(final long id, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM technicians WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.TechnicianDao_Impl$$ExternalSyntheticLambda8
            public final Object invoke(Object obj) {
                return TechnicianDao_Impl.deleteTechnicianById$lambda$11(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteTechnicianById$lambda$11(String $_sql, long $id, SQLiteConnection _connection) {
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

    /* compiled from: TechnicianDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/local/TechnicianDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
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
