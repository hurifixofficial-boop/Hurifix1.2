package com.example.ui.components;

import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.data.model.ExpertEntity;
import com.example.data.model.RankedExpert;
import com.example.util.LocationHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NearestExpertItemCard.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aQ\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"NearestExpertItemCard", "", "ranked", "Lcom/example/data/model/RankedExpert;", "rankIndex", "", "onSendWhatsApp", "Lkotlin/Function0;", "onCall", "onViewOnMap", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/example/data/model/RankedExpert;ILkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class NearestExpertItemCardKt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertItemCard$lambda$14(RankedExpert rankedExpert, int i, Function0 function0, Function0 function02, Function0 function03, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        NearestExpertItemCard(rankedExpert, i, function0, function02, function03, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static final void NearestExpertItemCard(final RankedExpert ranked, final int rankIndex, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        int $dirty;
        Modifier modifier4;
        long j;
        Composer $composer2;
        final Modifier modifier5;
        Intrinsics.checkNotNullParameter(ranked, "ranked");
        Intrinsics.checkNotNullParameter(function0, "onSendWhatsApp");
        Intrinsics.checkNotNullParameter(function02, "onCall");
        Intrinsics.checkNotNullParameter(function03, "onViewOnMap");
        Composer $composer3 = $composer.startRestartGroup(-609885618);
        ComposerKt.sourceInformation($composer3, "C(NearestExpertItemCard)P(5,4,2,1,3)87@3481L232,94@3748L64,95@3819L9696,67@2773L10742:NearestExpertItemCard.kt#qonjpd");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(ranked) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(rankIndex) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changedInstance(function02) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function03) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty2 |= 196608;
            modifier2 = modifier;
        } else if ((196608 & $changed) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        if ((74899 & $dirty2) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier5 = modifier2;
        } else {
            if (i2 != 0) {
                modifier3 = (Modifier) Modifier.Companion;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-609885618, $dirty2, -1, "com.example.ui.components.NearestExpertItemCard (NearestExpertItemCard.kt:62)");
            }
            int $dirty3 = $dirty2;
            final ExpertEntity expert = ranked.getExpert();
            final boolean isTopMatch = rankIndex == 0;
            final String distanceStr = LocationHelper.INSTANCE.formatDistance(ranked.getDistanceKm());
            Modifier testTag = TestTagKt.testTag(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), "nearest_expert_card_" + expert.getId());
            if (!isTopMatch) {
                $dirty = $dirty3;
                $composer3.startReplaceGroup(-1390001998);
                ComposerKt.sourceInformation($composer3, "81@3275L11");
                modifier4 = BorderKt.border-xT4_qwU(Modifier.Companion, Dp.constructor-impl((float) 1.5d), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOutlineVariant-0d7_KjU(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(16)));
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1390223493);
                ComposerKt.sourceInformation($composer3, "75@3050L11");
                $dirty = $dirty3;
                modifier4 = BorderKt.border-xT4_qwU(Modifier.Companion, Dp.constructor-impl(2), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary-0d7_KjU(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(16)));
                $composer3.endReplaceGroup();
            }
            Modifier then = testTag.then(modifier4);
            Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(16));
            CardDefaults cardDefaults = CardDefaults.INSTANCE;
            if (isTopMatch) {
                $composer3.startReplaceGroup(-1389637066);
                ComposerKt.sourceInformation($composer3, "89@3570L11");
                long j2 = Color.copy-wmQWz5c$default(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimaryContainer-0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, (Object) null);
                $composer3.endReplaceGroup();
                j = j2;
            } else {
                $composer3.startReplaceGroup(-1389538765);
                ComposerKt.sourceInformation($composer3, "91@3670L11");
                long j3 = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface-0d7_KjU();
                $composer3.endReplaceGroup();
                j = j3;
            }
            Modifier modifier6 = modifier3;
            $composer2 = $composer3;
            CardKt.Card(then, shape, cardDefaults.cardColors-ro_MJ88(j, 0L, 0L, 0L, $composer3, CardDefaults.$stable << 12, 14), CardDefaults.INSTANCE.cardElevation-aqJV_2Y(isTopMatch ? Dp.constructor-impl(3) : Dp.constructor-impl(1), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, CardDefaults.$stable << 18, 62), (BorderStroke) null, ComposableLambdaKt.rememberComposableLambda(-1723516132, true, new Function3() { // from class: com.example.ui.components.NearestExpertItemCardKt$$ExternalSyntheticLambda2
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return NearestExpertItemCardKt.NearestExpertItemCard$lambda$13(isTopMatch, ranked, rankIndex, distanceStr, expert, function0, function02, function03, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer2, 196608, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier6;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.ui.components.NearestExpertItemCardKt$$ExternalSyntheticLambda3
                public final Object invoke(Object obj, Object obj2) {
                    return NearestExpertItemCardKt.NearestExpertItemCard$lambda$14(RankedExpert.this, rankIndex, function0, function02, function03, modifier5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0b3a  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0b73  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0bf7  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0c16  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0c2f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0d0a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0d16  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0d4f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0f03  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0f0f  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0f46  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x112d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0f5c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0f15  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0d65 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0d1c  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0c34  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0c19  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0bfc  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0b89 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0b40  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0a5e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0a15  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x08a4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x085b  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x070f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x06c6  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x057a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0442 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x042c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x052b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0564  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x06b4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x06c0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x06f9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0849  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0855  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x088e  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0a03  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0a0f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0a48  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0b2e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit NearestExpertItemCard$lambda$13(final boolean r135, final com.example.data.model.RankedExpert r136, final int r137, final java.lang.String r138, com.example.data.model.ExpertEntity r139, kotlin.jvm.functions.Function0 r140, kotlin.jvm.functions.Function0 r141, kotlin.jvm.functions.Function0 r142, androidx.compose.foundation.layout.ColumnScope r143, androidx.compose.runtime.Composer r144, int r145) {
        /*
            Method dump skipped, instructions count: 4403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.NearestExpertItemCardKt.NearestExpertItemCard$lambda$13(boolean, com.example.data.model.RankedExpert, int, java.lang.String, com.example.data.model.ExpertEntity, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit NearestExpertItemCard$lambda$13$lambda$12$lambda$3$lambda$0(boolean $isTopMatch, int $rankIndex, Composer $composer, int $changed) {
        String str;
        long j;
        ComposerKt.sourceInformation($composer, "C112@4528L442:NearestExpertItemCard.kt#qonjpd");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(539664839, $changed, -1, "com.example.ui.components.NearestExpertItemCard.<anonymous>.<anonymous>.<anonymous>.<anonymous> (NearestExpertItemCard.kt:112)");
            }
            if ($isTopMatch) {
                str = "⭐ #1 TOP MATCH";
            } else {
                str = "#" + ($rankIndex + 1) + " NEAREST";
            }
            if ($isTopMatch) {
                $composer.startReplaceGroup(-1073126128);
                ComposerKt.sourceInformation($composer, "114@4694L11");
                j = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimary-0d7_KjU();
            } else {
                $composer.startReplaceGroup(-1073124809);
                ComposerKt.sourceInformation($composer, "114@4735L11");
                j = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant-0d7_KjU();
            }
            $composer.endReplaceGroup();
            TextKt.Text--4IGK_g(str, PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(8), Dp.constructor-impl(4)), j, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, $composer, 199728, 0, 131024);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0184  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit NearestExpertItemCard$lambda$13$lambda$12$lambda$3$lambda$2(com.example.data.model.RankedExpert r49, java.lang.String r50, androidx.compose.runtime.Composer r51, int r52) {
        /*
            Method dump skipped, instructions count: 553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.ui.components.NearestExpertItemCardKt.NearestExpertItemCard$lambda$13$lambda$12$lambda$3$lambda$2(com.example.data.model.RankedExpert, java.lang.String, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }
}
