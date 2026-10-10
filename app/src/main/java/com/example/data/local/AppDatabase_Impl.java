package com.example.data.local;

import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.example.BuildConfig;
import com.example.data.firebase.FirestoreSyncManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: AppDatabase_Impl.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\"\u0010\u0015\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u00180\u0016H\u0014J\u0016\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\u00170\u001aH\u0016J*\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00182\u001a\u0010\u001e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\u0017\u0012\u0004\u0012\u00020\u001b0\u0016H\u0016J\b\u0010\u001f\u001a\u00020\u0006H\u0016J\b\u0010 \u001a\u00020\bH\u0016J\b\u0010!\u001a\u00020\nH\u0016J\b\u0010\"\u001a\u00020\fH\u0016J\b\u0010#\u001a\u00020\u000eH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/example/data/local/AppDatabase_Impl;", "Lcom/example/data/local/AppDatabase;", "<init>", "()V", "_technicianDao", "Lkotlin/Lazy;", "Lcom/example/data/local/TechnicianDao;", "_customerDao", "Lcom/example/data/local/CustomerDao;", "_expertDao", "Lcom/example/data/local/ExpertDao;", "_customerJobDao", "Lcom/example/data/local/CustomerJobDao;", "_expertCategoryDao", "Lcom/example/data/local/ExpertCategoryDao;", "createOpenDelegate", "Landroidx/room/RoomOpenDelegate;", "createInvalidationTracker", "Landroidx/room/InvalidationTracker;", "clearAllTables", "", "getRequiredTypeConverterClasses", "", "Lkotlin/reflect/KClass;", "", "getRequiredAutoMigrationSpecClasses", "", "Landroidx/room/migration/AutoMigrationSpec;", "createAutoMigrations", "Landroidx/room/migration/Migration;", "autoMigrationSpecs", "technicianDao", "customerDao", "expertDao", "customerJobDao", "expertCategoryDao", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes7.dex */
public final class AppDatabase_Impl extends AppDatabase {
    public static final int $stable = 8;
    private final Lazy<TechnicianDao> _technicianDao = LazyKt.lazy(new Function0() { // from class: com.example.data.local.AppDatabase_Impl$$ExternalSyntheticLambda0
        public final Object invoke() {
            return AppDatabase_Impl._technicianDao$lambda$0(AppDatabase_Impl.this);
        }
    });
    private final Lazy<CustomerDao> _customerDao = LazyKt.lazy(new Function0() { // from class: com.example.data.local.AppDatabase_Impl$$ExternalSyntheticLambda1
        public final Object invoke() {
            return AppDatabase_Impl._customerDao$lambda$1(AppDatabase_Impl.this);
        }
    });
    private final Lazy<ExpertDao> _expertDao = LazyKt.lazy(new Function0() { // from class: com.example.data.local.AppDatabase_Impl$$ExternalSyntheticLambda2
        public final Object invoke() {
            return AppDatabase_Impl._expertDao$lambda$2(AppDatabase_Impl.this);
        }
    });
    private final Lazy<CustomerJobDao> _customerJobDao = LazyKt.lazy(new Function0() { // from class: com.example.data.local.AppDatabase_Impl$$ExternalSyntheticLambda3
        public final Object invoke() {
            return AppDatabase_Impl._customerJobDao$lambda$3(AppDatabase_Impl.this);
        }
    });
    private final Lazy<ExpertCategoryDao> _expertCategoryDao = LazyKt.lazy(new Function0() { // from class: com.example.data.local.AppDatabase_Impl$$ExternalSyntheticLambda4
        public final Object invoke() {
            return AppDatabase_Impl._expertCategoryDao$lambda$4(AppDatabase_Impl.this);
        }
    });

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final TechnicianDao_Impl _technicianDao$lambda$0(AppDatabase_Impl this$0) {
        return new TechnicianDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final CustomerDao_Impl _customerDao$lambda$1(AppDatabase_Impl this$0) {
        return new CustomerDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final ExpertDao_Impl _expertDao$lambda$2(AppDatabase_Impl this$0) {
        return new ExpertDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final CustomerJobDao_Impl _customerJobDao$lambda$3(AppDatabase_Impl this$0) {
        return new CustomerJobDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final ExpertCategoryDao_Impl _expertCategoryDao$lambda$4(AppDatabase_Impl this$0) {
        return new ExpertCategoryDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: createOpenDelegate, reason: merged with bridge method [inline-methods] */
    public RoomOpenDelegate m18createOpenDelegate() {
        RoomOpenDelegate _openDelegate = new RoomOpenDelegate() { // from class: com.example.data.local.AppDatabase_Impl$createOpenDelegate$_openDelegate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(10, "29ee1bd8de3e8824f9c019ec37903514", "23dca8a3c2d40c21f5087fb9ccefcecc");
            }

            public void createAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `technicians` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `contact` TEXT NOT NULL, `category` TEXT NOT NULL, `address` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `isAvailable` INTEGER NOT NULL, `rating` REAL NOT NULL, `completedJobsCount` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `last_updated` INTEGER NOT NULL, `is_synced` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `customers` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `contact` TEXT NOT NULL, `address` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `serviceRequired` TEXT NOT NULL, `issueDescription` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `last_updated` INTEGER NOT NULL, `is_synced` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `experts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `phone` TEXT NOT NULL, `category` TEXT NOT NULL, `address` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `isAvailable` INTEGER NOT NULL, `rating` REAL NOT NULL, `ratingSum` REAL NOT NULL, `totalRatingsCount` INTEGER NOT NULL, `completedJobsCount` INTEGER NOT NULL, `cancelledJobsCount` INTEGER NOT NULL, `isWelcomeMessageSent` INTEGER NOT NULL, `isDeleted` INTEGER NOT NULL, `deletedAt` INTEGER, `added_by_user_id` TEXT, `added_by_user_name` TEXT, `added_by_designation` TEXT, `profilePicUrl` TEXT, `created_at_timestamp` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `last_updated` INTEGER NOT NULL, `is_synced` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `customer_jobs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `customerName` TEXT NOT NULL, `customerPhone` TEXT NOT NULL, `serviceType` TEXT NOT NULL, `issueDescription` TEXT NOT NULL, `address` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `status` TEXT NOT NULL, `assignedExpertId` INTEGER, `assignedExpertName` TEXT, `assignedExpertPhone` TEXT, `distanceKmAtDispatch` REAL, `ratingGiven` REAL, `reviewFeedback` TEXT, `createdAt` INTEGER NOT NULL, `completedAt` INTEGER, `isExpertNotified` INTEGER NOT NULL, `isCustomerNotifiedOnAssign` INTEGER NOT NULL, `isCustomerNotifiedOnCompletion` INTEGER NOT NULL, `assignMessageLaterDismissedAt` INTEGER, `isDeleted` INTEGER NOT NULL, `deletedAt` INTEGER, `last_updated` INTEGER NOT NULL, `is_synced` INTEGER NOT NULL, `created_by_user_id` TEXT, `created_by_user_name` TEXT, `created_by_designation` TEXT, `managed_by_user_id` TEXT, `managed_by_user_name` TEXT, `managed_by_designation` TEXT, `assigned_technician_id` INTEGER, `assigned_technician_name` TEXT, `assigned_at_timestamp` INTEGER)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `expert_categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `isDefault` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `last_updated` INTEGER NOT NULL, `is_synced` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '29ee1bd8de3e8824f9c019ec37903514')");
            }

            public void dropAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `technicians`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `customers`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `experts`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `customer_jobs`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `expert_categories`");
            }

            public void onCreate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            public void onOpen(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                AppDatabase_Impl.this.internalInitInvalidationTracker(connection);
            }

            public void onPreMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                DBUtil.dropFtsSyncTriggers(connection);
            }

            public void onPostMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                Map _columnsTechnicians = new LinkedHashMap();
                _columnsTechnicians.put("id", new TableInfo.Column("id", "INTEGER", true, 1, (String) null, 1));
                _columnsTechnicians.put("name", new TableInfo.Column("name", "TEXT", true, 0, (String) null, 1));
                _columnsTechnicians.put("contact", new TableInfo.Column("contact", "TEXT", true, 0, (String) null, 1));
                _columnsTechnicians.put("category", new TableInfo.Column("category", "TEXT", true, 0, (String) null, 1));
                _columnsTechnicians.put("address", new TableInfo.Column("address", "TEXT", true, 0, (String) null, 1));
                _columnsTechnicians.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, (String) null, 1));
                _columnsTechnicians.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, (String) null, 1));
                _columnsTechnicians.put("isAvailable", new TableInfo.Column("isAvailable", "INTEGER", true, 0, (String) null, 1));
                _columnsTechnicians.put("rating", new TableInfo.Column("rating", "REAL", true, 0, (String) null, 1));
                _columnsTechnicians.put("completedJobsCount", new TableInfo.Column("completedJobsCount", "INTEGER", true, 0, (String) null, 1));
                _columnsTechnicians.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, (String) null, 1));
                _columnsTechnicians.put("last_updated", new TableInfo.Column("last_updated", "INTEGER", true, 0, (String) null, 1));
                _columnsTechnicians.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, (String) null, 1));
                Set _foreignKeysTechnicians = new LinkedHashSet();
                Set _indicesTechnicians = new LinkedHashSet();
                TableInfo _infoTechnicians = new TableInfo("technicians", _columnsTechnicians, _foreignKeysTechnicians, _indicesTechnicians);
                TableInfo _existingTechnicians = TableInfo.Companion.read(connection, "technicians");
                if (!_infoTechnicians.equals(_existingTechnicians)) {
                    return new RoomOpenDelegate.ValidationResult(false, "technicians(com.example.data.model.TechnicianEntity).\n Expected:\n" + _infoTechnicians + "\n Found:\n" + _existingTechnicians);
                }
                Map _columnsCustomers = new LinkedHashMap();
                _columnsCustomers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, (String) null, 1));
                _columnsCustomers.put("name", new TableInfo.Column("name", "TEXT", true, 0, (String) null, 1));
                _columnsCustomers.put("contact", new TableInfo.Column("contact", "TEXT", true, 0, (String) null, 1));
                _columnsCustomers.put("address", new TableInfo.Column("address", "TEXT", true, 0, (String) null, 1));
                _columnsCustomers.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, (String) null, 1));
                _columnsCustomers.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, (String) null, 1));
                _columnsCustomers.put("serviceRequired", new TableInfo.Column("serviceRequired", "TEXT", true, 0, (String) null, 1));
                _columnsCustomers.put("issueDescription", new TableInfo.Column("issueDescription", "TEXT", true, 0, (String) null, 1));
                _columnsCustomers.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, (String) null, 1));
                _columnsCustomers.put("last_updated", new TableInfo.Column("last_updated", "INTEGER", true, 0, (String) null, 1));
                _columnsCustomers.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, (String) null, 1));
                Set _foreignKeysCustomers = new LinkedHashSet();
                Set _indicesCustomers = new LinkedHashSet();
                TableInfo _infoCustomers = new TableInfo("customers", _columnsCustomers, _foreignKeysCustomers, _indicesCustomers);
                TableInfo _existingCustomers = TableInfo.Companion.read(connection, "customers");
                if (!_infoCustomers.equals(_existingCustomers)) {
                    return new RoomOpenDelegate.ValidationResult(false, "customers(com.example.data.model.CustomerEntity).\n Expected:\n" + _infoCustomers + "\n Found:\n" + _existingCustomers);
                }
                Map _columnsExperts = new LinkedHashMap();
                _columnsExperts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, (String) null, 1));
                _columnsExperts.put("name", new TableInfo.Column("name", "TEXT", true, 0, (String) null, 1));
                _columnsExperts.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, (String) null, 1));
                _columnsExperts.put("category", new TableInfo.Column("category", "TEXT", true, 0, (String) null, 1));
                _columnsExperts.put("address", new TableInfo.Column("address", "TEXT", true, 0, (String) null, 1));
                _columnsExperts.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, (String) null, 1));
                _columnsExperts.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, (String) null, 1));
                _columnsExperts.put("isAvailable", new TableInfo.Column("isAvailable", "INTEGER", true, 0, (String) null, 1));
                _columnsExperts.put("rating", new TableInfo.Column("rating", "REAL", true, 0, (String) null, 1));
                _columnsExperts.put("ratingSum", new TableInfo.Column("ratingSum", "REAL", true, 0, (String) null, 1));
                _columnsExperts.put("totalRatingsCount", new TableInfo.Column("totalRatingsCount", "INTEGER", true, 0, (String) null, 1));
                _columnsExperts.put("completedJobsCount", new TableInfo.Column("completedJobsCount", "INTEGER", true, 0, (String) null, 1));
                _columnsExperts.put("cancelledJobsCount", new TableInfo.Column("cancelledJobsCount", "INTEGER", true, 0, (String) null, 1));
                _columnsExperts.put("isWelcomeMessageSent", new TableInfo.Column("isWelcomeMessageSent", "INTEGER", true, 0, (String) null, 1));
                _columnsExperts.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, (String) null, 1));
                _columnsExperts.put("deletedAt", new TableInfo.Column("deletedAt", "INTEGER", false, 0, (String) null, 1));
                _columnsExperts.put("added_by_user_id", new TableInfo.Column("added_by_user_id", "TEXT", false, 0, (String) null, 1));
                _columnsExperts.put("added_by_user_name", new TableInfo.Column("added_by_user_name", "TEXT", false, 0, (String) null, 1));
                _columnsExperts.put("added_by_designation", new TableInfo.Column("added_by_designation", "TEXT", false, 0, (String) null, 1));
                _columnsExperts.put("profilePicUrl", new TableInfo.Column("profilePicUrl", "TEXT", false, 0, (String) null, 1));
                _columnsExperts.put("created_at_timestamp", new TableInfo.Column("created_at_timestamp", "INTEGER", true, 0, (String) null, 1));
                _columnsExperts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, (String) null, 1));
                _columnsExperts.put("last_updated", new TableInfo.Column("last_updated", "INTEGER", true, 0, (String) null, 1));
                _columnsExperts.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, (String) null, 1));
                Set _foreignKeysExperts = new LinkedHashSet();
                Set _indicesExperts = new LinkedHashSet();
                TableInfo _infoExperts = new TableInfo(FirestoreSyncManager.EXPERTS_COLLECTION, _columnsExperts, _foreignKeysExperts, _indicesExperts);
                TableInfo _existingExperts = TableInfo.Companion.read(connection, FirestoreSyncManager.EXPERTS_COLLECTION);
                if (!_infoExperts.equals(_existingExperts)) {
                    return new RoomOpenDelegate.ValidationResult(false, "experts(com.example.data.model.ExpertEntity).\n Expected:\n" + _infoExperts + "\n Found:\n" + _existingExperts);
                }
                Map _columnsCustomerJobs = new LinkedHashMap();
                _columnsCustomerJobs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, (String) null, 1));
                _columnsCustomerJobs.put("customerName", new TableInfo.Column("customerName", "TEXT", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("customerPhone", new TableInfo.Column("customerPhone", "TEXT", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("serviceType", new TableInfo.Column("serviceType", "TEXT", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("issueDescription", new TableInfo.Column("issueDescription", "TEXT", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("address", new TableInfo.Column("address", "TEXT", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("status", new TableInfo.Column("status", "TEXT", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("assignedExpertId", new TableInfo.Column("assignedExpertId", "INTEGER", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("assignedExpertName", new TableInfo.Column("assignedExpertName", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("assignedExpertPhone", new TableInfo.Column("assignedExpertPhone", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("distanceKmAtDispatch", new TableInfo.Column("distanceKmAtDispatch", "REAL", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("ratingGiven", new TableInfo.Column("ratingGiven", "REAL", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("reviewFeedback", new TableInfo.Column("reviewFeedback", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("isExpertNotified", new TableInfo.Column("isExpertNotified", "INTEGER", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("isCustomerNotifiedOnAssign", new TableInfo.Column("isCustomerNotifiedOnAssign", "INTEGER", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("isCustomerNotifiedOnCompletion", new TableInfo.Column("isCustomerNotifiedOnCompletion", "INTEGER", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("assignMessageLaterDismissedAt", new TableInfo.Column("assignMessageLaterDismissedAt", "INTEGER", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("deletedAt", new TableInfo.Column("deletedAt", "INTEGER", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("last_updated", new TableInfo.Column("last_updated", "INTEGER", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, (String) null, 1));
                _columnsCustomerJobs.put("created_by_user_id", new TableInfo.Column("created_by_user_id", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("created_by_user_name", new TableInfo.Column("created_by_user_name", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("created_by_designation", new TableInfo.Column("created_by_designation", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("managed_by_user_id", new TableInfo.Column("managed_by_user_id", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("managed_by_user_name", new TableInfo.Column("managed_by_user_name", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("managed_by_designation", new TableInfo.Column("managed_by_designation", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("assigned_technician_id", new TableInfo.Column("assigned_technician_id", "INTEGER", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("assigned_technician_name", new TableInfo.Column("assigned_technician_name", "TEXT", false, 0, (String) null, 1));
                _columnsCustomerJobs.put("assigned_at_timestamp", new TableInfo.Column("assigned_at_timestamp", "INTEGER", false, 0, (String) null, 1));
                Set _foreignKeysCustomerJobs = new LinkedHashSet();
                Set _indicesCustomerJobs = new LinkedHashSet();
                TableInfo _infoCustomerJobs = new TableInfo(FirestoreSyncManager.JOBS_COLLECTION, _columnsCustomerJobs, _foreignKeysCustomerJobs, _indicesCustomerJobs);
                TableInfo _existingCustomerJobs = TableInfo.Companion.read(connection, FirestoreSyncManager.JOBS_COLLECTION);
                if (!_infoCustomerJobs.equals(_existingCustomerJobs)) {
                    return new RoomOpenDelegate.ValidationResult(false, "customer_jobs(com.example.data.model.CustomerJobEntity).\n Expected:\n" + _infoCustomerJobs + "\n Found:\n" + _existingCustomerJobs);
                }
                Map _columnsExpertCategories = new LinkedHashMap();
                _columnsExpertCategories.put("id", new TableInfo.Column("id", "INTEGER", true, 1, (String) null, 1));
                _columnsExpertCategories.put("name", new TableInfo.Column("name", "TEXT", true, 0, (String) null, 1));
                _columnsExpertCategories.put("isDefault", new TableInfo.Column("isDefault", "INTEGER", true, 0, (String) null, 1));
                _columnsExpertCategories.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, (String) null, 1));
                _columnsExpertCategories.put("last_updated", new TableInfo.Column("last_updated", "INTEGER", true, 0, (String) null, 1));
                _columnsExpertCategories.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, (String) null, 1));
                Set _foreignKeysExpertCategories = new LinkedHashSet();
                Set _indicesExpertCategories = new LinkedHashSet();
                TableInfo _infoExpertCategories = new TableInfo(FirestoreSyncManager.CATEGORIES_COLLECTION, _columnsExpertCategories, _foreignKeysExpertCategories, _indicesExpertCategories);
                TableInfo _existingExpertCategories = TableInfo.Companion.read(connection, FirestoreSyncManager.CATEGORIES_COLLECTION);
                if (!_infoExpertCategories.equals(_existingExpertCategories)) {
                    return new RoomOpenDelegate.ValidationResult(false, "expert_categories(com.example.data.model.ExpertCategoryEntity).\n Expected:\n" + _infoExpertCategories + "\n Found:\n" + _existingExpertCategories);
                }
                return new RoomOpenDelegate.ValidationResult(true, (String) null);
            }
        };
        return _openDelegate;
    }

    protected InvalidationTracker createInvalidationTracker() {
        Map _shadowTablesMap = new LinkedHashMap();
        Map _viewTables = new LinkedHashMap();
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, new String[]{"technicians", "customers", FirestoreSyncManager.EXPERTS_COLLECTION, FirestoreSyncManager.JOBS_COLLECTION, FirestoreSyncManager.CATEGORIES_COLLECTION});
    }

    public void clearAllTables() {
        super.performClear(false, new String[]{"technicians", "customers", FirestoreSyncManager.EXPERTS_COLLECTION, FirestoreSyncManager.JOBS_COLLECTION, FirestoreSyncManager.CATEGORIES_COLLECTION});
    }

    protected Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        Map _typeConvertersMap = new LinkedHashMap();
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(TechnicianDao.class), TechnicianDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(CustomerDao.class), CustomerDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(ExpertDao.class), ExpertDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(CustomerJobDao.class), CustomerJobDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(ExpertCategoryDao.class), ExpertCategoryDao_Impl.INSTANCE.getRequiredConverters());
        return _typeConvertersMap;
    }

    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        Set _autoMigrationSpecsSet = new LinkedHashSet();
        return _autoMigrationSpecsSet;
    }

    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        List _autoMigrations = new ArrayList();
        return _autoMigrations;
    }

    @Override // com.example.data.local.AppDatabase
    public TechnicianDao technicianDao() {
        return (TechnicianDao) this._technicianDao.getValue();
    }

    @Override // com.example.data.local.AppDatabase
    public CustomerDao customerDao() {
        return (CustomerDao) this._customerDao.getValue();
    }

    @Override // com.example.data.local.AppDatabase
    public ExpertDao expertDao() {
        return (ExpertDao) this._expertDao.getValue();
    }

    @Override // com.example.data.local.AppDatabase
    public CustomerJobDao customerJobDao() {
        return (CustomerJobDao) this._customerJobDao.getValue();
    }

    @Override // com.example.data.local.AppDatabase
    public ExpertCategoryDao expertCategoryDao() {
        return (ExpertCategoryDao) this._expertCategoryDao.getValue();
    }
}
