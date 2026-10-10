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
import com.example.data.model.CustomerEntity;
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

/* compiled from: CustomerDao_Impl.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u0018H\u0016J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001a\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0096@¢\u0006\u0002\u0010\u001fJ\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u00182\u0006\u0010!\u001a\u00020\u001eH\u0016J\u000e\u0010\"\u001a\u00020#H\u0096@¢\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/example/data/local/CustomerDao_Impl;", "Lcom/example/data/local/CustomerDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfCustomerEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/example/data/model/CustomerEntity;", "__deleteAdapterOfCustomerEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfCustomerEntity", "insertCustomer", "", "customer", "(Lcom/example/data/model/CustomerEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCustomers", "", "customers", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomer", "updateCustomer", "getAllCustomers", "Lkotlinx/coroutines/flow/Flow;", "getCustomerById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCustomerByContact", "contact", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchCustomers", "query", "getCustomerCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomerById", "Companion", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes7.dex */
public final class CustomerDao_Impl implements CustomerDao {
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<CustomerEntity> __deleteAdapterOfCustomerEntity;
    private final EntityInsertAdapter<CustomerEntity> __insertAdapterOfCustomerEntity;
    private final EntityDeleteOrUpdateAdapter<CustomerEntity> __updateAdapterOfCustomerEntity;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public CustomerDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfCustomerEntity = new EntityInsertAdapter<CustomerEntity>() { // from class: com.example.data.local.CustomerDao_Impl.1
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `customers` (`id`,`name`,`contact`,`address`,`latitude`,`longitude`,`serviceRequired`,`issueDescription`,`createdAt`,`last_updated`,`is_synced`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, CustomerEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
                statement.bindText(2, entity.getName());
                statement.bindText(3, entity.getContact());
                statement.bindText(4, entity.getAddress());
                statement.bindDouble(5, entity.getLatitude());
                statement.bindDouble(6, entity.getLongitude());
                statement.bindText(7, entity.getServiceRequired());
                statement.bindText(8, entity.getIssueDescription());
                statement.bindLong(9, entity.getCreatedAt());
                statement.bindLong(10, entity.getLast_updated());
                statement.bindLong(11, entity.is_synced() ? 1L : 0L);
            }
        };
        this.__deleteAdapterOfCustomerEntity = new EntityDeleteOrUpdateAdapter<CustomerEntity>() { // from class: com.example.data.local.CustomerDao_Impl.2
            protected String createQuery() {
                return "DELETE FROM `customers` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, CustomerEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfCustomerEntity = new EntityDeleteOrUpdateAdapter<CustomerEntity>() { // from class: com.example.data.local.CustomerDao_Impl.3
            protected String createQuery() {
                return "UPDATE OR ABORT `customers` SET `id` = ?,`name` = ?,`contact` = ?,`address` = ?,`latitude` = ?,`longitude` = ?,`serviceRequired` = ?,`issueDescription` = ?,`createdAt` = ?,`last_updated` = ?,`is_synced` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void bind(SQLiteStatement statement, CustomerEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.bindLong(1, entity.getId());
                statement.bindText(2, entity.getName());
                statement.bindText(3, entity.getContact());
                statement.bindText(4, entity.getAddress());
                statement.bindDouble(5, entity.getLatitude());
                statement.bindDouble(6, entity.getLongitude());
                statement.bindText(7, entity.getServiceRequired());
                statement.bindText(8, entity.getIssueDescription());
                statement.bindLong(9, entity.getCreatedAt());
                statement.bindLong(10, entity.getLast_updated());
                statement.bindLong(11, entity.is_synced() ? 1L : 0L);
                statement.bindLong(12, entity.getId());
            }
        };
    }

    @Override // com.example.data.local.CustomerDao
    public Object insertCustomer(final CustomerEntity customer, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda1
            public final Object invoke(Object obj) {
                return Long.valueOf(CustomerDao_Impl.insertCustomer$lambda$0(CustomerDao_Impl.this, customer, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final long insertCustomer$lambda$0(CustomerDao_Impl this$0, CustomerEntity $customer, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfCustomerEntity.insertAndReturnId(_connection, $customer);
        return _result;
    }

    @Override // com.example.data.local.CustomerDao
    public Object insertCustomers(final List<CustomerEntity> list, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda4
            public final Object invoke(Object obj) {
                return CustomerDao_Impl.insertCustomers$lambda$1(CustomerDao_Impl.this, list, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit insertCustomers$lambda$1(CustomerDao_Impl this$0, List $customers, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfCustomerEntity.insert(_connection, $customers);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.CustomerDao
    public Object deleteCustomer(final CustomerEntity customer, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda7
            public final Object invoke(Object obj) {
                return CustomerDao_Impl.deleteCustomer$lambda$2(CustomerDao_Impl.this, customer, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteCustomer$lambda$2(CustomerDao_Impl this$0, CustomerEntity $customer, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfCustomerEntity.handle(_connection, $customer);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.CustomerDao
    public Object updateCustomer(final CustomerEntity customer, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda9
            public final Object invoke(Object obj) {
                return CustomerDao_Impl.updateCustomer$lambda$3(CustomerDao_Impl.this, customer, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit updateCustomer$lambda$3(CustomerDao_Impl this$0, CustomerEntity $customer, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfCustomerEntity.handle(_connection, $customer);
        return Unit.INSTANCE;
    }

    @Override // com.example.data.local.CustomerDao
    public Flow<List<CustomerEntity>> getAllCustomers() {
        final String _sql = "SELECT * FROM customers ORDER BY createdAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"customers"}, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda2
            public final Object invoke(Object obj) {
                return CustomerDao_Impl.getAllCustomers$lambda$4(_sql, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List getAllCustomers$lambda$4(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _tmp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfServiceRequired = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceRequired");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_tmp);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpServiceRequired = _stmt.getText(_columnIndexOfServiceRequired);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _columnIndexOfId = _tmp;
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp2 != 0;
                CustomerEntity _item = new CustomerEntity(_tmpId, _tmpName, _tmpContact, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpServiceRequired, _tmpIssueDescription, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                _result.add(_item);
                _tmp = _columnIndexOfId;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerDao
    public Object getCustomerById(final long id, Continuation<? super CustomerEntity> continuation) {
        final String _sql = "SELECT * FROM customers WHERE id = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda8
            public final Object invoke(Object obj) {
                return CustomerDao_Impl.getCustomerById$lambda$5(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final CustomerEntity getCustomerById$lambda$5(String $_sql, long $id, SQLiteConnection _connection) {
        CustomerEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindLong(1, $id);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfServiceRequired = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceRequired");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            if (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpServiceRequired = _stmt.getText(_columnIndexOfServiceRequired);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp != 0;
                _result = new CustomerEntity(_tmpId, _tmpName, _tmpContact, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpServiceRequired, _tmpIssueDescription, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerDao
    public Object getCustomerByContact(final String contact, Continuation<? super CustomerEntity> continuation) {
        final String _sql = "SELECT * FROM customers WHERE contact = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda3
            public final Object invoke(Object obj) {
                return CustomerDao_Impl.getCustomerByContact$lambda$6(_sql, contact, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final CustomerEntity getCustomerByContact$lambda$6(String $_sql, String $contact, SQLiteConnection _connection) {
        CustomerEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $contact);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfServiceRequired = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceRequired");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            if (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpServiceRequired = _stmt.getText(_columnIndexOfServiceRequired);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp != 0;
                _result = new CustomerEntity(_tmpId, _tmpName, _tmpContact, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpServiceRequired, _tmpIssueDescription, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerDao
    public Flow<List<CustomerEntity>> searchCustomers(final String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        final String _sql = "SELECT * FROM customers WHERE name LIKE '%' || ? || '%' OR contact LIKE '%' || ? || '%'";
        return FlowUtil.createFlow(this.__db, false, new String[]{"customers"}, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda6
            public final Object invoke(Object obj) {
                return CustomerDao_Impl.searchCustomers$lambda$7(_sql, query, (SQLiteConnection) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final List searchCustomers$lambda$7(String $_sql, String $query, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.bindText(1, $query);
            int _argIndex = 2;
            _stmt.bindText(2, $query);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfContact = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "contact");
            int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
            int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
            int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
            int _columnIndexOfServiceRequired = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serviceRequired");
            int _columnIndexOfIssueDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "issueDescription");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "last_updated");
            int _columnIndexOfIsSynced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_synced");
            List _result = new ArrayList();
            while (_stmt.step()) {
                long _tmpId = _stmt.getLong(_columnIndexOfId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpContact = _stmt.getText(_columnIndexOfContact);
                String _tmpAddress = _stmt.getText(_columnIndexOfAddress);
                double _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
                double _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
                String _tmpServiceRequired = _stmt.getText(_columnIndexOfServiceRequired);
                String _tmpIssueDescription = _stmt.getText(_columnIndexOfIssueDescription);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpLast_updated = _stmt.getLong(_columnIndexOfLastUpdated);
                int _argIndex2 = _argIndex;
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsSynced);
                boolean _tmpIs_synced = _tmp != 0;
                CustomerEntity _item = new CustomerEntity(_tmpId, _tmpName, _tmpContact, _tmpAddress, _tmpLatitude, _tmpLongitude, _tmpServiceRequired, _tmpIssueDescription, _tmpCreatedAt, _tmpLast_updated, _tmpIs_synced);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _argIndex = _argIndex2;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.example.data.local.CustomerDao
    public Object getCustomerCount(Continuation<? super Integer> continuation) {
        final String _sql = "SELECT COUNT(*) FROM customers";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda5
            public final Object invoke(Object obj) {
                return Integer.valueOf(CustomerDao_Impl.getCustomerCount$lambda$8(_sql, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int getCustomerCount$lambda$8(String $_sql, SQLiteConnection _connection) {
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

    @Override // com.example.data.local.CustomerDao
    public Object deleteCustomerById(final long id, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM customers WHERE id = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.example.data.local.CustomerDao_Impl$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                return CustomerDao_Impl.deleteCustomerById$lambda$9(_sql, id, (SQLiteConnection) obj);
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit deleteCustomerById$lambda$9(String $_sql, long $id, SQLiteConnection _connection) {
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

    /* compiled from: CustomerDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/data/local/CustomerDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
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
