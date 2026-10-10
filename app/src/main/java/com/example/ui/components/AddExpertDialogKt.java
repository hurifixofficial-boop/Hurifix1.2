package com.example.ui.components;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.widget.Toast;
import androidx.activity.compose.BackHandlerKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckCircleKt;
import androidx.compose.material.icons.filled.MyLocationKt;
import androidx.compose.material.icons.filled.PhoneKt;
import androidx.compose.material.icons.filled.PhotoCameraKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.ExposedDropdownMenuBoxScope;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.MenuItemColors;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.material3.TopAppBarScrollBehavior;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DoubleState;
import androidx.compose.runtime.MutableDoubleState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.example.data.model.ExpertEntity;
import com.example.util.LocationHelper;
import com.example.util.PhoneAuthManager;
import com.example.util.SoundHelper;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: AddExpertDialog.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0099\u0001\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000eH\u0007¢\u0006\u0002\u0010\u0012¨\u0006\u0013²\u0006\n\u0010\u0014\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010\u0017\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010\u0019\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010\u001c\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\bX\u008a\u008e\u0002²\u0006\f\u0010\u001e\u001a\u0004\u0018\u00010\u0006X\u008a\u008e\u0002²\u0006\n\u0010\u001f\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010 \u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010!\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\"\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010#\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010$\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010%\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010&\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010'\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010(\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\f\u0010)\u001a\u0004\u0018\u00010\u0006X\u008a\u008e\u0002²\u0006\n\u0010*\u001a\u00020\bX\u008a\u008e\u0002²\u0006\f\u0010+\u001a\u0004\u0018\u00010\u0006X\u008a\u008e\u0002²\u0006\n\u0010,\u001a\u00020\bX\u008a\u008e\u0002²\u0006\f\u0010-\u001a\u0004\u0018\u00010\u0006X\u008a\u008e\u0002²\u0006\n\u0010.\u001a\u00020\bX\u008a\u008e\u0002²\u0006\f\u0010/\u001a\u0004\u0018\u000100X\u008a\u008e\u0002²\u0006\n\u00101\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u00102\u001a\u00020\u0006X\u008a\u008e\u0002"}, d2 = {"AddExpertDialog", "", "initialExpert", "Lcom/example/data/model/ExpertEntity;", "availableCategories", "", "", "isAdmin", "", "currentUserId", "currentUserName", "currentUserDesignation", "existingPhones", "onAddNewCategory", "Lkotlin/Function1;", "onDismiss", "Lkotlin/Function0;", "onSave", "(Lcom/example/data/model/ExpertEntity;Ljava/util/List;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "app", "name", "phone", "category", "address", "rawLocation", "latitude", "", "longitude", "isAvailable", "isCategoryExpanded", "locationError", "isFetchingLocation", "showPermissionDeniedDialog", "showGpsDisabledDialog", "showSaveConfirmDialog", "isOtpSentInline", "inlineOtpInput", "isVerifiedInline", "showInlineOtpConfirmDialog", "isSendingInlineOtp", "activeVerificationIdInline", "inlineOtpError", "isInlineVerifying", "profilePicUrl", "isUploadingImage", "imageUploadError", "showImageSourcePicker", "tempCameraUri", "Landroid/net/Uri;", "showAddCategoryInlineDialog", "newCategoryInput"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class AddExpertDialogKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$238(ExpertEntity expertEntity, List list, boolean z, String str, String str2, String str3, List list2, Function1 function1, Function0 function0, Function1 function12, int i, int i2, Composer composer, int i3) {
        AddExpertDialog(expertEntity, list, z, str, str2, str3, list2, function1, function0, function12, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$1$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:390:0x0b6f, code lost:
    
        if (r10 == null) goto L406;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0474  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x04dc  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x05ab  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x05dc  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x060d  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x063d  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0671  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x069d  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0732  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0766  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x079e  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x07fa  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0845  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x088c  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x08c4  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x08fc  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0930  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x096b  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x09a4  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x09b4  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0a1d  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0a63  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0a74  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0ade  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0bc6  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0c1a  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0c7b  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0cb7  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0cfe  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0daf  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0e5f  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0e9b  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0eba  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0f62  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x102e  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x1207  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x115f  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x101a  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0f4b  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0eaa  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x0e76  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0e40  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0d9d  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0cc5  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0c88  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x0cea  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0be4  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x0b19  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x0b86 A[LOOP:1: B:382:0x0b33->B:395:0x0b86, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0b83 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0af3  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0a8d  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0a66  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x0a2b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:412:0x09c8  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x09a7  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x097c  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0947  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x090d  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x08d9  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x08a3  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0862  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x080f  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x07d9  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x07ab  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x077b  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0745  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x067e  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0650  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x05ed  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0589  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0304  */
    /* JADX WARN: Type inference failed for: r35v0 */
    /* JADX WARN: Type inference failed for: r35v1 */
    /* JADX WARN: Type inference failed for: r35v6 */
    /* JADX WARN: Type inference failed for: r91v2 */
    /* JADX WARN: Type inference failed for: r91v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r91v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AddExpertDialog(com.example.data.model.ExpertEntity r81, java.util.List<java.lang.String> r82, boolean r83, java.lang.String r84, java.lang.String r85, java.lang.String r86, java.util.List<java.lang.String> r87, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r88, final kotlin.jvm.functions.Function0<kotlin.Unit> r89, final kotlin.jvm.functions.Function1<? super com.example.data.model.ExpertEntity, kotlin.Unit> r90, androidx.compose.runtime.Composer r91, final int r92, final int r93) {
        /*
            Method dump skipped, instructions count: 4657
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.AddExpertDialogKt.AddExpertDialog(com.example.data.model.ExpertEntity, java.util.List, boolean, java.lang.String, java.lang.String, java.lang.String, java.util.List, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final String AddExpertDialog$lambda$6(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String AddExpertDialog$lambda$9(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String AddExpertDialog$lambda$12(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String AddExpertDialog$lambda$15(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String AddExpertDialog$lambda$18(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final double AddExpertDialog$lambda$21(MutableDoubleState $latitude$delegate) {
        return ((DoubleState) $latitude$delegate).getDoubleValue();
    }

    private static final double AddExpertDialog$lambda$24(MutableDoubleState $longitude$delegate) {
        return ((DoubleState) $longitude$delegate).getDoubleValue();
    }

    private static final boolean AddExpertDialog$lambda$27(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$28(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean AddExpertDialog$lambda$30(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$31(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String AddExpertDialog$lambda$33(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean AddExpertDialog$lambda$36(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$37(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean AddExpertDialog$lambda$39(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$40(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean AddExpertDialog$lambda$42(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$43(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean AddExpertDialog$lambda$45(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$46(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean AddExpertDialog$lambda$48(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$49(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String AddExpertDialog$lambda$51(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean AddExpertDialog$lambda$55(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$56(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean AddExpertDialog$lambda$58(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$59(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final void AddExpertDialog$lambda$62(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String AddExpertDialog$lambda$64(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String AddExpertDialog$lambda$67(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean AddExpertDialog$lambda$70(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$71(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String AddExpertDialog$lambda$73(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean AddExpertDialog$lambda$76(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AddExpertDialog$lambda$77(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String AddExpertDialog$lambda$79(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean AddExpertDialog$lambda$82(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$83(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final Uri AddExpertDialog$lambda$85(MutableState<Uri> mutableState) {
        return (Uri) ((State) mutableState).getValue();
    }

    private static final Uri AddExpertDialog$createCameraUri(Context context) {
        try {
            File tempFile = File.createTempFile("expert_photo_" + System.currentTimeMillis(), ".jpg", context.getCacheDir());
            return FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", tempFile);
        } catch (Exception e) {
            return null;
        }
    }

    private static final void AddExpertDialog$uploadPickedImage(CoroutineScope coroutineScope, MutableState<Boolean> mutableState, MutableState<String> mutableState2, Context context, ExpertEntity $initialExpert, MutableState<String> mutableState3, Uri uri) {
        AddExpertDialog$lambda$77(mutableState, true);
        mutableState2.setValue(null);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AddExpertDialogKt$AddExpertDialog$uploadPickedImage$1(context, uri, mutableState, $initialExpert, mutableState3, mutableState2, null), 3, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$88$lambda$87(MutableState $tempCameraUri$delegate, CoroutineScope $coroutineScope, MutableState $isUploadingImage$delegate, MutableState $imageUploadError$delegate, Context $context, ExpertEntity $initialExpert, MutableState $profilePicUrl$delegate, boolean success) {
        if (success && AddExpertDialog$lambda$85($tempCameraUri$delegate) != null) {
            Uri AddExpertDialog$lambda$85 = AddExpertDialog$lambda$85($tempCameraUri$delegate);
            Intrinsics.checkNotNull(AddExpertDialog$lambda$85);
            AddExpertDialog$uploadPickedImage($coroutineScope, $isUploadingImage$delegate, $imageUploadError$delegate, $context, $initialExpert, $profilePicUrl$delegate, AddExpertDialog$lambda$85);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$90$lambda$89(ManagedActivityResultLauncher $takePictureLauncher, Context $context, MutableState $tempCameraUri$delegate, boolean isGranted) {
        if (!isGranted) {
            Toast.makeText($context, "Camera permission is required to capture photo", 0).show();
        } else {
            Uri uri = AddExpertDialog$createCameraUri($context);
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
    public static final Unit AddExpertDialog$lambda$92$lambda$91(CoroutineScope $coroutineScope, MutableState $isUploadingImage$delegate, MutableState $imageUploadError$delegate, Context $context, ExpertEntity $initialExpert, MutableState $profilePicUrl$delegate, Uri uri) {
        if (uri != null) {
            AddExpertDialog$uploadPickedImage($coroutineScope, $isUploadingImage$delegate, $imageUploadError$delegate, $context, $initialExpert, $profilePicUrl$delegate, uri);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$98$lambda$97(Context $context, MutableState $showGpsDisabledDialog$delegate, final MutableState $isFetchingLocation$delegate, final MutableDoubleState $latitude$delegate, final MutableDoubleState $longitude$delegate, final MutableState $rawLocation$delegate, final MutableState $locationError$delegate, MutableState $showPermissionDeniedDialog$delegate, Map permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        boolean granted = Intrinsics.areEqual(permissions.get("android.permission.ACCESS_FINE_LOCATION"), true) || Intrinsics.areEqual(permissions.get("android.permission.ACCESS_COARSE_LOCATION"), true);
        if (granted) {
            if (!LocationHelper.INSTANCE.isLocationEnabled($context)) {
                AddExpertDialog$lambda$43($showGpsDisabledDialog$delegate, true);
            } else {
                AddExpertDialog$lambda$37($isFetchingLocation$delegate, true);
                LocationHelper.INSTANCE.fetchCurrentLocation($context, new Function1() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda79
                    public final Object invoke(Object obj) {
                        return AddExpertDialogKt.AddExpertDialog$lambda$98$lambda$97$lambda$95($isFetchingLocation$delegate, $latitude$delegate, $longitude$delegate, $rawLocation$delegate, $locationError$delegate, (Location) obj);
                    }
                }, new Function1() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda80
                    public final Object invoke(Object obj) {
                        return AddExpertDialogKt.AddExpertDialog$lambda$98$lambda$97$lambda$96($isFetchingLocation$delegate, $locationError$delegate, (String) obj);
                    }
                });
            }
        } else {
            AddExpertDialog$lambda$40($showPermissionDeniedDialog$delegate, true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$98$lambda$97$lambda$95(MutableState $isFetchingLocation$delegate, MutableDoubleState $latitude$delegate, MutableDoubleState $longitude$delegate, MutableState $rawLocation$delegate, MutableState $locationError$delegate, Location loc) {
        Intrinsics.checkNotNullParameter(loc, "loc");
        AddExpertDialog$lambda$37($isFetchingLocation$delegate, false);
        $latitude$delegate.setDoubleValue(loc.getLatitude());
        $longitude$delegate.setDoubleValue(loc.getLongitude());
        $rawLocation$delegate.setValue(loc.getLatitude() + ", " + loc.getLongitude());
        $locationError$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$98$lambda$97$lambda$96(MutableState $isFetchingLocation$delegate, MutableState $locationError$delegate, String err) {
        Intrinsics.checkNotNullParameter(err, "err");
        AddExpertDialog$lambda$37($isFetchingLocation$delegate, false);
        $locationError$delegate.setValue(err);
        return Unit.INSTANCE;
    }

    private static final void AddExpertDialog$handleLocationRequest(Context context, ManagedActivityResultLauncher<String[], Map<String, Boolean>> managedActivityResultLauncher, MutableState<Boolean> mutableState, final MutableState<Boolean> mutableState2, final MutableDoubleState latitude$delegate, final MutableDoubleState longitude$delegate, final MutableState<String> mutableState3, final MutableState<String> mutableState4) {
        if (!LocationHelper.INSTANCE.isLocationPermissionGranted(context)) {
            managedActivityResultLauncher.launch(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
        } else if (!LocationHelper.INSTANCE.isLocationEnabled(context)) {
            AddExpertDialog$lambda$43(mutableState, true);
        } else {
            AddExpertDialog$lambda$37(mutableState2, true);
            LocationHelper.INSTANCE.fetchCurrentLocation(context, new Function1() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda43
                public final Object invoke(Object obj) {
                    return AddExpertDialogKt.AddExpertDialog$handleLocationRequest$lambda$99(mutableState2, latitude$delegate, longitude$delegate, mutableState3, mutableState4, (Location) obj);
                }
            }, new Function1() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda45
                public final Object invoke(Object obj) {
                    return AddExpertDialogKt.AddExpertDialog$handleLocationRequest$lambda$100(mutableState2, mutableState4, (String) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$handleLocationRequest$lambda$99(MutableState $isFetchingLocation$delegate, MutableDoubleState $latitude$delegate, MutableDoubleState $longitude$delegate, MutableState $rawLocation$delegate, MutableState $locationError$delegate, Location loc) {
        Intrinsics.checkNotNullParameter(loc, "loc");
        AddExpertDialog$lambda$37($isFetchingLocation$delegate, false);
        $latitude$delegate.setDoubleValue(loc.getLatitude());
        $longitude$delegate.setDoubleValue(loc.getLongitude());
        $rawLocation$delegate.setValue(loc.getLatitude() + ", " + loc.getLongitude());
        $locationError$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$handleLocationRequest$lambda$100(MutableState $isFetchingLocation$delegate, MutableState $locationError$delegate, String err) {
        Intrinsics.checkNotNullParameter(err, "err");
        AddExpertDialog$lambda$37($isFetchingLocation$delegate, false);
        $locationError$delegate.setValue(err);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$102$lambda$101(Context $context, ManagedActivityResultLauncher $takePictureLauncher, ManagedActivityResultLauncher $cameraPermissionLauncher, MutableState $tempCameraUri$delegate) {
        boolean hasCameraPermission = ContextCompat.checkSelfPermission($context, "android.permission.CAMERA") == 0;
        if (hasCameraPermission) {
            Uri uri = AddExpertDialog$createCameraUri($context);
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
    public static final Unit AddExpertDialog$lambda$104$lambda$103(ManagedActivityResultLauncher $photoPickerLauncher) {
        $photoPickerLauncher.launch(PickVisualMediaRequestKt.PickVisualMediaRequest$default(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE, 0, false, (ActivityResultContracts.PickVisualMedia.DefaultTab) null, 14, (Object) null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$106$lambda$105(MutableState $showImageSourcePicker$delegate) {
        AddExpertDialog$lambda$83($showImageSourcePicker$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$108$lambda$107(MutableState $showPermissionDeniedDialog$delegate) {
        AddExpertDialog$lambda$40($showPermissionDeniedDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$111(final Context $context, final MutableState $showPermissionDeniedDialog$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C324@14250L134,324@14233L214:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1685168785, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous> (AddExpertDialog.kt:324)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -244530539, "CC(remember):AddExpertDialog.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($context);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda27
                    public final Object invoke() {
                        return AddExpertDialogKt.AddExpertDialog$lambda$111$lambda$110$lambda$109($context, $showPermissionDeniedDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.getLambda$543941983$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$111$lambda$110$lambda$109(Context $context, MutableState $showPermissionDeniedDialog$delegate) {
        AddExpertDialog$lambda$40($showPermissionDeniedDialog$delegate, false);
        LocationHelper.INSTANCE.openAppSettings($context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$114(final MutableState $showPermissionDeniedDialog$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C332@14530L38,332@14509L115:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(824395249, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous> (AddExpertDialog.kt:332)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1969853111, "CC(remember):AddExpertDialog.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda41
                    public final Object invoke() {
                        return AddExpertDialogKt.AddExpertDialog$lambda$114$lambda$113$lambda$112($showPermissionDeniedDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) obj, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.getLambda$325541038$app(), $composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$114$lambda$113$lambda$112(MutableState $showPermissionDeniedDialog$delegate) {
        AddExpertDialog$lambda$40($showPermissionDeniedDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$116$lambda$115(MutableState $showGpsDisabledDialog$delegate) {
        AddExpertDialog$lambda$43($showGpsDisabledDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$119(final Context $context, final MutableState $showGpsDisabledDialog$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C347@15223L134,347@15206L212:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1400032882, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous> (AddExpertDialog.kt:347)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1106834700, "CC(remember):AddExpertDialog.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($context);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda78
                    public final Object invoke() {
                        return AddExpertDialogKt.AddExpertDialog$lambda$119$lambda$118$lambda$117($context, $showGpsDisabledDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.getLambda$829077886$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$119$lambda$118$lambda$117(Context $context, MutableState $showGpsDisabledDialog$delegate) {
        AddExpertDialog$lambda$43($showGpsDisabledDialog$delegate, false);
        LocationHelper.INSTANCE.openLocationSettings($context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$122(final MutableState $showGpsDisabledDialog$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C355@15501L33,355@15480L110:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1109531152, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous> (AddExpertDialog.kt:355)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1107548945, "CC(remember):AddExpertDialog.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda46
                    public final Object invoke() {
                        return AddExpertDialogKt.AddExpertDialog$lambda$122$lambda$121$lambda$120($showGpsDisabledDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) obj, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.getLambda$610676941$app(), $composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$122$lambda$121$lambda$120(MutableState $showGpsDisabledDialog$delegate) {
        AddExpertDialog$lambda$43($showGpsDisabledDialog$delegate, false);
        return Unit.INSTANCE;
    }

    private static final boolean AddExpertDialog$lambda$124(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void AddExpertDialog$lambda$125(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String AddExpertDialog$lambda$127(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$130$lambda$129(MutableState $showAddCategoryInlineDialog$delegate) {
        AddExpertDialog$lambda$125($showAddCategoryInlineDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit AddExpertDialog$lambda$140(final androidx.compose.runtime.MutableState r54, androidx.compose.runtime.Composer r55, int r56) {
        /*
            Method dump skipped, instructions count: 533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.AddExpertDialogKt.AddExpertDialog$lambda$140(androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$140$lambda$139$lambda$138$lambda$137(MutableState $newCategoryInput$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $newCategoryInput$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$133(final Function1 $onAddNewCategory, final MutableState $newCategoryInput$delegate, final MutableState $category$delegate, final MutableState $showAddCategoryInlineDialog$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C384@16671L378,383@16633L486:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1114896979, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous> (AddExpertDialog.kt:383)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1969137945, "CC(remember):AddExpertDialog.kt#9igjgp");
            boolean changed = $composer.changed($onAddNewCategory);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda1
                    public final Object invoke() {
                        return AddExpertDialogKt.AddExpertDialog$lambda$133$lambda$132$lambda$131($onAddNewCategory, $newCategoryInput$delegate, $category$delegate, $showAddCategoryInlineDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.getLambda$1114213789$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$133$lambda$132$lambda$131(Function1 $onAddNewCategory, MutableState $newCategoryInput$delegate, MutableState $category$delegate, MutableState $showAddCategoryInlineDialog$delegate) {
        String trimmed = StringsKt.trim(AddExpertDialog$lambda$127($newCategoryInput$delegate)).toString();
        if (!StringsKt.isBlank(trimmed)) {
            $onAddNewCategory.invoke(trimmed);
            $category$delegate.setValue(trimmed);
            $newCategoryInput$delegate.setValue("");
            AddExpertDialog$lambda$125($showAddCategoryInlineDialog$delegate, false);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$136(final MutableState $showAddCategoryInlineDialog$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C398@17202L39,398@17181L116:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1394667055, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous> (AddExpertDialog.kt:398)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 245244790, "CC(remember):AddExpertDialog.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda31
                    public final Object invoke() {
                        return AddExpertDialogKt.AddExpertDialog$lambda$136$lambda$135$lambda$134($showAddCategoryInlineDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) obj, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.getLambda$895812844$app(), $composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$136$lambda$135$lambda$134(MutableState $showAddCategoryInlineDialog$delegate) {
        AddExpertDialog$lambda$125($showAddCategoryInlineDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$144$lambda$143(final Context $context, MutableState $showInlineOtpConfirmDialog$delegate, final MutableState $isSendingInlineOtp$delegate, MutableState $phone$delegate, final MutableState $isOtpSentInline$delegate, final MutableState $activeVerificationIdInline$delegate) {
        AddExpertDialog$lambda$59($showInlineOtpConfirmDialog$delegate, false);
        AddExpertDialog$lambda$62($isSendingInlineOtp$delegate, true);
        PhoneAuthManager.INSTANCE.sendOtp($context, $context instanceof Activity ? (Activity) $context : null, AddExpertDialog$lambda$9($phone$delegate), new Function2() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda33
            public final Object invoke(Object obj, Object obj2) {
                return AddExpertDialogKt.AddExpertDialog$lambda$144$lambda$143$lambda$141($context, $isSendingInlineOtp$delegate, $isOtpSentInline$delegate, $activeVerificationIdInline$delegate, (String) obj, (String) obj2);
            }
        }, new Function1() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda44
            public final Object invoke(Object obj) {
                return AddExpertDialogKt.AddExpertDialog$lambda$144$lambda$143$lambda$142($isSendingInlineOtp$delegate, (String) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$144$lambda$143$lambda$141(Context $context, MutableState $isSendingInlineOtp$delegate, MutableState $isOtpSentInline$delegate, MutableState $activeVerificationIdInline$delegate, String verId, String str) {
        Intrinsics.checkNotNullParameter(verId, "verId");
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        AddExpertDialog$lambda$62($isSendingInlineOtp$delegate, false);
        AddExpertDialog$lambda$49($isOtpSentInline$delegate, true);
        $activeVerificationIdInline$delegate.setValue(verId);
        Toast.makeText($context, "OTP Sent", 0).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$144$lambda$143$lambda$142(MutableState $isSendingInlineOtp$delegate, String str) {
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        AddExpertDialog$lambda$62($isSendingInlineOtp$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$146$lambda$145(MutableState $showInlineOtpConfirmDialog$delegate) {
        AddExpertDialog$lambda$59($showInlineOtpConfirmDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$153$lambda$152(ExpertEntity $initialExpert, String $currentUserId, String $currentUserName, String $currentUserDesignation, Function1 $onSave, MutableDoubleState $latitude$delegate, MutableDoubleState $longitude$delegate, MutableState $name$delegate, MutableState $phone$delegate, MutableState $category$delegate, MutableState $address$delegate, MutableState $profilePicUrl$delegate, MutableState $isAvailable$delegate, MutableState $showSaveConfirmDialog$delegate) {
        ExpertEntity expertEntity;
        double finalLat = !((AddExpertDialog$lambda$21($latitude$delegate) > 0.0d ? 1 : (AddExpertDialog$lambda$21($latitude$delegate) == 0.0d ? 0 : -1)) == 0) ? AddExpertDialog$lambda$21($latitude$delegate) : 28.5708d;
        double finalLng = !(AddExpertDialog$lambda$24($longitude$delegate) == 0.0d) ? AddExpertDialog$lambda$24($longitude$delegate) : 77.3261d;
        String str = "Local Area";
        if ($initialExpert == null) {
            String obj = StringsKt.trim(AddExpertDialog$lambda$6($name$delegate)).toString();
            String obj2 = StringsKt.trim(AddExpertDialog$lambda$9($phone$delegate)).toString();
            String AddExpertDialog$lambda$12 = AddExpertDialog$lambda$12($category$delegate);
            String obj3 = StringsKt.trim(AddExpertDialog$lambda$15($address$delegate)).toString();
            if (StringsKt.isBlank(obj3)) {
                obj3 = "Local Area";
            }
            String str2 = obj3;
            String AddExpertDialog$lambda$73 = AddExpertDialog$lambda$73($profilePicUrl$delegate);
            boolean AddExpertDialog$lambda$27 = AddExpertDialog$lambda$27($isAvailable$delegate);
            String str3 = $currentUserId;
            String str4 = null;
            if (StringsKt.isBlank(str3)) {
                str3 = null;
            }
            String str5 = str3;
            String str6 = $currentUserName;
            if (StringsKt.isBlank(str6)) {
                str6 = "Admin";
            }
            String str7 = str6;
            String str8 = $currentUserDesignation;
            if (!StringsKt.isBlank(str8)) {
                str4 = str8;
            }
            expertEntity = new ExpertEntity(0L, obj, obj2, AddExpertDialog$lambda$12, str2, finalLat, finalLng, AddExpertDialog$lambda$27, 0.0f, 0.0f, 0, 0, 0, false, false, null, str5, str7, str4, AddExpertDialog$lambda$73, System.currentTimeMillis(), 0L, 0L, false, 14745345, null);
        } else {
            expertEntity = $initialExpert;
        }
        String obj4 = StringsKt.trim(AddExpertDialog$lambda$6($name$delegate)).toString();
        String obj5 = StringsKt.trim(AddExpertDialog$lambda$9($phone$delegate)).toString();
        String AddExpertDialog$lambda$122 = AddExpertDialog$lambda$12($category$delegate);
        String obj6 = StringsKt.trim(AddExpertDialog$lambda$15($address$delegate)).toString();
        if (!StringsKt.isBlank(obj6)) {
            str = obj6;
        }
        ExpertEntity expert = ExpertEntity.copy$default(expertEntity, 0L, obj4, obj5, AddExpertDialog$lambda$122, str, finalLat, finalLng, AddExpertDialog$lambda$27($isAvailable$delegate), 0.0f, 0.0f, 0, 0, 0, false, false, null, null, null, null, AddExpertDialog$lambda$73($profilePicUrl$delegate), 0L, 0L, System.currentTimeMillis(), false, 12058369, null);
        $onSave.invoke(expert);
        AddExpertDialog$lambda$46($showSaveConfirmDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$155$lambda$154(MutableState $showSaveConfirmDialog$delegate) {
        AddExpertDialog$lambda$46($showSaveConfirmDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237(final Function0 $onDismiss, final ExpertEntity $initialExpert, final boolean $isTimeLocked, final MutableState $name$delegate, final MutableState $phone$delegate, final MutableState $isVerifiedInline$delegate, final MutableState $rawLocation$delegate, final MutableDoubleState $latitude$delegate, final MutableDoubleState $longitude$delegate, final MutableState $locationError$delegate, final MutableState $isUploadingImage$delegate, final MutableState $showSaveConfirmDialog$delegate, final boolean $isPhoneAlreadyRegistered, final MutableState $showImageSourcePicker$delegate, final MutableState $profilePicUrl$delegate, final MutableState $isOtpSentInline$delegate, final MutableState $inlineOtpInput$delegate, final MutableState $showInlineOtpConfirmDialog$delegate, final Context $context, final MutableState $isInlineVerifying$delegate, final MutableState $inlineOtpError$delegate, final MutableState $activeVerificationIdInline$delegate, final MutableState $isCategoryExpanded$delegate, final MutableState $category$delegate, final List $effectiveCategories, final MutableState $showAddCategoryInlineDialog$delegate, final MutableState $address$delegate, final ManagedActivityResultLauncher $locationPermissionLauncher, final MutableState $isFetchingLocation$delegate, final MutableState $showGpsDisabledDialog$delegate, final MutableState $isAvailable$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C476@20508L15,476@20496L27,479@20610L1380,508@22016L2380,561@24407L24210,477@20532L28085:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(927233090, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous> (AddExpertDialog.kt:476)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 2012769937, "CC(remember):AddExpertDialog.kt#9igjgp");
            boolean changed = $composer.changed($onDismiss);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda47
                    public final Object invoke() {
                        return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$157$lambda$156($onDismiss);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackHandlerKt.BackHandler(false, (Function0) obj, $composer, 0, 1);
            ScaffoldKt.Scaffold-TvnljyQ(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ComposableLambdaKt.rememberComposableLambda(1231428350, true, new Function2() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda48
                public final Object invoke(Object obj2, Object obj3) {
                    return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$161(ExpertEntity.this, $isTimeLocked, $onDismiss, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), ComposableLambdaKt.rememberComposableLambda(-944487971, true, new Function2() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda49
                public final Object invoke(Object obj2, Object obj3) {
                    return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$166($isTimeLocked, $onDismiss, $name$delegate, $phone$delegate, $isVerifiedInline$delegate, $rawLocation$delegate, $latitude$delegate, $longitude$delegate, $locationError$delegate, $isUploadingImage$delegate, $showSaveConfirmDialog$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, 0, 0L, 0L, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(1605560787, true, new Function3() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda50
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236($isTimeLocked, $isPhoneAlreadyRegistered, $initialExpert, $isUploadingImage$delegate, $showImageSourcePicker$delegate, $profilePicUrl$delegate, $name$delegate, $isVerifiedInline$delegate, $phone$delegate, $isOtpSentInline$delegate, $inlineOtpInput$delegate, $showInlineOtpConfirmDialog$delegate, $context, $isInlineVerifying$delegate, $inlineOtpError$delegate, $activeVerificationIdInline$delegate, $isCategoryExpanded$delegate, $category$delegate, $effectiveCategories, $showAddCategoryInlineDialog$delegate, $address$delegate, $locationError$delegate, $rawLocation$delegate, $latitude$delegate, $longitude$delegate, $locationPermissionLauncher, $isFetchingLocation$delegate, $showGpsDisabledDialog$delegate, $isAvailable$delegate, (PaddingValues) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306806, 504);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$157$lambda$156(Function0 $onDismiss) {
        $onDismiss.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$161(final ExpertEntity $initialExpert, final boolean $isTimeLocked, final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C481@20667L862,498@21568L206,504@21895L11,503@21823L135,480@20628L1348:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1231428350, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous> (AddExpertDialog.kt:480)");
            }
            AppBarKt.TopAppBar-GHTll3U(ComposableLambdaKt.rememberComposableLambda(1657840066, true, new Function2() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda28
                public final Object invoke(Object obj, Object obj2) {
                    return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$161$lambda$159(ExpertEntity.this, $isTimeLocked, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-472094396, true, new Function2() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda29
                public final Object invoke(Object obj, Object obj2) {
                    return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$161$lambda$160($onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function3) null, 0.0f, (WindowInsets) null, TopAppBarDefaults.INSTANCE.topAppBarColors-zjMxDiM(ColorSchemeKt.surfaceColorAtElevation-3ABfNKs(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable), Dp.constructor-impl(3)), 0L, 0L, 0L, 0L, $composer, TopAppBarDefaults.$stable << 15, 30), (TopAppBarScrollBehavior) null, $composer, 390, 186);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0135  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit AddExpertDialog$lambda$237$lambda$161$lambda$159(com.example.data.model.ExpertEntity r49, boolean r50, androidx.compose.runtime.Composer r51, int r52) {
        /*
            Method dump skipped, instructions count: 499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$161$lambda$159(com.example.data.model.ExpertEntity, boolean, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$161$lambda$160(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C499@21594L158:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-472094396, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:499)");
            }
            IconButtonKt.IconButton($onDismiss, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.getLambda$754189345$app(), $composer, 196608, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$166(final boolean $isTimeLocked, final Function0 $onDismiss, final MutableState $name$delegate, final MutableState $phone$delegate, final MutableState $isVerifiedInline$delegate, final MutableState $rawLocation$delegate, final MutableDoubleState $latitude$delegate, final MutableDoubleState $longitude$delegate, final MutableState $locationError$delegate, final MutableState $isUploadingImage$delegate, final MutableState $showSaveConfirmDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C513@22203L2179,509@22034L2348:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-944487971, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous> (AddExpertDialog.kt:509)");
            }
            SurfaceKt.Surface-T9BRK9s(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), (Shape) null, 0L, 0L, Dp.constructor-impl(4), Dp.constructor-impl(8), (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(-1099756254, true, new Function2() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda30
                public final Object invoke(Object obj, Object obj2) {
                    return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$166$lambda$165($isTimeLocked, $onDismiss, $name$delegate, $phone$delegate, $isVerifiedInline$delegate, $rawLocation$delegate, $latitude$delegate, $longitude$delegate, $locationError$delegate, $isUploadingImage$delegate, $showSaveConfirmDialog$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12804102, 78);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0265  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit AddExpertDialog$lambda$237$lambda$166$lambda$165(boolean r41, kotlin.jvm.functions.Function0 r42, androidx.compose.runtime.MutableState r43, androidx.compose.runtime.MutableState r44, androidx.compose.runtime.MutableState r45, androidx.compose.runtime.MutableState r46, androidx.compose.runtime.MutableDoubleState r47, androidx.compose.runtime.MutableDoubleState r48, androidx.compose.runtime.MutableState r49, androidx.compose.runtime.MutableState r50, final androidx.compose.runtime.MutableState r51, androidx.compose.runtime.Composer r52, int r53) {
        /*
            Method dump skipped, instructions count: 717
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$166$lambda$165(boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableDoubleState, androidx.compose.runtime.MutableDoubleState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$166$lambda$165$lambda$164$lambda$163$lambda$162(boolean $isSaveEnabled, MutableState $showSaveConfirmDialog$delegate) {
        if ($isSaveEnabled) {
            AddExpertDialog$lambda$46($showSaveConfirmDialog$delegate, true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x08c1  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x08d7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x08ec  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x08f1  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0910  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0a06  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0ac2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0bb3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0bcd  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0c3d  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0d21  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0d2d  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0d5e  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0dbd  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0dd3  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0dd8  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0df6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0e2f  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0f17  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x11a0  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x11ac  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x11e5  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x12bc  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x12c8  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x12ff  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x139d  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x1425  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x1491  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x13a0  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x1315  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x12ce  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x11fb  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x11b2  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x1104  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0dd5  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0d31  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x09a0  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x08ee  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x07a9  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x06f3  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x038b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0579  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x06e5  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x073f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x081e  */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r10v35 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit AddExpertDialog$lambda$237$lambda$236(final boolean r160, final boolean r161, final com.example.data.model.ExpertEntity r162, final androidx.compose.runtime.MutableState r163, final androidx.compose.runtime.MutableState r164, final androidx.compose.runtime.MutableState r165, final androidx.compose.runtime.MutableState r166, final androidx.compose.runtime.MutableState r167, final androidx.compose.runtime.MutableState r168, final androidx.compose.runtime.MutableState r169, final androidx.compose.runtime.MutableState r170, final androidx.compose.runtime.MutableState r171, final android.content.Context r172, final androidx.compose.runtime.MutableState r173, final androidx.compose.runtime.MutableState r174, final androidx.compose.runtime.MutableState r175, androidx.compose.runtime.MutableState r176, final androidx.compose.runtime.MutableState r177, final java.util.List r178, final androidx.compose.runtime.MutableState r179, final androidx.compose.runtime.MutableState r180, final androidx.compose.runtime.MutableState r181, final androidx.compose.runtime.MutableState r182, final androidx.compose.runtime.MutableDoubleState r183, final androidx.compose.runtime.MutableDoubleState r184, final androidx.activity.compose.ManagedActivityResultLauncher r185, final androidx.compose.runtime.MutableState r186, final androidx.compose.runtime.MutableState r187, final androidx.compose.runtime.MutableState r188, androidx.compose.foundation.layout.PaddingValues r189, androidx.compose.runtime.Composer r190, int r191) {
        /*
            Method dump skipped, instructions count: 5271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236(boolean, boolean, com.example.data.model.ExpertEntity, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, android.content.Context, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableDoubleState, androidx.compose.runtime.MutableDoubleState, androidx.activity.compose.ManagedActivityResultLauncher, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$181$lambda$174$lambda$168$lambda$167(MutableState $showImageSourcePicker$delegate) {
        AddExpertDialog$lambda$83($showImageSourcePicker$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:52:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x034c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$181$lambda$174$lambda$171(androidx.compose.runtime.MutableState r53, androidx.compose.runtime.MutableState r54, androidx.compose.runtime.MutableState r55, androidx.compose.runtime.Composer r56, int r57) {
        /*
            Method dump skipped, instructions count: 952
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$181$lambda$174$lambda$171(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$181$lambda$174$lambda$173$lambda$172(MutableState $showImageSourcePicker$delegate) {
        AddExpertDialog$lambda$83($showImageSourcePicker$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$181$lambda$180$lambda$176$lambda$175(MutableState $showImageSourcePicker$delegate) {
        AddExpertDialog$lambda$83($showImageSourcePicker$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$181$lambda$180$lambda$177(MutableState $isUploadingImage$delegate, MutableState $profilePicUrl$delegate, RowScope $this$OutlinedButton, Composer $composer, int $changed) {
        String str;
        Intrinsics.checkNotNullParameter($this$OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C669@30117L91,670@30241L28,671@30302L328:AddExpertDialog.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-108335440, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:669)");
            }
            IconKt.Icon-ww6aTOc(PhotoCameraKt.getPhotoCamera(Icons.INSTANCE.getDefault()), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), $composer, 6);
            if (AddExpertDialog$lambda$76($isUploadingImage$delegate)) {
                str = "Uploading...";
            } else {
                String AddExpertDialog$lambda$73 = AddExpertDialog$lambda$73($profilePicUrl$delegate);
                str = AddExpertDialog$lambda$73 == null || StringsKt.isBlank(AddExpertDialog$lambda$73) ? "Upload Profile Picture" : "Change Picture";
            }
            TextKt.Text--4IGK_g(str, (Modifier) null, 0L, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$181$lambda$180$lambda$179$lambda$178(MutableState $profilePicUrl$delegate) {
        $profilePicUrl$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$183$lambda$182(boolean $isTimeLocked, MutableState $name$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!$isTimeLocked) {
            $name$delegate.setValue(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$187$lambda$186(boolean r18, com.example.data.model.ExpertEntity r19, androidx.compose.runtime.MutableState r20, androidx.compose.runtime.MutableState r21, androidx.compose.runtime.MutableState r22, androidx.compose.runtime.MutableState r23, java.lang.String r24) {
        /*
            r0 = r21
            java.lang.String r1 = "input"
            r2 = r24
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            if (r18 != 0) goto Lb1
            r1 = r24
            r3 = 0
            r4 = r1
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.Appendable r5 = (java.lang.Appendable) r5
            r6 = 0
            r7 = 0
            int r8 = r4.length()
        L1e:
            if (r7 >= r8) goto L32
            char r9 = r4.charAt(r7)
            r10 = r9
            r11 = 0
            boolean r12 = java.lang.Character.isDigit(r10)
            if (r12 == 0) goto L2f
            r5.append(r9)
        L2f:
            int r7 = r7 + 1
            goto L1e
        L32:
            r4 = r5
            java.lang.StringBuilder r4 = (java.lang.StringBuilder) r4
            java.lang.String r1 = r4.toString()
            r3 = 10
            java.lang.String r1 = kotlin.text.StringsKt.take(r1, r3)
            r4 = r20
            AddExpertDialog$lambda$10(r4, r1)
            r5 = 0
            r6 = 1
            if (r19 == 0) goto L95
            java.lang.String r7 = r19.getPhone()
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r7 = kotlin.text.StringsKt.isBlank(r7)
            if (r7 != 0) goto L95
            java.lang.String r7 = r19.getPhone()
            r8 = 0
            r9 = r7
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.Appendable r10 = (java.lang.Appendable) r10
            r11 = 0
            r12 = 0
            int r13 = r9.length()
        L6a:
            if (r12 >= r13) goto L7f
            char r14 = r9.charAt(r12)
            r15 = r14
            r16 = 0
            boolean r17 = java.lang.Character.isDigit(r15)
            if (r17 == 0) goto L7c
            r10.append(r14)
        L7c:
            int r12 = r12 + 1
            goto L6a
        L7f:
            r9 = r10
            java.lang.StringBuilder r9 = (java.lang.StringBuilder) r9
            java.lang.String r7 = r9.toString()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r7)
            if (r7 == 0) goto L95
            int r7 = r1.length()
            if (r7 != r3) goto L95
            r3 = r6
            goto L96
        L95:
            r3 = r5
        L96:
            if (r3 == 0) goto La1
            AddExpertDialog$lambda$56(r0, r6)
            r6 = r22
            r7 = r23
            goto Lb7
        La1:
            AddExpertDialog$lambda$56(r0, r5)
            r6 = r22
            AddExpertDialog$lambda$49(r6, r5)
            java.lang.String r5 = ""
            r7 = r23
            AddExpertDialog$lambda$52(r7, r5)
            goto Lb7
        Lb1:
            r4 = r20
            r6 = r22
            r7 = r23
        Lb7:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$187$lambda$186(boolean, com.example.data.model.ExpertEntity, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, java.lang.String):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$188(boolean $isPhoneFullyVerified, Composer $composer, int $changed) {
        long j;
        ComposerKt.sourceInformation($composer, "C732@33374L277:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1826877672, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:732)");
            }
            ImageVector phone = PhoneKt.getPhone(Icons.INSTANCE.getDefault());
            if ($isPhoneFullyVerified) {
                $composer.startReplaceGroup(1012188105);
                $composer.endReplaceGroup();
                j = ColorKt.Color(4281236786L);
            } else {
                $composer.startReplaceGroup(1012189672);
                ComposerKt.sourceInformation($composer, "735@33597L11");
                j = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU();
                $composer.endReplaceGroup();
            }
            IconKt.Icon-ww6aTOc(phone, (String) null, (Modifier) null, j, $composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$192(boolean $isTimeLocked, boolean $isPhoneFullyVerified, boolean $isPhoneAlreadyRegistered, MutableState $phone$delegate, final MutableState $showInlineOtpConfirmDialog$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-970227529, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:739)");
            }
            if (!$isTimeLocked && !$isPhoneFullyVerified) {
                $composer.startReplaceGroup(1081679387);
                ComposerKt.sourceInformation($composer, "742@33963L37,745@34177L362,741@33909L630");
                final boolean isPhoneComplete = AddExpertDialog$lambda$9($phone$delegate).length() == 10 && !$isPhoneAlreadyRegistered;
                Modifier testTag = TestTagKt.testTag(Modifier.Companion, "inline_send_otp_button");
                ComposerKt.sourceInformationMarkerStart($composer, -380744036, "CC(remember):AddExpertDialog.kt#9igjgp");
                Object rememberedValue = $composer.rememberedValue();
                if (rememberedValue == Composer.Companion.getEmpty()) {
                    obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda0
                        public final Object invoke() {
                            return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$192$lambda$190$lambda$189($showInlineOtpConfirmDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.TextButton((Function0) obj, testTag, isPhoneComplete, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(439984223, true, new Function3() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda11
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$192$lambda$191(isPhoneComplete, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, $composer, 54), $composer, 805306422, 504);
                $composer.endReplaceGroup();
            } else {
                if ($isPhoneFullyVerified) {
                    $composer.startReplaceGroup(1082473421);
                    ComposerKt.sourceInformation($composer, "753@34627L230");
                    IconKt.Icon-ww6aTOc(CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), "Verified", (Modifier) null, ColorKt.Color(4281236786L), $composer, 3120, 4);
                } else {
                    $composer.startReplaceGroup(1048143339);
                }
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$192$lambda$190$lambda$189(MutableState $showInlineOtpConfirmDialog$delegate) {
        AddExpertDialog$lambda$59($showInlineOtpConfirmDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$192$lambda$191(boolean $isPhoneComplete, RowScope $this$TextButton, Composer $composer, int $changed) {
        long j;
        Intrinsics.checkNotNullParameter($this$TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C746@34211L298:AddExpertDialog.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(439984223, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:746)");
            }
            FontWeight bold = FontWeight.Companion.getBold();
            if ($isPhoneComplete) {
                $composer.startReplaceGroup(1918051366);
                ComposerKt.sourceInformation($composer, "749@34417L11");
                j = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary-0d7_KjU();
            } else {
                $composer.startReplaceGroup(1918052614);
                ComposerKt.sourceInformation($composer, "749@34456L11");
                j = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutline-0d7_KjU();
            }
            $composer.endReplaceGroup();
            TextKt.Text--4IGK_g("Send OTP", (Modifier) null, j, 0L, (FontStyle) null, bold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196614, 0, 131034);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$193(boolean $isPhoneAlreadyRegistered, boolean $isPhoneFullyVerified, MutableState $phone$delegate, Composer $composer, int $changed) {
        Composer composer = $composer;
        ComposerKt.sourceInformation(composer, "C:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1284111769, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:777)");
            }
            if ((AddExpertDialog$lambda$9($phone$delegate).length() > 0) && AddExpertDialog$lambda$9($phone$delegate).length() < 10) {
                composer.startReplaceGroup(300069657);
                ComposerKt.sourceInformation(composer, "778@35960L11,778@35904L74");
                TextKt.Text--4IGK_g(AddExpertDialog$lambda$9($phone$delegate).length() + "/10 digits", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131066);
                $composer.endReplaceGroup();
            } else {
                if (AddExpertDialog$lambda$9($phone$delegate).length() == 10) {
                    composer.startReplaceGroup(300243691);
                    ComposerKt.sourceInformation(composer, "");
                    if ($isPhoneAlreadyRegistered) {
                        composer.startReplaceGroup(300289354);
                        ComposerKt.sourceInformation(composer, "781@36193L11,781@36128L113");
                        TextKt.Text--4IGK_g("❌ Mobile number already registered", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196614, 0, 131034);
                        composer = $composer;
                        composer.endReplaceGroup();
                    } else if ($isPhoneFullyVerified) {
                        composer.startReplaceGroup(300496124);
                        ComposerKt.sourceInformation(composer, "783@36337L95");
                        TextKt.Text--4IGK_g("✓ Mobile Verified Successfully", (Modifier) null, ColorKt.Color(4281236786L), 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196998, 0, 131034);
                        composer = $composer;
                        composer.endReplaceGroup();
                    } else {
                        composer.startReplaceGroup(300659959);
                        ComposerKt.sourceInformation(composer, "785@36584L11,785@36502L100");
                        TextKt.Text--4IGK_g("10/10 digits (Verification Required - Tap Send OTP)", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 6, 0, 131066);
                        composer = $composer;
                        composer.endReplaceGroup();
                    }
                } else {
                    composer.startReplaceGroup(264477627);
                }
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$196$lambda$195(MutableState $inlineOtpInput$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = it;
        Appendable sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isDigit(charAt)) {
                sb.append(charAt);
            }
        }
        $inlineOtpInput$delegate.setValue(StringsKt.take(((StringBuilder) sb).toString(), 6));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$201(final Context $context, final MutableState $isInlineVerifying$delegate, final MutableState $inlineOtpInput$delegate, final MutableState $inlineOtpError$delegate, final MutableState $phone$delegate, final MutableState $activeVerificationIdInline$delegate, final MutableState $isVerifiedInline$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-397825317, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:804)");
            }
            if (AddExpertDialog$lambda$70($isInlineVerifying$delegate)) {
                $composer.startReplaceGroup(-1799192103);
                ComposerKt.sourceInformation($composer, "805@37640L78");
                ProgressIndicatorKt.CircularProgressIndicator-LxG7B9w(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), 0L, Dp.constructor-impl(2), 0L, 0, $composer, 390, 26);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1798996679);
                ComposerKt.sourceInformation($composer, "808@37846L1270,807@37788L1646");
                boolean z = AddExpertDialog$lambda$51($inlineOtpInput$delegate).length() == 6;
                Modifier testTag = TestTagKt.testTag(Modifier.Companion, "inline_verify_otp_button");
                ComposerKt.sourceInformationMarkerStart($composer, -58029647, "CC(remember):AddExpertDialog.kt#9igjgp");
                boolean changedInstance = $composer.changedInstance($context);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                    obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda22
                        public final Object invoke() {
                            return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$201$lambda$200$lambda$199($context, $isInlineVerifying$delegate, $inlineOtpError$delegate, $phone$delegate, $activeVerificationIdInline$delegate, $inlineOtpInput$delegate, $isVerifiedInline$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.TextButton((Function0) obj, testTag, z, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.getLambda$32506202$app(), $composer, 805306416, 504);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$201$lambda$200$lambda$199(final Context $context, final MutableState $isInlineVerifying$delegate, final MutableState $inlineOtpError$delegate, MutableState $phone$delegate, MutableState $activeVerificationIdInline$delegate, MutableState $inlineOtpInput$delegate, final MutableState $isVerifiedInline$delegate) {
        AddExpertDialog$lambda$71($isInlineVerifying$delegate, true);
        $inlineOtpError$delegate.setValue(null);
        PhoneAuthManager.INSTANCE.verifyOtp($context, AddExpertDialog$lambda$9($phone$delegate), AddExpertDialog$lambda$64($activeVerificationIdInline$delegate), AddExpertDialog$lambda$51($inlineOtpInput$delegate), new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda66
            public final Object invoke() {
                return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$201$lambda$200$lambda$199$lambda$197($context, $isInlineVerifying$delegate, $isVerifiedInline$delegate);
            }
        }, new Function1() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda77
            public final Object invoke(Object obj) {
                return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$201$lambda$200$lambda$199$lambda$198($isInlineVerifying$delegate, $inlineOtpError$delegate, (String) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$201$lambda$200$lambda$199$lambda$197(Context $context, MutableState $isInlineVerifying$delegate, MutableState $isVerifiedInline$delegate) {
        AddExpertDialog$lambda$71($isInlineVerifying$delegate, false);
        AddExpertDialog$lambda$56($isVerifiedInline$delegate, true);
        Toast.makeText($context, "Phone Verified Successfully", 0).show();
        SoundHelper.INSTANCE.playSFX("success");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$201$lambda$200$lambda$199$lambda$198(MutableState $isInlineVerifying$delegate, MutableState $inlineOtpError$delegate, String str) {
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        AddExpertDialog$lambda$71($isInlineVerifying$delegate, false);
        $inlineOtpError$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$202(MutableState $phone$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C836@39648L11,836@39563L114:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1452244107, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:836)");
            }
            TextKt.Text--4IGK_g("Enter the 6-digit verification code sent to +91 " + AddExpertDialog$lambda$9($phone$delegate), (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$204$lambda$203(boolean $isTimeLocked, MutableState $isCategoryExpanded$delegate, boolean it) {
        if (!$isTimeLocked) {
            AddExpertDialog$lambda$31($isCategoryExpanded$delegate, !AddExpertDialog$lambda$30($isCategoryExpanded$delegate));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217(final boolean $isTimeLocked, final MutableState $category$delegate, final MutableState $isCategoryExpanded$delegate, final List $effectiveCategories, final MutableState $showAddCategoryInlineDialog$delegate, ExposedDropdownMenuBoxScope $this$ExposedDropdownMenuBox, Composer $composer, int $changed) {
        Object obj;
        Object obj2;
        Intrinsics.checkNotNullParameter($this$ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
        ComposerKt.sourceInformation($composer, "C848@40150L2,852@40345L94,846@40049L543:AddExpertDialog.kt#qonjpd");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer.changed($this$ExposedDropdownMenuBox) : $composer.changedInstance($this$ExposedDropdownMenuBox) ? 4 : 2;
        }
        if (($dirty & 19) == 18 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(310879, $dirty, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:846)");
            }
            int $dirty2 = $dirty;
            String AddExpertDialog$lambda$12 = AddExpertDialog$lambda$12($category$delegate);
            boolean z = !$isTimeLocked;
            Modifier menuAnchor = $this$ExposedDropdownMenuBox.menuAnchor(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null));
            ComposerKt.sourceInformationMarkerStart($composer, 298758657, "CC(remember):AddExpertDialog.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function1() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda36
                    public final Object invoke(Object obj3) {
                        return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$206$lambda$205((String) obj3);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(AddExpertDialog$lambda$12, (Function1) obj, menuAnchor, z, true, (TextStyle) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.m30getLambda$2352391$app(), (Function2) null, (Function2) null, ComposableLambdaKt.rememberComposableLambda(-1293391210, true, new Function2() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda37
                public final Object invoke(Object obj3, Object obj4) {
                    return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$207($isTimeLocked, $isCategoryExpanded$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 806903856, 0, 0, 8388000);
            if (!$isTimeLocked) {
                $composer.startReplaceGroup(672125936);
                ComposerKt.sourceInformation($composer, "861@40786L30,862@40843L1803,859@40659L1987");
                boolean AddExpertDialog$lambda$30 = AddExpertDialog$lambda$30($isCategoryExpanded$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, 298779037, "CC(remember):AddExpertDialog.kt#9igjgp");
                Object rememberedValue2 = $composer.rememberedValue();
                if (rememberedValue2 == Composer.Companion.getEmpty()) {
                    obj2 = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda38
                        public final Object invoke() {
                            return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$209$lambda$208($isCategoryExpanded$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                } else {
                    obj2 = rememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                $this$ExposedDropdownMenuBox.ExposedDropdownMenu-vNxi1II(AddExpertDialog$lambda$30, (Function0) obj2, (Modifier) null, (ScrollState) null, false, (Shape) null, 0L, 0.0f, 0.0f, (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(-1274699080, true, new Function3() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda39
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$216($effectiveCategories, $category$delegate, $isCategoryExpanded$delegate, $showAddCategoryInlineDialog$delegate, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, $composer, 54), $composer, 48, (ExposedDropdownMenuBoxScope.$stable << 3) | 6 | (($dirty2 << 3) & 112), 1020);
            } else {
                $composer.startReplaceGroup(631753923);
            }
            $composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$206$lambda$205(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$207(boolean $isTimeLocked, MutableState $isCategoryExpanded$delegate, Composer $composer, int $changed) {
        Composer $composer2;
        ComposerKt.sourceInformation($composer, "C:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1293391210, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:852)");
            }
            if ($isTimeLocked) {
                $composer2 = $composer;
                $composer2.startReplaceGroup(-1305947924);
            } else {
                $composer.startReplaceGroup(-1703402655);
                ComposerKt.sourceInformation($composer, "852@40394L43");
                $composer2 = $composer;
                ExposedDropdownMenuDefaults.INSTANCE.TrailingIcon(AddExpertDialog$lambda$30($isCategoryExpanded$delegate), (Modifier) null, $composer2, ExposedDropdownMenuDefaults.$stable << 6, 2);
            }
            $composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$209$lambda$208(MutableState $isCategoryExpanded$delegate) {
        AddExpertDialog$lambda$31($isCategoryExpanded$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$216(List $effectiveCategories, final MutableState $category$delegate, final MutableState $isCategoryExpanded$delegate, final MutableState $showAddCategoryInlineDialog$delegate, ColumnScope $this$ExposedDropdownMenu, Composer $composer, int $changed) {
        Object obj;
        Object obj2;
        Composer composer = $composer;
        Intrinsics.checkNotNullParameter($this$ExposedDropdownMenu, "$this$ExposedDropdownMenu");
        ComposerKt.sourceInformation(composer, "C873@41319L19,893@42421L169,875@41368L1252:AddExpertDialog.kt#qonjpd");
        if (($changed & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1274699080, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:863)");
            }
            composer.startReplaceGroup(-920134524);
            ComposerKt.sourceInformation(composer, "*865@41003L13,866@41064L161,864@40942L317");
            Iterator it = $effectiveCategories.iterator();
            while (it.hasNext()) {
                final String str = (String) it.next();
                Function2 rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1320425160, true, new Function2() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda32
                    public final Object invoke(Object obj3, Object obj4) {
                        return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$216$lambda$213$lambda$210(str, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composer, 54);
                ComposerKt.sourceInformationMarkerStart(composer, 879006217, "CC(remember):AddExpertDialog.kt#9igjgp");
                boolean changed = composer.changed(str);
                Object rememberedValue = $composer.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                    obj2 = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda34
                        public final Object invoke() {
                            return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$216$lambda$213$lambda$212$lambda$211(str, $category$delegate, $isCategoryExpanded$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                } else {
                    obj2 = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                AndroidMenu_androidKt.DropdownMenuItem(rememberComposableLambda, (Function0) obj2, (Modifier) null, (Function2) null, (Function2) null, false, (MenuItemColors) null, (PaddingValues) null, (MutableInteractionSource) null, composer, 6, 508);
                composer = $composer;
            }
            $composer.endReplaceGroup();
            DividerKt.HorizontalDivider-9IZ8Weo((Modifier) null, 0.0f, 0L, $composer, 0, 7);
            Function2<Composer, Integer, Unit> lambda$1083702920$app = ComposableSingletons$AddExpertDialogKt.INSTANCE.getLambda$1083702920$app();
            ComposerKt.sourceInformationMarkerStart($composer, -920085855, "CC(remember):AddExpertDialog.kt#9igjgp");
            Object rememberedValue2 = $composer.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda35
                    public final Object invoke() {
                        return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$216$lambda$215$lambda$214($isCategoryExpanded$delegate, $showAddCategoryInlineDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            AndroidMenu_androidKt.DropdownMenuItem(lambda$1083702920$app, (Function0) obj, (Modifier) null, (Function2) null, (Function2) null, false, (MenuItemColors) null, (PaddingValues) null, (MutableInteractionSource) null, $composer, 54, 508);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$216$lambda$213$lambda$210(String $cat, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C865@41005L9:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1320425160, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:865)");
            }
            TextKt.Text--4IGK_g($cat, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$216$lambda$213$lambda$212$lambda$211(String $cat, MutableState $category$delegate, MutableState $isCategoryExpanded$delegate) {
        $category$delegate.setValue($cat);
        AddExpertDialog$lambda$31($isCategoryExpanded$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$217$lambda$216$lambda$215$lambda$214(MutableState $isCategoryExpanded$delegate, MutableState $showAddCategoryInlineDialog$delegate) {
        AddExpertDialog$lambda$31($isCategoryExpanded$delegate, false);
        AddExpertDialog$lambda$125($showAddCategoryInlineDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$219$lambda$218(boolean $isTimeLocked, MutableState $address$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!$isTimeLocked) {
            $address$delegate.setValue(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$230$lambda$221$lambda$220(boolean $isTimeLocked, MutableState $rawLocation$delegate, MutableDoubleState $latitude$delegate, MutableDoubleState $longitude$delegate, MutableState $locationError$delegate, String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (!$isTimeLocked) {
            $rawLocation$delegate.setValue(input);
            Pair parsed = LocationHelper.INSTANCE.parseCoordinatesFromText(input);
            if (parsed != null) {
                $latitude$delegate.setDoubleValue(((Number) parsed.getFirst()).doubleValue());
                $longitude$delegate.setDoubleValue(((Number) parsed.getSecond()).doubleValue());
                $locationError$delegate.setValue(null);
            } else {
                $locationError$delegate.setValue("Enter valid Lat, Lng or Google Maps link");
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$230$lambda$224(boolean $isTimeLocked, final Context $context, final ManagedActivityResultLauncher $locationPermissionLauncher, final MutableState $isFetchingLocation$delegate, final MutableState $showGpsDisabledDialog$delegate, final MutableDoubleState $latitude$delegate, final MutableDoubleState $longitude$delegate, final MutableState $rawLocation$delegate, final MutableState $locationError$delegate, Composer $composer, int $changed) {
        Object obj;
        Composer composer = $composer;
        ComposerKt.sourceInformation(composer, "C:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(268865453, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:938)");
            }
            if ($isTimeLocked) {
                composer.startReplaceGroup(-387671851);
            } else {
                composer.startReplaceGroup(-343220269);
                ComposerKt.sourceInformation(composer, "");
                if (AddExpertDialog$lambda$36($isFetchingLocation$delegate)) {
                    composer.startReplaceGroup(-343185921);
                    ComposerKt.sourceInformation(composer, "940@44877L78");
                    ProgressIndicatorKt.CircularProgressIndicator-LxG7B9w(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), 0L, Dp.constructor-impl(2), 0L, 0, $composer, 390, 26);
                    composer = $composer;
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(-343015545);
                    ComposerKt.sourceInformation(composer, "943@45095L27,942@45033L582");
                    ComposerKt.sourceInformationMarkerStart(composer, 681674216, "CC(remember):AddExpertDialog.kt#9igjgp");
                    boolean changedInstance = composer.changedInstance($context) | composer.changedInstance($locationPermissionLauncher);
                    Object rememberedValue = $composer.rememberedValue();
                    if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                        obj = new Function0() { // from class: com.example.ui.components.AddExpertDialogKt$$ExternalSyntheticLambda55
                            public final Object invoke() {
                                return AddExpertDialogKt.AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$230$lambda$224$lambda$223$lambda$222($context, $locationPermissionLauncher, $showGpsDisabledDialog$delegate, $isFetchingLocation$delegate, $latitude$delegate, $longitude$delegate, $rawLocation$delegate, $locationError$delegate);
                            }
                        };
                        $composer.updateRememberedValue(obj);
                    } else {
                        obj = rememberedValue;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    IconButtonKt.IconButton((Function0) obj, TestTagKt.testTag(Modifier.Companion, "expert_gps_fetch_button"), false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$AddExpertDialogKt.INSTANCE.m36getLambda$793898607$app(), composer, 196656, 28);
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
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$230$lambda$224$lambda$223$lambda$222(Context $context, ManagedActivityResultLauncher $locationPermissionLauncher, MutableState $showGpsDisabledDialog$delegate, MutableState $isFetchingLocation$delegate, MutableDoubleState $latitude$delegate, MutableDoubleState $longitude$delegate, MutableState $rawLocation$delegate, MutableState $locationError$delegate) {
        AddExpertDialog$handleLocationRequest($context, $locationPermissionLauncher, $showGpsDisabledDialog$delegate, $isFetchingLocation$delegate, $latitude$delegate, $longitude$delegate, $rawLocation$delegate, $locationError$delegate);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$230$lambda$225(boolean $isTimeLocked, MutableState $locationError$delegate, MutableState $rawLocation$delegate, MutableDoubleState $latitude$delegate, MutableDoubleState $longitude$delegate, Composer $composer, int $changed) {
        Composer composer = $composer;
        ComposerKt.sourceInformation(composer, "C:AddExpertDialog.kt#qonjpd");
        if (($changed & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2015140189, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:957)");
            }
            if ($isTimeLocked) {
                composer.startReplaceGroup(-2056944443);
            } else {
                composer.startReplaceGroup(-2011425562);
                ComposerKt.sourceInformation(composer, "");
                if (AddExpertDialog$lambda$33($locationError$delegate) != null) {
                    composer.startReplaceGroup(-2011379713);
                    ComposerKt.sourceInformation(composer, "959@46009L11,959@45965L62");
                    String AddExpertDialog$lambda$33 = AddExpertDialog$lambda$33($locationError$delegate);
                    Intrinsics.checkNotNull(AddExpertDialog$lambda$33);
                    TextKt.Text--4IGK_g(AddExpertDialog$lambda$33, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131066);
                    composer = $composer;
                    composer.endReplaceGroup();
                } else {
                    if (!StringsKt.isBlank(AddExpertDialog$lambda$18($rawLocation$delegate))) {
                        if (!(AddExpertDialog$lambda$21($latitude$delegate) == 0.0d)) {
                            if (!(AddExpertDialog$lambda$24($longitude$delegate) == 0.0d)) {
                                composer.startReplaceGroup(-2011010379);
                                ComposerKt.sourceInformation(composer, "963@46337L72");
                                TextKt.Text--4IGK_g("✓ Coordinates configured successfully", (Modifier) null, ColorKt.Color(4281236786L), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 390, 0, 131066);
                                composer = $composer;
                                composer.endReplaceGroup();
                            }
                        }
                    }
                    composer.startReplaceGroup(-2011174555);
                    ComposerKt.sourceInformation(composer, "961@46241L11,961@46171L88");
                    TextKt.Text--4IGK_g("Coordinates are compulsory (* Required)", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 6, 0, 131066);
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
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$230$lambda$229$lambda$227$lambda$226(Context $context, ManagedActivityResultLauncher $locationPermissionLauncher, MutableState $showGpsDisabledDialog$delegate, MutableState $isFetchingLocation$delegate, MutableDoubleState $latitude$delegate, MutableDoubleState $longitude$delegate, MutableState $rawLocation$delegate, MutableState $locationError$delegate) {
        AddExpertDialog$handleLocationRequest($context, $locationPermissionLauncher, $showGpsDisabledDialog$delegate, $isFetchingLocation$delegate, $latitude$delegate, $longitude$delegate, $rawLocation$delegate, $locationError$delegate);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$230$lambda$229$lambda$228(MutableState $isFetchingLocation$delegate, RowScope $this$TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C982@47230L90,983@47353L28,984@47414L76:AddExpertDialog.kt#qonjpd");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2069950670, $changed, -1, "com.example.ui.components.AddExpertDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AddExpertDialog.kt:982)");
            }
            IconKt.Icon-ww6aTOc(MyLocationKt.getMyLocation(Icons.INSTANCE.getDefault()), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), $composer, 6);
            TextKt.Text--4IGK_g(AddExpertDialog$lambda$36($isFetchingLocation$delegate) ? "Detecting GPS..." : "Use Current Location", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit AddExpertDialog$lambda$237$lambda$236$lambda$235$lambda$234$lambda$233$lambda$232(boolean $isTimeLocked, MutableState $isAvailable$delegate, boolean it) {
        if (!$isTimeLocked) {
            AddExpertDialog$lambda$28($isAvailable$delegate, it);
        }
        return Unit.INSTANCE;
    }
}
