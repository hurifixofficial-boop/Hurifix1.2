package com.example.ui.components;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;
import androidx.activity.compose.BackHandlerKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material.icons.filled.PhotoCameraKt;
import androidx.compose.material.icons.filled.VisibilityKt;
import androidx.compose.material.icons.filled.VisibilityOffKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.material3.TopAppBarScrollBehavior;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.example.data.model.CustomerJobEntity;
import com.example.data.model.ExpertEntity;
import com.example.data.model.RankedExpert;
import com.example.util.LocationHelper;
import com.example.util.WhatsAppHelper;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: CustomerWhatsAppDialogs.kt */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\u001aI\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\u000b\u001a1\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\r\u001a)\u0010\u000e\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\u0011\u001a)\u0010\u0012\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\u0011\u001aI\u0010\u0014\u001a\u00020\u00012\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0019H\u0007¢\u0006\u0002\u0010\u001a\u001a9\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\u001e\u001a7\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010#2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010$\u001aC\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00052\b\b\u0002\u0010(\u001a\u00020\u00052\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010*\u001a/\u0010+\u001a\u00020\u00012\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010#2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010-\u001a?\u0010.\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u00101\u001aO\u00102\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u0005042\b\b\u0002\u00105\u001a\u0002062\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010#2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u00108\u001aá\u0001\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020\u00052\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010>\u001a\u00020\u00052\u0016\b\u0002\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010#2\u0010\b\u0002\u0010@\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2b\u00107\u001a^\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(C\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(E\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010A2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010G\u001aá\u0001\u0010H\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020\u00052\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010>\u001a\u00020\u00052\u0016\b\u0002\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010#2\u0010\b\u0002\u0010@\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2b\u00107\u001a^\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(C\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(E\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010A2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010G¨\u0006I²\u0006\u0012\u0010J\u001a\n K*\u0004\u0018\u00010\u00050\u0005X\u008a\u008e\u0002²\u0006\u0012\u0010L\u001a\n K*\u0004\u0018\u00010\u00050\u0005X\u008a\u008e\u0002²\u0006\f\u0010M\u001a\u0004\u0018\u00010\u0005X\u008a\u008e\u0002²\u0006\n\u0010N\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\f\u0010O\u001a\u0004\u0018\u00010\u0005X\u008a\u008e\u0002²\u0006\n\u0010C\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010D\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010P\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010Q\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010R\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010S\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010T\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010U\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010V\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010W\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\f\u0010F\u001a\u0004\u0018\u00010\u0005X\u008a\u008e\u0002²\u0006\n\u0010X\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010Y\u001a\u000206X\u008a\u008e\u0002²\u0006\n\u0010Z\u001a\u000206X\u008a\u008e\u0002²\u0006\n\u0010[\u001a\u000206X\u008a\u008e\u0002²\u0006\n\u0010\\\u001a\u000206X\u008a\u008e\u0002²\u0006\n\u0010]\u001a\u000206X\u008a\u008e\u0002²\u0006\f\u0010^\u001a\u0004\u0018\u00010_X\u008a\u008e\u0002"}, d2 = {"CustomerAssignWhatsAppDialog", "", "job", "Lcom/example/data/model/CustomerJobEntity;", "expertName", "", "expertPhone", "estimatedTimeText", "onSendWhatsApp", "Lkotlin/Function0;", "onLater", "(Lcom/example/data/model/CustomerJobEntity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "CustomerCompletionWhatsAppDialog", "(Lcom/example/data/model/CustomerJobEntity;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "LocationPermissionDeniedDialog", "onDismiss", "onOpenSettings", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "GpsDisabledDialog", "onOpenLocationSettings", "CustomDateRangePickerDialog", "currentStartDate", "", "currentEndDate", "onApplyRange", "Lkotlin/Function2;", "(Ljava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "AssignExpertWhatsAppConfirmDialog", "ranked", "Lcom/example/data/model/RankedExpert;", "(Lcom/example/data/model/CustomerJobEntity;Lcom/example/data/model/RankedExpert;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "SendWelcomeExpertMessageDialog", "expert", "Lcom/example/data/model/ExpertEntity;", "onSend", "Lkotlin/Function1;", "(Lcom/example/data/model/ExpertEntity;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "UniversalDeleteConfirmationDialog", "title", "message", "confirmButtonText", "onConfirmDelete", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "AddNewCategoryDialog", "onAddCategory", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "OrderLongPressActionDialog", "onEditDetails", "onUnassignExpert", "(Lcom/example/data/model/CustomerJobEntity;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "EditCustomerOrderDialog", "availableCategories", "", "isAdmin", "", "onSave", "(Lcom/example/data/model/CustomerJobEntity;Ljava/util/List;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "EditUserProfileDialog", "initialName", "initialPhone", "initialRole", "initialPhotoUri", "currentPassword", "onUpdatePassword", "onDeleteAccount", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "phone", "role", "photoUri", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "EditAdminProfileDialog", "app", "startText", "kotlin.jvm.PlatformType", "endText", "errorMsg", "categoryName", "errorText", "serviceType", "address", "rawLocation", "issueDescription", "selectedStatus", "userName", "userPhone", "userRole", "newPassword", "passwordVisible", "showPasswordOtpModal", "showDeleteAccountConfirm", "isUploadingPhoto", "showImageSourcePicker", "tempCameraUri", "Landroid/net/Uri;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class CustomerWhatsAppDialogsKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddNewCategoryDialog$lambda$94(Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        AddNewCategoryDialog(function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AssignExpertWhatsAppConfirmDialog$lambda$56(CustomerJobEntity customerJobEntity, RankedExpert rankedExpert, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        AssignExpertWhatsAppConfirmDialog(customerJobEntity, rankedExpert, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomDateRangePickerDialog$lambda$46(Long l, Long l2, Function0 function0, Function2 function2, int i, Composer composer, int i2) {
        CustomDateRangePickerDialog(l, l2, function0, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerAssignWhatsAppDialog$lambda$9(CustomerJobEntity customerJobEntity, String str, String str2, String str3, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        CustomerAssignWhatsAppDialog(customerJobEntity, str, str2, str3, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerCompletionWhatsAppDialog$lambda$16(CustomerJobEntity customerJobEntity, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        CustomerCompletionWhatsAppDialog(customerJobEntity, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditAdminProfileDialog$lambda$297(String str, String str2, String str3, String str4, boolean z, String str5, Function1 function1, Function0 function0, Function4 function4, Function0 function02, int i, int i2, Composer composer, int i3) {
        EditAdminProfileDialog(str, str2, str3, str4, z, str5, function1, function0, function4, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$175(CustomerJobEntity customerJobEntity, List list, boolean z, Function1 function1, Function0 function0, int i, int i2, Composer composer, int i3) {
        EditCustomerOrderDialog(customerJobEntity, list, z, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$296(String str, String str2, String str3, String str4, boolean z, String str5, Function1 function1, Function0 function0, Function4 function4, Function0 function02, int i, int i2, Composer composer, int i3) {
        EditUserProfileDialog(str, str2, str3, str4, z, str5, function1, function0, function4, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit GpsDisabledDialog$lambda$22(Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        GpsDisabledDialog(function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit LocationPermissionDeniedDialog$lambda$19(Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        LocationPermissionDeniedDialog(function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderLongPressActionDialog$lambda$108(CustomerJobEntity customerJobEntity, Function0 function0, Function0 function02, Function0 function03, int i, Composer composer, int i2) {
        OrderLongPressActionDialog(customerJobEntity, function0, function02, function03, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit SendWelcomeExpertMessageDialog$lambda$68(ExpertEntity expertEntity, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        SendWelcomeExpertMessageDialog(expertEntity, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit UniversalDeleteConfirmationDialog$lambda$78(String str, String str2, String str3, Function0 function0, Function0 function02, int i, int i2, Composer composer, int i3) {
        UniversalDeleteConfirmationDialog(str, str2, str3, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void CustomerAssignWhatsAppDialog(final CustomerJobEntity job, final String expertName, final String expertPhone, final String estimatedTimeText, final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed) {
        boolean z;
        Object createCustomerAssignmentNotificationMessage;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(expertName, "expertName");
        Intrinsics.checkNotNullParameter(expertPhone, "expertPhone");
        Intrinsics.checkNotNullParameter(estimatedTimeText, "estimatedTimeText");
        Intrinsics.checkNotNullParameter(function0, "onSendWhatsApp");
        Intrinsics.checkNotNullParameter(function02, "onLater");
        Composer $composer3 = $composer.startRestartGroup(1306124020);
        ComposerKt.sourceInformation($composer3, "C(CustomerAssignWhatsAppDialog)P(3,1,2!1,5)125@5525L357,244@10591L468,255@11085L183,170@7263L3302,135@5888L5386:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(expertName) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(expertPhone) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(estimatedTimeText) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1306124020, $dirty2, -1, "com.example.ui.components.CustomerAssignWhatsAppDialog (CustomerWhatsAppDialogs.kt:124)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1054740697, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean z2 = (($dirty2 & 14) == 4) | (($dirty2 & 112) == 32) | (($dirty2 & 896) == 256) | (($dirty2 & 7168) == 2048);
            Object rememberedValue = $composer3.rememberedValue();
            if (z2 || rememberedValue == Composer.Companion.getEmpty()) {
                z = true;
                createCustomerAssignmentNotificationMessage = WhatsAppHelper.INSTANCE.createCustomerAssignmentNotificationMessage(job.getCustomerName(), expertName, expertPhone, job.getServiceType(), estimatedTimeText);
                $composer3.updateRememberedValue(createCustomerAssignmentNotificationMessage);
            } else {
                z = true;
                createCustomerAssignmentNotificationMessage = rememberedValue;
            }
            final String messageText = (String) createCustomerAssignmentNotificationMessage;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function02, ComposableLambdaKt.rememberComposableLambda(-1978016452, z, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda43
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.CustomerAssignWhatsAppDialog$lambda$1(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-378867394, z, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda44
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.CustomerAssignWhatsAppDialog$lambda$2(function02, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1220281664$app(), ComposableLambdaKt.rememberComposableLambda(2019856193, z, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda45
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.CustomerAssignWhatsAppDialog$lambda$8(CustomerJobEntity.this, expertName, expertPhone, estimatedTimeText, messageText, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty2 >> 15) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda46
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.CustomerAssignWhatsAppDialog$lambda$9(CustomerJobEntity.this, expertName, expertPhone, estimatedTimeText, function0, function02, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x02a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CustomerAssignWhatsAppDialog$lambda$8(final com.example.data.model.CustomerJobEntity r53, final java.lang.String r54, final java.lang.String r55, final java.lang.String r56, final java.lang.String r57, androidx.compose.runtime.Composer r58, int r59) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.CustomerAssignWhatsAppDialog$lambda$8(com.example.data.model.CustomerJobEntity, java.lang.String, java.lang.String, java.lang.String, java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerAssignWhatsAppDialog$lambda$8$lambda$7$lambda$5(CustomerJobEntity $job, String $expertName, String $expertPhone, final String $estimatedTimeText, ColumnScope $this$Card, Composer $composer, int $changed) {
        Function0 function0;
        Intrinsics.checkNotNullParameter($this$Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C181@7767L1703:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(282979625, $changed, -1, "com.example.ui.components.CustomerAssignWhatsAppDialog.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:181)");
            }
            Modifier modifier = PaddingKt.padding-3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(12));
            Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(6));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(vertical, Alignment.Companion.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composer = Updater.constructor-impl($composer);
            Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1596912537, "C189@8179L10,187@8029L258,194@8441L10,196@8571L11,192@8312L304,200@8767L10,198@8641L172,205@8989L459,202@8838L610:CustomerWhatsAppDialogs.kt#qonjpd");
            TextKt.Text--4IGK_g("👤 Customer: " + $job.getCustomerName() + " (" + $job.getCustomerPhone() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 196608, 0, 65502);
            TextKt.Text--4IGK_g("👨\u200d🔧 Assigned Expert: " + $expertName, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary-0d7_KjU(), 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 196608, 0, 65498);
            TextKt.Text--4IGK_g("📞 Expert Contact: " + $expertPhone, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 65534);
            SurfaceKt.Surface-T9BRK9s((Modifier) null, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(6)), ColorKt.Color(4294898631L), 0L, 0.0f, 0.0f, (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(1713012026, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda81
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.CustomerAssignWhatsAppDialog$lambda$8$lambda$7$lambda$5$lambda$4$lambda$3($estimatedTimeText, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12583296, 121);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerAssignWhatsAppDialog$lambda$8$lambda$7$lambda$5$lambda$4$lambda$3(String $estimatedTimeText, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C206@9019L403:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1713012026, $changed, -1, "com.example.ui.components.CustomerAssignWhatsAppDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:206)");
            }
            FontWeight bold = FontWeight.Companion.getBold();
            TextKt.Text--4IGK_g("⏱ Estimated Arrival (+30 min buffer): " + $estimatedTimeText, PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(6), Dp.constructor-impl(3)), ColorKt.Color(4290007817L), TextUnitKt.getSp(11.5d), (FontStyle) null, bold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 200112, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerAssignWhatsAppDialog$lambda$8$lambda$7$lambda$6(String $messageText, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C232@10113L10,230@10017L198:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(994921490, $changed, -1, "com.example.ui.components.CustomerAssignWhatsAppDialog.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:230)");
            }
            TextKt.Text--4IGK_g($messageText, PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 48, 0, 65532);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerAssignWhatsAppDialog$lambda$1(Function0 $onSendWhatsApp, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C247@10695L48,245@10605L444:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1978016452, $changed, -1, "com.example.ui.components.CustomerAssignWhatsAppDialog.<anonymous> (CustomerWhatsAppDialogs.kt:245)");
            }
            ButtonKt.Button($onSendWhatsApp, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(ColorKt.Color(4280669030L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14), (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1747650860$app(), $composer, 805306368, 486);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerAssignWhatsAppDialog$lambda$2(Function0 $onLater, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C256@11099L159:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-378867394, $changed, -1, "com.example.ui.components.CustomerAssignWhatsAppDialog.<anonymous> (CustomerWhatsAppDialogs.kt:256)");
            }
            ButtonKt.OutlinedButton($onLater, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m41getLambda$1121880016$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void CustomerCompletionWhatsAppDialog(final CustomerJobEntity job, final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed) {
        Object createCompletionCustomerMessage;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(function0, "onSendWhatsApp");
        Intrinsics.checkNotNullParameter(function02, "onLater");
        Composer $composer3 = $composer.startRestartGroup(96703366);
        ComposerKt.sourceInformation($composer3, "C(CustomerCompletionWhatsAppDialog)P(!1,2)276@11590L94,340@14074L468,351@14568L183,310@12840L1208,280@11690L3067:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 256 : 128;
        }
        if (($dirty & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(96703366, $dirty, -1, "com.example.ui.components.CustomerCompletionWhatsAppDialog (CustomerWhatsAppDialogs.kt:275)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1557340572, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean z = ($dirty & 14) == 4;
            Object rememberedValue = $composer3.rememberedValue();
            if (z || rememberedValue == Composer.Companion.getEmpty()) {
                createCompletionCustomerMessage = WhatsAppHelper.INSTANCE.createCompletionCustomerMessage(job.getCustomerName());
                $composer3.updateRememberedValue(createCompletionCustomerMessage);
            } else {
                createCompletionCustomerMessage = rememberedValue;
            }
            final String messageText = (String) createCompletionCustomerMessage;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function02, ComposableLambdaKt.rememberComposableLambda(736042046, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda25
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.CustomerCompletionWhatsAppDialog$lambda$11(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-49955844, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda26
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.CustomerCompletionWhatsAppDialog$lambda$12(function02, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m73getLambda$835953734$app(), ComposableLambdaKt.rememberComposableLambda(-1228952679, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda27
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.CustomerCompletionWhatsAppDialog$lambda$15(messageText, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty >> 6) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda28
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.CustomerCompletionWhatsAppDialog$lambda$16(CustomerJobEntity.this, function0, function02, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x022c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CustomerCompletionWhatsAppDialog$lambda$15(final java.lang.String r51, androidx.compose.runtime.Composer r52, int r53) {
        /*
            Method dump skipped, instructions count: 562
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.CustomerCompletionWhatsAppDialog$lambda$15(java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerCompletionWhatsAppDialog$lambda$15$lambda$14$lambda$13(String $messageText, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C328@13588L10,326@13492L198:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1232761176, $changed, -1, "com.example.ui.components.CustomerCompletionWhatsAppDialog.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:326)");
            }
            TextKt.Text--4IGK_g($messageText, PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 48, 0, 65532);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerCompletionWhatsAppDialog$lambda$11(Function0 $onSendWhatsApp, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C343@14178L48,341@14088L444:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(736042046, $changed, -1, "com.example.ui.components.CustomerCompletionWhatsAppDialog.<anonymous> (CustomerWhatsAppDialogs.kt:341)");
            }
            ButtonKt.Button($onSendWhatsApp, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(ColorKt.Color(4280669030L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14), (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m57getLambda$2121346482$app(), $composer, 805306368, 486);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerCompletionWhatsAppDialog$lambda$12(Function0 $onLater, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C352@14582L159:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-49955844, $changed, -1, "com.example.ui.components.CustomerCompletionWhatsAppDialog.<anonymous> (CustomerWhatsAppDialogs.kt:352)");
            }
            ButtonKt.OutlinedButton($onLater, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m53getLambda$1858802998$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void LocationPermissionDeniedDialog(final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed) {
        Composer $composer2;
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Intrinsics.checkNotNullParameter(function02, "onOpenSettings");
        Composer $composer3 = $composer.startRestartGroup(-602380567);
        ComposerKt.sourceInformation($composer3, "C(LocationPermissionDeniedDialog)384@15691L344,394@16061L102,370@14957L1212:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-602380567, $dirty, -1, "com.example.ui.components.LocationPermissionDeniedDialog (CustomerWhatsAppDialogs.kt:369)");
            }
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function0, ComposableLambdaKt.rememberComposableLambda(-1584039007, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda124
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.LocationPermissionDeniedDialog$lambda$17(function02, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-1173428641, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.LocationPermissionDeniedDialog$lambda$18(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m72getLambda$762818275$app(), ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1589970556$app(), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, ($dirty & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda2
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.LocationPermissionDeniedDialog$lambda$19(function0, function02, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit LocationPermissionDeniedDialog$lambda$17(Function0 $onOpenSettings, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C385@15705L320:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1584039007, $changed, -1, "com.example.ui.components.LocationPermissionDeniedDialog.<anonymous> (CustomerWhatsAppDialogs.kt:385)");
            }
            ButtonKt.Button($onOpenSettings, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m55getLambda$1894721103$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit LocationPermissionDeniedDialog$lambda$18(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C395@16075L78:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1173428641, $changed, -1, "com.example.ui.components.LocationPermissionDeniedDialog.<anonymous> (CustomerWhatsAppDialogs.kt:395)");
            }
            ButtonKt.TextButton($onDismiss, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m56getLambda$2077162814$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void GpsDisabledDialog(final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed) {
        Composer $composer2;
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Intrinsics.checkNotNullParameter(function02, "onOpenLocationSettings");
        Composer $composer3 = $composer.startRestartGroup(-908950082);
        ComposerKt.sourceInformation($composer3, "C(GpsDisabledDialog)424@17106L443,435@17575L102,410@16372L1311:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-908950082, $dirty, -1, "com.example.ui.components.GpsDisabledDialog (CustomerWhatsAppDialogs.kt:409)");
            }
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function0, ComposableLambdaKt.rememberComposableLambda(1410999814, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda67
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.GpsDisabledDialog$lambda$20(function02, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(976494216, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda68
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.GpsDisabledDialog$lambda$21(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$541988618$app(), ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m51getLambda$1822747829$app(), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, ($dirty & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda69
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.GpsDisabledDialog$lambda$22(function0, function02, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit GpsDisabledDialog$lambda$20(Function0 $onOpenLocationSettings, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C428@17268L48,425@17120L419:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1410999814, $changed, -1, "com.example.ui.components.GpsDisabledDialog.<anonymous> (CustomerWhatsAppDialogs.kt:425)");
            }
            ButtonKt.Button($onOpenLocationSettings, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(ColorKt.Color(4292441862L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14), (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m63getLambda$517001738$app(), $composer, 805306368, 486);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit GpsDisabledDialog$lambda$21(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C436@17589L78:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(976494216, $changed, -1, "com.example.ui.components.GpsDisabledDialog.<anonymous> (CustomerWhatsAppDialogs.kt:436)");
            }
            ButtonKt.TextButton($onDismiss, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$957128005$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0116, code lost:
    
        if (r0 == null) goto L56;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CustomDateRangePickerDialog(final java.lang.Long r29, final java.lang.Long r30, final kotlin.jvm.functions.Function0<kotlin.Unit> r31, final kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, final int r34) {
        /*
            Method dump skipped, instructions count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.CustomDateRangePickerDialog(java.lang.Long, java.lang.Long, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    private static final String CustomDateRangePickerDialog$lambda$25(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String CustomDateRangePickerDialog$lambda$29(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String CustomDateRangePickerDialog$lambda$32(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit CustomDateRangePickerDialog$lambda$45(final androidx.compose.runtime.MutableState r58, final androidx.compose.runtime.MutableState r59, final androidx.compose.runtime.MutableState r60, androidx.compose.runtime.Composer r61, int r62) {
        /*
            Method dump skipped, instructions count: 821
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.CustomDateRangePickerDialog$lambda$45(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomDateRangePickerDialog$lambda$45$lambda$44$lambda$40$lambda$39(MutableState $startText$delegate, MutableState $errorMsg$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $startText$delegate.setValue(it);
        $errorMsg$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomDateRangePickerDialog$lambda$45$lambda$44$lambda$42$lambda$41(MutableState $endText$delegate, MutableState $errorMsg$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $endText$delegate.setValue(it);
        $errorMsg$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomDateRangePickerDialog$lambda$37(final SimpleDateFormat $dateFormat, final Function2 $onApplyRange, final MutableState $startText$delegate, final MutableState $endText$delegate, final MutableState $errorMsg$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C506@20180L1020,505@20146L1171:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-787599394, $changed, -1, "com.example.ui.components.CustomDateRangePickerDialog.<anonymous> (CustomerWhatsAppDialogs.kt:505)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -968770982, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($dateFormat) | $composer.changed($onApplyRange);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda52
                    public final Object invoke() {
                        return CustomerWhatsAppDialogsKt.CustomDateRangePickerDialog$lambda$37$lambda$36$lambda$35($dateFormat, $onApplyRange, $startText$delegate, $endText$delegate, $errorMsg$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m62getLambda$4202546$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomDateRangePickerDialog$lambda$37$lambda$36$lambda$35(SimpleDateFormat $dateFormat, Function2 $onApplyRange, MutableState $startText$delegate, MutableState $endText$delegate, MutableState $errorMsg$delegate) {
        Date parsedStart;
        Date parsedEnd;
        try {
            String CustomDateRangePickerDialog$lambda$25 = CustomDateRangePickerDialog$lambda$25($startText$delegate);
            Intrinsics.checkNotNullExpressionValue(CustomDateRangePickerDialog$lambda$25, "CustomDateRangePickerDialog$lambda$25(...)");
            parsedStart = $dateFormat.parse(StringsKt.trim(CustomDateRangePickerDialog$lambda$25).toString());
            String CustomDateRangePickerDialog$lambda$29 = CustomDateRangePickerDialog$lambda$29($endText$delegate);
            Intrinsics.checkNotNullExpressionValue(CustomDateRangePickerDialog$lambda$29, "CustomDateRangePickerDialog$lambda$29(...)");
            parsedEnd = $dateFormat.parse(StringsKt.trim(CustomDateRangePickerDialog$lambda$29).toString());
        } catch (Exception e) {
            $errorMsg$delegate.setValue("Invalid date. Please verify DD/MM/YYYY format.");
        }
        if (parsedStart != null && parsedEnd != null) {
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(parsedEnd);
            endCal.set(11, 23);
            endCal.set(12, 59);
            endCal.set(13, 59);
            $onApplyRange.invoke(Long.valueOf(parsedStart.getTime()), Long.valueOf(endCal.getTimeInMillis()));
            return Unit.INSTANCE;
        }
        $errorMsg$delegate.setValue("Please enter valid date format (DD/MM/YYYY)");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomDateRangePickerDialog$lambda$38(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C532@21367L78:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1143526240, $changed, -1, "com.example.ui.components.CustomDateRangePickerDialog.<anonymous> (CustomerWhatsAppDialogs.kt:532)");
            }
            ButtonKt.TextButton($onDismiss, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m64getLambda$54292067$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void AssignExpertWhatsAppConfirmDialog(final CustomerJobEntity job, final RankedExpert ranked, final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed) {
        Composer $composer2;
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(ranked, "ranked");
        Intrinsics.checkNotNullParameter(function0, "onSendWhatsApp");
        Intrinsics.checkNotNullParameter(function02, "onLater");
        Composer $composer3 = $composer.startRestartGroup(-1474341014);
        ComposerKt.sourceInformation($composer3, "C(AssignExpertWhatsAppConfirmDialog)P(!1,3,2)637@25588L472,648@26086L183,554@21906L1300,587@23223L2339,552@21841L4434:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(ranked) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1474341014, $dirty, -1, "com.example.ui.components.AssignExpertWhatsAppConfirmDialog (CustomerWhatsAppDialogs.kt:549)");
            }
            final ExpertEntity expert = ranked.getExpert();
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function02, ComposableLambdaKt.rememberComposableLambda(-1457439198, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda18
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.AssignExpertWhatsAppConfirmDialog$lambda$47(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(2006874464, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda19
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.AssignExpertWhatsAppConfirmDialog$lambda$48(function02, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableLambdaKt.rememberComposableLambda(1176220830, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda20
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.AssignExpertWhatsAppConfirmDialog$lambda$51(CustomerJobEntity.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(760894013, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda21
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.AssignExpertWhatsAppConfirmDialog$lambda$55(ExpertEntity.this, job, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty >> 9) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda23
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.AssignExpertWhatsAppConfirmDialog$lambda$56(CustomerJobEntity.this, ranked, function0, function02, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x020b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit AssignExpertWhatsAppConfirmDialog$lambda$51(com.example.data.model.CustomerJobEntity r73, androidx.compose.runtime.Composer r74, int r75) {
        /*
            Method dump skipped, instructions count: 859
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.AssignExpertWhatsAppConfirmDialog$lambda$51(com.example.data.model.CustomerJobEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x025b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit AssignExpertWhatsAppConfirmDialog$lambda$55(final com.example.data.model.ExpertEntity r52, final com.example.data.model.CustomerJobEntity r53, androidx.compose.runtime.Composer r54, int r55) {
        /*
            Method dump skipped, instructions count: 609
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.AssignExpertWhatsAppConfirmDialog$lambda$55(com.example.data.model.ExpertEntity, com.example.data.model.CustomerJobEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AssignExpertWhatsAppConfirmDialog$lambda$55$lambda$54$lambda$53(ExpertEntity $expert, CustomerJobEntity $job, ColumnScope $this$Card, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C602@23909L1290:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(394788181, $changed, -1, "com.example.ui.components.AssignExpertWhatsAppConfirmDialog.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:602)");
            }
            Modifier modifier = PaddingKt.padding-3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(10));
            Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(4));
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)85@4251L61,86@4317L133:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(vertical, Alignment.Companion.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composer = Updater.constructor-impl($composer);
            Updater.set-impl(composer, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384862393, "C87@4365L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -2117837674, "C610@24312L10,608@24171L245,615@24591L10,613@24441L196,619@24787L10,620@24859L11,617@24662L242,624@25050L10,625@25123L11,622@24929L248:CustomerWhatsAppDialogs.kt#qonjpd");
            TextKt.Text--4IGK_g("👨\u200d🔧 Expert: " + $expert.getName() + " (" + $expert.getPhone() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 196608, 0, 65502);
            TextKt.Text--4IGK_g("👤 Customer: " + $job.getCustomerName() + " (" + $job.getCustomerPhone() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 65534);
            TextKt.Text--4IGK_g("🛠 Service: " + $job.getServiceType(), (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 65530);
            TextKt.Text--4IGK_g("📍 Address: " + $job.getAddress(), (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 0, 0, 65530);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AssignExpertWhatsAppConfirmDialog$lambda$47(Function0 $onSendWhatsApp, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C640@25692L48,638@25602L448:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1457439198, $changed, -1, "com.example.ui.components.AssignExpertWhatsAppConfirmDialog.<anonymous> (CustomerWhatsAppDialogs.kt:638)");
            }
            ButtonKt.Button($onSendWhatsApp, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(ColorKt.Color(4280669030L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14), (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$2077300786$app(), $composer, 805306368, 486);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AssignExpertWhatsAppConfirmDialog$lambda$48(Function0 $onLater, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C649@26100L159:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2006874464, $changed, -1, "com.example.ui.components.AssignExpertWhatsAppConfirmDialog.<anonymous> (CustomerWhatsAppDialogs.kt:649)");
            }
            ButtonKt.OutlinedButton($onLater, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1228187566$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void SendWelcomeExpertMessageDialog(final ExpertEntity expert, final Function1<? super String, Unit> function1, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Object createNewExpertWelcomeMessage;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(expert, "expert");
        Intrinsics.checkNotNullParameter(function1, "onSend");
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(1058670021);
        ComposerKt.sourceInformation($composer3, "C(SendWelcomeExpertMessageDialog)P(!1,2)668@26526L95,725@28676L476,736@29178L101,674@26694L1060,702@27771L879,672@26627L2658:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(expert) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($dirty & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1058670021, $dirty, -1, "com.example.ui.components.SendWelcomeExpertMessageDialog (CustomerWhatsAppDialogs.kt:667)");
            }
            String name = expert.getName();
            ComposerKt.sourceInformationMarkerStart($composer3, -525353148, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed = $composer3.changed(name);
            Object rememberedValue = $composer3.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                createNewExpertWelcomeMessage = WhatsAppHelper.INSTANCE.createNewExpertWelcomeMessage(expert.getName());
                $composer3.updateRememberedValue(createNewExpertWelcomeMessage);
            } else {
                createNewExpertWelcomeMessage = rememberedValue;
            }
            final String messageText = (String) createNewExpertWelcomeMessage;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function0, ComposableLambdaKt.rememberComposableLambda(-1209639923, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda0
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.SendWelcomeExpertMessageDialog$lambda$60(function1, messageText, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(1680135695, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda37
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.SendWelcomeExpertMessageDialog$lambda$61(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableLambdaKt.rememberComposableLambda(274944017, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda48
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.SendWelcomeExpertMessageDialog$lambda$64(ExpertEntity.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-427651822, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda59
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.SendWelcomeExpertMessageDialog$lambda$67(ExpertEntity.this, messageText, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty >> 6) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda70
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.SendWelcomeExpertMessageDialog$lambda$68(ExpertEntity.this, function1, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x020b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit SendWelcomeExpertMessageDialog$lambda$64(com.example.data.model.ExpertEntity r72, androidx.compose.runtime.Composer r73, int r74) {
        /*
            Method dump skipped, instructions count: 863
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.SendWelcomeExpertMessageDialog$lambda$64(com.example.data.model.ExpertEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0222  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit SendWelcomeExpertMessageDialog$lambda$67(com.example.data.model.ExpertEntity r52, final java.lang.String r53, androidx.compose.runtime.Composer r54, int r55) {
        /*
            Method dump skipped, instructions count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.SendWelcomeExpertMessageDialog$lambda$67(com.example.data.model.ExpertEntity, java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit SendWelcomeExpertMessageDialog$lambda$67$lambda$66$lambda$65(String $messageText, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C719@28506L10,717@28410L198:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-51811101, $changed, -1, "com.example.ui.components.SendWelcomeExpertMessageDialog.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:717)");
            }
            TextKt.Text--4IGK_g($messageText, PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 48, 0, 65532);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit SendWelcomeExpertMessageDialog$lambda$60(final Function1 $onSend, final String $messageText, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C728@28789L48,727@28724L23,726@28690L452:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1209639923, $changed, -1, "com.example.ui.components.SendWelcomeExpertMessageDialog.<anonymous> (CustomerWhatsAppDialogs.kt:726)");
            }
            ButtonColors buttonColors = ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(ColorKt.Color(4280669030L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14);
            Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8));
            ComposerKt.sourceInformationMarkerStart($composer, 1472172772, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed = $composer.changed($onSend) | $composer.changed($messageText);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda97
                    public final Object invoke() {
                        return CustomerWhatsAppDialogsKt.SendWelcomeExpertMessageDialog$lambda$60$lambda$59$lambda$58($onSend, $messageText);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, shape, buttonColors, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1479563261$app(), $composer, 805306368, 486);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit SendWelcomeExpertMessageDialog$lambda$60$lambda$59$lambda$58(Function1 $onSend, String $messageText) {
        $onSend.invoke($messageText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit SendWelcomeExpertMessageDialog$lambda$61(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C737@29192L77:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1680135695, $changed, -1, "com.example.ui.components.SendWelcomeExpertMessageDialog.<anonymous> (CustomerWhatsAppDialogs.kt:737)");
            }
            ButtonKt.TextButton($onDismiss, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1008022156$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void UniversalDeleteConfirmationDialog(final String title, final String message, String confirmButtonText, final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed, final int i) {
        String str;
        final String confirmButtonText2;
        final String confirmButtonText3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(function0, "onConfirmDelete");
        Intrinsics.checkNotNullParameter(function02, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(-822188082);
        ComposerKt.sourceInformation($composer3, "C(UniversalDeleteConfirmationDialog)P(4,1)792@31055L401,804@31482L186,758@29739L424,771@30180L849,756@29672L2002:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(message) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            str = confirmButtonText;
        } else if (($changed & 384) == 0) {
            str = confirmButtonText;
            $dirty |= $composer3.changed(str) ? 256 : 128;
        } else {
            str = confirmButtonText;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            confirmButtonText3 = str;
        } else {
            if (i2 != 0) {
                confirmButtonText2 = "Move to Recycle Bin";
            } else {
                confirmButtonText2 = str;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-822188082, $dirty, -1, "com.example.ui.components.UniversalDeleteConfirmationDialog (CustomerWhatsAppDialogs.kt:755)");
            }
            confirmButtonText3 = confirmButtonText2;
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function02, ComposableLambdaKt.rememberComposableLambda(-164178810, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda111
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.UniversalDeleteConfirmationDialog$lambda$72(function0, function02, confirmButtonText2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-1888787132, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda112
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.UniversalDeleteConfirmationDialog$lambda$73(function02, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableLambdaKt.rememberComposableLambda(681571842, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda113
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.UniversalDeleteConfirmationDialog$lambda$75(title, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-180732319, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda115
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.UniversalDeleteConfirmationDialog$lambda$77(message, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty >> 12) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda116
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.UniversalDeleteConfirmationDialog$lambda$78(title, message, confirmButtonText3, function0, function02, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit UniversalDeleteConfirmationDialog$lambda$75(java.lang.String r49, androidx.compose.runtime.Composer r50, int r51) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.UniversalDeleteConfirmationDialog$lambda$75(java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit UniversalDeleteConfirmationDialog$lambda$77(java.lang.String r49, androidx.compose.runtime.Composer r50, int r51) {
        /*
            Method dump skipped, instructions count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.UniversalDeleteConfirmationDialog$lambda$77(java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit UniversalDeleteConfirmationDialog$lambda$72(final Function0 $onConfirmDelete, final Function0 $onDismiss, final String $confirmButtonText, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C798@31278L11,798@31234L62,794@31103L89,800@31361L85,793@31069L377:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-164178810, $changed, -1, "com.example.ui.components.UniversalDeleteConfirmationDialog.<anonymous> (CustomerWhatsAppDialogs.kt:793)");
            }
            ButtonColors buttonColors = ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14);
            Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8));
            ComposerKt.sourceInformationMarkerStart($composer, 1137955167, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed = $composer.changed($onConfirmDelete) | $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda38
                    public final Object invoke() {
                        return CustomerWhatsAppDialogsKt.UniversalDeleteConfirmationDialog$lambda$72$lambda$70$lambda$69($onConfirmDelete, $onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, shape, buttonColors, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-503512426, true, new Function3() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda39
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return CustomerWhatsAppDialogsKt.UniversalDeleteConfirmationDialog$lambda$72$lambda$71($confirmButtonText, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, 486);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit UniversalDeleteConfirmationDialog$lambda$72$lambda$70$lambda$69(Function0 $onConfirmDelete, Function0 $onDismiss) {
        $onConfirmDelete.invoke();
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit UniversalDeleteConfirmationDialog$lambda$72$lambda$71(String $confirmButtonText, RowScope $this$Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C801@31379L53:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-503512426, $changed, -1, "com.example.ui.components.UniversalDeleteConfirmationDialog.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:801)");
            }
            TextKt.Text--4IGK_g($confirmButtonText, (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196608, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit UniversalDeleteConfirmationDialog$lambda$73(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C805@31496L162:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1888787132, $changed, -1, "com.example.ui.components.UniversalDeleteConfirmationDialog.<anonymous> (CustomerWhatsAppDialogs.kt:805)");
            }
            ButtonKt.OutlinedButton($onDismiss, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$128613650$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void AddNewCategoryDialog(final Function1<? super String, Unit> function1, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Object obj;
        Object obj2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(function1, "onAddCategory");
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(738786442);
        ComposerKt.sourceInformation($composer3, "C(AddNewCategoryDialog)823@31851L31,824@31904L42,861@33321L522,877@33869L102,835@32229L1066,826@31952L2025:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(738786442, $dirty, -1, "com.example.ui.components.AddNewCategoryDialog (CustomerWhatsAppDialogs.kt:822)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1304171081, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            final MutableState categoryName$delegate = (MutableState) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1304172788, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                obj2 = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            final MutableState errorText$delegate = (MutableState) obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function0, ComposableLambdaKt.rememberComposableLambda(-247589054, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda98
                public final Object invoke(Object obj3, Object obj4) {
                    return CustomerWhatsAppDialogsKt.AddNewCategoryDialog$lambda$87(function1, function0, categoryName$delegate, errorText$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(427571072, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda99
                public final Object invoke(Object obj3, Object obj4) {
                    return CustomerWhatsAppDialogsKt.AddNewCategoryDialog$lambda$88(function0, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1102731198$app(), ComposableLambdaKt.rememberComposableLambda(-707172387, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda100
                public final Object invoke(Object obj3, Object obj4) {
                    return CustomerWhatsAppDialogsKt.AddNewCategoryDialog$lambda$93(categoryName$delegate, errorText$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty >> 3) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda101
                public final Object invoke(Object obj3, Object obj4) {
                    return CustomerWhatsAppDialogsKt.AddNewCategoryDialog$lambda$94(function1, function0, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final String AddNewCategoryDialog$lambda$80(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String AddNewCategoryDialog$lambda$83(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit AddNewCategoryDialog$lambda$93(final androidx.compose.runtime.MutableState r55, androidx.compose.runtime.MutableState r56, androidx.compose.runtime.Composer r57, int r58) {
        /*
            Method dump skipped, instructions count: 684
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.AddNewCategoryDialog$lambda$93(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddNewCategoryDialog$lambda$93$lambda$92$lambda$90$lambda$89(MutableState $categoryName$delegate, MutableState $errorText$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $categoryName$delegate.setValue(it);
        $errorText$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddNewCategoryDialog$lambda$87(final Function1 $onAddCategory, final Function0 $onDismiss, final MutableState $categoryName$delegate, final MutableState $errorText$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C863@33369L317,862@33335L498:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-247589054, $changed, -1, "com.example.ui.components.AddNewCategoryDialog.<anonymous> (CustomerWhatsAppDialogs.kt:862)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1709877761, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed = $composer.changed($onAddCategory) | $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda24
                    public final Object invoke() {
                        return CustomerWhatsAppDialogsKt.AddNewCategoryDialog$lambda$87$lambda$86$lambda$85($onAddCategory, $onDismiss, $categoryName$delegate, $errorText$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m70getLambda$744175278$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddNewCategoryDialog$lambda$87$lambda$86$lambda$85(Function1 $onAddCategory, Function0 $onDismiss, MutableState $categoryName$delegate, MutableState $errorText$delegate) {
        String trimmed = StringsKt.trim(AddNewCategoryDialog$lambda$80($categoryName$delegate)).toString();
        if (!StringsKt.isBlank(trimmed)) {
            $onAddCategory.invoke(trimmed);
            $onDismiss.invoke();
        } else {
            $errorText$delegate.setValue("Please enter category name");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddNewCategoryDialog$lambda$88(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C878@33883L78:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(427571072, $changed, -1, "com.example.ui.components.AddNewCategoryDialog.<anonymous> (CustomerWhatsAppDialogs.kt:878)");
            }
            ButtonKt.TextButton($onDismiss, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m75getLambda$985603165$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void OrderLongPressActionDialog(final CustomerJobEntity job, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03, Composer $composer, final int $changed) {
        Composer $composer2;
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(function0, "onEditDetails");
        Intrinsics.checkNotNullParameter(function02, "onUnassignExpert");
        Intrinsics.checkNotNullParameter(function03, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(1273022007);
        ComposerKt.sourceInformation($composer3, "C(OrderLongPressActionDialog)P(!1,2,3)1045@41223L101,898@34355L1340,931@35712L5457,896@34288L7042:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function03) ? 2048 : 1024;
        }
        if (($dirty & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1273022007, $dirty, -1, "com.example.ui.components.OrderLongPressActionDialog (CustomerWhatsAppDialogs.kt:895)");
            }
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function03, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1931031279$app(), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(206422957, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda104
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.OrderLongPressActionDialog$lambda$95(function03, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2) null, ComposableLambdaKt.rememberComposableLambda(-1518185365, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda105
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.OrderLongPressActionDialog$lambda$98(CustomerJobEntity.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(1914477770, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda106
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.OrderLongPressActionDialog$lambda$107(function03, function0, function02, job, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Shape) null, 0L, 0L, 0L, 0L, 0.0f, (DialogProperties) null, $composer2, (($dirty >> 9) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda107
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.OrderLongPressActionDialog$lambda$108(CustomerJobEntity.this, function0, function02, function03, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x020e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrderLongPressActionDialog$lambda$98(com.example.data.model.CustomerJobEntity r74, androidx.compose.runtime.Composer r75, int r76) {
        /*
            Method dump skipped, instructions count: 898
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.OrderLongPressActionDialog$lambda$98(com.example.data.model.CustomerJobEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0287 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrderLongPressActionDialog$lambda$107(final kotlin.jvm.functions.Function0 r54, final kotlin.jvm.functions.Function0 r55, final kotlin.jvm.functions.Function0 r56, final com.example.data.model.CustomerJobEntity r57, androidx.compose.runtime.Composer r58, int r59) {
        /*
            Method dump skipped, instructions count: 830
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.OrderLongPressActionDialog$lambda$107(kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, com.example.data.model.CustomerJobEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderLongPressActionDialog$lambda$107$lambda$106$lambda$100$lambda$99(Function0 $onDismiss, Function0 $onEditDetails) {
        $onDismiss.invoke();
        $onEditDetails.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderLongPressActionDialog$lambda$107$lambda$106$lambda$102$lambda$101(Function0 $onDismiss, Function0 $onUnassignExpert) {
        $onDismiss.invoke();
        $onUnassignExpert.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x022e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrderLongPressActionDialog$lambda$107$lambda$106$lambda$105(com.example.data.model.CustomerJobEntity r72, androidx.compose.foundation.layout.ColumnScope r73, androidx.compose.runtime.Composer r74, int r75) {
        /*
            Method dump skipped, instructions count: 955
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.OrderLongPressActionDialog$lambda$107$lambda$106$lambda$105(com.example.data.model.CustomerJobEntity, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderLongPressActionDialog$lambda$95(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1046@41237L77:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(206422957, $changed, -1, "com.example.ui.components.OrderLongPressActionDialog.<anonymous> (CustomerWhatsAppDialogs.kt:1046)");
            }
            ButtonKt.TextButton($onDismiss, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$2005509904$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void EditCustomerOrderDialog(final CustomerJobEntity job, final List<String> list, boolean isAdmin, final Function1<? super CustomerJobEntity, Unit> function1, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        boolean z;
        Object obj;
        MutableState name$delegate;
        boolean isClosedOrder;
        Object obj2;
        MutableState phone$delegate;
        Object obj3;
        Object obj4;
        int $dirty;
        boolean isAdmin2;
        Object obj5;
        MutableState rawLocation$delegate;
        Object obj6;
        MutableState issueDescription$delegate;
        Object obj7;
        Object take;
        int i2;
        Appendable appendable;
        boolean isPhoneValid;
        Object emptyList;
        Object parseCoordinatesFromText;
        Composer $composer2;
        final boolean isAdmin3;
        Intrinsics.checkNotNullParameter(job, "job");
        Intrinsics.checkNotNullParameter(list, "availableCategories");
        Intrinsics.checkNotNullParameter(function1, "onSave");
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(800853429);
        ComposerKt.sourceInformation($composer3, "C(EditCustomerOrderDialog)P(2!2,4)1070@42008L45,1071@42071L46,1072@42141L44,1073@42205L40,1074@42269L64,1075@42362L49,1076@42438L39,1078@42500L58,1081@42636L381,1089@43042L90,1096@43293L11508,1093@43138L11663:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(job) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(list) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            z = isAdmin;
        } else if (($changed & 384) == 0) {
            z = isAdmin;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = isAdmin;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 16384 : 8192;
        }
        if (($dirty2 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            isAdmin3 = z;
        } else {
            boolean isAdmin4 = i3 != 0 ? false : z;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(800853429, $dirty2, -1, "com.example.ui.components.EditCustomerOrderDialog (CustomerWhatsAppDialogs.kt:1065)");
            }
            boolean isClosedOrder2 = StringsKt.equals(job.getStatus(), "COMPLETED", true) || StringsKt.equals(job.getStatus(), "CANCELLED", true);
            boolean isLockedForStaff = isClosedOrder2 && !isAdmin4;
            ComposerKt.sourceInformationMarkerStart($composer3, 821013538, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            boolean z2 = true;
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = SnapshotStateKt.mutableStateOf$default(job.getCustomerName(), (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            MutableState name$delegate2 = (MutableState) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 821015555, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                name$delegate = name$delegate2;
                isClosedOrder = isClosedOrder2;
                obj2 = SnapshotStateKt.mutableStateOf$default(job.getCustomerPhone(), (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj2);
            } else {
                name$delegate = name$delegate2;
                isClosedOrder = isClosedOrder2;
                obj2 = rememberedValue2;
            }
            MutableState phone$delegate2 = (MutableState) obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 821017793, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue3 = $composer3.rememberedValue();
            if (rememberedValue3 == Composer.Companion.getEmpty()) {
                phone$delegate = phone$delegate2;
                obj3 = SnapshotStateKt.mutableStateOf$default(job.getServiceType(), (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj3);
            } else {
                phone$delegate = phone$delegate2;
                obj3 = rememberedValue3;
            }
            final MutableState serviceType$delegate = (MutableState) obj3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 821019837, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue4 = $composer3.rememberedValue();
            if (rememberedValue4 == Composer.Companion.getEmpty()) {
                obj4 = SnapshotStateKt.mutableStateOf$default(job.getAddress(), (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj4);
            } else {
                obj4 = rememberedValue4;
            }
            final MutableState address$delegate = (MutableState) obj4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 821021909, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue5 = $composer3.rememberedValue();
            if (rememberedValue5 == Composer.Companion.getEmpty()) {
                $dirty = $dirty2;
                isAdmin2 = isAdmin4;
                obj5 = SnapshotStateKt.mutableStateOf$default(job.getLatitude() + ", " + job.getLongitude(), (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj5);
            } else {
                $dirty = $dirty2;
                isAdmin2 = isAdmin4;
                obj5 = rememberedValue5;
            }
            MutableState rawLocation$delegate2 = (MutableState) obj5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 821024870, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue6 = $composer3.rememberedValue();
            if (rememberedValue6 == Composer.Companion.getEmpty()) {
                rawLocation$delegate = rawLocation$delegate2;
                obj6 = SnapshotStateKt.mutableStateOf$default(job.getIssueDescription(), (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj6);
            } else {
                rawLocation$delegate = rawLocation$delegate2;
                obj6 = rememberedValue6;
            }
            MutableState issueDescription$delegate2 = (MutableState) obj6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 821027292, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue7 = $composer3.rememberedValue();
            if (rememberedValue7 == Composer.Companion.getEmpty()) {
                issueDescription$delegate = issueDescription$delegate2;
                obj7 = SnapshotStateKt.mutableStateOf$default(job.getStatus(), (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj7);
            } else {
                issueDescription$delegate = issueDescription$delegate2;
                obj7 = rememberedValue7;
            }
            final MutableState selectedStatus$delegate = (MutableState) obj7;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String EditCustomerOrderDialog$lambda$113 = EditCustomerOrderDialog$lambda$113(phone$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 821029295, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed = $composer3.changed(EditCustomerOrderDialog$lambda$113);
            Object rememberedValue8 = $composer3.rememberedValue();
            if (changed || rememberedValue8 == Composer.Companion.getEmpty()) {
                CharSequence EditCustomerOrderDialog$lambda$1132 = EditCustomerOrderDialog$lambda$113(phone$delegate);
                Appendable sb = new StringBuilder();
                int length = EditCustomerOrderDialog$lambda$1132.length();
                int i4 = 0;
                while (i4 < length) {
                    int i5 = length;
                    char charAt = EditCustomerOrderDialog$lambda$1132.charAt(i4);
                    if (Character.isDigit(charAt)) {
                        i2 = i4;
                        appendable = sb;
                        appendable.append(charAt);
                    } else {
                        i2 = i4;
                        appendable = sb;
                    }
                    sb = appendable;
                    i4 = i2 + 1;
                    length = i5;
                }
                take = StringsKt.take(((StringBuilder) sb).toString(), 10);
                $composer3.updateRememberedValue(take);
            } else {
                take = rememberedValue8;
            }
            final String cleanPhone = (String) take;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean isPhoneValid2 = cleanPhone.length() == 10;
            String EditCustomerOrderDialog$lambda$116 = EditCustomerOrderDialog$lambda$116(serviceType$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 821033970, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed2 = $composer3.changed(EditCustomerOrderDialog$lambda$116) | $composer3.changed(list);
            int i6 = 0;
            Object rememberedValue9 = $composer3.rememberedValue();
            int i7 = 0;
            if (changed2 || rememberedValue9 == Composer.Companion.getEmpty()) {
                if (StringsKt.isBlank(EditCustomerOrderDialog$lambda$116(serviceType$delegate)) || isLockedForStaff) {
                    isPhoneValid = isPhoneValid2;
                    emptyList = CollectionsKt.emptyList();
                } else {
                    LinkedHashSet linkedSetOf = SetsKt.linkedSetOf(new String[]{"Electrician", "Plumber"});
                    linkedSetOf.addAll(list);
                    LinkedHashSet linkedHashSet = linkedSetOf;
                    Collection arrayList = new ArrayList();
                    for (Object obj8 : linkedHashSet) {
                        Iterable iterable = linkedHashSet;
                        int i8 = i6;
                        String str = (String) obj8;
                        boolean isPhoneValid3 = isPhoneValid2;
                        Object obj9 = rememberedValue9;
                        int i9 = i7;
                        boolean z3 = z2;
                        if ((!StringsKt.contains(str, StringsKt.trim(EditCustomerOrderDialog$lambda$116(serviceType$delegate)).toString(), z3) || StringsKt.equals(str, StringsKt.trim(EditCustomerOrderDialog$lambda$116(serviceType$delegate)).toString(), z3)) ? false : z3) {
                            arrayList.add(obj8);
                        }
                        z2 = z3;
                        linkedHashSet = iterable;
                        i6 = i8;
                        isPhoneValid2 = isPhoneValid3;
                        i7 = i9;
                        rememberedValue9 = obj9;
                    }
                    isPhoneValid = isPhoneValid2;
                    emptyList = CollectionsKt.take((List) arrayList, 4);
                }
                $composer3.updateRememberedValue(emptyList);
            } else {
                isPhoneValid = isPhoneValid2;
                emptyList = rememberedValue9;
            }
            final List matchingCategories = (List) emptyList;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String EditCustomerOrderDialog$lambda$122 = EditCustomerOrderDialog$lambda$122(rawLocation$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 821046671, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed3 = $composer3.changed(EditCustomerOrderDialog$lambda$122);
            Object rememberedValue10 = $composer3.rememberedValue();
            if (changed3 || rememberedValue10 == Composer.Companion.getEmpty()) {
                parseCoordinatesFromText = LocationHelper.INSTANCE.parseCoordinatesFromText(EditCustomerOrderDialog$lambda$122(rawLocation$delegate));
                $composer3.updateRememberedValue(parseCoordinatesFromText);
            } else {
                parseCoordinatesFromText = rememberedValue10;
            }
            final Pair parsedCoords = (Pair) parseCoordinatesFromText;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            final boolean isLockedForStaff2 = isLockedForStaff;
            final MutableState phone$delegate3 = phone$delegate;
            final MutableState name$delegate3 = name$delegate;
            final boolean isClosedOrder3 = isClosedOrder;
            final boolean isAdmin5 = isAdmin2;
            final MutableState phone$delegate4 = rawLocation$delegate;
            final boolean isPhoneValid4 = isPhoneValid;
            final MutableState issueDescription$delegate3 = issueDescription$delegate;
            AndroidDialog_androidKt.Dialog(function0, new DialogProperties(false, false, (SecureFlagPolicy) null, false, false, 7, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(398553804, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda65
                public final Object invoke(Object obj10, Object obj11) {
                    return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174(function0, isLockedForStaff2, job, isClosedOrder3, isPhoneValid4, parsedCoords, isAdmin5, cleanPhone, function1, name$delegate3, serviceType$delegate, address$delegate, issueDescription$delegate3, selectedStatus$delegate, matchingCategories, phone$delegate3, phone$delegate4, (Composer) obj10, ((Integer) obj11).intValue());
                }
            }, $composer2, 54), $composer2, (($dirty >> 12) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isAdmin3 = isAdmin5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda66
                public final Object invoke(Object obj10, Object obj11) {
                    return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$175(CustomerJobEntity.this, list, isAdmin3, function1, function0, $changed, i, (Composer) obj10, ((Integer) obj11).intValue());
                }
            });
        }
    }

    private static final String EditCustomerOrderDialog$lambda$110(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditCustomerOrderDialog$lambda$113(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditCustomerOrderDialog$lambda$116(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditCustomerOrderDialog$lambda$119(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditCustomerOrderDialog$lambda$122(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditCustomerOrderDialog$lambda$125(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditCustomerOrderDialog$lambda$128(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174(final Function0 $onDismiss, final boolean $isLockedForStaff, final CustomerJobEntity $job, final boolean $isClosedOrder, final boolean $isPhoneValid, final Pair $parsedCoords, final boolean $isAdmin, final String $cleanPhone, final Function1 $onSave, final MutableState $name$delegate, final MutableState $serviceType$delegate, final MutableState $address$delegate, final MutableState $issueDescription$delegate, final MutableState $selectedStatus$delegate, final List $matchingCategories, final MutableState $phone$delegate, final MutableState $rawLocation$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C1097@43315L15,1097@43303L27,1100@43417L1468,1129@44911L2487,1180@47409L7386,1098@43339L11456:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(398553804, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous> (CustomerWhatsAppDialogs.kt:1097)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 758117019, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda71
                    public final Object invoke() {
                        return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$136$lambda$135($onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackHandlerKt.BackHandler(false, (Function0) obj, $composer, 0, 1);
            ScaffoldKt.Scaffold-TvnljyQ(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ComposableLambdaKt.rememberComposableLambda(-382293104, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda72
                public final Object invoke(Object obj2, Object obj3) {
                    return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$140($isLockedForStaff, $job, $isClosedOrder, $onDismiss, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), ComposableLambdaKt.rememberComposableLambda(781288081, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda73
                public final Object invoke(Object obj2, Object obj3) {
                    return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$145($isLockedForStaff, $onDismiss, $isPhoneValid, $parsedCoords, $job, $isAdmin, $cleanPhone, $onSave, $name$delegate, $serviceType$delegate, $address$delegate, $issueDescription$delegate, $selectedStatus$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, 0, 0L, 0L, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(-202199717, true, new Function3() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda74
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$173($isLockedForStaff, $isPhoneValid, $matchingCategories, $parsedCoords, $isAdmin, $isClosedOrder, $job, $name$delegate, $phone$delegate, $cleanPhone, $serviceType$delegate, $address$delegate, $rawLocation$delegate, $issueDescription$delegate, $selectedStatus$delegate, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306806, 504);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$136$lambda$135(Function0 $onDismiss) {
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$140(final boolean $isLockedForStaff, final CustomerJobEntity $job, final boolean $isClosedOrder, final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1102@43474L950,1119@44463L206,1125@44790L11,1124@44718L135,1101@43435L1436:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-382293104, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1101)");
            }
            AppBarKt.TopAppBar-GHTll3U(ComposableLambdaKt.rememberComposableLambda(571471180, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda50
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$140$lambda$138($isLockedForStaff, $job, $isClosedOrder, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(229792266, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda51
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$140$lambda$139($onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function3) null, 0.0f, (WindowInsets) null, TopAppBarDefaults.INSTANCE.topAppBarColors-zjMxDiM(ColorSchemeKt.surfaceColorAtElevation-3ABfNKs(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable), Dp.constructor-impl(3)), 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 15, 30), (TopAppBarScrollBehavior) null, $composer, 390, 186);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit EditCustomerOrderDialog$lambda$174$lambda$140$lambda$138(boolean r52, com.example.data.model.CustomerJobEntity r53, boolean r54, androidx.compose.runtime.Composer r55, int r56) {
        /*
            Method dump skipped, instructions count: 595
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$140$lambda$138(boolean, com.example.data.model.CustomerJobEntity, boolean, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$140$lambda$139(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1120@44489L158:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(229792266, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1120)");
            }
            IconButtonKt.IconButton($onDismiss, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m44getLambda$1237974835$app(), $composer, 196608, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$145(final boolean $isLockedForStaff, final Function0 $onDismiss, final boolean $isPhoneValid, final Pair $parsedCoords, final CustomerJobEntity $job, final boolean $isAdmin, final String $cleanPhone, final Function1 $onSave, final MutableState $name$delegate, final MutableState $serviceType$delegate, final MutableState $address$delegate, final MutableState $issueDescription$delegate, final MutableState $selectedStatus$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1134@45098L2286,1130@44929L2455:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(781288081, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1130)");
            }
            SurfaceKt.Surface-T9BRK9s(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), (Shape) null, 0L, 0L, Dp.constructor-impl(4), Dp.constructor-impl(8), (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(1494234604, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda76
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$145$lambda$144($isLockedForStaff, $onDismiss, $isPhoneValid, $parsedCoords, $job, $isAdmin, $cleanPhone, $onSave, $name$delegate, $serviceType$delegate, $address$delegate, $issueDescription$delegate, $selectedStatus$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12804102, 78);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$145$lambda$144(boolean $isLockedForStaff, final Function0 $onDismiss, boolean $isPhoneValid, final Pair $parsedCoords, final CustomerJobEntity $job, final boolean $isAdmin, final String $cleanPhone, final Function1 $onSave, final MutableState $name$delegate, final MutableState $serviceType$delegate, final MutableState $address$delegate, final MutableState $issueDescription$delegate, final MutableState $selectedStatus$delegate, Composer $composer, int $changed) {
        Composer composer;
        Composer composer2;
        Composer composer3;
        Object obj;
        ComposerKt.sourceInformation($composer, "C1135@45120L2246:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1494234604, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1135)");
            }
            Modifier modifier = PaddingKt.padding-3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(16));
            Arrangement.Horizontal horizontal = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(12));
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(horizontal, Alignment.Companion.getTop(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composer4 = Updater.constructor-impl($composer);
            Updater.set-impl(composer4, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer4.getInserting() || !Intrinsics.areEqual(composer4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer4.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer4, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407918630, "C100@5047L9:Row.kt#2w3rfo");
            int i3 = ((54 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1898928721, "C:CustomerWhatsAppDialogs.kt#qonjpd");
            if (!$isLockedForStaff) {
                $composer.startReplaceGroup(1898941244);
                ComposerKt.sourceInformation($composer, "1142@45435L240,1149@45754L917,1148@45704L1305");
                ButtonKt.OutlinedButton($onDismiss, RowScope.weight$default(rowScope, Modifier.Companion, 1.0f, false, 2, (Object) null), false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1179044059$app(), $composer, 805306368, 508);
                boolean z = (StringsKt.isBlank(EditCustomerOrderDialog$lambda$110($name$delegate)) || !$isPhoneValid || StringsKt.isBlank(EditCustomerOrderDialog$lambda$116($serviceType$delegate)) || $parsedCoords == null) ? false : true;
                Modifier weight$default = RowScope.weight$default(rowScope, Modifier.Companion, 1.0f, false, 2, (Object) null);
                ComposerKt.sourceInformationMarkerStart($composer, 1031097949, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
                boolean changed = $composer.changed($parsedCoords) | $composer.changed($job) | $composer.changed($isAdmin) | $composer.changed($cleanPhone) | $composer.changed($onSave) | $composer.changed($onDismiss);
                Object rememberedValue = $composer.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                    composer2 = $composer;
                    composer3 = $composer;
                    composer = $composer;
                    obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda58
                        public final Object invoke() {
                            return CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$145$lambda$144$lambda$143$lambda$142$lambda$141($parsedCoords, $job, $isAdmin, $cleanPhone, $onSave, $onDismiss, $name$delegate, $serviceType$delegate, $address$delegate, $issueDescription$delegate, $selectedStatus$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    composer2 = $composer;
                    composer3 = $composer;
                    obj = rememberedValue;
                    composer = $composer;
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ButtonKt.Button((Function0) obj, weight$default, z, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$1106412957$app(), composer3, 805306368, 504);
                composer3.endReplaceGroup();
            } else {
                composer = $composer;
                composer2 = $composer;
                $composer.startReplaceGroup(1900523019);
                ComposerKt.sourceInformation($composer, "1170@47071L247");
                composer3 = $composer;
                ButtonKt.Button($onDismiss, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m74getLambda$985132300$app(), composer3, 805306416, 508);
                composer3.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$145$lambda$144$lambda$143$lambda$142$lambda$141(Pair $parsedCoords, CustomerJobEntity $job, boolean $isAdmin, String $cleanPhone, Function1 $onSave, Function0 $onDismiss, MutableState $name$delegate, MutableState $serviceType$delegate, MutableState $address$delegate, MutableState $issueDescription$delegate, MutableState $selectedStatus$delegate) {
        Pair coords = $parsedCoords == null ? new Pair(Double.valueOf($job.getLatitude()), Double.valueOf($job.getLongitude())) : $parsedCoords;
        CustomerJobEntity updated = CustomerJobEntity.copy$default($job, 0L, StringsKt.trim(EditCustomerOrderDialog$lambda$110($name$delegate)).toString(), $cleanPhone, StringsKt.trim(EditCustomerOrderDialog$lambda$116($serviceType$delegate)).toString(), StringsKt.trim(EditCustomerOrderDialog$lambda$125($issueDescription$delegate)).toString(), StringsKt.trim(EditCustomerOrderDialog$lambda$119($address$delegate)).toString(), ((Number) coords.getFirst()).doubleValue(), ((Number) coords.getSecond()).doubleValue(), $isAdmin ? EditCustomerOrderDialog$lambda$128($selectedStatus$delegate) : $job.getStatus(), null, null, null, null, null, null, 0L, null, false, false, false, null, false, null, 0L, false, null, null, null, null, null, null, null, null, null, -511, 3, null);
        $onSave.invoke(updated);
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0a45  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0b35  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit EditCustomerOrderDialog$lambda$174$lambda$173(final boolean r109, final boolean r110, java.util.List r111, final kotlin.Pair r112, boolean r113, boolean r114, final com.example.data.model.CustomerJobEntity r115, final androidx.compose.runtime.MutableState r116, final androidx.compose.runtime.MutableState r117, final java.lang.String r118, final androidx.compose.runtime.MutableState r119, final androidx.compose.runtime.MutableState r120, final androidx.compose.runtime.MutableState r121, final androidx.compose.runtime.MutableState r122, final androidx.compose.runtime.MutableState r123, androidx.compose.foundation.layout.PaddingValues r124, androidx.compose.runtime.Composer r125, int r126) {
        /*
            Method dump skipped, instructions count: 2875
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.EditCustomerOrderDialog$lambda$174$lambda$173(boolean, boolean, java.util.List, kotlin.Pair, boolean, boolean, com.example.data.model.CustomerJobEntity, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, java.lang.String, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$146(CustomerJobEntity $job, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1198@48310L10,1199@48382L11,1196@48102L398:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1181902639, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1196)");
            }
            TextKt.Text--4IGK_g("ℹ️ This order is marked as " + $job.getStatus() + ". Non-admin editing is locked. Contact an Admin to modify closed records.", PaddingKt.padding-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 48, 0, 65528);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$148$lambda$147(boolean $isLockedForStaff, MutableState $name$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!$isLockedForStaff) {
            $name$delegate.setValue(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$151$lambda$150(boolean $isLockedForStaff, MutableState $phone$delegate, String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (!$isLockedForStaff) {
            String str = input;
            Appendable sb = new StringBuilder();
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (Character.isDigit(charAt)) {
                    sb.append(charAt);
                }
            }
            String digits = StringsKt.take(((StringBuilder) sb).toString(), 10);
            $phone$delegate.setValue(digits);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$152(boolean $isLockedForStaff, boolean $isPhoneValid, String $cleanPhone, Composer $composer, int $changed) {
        Composer composer = $composer;
        ComposerKt.sourceInformation(composer, "C:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1363411129, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1229)");
            }
            if ($isLockedForStaff) {
                composer.startReplaceGroup(75545243);
            } else {
                composer.startReplaceGroup(124833383);
                ComposerKt.sourceInformation(composer, "");
                if (!$isPhoneValid) {
                    composer.startReplaceGroup(124876535);
                    ComposerKt.sourceInformation(composer, "1231@49839L11,1231@49757L100");
                    TextKt.Text--4IGK_g("Must be exactly 10 digits (" + $cleanPhone.length() + "/10)", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131066);
                    composer = $composer;
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(125043036);
                    ComposerKt.sourceInformation(composer, "1233@49927L31");
                    TextKt.Text--4IGK_g("✅ Valid 10-digit number", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 6, 0, 131070);
                    composer = $composer;
                    composer.endReplaceGroup();
                }
            }
            composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$154$lambda$153(boolean $isLockedForStaff, MutableState $serviceType$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!$isLockedForStaff) {
            $serviceType$delegate.setValue(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$159$lambda$158$lambda$156$lambda$155(String $cat, MutableState $serviceType$delegate) {
        $serviceType$delegate.setValue($cat);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$159$lambda$158$lambda$157(String $cat, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1267@51647L11,1263@51406L406:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1406608762, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1263)");
            }
            TextKt.Text--4IGK_g("+ " + $cat, PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(8), Dp.constructor-impl(4)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryContainer-0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$161$lambda$160(boolean $isLockedForStaff, MutableState $address$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!$isLockedForStaff) {
            $address$delegate.setValue(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$163$lambda$162(boolean $isLockedForStaff, MutableState $rawLocation$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!$isLockedForStaff) {
            $rawLocation$delegate.setValue(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$164(boolean $isLockedForStaff, Pair $parsedCoords, Composer $composer, int $changed) {
        Composer composer = $composer;
        ComposerKt.sourceInformation(composer, "C:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1656632612, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1294)");
            }
            if ($isLockedForStaff) {
                composer.startReplaceGroup(-779451682);
            } else {
                composer.startReplaceGroup(-726994474);
                ComposerKt.sourceInformation(composer, "");
                if ($parsedCoords == null) {
                    composer.startReplaceGroup(-726945587);
                    ComposerKt.sourceInformation(composer, "1296@53021L11,1296@52958L81");
                    TextKt.Text--4IGK_g("Enter valid Lat, Lng or Maps URL", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 6, 0, 131066);
                    composer = $composer;
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(-726796353);
                    ComposerKt.sourceInformation(composer, "1298@53109L63");
                    TextKt.Text--4IGK_g("✅ Coords: " + $parsedCoords.getFirst() + ", " + $parsedCoords.getSecond(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
                    composer = $composer;
                    composer.endReplaceGroup();
                }
            }
            composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$166$lambda$165(boolean $isLockedForStaff, MutableState $issueDescription$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!$isLockedForStaff) {
            $issueDescription$delegate.setValue(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$171$lambda$170$lambda$168$lambda$167(String $st, MutableState $selectedStatus$delegate) {
        $selectedStatus$delegate.setValue($st);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditCustomerOrderDialog$lambda$174$lambda$173$lambda$172$lambda$171$lambda$170$lambda$169(String $st, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1327@54617L56:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-355302479, $changed, -1, "com.example.ui.components.EditCustomerOrderDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1327)");
            }
            TextKt.Text--4IGK_g($st, (Modifier) null, 0L, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0656  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x06d3  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0755 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0823  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x08bb  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x08ab  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x06d5  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0689  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0609  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x053a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void EditUserProfileDialog(final java.lang.String r51, final java.lang.String r52, final java.lang.String r53, java.lang.String r54, boolean r55, java.lang.String r56, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r57, kotlin.jvm.functions.Function0<kotlin.Unit> r58, final kotlin.jvm.functions.Function4<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> r59, final kotlin.jvm.functions.Function0<kotlin.Unit> r60, androidx.compose.runtime.Composer r61, final int r62, final int r63) {
        /*
            Method dump skipped, instructions count: 2278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.EditUserProfileDialog(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function4, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final String EditUserProfileDialog$lambda$177(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditUserProfileDialog$lambda$180(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditUserProfileDialog$lambda$183(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditUserProfileDialog$lambda$186(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditUserProfileDialog$lambda$189(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean EditUserProfileDialog$lambda$192(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void EditUserProfileDialog$lambda$193(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean EditUserProfileDialog$lambda$195(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void EditUserProfileDialog$lambda$196(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean EditUserProfileDialog$lambda$198(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void EditUserProfileDialog$lambda$199(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean EditUserProfileDialog$lambda$201(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void EditUserProfileDialog$lambda$202(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean EditUserProfileDialog$lambda$204(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void EditUserProfileDialog$lambda$205(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final Uri EditUserProfileDialog$lambda$207(MutableState<Uri> mutableState) {
        return (Uri) ((State) mutableState).getValue();
    }

    private static final Uri EditUserProfileDialog$createCameraUri(Context context) {
        try {
            File tempFile = File.createTempFile("user_photo_" + System.currentTimeMillis(), ".jpg", context.getCacheDir());
            return FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", tempFile);
        } catch (Exception e) {
            return null;
        }
    }

    private static final void EditUserProfileDialog$uploadPickedImage(CoroutineScope coroutineScope, MutableState<Boolean> mutableState, Context context, MutableState<String> mutableState2, Uri uri) {
        EditUserProfileDialog$lambda$202(mutableState, true);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CustomerWhatsAppDialogsKt$EditUserProfileDialog$uploadPickedImage$1(context, uri, mutableState, mutableState2, null), 3, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$210$lambda$209(MutableState $tempCameraUri$delegate, CoroutineScope $coroutineScope, MutableState $isUploadingPhoto$delegate, Context $context, MutableState $photoUri$delegate, boolean success) {
        if (success && EditUserProfileDialog$lambda$207($tempCameraUri$delegate) != null) {
            Uri EditUserProfileDialog$lambda$207 = EditUserProfileDialog$lambda$207($tempCameraUri$delegate);
            Intrinsics.checkNotNull(EditUserProfileDialog$lambda$207);
            EditUserProfileDialog$uploadPickedImage($coroutineScope, $isUploadingPhoto$delegate, $context, $photoUri$delegate, EditUserProfileDialog$lambda$207);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$212$lambda$211(ManagedActivityResultLauncher $takePictureLauncher, Context $context, MutableState $tempCameraUri$delegate, boolean isGranted) {
        if (!isGranted) {
            Toast.makeText($context, "Camera permission is required to capture photo", 0).show();
        } else {
            Uri uri = EditUserProfileDialog$createCameraUri($context);
            if (uri == null) {
                Toast.makeText($context, "Unable to access camera cache file", 0).show();
            } else {
                $tempCameraUri$delegate.setValue(uri);
                $takePictureLauncher.launch(uri);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$214$lambda$213(CoroutineScope $coroutineScope, MutableState $isUploadingPhoto$delegate, Context $context, MutableState $photoUri$delegate, Uri uri) {
        if (uri != null) {
            EditUserProfileDialog$uploadPickedImage($coroutineScope, $isUploadingPhoto$delegate, $context, $photoUri$delegate, uri);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$216$lambda$215(Context $context, ManagedActivityResultLauncher $takePictureLauncher, ManagedActivityResultLauncher $cameraPermissionLauncher, MutableState $tempCameraUri$delegate) {
        boolean hasCameraPermission = ContextCompat.checkSelfPermission($context, "android.permission.CAMERA") == 0;
        if (hasCameraPermission) {
            Uri uri = EditUserProfileDialog$createCameraUri($context);
            if (uri == null) {
                Toast.makeText($context, "Unable to access camera", 0).show();
            } else {
                $tempCameraUri$delegate.setValue(uri);
                $takePictureLauncher.launch(uri);
            }
        } else {
            $cameraPermissionLauncher.launch("android.permission.CAMERA");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$218$lambda$217(ManagedActivityResultLauncher $photoPickerLauncher) {
        $photoPickerLauncher.launch(PickVisualMediaRequestKt.PickVisualMediaRequest$default(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE, 0, false, (ActivityResultContracts.PickVisualMedia.DefaultTab) null, 14, (Object) null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$220$lambda$219(MutableState $showImageSourcePicker$delegate) {
        EditUserProfileDialog$lambda$205($showImageSourcePicker$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282(final Function0 $onDismiss, final boolean $isAdmin, final boolean $isPhoneValid, final Function1 $onUpdatePassword, final Function4 $onSave, final String $cleanPhone, final MutableState $userName$delegate, final MutableState $newPassword$delegate, final MutableState $userRole$delegate, final MutableState $photoUri$delegate, final Function0 $onDeleteAccount, final MutableState $isUploadingPhoto$delegate, final MutableState $showImageSourcePicker$delegate, final List $defaultAvatars, final MutableState $userPhone$delegate, final String $initialRole, final MutableState $passwordVisible$delegate, final MutableState $showPasswordOtpModal$delegate, final MutableState $showDeleteAccountConfirm$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C1474@59912L15,1474@59900L27,1477@60014L1862,1515@61902L1435,1549@63348L15896,1475@59936L19308:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1005713043, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous> (CustomerWhatsAppDialogs.kt:1474)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -122592004, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda54
                    public final Object invoke() {
                        return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$224$lambda$223($onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackHandlerKt.BackHandler(false, (Function0) obj, $composer, 0, 1);
            ScaffoldKt.Scaffold-TvnljyQ(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ComposableLambdaKt.rememberComposableLambda(1221235505, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda55
                public final Object invoke(Object obj2, Object obj3) {
                    return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$228($isAdmin, $onDismiss, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), ComposableLambdaKt.rememberComposableLambda(-910429582, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda56
                public final Object invoke(Object obj2, Object obj3) {
                    return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$233($onDismiss, $isPhoneValid, $onUpdatePassword, $onSave, $cleanPhone, $userName$delegate, $newPassword$delegate, $userRole$delegate, $photoUri$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, 0, 0L, 0L, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(1608884156, true, new Function3() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda57
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$281($isPhoneValid, $isAdmin, $onDeleteAccount, $isUploadingPhoto$delegate, $showImageSourcePicker$delegate, $photoUri$delegate, $userName$delegate, $defaultAvatars, $userPhone$delegate, $cleanPhone, $userRole$delegate, $initialRole, $newPassword$delegate, $passwordVisible$delegate, $showPasswordOtpModal$delegate, $showDeleteAccountConfirm$delegate, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306806, 504);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$224$lambda$223(Function0 $onDismiss) {
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$228(final boolean $isAdmin, final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1479@60071L1344,1505@61454L206,1511@61781L11,1510@61709L135,1478@60032L1830:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1221235505, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1478)");
            }
            AppBarKt.TopAppBar-GHTll3U(ComposableLambdaKt.rememberComposableLambda(-68080659, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda41
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$228$lambda$226($isAdmin, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-753848277, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda42
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$228$lambda$227($onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function3) null, 0.0f, (WindowInsets) null, TopAppBarDefaults.INSTANCE.topAppBarColors-zjMxDiM(ColorSchemeKt.surfaceColorAtElevation-3ABfNKs(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable), Dp.constructor-impl(3)), 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 15, 30), (TopAppBarScrollBehavior) null, $composer, 390, 186);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0182  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit EditUserProfileDialog$lambda$282$lambda$228$lambda$226(boolean r49, androidx.compose.runtime.Composer r50, int r51) {
        /*
            Method dump skipped, instructions count: 488
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$228$lambda$226(boolean, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$228$lambda$227(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1506@61480L158:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-753848277, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1506)");
            }
            IconButtonKt.IconButton($onDismiss, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.m71getLambda$746659922$app(), $composer, 196608, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$233(final Function0 $onDismiss, final boolean $isPhoneValid, final Function1 $onUpdatePassword, final Function4 $onSave, final String $cleanPhone, final MutableState $userName$delegate, final MutableState $newPassword$delegate, final MutableState $userRole$delegate, final MutableState $photoUri$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1520@62089L1234,1516@61920L1403:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-910429582, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1516)");
            }
            SurfaceKt.Surface-T9BRK9s(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), (Shape) null, 0L, 0L, Dp.constructor-impl(4), Dp.constructor-impl(8), (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(914265229, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda108
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$233$lambda$232($onDismiss, $isPhoneValid, $onUpdatePassword, $onSave, $cleanPhone, $userName$delegate, $newPassword$delegate, $userRole$delegate, $photoUri$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12804102, 78);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit EditUserProfileDialog$lambda$282$lambda$233$lambda$232(final kotlin.jvm.functions.Function0 r48, boolean r49, final kotlin.jvm.functions.Function1 r50, final kotlin.jvm.functions.Function4 r51, final java.lang.String r52, final androidx.compose.runtime.MutableState r53, final androidx.compose.runtime.MutableState r54, final androidx.compose.runtime.MutableState r55, final androidx.compose.runtime.MutableState r56, androidx.compose.runtime.Composer r57, int r58) {
        /*
            Method dump skipped, instructions count: 592
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$233$lambda$232(kotlin.jvm.functions.Function0, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function4, java.lang.String, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$233$lambda$232$lambda$231$lambda$230$lambda$229(Function1 $onUpdatePassword, Function4 $onSave, String $cleanPhone, Function0 $onDismiss, MutableState $newPassword$delegate, MutableState $userName$delegate, MutableState $userRole$delegate, MutableState $photoUri$delegate) {
        if (!StringsKt.isBlank(EditUserProfileDialog$lambda$189($newPassword$delegate)) && $onUpdatePassword != null) {
            $onUpdatePassword.invoke(StringsKt.trim(EditUserProfileDialog$lambda$189($newPassword$delegate)).toString());
        }
        $onSave.invoke(StringsKt.trim(EditUserProfileDialog$lambda$177($userName$delegate)).toString(), $cleanPhone, StringsKt.trim(EditUserProfileDialog$lambda$183($userRole$delegate)).toString(), EditUserProfileDialog$lambda$186($photoUri$delegate));
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0a44  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0a50  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0a87  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0b48  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0b95  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0ba1  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0cc0  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0d35  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0d68  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0dd6  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0f53  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0f88  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x1008  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x108e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x11dc  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x1081  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0f98  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0f5c  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0e68  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0d76  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0ccc  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0c1e  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0b56  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0a9d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0a56  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x07aa A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0761  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x063d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x05f4  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x051a  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0396 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x05e2  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x05ee  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0627  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x074f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x075b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0794  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0813  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit EditUserProfileDialog$lambda$282$lambda$281(final boolean r135, boolean r136, kotlin.jvm.functions.Function0 r137, final androidx.compose.runtime.MutableState r138, final androidx.compose.runtime.MutableState r139, final androidx.compose.runtime.MutableState r140, final androidx.compose.runtime.MutableState r141, java.util.List r142, final androidx.compose.runtime.MutableState r143, final java.lang.String r144, final androidx.compose.runtime.MutableState r145, final java.lang.String r146, androidx.compose.runtime.MutableState r147, final androidx.compose.runtime.MutableState r148, final androidx.compose.runtime.MutableState r149, final androidx.compose.runtime.MutableState r150, androidx.compose.foundation.layout.PaddingValues r151, androidx.compose.runtime.Composer r152, int r153) {
        /*
            Method dump skipped, instructions count: 4578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$281(boolean, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, java.util.List, androidx.compose.runtime.MutableState, java.lang.String, androidx.compose.runtime.MutableState, java.lang.String, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$243$lambda$235$lambda$234(MutableState $showImageSourcePicker$delegate) {
        EditUserProfileDialog$lambda$205($showImageSourcePicker$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:70:0x046e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$243$lambda$240(androidx.compose.runtime.MutableState r52, androidx.compose.runtime.MutableState r53, androidx.compose.runtime.MutableState r54, androidx.compose.runtime.Composer r55, int r56) {
        /*
            Method dump skipped, instructions count: 1252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$243$lambda$240(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$243$lambda$242$lambda$241(MutableState $showImageSourcePicker$delegate) {
        EditUserProfileDialog$lambda$205($showImageSourcePicker$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$250$lambda$249$lambda$248$lambda$245$lambda$244(String $emoji, MutableState $photoUri$delegate) {
        $photoUri$delegate.setValue("preset:" + $emoji);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x016e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$250$lambda$249$lambda$248$lambda$247(java.lang.String r49, androidx.compose.runtime.Composer r50, int r51) {
        /*
            Method dump skipped, instructions count: 372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$250$lambda$249$lambda$248$lambda$247(java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$256$lambda$252$lambda$251(MutableState $showImageSourcePicker$delegate) {
        EditUserProfileDialog$lambda$205($showImageSourcePicker$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$256$lambda$253(MutableState $isUploadingPhoto$delegate, MutableState $photoUri$delegate, RowScope $this$OutlinedButton, Composer $composer, int $changed) {
        String str;
        Intrinsics.checkNotNullParameter($this$OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C1686@70720L91,1687@70840L28,1688@70897L231:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-142240070, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1686)");
            }
            IconKt.Icon-ww6aTOc(PhotoCameraKt.getPhotoCamera(Icons.INSTANCE.getDefault()), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(14)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer, 6);
            if (EditUserProfileDialog$lambda$201($isUploadingPhoto$delegate)) {
                str = "Uploading...";
            } else {
                String EditUserProfileDialog$lambda$186 = EditUserProfileDialog$lambda$186($photoUri$delegate);
                str = EditUserProfileDialog$lambda$186 == null || StringsKt.isBlank(EditUserProfileDialog$lambda$186) ? "Gallery / Camera" : "Change Photo";
            }
            TextKt.Text--4IGK_g(str, (Modifier) null, 0L, TextUnitKt.getSp(11.5d), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 0, 131062);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$257$lambda$256$lambda$255$lambda$254(MutableState $photoUri$delegate) {
        $photoUri$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$259$lambda$258(MutableState $userName$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $userName$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$262$lambda$261(MutableState $userPhone$delegate, String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        String str = input;
        Appendable sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isDigit(charAt)) {
                sb.append(charAt);
            }
        }
        $userPhone$delegate.setValue(StringsKt.take(((StringBuilder) sb).toString(), 10));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$263(boolean $isPhoneValid, String $cleanPhone, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1025258328, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1731)");
            }
            if (!$isPhoneValid) {
                $composer.startReplaceGroup(-1958358362);
                ComposerKt.sourceInformation($composer, "1732@73051L11,1732@72977L92");
                TextKt.Text--4IGK_g("Must be 10 digits (" + $cleanPhone.length() + "/10)", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131066);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1958207485);
                ComposerKt.sourceInformation($composer, "1734@73131L31");
                TextKt.Text--4IGK_g("✅ Valid 10-digit number", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 6, 0, 131070);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$265$lambda$264(MutableState $userRole$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $userRole$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$268(java.lang.String r71, androidx.compose.runtime.Composer r72, int r73) {
        /*
            Method dump skipped, instructions count: 891
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$268(java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$270$lambda$269(MutableState $newPassword$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $newPassword$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$274(final MutableState $passwordVisible$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C1811@76994L38,1811@77034L261,1811@76973L322:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1006894921, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1811)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1258375939, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda121
                    public final Object invoke() {
                        return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$274$lambda$272$lambda$271($passwordVisible$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) obj, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(-1085331116, true, new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda122
                public final Object invoke(Object obj2, Object obj3) {
                    return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$274$lambda$273($passwordVisible$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 196614, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$274$lambda$272$lambda$271(MutableState $passwordVisible$delegate) {
        EditUserProfileDialog$lambda$193($passwordVisible$delegate, !EditUserProfileDialog$lambda$192($passwordVisible$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$274$lambda$273(MutableState $passwordVisible$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1812@77064L205:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1085331116, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1812)");
            }
            IconKt.Icon-ww6aTOc(EditUserProfileDialog$lambda$192($passwordVisible$delegate) ? VisibilityOffKt.getVisibilityOff(Icons.INSTANCE.getDefault()) : VisibilityKt.getVisibility(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$276$lambda$275(MutableState $showPasswordOtpModal$delegate) {
        EditUserProfileDialog$lambda$196($showPasswordOtpModal$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$278$lambda$277(MutableState $showDeleteAccountConfirm$delegate) {
        EditUserProfileDialog$lambda$199($showDeleteAccountConfirm$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$282$lambda$281$lambda$280$lambda$279(String $cleanPhone, RowScope $this$OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C1850@78938L86,1851@79049L28,1852@79102L78:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(484230049, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CustomerWhatsAppDialogs.kt:1850)");
            }
            IconKt.Icon-ww6aTOc(DeleteKt.getDelete(Icons.INSTANCE.getDefault()), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer, 6);
            TextKt.Text--4IGK_g("Delete Current Account (ID: " + $cleanPhone + ")", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196608, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$284$lambda$283(MutableState $showDeleteAccountConfirm$delegate) {
        EditUserProfileDialog$lambda$199($showDeleteAccountConfirm$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$291(String $cleanPhone, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1869@79848L168:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(249751739, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous> (CustomerWhatsAppDialogs.kt:1869)");
            }
            TextKt.Text--4IGK_g("Are you sure you want to permanently delete your account ID (" + $cleanPhone + ")? You will be immediately logged out and your credentials will be removed from Hurifix.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$287(final Function0 $onDismiss, final Function0 $onDeleteAccount, final MutableState $showDeleteAccountConfirm$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C1873@80116L165,1878@80371L11,1878@80327L62,1872@80078L425:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1908866912, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous> (CustomerWhatsAppDialogs.kt:1872)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -470065083, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss) | $composer.changed($onDeleteAccount);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda75
                    public final Object invoke() {
                        return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$287$lambda$286$lambda$285($onDismiss, $onDeleteAccount, $showDeleteAccountConfirm$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, (Shape) null, ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14), (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$2021431984$app(), $composer, 805306368, 494);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$287$lambda$286$lambda$285(Function0 $onDismiss, Function0 $onDeleteAccount, MutableState $showDeleteAccountConfirm$delegate) {
        EditUserProfileDialog$lambda$199($showDeleteAccountConfirm$delegate, false);
        $onDismiss.invoke();
        $onDeleteAccount.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$290(final MutableState $showDeleteAccountConfirm$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C1884@80586L36,1884@80565L113:CustomerWhatsAppDialogs.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1531560926, $changed, -1, "com.example.ui.components.EditUserProfileDialog.<anonymous> (CustomerWhatsAppDialogs.kt:1884)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1343731358, "CC(remember):CustomerWhatsAppDialogs.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda102
                    public final Object invoke() {
                        return CustomerWhatsAppDialogsKt.EditUserProfileDialog$lambda$290$lambda$289$lambda$288($showDeleteAccountConfirm$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) obj, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$CustomerWhatsAppDialogsKt.INSTANCE.getLambda$977779585$app(), $composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$290$lambda$289$lambda$288(MutableState $showDeleteAccountConfirm$delegate) {
        EditUserProfileDialog$lambda$199($showDeleteAccountConfirm$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$293$lambda$292(Function1 $onUpdatePassword, Context $context, MutableState $showPasswordOtpModal$delegate, MutableState $newPassword$delegate) {
        EditUserProfileDialog$lambda$196($showPasswordOtpModal$delegate, false);
        if (!StringsKt.isBlank(EditUserProfileDialog$lambda$189($newPassword$delegate))) {
            if ($onUpdatePassword != null) {
                $onUpdatePassword.invoke(StringsKt.trim(EditUserProfileDialog$lambda$189($newPassword$delegate)).toString());
            }
            Toast.makeText($context, "✅ Password updated successfully!", 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit EditUserProfileDialog$lambda$295$lambda$294(MutableState $showPasswordOtpModal$delegate) {
        EditUserProfileDialog$lambda$196($showPasswordOtpModal$delegate, false);
        return Unit.INSTANCE;
    }

    public static final void EditAdminProfileDialog(final String initialName, final String initialPhone, final String initialRole, String initialPhotoUri, boolean isAdmin, String currentPassword, Function1<? super String, Unit> function1, Function0<Unit> function0, final Function4<? super String, ? super String, ? super String, ? super String, Unit> function4, final Function0<Unit> function02, Composer $composer, final int $changed, final int i) {
        String initialPhotoUri2;
        boolean isAdmin2;
        String str;
        Function1 function12;
        int i2;
        String currentPassword2;
        Function1 onUpdatePassword;
        Function0 onDeleteAccount;
        Composer $composer2;
        final Function0 onDeleteAccount2;
        final Function1 onUpdatePassword2;
        final String currentPassword3;
        final boolean isAdmin3;
        final String initialPhotoUri3;
        Intrinsics.checkNotNullParameter(initialName, "initialName");
        Intrinsics.checkNotNullParameter(initialPhone, "initialPhone");
        Intrinsics.checkNotNullParameter(initialRole, "initialRole");
        Intrinsics.checkNotNullParameter(function4, "onSave");
        Intrinsics.checkNotNullParameter(function02, "onDismiss");
        Composer $composer3 = $composer.startRestartGroup(389123906);
        ComposerKt.sourceInformation($composer3, "C(EditAdminProfileDialog)P(1,2,4,3,5!1,9!1,8)1924@81837L391:CustomerWhatsAppDialogs.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(initialName) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(initialPhone) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(initialRole) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            initialPhotoUri2 = initialPhotoUri;
        } else if (($changed & 3072) == 0) {
            initialPhotoUri2 = initialPhotoUri;
            $dirty |= $composer3.changed(initialPhotoUri2) ? 2048 : 1024;
        } else {
            initialPhotoUri2 = initialPhotoUri;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            isAdmin2 = isAdmin;
        } else if (($changed & 24576) == 0) {
            isAdmin2 = isAdmin;
            $dirty |= $composer3.changed(isAdmin2) ? 16384 : 8192;
        } else {
            isAdmin2 = isAdmin;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= 196608;
            str = currentPassword;
        } else if ((196608 & $changed) == 0) {
            str = currentPassword;
            $dirty |= $composer3.changed(str) ? 131072 : 65536;
        } else {
            str = currentPassword;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty |= 1572864;
            function12 = function1;
        } else if (($changed & 1572864) == 0) {
            function12 = function1;
            $dirty |= $composer3.changedInstance(function12) ? 1048576 : 524288;
        } else {
            function12 = function1;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty |= 12582912;
            i2 = i7;
        } else if (($changed & 12582912) == 0) {
            i2 = i7;
            $dirty |= $composer3.changedInstance(function0) ? 8388608 : 4194304;
        } else {
            i2 = i7;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 67108864 : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 536870912 : 268435456;
        }
        if (($dirty & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            initialPhotoUri3 = initialPhotoUri2;
            currentPassword3 = str;
            onDeleteAccount2 = function0;
            onUpdatePassword2 = function12;
            $composer2 = $composer3;
            isAdmin3 = isAdmin2;
        } else {
            if (i3 != 0) {
                initialPhotoUri2 = null;
            }
            if (i4 != 0) {
                isAdmin2 = true;
            }
            if (i5 == 0) {
                currentPassword2 = str;
            } else {
                currentPassword2 = "";
            }
            if (i6 == 0) {
                onUpdatePassword = function12;
            } else {
                onUpdatePassword = null;
            }
            if (i2 == 0) {
                onDeleteAccount = function0;
            } else {
                onDeleteAccount = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(389123906, $dirty, -1, "com.example.ui.components.EditAdminProfileDialog (CustomerWhatsAppDialogs.kt:1923)");
            }
            int i8 = ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty);
            String initialPhotoUri4 = initialPhotoUri2;
            boolean isAdmin4 = isAdmin2;
            Function1 onUpdatePassword3 = onUpdatePassword;
            EditUserProfileDialog(initialName, initialPhone, initialRole, initialPhotoUri4, isAdmin4, currentPassword2, onUpdatePassword3, onDeleteAccount, function4, function02, $composer3, i8, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            onDeleteAccount2 = onDeleteAccount;
            onUpdatePassword2 = onUpdatePassword3;
            currentPassword3 = currentPassword2;
            isAdmin3 = isAdmin4;
            initialPhotoUri3 = initialPhotoUri4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.CustomerWhatsAppDialogsKt$$ExternalSyntheticLambda40
                public final Object invoke(Object obj, Object obj2) {
                    return CustomerWhatsAppDialogsKt.EditAdminProfileDialog$lambda$297(initialName, initialPhone, initialRole, initialPhotoUri3, isAdmin3, currentPassword3, onUpdatePassword2, onDeleteAccount2, function4, function02, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
