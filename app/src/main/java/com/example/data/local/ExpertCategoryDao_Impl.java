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
import com.example.data.model.ExpertCategoryEntity;
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

/* compiled from: ExpertCategoryDao_Impl.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0012H\u0016J\u000e\u0010\u0014\u001a\u00020\u0015H\u0096@¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010\u0016J\u0016\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/example/data/local/ExpertCategoryDao_Impl;", "Lcom/example/data/local/ExpertCategoryDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfExpertCategoryEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/model/ExpertCategoryEntity;", "__deleteAdapterOfExpertCategoryEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "insertCategory", "", "category", "(Lcom/example/data/model/ExpertCategoryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCategory", "", "getAllCategories", "Lkotlinx/coroutines/flow/Flow;", "", "getCategoryCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsyncedCategories", "getAllCategoriesDirectList", "deleteCategoryByName", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markCategorySynced", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes7.dex */
public final class ExpertCategoryDao_Impl implements ExpertCategoryDao {
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<ExpertCategoryEntity> __deleteAdapterOfExpertCategoryEntity;
    private final EntityInsertAdapter<ExpertCategoryEntity> __insertAdapterOfExpertCategoryEntity;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public ExpertCategoryDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfExpertCategoryEntity = new EntityInsertAdapter<ExpertCategoryEntity>() { // from class: com.example.data.local.ExpertCategoryDao_Impl.1
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `expert_categories` (`id`,`name`,`isDefault`,`createdAt`,`last_updated`,`is_synced`) VALUES (nullif(?, 0),?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, ExpertCategoryEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
                statement.bindText(2, entity.getName());
                statement.bindLong(3, entity.isDefault() ? 1L : 0L);
                statement.bindLong(4, entity.getCreatedAt());
                statement.bindLong(5, entity.getLast_updated());
                statement.bindLong(6, entity.is_synced() ? 1L : 0L);
            }
        };
        this.__deleteAdapterOfExpertCategoryEntity = new EntityDeleteOrUpdateAdapter<ExpertCategoryEntity>() { // from class: com.example.data.local.ExpertCategoryDao_Impl.2
            protected String createQuery() {
                return "DELETE FROM `expert_categories` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, ExpertCategoryEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
            }
        };
    }

    @Override // com.example.data.local.ExpertCategoryDao
    public Object insertCategory(final ExpertCategoryEntity category, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertCategoryDao_Impl$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                return Long.valueOf(ExpertCategoryDao_Impl.insertCategory$lambda$0(ExpertCategoryDao_Impl.this, category, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final long insertCategory$lambda$0(ExpertCategoryDao_Impl this$0, ExpertCategoryEntity $category, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfExpertCategoryEntity.insertAndReturnId(_connection, $category);
        return _result;
    }

    @Override // com.example.data.local.ExpertCategoryDao
    public Object deleteCategory(final ExpertCategoryEntity category, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertCategoryDao_Impl$$ExternalSyntheticLambda7
            public final Object invoke(Object obj) {
                return ExpertCategoryDao_Impl.deleteCategory$lambda$1(ExpertCategoryDao_Impl.this, category, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteCategory$lambda$1(ExpertCategoryDao_Impl this$0, ExpertCategoryEntity $category, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfExpertCategoryEntity.handle(_connection, $category);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.ExpertCategoryDao
    public Flow<List<ExpertCategoryEntity>> getAllCategories() {
        final String _sql = "SELECT * FROM expert_categories ORDER BY isDefault DESC, name ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{FirestoreSyncManager.CATEGORIES_COLLECTION}, new Function1() { // from class: com.example.data.local.ExpertCategoryDao_Impl$$ExternalSyntheticLambda1
            public final Object invoke(Object obj) {
                return ExpertCategoryDao_Impl.getAllCategories$lambda$2(_sql, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAllCategories$lambda$2(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfIsDefault = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDefault");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsDefault);
                boolean _tmpIsDefault = _tmp != 0;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_1 != 0;
                ExpertCategoryEntity _item = new ExpertCategoryEntity(_tmpId, _tmpName, _tmpIsDefault, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                _result.add(_item);
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.ExpertCategoryDao
    public Object getCategoryCount(Continuation<? super Integer> continuation) {
        final String _sql = "SELECT COUNT(*) FROM expert_categories";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.ExpertCategoryDao_Impl$$ExternalSyntheticLambda2
            public final Object invoke(Object obj) {
                return Integer.valueOf(ExpertCategoryDao_Impl.getCategoryCount$lambda$3(_sql, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int getCategoryCount$lambda$3(String $_sql, SQLiteConnection _connection) {
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

    @Override // com.example.data.local.ExpertCategoryDao
    public Object getUnsyncedCategories(Continuation<? super List<ExpertCategoryEntity>> continuation) {
        final String _sql = "SELECT * FROM expert_categories WHERE is_synced = 0";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.ExpertCategoryDao_Impl$$ExternalSyntheticLambda3
            public final Object invoke(Object obj) {
                return ExpertCategoryDao_Impl.getUnsyncedCategories$lambda$4(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getUnsyncedCategories$lambda$4(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfIsDefault = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDefault");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsDefault);
                boolean _tmpIsDefault = _tmp != 0;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_1 != 0;
                ExpertCategoryEntity _item = new ExpertCategoryEntity(_tmpId, _tmpName, _tmpIsDefault, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                _result.add(_item);
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.ExpertCategoryDao
    public Object getAllCategoriesDirectList(Continuation<? super List<ExpertCategoryEntity>> continuation) {
        final String _sql = "SELECT * FROM expert_categories";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.ExpertCategoryDao_Impl$$ExternalSyntheticLambda5
            public final Object invoke(Object obj) {
                return ExpertCategoryDao_Impl.getAllCategoriesDirectList$lambda$5(_sql, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAllCategoriesDirectList$lambda$5(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfIsDefault = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDefault");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsDefault);
                boolean _tmpIsDefault = _tmp != 0;
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp_1 != 0;
                ExpertCategoryEntity _item = new ExpertCategoryEntity(_tmpId, _tmpName, _tmpIsDefault, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                _result.add(_item);
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.ExpertCategoryDao
    public Object deleteCategoryByName(final String name, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM expert_categories WHERE name = ? AND isDefault = 0";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertCategoryDao_Impl$$ExternalSyntheticLambda6
            public final Object invoke(Object obj) {
                return ExpertCategoryDao_Impl.deleteCategoryByName$lambda$6(_sql, name, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteCategoryByName$lambda$6(String $_sql, String $name, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $name);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    @Override // com.example.data.local.ExpertCategoryDao
    public Object markCategorySynced(final long id, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE expert_categories SET is_synced = 1 WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.ExpertCategoryDao_Impl$$ExternalSyntheticLambda4
            public final Object invoke(Object obj) {
                return ExpertCategoryDao_Impl.markCategorySynced$lambda$7(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit markCategorySynced$lambda$7(String $_sql, long $id, SQLiteConnection _connection) {
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

    /* compiled from: ExpertCategoryDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/local/ExpertCategoryDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
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
