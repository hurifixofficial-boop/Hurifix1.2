import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { CustomerJob, MonthOrderStat } from '../types';

export const ReportExportHelper = {
  /**
   * Exports monthly orders to CSV
   */
  exportMonthlyReportToCsv(monthStat: MonthOrderStat, jobsInMonth: CustomerJob[]) {
    const cleanMonth = monthStat.displayMonth.replace(/\s+/g, '_');
    const fileName = `Hurifix_Report_${cleanMonth}.csv`;
    const now = new Date();
    const generatedAt = now.toLocaleString('en-IN', {
      day: '2-digit',
      month: 'short',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
      hour12: true,
    });
    const successRate =
      monthStat.totalOrders > 0
        ? Math.round((monthStat.completedOrders * 100) / monthStat.totalOrders)
        : 0;

    const escapeCsv = (str: string) => {
      if (!str) return '""';
      if (str.includes(',') || str.includes('"') || str.includes('\n')) {
        return `"${str.replace(/"/g, '""')}"`;
      }
      return `"${str}"`;
    };

    let csvContent = 'HURIFIX PERFORMANCE & OPERATIONS REPORT\n';
    csvContent += 'Tagline: Many Problems | One Solution\n';
    csvContent += `Report Period: ${monthStat.displayMonth}\n`;
    csvContent += `Generated On: ${generatedAt}\n`;
    csvContent += `Total Orders: ${monthStat.totalOrders}\n`;
    csvContent += `Completed Orders: ${monthStat.completedOrders}\n`;
    csvContent += `Processing Orders: ${monthStat.processingOrders}\n`;
    csvContent += `Cancelled Orders: ${monthStat.cancelledOrders}\n`;
    csvContent += `Success Rate: ${successRate}%\n\n`;

    csvContent +=
      'Order ID,Created Date,Customer Name,Customer Phone,Service Required,Problem Description,Address,Assigned Expert,Status,Rating Given,Feedback\n';

    jobsInMonth.forEach((job) => {
      const dateStr = new Date(job.createdAt).toLocaleString('en-IN', {
        day: '2-digit',
        month: 'short',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        hour12: true,
      });
      const orderId = `#${job.id}`;
      const name = escapeCsv(job.customerName);
      const phone = escapeCsv(job.customerPhone);
      const service = escapeCsv(job.serviceType);
      const problem = escapeCsv(job.issueDescription || '');
      const address = escapeCsv(job.address || '');
      const expert = escapeCsv(job.assignedExpertName || 'Not Assigned');
      const status = escapeCsv(job.status);
      const rating = job.ratingGiven != null ? `"${job.ratingGiven.toFixed(1)}"` : '"N/A"';
      const feedback = escapeCsv(job.reviewFeedback || '');

      csvContent += `${orderId},${escapeCsv(dateStr)},${name},${phone},${service},${problem},${address},${expert},${status},${rating},${feedback}\n`;
    });

    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.setAttribute('download', fileName);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  },

  /**
   * Generates a beautifully formatted PDF report matching the Android template
   */
  exportMonthlyReportToPdf(monthStat: MonthOrderStat, jobsInMonth: CustomerJob[]) {
    const doc = new jsPDF({
      orientation: 'portrait',
      unit: 'pt',
      format: 'a4',
    });

    const pageWidth = doc.internal.pageSize.getWidth();
    const pageHeight = doc.internal.pageSize.getHeight();
    const cleanMonth = monthStat.displayMonth.replace(/\s+/g, '_');
    const generatedAt = new Date().toLocaleString('en-IN', {
      day: '2-digit',
      month: 'short',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
      hour12: true,
    });
    const successRate =
      monthStat.totalOrders > 0
        ? Math.round((monthStat.completedOrders * 100) / monthStat.totalOrders)
        : 0;

    // Header Background
    doc.setFillColor(30, 41, 59); // #1E293B Slate navy
    doc.rect(0, 0, pageWidth, 95, 'F');

    // Accent line
    doc.setFillColor(217, 119, 6); // #D97706 Amber
    doc.rect(0, 95, pageWidth, 5, 'F');

    // Title
    doc.setTextColor(255, 255, 255);
    doc.setFont('helvetica', 'bold');
    doc.setFontSize(22);
    doc.text('HURIFIX', 28, 38);

    doc.setTextColor(245, 158, 11);
    doc.setFontSize(9);
    doc.text('MANY PROBLEMS  |  ONE SOLUTION', 28, 52);

    doc.setTextColor(255, 255, 255);
    doc.setFont('helvetica', 'normal');
    doc.setFontSize(11);
    doc.text('Monthly Operations & Performance Report', 28, 74);

    // Meta right
    doc.setFontSize(8.5);
    doc.setTextColor(203, 213, 225);
    doc.text(`Period: ${monthStat.displayMonth}`, pageWidth - 180, 38);
    doc.text(`Generated: ${generatedAt}`, pageWidth - 180, 52);

    // KPI Cards Box
    const kpiY = 115;
    const kpiH = 55;
    doc.setFillColor(248, 250, 252);
    doc.roundedRect(24, kpiY, pageWidth - 48, kpiH, 6, 6, 'F');
    doc.setDrawColor(226, 232, 240);
    doc.roundedRect(24, kpiY, pageWidth - 48, kpiH, 6, 6, 'S');

    const colWidth = (pageWidth - 48) / 5;
    const kpis = [
      { label: 'TOTAL', val: `${monthStat.totalOrders}`, color: [30, 41, 59] },
      { label: 'COMPLETED', val: `${monthStat.completedOrders}`, color: [22, 163, 74] },
      { label: 'PROCESSING', val: `${monthStat.processingOrders}`, color: [217, 119, 6] },
      { label: 'CANCELLED', val: `${monthStat.cancelledOrders}`, color: [220, 38, 38] },
      { label: 'SUCCESS', val: `${successRate}%`, color: [37, 99, 235] },
    ];

    kpis.forEach((kpi, idx) => {
      const colCenterX = 24 + idx * colWidth + colWidth / 2;
      doc.setFont('helvetica', 'bold');
      doc.setFontSize(8);
      doc.setTextColor(100, 116, 139);
      doc.text(kpi.label, colCenterX, kpiY + 20, { align: 'center' });

      doc.setFontSize(15);
      doc.setTextColor(kpi.color[0], kpi.color[1], kpi.color[2]);
      doc.text(kpi.val, colCenterX, kpiY + 42, { align: 'center' });
    });

    // Table
    const tableRows = jobsInMonth.map((job) => [
      `#${job.id}`,
      job.customerName,
      job.customerPhone,
      job.serviceType,
      job.assignedExpertName || 'Unassigned',
      job.status,
      job.ratingGiven ? `★ ${job.ratingGiven.toFixed(0)}` : '-',
    ]);

    autoTable(doc, {
      startY: 185,
      head: [['ID', 'CUSTOMER', 'MOBILE', 'SERVICE', 'EXPERT', 'STATUS', 'RATING']],
      body: tableRows,
      theme: 'grid',
      headStyles: {
        fillColor: [15, 23, 42],
        textColor: [255, 255, 255],
        fontSize: 8.5,
        fontStyle: 'bold',
      },
      bodyStyles: {
        fontSize: 8,
        textColor: [30, 41, 59],
      },
      alternateRowStyles: {
        fillColor: [248, 250, 252],
      },
      didParseCell: (data) => {
        if (data.section === 'body' && data.column.index === 5) {
          const val = data.cell.raw as string;
          if (val === 'COMPLETED') {
            data.cell.styles.textColor = [21, 128, 61];
            data.cell.styles.fontStyle = 'bold';
          } else if (val === 'CANCELLED') {
            data.cell.styles.textColor = [185, 28, 28];
            data.cell.styles.fontStyle = 'bold';
          } else if (val === 'PROCESSING') {
            data.cell.styles.textColor = [29, 78, 216];
            data.cell.styles.fontStyle = 'bold';
          } else {
            data.cell.styles.textColor = [180, 83, 9];
            data.cell.styles.fontStyle = 'bold';
          }
        }
      },
      margin: { left: 24, right: 24, bottom: 35 },
    });

    // Footers
    const pageCount = (doc as any).internal.getNumberOfPages();
    for (let i = 1; i <= pageCount; i++) {
      doc.setPage(i);
      doc.setFont('helvetica', 'normal');
      doc.setFontSize(7.5);
      doc.setTextColor(148, 163, 184);
      doc.text(
        'Hurifix Operations & Dispatch Management System • Strictly Confidential',
        24,
        pageHeight - 15
      );
      doc.text(`Page ${i} of ${pageCount}`, pageWidth - 24, pageHeight - 15, { align: 'right' });
    }

    doc.save(`Hurifix_Report_${cleanMonth}.pdf`);
  },
};
