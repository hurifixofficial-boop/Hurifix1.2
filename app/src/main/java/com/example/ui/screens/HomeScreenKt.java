package com.example.ui.screens;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.pager.PageSize;
import androidx.compose.foundation.pager.PagerKt;
import androidx.compose.foundation.pager.PagerScope;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.foundation.pager.PagerStateKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.SendKt;
import androidx.compose.material.icons.filled.CheckCircleKt;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material.icons.filled.PhoneKt;
import androidx.compose.material.icons.filled.WarningKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.DrawerState;
import androidx.compose.material3.FloatingActionButtonDefaults;
import androidx.compose.material3.FloatingActionButtonKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.NavigationDrawerKt;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SnackbarHostKt;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.TabKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.material3.TopAppBarScrollBehavior;
import androidx.compose.material3.pulltorefresh.PullToRefreshKt;
import androidx.compose.material3.pulltorefresh.PullToRefreshState;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
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
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import coil.compose.EqualityDelegate;
import coil.compose.SingletonAsyncImageKt;
import com.example.data.firebase.FirestoreSyncManager;
import com.example.data.firebase.SyncState;
import com.example.data.model.CustomerJobEntity;
import com.example.data.model.ExpertCategoryEntity;
import com.example.data.model.ExpertEntity;
import com.example.data.model.JobStatus;
import com.example.data.model.RankedExpert;
import com.example.ui.CustomerFormState;
import com.example.ui.CustomerSubTab;
import com.example.ui.DispatchViewModel;
import com.example.ui.MainTab;
import com.example.ui.OrderStatusTab;
import com.example.ui.components.CompletedOrderDetailDialogKt;
import com.example.ui.components.CustomerWhatsAppDialogsKt;
import com.example.ui.components.HurifixConfirmDialogKt;
import com.example.ui.screens.DeleteTarget;
import com.example.util.BackupRestoreHelper;
import com.example.util.LocationHelper;
import com.example.util.SessionManager;
import com.example.util.WhatsAppHelper;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.enums.EnumEntries;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: HomeScreen.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007¢\u0006\u0002\u0010\f\u001aÝ\u0001\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\t2\u0018\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010 \u001aK\u0010!\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010\"\u001a½\u0001\u0010#\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010$\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00072\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\t2\u0018\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010&\u001a×\u0001\u0010'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00072\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u001c2\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u001c2\u0018\u00100\u001a\u0014\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u001cH\u0003¢\u0006\u0002\u00101\u001aÝ\u0001\u00102\u001a\u00020\u00012\f\u00103\u001a\b\u0012\u0004\u0012\u0002040\u000f2\f\u00105\u001a\b\u0012\u0004\u0012\u0002060\u000f2\b\b\u0002\u00107\u001a\u00020\u00072\b\b\u0002\u00108\u001a\u00020\u00072\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u00010\t2\u0018\u0010>\u001a\u0014\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u001c2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u00010\t2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0003¢\u0006\u0002\u0010B¨\u0006C²\u0006\u0010\u0010D\u001a\b\u0012\u0004\u0012\u0002040\u000fX\u008a\u0084\u0002²\u0006\u0010\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u008a\u0084\u0002²\u0006\u0010\u0010E\u001a\b\u0012\u0004\u0012\u0002060\u000fX\u008a\u0084\u0002²\u0006\u0010\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u008a\u0084\u0002²\u0006\u0010\u0010G\u001a\b\u0012\u0004\u0012\u0002040\u000fX\u008a\u0084\u0002²\u0006\n\u0010H\u001a\u00020IX\u008a\u0084\u0002²\u0006\n\u0010J\u001a\u00020\u0012X\u008a\u0084\u0002²\u0006\n\u0010\u0013\u001a\u00020\u0014X\u008a\u0084\u0002²\u0006\f\u0010K\u001a\u0004\u0018\u00010:X\u008a\u0084\u0002²\u0006\f\u0010L\u001a\u0004\u0018\u00010\u0010X\u008a\u0084\u0002²\u0006\n\u0010M\u001a\u00020\u0007X\u008a\u0084\u0002²\u0006\n\u0010N\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\f\u0010O\u001a\u0004\u0018\u000104X\u008a\u008e\u0002²\u0006\f\u0010P\u001a\u0004\u0018\u000104X\u008a\u008e\u0002²\u0006\n\u0010Q\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010R\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\f\u0010S\u001a\u0004\u0018\u00010\u0010X\u008a\u008e\u0002²\u0006\n\u0010T\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010U\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\f\u0010V\u001a\u0004\u0018\u00010\u0010X\u008a\u008e\u0002²\u0006\u0018\u0010W\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0007\u0018\u00010XX\u008a\u008e\u0002²\u0006\u0018\u0010Y\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020Z\u0018\u00010XX\u008a\u008e\u0002²\u0006\u001e\u0010[\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020:\u0018\u00010\\X\u008a\u008e\u0002²\u0006\f\u0010]\u001a\u0004\u0018\u00010\u0010X\u008a\u008e\u0002²\u0006\f\u0010^\u001a\u0004\u0018\u000104X\u008a\u008e\u0002²\u0006\n\u0010_\u001a\u00020`X\u008a\u0084\u0002²\u0006\n\u0010a\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010b\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010c\u001a\u00020:X\u008a\u008e\u0002²\u0006\n\u0010d\u001a\u00020:X\u008a\u008e\u0002²\u0006\n\u0010e\u001a\u00020:X\u008a\u008e\u0002²\u0006\f\u0010f\u001a\u0004\u0018\u00010:X\u008a\u008e\u0002²\u0006\f\u0010g\u001a\u0004\u0018\u00010\u0010X\u008a\u008e\u0002²\u0006\f\u0010h\u001a\u0004\u0018\u00010\u0010X\u008a\u008e\u0002²\u0006\f\u0010i\u001a\u0004\u0018\u00010jX\u008a\u008e\u0002²\u0006\f\u0010k\u001a\u0004\u0018\u00010lX\u008a\u008e\u0002²\u0006\n\u0010m\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010n\u001a\u00020oX\u008a\u0084\u0002²\u0006\u0010\u0010E\u001a\b\u0012\u0004\u0012\u0002060\u000fX\u008a\u0084\u0002²\u0006\n\u0010p\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010q\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010r\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010s\u001a\u00020:X\u008a\u008e\u0002²\u0006\n\u0010t\u001a\u00020:X\u008a\u008e\u0002²\u0006\f\u0010u\u001a\u0004\u0018\u00010.X\u008a\u008e\u0002²\u0006\f\u0010v\u001a\u0004\u0018\u00010.X\u008a\u008e\u0002²\u0006\n\u0010w\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010x\u001a\u00020\u0007X\u008a\u0084\u0002²\u0006\n\u0010y\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010z\u001a\u00020:X\u008a\u008e\u0002²\u0006\n\u0010{\u001a\u00020:X\u008a\u008e\u0002²\u0006\n\u0010|\u001a\u00020:X\u008a\u008e\u0002²\u0006\n\u0010}\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\n\u0010~\u001a\u00020\u007fX\u008a\u008e\u0002²\u0006\u0011\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bX\u008a\u008e\u0002²\u0006\n\u0010s\u001a\u00020:X\u008a\u008e\u0002²\u0006\u000b\u0010\u0081\u0001\u001a\u00020\u0007X\u008a\u008e\u0002²\u0006\u000b\u0010\u0082\u0001\u001a\u00020\u0007X\u008a\u0084\u0002²\u0006\n\u0010x\u001a\u00020\u0007X\u008a\u0084\u0002"}, d2 = {"HomeScreen", "", "viewModel", "Lcom/example/ui/DispatchViewModel;", "sessionManager", "Lcom/example/util/SessionManager;", "isDarkMode", "", "onToggleDarkMode", "Lkotlin/Function1;", "onLogout", "Lkotlin/Function0;", "(Lcom/example/ui/DispatchViewModel;Lcom/example/util/SessionManager;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "CustomerOrdersSection", "allJobs", "", "Lcom/example/data/model/CustomerJobEntity;", "currentSubTab", "Lcom/example/ui/CustomerSubTab;", "currentOrderStatusTab", "Lcom/example/ui/OrderStatusTab;", "canManageOrders", "canDeleteOrders", "isViewOnly", "onOpenWhatsAppParser", "onOrderSaved", "onOpenNearestExperts", "onCompleteOrCancelAction", "Lkotlin/Function2;", "onShowCompletedDetail", "onDeleteJob", "onLongPressOrder", "(Lcom/example/ui/DispatchViewModel;Ljava/util/List;Lcom/example/ui/CustomerSubTab;Lcom/example/ui/OrderStatusTab;ZZZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "DispatchOrderFormContent", "(Lcom/example/ui/DispatchViewModel;ZZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "OrdersListContent", "currentStatusTab", "onSelectStatusTab", "(Lcom/example/ui/DispatchViewModel;Ljava/util/List;Lcom/example/ui/OrderStatusTab;ZZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "OrderItemCard", "job", "currentStatus", "onCompleteAction", "onCancelAction", "onLongPress", "onUpdateExpertNotified", "", "onUpdateCustomerNotified", "onUpdateCompletionNotified", "(Lcom/example/data/model/CustomerJobEntity;Lcom/example/ui/OrderStatusTab;ZZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "ExpertsTabContent", FirestoreSyncManager.EXPERTS_COLLECTION, "Lcom/example/data/model/ExpertEntity;", "categories", "Lcom/example/data/model/ExpertCategoryEntity;", "canAddExperts", "isAdmin", "onAddNewCategory", "", "onDeleteCategory", "onEditExpert", "onDeleteExpert", "onToggleAvailability", "onViewWorkHistory", "onSendWelcome", "onAddExpert", "(Ljava/util/List;Ljava/util/List;ZZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;III)V", "app", "rawExperts", "allCategories", "deletedJobs", "deletedExperts", "currentMainTab", "Lcom/example/ui/MainTab;", "currentCustomerSubTab", "statusMessage", "activeJobForNearestExperts", "isRefreshing", "showAddExpertDialog", "expertToEdit", "expertForWorkHistory", "showRecycleBinDialog", "showWhatsAppParserDialog", "showSaveChoicePopup", "showRankingDialog", "showMonthlyAnalyticsDialog", "showCompletedDetailJob", "reviewJobTarget", "Lkotlin/Pair;", "showAssignExpertWhatsAppPopup", "Lcom/example/data/model/RankedExpert;", "showAssignCustomerWhatsAppPopup", "Lkotlin/Triple;", "showCompletionCustomerWhatsAppJob", "showWelcomeExpertDialog", "syncState", "Lcom/example/data/firebase/SyncState;", "showUserManagementDialog", "showEditAdminProfileDialog", "adminName", "adminPhone", "adminDesignation", "adminPhotoUri", "activeLongPressJob", "editingCustomerJob", "collisionWarningTarget", "Lcom/example/ui/screens/OrderCollisionTarget;", "deleteTarget", "Lcom/example/ui/screens/DeleteTarget;", "showAddNewCategoryDialog", "form", "Lcom/example/ui/CustomerFormState;", "showPermissionDeniedDialog", "showGpsDisabledDialog", "showConfirmSaveOrderDialog", "searchQuery", "selectedDateFilter", "customStartDate", "customEndDate", "showCustomDatePicker", "showScrollToTop", "showConfirmDialog", "confirmTitle", "confirmMessage", "confirmButtonText", "confirmIsDestructive", "confirmIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "confirmAction", "showAddCategoryDialog", "isAtTop"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes4.dex */
public final class HomeScreenKt {

    /* compiled from: HomeScreen.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: /tmp/app_dex/classes4.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[MainTab.values().length];
            try {
                iArr[MainTab.CUSTOMER_ORDERS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MainTab.EXPERTS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[OrderStatusTab.values().length];
            try {
                iArr2[OrderStatusTab.PENDING.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr2[OrderStatusTab.PROCESSING.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[OrderStatusTab.COMPLETED.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[OrderStatusTab.CANCELLED.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerOrdersSection$lambda$343(DispatchViewModel dispatchViewModel, List list, CustomerSubTab customerSubTab, OrderStatusTab orderStatusTab, boolean z, boolean z2, boolean z3, Function0 function0, Function1 function1, Function1 function12, Function2 function2, Function1 function13, Function1 function14, Function1 function15, int i, int i2, int i3, Composer composer, int i4) {
        CustomerOrdersSection(dispatchViewModel, list, customerSubTab, orderStatusTab, z, z2, z3, function0, function1, function12, function2, function13, function14, function15, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$408(DispatchViewModel dispatchViewModel, boolean z, boolean z2, Function0 function0, Function1 function1, int i, int i2, Composer composer, int i3) {
        DispatchOrderFormContent(dispatchViewModel, z, z2, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$649(List list, List list2, boolean z, boolean z2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function2 function2, Function1 function15, Function1 function16, Function0 function0, int i, int i2, int i3, Composer composer, int i4) {
        ExpertsTabContent(list, list2, z, z2, function1, function12, function13, function14, function2, function15, function16, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$328(DispatchViewModel dispatchViewModel, SessionManager sessionManager, boolean z, Function1 function1, Function0 function0, int i, int i2, Composer composer, int i3) {
        HomeScreen(dispatchViewModel, sessionManager, z, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$580(CustomerJobEntity customerJobEntity, OrderStatusTab orderStatusTab, boolean z, boolean z2, Function0 function0, Function0 function02, Function0 function03, Function0 function04, Function0 function05, Function0 function06, Function2 function2, Function2 function22, Function2 function23, int i, int i2, int i3, Composer composer, int i4) {
        OrderItemCard(customerJobEntity, orderStatusTab, z, z2, function0, function02, function03, function04, function05, function06, function2, function22, function23, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$484(DispatchViewModel dispatchViewModel, List list, OrderStatusTab orderStatusTab, boolean z, boolean z2, Function1 function1, Function1 function12, Function2 function2, Function1 function13, Function1 function14, Function1 function15, int i, int i2, int i3, Composer composer, int i4) {
        OrdersListContent(dispatchViewModel, list, orderStatusTab, z, z2, function1, function12, function2, function13, function14, function15, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:202:0x0c51  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0c7f  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x1e61  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x1e73  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x1c17  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x1c25  */
    /* JADX WARN: Removed duplicated region for block: B:573:0x0c8d  */
    /* JADX WARN: Removed duplicated region for block: B:574:0x0c5f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void HomeScreen(final com.example.ui.DispatchViewModel r83, final com.example.util.SessionManager r84, boolean r85, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r86, final kotlin.jvm.functions.Function0<kotlin.Unit> r87, androidx.compose.runtime.Composer r88, final int r89, final int r90) {
        /*
            Method dump skipped, instructions count: 7888
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.HomeScreen(com.example.ui.DispatchViewModel, com.example.util.SessionManager, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final List<ExpertEntity> HomeScreen$lambda$3(State<? extends List<ExpertEntity>> state) {
        return (List) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<CustomerJobEntity> HomeScreen$lambda$6(State<? extends List<CustomerJobEntity>> state) {
        return (List) state.getValue();
    }

    private static final List<ExpertCategoryEntity> HomeScreen$lambda$7(State<? extends List<ExpertCategoryEntity>> state) {
        return (List) state.getValue();
    }

    private static final List<CustomerJobEntity> HomeScreen$lambda$8(State<? extends List<CustomerJobEntity>> state) {
        return (List) state.getValue();
    }

    private static final List<ExpertEntity> HomeScreen$lambda$9(State<? extends List<ExpertEntity>> state) {
        return (List) state.getValue();
    }

    private static final MainTab HomeScreen$lambda$10(State<? extends MainTab> state) {
        return (MainTab) state.getValue();
    }

    private static final CustomerSubTab HomeScreen$lambda$11(State<? extends CustomerSubTab> state) {
        return (CustomerSubTab) state.getValue();
    }

    private static final OrderStatusTab HomeScreen$lambda$12(State<? extends OrderStatusTab> state) {
        return (OrderStatusTab) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeScreen$lambda$13(State<String> state) {
        return (String) state.getValue();
    }

    private static final CustomerJobEntity HomeScreen$lambda$14(State<CustomerJobEntity> state) {
        return (CustomerJobEntity) state.getValue();
    }

    private static final boolean HomeScreen$lambda$15(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean HomeScreen$lambda$17(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void HomeScreen$lambda$18(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final ExpertEntity HomeScreen$lambda$20(MutableState<ExpertEntity> mutableState) {
        return (ExpertEntity) ((State) mutableState).getValue();
    }

    private static final ExpertEntity HomeScreen$lambda$23(MutableState<ExpertEntity> mutableState) {
        return (ExpertEntity) ((State) mutableState).getValue();
    }

    private static final boolean HomeScreen$lambda$26(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void HomeScreen$lambda$27(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean HomeScreen$lambda$29(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void HomeScreen$lambda$30(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final CustomerJobEntity HomeScreen$lambda$32(MutableState<CustomerJobEntity> mutableState) {
        return (CustomerJobEntity) ((State) mutableState).getValue();
    }

    private static final boolean HomeScreen$lambda$35(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void HomeScreen$lambda$36(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean HomeScreen$lambda$38(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void HomeScreen$lambda$39(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final CustomerJobEntity HomeScreen$lambda$41(MutableState<CustomerJobEntity> mutableState) {
        return (CustomerJobEntity) ((State) mutableState).getValue();
    }

    private static final Pair<CustomerJobEntity, Boolean> HomeScreen$lambda$44(MutableState<Pair<CustomerJobEntity, Boolean>> mutableState) {
        return (Pair) ((State) mutableState).getValue();
    }

    private static final Pair<CustomerJobEntity, RankedExpert> HomeScreen$lambda$47(MutableState<Pair<CustomerJobEntity, RankedExpert>> mutableState) {
        return (Pair) ((State) mutableState).getValue();
    }

    private static final Triple<CustomerJobEntity, RankedExpert, String> HomeScreen$lambda$50(MutableState<Triple<CustomerJobEntity, RankedExpert, String>> mutableState) {
        return (Triple) ((State) mutableState).getValue();
    }

    private static final CustomerJobEntity HomeScreen$lambda$53(MutableState<CustomerJobEntity> mutableState) {
        return (CustomerJobEntity) ((State) mutableState).getValue();
    }

    private static final ExpertEntity HomeScreen$lambda$56(MutableState<ExpertEntity> mutableState) {
        return (ExpertEntity) ((State) mutableState).getValue();
    }

    private static final SyncState HomeScreen$lambda$59(State<? extends SyncState> state) {
        return (SyncState) state.getValue();
    }

    private static final boolean HomeScreen$lambda$61(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void HomeScreen$lambda$62(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean HomeScreen$lambda$64(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void HomeScreen$lambda$65(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String HomeScreen$lambda$67(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String HomeScreen$lambda$70(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String HomeScreen$lambda$73(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String HomeScreen$lambda$76(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final CustomerJobEntity HomeScreen$lambda$80(MutableState<CustomerJobEntity> mutableState) {
        return (CustomerJobEntity) ((State) mutableState).getValue();
    }

    private static final CustomerJobEntity HomeScreen$lambda$83(MutableState<CustomerJobEntity> mutableState) {
        return (CustomerJobEntity) ((State) mutableState).getValue();
    }

    private static final OrderCollisionTarget HomeScreen$lambda$86(MutableState<OrderCollisionTarget> mutableState) {
        return (OrderCollisionTarget) ((State) mutableState).getValue();
    }

    private static final void HomeScreen$checkCollisionAndExecute(SessionManager $sessionManager, MutableState<OrderCollisionTarget> mutableState, CustomerJobEntity job, Function0<Unit> function0) {
        String jobManagerId = "";
        String currentUserId = new Regex("[^0-9]").replace($sessionManager.getUserPhone(), "");
        String managed_by_user_id = job.getManaged_by_user_id();
        if (managed_by_user_id != null) {
            String replace = new Regex("[^0-9]").replace(managed_by_user_id, "");
            if (replace != null) {
                jobManagerId = replace;
            }
        }
        boolean isManagedByOther = (StringsKt.isBlank(jobManagerId) || Intrinsics.areEqual(jobManagerId, currentUserId)) ? false : true;
        if (isManagedByOther) {
            String managerName = job.getManaged_by_user_name();
            if (managerName == null) {
                managerName = "Another Team Member";
            }
            String managerDesig = job.getManaged_by_designation();
            if (managerDesig == null) {
                managerDesig = "Partner";
            }
            mutableState.setValue(new OrderCollisionTarget(job, managerName, managerDesig, function0));
            return;
        }
        function0.invoke();
    }

    private static final DeleteTarget HomeScreen$lambda$89(MutableState<DeleteTarget> mutableState) {
        return (DeleteTarget) ((State) mutableState).getValue();
    }

    private static final boolean HomeScreen$lambda$92(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void HomeScreen$lambda$93(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$97$lambda$96(Context $context, DispatchViewModel $viewModel, Uri uri) {
        if (uri != null) {
            try {
                InputStream openInputStream = $context.getContentResolver().openInputStream(uri);
                if (openInputStream != null) {
                    InputStream inputStream = openInputStream;
                    try {
                        BackupRestoreHelper.BackupData parseBackupJson = BackupRestoreHelper.INSTANCE.parseBackupJson(inputStream);
                        $viewModel.restoreBackupData(parseBackupJson.getJobs(), parseBackupJson.getExperts(), parseBackupJson.getCategories());
                        Toast.makeText($context, "Restored " + parseBackupJson.getJobs().size() + " orders and " + parseBackupJson.getExperts().size() + " experts!", 1).show();
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(inputStream, (Throwable) null);
                    } finally {
                    }
                }
            } catch (Exception e) {
                Toast.makeText($context, "Failed to restore backup: " + e.getLocalizedMessage(), 1).show();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144(final CoroutineScope $coroutineScope, final DrawerState $drawerState, final SessionManager $sessionManager, final Function0 $onLogout, final DispatchViewModel $viewModel, final MutableState $adminPhotoUri$delegate, final MutableState $adminName$delegate, final MutableState $adminPhone$delegate, final MutableState $adminDesignation$delegate, final MutableState $showEditAdminProfileDialog$delegate, final MutableState $showUserManagementDialog$delegate, final State $currentMainTab$delegate, final State $currentCustomerSubTab$delegate, final State $allJobs$delegate, final List $experts, final MutableState $showRankingDialog$delegate, final MutableState $showMonthlyAnalyticsDialog$delegate, final State $deletedJobs$delegate, final State $deletedExperts$delegate, final MutableState $showRecycleBinDialog$delegate, final boolean $isDarkMode, final Function1 $onToggleDarkMode, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C344@16423L21080,344@16371L21132:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-170731483, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous> (HomeScreen.kt:344)");
            }
            NavigationDrawerKt.ModalDrawerSheet-afqeVBk(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(310)), (Shape) null, 0L, 0L, 0.0f, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(-1605348919, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda161
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.HomeScreen$lambda$144$lambda$143($coroutineScope, $drawerState, $sessionManager, $onLogout, $viewModel, $adminPhotoUri$delegate, $adminName$delegate, $adminPhone$delegate, $adminDesignation$delegate, $showEditAdminProfileDialog$delegate, $showUserManagementDialog$delegate, $currentMainTab$delegate, $currentCustomerSubTab$delegate, $allJobs$delegate, $experts, $showRankingDialog$delegate, $showMonthlyAnalyticsDialog$delegate, $deletedJobs$delegate, $deletedExperts$delegate, $showRecycleBinDialog$delegate, $isDarkMode, $onToggleDarkMode, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 1572870, 62);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0a9f  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0db1  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0e14  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0dbe  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x086f  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0378 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0483  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x070d  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x07d0  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x07fa  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x086c  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x088e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x09ac  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x09fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit HomeScreen$lambda$144$lambda$143(final kotlinx.coroutines.CoroutineScope r125, final androidx.compose.material3.DrawerState r126, final com.example.util.SessionManager r127, final kotlin.jvm.functions.Function0 r128, final com.example.ui.DispatchViewModel r129, final androidx.compose.runtime.MutableState r130, final androidx.compose.runtime.MutableState r131, final androidx.compose.runtime.MutableState r132, final androidx.compose.runtime.MutableState r133, final androidx.compose.runtime.MutableState r134, final androidx.compose.runtime.MutableState r135, androidx.compose.runtime.State r136, androidx.compose.runtime.State r137, final androidx.compose.runtime.State r138, final java.util.List r139, final androidx.compose.runtime.MutableState r140, final androidx.compose.runtime.MutableState r141, final androidx.compose.runtime.State r142, final androidx.compose.runtime.State r143, final androidx.compose.runtime.MutableState r144, final boolean r145, final kotlin.jvm.functions.Function1 r146, androidx.compose.foundation.layout.ColumnScope r147, androidx.compose.runtime.Composer r148, int r149) {
        /*
            Method dump skipped, instructions count: 3610
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.HomeScreen$lambda$144$lambda$143(kotlinx.coroutines.CoroutineScope, androidx.compose.material3.DrawerState, com.example.util.SessionManager, kotlin.jvm.functions.Function0, com.example.ui.DispatchViewModel, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.runtime.State, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.runtime.MutableState, boolean, kotlin.jvm.functions.Function1, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x056a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x05e2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x06a9  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0681  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x04fc  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0200  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$116(final com.example.util.SessionManager r97, final kotlinx.coroutines.CoroutineScope r98, final androidx.compose.material3.DrawerState r99, final androidx.compose.runtime.MutableState r100, final androidx.compose.runtime.MutableState r101, androidx.compose.runtime.MutableState r102, final androidx.compose.runtime.MutableState r103, final androidx.compose.runtime.MutableState r104, final androidx.compose.runtime.MutableState r105, androidx.compose.foundation.layout.ColumnScope r106, androidx.compose.runtime.Composer r107, int r108) {
        /*
            Method dump skipped, instructions count: 1711
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$116(com.example.util.SessionManager, kotlinx.coroutines.CoroutineScope, androidx.compose.material3.DrawerState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$116$lambda$115$lambda$109$lambda$107(MutableState $adminPhotoUri$delegate, MutableState $adminName$delegate, Composer $composer, int $changed) {
        int i;
        Composer composer;
        ComposerKt.sourceInformation($composer, "C:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1811945308, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:409)");
            }
            String HomeScreen$lambda$76 = HomeScreen$lambda$76($adminPhotoUri$delegate);
            if (!(HomeScreen$lambda$76 == null || StringsKt.isBlank(HomeScreen$lambda$76))) {
                $composer.startReplaceGroup(513804769);
                ComposerKt.sourceInformation($composer, "");
                String HomeScreen$lambda$762 = HomeScreen$lambda$76($adminPhotoUri$delegate);
                Intrinsics.checkNotNull(HomeScreen$lambda$762);
                if (StringsKt.startsWith$default(HomeScreen$lambda$762, "preset:", false, 2, (Object) null)) {
                    $composer.startReplaceGroup(513872938);
                    ComposerKt.sourceInformation($composer, "412@20178L216");
                    String HomeScreen$lambda$763 = HomeScreen$lambda$76($adminPhotoUri$delegate);
                    Intrinsics.checkNotNull(HomeScreen$lambda$763);
                    String emoji = StringsKt.removePrefix(HomeScreen$lambda$763, "preset:");
                    Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                    Alignment center = Alignment.Companion.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, fillMaxSize$default);
                    Function0 constructor = ComposeUiNode.Companion.getConstructor();
                    int i2 = ((((54 << 3) & 112) << 6) & 896) | 6;
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
                    Composer composer2 = Updater.constructor-impl($composer);
                    Updater.set-impl(composer2, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer2, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                    if (!composer2.getInserting() && Intrinsics.areEqual(composer2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        Updater.set-impl(composer2, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                        int i3 = (i2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScope boxScope = BoxScopeInstance.INSTANCE;
                        int i4 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -690751553, "C413@20308L36:HomeScreen.kt#2thlc2");
                        TextKt.Text--4IGK_g(emoji, (Modifier) null, 0L, TextUnitKt.getSp(24), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 0, 131062);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endReplaceGroup();
                    }
                    composer2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composer2.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    Updater.set-impl(composer2, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                    int i32 = (i2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
                    int i42 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -690751553, "C413@20308L36:HomeScreen.kt#2thlc2");
                    TextKt.Text--4IGK_g(emoji, (Modifier) null, 0L, TextUnitKt.getSp(24), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 3072, 0, 131062);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(514290694);
                    ComposerKt.sourceInformation($composer, "416@20496L416");
                    SingletonAsyncImageKt.AsyncImage-gl8XCv8(HomeScreen$lambda$76($adminPhotoUri$delegate), "Profile Photo", ClipKt.clip(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.getCircleShape()), (Function1) null, (Function1) null, (Alignment) null, ContentScale.Companion.getCrop(), 0.0f, (ColorFilter) null, 0, false, (EqualityDelegate) null, $composer, 1572912, 0, 4024);
                    $composer.endReplaceGroup();
                }
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(514848756);
                ComposerKt.sourceInformation($composer, "424@21052L506");
                Alignment center2 = Alignment.Companion.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                Modifier modifier = Modifier.Companion;
                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                int i5 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(constructor2);
                } else {
                    $composer.useNode();
                }
                Composer composer3 = Updater.constructor-impl($composer);
                Updater.set-impl(composer3, maybeCachedBoxMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer3, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composer3.getInserting()) {
                    i = 48;
                    composer = $composer;
                } else {
                    i = 48;
                    composer = $composer;
                    if (Intrinsics.areEqual(composer3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        Updater.set-impl(composer3, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                        int i6 = (i5 >> 6) & 14;
                        Composer composer4 = composer;
                        ComposerKt.sourceInformationMarkerStart(composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                        BoxScope boxScope3 = BoxScopeInstance.INSTANCE;
                        int i7 = ((i >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer4, -696254272, "C425@21143L369:HomeScreen.kt#2thlc2");
                        String upperCase = StringsKt.take(HomeScreen$lambda$67($adminName$delegate), 1).toUpperCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                        TextKt.Text--4IGK_g(upperCase, (Modifier) null, Color.Companion.getWhite-0d7_KjU(), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer4, 200064, 0, 131026);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        $composer.endReplaceGroup();
                    }
                }
                composer3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composer3.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                Updater.set-impl(composer3, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                int i62 = (i5 >> 6) & 14;
                Composer composer42 = composer;
                ComposerKt.sourceInformationMarkerStart(composer42, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScope boxScope32 = BoxScopeInstance.INSTANCE;
                int i72 = ((i >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer42, -696254272, "C425@21143L369:HomeScreen.kt#2thlc2");
                String upperCase2 = StringsKt.take(HomeScreen$lambda$67($adminName$delegate), 1).toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                TextKt.Text--4IGK_g(upperCase2, (Modifier) null, Color.Companion.getWhite-0d7_KjU(), TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer42, 200064, 0, 131026);
                ComposerKt.sourceInformationMarkerEnd(composer42);
                ComposerKt.sourceInformationMarkerEnd(composer42);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$116$lambda$115$lambda$110(SessionManager $sessionManager, MutableState $adminDesignation$delegate, Composer $composer, int $changed) {
        String HomeScreen$lambda$73;
        StringBuilder sb;
        String str;
        ComposerKt.sourceInformation($composer, "C454@22914L467:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(188897848, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:454)");
            }
            if ($sessionManager.isAdmin()) {
                HomeScreen$lambda$73 = HomeScreen$lambda$73($adminDesignation$delegate);
                sb = new StringBuilder();
                str = "👑 ";
            } else {
                HomeScreen$lambda$73 = HomeScreen$lambda$73($adminDesignation$delegate);
                sb = new StringBuilder();
                str = "👤 ";
            }
            TextKt.Text--4IGK_g(sb.append(str).append(HomeScreen$lambda$73).toString(), PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(8), Dp.constructor-impl(3)), Color.Companion.getWhite-0d7_KjU(), TextUnitKt.getSp(10.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 200112, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$116$lambda$115$lambda$112$lambda$111(MutableState $showEditAdminProfileDialog$delegate) {
        HomeScreen$lambda$65($showEditAdminProfileDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$116$lambda$115$lambda$114$lambda$113(CoroutineScope $coroutineScope, DrawerState $drawerState, MutableState $showUserManagementDialog$delegate) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$6$1$1$1$2$1$4$1$1($drawerState, null), 3, (Object) null);
        HomeScreen$lambda$62($showUserManagementDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$118$lambda$117(CoroutineScope $coroutineScope, DispatchViewModel $viewModel, DrawerState $drawerState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$6$1$1$1$3$1$1($drawerState, null), 3, (Object) null);
        $viewModel.selectMainTab(MainTab.CUSTOMER_ORDERS);
        $viewModel.selectCustomerSubTab(CustomerSubTab.DISPATCH_ORDER);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$119(State $allJobs$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C525@26947L191:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1294640230, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:525)");
            }
            TextKt.Text--4IGK_g("📋 Customer Orders (" + HomeScreen$lambda$6($allJobs$delegate).size() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196608, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$121$lambda$120(CoroutineScope $coroutineScope, DispatchViewModel $viewModel, DrawerState $drawerState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$6$1$1$1$5$1$1($drawerState, null), 3, (Object) null);
        $viewModel.selectMainTab(MainTab.CUSTOMER_ORDERS);
        $viewModel.selectCustomerSubTab(CustomerSubTab.ORDERS);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$122(List $experts, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C543@27878L190:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2121132719, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:543)");
            }
            TextKt.Text--4IGK_g("🛠 Manage Experts (" + $experts.size() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196608, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$124$lambda$123(CoroutineScope $coroutineScope, DispatchViewModel $viewModel, DrawerState $drawerState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$6$1$1$1$7$1$1($drawerState, null), 3, (Object) null);
        $viewModel.selectMainTab(MainTab.EXPERTS);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$126$lambda$125(CoroutineScope $coroutineScope, DrawerState $drawerState, MutableState $showRankingDialog$delegate) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$6$1$1$1$8$1$1($drawerState, null), 3, (Object) null);
        HomeScreen$lambda$36($showRankingDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$128$lambda$127(CoroutineScope $coroutineScope, DrawerState $drawerState, MutableState $showMonthlyAnalyticsDialog$delegate) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$6$1$1$1$9$1$1($drawerState, null), 3, (Object) null);
        HomeScreen$lambda$39($showMonthlyAnalyticsDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$131(androidx.compose.runtime.State r49, androidx.compose.runtime.State r50, androidx.compose.runtime.Composer r51, int r52) {
        /*
            Method dump skipped, instructions count: 509
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$131(androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$131$lambda$130$lambda$129(int $totalDeleted, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C637@32945L11,633@32637L503:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(756228103, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:633)");
            }
            TextKt.Text--4IGK_g(String.valueOf($totalDeleted), PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(6), Dp.constructor-impl(2)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnErrorContainer-0d7_KjU(), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$133$lambda$132(CoroutineScope $coroutineScope, DrawerState $drawerState, MutableState $showRecycleBinDialog$delegate) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$6$1$1$1$11$1$1($drawerState, null), 3, (Object) null);
        HomeScreen$lambda$27($showRecycleBinDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$138(boolean r73, final kotlin.jvm.functions.Function1 r74, androidx.compose.runtime.Composer r75, int r76) {
        /*
            Method dump skipped, instructions count: 912
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$138(boolean, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$139$lambda$138$lambda$137$lambda$136$lambda$135(Function1 $onToggleDarkMode, boolean it) {
        $onToggleDarkMode.invoke(Boolean.valueOf(it));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$140(CoroutineScope $coroutineScope, SessionManager $sessionManager, Function0 $onLogout, DrawerState $drawerState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$6$1$1$2$1$1($drawerState, null), 3, (Object) null);
        $sessionManager.logout();
        $onLogout.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193(final List $experts, final State $currentMainTab$delegate, final DispatchViewModel $viewModel, final CoroutineScope $coroutineScope, final DrawerState $drawerState, final SnackbarHostState $snackbarHostState, final FirestoreSyncManager $syncManager, final State $isRefreshing$delegate, final SessionManager $sessionManager, final State $allJobs$delegate, final State $currentCustomerSubTab$delegate, final State $currentOrderStatusTab$delegate, final MutableState $showWhatsAppParserDialog$delegate, final MutableState $showSaveChoicePopup$delegate, final MutableState $collisionWarningTarget$delegate, final MutableState $showCompletionCustomerWhatsAppJob$delegate, final MutableState $reviewJobTarget$delegate, final MutableState $showCompletedDetailJob$delegate, final MutableState $deleteTarget$delegate, final MutableState $activeLongPressJob$delegate, final State $allCategories$delegate, final MutableState $expertToEdit$delegate, final MutableState $showAddExpertDialog$delegate, final MutableState $expertForWorkHistory$delegate, final MutableState $showWelcomeExpertDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C730@37625L2720,729@37567L35,783@40356L4260,728@37530L7086:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-299443222, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous> (HomeScreen.kt:728)");
            }
            ScaffoldKt.Scaffold-TvnljyQ((Modifier) null, ComposableLambdaKt.rememberComposableLambda(-276883794, true, new Function2() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda99
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$193$lambda$152($experts, $currentMainTab$delegate, $viewModel, $coroutineScope, $drawerState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function2) null, ComposableLambdaKt.rememberComposableLambda(-2001726032, true, new Function2() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda110
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$193$lambda$153($snackbarHostState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Function2) null, 0, 0L, 0L, (WindowInsets) null, ComposableLambdaKt.rememberComposableLambda(-2038319623, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda122
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.HomeScreen$lambda$193$lambda$192($coroutineScope, $syncManager, $viewModel, $isRefreshing$delegate, $sessionManager, $experts, $currentMainTab$delegate, $allJobs$delegate, $currentCustomerSubTab$delegate, $currentOrderStatusTab$delegate, $showWhatsAppParserDialog$delegate, $showSaveChoicePopup$delegate, $collisionWarningTarget$delegate, $showCompletionCustomerWhatsAppJob$delegate, $reviewJobTarget$delegate, $showCompletedDetailJob$delegate, $deleteTarget$delegate, $activeLongPressJob$delegate, $allCategories$delegate, $expertToEdit$delegate, $showAddExpertDialog$delegate, $expertForWorkHistory$delegate, $showWelcomeExpertDialog$delegate, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 805309488, 501);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$153(SnackbarHostState $snackbarHostState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C729@37569L31:HomeScreen.kt#2thlc2");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2001726032, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:729)");
            }
            SnackbarHostKt.SnackbarHost($snackbarHostState, (Modifier) null, (Function3) null, $composer, 6, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$152(final List $experts, final State $currentMainTab$delegate, final DispatchViewModel $viewModel, final CoroutineScope $coroutineScope, final DrawerState $drawerState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C733@37799L11,734@37878L11,732@37714L207,761@39283L996,736@37960L1293,731@37643L2688:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-276883794, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:731)");
            }
            AppBarKt.CenterAlignedTopAppBar-GHTll3U(ComposableLambdaKt.rememberComposableLambda(923928979, true, new Function2() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda66
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$193$lambda$152$lambda$146($experts, $currentMainTab$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(1080171157, true, new Function2() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda77
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$193$lambda$152$lambda$151(DispatchViewModel.this, $coroutineScope, $drawerState, $currentMainTab$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), ComposableSingletons$HomeScreenKt.INSTANCE.m174getLambda$1452969282$app(), 0.0f, (WindowInsets) null, TopAppBarDefaults.INSTANCE.centerAlignedTopAppBarColors-zjMxDiM(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface-0d7_KjU(), 0L, 0L, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface-0d7_KjU(), 0L, $composer, TopAppBarDefaults.$stable << 15, 22), (TopAppBarScrollBehavior) null, $composer, 3462, 178);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$152$lambda$151(final DispatchViewModel $viewModel, final CoroutineScope $coroutineScope, final DrawerState $drawerState, State $currentMainTab$delegate, Composer $composer, int $changed) {
        Object obj;
        Object obj2;
        ComposerKt.sourceInformation($composer, "C:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1080171157, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:737)");
            }
            if (HomeScreen$lambda$10($currentMainTab$delegate) == MainTab.EXPERTS) {
                $composer.startReplaceGroup(1484033282);
                ComposerKt.sourceInformation($composer, "738@38076L52,738@38055L339");
                ComposerKt.sourceInformationMarkerStart($composer, -1337599991, "CC(remember):HomeScreen.kt#9igjgp");
                boolean changedInstance = $composer.changedInstance($viewModel);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                    obj2 = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda116
                        public final Object invoke() {
                            return HomeScreenKt.HomeScreen$lambda$193$lambda$152$lambda$151$lambda$148$lambda$147(DispatchViewModel.this);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                } else {
                    obj2 = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconButtonKt.IconButton((Function0) obj2, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$1580406925$app(), $composer, 196608, 30);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1484443784);
                ComposerKt.sourceInformation($composer, "746@38510L243,745@38456L749");
                ComposerKt.sourceInformationMarkerStart($composer, -1337585912, "CC(remember):HomeScreen.kt#9igjgp");
                boolean changedInstance2 = $composer.changedInstance($coroutineScope) | $composer.changed($drawerState);
                Object rememberedValue2 = $composer.rememberedValue();
                if (changedInstance2 || rememberedValue2 == Composer.Companion.getEmpty()) {
                    obj = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda117
                        public final Object invoke() {
                            return HomeScreenKt.HomeScreen$lambda$193$lambda$152$lambda$151$lambda$150$lambda$149($coroutineScope, $drawerState);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = rememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconButtonKt.IconButton((Function0) obj, TestTagKt.testTag(Modifier.Companion, "app_navigation_drawer_btn"), false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$1289354710$app(), $composer, 196656, 28);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$152$lambda$151$lambda$148$lambda$147(DispatchViewModel $viewModel) {
        $viewModel.selectMainTab(MainTab.CUSTOMER_ORDERS);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$152$lambda$151$lambda$150$lambda$149(CoroutineScope $coroutineScope, DrawerState $drawerState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$7$1$2$2$1$1($drawerState, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit HomeScreen$lambda$193$lambda$152$lambda$146(java.util.List r50, androidx.compose.runtime.State r51, androidx.compose.runtime.Composer r52, int r53) {
        /*
            Method dump skipped, instructions count: 521
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.HomeScreen$lambda$193$lambda$152$lambda$146(java.util.List, androidx.compose.runtime.State, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192(final CoroutineScope $coroutineScope, final FirestoreSyncManager $syncManager, final DispatchViewModel $viewModel, State $isRefreshing$delegate, final SessionManager $sessionManager, final List $experts, final State $currentMainTab$delegate, final State $allJobs$delegate, final State $currentCustomerSubTab$delegate, final State $currentOrderStatusTab$delegate, final MutableState $showWhatsAppParserDialog$delegate, final MutableState $showSaveChoicePopup$delegate, final MutableState $collisionWarningTarget$delegate, final MutableState $showCompletionCustomerWhatsAppJob$delegate, final MutableState $reviewJobTarget$delegate, final MutableState $showCompletedDetailJob$delegate, final MutableState $deleteTarget$delegate, final MutableState $activeLongPressJob$delegate, final State $allCategories$delegate, final MutableState $expertToEdit$delegate, final MutableState $showAddExpertDialog$delegate, final MutableState $expertForWorkHistory$delegate, final MutableState $showWelcomeExpertDialog$delegate, PaddingValues paddingValues, Composer $composer, int $changed) {
        Object obj;
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        ComposerKt.sourceInformation($composer, "C786@40478L182,795@40791L3815,784@40387L4219:HomeScreen.kt#2thlc2");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(paddingValues) ? 4 : 2;
        }
        if (($dirty & 19) == 18 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2038319623, $dirty, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:784)");
            }
            boolean HomeScreen$lambda$15 = HomeScreen$lambda$15($isRefreshing$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -320066641, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($coroutineScope) | $composer.changedInstance($syncManager) | $composer.changedInstance($viewModel);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda113
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$193$lambda$192$lambda$155$lambda$154($coroutineScope, $syncManager, $viewModel);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            PullToRefreshKt.PullToRefreshBox(HomeScreen$lambda$15, (Function0) obj, PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), paddingValues), (PullToRefreshState) null, (Alignment) null, (Function3) null, ComposableLambdaKt.rememberComposableLambda(-515794337, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda114
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return HomeScreenKt.HomeScreen$lambda$193$lambda$192$lambda$191(DispatchViewModel.this, $sessionManager, $experts, $currentMainTab$delegate, $allJobs$delegate, $currentCustomerSubTab$delegate, $currentOrderStatusTab$delegate, $showWhatsAppParserDialog$delegate, $showSaveChoicePopup$delegate, $collisionWarningTarget$delegate, $showCompletionCustomerWhatsAppJob$delegate, $reviewJobTarget$delegate, $showCompletedDetailJob$delegate, $deleteTarget$delegate, $activeLongPressJob$delegate, $allCategories$delegate, $expertToEdit$delegate, $showAddExpertDialog$delegate, $expertForWorkHistory$delegate, $showWelcomeExpertDialog$delegate, (BoxScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 1572864, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$155$lambda$154(CoroutineScope $coroutineScope, FirestoreSyncManager $syncManager, DispatchViewModel $viewModel) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$7$3$1$1$1($syncManager, $viewModel, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit HomeScreen$lambda$193$lambda$192$lambda$191(final com.example.ui.DispatchViewModel r24, final com.example.util.SessionManager r25, java.util.List r26, androidx.compose.runtime.State r27, androidx.compose.runtime.State r28, androidx.compose.runtime.State r29, androidx.compose.runtime.State r30, final androidx.compose.runtime.MutableState r31, final androidx.compose.runtime.MutableState r32, androidx.compose.runtime.MutableState r33, final androidx.compose.runtime.MutableState r34, final androidx.compose.runtime.MutableState r35, final androidx.compose.runtime.MutableState r36, final androidx.compose.runtime.MutableState r37, final androidx.compose.runtime.MutableState r38, androidx.compose.runtime.State r39, final androidx.compose.runtime.MutableState r40, final androidx.compose.runtime.MutableState r41, final androidx.compose.runtime.MutableState r42, final androidx.compose.runtime.MutableState r43, androidx.compose.foundation.layout.BoxScope r44, androidx.compose.runtime.Composer r45, int r46) {
        /*
            Method dump skipped, instructions count: 1198
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.HomeScreen$lambda$193$lambda$192$lambda$191(com.example.ui.DispatchViewModel, com.example.util.SessionManager, java.util.List, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.State, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.BoxScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$157$lambda$156(MutableState $showWhatsAppParserDialog$delegate) {
        HomeScreen$lambda$30($showWhatsAppParserDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$159$lambda$158(MutableState $showSaveChoicePopup$delegate, CustomerJobEntity savedJob) {
        Intrinsics.checkNotNullParameter(savedJob, "savedJob");
        $showSaveChoicePopup$delegate.setValue(savedJob);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$162$lambda$161(final DispatchViewModel $viewModel, SessionManager $sessionManager, MutableState $collisionWarningTarget$delegate, final CustomerJobEntity job) {
        Intrinsics.checkNotNullParameter(job, "job");
        HomeScreen$checkCollisionAndExecute($sessionManager, $collisionWarningTarget$delegate, job, new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda129
            public final Object invoke() {
                return HomeScreenKt.HomeScreen$lambda$193$lambda$192$lambda$191$lambda$162$lambda$161$lambda$160(DispatchViewModel.this, job);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$162$lambda$161$lambda$160(DispatchViewModel $viewModel, CustomerJobEntity $job) {
        $viewModel.openFindNearestExperts($job);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$165$lambda$164(final MutableState $showCompletionCustomerWhatsAppJob$delegate, final MutableState $reviewJobTarget$delegate, SessionManager $sessionManager, MutableState $collisionWarningTarget$delegate, final CustomerJobEntity job, final boolean isComplete) {
        Intrinsics.checkNotNullParameter(job, "job");
        HomeScreen$checkCollisionAndExecute($sessionManager, $collisionWarningTarget$delegate, job, new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda112
            public final Object invoke() {
                return HomeScreenKt.HomeScreen$lambda$193$lambda$192$lambda$191$lambda$165$lambda$164$lambda$163(isComplete, job, $showCompletionCustomerWhatsAppJob$delegate, $reviewJobTarget$delegate);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$165$lambda$164$lambda$163(boolean $isComplete, CustomerJobEntity $job, MutableState $showCompletionCustomerWhatsAppJob$delegate, MutableState $reviewJobTarget$delegate) {
        if (!$isComplete) {
            $reviewJobTarget$delegate.setValue(new Pair($job, false));
        } else {
            $showCompletionCustomerWhatsAppJob$delegate.setValue($job);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$167$lambda$166(MutableState $showCompletedDetailJob$delegate, CustomerJobEntity job) {
        Intrinsics.checkNotNullParameter(job, "job");
        $showCompletedDetailJob$delegate.setValue(job);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$170$lambda$169(final MutableState $deleteTarget$delegate, SessionManager $sessionManager, MutableState $collisionWarningTarget$delegate, final CustomerJobEntity job) {
        Intrinsics.checkNotNullParameter(job, "job");
        HomeScreen$checkCollisionAndExecute($sessionManager, $collisionWarningTarget$delegate, job, new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda162
            public final Object invoke() {
                return HomeScreenKt.HomeScreen$lambda$193$lambda$192$lambda$191$lambda$170$lambda$169$lambda$168(CustomerJobEntity.this, $deleteTarget$delegate);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$170$lambda$169$lambda$168(CustomerJobEntity $job, MutableState $deleteTarget$delegate) {
        $deleteTarget$delegate.setValue(new DeleteTarget.Job($job));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$172$lambda$171(MutableState $activeLongPressJob$delegate, CustomerJobEntity job) {
        Intrinsics.checkNotNullParameter(job, "job");
        $activeLongPressJob$delegate.setValue(job);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$176$lambda$175(DispatchViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.addNewCategory(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$178$lambda$177(MutableState $deleteTarget$delegate, ExpertCategoryEntity it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $deleteTarget$delegate.setValue(new DeleteTarget.Category(it));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$180$lambda$179(MutableState $expertToEdit$delegate, MutableState $showAddExpertDialog$delegate, ExpertEntity expert) {
        Intrinsics.checkNotNullParameter(expert, "expert");
        $expertToEdit$delegate.setValue(expert);
        HomeScreen$lambda$18($showAddExpertDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$182$lambda$181(DispatchViewModel $viewModel, ExpertEntity expert) {
        Intrinsics.checkNotNullParameter(expert, "expert");
        $viewModel.deleteExpert(expert);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$184$lambda$183(DispatchViewModel $viewModel, ExpertEntity exp, boolean avail) {
        Intrinsics.checkNotNullParameter(exp, "exp");
        $viewModel.updateExpert(ExpertEntity.copy$default(exp, 0L, null, null, null, null, 0.0d, 0.0d, avail, 0.0f, 0.0f, 0, 0, 0, false, false, null, null, null, null, null, 0L, 0L, 0L, false, 16777087, null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$186$lambda$185(MutableState $expertForWorkHistory$delegate, ExpertEntity expert) {
        Intrinsics.checkNotNullParameter(expert, "expert");
        $expertForWorkHistory$delegate.setValue(expert);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$188$lambda$187(MutableState $showWelcomeExpertDialog$delegate, ExpertEntity expert) {
        Intrinsics.checkNotNullParameter(expert, "expert");
        $showWelcomeExpertDialog$delegate.setValue(expert);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$193$lambda$192$lambda$191$lambda$190$lambda$189(MutableState $expertToEdit$delegate, MutableState $showAddExpertDialog$delegate) {
        $expertToEdit$delegate.setValue(null);
        HomeScreen$lambda$18($showAddExpertDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$200$lambda$199(DispatchViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.addNewCategory(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$202$lambda$201(MutableState $showAddExpertDialog$delegate, MutableState $expertToEdit$delegate) {
        HomeScreen$lambda$18($showAddExpertDialog$delegate, false);
        $expertToEdit$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$205$lambda$204(DispatchViewModel $viewModel, MutableState $expertToEdit$delegate, final MutableState $showWelcomeExpertDialog$delegate, MutableState $showAddExpertDialog$delegate, ExpertEntity expert) {
        Intrinsics.checkNotNullParameter(expert, "expert");
        if (HomeScreen$lambda$20($expertToEdit$delegate) == null) {
            $viewModel.saveNewExpert(expert, new Function1() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda167
                public final Object invoke(Object obj) {
                    return HomeScreenKt.HomeScreen$lambda$205$lambda$204$lambda$203($showWelcomeExpertDialog$delegate, (ExpertEntity) obj);
                }
            });
        } else {
            $viewModel.updateExpert(expert);
        }
        HomeScreen$lambda$18($showAddExpertDialog$delegate, false);
        $expertToEdit$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$205$lambda$204$lambda$203(MutableState $showWelcomeExpertDialog$delegate, ExpertEntity saved) {
        Intrinsics.checkNotNullParameter(saved, "saved");
        $showWelcomeExpertDialog$delegate.setValue(saved);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$207$lambda$206(MutableState $showWhatsAppParserDialog$delegate) {
        HomeScreen$lambda$30($showWhatsAppParserDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$209$lambda$208(DispatchViewModel $viewModel, MutableState $showWhatsAppParserDialog$delegate, String rawText) {
        Intrinsics.checkNotNullParameter(rawText, "rawText");
        $viewModel.parseAndFillFromWhatsAppText(rawText);
        HomeScreen$lambda$30($showWhatsAppParserDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$220$lambda$211$lambda$210(DispatchViewModel $viewModel, MutableState $showSaveChoicePopup$delegate) {
        $showSaveChoicePopup$delegate.setValue(null);
        $viewModel.selectCustomerSubTab(CustomerSubTab.ORDERS);
        $viewModel.selectOrderStatusTab(OrderStatusTab.PENDING);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit HomeScreen$lambda$220$lambda$219(com.example.data.model.CustomerJobEntity r50, androidx.compose.runtime.Composer r51, int r52) {
        /*
            Method dump skipped, instructions count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.HomeScreen$lambda$220$lambda$219(com.example.data.model.CustomerJobEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$220$lambda$214(final CustomerJobEntity $job, final DispatchViewModel $viewModel, final MutableState $showSaveChoicePopup$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C947@47827L188,946@47789L400:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1764526679, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:946)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -801941421, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changed = $composer.changed($job) | $composer.changedInstance($viewModel);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda138
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$220$lambda$214$lambda$213$lambda$212(CustomerJobEntity.this, $viewModel, $showSaveChoicePopup$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$HomeScreenKt.INSTANCE.m201getLambda$99486137$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$220$lambda$214$lambda$213$lambda$212(CustomerJobEntity $job, DispatchViewModel $viewModel, MutableState $showSaveChoicePopup$delegate) {
        $showSaveChoicePopup$delegate.setValue(null);
        $viewModel.openFindNearestExperts($job);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$220$lambda$217(final DispatchViewModel $viewModel, final MutableState $showSaveChoicePopup$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C959@48297L231,958@48251L421:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(450696793, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:958)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -672076544, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($viewModel);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda139
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$220$lambda$217$lambda$216$lambda$215(DispatchViewModel.this, $showSaveChoicePopup$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.OutlinedButton((Function0) obj, (Modifier) null, false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)), (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$2028688715$app(), $composer, 805306368, 502);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$220$lambda$217$lambda$216$lambda$215(DispatchViewModel $viewModel, MutableState $showSaveChoicePopup$delegate) {
        $showSaveChoicePopup$delegate.setValue(null);
        $viewModel.selectCustomerSubTab(CustomerSubTab.ORDERS);
        $viewModel.selectOrderStatusTab(OrderStatusTab.PENDING);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$226$lambda$222$lambda$221(DispatchViewModel $viewModel) {
        $viewModel.closeFindNearestExperts();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$226$lambda$225$lambda$224(SessionManager $sessionManager, DispatchViewModel $viewModel, CustomerJobEntity $job, MutableState $showAssignExpertWhatsAppPopup$delegate, RankedExpert ranked) {
        Intrinsics.checkNotNullParameter(ranked, "ranked");
        String mId = new Regex("[^0-9]").replace($sessionManager.getUserPhone(), "");
        String userName = $sessionManager.getUserName();
        if (StringsKt.isBlank(userName)) {
            userName = "User";
        }
        String mName = userName;
        String mDesig = $sessionManager.getUserDesignationTag();
        $viewModel.assignExpertToJob($job, ranked, mId, mName, mDesig);
        $viewModel.closeFindNearestExperts();
        $showAssignExpertWhatsAppPopup$delegate.setValue(new Pair($job, ranked));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$231$lambda$228$lambda$227(Context $context, RankedExpert $ranked, CustomerJobEntity $job, DispatchViewModel $viewModel, MutableState $showAssignExpertWhatsAppPopup$delegate, MutableState $showAssignCustomerWhatsAppPopup$delegate) {
        $showAssignExpertWhatsAppPopup$delegate.setValue(null);
        WhatsAppHelper.INSTANCE.sendWhatsAppMessageToExpert($context, $ranked.getExpert(), $job);
        $viewModel.updateExpertNotified($job.getId(), true);
        String estimatedTimeText = WhatsAppHelper.INSTANCE.calculateEstimatedArrivalTimeWithBuffer($ranked.getDistanceKm());
        $showAssignCustomerWhatsAppPopup$delegate.setValue(new Triple($job, $ranked, estimatedTimeText));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$231$lambda$230$lambda$229(DispatchViewModel $viewModel, CustomerJobEntity $job, RankedExpert $ranked, MutableState $showAssignExpertWhatsAppPopup$delegate, MutableState $showAssignCustomerWhatsAppPopup$delegate) {
        $showAssignExpertWhatsAppPopup$delegate.setValue(null);
        $viewModel.updateExpertNotified($job.getId(), false);
        $viewModel.markMessageLaterDismissed($job.getId());
        String estimatedTimeText = WhatsAppHelper.INSTANCE.calculateEstimatedArrivalTimeWithBuffer($ranked.getDistanceKm());
        $showAssignCustomerWhatsAppPopup$delegate.setValue(new Triple($job, $ranked, estimatedTimeText));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$236$lambda$233$lambda$232(MutableState $reviewJobTarget$delegate) {
        $reviewJobTarget$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$236$lambda$235$lambda$234(DispatchViewModel $viewModel, CustomerJobEntity $job, boolean $isCompleted, MutableState $reviewJobTarget$delegate, float rating, String feedback) {
        $reviewJobTarget$delegate.setValue(null);
        $viewModel.completeOrCancelJobWithReview($job, $isCompleted, rating, feedback);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$238$lambda$237(MutableState $showRankingDialog$delegate) {
        HomeScreen$lambda$36($showRankingDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$240$lambda$239(MutableState $showMonthlyAnalyticsDialog$delegate) {
        HomeScreen$lambda$39($showMonthlyAnalyticsDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$243$lambda$242$lambda$241(MutableState $showCompletedDetailJob$delegate) {
        $showCompletedDetailJob$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$248$lambda$245$lambda$244(CustomerJobEntity $job, RankedExpert $ranked, String $estTime, Context $context, DispatchViewModel $viewModel, MutableState $showAssignCustomerWhatsAppPopup$delegate) {
        String msg = WhatsAppHelper.INSTANCE.createCustomerAssignmentNotificationMessage($job.getCustomerName(), $ranked.getExpert().getName(), $ranked.getExpert().getPhone(), $job.getServiceType(), $estTime);
        WhatsAppHelper.INSTANCE.sendWhatsAppDirectMessage($context, $job.getCustomerPhone(), msg);
        $viewModel.updateCustomerNotifiedOnAssign($job.getId(), true);
        $showAssignCustomerWhatsAppPopup$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$248$lambda$247$lambda$246(DispatchViewModel $viewModel, CustomerJobEntity $job, MutableState $showAssignCustomerWhatsAppPopup$delegate) {
        $viewModel.updateCustomerNotifiedOnAssign($job.getId(), false);
        $showAssignCustomerWhatsAppPopup$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$253$lambda$250$lambda$249(CustomerJobEntity $job, Context $context, DispatchViewModel $viewModel, MutableState $showCompletionCustomerWhatsAppJob$delegate, MutableState $reviewJobTarget$delegate) {
        String msg = WhatsAppHelper.INSTANCE.createCompletionCustomerMessage($job.getCustomerName());
        WhatsAppHelper.INSTANCE.sendWhatsAppDirectMessage($context, $job.getCustomerPhone(), msg);
        $viewModel.updateCustomerNotifiedOnCompletion($job.getId(), true);
        $showCompletionCustomerWhatsAppJob$delegate.setValue(null);
        $reviewJobTarget$delegate.setValue(new Pair($job, true));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$253$lambda$252$lambda$251(DispatchViewModel $viewModel, CustomerJobEntity $job, MutableState $showCompletionCustomerWhatsAppJob$delegate, MutableState $reviewJobTarget$delegate) {
        $viewModel.updateCustomerNotifiedOnCompletion($job.getId(), false);
        $showCompletionCustomerWhatsAppJob$delegate.setValue(null);
        $reviewJobTarget$delegate.setValue(new Pair($job, true));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$258$lambda$255$lambda$254(DispatchViewModel $viewModel, ExpertEntity $expert, Context $context, MutableState $showWelcomeExpertDialog$delegate, String messageText) {
        Intrinsics.checkNotNullParameter(messageText, "messageText");
        $showWelcomeExpertDialog$delegate.setValue(null);
        $viewModel.updateWelcomeMessageSent($expert.getId(), true);
        WhatsAppHelper.INSTANCE.sendWhatsAppDirectMessage($context, $expert.getPhone(), messageText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$258$lambda$257$lambda$256(DispatchViewModel $viewModel, ExpertEntity $expert, MutableState $showWelcomeExpertDialog$delegate) {
        $showWelcomeExpertDialog$delegate.setValue(null);
        $viewModel.updateWelcomeMessageSent($expert.getId(), false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$260$lambda$259(CoroutineScope $coroutineScope, SessionManager $sessionManager, Context $context, String newPassword) {
        Intrinsics.checkNotNullParameter(newPassword, "newPassword");
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$26$1$1($sessionManager, newPassword, $context, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$262$lambda$261(CoroutineScope $coroutineScope, SessionManager $sessionManager, Context $context, Function0 $onLogout) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$27$1$1($sessionManager, $context, $onLogout, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$264$lambda$263(SessionManager $sessionManager, CoroutineScope $coroutineScope, Context $context, MutableState $adminDesignation$delegate, MutableState $adminName$delegate, MutableState $adminPhone$delegate, MutableState $adminPhotoUri$delegate, FirestoreSyncManager $syncManager, MutableState $showEditAdminProfileDialog$delegate, String newName, String newPhone, String newRole, String newPhotoUri) {
        Intrinsics.checkNotNullParameter(newName, "newName");
        Intrinsics.checkNotNullParameter(newPhone, "newPhone");
        Intrinsics.checkNotNullParameter(newRole, "newRole");
        String finalDesignation = $sessionManager.isAdmin() ? newRole : HomeScreen$lambda$73($adminDesignation$delegate);
        if ($sessionManager.isAdmin()) {
            $sessionManager.updateAdminProfile(newName, newPhone, finalDesignation, newPhotoUri);
        } else {
            $sessionManager.updateUserProfile(newName, newPhone, finalDesignation, newPhotoUri);
        }
        $adminName$delegate.setValue(newName);
        $adminPhone$delegate.setValue(newPhone);
        $adminDesignation$delegate.setValue(finalDesignation);
        $adminPhotoUri$delegate.setValue(newPhotoUri);
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$HomeScreen$28$1$1($syncManager, newPhone, newName, finalDesignation, newPhotoUri, null), 3, (Object) null);
        HomeScreen$lambda$65($showEditAdminProfileDialog$delegate, false);
        Toast.makeText($context, "Profile updated successfully!", 0).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$266$lambda$265(MutableState $showEditAdminProfileDialog$delegate) {
        HomeScreen$lambda$65($showEditAdminProfileDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$268$lambda$267(DispatchViewModel $viewModel, List restoredJobs, List restoredExperts, List restoredCategories) {
        Intrinsics.checkNotNullParameter(restoredJobs, "restoredJobs");
        Intrinsics.checkNotNullParameter(restoredExperts, "restoredExperts");
        Intrinsics.checkNotNullParameter(restoredCategories, "restoredCategories");
        $viewModel.restoreBackupData(restoredJobs, restoredExperts, restoredCategories);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$270$lambda$269(MutableState $showUserManagementDialog$delegate) {
        HomeScreen$lambda$62($showUserManagementDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$281$lambda$273$lambda$272(MutableState $collisionWarningTarget$delegate) {
        $collisionWarningTarget$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$281$lambda$280(OrderCollisionTarget $target, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1206@58999L10,1204@58771L267:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-335410245, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:1204)");
            }
            TextKt.Text--4IGK_g("This order is currently being managed by " + $target.getManagerName() + " (" + $target.getManagerDesignation() + "). Are you sure you want to take over or edit this order?", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 0, 0, 65534);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$281$lambda$276(final OrderCollisionTarget $target, final DispatchViewModel $viewModel, final String $currentUserId, final String $currentUserName, final String $currentUserDesignation, final MutableState $collisionWarningTarget$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C1211@59138L547,1223@59775L11,1223@59731L64,1210@59100L806:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2089316000, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:1210)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 2082951107, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changed = $composer.changed($target) | $composer.changedInstance($viewModel) | $composer.changed($currentUserId) | $composer.changed($currentUserName) | $composer.changed($currentUserDesignation);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda223
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$281$lambda$276$lambda$275$lambda$274(OrderCollisionTarget.this, $viewModel, $currentUserId, $currentUserName, $currentUserDesignation, $collisionWarningTarget$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) obj, (Modifier) null, false, (Shape) null, ButtonDefaults.INSTANCE.buttonColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary-0d7_KjU(), 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14), (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$HomeScreenKt.INSTANCE.m189getLambda$489097040$app(), $composer, 805306368, 494);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$281$lambda$276$lambda$275$lambda$274(OrderCollisionTarget $target, DispatchViewModel $viewModel, String $currentUserId, String $currentUserName, String $currentUserDesignation, MutableState $collisionWarningTarget$delegate) {
        Function0 proceedAction = $target.getOnProceed();
        CustomerJobEntity job = $target.getJob();
        $collisionWarningTarget$delegate.setValue(null);
        $viewModel.takeoverOrder(job, $currentUserId, $currentUserName, $currentUserDesignation, proceedAction);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$281$lambda$279(final MutableState $collisionWarningTarget$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C1229@59993L33,1229@59968L114:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1119425502, $changed, -1, "com.example.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:1229)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 2026084831, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda3
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$281$lambda$279$lambda$278$lambda$277($collisionWarningTarget$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.OutlinedButton((Function0) obj, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons$HomeScreenKt.INSTANCE.m187getLambda$422159316$app(), $composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$281$lambda$279$lambda$278$lambda$277(MutableState $collisionWarningTarget$delegate) {
        $collisionWarningTarget$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$290$lambda$284$lambda$283(final CustomerJobEntity $job, final MutableState $activeLongPressJob$delegate, final MutableState $editingCustomerJob$delegate, SessionManager $sessionManager, MutableState $collisionWarningTarget$delegate) {
        HomeScreen$checkCollisionAndExecute($sessionManager, $collisionWarningTarget$delegate, $job, new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda130
            public final Object invoke() {
                return HomeScreenKt.HomeScreen$lambda$290$lambda$284$lambda$283$lambda$282(CustomerJobEntity.this, $activeLongPressJob$delegate, $editingCustomerJob$delegate);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$290$lambda$284$lambda$283$lambda$282(CustomerJobEntity $job, MutableState $activeLongPressJob$delegate, MutableState $editingCustomerJob$delegate) {
        $activeLongPressJob$delegate.setValue(null);
        $editingCustomerJob$delegate.setValue($job);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$290$lambda$287$lambda$286(final CustomerJobEntity $job, final DispatchViewModel $viewModel, final MutableState $activeLongPressJob$delegate, SessionManager $sessionManager, MutableState $collisionWarningTarget$delegate) {
        HomeScreen$checkCollisionAndExecute($sessionManager, $collisionWarningTarget$delegate, $job, new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda133
            public final Object invoke() {
                return HomeScreenKt.HomeScreen$lambda$290$lambda$287$lambda$286$lambda$285(DispatchViewModel.this, $job, $activeLongPressJob$delegate);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$290$lambda$287$lambda$286$lambda$285(DispatchViewModel $viewModel, CustomerJobEntity $job, MutableState $activeLongPressJob$delegate) {
        $activeLongPressJob$delegate.setValue(null);
        $viewModel.unassignExpert($job);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$290$lambda$289$lambda$288(MutableState $activeLongPressJob$delegate) {
        $activeLongPressJob$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$297$lambda$294$lambda$293(DispatchViewModel $viewModel, MutableState $editingCustomerJob$delegate, CustomerJobEntity updatedJob) {
        Intrinsics.checkNotNullParameter(updatedJob, "updatedJob");
        $viewModel.updateJob(updatedJob);
        $editingCustomerJob$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$297$lambda$296$lambda$295(MutableState $editingCustomerJob$delegate) {
        $editingCustomerJob$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$308$lambda$299$lambda$298(DispatchViewModel $viewModel, DeleteTarget $target) {
        $viewModel.deleteJob(((DeleteTarget.Job) $target).getJob());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$308$lambda$301$lambda$300(DispatchViewModel $viewModel, DeleteTarget $target) {
        $viewModel.deleteExpert(((DeleteTarget.Expert) $target).getExpert());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$308$lambda$303$lambda$302(DispatchViewModel $viewModel, DeleteTarget $target) {
        $viewModel.deleteCategory(((DeleteTarget.Category) $target).getCategory());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$308$lambda$305$lambda$304(Object $onConfirm, MutableState $deleteTarget$delegate) {
        Intrinsics.checkNotNull($onConfirm, "null cannot be cast to non-null type kotlin.Function0<kotlin.Unit>");
        ((Function0) TypeIntrinsics.beforeCheckcastToFunctionOfArity($onConfirm, 0)).invoke();
        $deleteTarget$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$308$lambda$307$lambda$306(MutableState $deleteTarget$delegate) {
        $deleteTarget$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$310$lambda$309(DispatchViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.addNewCategory(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$312$lambda$311(MutableState $showAddNewCategoryDialog$delegate) {
        HomeScreen$lambda$93($showAddNewCategoryDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$315$lambda$314$lambda$313(MutableState $expertForWorkHistory$delegate) {
        $expertForWorkHistory$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$317$lambda$316(DispatchViewModel $viewModel, long it) {
        $viewModel.restoreJobFromRecycleBin(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$319$lambda$318(DispatchViewModel $viewModel, long it) {
        $viewModel.deleteJobPermanently(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$321$lambda$320(DispatchViewModel $viewModel, long it) {
        $viewModel.restoreExpertFromRecycleBin(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$323$lambda$322(DispatchViewModel $viewModel, long it) {
        $viewModel.deleteExpertPermanently(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$325$lambda$324(DispatchViewModel $viewModel) {
        $viewModel.emptyRecycleBin();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit HomeScreen$lambda$327$lambda$326(MutableState $showRecycleBinDialog$delegate) {
        HomeScreen$lambda$27($showRecycleBinDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x04ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void CustomerOrdersSection(final com.example.ui.DispatchViewModel r63, final java.util.List<com.example.data.model.CustomerJobEntity> r64, final com.example.ui.CustomerSubTab r65, final com.example.ui.OrderStatusTab r66, boolean r67, boolean r68, boolean r69, final kotlin.jvm.functions.Function0<kotlin.Unit> r70, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r71, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r72, final kotlin.jvm.functions.Function2<? super com.example.data.model.CustomerJobEntity, ? super java.lang.Boolean, kotlin.Unit> r73, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r74, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r75, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r76, androidx.compose.runtime.Composer r77, final int r78, final int r79, final int r80) {
        /*
            Method dump skipped, instructions count: 1317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.CustomerOrdersSection(com.example.ui.DispatchViewModel, java.util.List, com.example.ui.CustomerSubTab, com.example.ui.OrderStatusTab, boolean, boolean, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int CustomerOrdersSection$lambda$330$lambda$329() {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerOrdersSection$lambda$342$lambda$338(final PagerState $subTabPagerState, final CoroutineScope $coroutineScope, final List $allJobs, Composer $composer, int $changed) {
        Object obj;
        Object obj2;
        ComposerKt.sourceInformation($composer, "C1388@66145L105,1386@66052L300,1395@66458L105,1398@66588L73,1393@66365L310:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(404286994, $changed, -1, "com.example.ui.screens.CustomerOrdersSection.<anonymous>.<anonymous> (HomeScreen.kt:1386)");
            }
            boolean z = $subTabPagerState.getCurrentPage() == 0;
            ComposerKt.sourceInformationMarkerStart($composer, 1972748123, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($coroutineScope) | $composer.changed($subTabPagerState);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda21
                    public final Object invoke() {
                        return HomeScreenKt.CustomerOrdersSection$lambda$342$lambda$338$lambda$334$lambda$333($coroutineScope, $subTabPagerState);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TabKt.Tab-wqdebIU(z, (Function0) obj, (Modifier) null, false, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$1993036792$app(), (Function2) null, 0L, 0L, (MutableInteractionSource) null, $composer, 24576, 492);
            boolean z2 = $subTabPagerState.getCurrentPage() == 1;
            ComposerKt.sourceInformationMarkerStart($composer, 1972758139, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changedInstance2 = $composer.changedInstance($coroutineScope) | $composer.changed($subTabPagerState);
            Object rememberedValue2 = $composer.rememberedValue();
            if (changedInstance2 || rememberedValue2 == Composer.Companion.getEmpty()) {
                obj2 = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda23
                    public final Object invoke() {
                        return HomeScreenKt.CustomerOrdersSection$lambda$342$lambda$338$lambda$336$lambda$335($coroutineScope, $subTabPagerState);
                    }
                };
                $composer.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TabKt.Tab-wqdebIU(z2, (Function0) obj2, (Modifier) null, false, ComposableLambdaKt.rememberComposableLambda(-1075745425, true, new Function2() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda24
                public final Object invoke(Object obj3, Object obj4) {
                    return HomeScreenKt.CustomerOrdersSection$lambda$342$lambda$338$lambda$337($allJobs, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), (Function2) null, 0L, 0L, (MutableInteractionSource) null, $composer, 24576, 492);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerOrdersSection$lambda$342$lambda$338$lambda$334$lambda$333(CoroutineScope $coroutineScope, PagerState $subTabPagerState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$CustomerOrdersSection$3$1$1$1$1($subTabPagerState, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerOrdersSection$lambda$342$lambda$338$lambda$336$lambda$335(CoroutineScope $coroutineScope, PagerState $subTabPagerState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$CustomerOrdersSection$3$1$2$1$1($subTabPagerState, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerOrdersSection$lambda$342$lambda$338$lambda$337(List $allJobs, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1398@66590L69:HomeScreen.kt#2thlc2");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1075745425, $changed, -1, "com.example.ui.screens.CustomerOrdersSection.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:1398)");
            }
            TextKt.Text--4IGK_g("📋 Orders (" + $allJobs.size() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 196608, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerOrdersSection$lambda$342$lambda$341(final DispatchViewModel $viewModel, boolean $canManageOrders, boolean $isViewOnly, Function0 $onOpenWhatsAppParser, Function1 $onOrderSaved, List $allJobs, OrderStatusTab $currentOrderStatusTab, boolean $canDeleteOrders, Function1 $onOpenNearestExperts, Function2 $onCompleteOrCancelAction, Function1 $onShowCompletedDetail, Function1 $onDeleteJob, Function1 $onLongPressOrder, PagerScope $this$HorizontalPager, int page, Composer $composer, int $changed) {
        Object obj;
        Intrinsics.checkNotNullParameter($this$HorizontalPager, "$this$HorizontalPager");
        ComposerKt.sourceInformation($composer, "C:HomeScreen.kt#2thlc2");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1128033124, $changed, -1, "com.example.ui.screens.CustomerOrdersSection.<anonymous>.<anonymous> (HomeScreen.kt:1406)");
        }
        if (page == 0) {
            $composer.startReplaceGroup(-1410626213);
            ComposerKt.sourceInformation($composer, "1407@66861L299");
            DispatchOrderFormContent($viewModel, $canManageOrders, $isViewOnly, $onOpenWhatsAppParser, $onOrderSaved, $composer, 0, 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-1410280470);
            ComposerKt.sourceInformation($composer, "1421@67501L38,1415@67198L668");
            ComposerKt.sourceInformationMarkerStart($composer, -1708051294, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($viewModel);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function1() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda115
                    public final Object invoke(Object obj2) {
                        return HomeScreenKt.CustomerOrdersSection$lambda$342$lambda$341$lambda$340$lambda$339(DispatchViewModel.this, (OrderStatusTab) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OrdersListContent($viewModel, $allJobs, $currentOrderStatusTab, $canDeleteOrders, $isViewOnly, (Function1) obj, $onOpenNearestExperts, $onCompleteOrCancelAction, $onShowCompletedDetail, $onDeleteJob, $onLongPressOrder, $composer, 0, 0, 0);
            $composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit CustomerOrdersSection$lambda$342$lambda$341$lambda$340$lambda$339(DispatchViewModel $viewModel, OrderStatusTab it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.selectOrderStatusTab(it);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x0609  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0687  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0705  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x06f4  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0438  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x042c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x04df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void DispatchOrderFormContent(final com.example.ui.DispatchViewModel r38, boolean r39, boolean r40, final kotlin.jvm.functions.Function0<kotlin.Unit> r41, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r42, androidx.compose.runtime.Composer r43, final int r44, final int r45) {
        /*
            Method dump skipped, instructions count: 1828
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.DispatchOrderFormContent(com.example.ui.DispatchViewModel, boolean, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final CustomerFormState DispatchOrderFormContent$lambda$344(State<CustomerFormState> state) {
        return (CustomerFormState) state.getValue();
    }

    private static final List<ExpertCategoryEntity> DispatchOrderFormContent$lambda$345(State<? extends List<ExpertCategoryEntity>> state) {
        return (List) state.getValue();
    }

    private static final boolean DispatchOrderFormContent$lambda$352(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void DispatchOrderFormContent$lambda$353(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean DispatchOrderFormContent$lambda$355(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void DispatchOrderFormContent$lambda$356(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean DispatchOrderFormContent$lambda$358(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void DispatchOrderFormContent$lambda$359(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$361$lambda$360(Context $context, DispatchViewModel $viewModel, MutableState $showGpsDisabledDialog$delegate, MutableState $showPermissionDeniedDialog$delegate, Map permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        boolean granted = Intrinsics.areEqual(permissions.get("android.permission.ACCESS_FINE_LOCATION"), true) || Intrinsics.areEqual(permissions.get("android.permission.ACCESS_COARSE_LOCATION"), true);
        if (granted) {
            if (!LocationHelper.INSTANCE.isLocationEnabled($context)) {
                DispatchOrderFormContent$lambda$356($showGpsDisabledDialog$delegate, true);
            } else {
                $viewModel.fetchCurrentGps($context);
            }
        } else {
            DispatchOrderFormContent$lambda$353($showPermissionDeniedDialog$delegate, true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391(final DispatchViewModel $viewModel, final boolean $isPhoneValid, final List $matchingCategories, final boolean $canManageOrders, final boolean $isViewOnly, final Function0 $onOpenWhatsAppParser, final State $form$delegate, final String $cleanPhone, final Context $context, final ManagedActivityResultLauncher $locationPermissionLauncher, final MutableState $showGpsDisabledDialog$delegate, final MutableState $showConfirmSaveOrderDialog$delegate, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default($this$LazyColumn, (Object) null, (Object) null, ComposableLambdaKt.composableLambdaInstance(-2030968837, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda91
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.DispatchOrderFormContent$lambda$392$lambda$391$lambda$390(DispatchViewModel.this, $isPhoneValid, $matchingCategories, $canManageOrders, $isViewOnly, $onOpenWhatsAppParser, $form$delegate, $cleanPhone, $context, $locationPermissionLauncher, $showGpsDisabledDialog$delegate, $showConfirmSaveOrderDialog$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390(final DispatchViewModel $viewModel, final boolean $isPhoneValid, final List $matchingCategories, final boolean $canManageOrders, final boolean $isViewOnly, final Function0 $onOpenWhatsAppParser, final State $form$delegate, final String $cleanPhone, final Context $context, final ManagedActivityResultLauncher $locationPermissionLauncher, final MutableState $showGpsDisabledDialog$delegate, final MutableState $showConfirmSaveOrderDialog$delegate, LazyItemScope $this$item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$item, "$this$item");
        ComposerKt.sourceInformation($composer, "C1497@70527L11,1498@70636L11,1498@70594L62,1499@70699L38,1500@70752L11079,1494@70358L11473:HomeScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2030968837, $changed, -1, "com.example.ui.screens.DispatchOrderFormContent.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:1494)");
            }
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14)), CardDefaults.INSTANCE.cardColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface-0d7_KjU(), 0L, 0L, 0L, $composer, CardDefaults.$stable << 12, 14), CardDefaults.INSTANCE.cardElevation-aqJV_2Y(Dp.constructor-impl(2), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer, (CardDefaults.$stable << 18) | 6, 62), BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl((float) 1.2d), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOutlineVariant-0d7_KjU()), ComposableLambdaKt.rememberComposableLambda(996985069, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda90
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389(DispatchViewModel.this, $isPhoneValid, $matchingCategories, $canManageOrders, $isViewOnly, $onOpenWhatsAppParser, $form$delegate, $cleanPhone, $context, $locationPermissionLauncher, $showGpsDisabledDialog$delegate, $showConfirmSaveOrderDialog$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 196614, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0ab4  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0ad3  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0b1d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0ba1  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0c49 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0cbb  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0d1f  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0cc9  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0bb1  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0b2b  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0a4f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0a08  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x091f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x088c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x084f  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x058a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x04e3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x041c  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x04a7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x057d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x05ec  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x074a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x087f  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0912  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x09f6  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0a02  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0a39  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389(final com.example.ui.DispatchViewModel r97, final boolean r98, java.util.List r99, final boolean r100, final boolean r101, kotlin.jvm.functions.Function0 r102, final androidx.compose.runtime.State r103, final java.lang.String r104, final android.content.Context r105, final androidx.activity.compose.ManagedActivityResultLauncher r106, final androidx.compose.runtime.MutableState r107, final androidx.compose.runtime.MutableState r108, androidx.compose.foundation.layout.ColumnScope r109, androidx.compose.runtime.Composer r110, int r111) {
        /*
            Method dump skipped, instructions count: 3365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389(com.example.ui.DispatchViewModel, boolean, java.util.List, boolean, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.State, java.lang.String, android.content.Context, androidx.activity.compose.ManagedActivityResultLauncher, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$364$lambda$363(DispatchViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.updateName(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$367$lambda$366(DispatchViewModel $viewModel, String input) {
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
        String digits = StringsKt.take(((StringBuilder) sb).toString(), 10);
        $viewModel.updatePhone(digits);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$368(boolean $isPhoneValid, String $cleanPhone, State $form$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-257109375, $changed, -1, "com.example.ui.screens.DispatchOrderFormContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:1566)");
            }
            if (!StringsKt.isBlank(DispatchOrderFormContent$lambda$344($form$delegate).getPhone()) && !$isPhoneValid) {
                $composer.startReplaceGroup(-1618621379);
                ComposerKt.sourceInformation($composer, "1567@74047L11,1567@73965L100");
                TextKt.Text--4IGK_g("Must be exactly 10 digits (" + $cleanPhone.length() + "/10)", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131066);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1618454041);
                ComposerKt.sourceInformation($composer, "1569@74135L58");
                TextKt.Text--4IGK_g("Required 10-digit mobile (" + $cleanPhone.length() + "/10)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$370$lambda$369(DispatchViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.updateServiceType(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$375$lambda$374$lambda$372$lambda$371(DispatchViewModel $viewModel, String $catName) {
        $viewModel.updateServiceType($catName);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$375$lambda$374$lambda$373(String $catName, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1608@76333L11,1604@76069L438:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-602019209, $changed, -1, "com.example.ui.screens.DispatchOrderFormContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:1604)");
            }
            TextKt.Text--4IGK_g("💡 " + $catName, PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(10), Dp.constructor-impl(5)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryContainer-0d7_KjU(), TextUnitKt.getSp(11.5d), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$377$lambda$376(DispatchViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.updateIssue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$379$lambda$378(DispatchViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.updateAddress(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$384$lambda$381$lambda$380(DispatchViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.updateLocationInput(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$384$lambda$383$lambda$382(Context $context, ManagedActivityResultLauncher $locationPermissionLauncher, DispatchViewModel $viewModel, MutableState $showGpsDisabledDialog$delegate) {
        if (!LocationHelper.INSTANCE.isLocationPermissionGranted($context)) {
            $locationPermissionLauncher.launch(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
        } else if (!LocationHelper.INSTANCE.isLocationEnabled($context)) {
            DispatchOrderFormContent$lambda$356($showGpsDisabledDialog$delegate, true);
        } else {
            $viewModel.fetchCurrentGps($context);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$386$lambda$385(MutableState $showConfirmSaveOrderDialog$delegate) {
        DispatchOrderFormContent$lambda$359($showConfirmSaveOrderDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$392$lambda$391$lambda$390$lambda$389$lambda$388$lambda$387(boolean $isViewOnly, boolean $canManageOrders, RowScope $this$Button, Composer $composer, int $changed) {
        String str;
        Intrinsics.checkNotNullParameter($this$Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1703@81414L363:HomeScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(615270739, $changed, -1, "com.example.ui.screens.DispatchOrderFormContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:1703)");
            }
            if ($isViewOnly) {
                str = "🔒 View Only Mode (Read Only)";
            } else {
                str = !$canManageOrders ? "🔒 Dispatching Disabled by Admin" : "Save Customer Order";
            }
            TextKt.Text--4IGK_g(str, (Modifier) null, 0L, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$397$lambda$396(Context $context, DispatchViewModel $viewModel, MutableState $showConfirmSaveOrderDialog$delegate, final Function1 $onOrderSaved) {
        DispatchOrderFormContent$lambda$359($showConfirmSaveOrderDialog$delegate, false);
        SessionManager sessionManager = new SessionManager($context);
        String cId = new Regex("[^0-9]").replace(sessionManager.getUserPhone(), "");
        String userName = sessionManager.getUserName();
        if (StringsKt.isBlank(userName)) {
            userName = "User";
        }
        String cName = userName;
        String cDesig = sessionManager.getUserDesignationTag();
        $viewModel.saveCustomerOrder(JobStatus.PENDING, cId, cName, cDesig, new Function1() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda220
            public final Object invoke(Object obj) {
                return HomeScreenKt.DispatchOrderFormContent$lambda$397$lambda$396$lambda$395($onOrderSaved, (CustomerJobEntity) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$397$lambda$396$lambda$395(Function1 $onOrderSaved, CustomerJobEntity savedJob) {
        Intrinsics.checkNotNullParameter(savedJob, "savedJob");
        $onOrderSaved.invoke(savedJob);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$399$lambda$398(MutableState $showConfirmSaveOrderDialog$delegate) {
        DispatchOrderFormContent$lambda$359($showConfirmSaveOrderDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$401$lambda$400(MutableState $showPermissionDeniedDialog$delegate) {
        DispatchOrderFormContent$lambda$353($showPermissionDeniedDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$403$lambda$402(Context $context, MutableState $showPermissionDeniedDialog$delegate) {
        DispatchOrderFormContent$lambda$353($showPermissionDeniedDialog$delegate, false);
        LocationHelper.INSTANCE.openAppSettings($context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$405$lambda$404(MutableState $showGpsDisabledDialog$delegate) {
        DispatchOrderFormContent$lambda$356($showGpsDisabledDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit DispatchOrderFormContent$lambda$407$lambda$406(Context $context, MutableState $showGpsDisabledDialog$delegate) {
        DispatchOrderFormContent$lambda$356($showGpsDisabledDialog$delegate, false);
        LocationHelper.INSTANCE.openLocationSettings($context);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0377  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void OrdersListContent(final com.example.ui.DispatchViewModel r34, final java.util.List<com.example.data.model.CustomerJobEntity> r35, final com.example.ui.OrderStatusTab r36, boolean r37, boolean r38, final kotlin.jvm.functions.Function1<? super com.example.ui.OrderStatusTab, kotlin.Unit> r39, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r40, final kotlin.jvm.functions.Function2<? super com.example.data.model.CustomerJobEntity, ? super java.lang.Boolean, kotlin.Unit> r41, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r42, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r43, final kotlin.jvm.functions.Function1<? super com.example.data.model.CustomerJobEntity, kotlin.Unit> r44, androidx.compose.runtime.Composer r45, final int r46, final int r47, final int r48) {
        /*
            Method dump skipped, instructions count: 1384
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.OrdersListContent(com.example.ui.DispatchViewModel, java.util.List, com.example.ui.OrderStatusTab, boolean, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final String OrdersListContent$lambda$410(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String OrdersListContent$lambda$413(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final Long OrdersListContent$lambda$416(MutableState<Long> mutableState) {
        return (Long) ((State) mutableState).getValue();
    }

    private static final Long OrdersListContent$lambda$419(MutableState<Long> mutableState) {
        return (Long) ((State) mutableState).getValue();
    }

    private static final boolean OrdersListContent$lambda$422(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void OrdersListContent$lambda$423(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean OrdersListContent$matchesDate(MutableState<String> mutableState, MutableState<Long> mutableState2, MutableState<Long> mutableState3, long timestamp) {
        long now = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        String OrdersListContent$lambda$413 = OrdersListContent$lambda$413(mutableState);
        switch (OrdersListContent$lambda$413.hashCode()) {
            case 79996705:
                if (!OrdersListContent$lambda$413.equals("TODAY")) {
                    return true;
                }
                cal.setTimeInMillis(now);
                cal.set(11, 0);
                cal.set(12, 0);
                cal.set(13, 0);
                return timestamp >= cal.getTimeInMillis();
            case 1202840959:
                if (!OrdersListContent$lambda$413.equals("THIS_MONTH")) {
                    return true;
                }
                cal.setTimeInMillis(now);
                cal.set(5, 1);
                cal.set(11, 0);
                cal.set(12, 0);
                cal.set(13, 0);
                return timestamp >= cal.getTimeInMillis();
            case 1732421928:
                if (!OrdersListContent$lambda$413.equals("LAST_7_DAYS")) {
                    return true;
                }
                long sevenDaysAgo = now - 604800000;
                return timestamp >= sevenDaysAgo;
            case 1999208305:
                if (!OrdersListContent$lambda$413.equals("CUSTOM") || OrdersListContent$lambda$416(mutableState2) == null || OrdersListContent$lambda$419(mutableState3) == null) {
                    return true;
                }
                Long OrdersListContent$lambda$416 = OrdersListContent$lambda$416(mutableState2);
                Intrinsics.checkNotNull(OrdersListContent$lambda$416);
                long longValue = OrdersListContent$lambda$416.longValue();
                Long OrdersListContent$lambda$419 = OrdersListContent$lambda$419(mutableState3);
                Intrinsics.checkNotNull(OrdersListContent$lambda$419);
                return timestamp <= OrdersListContent$lambda$419.longValue() && longValue <= timestamp;
            default:
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$430$lambda$429(MutableState $showCustomDatePicker$delegate) {
        OrdersListContent$lambda$423($showCustomDatePicker$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$432$lambda$431(MutableState $customStartDate$delegate, MutableState $customEndDate$delegate, MutableState $selectedDateFilter$delegate, MutableState $showCustomDatePicker$delegate, long start, long end) {
        $customStartDate$delegate.setValue(Long.valueOf(start));
        $customEndDate$delegate.setValue(Long.valueOf(end));
        $selectedDateFilter$delegate.setValue("CUSTOM");
        OrdersListContent$lambda$423($showCustomDatePicker$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x04d1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x018b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrdersListContent$lambda$483(final kotlin.enums.EnumEntries r57, final java.util.List r58, final androidx.compose.runtime.MutableState r59, final androidx.compose.runtime.MutableState r60, androidx.compose.runtime.MutableState r61, androidx.compose.runtime.MutableState r62, final kotlin.jvm.functions.Function1 r63, final kotlinx.coroutines.CoroutineScope r64, final androidx.compose.foundation.pager.PagerState r65, final boolean r66, final boolean r67, final kotlin.jvm.functions.Function1 r68, final kotlin.jvm.functions.Function2 r69, final kotlin.jvm.functions.Function1 r70, final kotlin.jvm.functions.Function1 r71, final kotlin.jvm.functions.Function1 r72, final com.example.ui.DispatchViewModel r73, final androidx.compose.runtime.MutableState r74, androidx.compose.foundation.pager.PagerScope r75, int r76, androidx.compose.runtime.Composer r77, int r78) {
        /*
            Method dump skipped, instructions count: 1239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.OrdersListContent$lambda$483(kotlin.enums.EnumEntries, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, kotlin.jvm.functions.Function1, kotlinx.coroutines.CoroutineScope, androidx.compose.foundation.pager.PagerState, boolean, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, com.example.ui.DispatchViewModel, androidx.compose.runtime.MutableState, androidx.compose.foundation.pager.PagerScope, int, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final boolean OrdersListContent$lambda$483$lambda$437(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean OrdersListContent$lambda$483$lambda$436$lambda$435(LazyListState $listState) {
        return $listState.getFirstVisibleItemIndex() > 0 || $listState.getFirstVisibleItemScrollOffset() > 20;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478(final List $jobsForThisPage, final EnumEntries $statusTabs, final List $allJobs, final OrderStatusTab $pageStatus, final Function1 $onSelectStatusTab, final CoroutineScope $coroutineScope, final PagerState $pagerState, final MutableState $searchQuery$delegate, final MutableState $selectedDateFilter$delegate, final MutableState $showCustomDatePicker$delegate, final MutableState $customStartDate$delegate, final MutableState $customEndDate$delegate, final boolean $canDeleteOrders, final boolean $isViewOnly, final Function1 $onOpenNearestExperts, final Function2 $onCompleteOrCancelAction, final Function1 $onShowCompletedDetail, final Function1 $onDeleteJob, final Function1 $onLongPressOrder, final DispatchViewModel $viewModel, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default($this$LazyColumn, "status_chips", (Object) null, ComposableLambdaKt.composableLambdaInstance(2107059717, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda197
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$444($statusTabs, $allJobs, $pageStatus, $onSelectStatusTab, $coroutineScope, $pagerState, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 2, (Object) null);
        LazyListScope.item$default($this$LazyColumn, "search_bar", (Object) null, ComposableLambdaKt.composableLambdaInstance(707716974, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda198
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$450($searchQuery$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 2, (Object) null);
        LazyListScope.item$default($this$LazyColumn, "date_chips", (Object) null, ComposableLambdaKt.composableLambdaInstance(292669389, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda199
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$463($selectedDateFilter$delegate, $showCustomDatePicker$delegate, $customStartDate$delegate, $customEndDate$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 2, (Object) null);
        if ($jobsForThisPage.isEmpty()) {
            LazyListScope.item$default($this$LazyColumn, "empty_jobs", (Object) null, ComposableLambdaKt.composableLambdaInstance(1586301642, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda200
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$466(OrderStatusTab.this, $searchQuery$delegate, $selectedDateFilter$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 2, (Object) null);
        } else {
            final Function1 function1 = new Function1() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda201
                public final Object invoke(Object obj) {
                    return HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$467((CustomerJobEntity) obj);
                }
            };
            final Function1 function12 = new Function1() { // from class: com.example.ui.screens.HomeScreenKt$OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$$inlined$items$default$1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return m203invoke((CustomerJobEntity) p1);
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final Void m203invoke(CustomerJobEntity customerJobEntity) {
                    return null;
                }
            };
            $this$LazyColumn.items($jobsForThisPage.size(), new Function1<Integer, Object>() { // from class: com.example.ui.screens.HomeScreenKt$OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$$inlined$items$default$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke(((Number) p1).intValue());
                }

                public final Object invoke(int index) {
                    return function1.invoke($jobsForThisPage.get(index));
                }
            }, new Function1<Integer, Object>() { // from class: com.example.ui.screens.HomeScreenKt$OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$$inlined$items$default$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke(((Number) p1).intValue());
                }

                public final Object invoke(int index) {
                    return function12.invoke($jobsForThisPage.get(index));
                }
            }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.screens.HomeScreenKt$OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$$inlined$items$default$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(4);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                    invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:101:0x038b  */
                /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:106:0x030e A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:108:0x02cc A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:110:0x0288 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:114:0x0221 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:119:0x01c5  */
                /* JADX WARN: Removed duplicated region for block: B:56:0x019a  */
                /* JADX WARN: Removed duplicated region for block: B:61:0x01b6  */
                /* JADX WARN: Removed duplicated region for block: B:66:0x01f6  */
                /* JADX WARN: Removed duplicated region for block: B:71:0x0212  */
                /* JADX WARN: Removed duplicated region for block: B:76:0x025e  */
                /* JADX WARN: Removed duplicated region for block: B:81:0x027a  */
                /* JADX WARN: Removed duplicated region for block: B:86:0x02bd  */
                /* JADX WARN: Removed duplicated region for block: B:91:0x02ff  */
                /* JADX WARN: Removed duplicated region for block: B:96:0x0341  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.lazy.LazyItemScope r28, int r29, androidx.compose.runtime.Composer r30, int r31) {
                    /*
                        Method dump skipped, instructions count: 911
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt$OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$$inlined$items$default$4.invoke(androidx.compose.foundation.lazy.LazyItemScope, int, androidx.compose.runtime.Composer, int):void");
                }
            }));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0412  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$444(kotlin.enums.EnumEntries r65, java.util.List r66, com.example.ui.OrderStatusTab r67, kotlin.jvm.functions.Function1 r68, final kotlinx.coroutines.CoroutineScope r69, final androidx.compose.foundation.pager.PagerState r70, androidx.compose.foundation.lazy.LazyItemScope r71, androidx.compose.runtime.Composer r72, int r73) {
        /*
            Method dump skipped, instructions count: 1060
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$444(kotlin.enums.EnumEntries, java.util.List, com.example.ui.OrderStatusTab, kotlin.jvm.functions.Function1, kotlinx.coroutines.CoroutineScope, androidx.compose.foundation.pager.PagerState, androidx.compose.foundation.lazy.LazyItemScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$444$lambda$443$lambda$442$lambda$440$lambda$439(Function1 $onSelectStatusTab, OrderStatusTab $tab, CoroutineScope $coroutineScope, PagerState $pagerState) {
        $onSelectStatusTab.invoke($tab);
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$OrdersListContent$5$1$1$1$1$1$1$1$1$1($pagerState, $tab, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$444$lambda$443$lambda$442$lambda$441(OrderStatusTab $tab, int $count, boolean $isSelected, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1913@90235L223:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(466548416, $changed, -1, "com.example.ui.screens.OrdersListContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:1913)");
            }
            String str = $tab.getLabel() + " (" + $count + ")";
            FontWeight.Companion companion = FontWeight.Companion;
            TextKt.Text--4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, $isSelected ? companion.getBold() : companion.getNormal(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$450(final MutableState $searchQuery$delegate, LazyItemScope $this$item, Composer $composer, int $changed) {
        Object obj;
        Intrinsics.checkNotNullParameter($this$item, "$this$item");
        ComposerKt.sourceInformation($composer, "C1933@91227L20,1936@91471L320,1931@91123L935:HomeScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(707716974, $changed, -1, "com.example.ui.screens.OrdersListContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:1931)");
            }
            String OrdersListContent$lambda$410 = OrdersListContent$lambda$410($searchQuery$delegate);
            Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10));
            Modifier modifier = PaddingKt.padding-VpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, Dp.constructor-impl(2), 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart($composer, 1511183298, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function1() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda6
                    public final Object invoke(Object obj2) {
                        return HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$450$lambda$446$lambda$445($searchQuery$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(OrdersListContent$lambda$410, (Function1) obj, modifier, false, false, (TextStyle) null, (Function2) null, ComposableSingletons$HomeScreenKt.INSTANCE.m180getLambda$1826391019$app(), ComposableSingletons$HomeScreenKt.INSTANCE.m184getLambda$2046171626$app(), ComposableLambdaKt.rememberComposableLambda(2029015063, true, new Function2() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda7
                public final Object invoke(Object obj2, Object obj3) {
                    return HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$450$lambda$449($searchQuery$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, shape, (TextFieldColors) null, $composer, 918553008, 12582912, 0, 6159480);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$450$lambda$446$lambda$445(MutableState $searchQuery$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $searchQuery$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$450$lambda$449(final MutableState $searchQuery$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2029015063, $changed, -1, "com.example.ui.screens.OrdersListContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:1937)");
            }
            if (OrdersListContent$lambda$410($searchQuery$delegate).length() > 0) {
                $composer.startReplaceGroup(-981915295);
                ComposerKt.sourceInformation($composer, "1938@91586L20,1938@91565L170");
                ComposerKt.sourceInformationMarkerStart($composer, -31673141, "CC(remember):HomeScreen.kt#9igjgp");
                Object rememberedValue = $composer.rememberedValue();
                if (rememberedValue == Composer.Companion.getEmpty()) {
                    obj = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda92
                        public final Object invoke() {
                            return HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$450$lambda$449$lambda$448$lambda$447($searchQuery$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                } else {
                    obj = rememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconButtonKt.IconButton((Function0) obj, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$HomeScreenKt.INSTANCE.m182getLambda$1980677617$app(), $composer, 196614, 30);
            } else {
                $composer.startReplaceGroup(-1072722293);
            }
            $composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$450$lambda$449$lambda$448$lambda$447(MutableState $searchQuery$delegate) {
        $searchQuery$delegate.setValue("");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$463(final androidx.compose.runtime.MutableState r42, final androidx.compose.runtime.MutableState r43, final androidx.compose.runtime.MutableState r44, final androidx.compose.runtime.MutableState r45, androidx.compose.foundation.lazy.LazyItemScope r46, androidx.compose.runtime.Composer r47, int r48) {
        /*
            Method dump skipped, instructions count: 909
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$463(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.lazy.LazyItemScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$463$lambda$462$lambda$452$lambda$451(MutableState $selectedDateFilter$delegate) {
        $selectedDateFilter$delegate.setValue("ALL");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$463$lambda$462$lambda$454$lambda$453(MutableState $selectedDateFilter$delegate) {
        $selectedDateFilter$delegate.setValue("TODAY");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$463$lambda$462$lambda$456$lambda$455(MutableState $selectedDateFilter$delegate) {
        $selectedDateFilter$delegate.setValue("LAST_7_DAYS");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$463$lambda$462$lambda$458$lambda$457(MutableState $selectedDateFilter$delegate) {
        $selectedDateFilter$delegate.setValue("THIS_MONTH");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$463$lambda$462$lambda$460$lambda$459(MutableState $showCustomDatePicker$delegate) {
        OrdersListContent$lambda$423($showCustomDatePicker$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$463$lambda$462$lambda$461(MutableState $selectedDateFilter$delegate, MutableState $customStartDate$delegate, MutableState $customEndDate$delegate, Composer $composer, int $changed) {
        String labelText;
        ComposerKt.sourceInformation($composer, "C1988@94241L15:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1953208092, $changed, -1, "com.example.ui.screens.OrdersListContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:1984)");
            }
            if (Intrinsics.areEqual(OrdersListContent$lambda$413($selectedDateFilter$delegate), "CUSTOM") && OrdersListContent$lambda$416($customStartDate$delegate) != null && OrdersListContent$lambda$419($customEndDate$delegate) != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM", Locale.getDefault());
                Long OrdersListContent$lambda$416 = OrdersListContent$lambda$416($customStartDate$delegate);
                Intrinsics.checkNotNull(OrdersListContent$lambda$416);
                String format = df.format(new Date(OrdersListContent$lambda$416.longValue()));
                Long OrdersListContent$lambda$419 = OrdersListContent$lambda$419($customEndDate$delegate);
                Intrinsics.checkNotNull(OrdersListContent$lambda$419);
                labelText = "📅 " + format + " - " + df.format(new Date(OrdersListContent$lambda$419.longValue()));
            } else {
                labelText = "📅 Custom Range";
            }
            TextKt.Text--4IGK_g(labelText, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$466(com.example.ui.OrderStatusTab r71, androidx.compose.runtime.MutableState r72, androidx.compose.runtime.MutableState r73, androidx.compose.foundation.lazy.LazyItemScope r74, androidx.compose.runtime.Composer r75, int r76) {
        /*
            Method dump skipped, instructions count: 820
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$466(com.example.ui.OrderStatusTab, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.foundation.lazy.LazyItemScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Object OrdersListContent$lambda$483$lambda$482$lambda$479$lambda$478$lambda$467(CustomerJobEntity it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrdersListContent$lambda$483$lambda$482$lambda$481$lambda$480(CoroutineScope $coroutineScope, LazyListState $listState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$OrdersListContent$5$1$2$1$1($listState, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static final void OrderItemCard(final CustomerJobEntity job, final OrderStatusTab currentStatus, boolean canDeleteOrders, boolean isViewOnly, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03, final Function0<Unit> function04, final Function0<Unit> function05, Function0<Unit> function06, final Function2<? super Long, ? super Boolean, Unit> function2, final Function2<? super Long, ? super Boolean, Unit> function22, final Function2<? super Long, ? super Boolean, Unit> function23, Composer $composer, final int $changed, final int $changed1, final int i) {
        CustomerJobEntity customerJobEntity;
        boolean z;
        boolean z2;
        int i2;
        boolean isViewOnly2;
        SimpleDateFormat dateFormat;
        Object obj;
        int $dirty;
        Object obj2;
        Object obj3;
        MutableState confirmMessage$delegate;
        Object obj4;
        MutableState confirmButtonText$delegate;
        Object obj5;
        MutableState confirmIsDestructive$delegate;
        Object obj6;
        MutableState confirmIcon$delegate;
        Object obj7;
        Object obj8;
        MutableState confirmAction$delegate;
        Object obj9;
        final Function0 onLongPress;
        int i3;
        int i4;
        long j;
        Object obj10;
        Object obj11;
        Composer $composer2;
        final boolean canDeleteOrders2;
        final boolean isViewOnly3;
        final Function0 onLongPress2;
        Object obj12;
        Composer $composer3 = $composer.startRestartGroup(845183003);
        ComposerKt.sourceInformation($composer3, "C(OrderItemCard)P(3,1!2,8,5!1,9!2,12,11)2078@98553L7,2079@98582L69,2081@98682L34,2082@98741L31,2083@98799L31,2084@98860L38,2085@98931L34,2086@98989L50,2087@99065L43,2138@100740L61,2133@100550L158,2143@100912L11,2144@101013L11,2144@100971L62,2145@101068L38,2146@101113L29767,2129@100430L30450:HomeScreen.kt#2thlc2");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            customerJobEntity = job;
            $dirty2 |= $composer3.changed(customerJobEntity) ? 4 : 2;
        } else {
            customerJobEntity = job;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(currentStatus.ordinal()) ? 32 : 16;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty2 |= 384;
            z = canDeleteOrders;
        } else if (($changed & 384) == 0) {
            z = canDeleteOrders;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = canDeleteOrders;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty2 |= 3072;
            z2 = isViewOnly;
        } else if (($changed & 3072) == 0) {
            z2 = isViewOnly;
            $dirty2 |= $composer3.changed(z2) ? 2048 : 1024;
        } else {
            z2 = isViewOnly;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 16384 : 8192;
        }
        if (($changed & 196608) == 0) {
            i2 = i5;
            $dirty2 |= $composer3.changedInstance(function02) ? 131072 : 65536;
        } else {
            i2 = i5;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function03) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function04) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(function05) ? 67108864 : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changedInstance(function06) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(function22) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changedInstance(function23) ? 256 : 128;
        }
        if (($dirty2 & 306783379) == 306783378 && ($dirty1 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            onLongPress2 = function06;
            canDeleteOrders2 = z;
            $composer2 = $composer3;
            isViewOnly3 = z2;
        } else {
            boolean canDeleteOrders3 = i2 != 0 ? true : z;
            boolean isViewOnly4 = i6 != 0 ? false : z2;
            Function0 onLongPress3 = i7 != 0 ? null : function06;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(845183003, $dirty2, $dirty1, "com.example.ui.screens.OrderItemCard (HomeScreen.kt:2077)");
            }
            CompositionLocal localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            final boolean canDeleteOrders4 = canDeleteOrders3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer3, 472067872, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                isViewOnly2 = isViewOnly4;
                rememberedValue = new SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault());
                $composer3.updateRememberedValue(rememberedValue);
            } else {
                isViewOnly2 = isViewOnly4;
            }
            SimpleDateFormat dateFormat2 = (SimpleDateFormat) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 472071037, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                dateFormat = dateFormat2;
                obj = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj);
            } else {
                dateFormat = dateFormat2;
                obj = rememberedValue2;
            }
            final MutableState showConfirmDialog$delegate = (MutableState) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 472072922, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue3 = $composer3.rememberedValue();
            if (rememberedValue3 == Composer.Companion.getEmpty()) {
                $dirty = $dirty2;
                obj2 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj2);
            } else {
                $dirty = $dirty2;
                obj2 = rememberedValue3;
            }
            final MutableState confirmTitle$delegate = (MutableState) obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 472074778, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue4 = $composer3.rememberedValue();
            if (rememberedValue4 == Composer.Companion.getEmpty()) {
                obj3 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj3);
            } else {
                obj3 = rememberedValue4;
            }
            MutableState confirmMessage$delegate2 = (MutableState) obj3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 472076737, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue5 = $composer3.rememberedValue();
            if (rememberedValue5 == Composer.Companion.getEmpty()) {
                confirmMessage$delegate = confirmMessage$delegate2;
                obj4 = SnapshotStateKt.mutableStateOf$default("Confirm", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj4);
            } else {
                confirmMessage$delegate = confirmMessage$delegate2;
                obj4 = rememberedValue5;
            }
            MutableState confirmButtonText$delegate2 = (MutableState) obj4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 472079005, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue6 = $composer3.rememberedValue();
            if (rememberedValue6 == Composer.Companion.getEmpty()) {
                confirmButtonText$delegate = confirmButtonText$delegate2;
                obj5 = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj5);
            } else {
                confirmButtonText$delegate = confirmButtonText$delegate2;
                obj5 = rememberedValue6;
            }
            MutableState confirmIsDestructive$delegate2 = (MutableState) obj5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 472080877, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue7 = $composer3.rememberedValue();
            if (rememberedValue7 == Composer.Companion.getEmpty()) {
                confirmIsDestructive$delegate = confirmIsDestructive$delegate2;
                obj6 = SnapshotStateKt.mutableStateOf$default(WarningKt.getWarning(Icons.INSTANCE.getDefault()), (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj6);
            } else {
                confirmIsDestructive$delegate = confirmIsDestructive$delegate2;
                obj6 = rememberedValue7;
            }
            MutableState confirmIcon$delegate2 = (MutableState) obj6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 472083302, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue8 = $composer3.rememberedValue();
            if (rememberedValue8 == Composer.Companion.getEmpty()) {
                confirmIcon$delegate = confirmIcon$delegate2;
                obj7 = null;
                obj8 = SnapshotStateKt.mutableStateOf$default(new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda216
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                }, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj8);
            } else {
                confirmIcon$delegate = confirmIcon$delegate2;
                obj7 = null;
                obj8 = rememberedValue8;
            }
            final MutableState confirmAction$delegate2 = (MutableState) obj8;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (OrderItemCard$lambda$487(showConfirmDialog$delegate)) {
                $composer3.startReplaceGroup(1750278094);
                ComposerKt.sourceInformation($composer3, "2113@99936L89,2117@100051L29,2107@99691L399");
                Function0 onLongPress4 = onLongPress3;
                String OrderItemCard$lambda$490 = OrderItemCard$lambda$490(confirmTitle$delegate);
                String OrderItemCard$lambda$493 = OrderItemCard$lambda$493(confirmMessage$delegate);
                String OrderItemCard$lambda$496 = OrderItemCard$lambda$496(confirmButtonText$delegate);
                boolean OrderItemCard$lambda$499 = OrderItemCard$lambda$499(confirmIsDestructive$delegate);
                ImageVector OrderItemCard$lambda$502 = OrderItemCard$lambda$502(confirmIcon$delegate);
                ComposerKt.sourceInformationMarkerStart($composer3, 472111220, "CC(remember):HomeScreen.kt#9igjgp");
                Object rememberedValue9 = $composer3.rememberedValue();
                if (rememberedValue9 == Composer.Companion.getEmpty()) {
                    obj12 = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda227
                        public final Object invoke() {
                            return HomeScreenKt.OrderItemCard$lambda$509$lambda$508(showConfirmDialog$delegate, confirmAction$delegate2);
                        }
                    };
                    $composer3.updateRememberedValue(obj12);
                } else {
                    obj12 = rememberedValue9;
                }
                Function0 function07 = (Function0) obj12;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 472114840, "CC(remember):HomeScreen.kt#9igjgp");
                confirmAction$delegate = confirmAction$delegate2;
                Object rememberedValue10 = $composer3.rememberedValue();
                if (rememberedValue10 == Composer.Companion.getEmpty()) {
                    rememberedValue10 = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda11
                        public final Object invoke() {
                            return HomeScreenKt.OrderItemCard$lambda$511$lambda$510(showConfirmDialog$delegate);
                        }
                    };
                    $composer3.updateRememberedValue(rememberedValue10);
                }
                Function0 function08 = (Function0) rememberedValue10;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                i4 = 32;
                i3 = 536870912;
                onLongPress = onLongPress4;
                obj9 = null;
                HurifixConfirmDialogKt.HurifixConfirmDialog(OrderItemCard$lambda$490, OrderItemCard$lambda$493, OrderItemCard$lambda$496, null, OrderItemCard$lambda$502, OrderItemCard$lambda$499, function07, function08, $composer3, 14155776, 8);
                $composer3 = $composer3;
            } else {
                confirmAction$delegate = confirmAction$delegate2;
                obj9 = obj7;
                onLongPress = onLongPress3;
                i3 = 536870912;
                i4 = 32;
                $composer3.startReplaceGroup(1651405671);
            }
            $composer3.endReplaceGroup();
            String status = customerJobEntity.getStatus();
            switch (status.hashCode()) {
                case -1031784143:
                    if (status.equals("CANCELLED")) {
                        $composer3.startReplaceGroup(472124800);
                        ComposerKt.sourceInformation($composer3, "2125@100351L11");
                        j = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getError-0d7_KjU();
                        $composer3.endReplaceGroup();
                        break;
                    }
                    $composer3.startReplaceGroup(472126338);
                    ComposerKt.sourceInformation($composer3, "2126@100399L11");
                    j = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary-0d7_KjU();
                    $composer3.endReplaceGroup();
                    break;
                case 35394935:
                    if (status.equals("PENDING")) {
                        $composer3.startReplaceGroup(472118764);
                        $composer3.endReplaceGroup();
                        j = ColorKt.Color(4294286859L);
                        break;
                    }
                    $composer3.startReplaceGroup(472126338);
                    ComposerKt.sourceInformation($composer3, "2126@100399L11");
                    j = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary-0d7_KjU();
                    $composer3.endReplaceGroup();
                    break;
                case 907287315:
                    if (status.equals("PROCESSING")) {
                        $composer3.startReplaceGroup(472120524);
                        $composer3.endReplaceGroup();
                        j = ColorKt.Color(4282090230L);
                        break;
                    }
                    $composer3.startReplaceGroup(472126338);
                    ComposerKt.sourceInformation($composer3, "2126@100399L11");
                    j = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary-0d7_KjU();
                    $composer3.endReplaceGroup();
                    break;
                case 1383663147:
                    if (status.equals("COMPLETED")) {
                        $composer3.startReplaceGroup(472122252);
                        $composer3.endReplaceGroup();
                        j = ColorKt.Color(4279673674L);
                        break;
                    }
                    $composer3.startReplaceGroup(472126338);
                    ComposerKt.sourceInformation($composer3, "2126@100399L11");
                    j = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary-0d7_KjU();
                    $composer3.endReplaceGroup();
                    break;
                default:
                    $composer3.startReplaceGroup(472126338);
                    ComposerKt.sourceInformation($composer3, "2126@100399L11");
                    j = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary-0d7_KjU();
                    $composer3.endReplaceGroup();
                    break;
            }
            final long statusColor = j;
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, obj9);
            ComposerKt.sourceInformationMarkerStart($composer3, 472136920, "CC(remember):HomeScreen.kt#9igjgp");
            boolean z3 = ($dirty & 1879048192) == i3;
            Composer composer = $composer3;
            Object rememberedValue11 = composer.rememberedValue();
            if (z3 || rememberedValue11 == Composer.Companion.getEmpty()) {
                obj10 = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda22
                    public final Object invoke() {
                        return HomeScreenKt.OrderItemCard$lambda$513$lambda$512(onLongPress);
                    }
                };
                composer.updateRememberedValue(obj10);
            } else {
                obj10 = rememberedValue11;
            }
            Function0 function09 = (Function0) obj10;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 472130937, "CC(remember):HomeScreen.kt#9igjgp");
            boolean z4 = (($dirty & 112) == i4) | (($dirty & 29360128) == 8388608);
            Composer composer2 = $composer3;
            Object rememberedValue12 = composer2.rememberedValue();
            if (z4 || rememberedValue12 == Composer.Companion.getEmpty()) {
                obj11 = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda33
                    public final Object invoke() {
                        return HomeScreenKt.OrderItemCard$lambda$515$lambda$514(OrderStatusTab.this, function04);
                    }
                };
                composer2.updateRememberedValue(obj11);
            } else {
                obj11 = rememberedValue12;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier = ClickableKt.combinedClickable-cJG_KMw$default(fillMaxWidth$default, false, (String) null, (Role) null, (String) null, function09, (Function0) null, (Function0) obj11, 47, (Object) null);
            Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12));
            BorderStroke borderStroke = BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl((float) 1.2d), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOutlineVariant-0d7_KjU());
            Composer $composer4 = $composer3;
            final boolean isViewOnly5 = isViewOnly2;
            final SimpleDateFormat dateFormat3 = dateFormat;
            final MutableState confirmMessage$delegate3 = confirmMessage$delegate;
            final MutableState confirmButtonText$delegate3 = confirmButtonText$delegate;
            final MutableState confirmIsDestructive$delegate3 = confirmIsDestructive$delegate;
            final MutableState confirmIcon$delegate3 = confirmIcon$delegate;
            final MutableState confirmAction$delegate3 = confirmAction$delegate;
            Function0 onLongPress5 = onLongPress;
            final CustomerJobEntity customerJobEntity2 = customerJobEntity;
            CardKt.Card(modifier, shape, CardDefaults.INSTANCE.cardColors-ro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface-0d7_KjU(), 0L, 0L, 0L, $composer4, CardDefaults.$stable << 12, 14), CardDefaults.INSTANCE.cardElevation-aqJV_2Y(Dp.constructor-impl(2), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer4, (CardDefaults.$stable << 18) | 6, 62), borderStroke, ComposableLambdaKt.rememberComposableLambda(-1447169523, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda44
                public final Object invoke(Object obj13, Object obj14, Object obj15) {
                    return HomeScreenKt.OrderItemCard$lambda$579(CustomerJobEntity.this, statusColor, dateFormat3, currentStatus, context, confirmTitle$delegate, confirmMessage$delegate3, confirmButtonText$delegate3, confirmIsDestructive$delegate3, confirmIcon$delegate3, confirmAction$delegate3, showConfirmDialog$delegate, function2, function22, function23, function0, canDeleteOrders4, isViewOnly5, function05, function02, function03, function04, (ColumnScope) obj13, (Composer) obj14, ((Integer) obj15).intValue());
                }
            }, $composer4, 54), $composer4, 196608, 0);
            $composer2 = $composer4;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            canDeleteOrders2 = canDeleteOrders4;
            isViewOnly3 = isViewOnly5;
            onLongPress2 = onLongPress5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda55
                public final Object invoke(Object obj13, Object obj14) {
                    return HomeScreenKt.OrderItemCard$lambda$580(CustomerJobEntity.this, currentStatus, canDeleteOrders2, isViewOnly3, function0, function02, function03, function04, function05, onLongPress2, function2, function22, function23, $changed, $changed1, i, (Composer) obj13, ((Integer) obj14).intValue());
                }
            });
        }
    }

    private static final boolean OrderItemCard$lambda$487(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void OrderItemCard$lambda$488(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String OrderItemCard$lambda$490(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String OrderItemCard$lambda$493(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String OrderItemCard$lambda$496(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean OrderItemCard$lambda$499(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void OrderItemCard$lambda$500(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final ImageVector OrderItemCard$lambda$502(MutableState<ImageVector> mutableState) {
        return (ImageVector) ((State) mutableState).getValue();
    }

    private static final Function0<Unit> OrderItemCard$lambda$506(MutableState<Function0<Unit>> mutableState) {
        return (Function0) ((State) mutableState).getValue();
    }

    static /* synthetic */ void OrderItemCard$requestConfirm$default(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, String str, String str2, String str3, boolean z, ImageVector imageVector, Function0 function0, int i, Object obj) {
        OrderItemCard$requestConfirm(mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, str, str2, (i & 512) != 0 ? "Confirm" : str3, (i & 1024) != 0 ? false : z, (i & 2048) != 0 ? WarningKt.getWarning(Icons.INSTANCE.getDefault()) : imageVector, function0);
    }

    private static final void OrderItemCard$requestConfirm(MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<Boolean> mutableState4, MutableState<ImageVector> mutableState5, MutableState<Function0<Unit>> mutableState6, MutableState<Boolean> mutableState7, String title, String message, String buttonText, boolean isDestructive, ImageVector icon, Function0<Unit> function0) {
        mutableState.setValue(title);
        mutableState2.setValue(message);
        mutableState3.setValue(buttonText);
        OrderItemCard$lambda$500(mutableState4, isDestructive);
        mutableState5.setValue(icon);
        mutableState6.setValue(function0);
        OrderItemCard$lambda$488(mutableState7, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$509$lambda$508(MutableState $showConfirmDialog$delegate, MutableState $confirmAction$delegate) {
        OrderItemCard$lambda$488($showConfirmDialog$delegate, false);
        OrderItemCard$lambda$506($confirmAction$delegate).invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$511$lambda$510(MutableState $showConfirmDialog$delegate) {
        OrderItemCard$lambda$488($showConfirmDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$515$lambda$514(OrderStatusTab $currentStatus, Function0 $onShowCompletedDetail) {
        if ($currentStatus == OrderStatusTab.COMPLETED) {
            $onShowCompletedDetail.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$513$lambda$512(Function0 $onLongPress) {
        if ($onLongPress != null) {
            $onLongPress.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0cea  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0dbe  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0e44  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0e4d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0e78  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0f9e  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0faa  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0fdc  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x1045  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x1059  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x1687  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x11b9  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x1359  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x14e8  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0ff2  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0fae  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0dc8  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0e33  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0a2f  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0994  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0b62  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x06ca  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x05bb  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x035d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0555  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x05cd  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x062d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x06fd  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x08b1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0982  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x098e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0a29  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0abc  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0b82  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrderItemCard$lambda$579(final com.example.data.model.CustomerJobEntity r112, final long r113, java.text.SimpleDateFormat r115, final com.example.ui.OrderStatusTab r116, final android.content.Context r117, final androidx.compose.runtime.MutableState r118, final androidx.compose.runtime.MutableState r119, final androidx.compose.runtime.MutableState r120, final androidx.compose.runtime.MutableState r121, final androidx.compose.runtime.MutableState r122, final androidx.compose.runtime.MutableState r123, final androidx.compose.runtime.MutableState r124, final kotlin.jvm.functions.Function2 r125, final kotlin.jvm.functions.Function2 r126, final kotlin.jvm.functions.Function2 r127, kotlin.jvm.functions.Function0 r128, boolean r129, boolean r130, final kotlin.jvm.functions.Function0 r131, final kotlin.jvm.functions.Function0 r132, final kotlin.jvm.functions.Function0 r133, kotlin.jvm.functions.Function0 r134, androidx.compose.foundation.layout.ColumnScope r135, androidx.compose.runtime.Composer r136, int r137) {
        /*
            Method dump skipped, instructions count: 5786
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.OrderItemCard$lambda$579(com.example.data.model.CustomerJobEntity, long, java.text.SimpleDateFormat, com.example.ui.OrderStatusTab, android.content.Context, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function0, boolean, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$518$lambda$517(CustomerJobEntity $job, long $statusColor, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2176@102339L299:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1557734590, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2176)");
            }
            TextKt.Text--4IGK_g($job.getStatus(), PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(8), Dp.constructor-impl(4)), $statusColor, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x07a5  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x06f4  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0a5b  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0344 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x05ae  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x06ee  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0783  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x088f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x089b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x08ca  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x092c  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x09bf  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0a83  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0932  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x08e0  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x089f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrderItemCard$lambda$579$lambda$578$lambda$541(com.example.ui.OrderStatusTab r111, final com.example.data.model.CustomerJobEntity r112, final android.content.Context r113, final androidx.compose.runtime.MutableState r114, final androidx.compose.runtime.MutableState r115, final androidx.compose.runtime.MutableState r116, final androidx.compose.runtime.MutableState r117, final androidx.compose.runtime.MutableState r118, final androidx.compose.runtime.MutableState r119, final androidx.compose.runtime.MutableState r120, final kotlin.jvm.functions.Function2 r121, final kotlin.jvm.functions.Function2 r122, androidx.compose.runtime.Composer r123, int r124) {
        /*
            Method dump skipped, instructions count: 2697
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.OrderItemCard$lambda$579$lambda$578$lambda$541(com.example.ui.OrderStatusTab, com.example.data.model.CustomerJobEntity, android.content.Context, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$526$lambda$525$lambda$524$lambda$523(CustomerJobEntity $job, final String $phone, final Context $context, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        String assignedExpertName = $job.getAssignedExpertName();
        if (assignedExpertName == null) {
            assignedExpertName = "Expert";
        }
        OrderItemCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Call Assigned Expert?", "Call expert " + assignedExpertName + " at " + $phone + "?", "Call Now", false, PhoneKt.getPhone(Icons.INSTANCE.getDefault()), new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda5
            public final Object invoke() {
                return HomeScreenKt.OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$526$lambda$525$lambda$524$lambda$523$lambda$522($context, $phone);
            }
        }, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$526$lambda$525$lambda$524$lambda$523$lambda$522(Context $context, String $phone) {
        WhatsAppHelper.INSTANCE.openDialer($context, $phone);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$533$lambda$527(CustomerJobEntity $job, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2272@107105L532:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1874014368, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2272)");
            }
            TextKt.Text--4IGK_g($job.isExpertNotified() ? "✅ Expert WhatsApp Sent" : "⚠️ WhatsApp message not sent yet", PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(6), Dp.constructor-impl(2)), ColorKt.Color($job.isExpertNotified() ? 4279599165L : 4290007817L), TextUnitKt.getSp(10.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$533$lambda$531$lambda$530(final CustomerJobEntity $job, final Context $context, final Function2 $onUpdateExpertNotified, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        final String assignedExpertPhone = $job.getAssignedExpertPhone();
        if (assignedExpertPhone != null) {
            String str = $job.isExpertNotified() ? "Send WhatsApp Again?" : "Send WhatsApp to Expert?";
            String assignedExpertName = $job.getAssignedExpertName();
            if (assignedExpertName == null) {
                assignedExpertName = "Expert";
            }
            OrderItemCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, str, "Send job details and customer location to " + assignedExpertName + " on WhatsApp?", "Send via WhatsApp", false, SendKt.getSend(Icons.AutoMirrored.Filled.INSTANCE), new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda202
                public final Object invoke() {
                    return HomeScreenKt.OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$533$lambda$531$lambda$530$lambda$529$lambda$528(CustomerJobEntity.this, assignedExpertPhone, $context, $onUpdateExpertNotified);
                }
            }, 1024, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$533$lambda$531$lambda$530$lambda$529$lambda$528(CustomerJobEntity $job, String $phone, Context $context, Function2 $onUpdateExpertNotified) {
        Long assignedExpertId = $job.getAssignedExpertId();
        long longValue = assignedExpertId != null ? assignedExpertId.longValue() : 0L;
        String assignedExpertName = $job.getAssignedExpertName();
        if (assignedExpertName == null) {
            assignedExpertName = "Expert";
        }
        ExpertEntity expertObj = new ExpertEntity(longValue, assignedExpertName, $phone, $job.getServiceType(), "", 0.0d, 0.0d, false, 0.0f, 0.0f, 0, 0, 0, false, false, null, null, null, null, null, 0L, 0L, 0L, false, 16777088, null);
        WhatsAppHelper.INSTANCE.sendWhatsAppMessageToExpert($context, expertObj, $job);
        $onUpdateExpertNotified.invoke(Long.valueOf($job.getId()), true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$533$lambda$532(CustomerJobEntity $job, RowScope $this$OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C2308@109754L281:HomeScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(34482601, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2308)");
            }
            TextKt.Text--4IGK_g($job.isExpertNotified() ? "Send Again" : "Send to Expert", (Modifier) null, 0L, TextUnitKt.getSp(10.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$539$lambda$534(CustomerJobEntity $job, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2326@110764L550:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-669720887, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2326)");
            }
            TextKt.Text--4IGK_g($job.isCustomerNotifiedOnAssign() ? "✅ Customer WhatsApp Sent" : "⚠️ Customer WhatsApp pending", PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(6), Dp.constructor-impl(2)), ColorKt.Color($job.isCustomerNotifiedOnAssign() ? 4279599165L : 4290007817L), TextUnitKt.getSp(10.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$539$lambda$537$lambda$536(final CustomerJobEntity $job, final Context $context, final Function2 $onUpdateCustomerNotified, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, $job.isCustomerNotifiedOnAssign() ? "Send WhatsApp Again?" : "Send WhatsApp to Customer?", "Send technician assignment notification to customer " + $job.getCustomerName() + " on WhatsApp?", "Send via WhatsApp", false, SendKt.getSend(Icons.AutoMirrored.Filled.INSTANCE), new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda88
            public final Object invoke() {
                return HomeScreenKt.OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$539$lambda$537$lambda$536$lambda$535(CustomerJobEntity.this, $context, $onUpdateCustomerNotified);
            }
        }, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$539$lambda$537$lambda$536$lambda$535(CustomerJobEntity $job, Context $context, Function2 $onUpdateCustomerNotified) {
        WhatsAppHelper whatsAppHelper = WhatsAppHelper.INSTANCE;
        Double distanceKmAtDispatch = $job.getDistanceKmAtDispatch();
        String estTime = whatsAppHelper.calculateEstimatedArrivalTimeWithBuffer(distanceKmAtDispatch != null ? distanceKmAtDispatch.doubleValue() : 3.0d);
        WhatsAppHelper whatsAppHelper2 = WhatsAppHelper.INSTANCE;
        String customerName = $job.getCustomerName();
        String assignedExpertName = $job.getAssignedExpertName();
        if (assignedExpertName == null) {
            assignedExpertName = "Expert";
        }
        String str = assignedExpertName;
        String assignedExpertPhone = $job.getAssignedExpertPhone();
        if (assignedExpertPhone == null) {
            assignedExpertPhone = "";
        }
        String msg = whatsAppHelper2.createCustomerAssignmentNotificationMessage(customerName, str, assignedExpertPhone, $job.getServiceType(), estTime);
        WhatsAppHelper.INSTANCE.sendWhatsAppDirectMessage($context, $job.getCustomerPhone(), msg);
        $onUpdateCustomerNotified.invoke(Long.valueOf($job.getId()), true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$541$lambda$540$lambda$539$lambda$538(CustomerJobEntity $job, RowScope $this$OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C2359@113352L293:HomeScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1066776978, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2359)");
            }
            TextKt.Text--4IGK_g($job.isCustomerNotifiedOnAssign() ? "Send Again" : "Send to Customer", (Modifier) null, 0L, TextUnitKt.getSp(10.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$544$lambda$542(CustomerJobEntity $job, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2387@114660L379:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-460351531, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2387)");
            }
            String str = "⏱ " + CompletedOrderDetailDialogKt.calculateTaskDuration($job.getCreatedAt(), $job.getCompletedAt());
            FontWeight bold = FontWeight.Companion.getBold();
            TextKt.Text--4IGK_g(str, PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(7), Dp.constructor-impl(3)), ColorKt.Color(4279599165L), TextUnitKt.getSp(11), (FontStyle) null, bold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 200112, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$544$lambda$543(boolean $isWarrantyValid, int $daysRemaining, int $daysPassed, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2400@115292L609:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1983285900, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2400)");
            }
            TextKt.Text--4IGK_g($isWarrantyValid ? "🛡️ 10-Day Warranty: " + $daysRemaining + " Days Left" : "⚠️ Warranty Expired (" + $daysPassed + " days ago)", PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(7), Dp.constructor-impl(3)), ColorKt.Color($isWarrantyValid ? 4279599165L : 4290321436L), TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$550$lambda$545(CustomerJobEntity $job, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2424@116471L490:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2121427404, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2424)");
            }
            TextKt.Text--4IGK_g($job.isCustomerNotifiedOnCompletion() ? "✅ Completion WhatsApp Sent" : "⚠️ Completion WhatsApp pending", PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(6), Dp.constructor-impl(2)), ColorKt.Color($job.isCustomerNotifiedOnCompletion() ? 4279599165L : 4290007817L), TextUnitKt.getSp(10.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$550$lambda$548$lambda$547(final CustomerJobEntity $job, final Context $context, final Function2 $onUpdateCompletionNotified, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, $job.isCustomerNotifiedOnCompletion() ? "Send Completion WhatsApp Again?" : "Send Completion WhatsApp?", "Send 10-day warranty and feedback link to customer " + $job.getCustomerName() + " on WhatsApp?", "Send via WhatsApp", false, SendKt.getSend(Icons.AutoMirrored.Filled.INSTANCE), new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda4
            public final Object invoke() {
                return HomeScreenKt.OrderItemCard$lambda$579$lambda$578$lambda$550$lambda$548$lambda$547$lambda$546(CustomerJobEntity.this, $context, $onUpdateCompletionNotified);
            }
        }, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$550$lambda$548$lambda$547$lambda$546(CustomerJobEntity $job, Context $context, Function2 $onUpdateCompletionNotified) {
        String msg = WhatsAppHelper.INSTANCE.createCompletionCustomerMessage($job.getCustomerName());
        WhatsAppHelper.INSTANCE.sendWhatsAppDirectMessage($context, $job.getCustomerPhone(), msg);
        $onUpdateCompletionNotified.invoke(Long.valueOf($job.getId()), true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$550$lambda$549(CustomerJobEntity $job, RowScope $this$OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C2450@118145L249:HomeScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1879671587, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2450)");
            }
            TextKt.Text--4IGK_g($job.isCustomerNotifiedOnCompletion() ? "Send Again" : "Send to Customer", (Modifier) null, 0L, TextUnitKt.getSp(10.5d), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199680, 0, 131030);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x021a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit OrderItemCard$lambda$579$lambda$578$lambda$557(java.lang.String r50, java.lang.String r51, final java.lang.String r52, androidx.compose.runtime.Composer r53, int r54) {
        /*
            Method dump skipped, instructions count: 584
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.OrderItemCard$lambda$579$lambda$578$lambda$557(java.lang.String, java.lang.String, java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$557$lambda$556$lambda$555(String $managerText, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2524@121605L10,2525@121682L11,2522@121487L414:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1081730116, $changed, -1, "com.example.ui.screens.OrderItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2522)");
            }
            TextKt.Text--4IGK_g("💼 " + $managerText, PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(6), Dp.constructor-impl(2)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryContainer-0d7_KjU(), 0L, (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 196656, 0, 65496);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$560$lambda$559(final CustomerJobEntity $job, final Context $context, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Call Customer?", "Call customer " + $job.getCustomerName() + " at " + $job.getCustomerPhone() + "?", "Call Now", false, PhoneKt.getPhone(Icons.INSTANCE.getDefault()), new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda174
            public final Object invoke() {
                return HomeScreenKt.OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$560$lambda$559$lambda$558($context, $job);
            }
        }, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$560$lambda$559$lambda$558(Context $context, CustomerJobEntity $job) {
        WhatsAppHelper.INSTANCE.openDialer($context, $job.getCustomerPhone());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$562$lambda$561(CustomerJobEntity $job, Function0 $onDeleteJob, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Move to Recycle Bin?", "Are you sure you want to move order #" + $job.getId() + " for " + $job.getCustomerName() + " to the recycle bin?", "Yes, Move to Bin", true, DeleteKt.getDelete(Icons.INSTANCE.getDefault()), $onDeleteJob);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$564$lambda$563(CustomerJobEntity $job, Function0 $onCompleteAction, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Mark Order Complete?", "Are you sure you want to mark order #" + $job.getId() + " for customer " + $job.getCustomerName() + " as COMPLETED?", "Yes, Complete", false, CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), $onCompleteAction, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$566$lambda$565(CustomerJobEntity $job, Function0 $onCancelAction, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Cancel Order?", "Are you sure you want to cancel order #" + $job.getId() + " for " + $job.getCustomerName() + "?", "Yes, Cancel Order", true, WarningKt.getWarning(Icons.INSTANCE.getDefault()), $onCancelAction);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$569$lambda$568(final CustomerJobEntity $job, final Context $context, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Call Customer?", "Call customer " + $job.getCustomerName() + " at " + $job.getCustomerPhone() + "?", "Call Now", false, PhoneKt.getPhone(Icons.INSTANCE.getDefault()), new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda163
            public final Object invoke() {
                return HomeScreenKt.OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$569$lambda$568$lambda$567($context, $job);
            }
        }, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$569$lambda$568$lambda$567(Context $context, CustomerJobEntity $job) {
        WhatsAppHelper.INSTANCE.openDialer($context, $job.getCustomerPhone());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$571$lambda$570(CustomerJobEntity $job, Function0 $onDeleteJob, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Move to Recycle Bin?", "Are you sure you want to move order #" + $job.getId() + " for " + $job.getCustomerName() + " to the recycle bin?", "Yes, Move to Bin", true, DeleteKt.getDelete(Icons.INSTANCE.getDefault()), $onDeleteJob);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$574$lambda$573(final CustomerJobEntity $job, final Context $context, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm$default($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Call Customer?", "Call customer " + $job.getCustomerName() + " at " + $job.getCustomerPhone() + "?", "Call Now", false, PhoneKt.getPhone(Icons.INSTANCE.getDefault()), new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda222
            public final Object invoke() {
                return HomeScreenKt.OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$574$lambda$573$lambda$572($context, $job);
            }
        }, 1024, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$574$lambda$573$lambda$572(Context $context, CustomerJobEntity $job) {
        WhatsAppHelper.INSTANCE.openDialer($context, $job.getCustomerPhone());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit OrderItemCard$lambda$579$lambda$578$lambda$577$lambda$576$lambda$575(CustomerJobEntity $job, Function0 $onDeleteJob, MutableState $confirmTitle$delegate, MutableState $confirmMessage$delegate, MutableState $confirmButtonText$delegate, MutableState $confirmIsDestructive$delegate, MutableState $confirmIcon$delegate, MutableState $confirmAction$delegate, MutableState $showConfirmDialog$delegate) {
        OrderItemCard$requestConfirm($confirmTitle$delegate, $confirmMessage$delegate, $confirmButtonText$delegate, $confirmIsDestructive$delegate, $confirmIcon$delegate, $confirmAction$delegate, $showConfirmDialog$delegate, "Move to Recycle Bin?", "Are you sure you want to move order #" + $job.getId() + " for " + $job.getCustomerName() + " to the recycle bin?", "Yes, Move to Bin", true, DeleteKt.getDelete(Icons.INSTANCE.getDefault()), $onDeleteJob);
        return Unit.INSTANCE;
    }

    private static final void ExpertsTabContent(final List<ExpertEntity> list, final List<ExpertCategoryEntity> list2, boolean canAddExperts, boolean isAdmin, final Function1<? super String, Unit> function1, final Function1<? super ExpertCategoryEntity, Unit> function12, final Function1<? super ExpertEntity, Unit> function13, final Function1<? super ExpertEntity, Unit> function14, final Function2<? super ExpertEntity, ? super Boolean, Unit> function2, final Function1<? super ExpertEntity, Unit> function15, final Function1<? super ExpertEntity, Unit> function16, final Function0<Unit> function0, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        boolean z2;
        final boolean canAddExperts2;
        final boolean isAdmin2;
        int $dirty;
        Object obj;
        Object obj2;
        Object obj3;
        int $dirty1;
        Object list3;
        final List displayCategories;
        Object plus;
        Object obj4;
        Composer $composer2;
        final boolean isAdmin3;
        final boolean canAddExperts3;
        Object obj5;
        Object obj6;
        Composer $composer3 = $composer.startRestartGroup(2113186150);
        ComposerKt.sourceInformation($composer3, "C(ExpertsTabContent)P(2,1!2,5,6,8,7,10,11,9)2725@131747L7,2726@131780L24,2727@131828L31,2728@131893L34,2731@132045L383,2741@132453L93,2747@132642L21,2745@132577L92,2763@133074L12295,2760@132973L12396:HomeScreen.kt#2thlc2");
        int $dirty2 = $changed;
        int $dirty12 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(list) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(list2) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            z = canAddExperts;
        } else if (($changed & 384) == 0) {
            z = canAddExperts;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = canAddExperts;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            z2 = isAdmin;
        } else if (($changed & 3072) == 0) {
            z2 = isAdmin;
            $dirty2 |= $composer3.changed(z2) ? 2048 : 1024;
        } else {
            z2 = isAdmin;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 16384 : 8192;
        }
        if (($changed & 196608) == 0) {
            $dirty2 |= $composer3.changedInstance(function12) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function13) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function14) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 67108864 : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changedInstance(function15) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty12 |= $composer3.changedInstance(function16) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty12 |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if ((306783379 & $dirty2) == 306783378 && ($dirty12 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $dirty1 = $dirty12;
            $composer2 = $composer3;
            isAdmin3 = z2;
            canAddExperts3 = z;
        } else {
            if (i2 != 0) {
                canAddExperts2 = true;
            } else {
                canAddExperts2 = z;
            }
            if (i3 == 0) {
                isAdmin2 = z2;
            } else {
                isAdmin2 = false;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2113186150, $dirty2, $dirty12, "com.example.ui.screens.ExpertsTabContent (HomeScreen.kt:2724)");
            }
            CompositionLocal localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, -954367824, "CC(remember):Effects.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                $dirty = $dirty2;
                obj = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(obj);
            } else {
                $dirty = $dirty2;
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) obj).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -747618459, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                obj2 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            final MutableState searchQuery$delegate = (MutableState) obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -747616376, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue3 = $composer3.rememberedValue();
            if (rememberedValue3 == Composer.Companion.getEmpty()) {
                obj3 = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                $composer3.updateRememberedValue(obj3);
            } else {
                obj3 = rememberedValue3;
            }
            final MutableState showAddCategoryDialog$delegate = (MutableState) obj3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -747611163, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changed = $composer3.changed(list2);
            Object rememberedValue4 = $composer3.rememberedValue();
            if (changed || rememberedValue4 == Composer.Companion.getEmpty()) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                $dirty1 = $dirty12;
                linkedHashMap.put("Electrician", new ExpertCategoryEntity(0L, "Electrician", true, 0L, 0L, false, 57, null));
                linkedHashMap.put("Plumber", new ExpertCategoryEntity(0L, "Plumber", true, 0L, 0L, false, 57, null));
                List<ExpertCategoryEntity> list4 = list2;
                int i4 = 0;
                for (ExpertCategoryEntity expertCategoryEntity : list4) {
                    Iterable iterable = list4;
                    linkedHashMap.put(expertCategoryEntity.getName(), expertCategoryEntity);
                    list4 = iterable;
                    i4 = i4;
                }
                Iterable values = linkedHashMap.values();
                Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
                list3 = CollectionsKt.toList(values);
                $composer3.updateRememberedValue(list3);
            } else {
                $dirty1 = $dirty12;
                list3 = rememberedValue4;
            }
            List displayCategories2 = (List) list3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -747598397, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changed2 = $composer3.changed(displayCategories2);
            Object rememberedValue5 = $composer3.rememberedValue();
            if (changed2 || rememberedValue5 == Composer.Companion.getEmpty()) {
                List listOf = CollectionsKt.listOf("All");
                List list5 = displayCategories2;
                displayCategories = displayCategories2;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
                Iterable iterable2 = list5;
                Iterator it = iterable2.iterator();
                while (it.hasNext()) {
                    arrayList.add(((ExpertCategoryEntity) it.next()).getName());
                    iterable2 = iterable2;
                }
                plus = CollectionsKt.plus(listOf, (List) arrayList);
                $composer3.updateRememberedValue(plus);
            } else {
                displayCategories = displayCategories2;
                plus = rememberedValue5;
            }
            final List categoryTabs = (List) plus;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -747592421, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changedInstance = $composer3.changedInstance(categoryTabs);
            Object rememberedValue6 = $composer3.rememberedValue();
            if (changedInstance || rememberedValue6 == Composer.Companion.getEmpty()) {
                obj4 = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda168
                    public final Object invoke() {
                        int size;
                        size = categoryTabs.size();
                        return Integer.valueOf(size);
                    }
                };
                $composer3.updateRememberedValue(obj4);
            } else {
                obj4 = rememberedValue6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final PagerState categoryPagerState = PagerStateKt.rememberPagerState(0, 0.0f, (Function0) obj4, $composer3, 6, 2);
            if (ExpertsTabContent$lambda$585(showAddCategoryDialog$delegate)) {
                $composer3.startReplaceGroup(-1700463407);
                ComposerKt.sourceInformation($composer3, "2752@132762L68,2755@132856L33,2751@132712L187");
                ComposerKt.sourceInformationMarkerStart($composer3, -747588534, "CC(remember):HomeScreen.kt#9igjgp");
                boolean z3 = ($dirty & 57344) == 16384;
                Object rememberedValue7 = $composer3.rememberedValue();
                if (z3 || rememberedValue7 == Composer.Companion.getEmpty()) {
                    obj5 = new Function1() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda169
                        public final Object invoke(Object obj7) {
                            return HomeScreenKt.ExpertsTabContent$lambda$594$lambda$593(function1, (String) obj7);
                        }
                    };
                    $composer3.updateRememberedValue(obj5);
                } else {
                    obj5 = rememberedValue7;
                }
                Function1 function17 = (Function1) obj5;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -747585561, "CC(remember):HomeScreen.kt#9igjgp");
                Object rememberedValue8 = $composer3.rememberedValue();
                if (rememberedValue8 == Composer.Companion.getEmpty()) {
                    obj6 = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda170
                        public final Object invoke() {
                            return HomeScreenKt.ExpertsTabContent$lambda$596$lambda$595(showAddCategoryDialog$delegate);
                        }
                    };
                    $composer3.updateRememberedValue(obj6);
                } else {
                    obj6 = rememberedValue8;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                CustomerWhatsAppDialogsKt.AddNewCategoryDialog(function17, (Function0) obj6, $composer3, 48);
            } else {
                $composer3.startReplaceGroup(-1832111076);
            }
            $composer3.endReplaceGroup();
            boolean canAddExperts4 = canAddExperts2;
            boolean isAdmin4 = isAdmin2;
            $composer2 = $composer3;
            PagerKt.HorizontalPager-oI3XNZo(categoryPagerState, SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), (PaddingValues) null, (PageSize) null, 0, 0.0f, (Alignment.Vertical) null, (TargetedFlingBehavior) null, false, false, (Function1) null, (NestedScrollConnection) null, (SnapPosition) null, ComposableLambdaKt.rememberComposableLambda(1909662468, true, new Function4() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda171
                public final Object invoke(Object obj7, Object obj8, Object obj9, Object obj10) {
                    return HomeScreenKt.ExpertsTabContent$lambda$648(categoryTabs, list, searchQuery$delegate, canAddExperts2, categoryPagerState, displayCategories, function12, context, coroutineScope, isAdmin2, function13, function14, function2, function15, function16, showAddCategoryDialog$delegate, function0, (PagerScope) obj7, ((Integer) obj8).intValue(), (Composer) obj9, ((Integer) obj10).intValue());
                }
            }, $composer3, 54), $composer2, 48, 3072, 8188);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isAdmin3 = isAdmin4;
            canAddExperts3 = canAddExperts4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda173
                public final Object invoke(Object obj7, Object obj8) {
                    return HomeScreenKt.ExpertsTabContent$lambda$649(list, list2, canAddExperts3, isAdmin3, function1, function12, function13, function14, function2, function15, function16, function0, $changed, $changed1, i, (Composer) obj7, ((Integer) obj8).intValue());
                }
            });
        }
    }

    private static final String ExpertsTabContent$lambda$582(MutableState<String> mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final boolean ExpertsTabContent$lambda$585(MutableState<Boolean> mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void ExpertsTabContent$lambda$586(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$594$lambda$593(Function1 $onAddNewCategory, String newName) {
        Intrinsics.checkNotNullParameter(newName, "newName");
        $onAddNewCategory.invoke(newName);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$596$lambda$595(MutableState $showAddCategoryDialog$delegate) {
        ExpertsTabContent$lambda$586($showAddCategoryDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x04bf  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x05ec  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x05b9  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertsTabContent$lambda$648(final java.util.List r73, final java.util.List r74, final androidx.compose.runtime.MutableState r75, final boolean r76, final androidx.compose.foundation.pager.PagerState r77, final java.util.List r78, final kotlin.jvm.functions.Function1 r79, final android.content.Context r80, final kotlinx.coroutines.CoroutineScope r81, final boolean r82, final kotlin.jvm.functions.Function1 r83, final kotlin.jvm.functions.Function1 r84, final kotlin.jvm.functions.Function2 r85, final kotlin.jvm.functions.Function1 r86, final kotlin.jvm.functions.Function1 r87, final androidx.compose.runtime.MutableState r88, final kotlin.jvm.functions.Function0 r89, androidx.compose.foundation.pager.PagerScope r90, int r91, androidx.compose.runtime.Composer r92, int r93) {
        /*
            Method dump skipped, instructions count: 1620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.ExpertsTabContent$lambda$648(java.util.List, java.util.List, androidx.compose.runtime.MutableState, boolean, androidx.compose.foundation.pager.PagerState, java.util.List, kotlin.jvm.functions.Function1, android.content.Context, kotlinx.coroutines.CoroutineScope, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.runtime.MutableState, kotlin.jvm.functions.Function0, androidx.compose.foundation.pager.PagerScope, int, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final boolean ExpertsTabContent$lambda$648$lambda$601(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean ExpertsTabContent$lambda$648$lambda$600$lambda$599(LazyListState $listState) {
        return $listState.getFirstVisibleItemIndex() == 0 && $listState.getFirstVisibleItemScrollOffset() <= 20;
    }

    private static final boolean ExpertsTabContent$lambda$648$lambda$604(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean ExpertsTabContent$lambda$648$lambda$603$lambda$602(LazyListState $listState) {
        return $listState.getFirstVisibleItemIndex() > 0 || $listState.getFirstVisibleItemScrollOffset() > 20;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641(final List $expertsForPage, final boolean $canAddExperts, final List $experts, final MutableState $showAddCategoryDialog$delegate, final List $categoryTabs, final PagerState $categoryPagerState, final List $displayCategories, final Function1 $onDeleteCategory, final Context $context, final CoroutineScope $coroutineScope, final MutableState $searchQuery$delegate, final String $pageCategory, final boolean $isAdmin, final Function1 $onEditExpert, final Function1 $onDeleteExpert, final Function2 $onToggleAvailability, final Function1 $onViewWorkHistory, final Function1 $onSendWelcome, LazyListScope $this$LazyColumn) {
        Intrinsics.checkNotNullParameter($this$LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default($this$LazyColumn, "experts_header", (Object) null, ComposableLambdaKt.composableLambdaInstance(-2112298582, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda224
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$610($canAddExperts, $experts, $showAddCategoryDialog$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 2, (Object) null);
        LazyListScope.item$default($this$LazyColumn, "category_tabs", (Object) null, ComposableLambdaKt.composableLambdaInstance(1492194323, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda225
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$621($categoryTabs, $categoryPagerState, $experts, $displayCategories, $onDeleteCategory, $context, $coroutineScope, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 2, (Object) null);
        LazyListScope.item$default($this$LazyColumn, "experts_tip", (Object) null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$787334002$app(), 2, (Object) null);
        LazyListScope.item$default($this$LazyColumn, "search_field", (Object) null, ComposableLambdaKt.composableLambdaInstance(82473681, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda226
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$627($searchQuery$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 2, (Object) null);
        if ($expertsForPage.isEmpty()) {
            LazyListScope.item$default($this$LazyColumn, "empty_experts", (Object) null, ComposableLambdaKt.composableLambdaInstance(-1301916817, true, new Function3() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda1
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$629($pageCategory, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 2, (Object) null);
        } else {
            final Function1 function1 = new Function1() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda2
                public final Object invoke(Object obj) {
                    return HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$630((ExpertEntity) obj);
                }
            };
            final Function1 function12 = new Function1() { // from class: com.example.ui.screens.HomeScreenKt$ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$$inlined$items$default$1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return m202invoke((ExpertEntity) p1);
                }

                /* renamed from: invoke, reason: collision with other method in class */
                public final Void m202invoke(ExpertEntity expertEntity) {
                    return null;
                }
            };
            $this$LazyColumn.items($expertsForPage.size(), new Function1<Integer, Object>() { // from class: com.example.ui.screens.HomeScreenKt$ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$$inlined$items$default$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke(((Number) p1).intValue());
                }

                public final Object invoke(int index) {
                    return function1.invoke($expertsForPage.get(index));
                }
            }, new Function1<Integer, Object>() { // from class: com.example.ui.screens.HomeScreenKt$ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$$inlined$items$default$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke(((Number) p1).intValue());
                }

                public final Object invoke(int index) {
                    return function12.invoke($expertsForPage.get(index));
                }
            }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.ui.screens.HomeScreenKt$ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$$inlined$items$default$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(4);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                    invoke((LazyItemScope) p1, ((Number) p2).intValue(), (Composer) p3, ((Number) p4).intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:101:0x0305  */
                /* JADX WARN: Removed duplicated region for block: B:106:0x0343  */
                /* JADX WARN: Removed duplicated region for block: B:111:0x0360  */
                /* JADX WARN: Removed duplicated region for block: B:116:0x03ad  */
                /* JADX WARN: Removed duplicated region for block: B:118:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:120:0x036f  */
                /* JADX WARN: Removed duplicated region for block: B:125:0x0313 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:130:0x02b9 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:135:0x025e A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:140:0x0204 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:145:0x01ab A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:150:0x0152  */
                /* JADX WARN: Removed duplicated region for block: B:51:0x0143  */
                /* JADX WARN: Removed duplicated region for block: B:56:0x0183  */
                /* JADX WARN: Removed duplicated region for block: B:61:0x019c  */
                /* JADX WARN: Removed duplicated region for block: B:66:0x01dc  */
                /* JADX WARN: Removed duplicated region for block: B:71:0x01f5  */
                /* JADX WARN: Removed duplicated region for block: B:76:0x0235  */
                /* JADX WARN: Removed duplicated region for block: B:81:0x024f  */
                /* JADX WARN: Removed duplicated region for block: B:86:0x0290  */
                /* JADX WARN: Removed duplicated region for block: B:91:0x02aa  */
                /* JADX WARN: Removed duplicated region for block: B:96:0x02e9  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.lazy.LazyItemScope r29, int r30, androidx.compose.runtime.Composer r31, int r32) {
                    /*
                        Method dump skipped, instructions count: 945
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt$ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$$inlined$items$default$4.invoke(androidx.compose.foundation.lazy.LazyItemScope, int, androidx.compose.runtime.Composer, int):void");
                }
            }));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$610(boolean r73, java.util.List r74, final androidx.compose.runtime.MutableState r75, androidx.compose.foundation.lazy.LazyItemScope r76, androidx.compose.runtime.Composer r77, int r78) {
        /*
            Method dump skipped, instructions count: 1033
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$610(boolean, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.foundation.lazy.LazyItemScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$610$lambda$609$lambda$608$lambda$607(MutableState $showAddCategoryDialog$delegate) {
        ExpertsTabContent$lambda$586($showAddCategoryDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$621(java.util.List r65, final androidx.compose.foundation.pager.PagerState r66, java.util.List r67, java.util.List r68, kotlin.jvm.functions.Function1 r69, final android.content.Context r70, final kotlinx.coroutines.CoroutineScope r71, androidx.compose.foundation.lazy.LazyItemScope r72, androidx.compose.runtime.Composer r73, int r74) {
        /*
            Method dump skipped, instructions count: 1028
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$621(java.util.List, androidx.compose.foundation.pager.PagerState, java.util.List, java.util.List, kotlin.jvm.functions.Function1, android.content.Context, kotlinx.coroutines.CoroutineScope, androidx.compose.foundation.lazy.LazyItemScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$621$lambda$620$lambda$619$lambda$616$lambda$615(CoroutineScope $coroutineScope, PagerState $categoryPagerState, int $index) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$ExpertsTabContent$3$1$1$1$2$1$1$2$1$1($categoryPagerState, $index, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$621$lambda$620$lambda$619$lambda$614$lambda$613(ExpertCategoryEntity $catEntity, Function1 $onDeleteCategory, String $catName, Context $context) {
        if ($catEntity != null && !$catEntity.isDefault() && !Intrinsics.areEqual($catEntity.getName(), "Electrician") && !Intrinsics.areEqual($catEntity.getName(), "Plumber")) {
            $onDeleteCategory.invoke($catEntity);
        } else if (!Intrinsics.areEqual($catName, "All")) {
            Toast.makeText($context, $catName + " is a default category and cannot be deleted", 0).show();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0178  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$621$lambda$620$lambda$619$lambda$618(java.lang.String r49, int r50, boolean r51, com.example.data.model.ExpertCategoryEntity r52, androidx.compose.runtime.Composer r53, int r54) {
        /*
            Method dump skipped, instructions count: 628
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.screens.HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$621$lambda$620$lambda$619$lambda$618(java.lang.String, int, boolean, com.example.data.model.ExpertCategoryEntity, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$627(final MutableState $searchQuery$delegate, LazyItemScope $this$item, Composer $composer, int $changed) {
        Function2 function2;
        Object obj;
        Intrinsics.checkNotNullParameter($this$item, "$this$item");
        ComposerKt.sourceInformation($composer, "C2897@140615L20,2895@140511L819:HomeScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(82473681, $changed, -1, "com.example.ui.screens.ExpertsTabContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2895)");
            }
            String ExpertsTabContent$lambda$582 = ExpertsTabContent$lambda$582($searchQuery$delegate);
            if (!StringsKt.isBlank(ExpertsTabContent$lambda$582($searchQuery$delegate))) {
                $composer.startReplaceGroup(252537106);
                ComposerKt.sourceInformation($composer, "2901@140917L103");
                Function2 function22 = (Function2) ComposableLambdaKt.rememberComposableLambda(49270132, true, new Function2() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda140
                    public final Object invoke(Object obj2, Object obj3) {
                        return HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$627$lambda$624($searchQuery$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54);
                $composer.endReplaceGroup();
                function2 = function22;
            } else {
                $composer.startReplaceGroup(252695980);
                $composer.endReplaceGroup();
                function2 = null;
            }
            Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10));
            Modifier testTag = TestTagKt.testTag(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), "search_experts_input");
            ComposerKt.sourceInformationMarkerStart($composer, 1947800165, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function1() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda141
                    public final Object invoke(Object obj2) {
                        return HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$627$lambda$626$lambda$625($searchQuery$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(ExpertsTabContent$lambda$582, (Function1) obj, testTag, false, false, (TextStyle) null, (Function2) null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$295018104$app(), ComposableSingletons$HomeScreenKt.INSTANCE.m188getLambda$480077447$app(), function2, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, shape, (TextFieldColors) null, $composer, 113246640, 12582912, 0, 6159480);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$627$lambda$626$lambda$625(MutableState $searchQuery$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $searchQuery$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$627$lambda$624(final MutableState $searchQuery$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C2901@140940L20,2901@140919L99:HomeScreen.kt#2thlc2");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(49270132, $changed, -1, "com.example.ui.screens.ExpertsTabContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2901)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 103957352, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                obj = new Function0() { // from class: com.example.ui.screens.HomeScreenKt$$ExternalSyntheticLambda221
                    public final Object invoke() {
                        return HomeScreenKt.ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$627$lambda$624$lambda$623$lambda$622($searchQuery$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) obj, (Modifier) null, false, (IconButtonColors) null, (MutableInteractionSource) null, ComposableSingletons$HomeScreenKt.INSTANCE.m197getLambda$714268873$app(), $composer, 196614, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$627$lambda$624$lambda$623$lambda$622(MutableState $searchQuery$delegate) {
        $searchQuery$delegate.setValue("");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$629(String $pageCategory, LazyItemScope $this$item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$item, "$this$item");
        ComposerKt.sourceInformation($composer, "C2913@141472L589:HomeScreen.kt#2thlc2");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1301916817, $changed, -1, "com.example.ui.screens.ExpertsTabContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2913)");
            }
            Modifier modifier = PaddingKt.padding-VpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, Dp.constructor-impl(40), 1, (Object) null);
            Alignment center = Alignment.Companion.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
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
            Updater.set-impl(composer, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
            if (composer.getInserting() || !Intrinsics.areEqual(composer.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composer.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composer.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.set-impl(composer, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1545550880, "C2921@141900L10,2922@141977L11,2919@141753L282:HomeScreen.kt#2thlc2");
            TextKt.Text--4IGK_g("No partner experts found in '" + $pageCategory + "'.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 0, 0, 65530);
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
    public static final Object ExpertsTabContent$lambda$648$lambda$647$lambda$642$lambda$641$lambda$630(ExpertEntity it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$646$lambda$643(Function0 $onAddExpert, AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter($this$AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C2973@144739L11,2975@144893L34,2969@144403L554:HomeScreen.kt#2thlc2");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1268894427, $changed, -1, "com.example.ui.screens.ExpertsTabContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2969)");
        }
        FloatingActionButtonKt.ExtendedFloatingActionButton-ElI5-7k(ComposableSingletons$HomeScreenKt.INSTANCE.m183getLambda$1980953327$app(), ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$1433465328$app(), $onAddExpert, (Modifier) null, false, (Shape) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary-0d7_KjU(), Color.Companion.getWhite-0d7_KjU(), FloatingActionButtonDefaults.INSTANCE.elevation-xZ9-QkE(Dp.constructor-impl(6), 0.0f, 0.0f, 0.0f, $composer, (FloatingActionButtonDefaults.$stable << 12) | 6, 14), (MutableInteractionSource) null, $composer, 12582966, 568);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit ExpertsTabContent$lambda$648$lambda$647$lambda$646$lambda$645$lambda$644(CoroutineScope $coroutineScope, LazyListState $listState) {
        BuildersKt.launch$default($coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new HomeScreenKt$ExpertsTabContent$3$1$2$2$1$1($listState, null), 3, (Object) null);
        return Unit.INSTANCE;
    }
}
