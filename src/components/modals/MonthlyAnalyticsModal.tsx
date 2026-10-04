import React, { useState, useMemo } from 'react';
import {
  X,
  BarChart3,
  Download,
  FileSpreadsheet,
  FileText,
  ChevronDown,
  ChevronUp,
  Search,
} from 'lucide-react';
import { CustomerJob, MonthOrderStat } from '../../types';
import { ReportExportHelper } from '../../utils/reportExportHelper';

interface MonthlyAnalyticsModalProps {
  isOpen: boolean;
  onClose: () => void;
  jobs: CustomerJob[];
}

export const MonthlyAnalyticsModal: React.FC<MonthlyAnalyticsModalProps> = ({
  isOpen,
  onClose,
  jobs,
}) => {
  const [selectedYear, setSelectedYear] = useState<string>('All');
  const [expandedMonthKey, setExpandedMonthKey] = useState<string | null>(null);
  const [searchQuery, setSearchQuery] = useState('');

  // Extract years
  const availableYears = useMemo(() => {
    const years = Array.from(
      new Set(jobs.map((j) => new Date(j.createdAt).getFullYear().toString()))
    ).sort((a, b) => Number(b) - Number(a));
    return ['All', ...years];
  }, [jobs]);

  // Group by Month
  const monthlyStats = useMemo(() => {
    const filtered =
      selectedYear === 'All'
        ? jobs
        : jobs.filter((j) => new Date(j.createdAt).getFullYear().toString() === selectedYear);

    const groups = new Map<string, CustomerJob[]>();
    filtered.forEach((job) => {
      const d = new Date(job.createdAt);
      const key = `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}`;
      if (!groups.has(key)) groups.set(key, []);
      groups.get(key)!.push(job);
    });

    const list: { stat: MonthOrderStat; jobs: CustomerJob[] }[] = [];
    groups.forEach((jobList, key) => {
      const sample = new Date(jobList[0].createdAt);
      const displayMonth = sample.toLocaleString('en-IN', { month: 'long', year: 'numeric' });
      const stat: MonthOrderStat = {
        monthYearKey: key,
        displayMonth,
        totalOrders: jobList.length,
        completedOrders: jobList.filter((j) => j.status === 'COMPLETED').length,
        cancelledOrders: jobList.filter((j) => j.status === 'CANCELLED').length,
        processingOrders: jobList.filter((j) => j.status === 'PROCESSING').length,
        pendingOrders: jobList.filter((j) => j.status === 'PENDING').length,
      };
      list.push({ stat, jobs: jobList });
    });

    return list.sort((a, b) => b.stat.monthYearKey.localeCompare(a.stat.monthYearKey));
  }, [jobs, selectedYear]);

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-3xl bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 overflow-hidden z-10 max-h-[90vh] flex flex-col">
        {/* Header */}
        <div className="p-4 sm:p-5 border-b border-slate-100 dark:border-slate-800 flex items-start justify-between bg-blue-50/50 dark:bg-blue-950/20">
          <div className="flex items-center gap-3">
            <div className="w-11 h-11 rounded-full bg-blue-100 dark:bg-blue-950 text-blue-600 dark:text-blue-400 flex items-center justify-center shrink-0">
              <BarChart3 className="w-6 h-6" />
            </div>
            <div>
              <h3 className="text-base sm:text-lg font-bold text-slate-900 dark:text-white">
                Monthly Performance & Operations Reports
              </h3>
              <p className="text-xs text-slate-500 dark:text-slate-400">
                Detailed breakdowns, KPI success rates, and downloadable CSV/PDF reports
              </p>
            </div>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Filters */}
        <div className="p-4 border-b border-slate-100 dark:border-slate-800 bg-slate-50 dark:bg-slate-850 flex flex-wrap items-center justify-between gap-3 text-xs">
          <div className="flex items-center gap-2">
            <span className="font-semibold text-slate-500">Filter Year:</span>
            <div className="flex items-center gap-1">
              {availableYears.map((yr) => (
                <button
                  key={yr}
                  onClick={() => setSelectedYear(yr)}
                  className={`px-2.5 py-1 rounded-lg font-semibold transition-colors cursor-pointer ${
                    selectedYear === yr
                      ? 'bg-blue-600 text-white'
                      : 'bg-white dark:bg-slate-750 text-slate-600 dark:text-slate-300 border border-slate-200 dark:border-slate-700'
                  }`}
                >
                  {yr}
                </button>
              ))}
            </div>
          </div>

          <span className="text-slate-500 font-semibold">
            Total Months: {monthlyStats.length}
          </span>
        </div>

        {/* Months List */}
        <div className="p-4 sm:p-5 overflow-y-auto space-y-4 flex-1">
          {monthlyStats.length === 0 ? (
            <div className="p-12 text-center text-slate-400">
              No orders recorded for selected period.
            </div>
          ) : (
            monthlyStats.map(({ stat, jobs: monthJobs }) => {
              const isExpanded = expandedMonthKey === stat.monthYearKey;
              const successRate =
                stat.totalOrders > 0
                  ? Math.round((stat.completedOrders * 100) / stat.totalOrders)
                  : 0;

              return (
                <div
                  key={stat.monthYearKey}
                  className="rounded-2xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-850 overflow-hidden shadow-xs"
                >
                  {/* Month Card Header */}
                  <div className="p-4 flex flex-col sm:flex-row sm:items-center justify-between gap-3 bg-slate-50/50 dark:bg-slate-800/40">
                    <div>
                      <h4 className="font-bold text-base text-slate-900 dark:text-white">
                        {stat.displayMonth}
                      </h4>
                      <p className="text-xs text-slate-500 dark:text-slate-400">
                        {stat.totalOrders} total orders placed
                      </p>
                    </div>

                    {/* Export Actions */}
                    <div className="flex items-center gap-2">
                      <button
                        onClick={() => ReportExportHelper.exportMonthlyReportToCsv(stat, monthJobs)}
                        className="px-3 py-1.5 rounded-xl border border-emerald-300 dark:border-emerald-800 bg-emerald-50 dark:bg-emerald-950/60 text-emerald-700 dark:text-emerald-300 hover:bg-emerald-100 text-xs font-bold flex items-center gap-1.5 cursor-pointer shadow-2xs"
                        title="Download CSV Spreadsheet"
                      >
                        <FileSpreadsheet className="w-3.5 h-3.5" />
                        CSV
                      </button>

                      <button
                        onClick={() => ReportExportHelper.exportMonthlyReportToPdf(stat, monthJobs)}
                        className="px-3 py-1.5 rounded-xl border border-blue-300 dark:border-blue-800 bg-blue-50 dark:bg-blue-950/60 text-blue-700 dark:text-blue-300 hover:bg-blue-100 text-xs font-bold flex items-center gap-1.5 cursor-pointer shadow-2xs"
                        title="Download Styled PDF Report"
                      >
                        <FileText className="w-3.5 h-3.5" />
                        PDF
                      </button>

                      <button
                        onClick={() =>
                          setExpandedMonthKey(isExpanded ? null : stat.monthYearKey)
                        }
                        className="p-1.5 rounded-xl border border-slate-200 dark:border-slate-700 hover:bg-slate-100 text-slate-600"
                        title={isExpanded ? 'Collapse' : 'View Orders'}
                      >
                        {isExpanded ? <ChevronUp className="w-4 h-4" /> : <ChevronDown className="w-4 h-4" />}
                      </button>
                    </div>
                  </div>

                  {/* KPI Row */}
                  <div className="p-4 grid grid-cols-2 sm:grid-cols-5 gap-2.5 text-center text-xs border-t border-slate-100 dark:border-slate-800">
                    <div className="p-2.5 rounded-xl bg-slate-50 dark:bg-slate-800/60">
                      <span className="text-[10px] font-bold text-slate-400 block uppercase">
                        Total
                      </span>
                      <span className="font-extrabold text-base text-slate-900 dark:text-white">
                        {stat.totalOrders}
                      </span>
                    </div>

                    <div className="p-2.5 rounded-xl bg-emerald-50/70 dark:bg-emerald-950/40">
                      <span className="text-[10px] font-bold text-emerald-600 block uppercase">
                        Completed
                      </span>
                      <span className="font-extrabold text-base text-emerald-700 dark:text-emerald-400">
                        {stat.completedOrders}
                      </span>
                    </div>

                    <div className="p-2.5 rounded-xl bg-blue-50/70 dark:bg-blue-950/40">
                      <span className="text-[10px] font-bold text-blue-600 block uppercase">
                        Processing
                      </span>
                      <span className="font-extrabold text-base text-blue-700 dark:text-blue-400">
                        {stat.processingOrders}
                      </span>
                    </div>

                    <div className="p-2.5 rounded-xl bg-rose-50/70 dark:bg-rose-950/40">
                      <span className="text-[10px] font-bold text-rose-600 block uppercase">
                        Cancelled
                      </span>
                      <span className="font-extrabold text-base text-rose-700 dark:text-rose-400">
                        {stat.cancelledOrders}
                      </span>
                    </div>

                    <div className="p-2.5 rounded-xl bg-amber-50/70 dark:bg-amber-950/40 col-span-2 sm:col-span-1">
                      <span className="text-[10px] font-bold text-amber-600 block uppercase">
                        Success Rate
                      </span>
                      <span className="font-extrabold text-base text-amber-700 dark:text-amber-400">
                        {successRate}%
                      </span>
                    </div>
                  </div>

                  {/* Expanded Orders Table */}
                  {isExpanded && (
                    <div className="border-t border-slate-100 dark:border-slate-800 p-4 bg-slate-50/30 dark:bg-slate-900/40">
                      <h5 className="font-bold text-xs text-slate-700 dark:text-slate-300 mb-2">
                        Orders in {stat.displayMonth} ({monthJobs.length}):
                      </h5>
                      <div className="space-y-2 max-h-60 overflow-y-auto pr-1">
                        {monthJobs.map((j) => (
                          <div
                            key={j.id}
                            className="p-2.5 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800 flex items-center justify-between gap-2 text-xs"
                          >
                            <div className="min-w-0">
                              <span className="font-bold text-slate-900 dark:text-white">
                                #{j.id.toString().slice(-4)} • {j.customerName}
                              </span>
                              <p className="text-slate-500 truncate">
                                {j.serviceType} • 📞 {j.customerPhone}
                              </p>
                            </div>
                            <span
                              className={`text-[10px] font-bold px-2 py-0.5 rounded-md shrink-0 ${
                                j.status === 'COMPLETED'
                                  ? 'bg-emerald-100 text-emerald-800 dark:bg-emerald-950 dark:text-emerald-300'
                                  : j.status === 'PROCESSING'
                                  ? 'bg-blue-100 text-blue-800 dark:bg-blue-950 dark:text-blue-300'
                                  : j.status === 'CANCELLED'
                                  ? 'bg-rose-100 text-rose-800 dark:bg-rose-950 dark:text-rose-300'
                                  : 'bg-amber-100 text-amber-800 dark:bg-amber-950 dark:text-amber-300'
                              }`}
                            >
                              {j.status}
                            </span>
                          </div>
                        ))}
                      </div>
                    </div>
                  )}
                </div>
              );
            })
          )}
        </div>

        {/* Footer */}
        <div className="p-4 border-t border-slate-100 dark:border-slate-800 flex justify-end">
          <button
            onClick={onClose}
            className="px-5 py-2 rounded-xl bg-slate-900 dark:bg-white text-white dark:text-slate-900 font-bold text-xs"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
};
