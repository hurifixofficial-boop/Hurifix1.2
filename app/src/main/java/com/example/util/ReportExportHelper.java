package com.example.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.example.BuildConfig;
import com.example.data.model.CustomerJobEntity;
import com.example.ui.components.MonthOrderStat;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ReportExportHelper.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ$\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ(\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0002¨\u0006\u0016"}, d2 = {"Lcom/example/util/ReportExportHelper;", "", "<init>", "()V", "exportMonthlyReportToCsv", "", "context", "Landroid/content/Context;", "monthStat", "Lcom/example/ui/components/MonthOrderStat;", "jobsInMonth", "", "Lcom/example/data/model/CustomerJobEntity;", "exportMonthlyReportToPdf", "shareFile", "file", "Ljava/io/File;", "mimeType", "", "subject", "escapeCsv", "text", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes6.dex */
public final class ReportExportHelper {
    public static final int $stable = 0;
    public static final ReportExportHelper INSTANCE = new ReportExportHelper();

    private ReportExportHelper() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0256  */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void exportMonthlyReportToCsv(android.content.Context r32, com.example.ui.components.MonthOrderStat r33, java.util.List<com.example.data.model.CustomerJobEntity> r34) {
        /*
            Method dump skipped, instructions count: 972
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.util.ReportExportHelper.exportMonthlyReportToCsv(android.content.Context, com.example.ui.components.MonthOrderStat, java.util.List):void");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:34:0x0540. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [android.graphics.pdf.PdfDocument] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v9, types: [java.io.OutputStream] */
    public final void exportMonthlyReportToPdf(Context context, MonthOrderStat monthStat, List<CustomerJobEntity> jobsInMonth) {
        FileOutputStream fileOutputStream;
        String str;
        PdfDocument pdfDocument;
        int pageHeight;
        String generatedAt;
        Paint paint;
        int successRate;
        PdfDocument.Page page;
        Canvas canvas;
        String str2;
        int statusBgColor;
        int statusTextColor;
        List<CustomerJobEntity> list = jobsInMonth;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(monthStat, "monthStat");
        Intrinsics.checkNotNullParameter(list, "jobsInMonth");
        int i = 1;
        try {
            PdfDocument pdfDocument2 = new PdfDocument();
            int pageHeight2 = 842;
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
            String generatedAt2 = dateTimeFormat.format(new Date());
            int successRate2 = monthStat.getTotalOrders() > 0 ? (monthStat.getCompletedOrders() * 100) / monthStat.getTotalOrders() : 0;
            int itemsPerPage = 12;
            int totalPages = Math.max(1, ((list.size() + 12) - 1) / 12);
            int pageIndex = 0;
            while (pageIndex < totalPages) {
                try {
                    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, pageHeight2, pageIndex + 1).create();
                    PdfDocument.Page page2 = pdfDocument2.startPage(pageInfo);
                    Canvas canvas2 = page2.getCanvas();
                    Paint paint2 = new Paint(i);
                    String str3 = "CANCELLED";
                    int pageIndex2 = pageIndex;
                    String str4 = "PROCESSING";
                    String str5 = "COMPLETED";
                    SimpleDateFormat dateTimeFormat2 = dateTimeFormat;
                    int itemsPerPage2 = itemsPerPage;
                    if (pageIndex2 != 0) {
                        str = "CANCELLED";
                        pdfDocument = pdfDocument2;
                        pageHeight = pageHeight2;
                        generatedAt = generatedAt2;
                        paint = paint2;
                        successRate = successRate2;
                        page = page2;
                        canvas = canvas2;
                        paint.setColor(Color.parseColor("#1E293B"));
                        canvas.drawRect(0.0f, 0.0f, 595, 45.0f, paint);
                        paint.setColor(-1);
                        paint.setTextSize(14.0f);
                        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                        canvas.drawText("Hurifix Operations Report - " + monthStat.getDisplayMonth(), 24.0f, 28.0f, paint);
                        paint.setTextSize(9.0f);
                        paint.setColor(Color.parseColor("#CBD5E1"));
                        canvas.drawText("Page " + (pageIndex2 + 1) + " of " + totalPages, 595 - 110.0f, 28.0f, paint);
                    } else {
                        paint2.setColor(Color.parseColor("#1E293B"));
                        canvas2.drawRect(0.0f, 0.0f, 595, 105.0f, paint2);
                        paint2.setColor(Color.parseColor("#D97706"));
                        canvas2.drawRect(0.0f, 105.0f, 595, 110.0f, paint2);
                        canvas = canvas2;
                        paint = paint2;
                        paint.setColor(-1);
                        paint.setTextSize(24.0f);
                        pdfDocument = pdfDocument2;
                        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                        page = page2;
                        canvas.drawText("HURIFIX", 28.0f, 42.0f, paint);
                        paint.setTextSize(10.0f);
                        paint.setColor(Color.parseColor("#F59E0B"));
                        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                        canvas.drawText("MANY PROBLEMS  |  ONE SOLUTION", 28.0f, 58.0f, paint);
                        paint.setTextSize(13.0f);
                        paint.setColor(-1);
                        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
                        canvas.drawText("Monthly Operations & Performance Report", 28.0f, 82.0f, paint);
                        paint.setTextSize(9.0f);
                        paint.setColor(Color.parseColor("#CBD5E1"));
                        canvas.drawText("Period: " + monthStat.getDisplayMonth(), 595 - 190.0f, 42.0f, paint);
                        canvas.drawText("Generated: " + generatedAt2, 595 - 190.0f, 58.0f, paint);
                        canvas.drawText("Page " + (pageIndex2 + 1) + " of " + totalPages, 595 - 190.0f, 74.0f, paint);
                        paint.setColor(Color.parseColor("#F8FAFC"));
                        float cardHeight = 595;
                        generatedAt = generatedAt2;
                        pageHeight = pageHeight2;
                        RectF kpiRect = new RectF(24.0f, 125.0f, cardHeight - 24.0f, 125.0f + 65.0f);
                        canvas.drawRoundRect(kpiRect, 8.0f, 8.0f, paint);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(1.0f);
                        paint.setColor(Color.parseColor("#E2E8F0"));
                        canvas.drawRoundRect(kpiRect, 8.0f, 8.0f, paint);
                        paint.setStyle(Paint.Style.FILL);
                        float colWidth = (595 - 48.0f) / 5.0f;
                        List kpiLabels = CollectionsKt.listOf(new String[]{"TOTAL", "COMPLETED", "PROCESSING", "CANCELLED", "SUCCESS"});
                        List kpiValues = CollectionsKt.listOf(new String[]{String.valueOf(monthStat.getTotalOrders()), String.valueOf(monthStat.getCompletedOrders()), String.valueOf(monthStat.getProcessingOrders()), String.valueOf(monthStat.getCancelledOrders()), successRate2 + "%"});
                        List kpiColors = CollectionsKt.listOf(new Integer[]{Integer.valueOf(Color.parseColor("#1E293B")), Integer.valueOf(Color.parseColor("#16A34A")), Integer.valueOf(Color.parseColor("#D97706")), Integer.valueOf(Color.parseColor("#DC2626")), Integer.valueOf(Color.parseColor("#2563EB"))});
                        int i2 = 0;
                        while (true) {
                            successRate = successRate2;
                            if (i2 < 5) {
                                float colX = (i2 * colWidth) + 24.0f + (colWidth / 2.0f);
                                paint.setTextSize(9.0f);
                                paint.setColor(Color.parseColor("#64748B"));
                                paint.setTextAlign(Paint.Align.CENTER);
                                paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                                canvas.drawText((String) kpiLabels.get(i2), colX, 125.0f + 24.0f, paint);
                                paint.setTextSize(18.0f);
                                paint.setColor(((Number) kpiColors.get(i2)).intValue());
                                paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                                canvas.drawText((String) kpiValues.get(i2), colX, 125.0f + 50.0f, paint);
                                i2++;
                                kpiRect = kpiRect;
                                successRate2 = successRate;
                                str3 = str3;
                            } else {
                                str = str3;
                                paint.setTextAlign(Paint.Align.LEFT);
                            }
                        }
                    }
                    float tableTop = pageIndex2 == 0 ? 205.0f : 60.0f;
                    paint.setColor(Color.parseColor("#0F172A"));
                    canvas.drawRect(24.0f, tableTop, 595 - 24.0f, tableTop + 24.0f, paint);
                    float tableTop2 = tableTop;
                    int i3 = -1;
                    paint.setColor(-1);
                    paint.setTextSize(9.0f);
                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                    canvas.drawText("ID", 28.0f, tableTop2 + 16.0f, paint);
                    canvas.drawText("CUSTOMER", 60.0f, tableTop2 + 16.0f, paint);
                    canvas.drawText("MOBILE", 160.0f, tableTop2 + 16.0f, paint);
                    canvas.drawText("SERVICE", 235.0f, tableTop2 + 16.0f, paint);
                    canvas.drawText("EXPERT ASSIGNED", 345.0f, tableTop2 + 16.0f, paint);
                    canvas.drawText("STATUS", 465.0f, tableTop2 + 16.0f, paint);
                    canvas.drawText("RATING", 535.0f, 16.0f + tableTop2, paint);
                    int startIndex = pageIndex2 * itemsPerPage2;
                    int endIndex = Math.min(startIndex + itemsPerPage2, list.size());
                    float tableTop3 = tableTop2 + 24.0f;
                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
                    int idx = startIndex;
                    while (idx < endIndex) {
                        CustomerJobEntity job = list.get(idx);
                        boolean isEven = idx % 2 == 0;
                        if (isEven) {
                            i3 = Color.parseColor("#F8FAFC");
                        }
                        paint.setColor(i3);
                        Canvas canvas3 = canvas;
                        canvas3.drawRect(24.0f, tableTop3, 595 - 24.0f, tableTop3 + 38.0f, paint);
                        float rowY = tableTop3;
                        paint.setColor(Color.parseColor("#E2E8F0"));
                        paint.setStrokeWidth(0.5f);
                        canvas3.drawLine(24.0f, rowY + 38.0f, 595 - 24.0f, rowY + 38.0f, paint);
                        paint.setColor(Color.parseColor("#1E293B"));
                        paint.setTextSize(9.0f);
                        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                        float tableTop4 = tableTop2;
                        int startIndex2 = startIndex;
                        canvas3.drawText("#" + job.getId(), 28.0f, rowY + 18.0f, paint);
                        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
                        canvas3.drawText(StringsKt.take(job.getCustomerName(), 18), 60.0f, rowY + 18.0f, paint);
                        paint.setColor(Color.parseColor("#64748B"));
                        paint.setTextSize(8.0f);
                        canvas3.drawText(StringsKt.take(job.getAddress(), 20), 60.0f, 30.0f + rowY, paint);
                        paint.setColor(Color.parseColor("#1E293B"));
                        paint.setTextSize(9.0f);
                        canvas3.drawText(job.getCustomerPhone(), 160.0f, rowY + 18.0f, paint);
                        canvas3.drawText(StringsKt.take(job.getServiceType(), 18), 235.0f, rowY + 18.0f, paint);
                        String assignedExpertName = job.getAssignedExpertName();
                        if (assignedExpertName == null) {
                            assignedExpertName = "Unassigned";
                        }
                        canvas3.drawText(StringsKt.take(assignedExpertName, 18), 345.0f, rowY + 18.0f, paint);
                        String status = job.getStatus();
                        switch (status.hashCode()) {
                            case -1031784143:
                                str2 = str;
                                if (status.equals(str2)) {
                                    statusBgColor = Color.parseColor("#FEE2E2");
                                    break;
                                } else {
                                    statusBgColor = Color.parseColor("#FEF3C7");
                                    break;
                                }
                            case 907287315:
                                if (status.equals(str4)) {
                                    statusBgColor = Color.parseColor("#DBEAFE");
                                    str2 = str;
                                    break;
                                }
                                str2 = str;
                                statusBgColor = Color.parseColor("#FEF3C7");
                                break;
                            case 1383663147:
                                if (status.equals(str5)) {
                                    statusBgColor = Color.parseColor("#DCFCE7");
                                    str2 = str;
                                    break;
                                } else {
                                    str2 = str;
                                    statusBgColor = Color.parseColor("#FEF3C7");
                                    break;
                                }
                            default:
                                str2 = str;
                                statusBgColor = Color.parseColor("#FEF3C7");
                                break;
                        }
                        String status2 = job.getStatus();
                        switch (status2.hashCode()) {
                            case -1031784143:
                                if (!status2.equals(str2)) {
                                    break;
                                } else {
                                    statusTextColor = Color.parseColor("#B91C1C");
                                    break;
                                }
                            case 907287315:
                                if (!status2.equals(str4)) {
                                    break;
                                } else {
                                    statusTextColor = Color.parseColor("#1D4ED8");
                                    break;
                                }
                            case 1383663147:
                                if (!status2.equals(str5)) {
                                    break;
                                } else {
                                    statusTextColor = Color.parseColor("#15803D");
                                    break;
                                }
                        }
                        statusTextColor = Color.parseColor("#B45309");
                        String str6 = str4;
                        str = str2;
                        String str7 = str5;
                        int endIndex2 = endIndex;
                        RectF statusRect = new RectF(465.0f, rowY + 6.0f, 525.0f, rowY + 24.0f);
                        paint.setColor(statusBgColor);
                        canvas3.drawRoundRect(statusRect, 4.0f, 4.0f, paint);
                        paint.setColor(statusTextColor);
                        paint.setTextSize(7.5f);
                        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                        canvas3.drawText(StringsKt.take(job.getStatus(), 10), 470.0f, rowY + 18.0f, paint);
                        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
                        paint.setColor(Color.parseColor("#D97706"));
                        paint.setTextSize(9.0f);
                        Float ratingGiven = job.getRatingGiven();
                        if (ratingGiven != null && (ratingStr = "★ " + ((int) ratingGiven.floatValue())) != null) {
                            canvas3.drawText(ratingStr, 540.0f, rowY + 18.0f, paint);
                            float rowY2 = rowY + 38.0f;
                            idx++;
                            list = jobsInMonth;
                            canvas = canvas3;
                            startIndex = startIndex2;
                            tableTop2 = tableTop4;
                            str5 = str7;
                            endIndex = endIndex2;
                            i3 = -1;
                            tableTop3 = rowY2;
                            str4 = str6;
                        }
                        String ratingStr = "-";
                        canvas3.drawText(ratingStr, 540.0f, rowY + 18.0f, paint);
                        float rowY22 = rowY + 38.0f;
                        idx++;
                        list = jobsInMonth;
                        canvas = canvas3;
                        startIndex = startIndex2;
                        tableTop2 = tableTop4;
                        str5 = str7;
                        endIndex = endIndex2;
                        i3 = -1;
                        tableTop3 = rowY22;
                        str4 = str6;
                    }
                    Canvas canvas4 = canvas;
                    paint.setColor(Color.parseColor("#94A3B8"));
                    paint.setTextSize(8.0f);
                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
                    int pageHeight3 = pageHeight;
                    canvas4.drawText("Hurifix Operations & Dispatch Management System • Strictly Confidential", 24.0f, pageHeight3 - 20.0f, paint);
                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas4.drawText("Page " + (pageIndex2 + 1) + " of " + totalPages, 595 - 24.0f, pageHeight3 - 20.0f, paint);
                    paint.setTextAlign(Paint.Align.LEFT);
                    PdfDocument pdfDocument3 = pdfDocument;
                    pdfDocument3.finishPage(page);
                    pageIndex = pageIndex2 + 1;
                    pageHeight2 = pageHeight3;
                    pdfDocument2 = pdfDocument3;
                    dateTimeFormat = dateTimeFormat2;
                    itemsPerPage = itemsPerPage2;
                    generatedAt2 = generatedAt;
                    successRate2 = successRate;
                    i = 1;
                    list = jobsInMonth;
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = context;
                    Toast.makeText((Context) fileOutputStream, "PDF generate karne me error: " + e.getLocalizedMessage(), 1).show();
                }
            }
            ?? r2 = pdfDocument2;
            File file = new File(context.getCacheDir(), "reports");
            file.mkdirs();
            fileOutputStream = "_";
            String cleanMonth = StringsKt.replace$default(monthStat.getDisplayMonth(), " ", "_", false, 4, (Object) null);
            File pdfFile = new File(file, "Hurifix_Report_" + cleanMonth + ".pdf");
            FileOutputStream fileOutputStream2 = new FileOutputStream(pdfFile);
            try {
                try {
                    fileOutputStream = fileOutputStream2;
                    r2.writeTo(fileOutputStream);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream2, (Throwable) null);
                    r2.close();
                    shareFile(context, pdfFile, "application/pdf", "Hurifix Monthly Report - " + monthStat.getDisplayMonth() + " (PDF)");
                } finally {
                }
            } catch (Exception e2) {
                e = e2;
                Toast.makeText((Context) fileOutputStream, "PDF generate karne me error: " + e.getLocalizedMessage(), 1).show();
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = context;
        }
    }

    private final void shareFile(Context context, File file, String mimeType, String subject) {
        Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        Intrinsics.checkNotNullExpressionValue(uri, "getUriForFile(...)");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(mimeType);
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        intent.putExtra("android.intent.extra.TEXT", "Hurifix Monthly Performance Report is attached.");
        intent.addFlags(1);
        Intent chooser = Intent.createChooser(intent, "Export / Share Report");
        chooser.addFlags(268435456);
        context.startActivity(chooser);
    }

    private final String escapeCsv(String text) {
        if (!StringsKt.contains$default(text, ",", false, 2, (Object) null) && !StringsKt.contains$default(text, "\"", false, 2, (Object) null) && !StringsKt.contains$default(text, "\n", false, 2, (Object) null)) {
            return text;
        }
        return "\"" + StringsKt.replace$default(text, "\"", "\"\"", false, 4, (Object) null) + "\"";
    }
}
