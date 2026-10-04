import React, { useMemo } from 'react';
import { X, History, Star, Phone, CheckCircle2, Clock } from 'lucide-react';
import { Expert, CustomerJob } from '../../types';

interface ExpertWorkHistoryModalProps {
  expert: Expert | null;
  allJobs: CustomerJob[];
  onClose: () => void;
}

export const ExpertWorkHistoryModal: React.FC<ExpertWorkHistoryModalProps> = ({
  expert,
  allJobs,
  onClose,
}) => {
  if (!expert) return null;

  const expertJobs = useMemo(() => {
    return allJobs.filter((j) => j.assignedExpertId === expert.id);
  }, [allJobs, expert]);

  const completed = expertJobs.filter((j) => j.status === 'COMPLETED').length;
  const cancelled = expertJobs.filter((j) => j.status === 'CANCELLED').length;
  const processing = expertJobs.filter((j) => j.status === 'PROCESSING').length;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-lg bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 overflow-hidden z-10 max-h-[85vh] flex flex-col">
        {/* Header */}
        <div className="p-4 sm:p-5 border-b border-slate-100 dark:border-slate-800 flex items-start justify-between bg-blue-50/50 dark:bg-blue-950/20">
          <div className="flex items-center gap-3">
            <div className="w-11 h-11 rounded-full bg-blue-100 dark:bg-blue-950 text-blue-600 dark:text-blue-400 flex items-center justify-center shrink-0">
              <History className="w-6 h-6" />
            </div>
            <div>
              <h3 className="text-base sm:text-lg font-bold text-slate-900 dark:text-white">
                Work History: {expert.name}
              </h3>
              <p className="text-xs text-slate-500 dark:text-slate-400">
                {expert.category} • 📞 +91 {expert.phone}
              </p>
            </div>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Stats Row */}
        <div className="p-4 bg-slate-50 dark:bg-slate-850 border-b border-slate-100 dark:border-slate-800 grid grid-cols-4 gap-2 text-center text-xs">
          <div className="p-2 rounded-xl bg-white dark:bg-slate-800 border border-slate-200 dark:border-slate-700">
            <span className="text-[10px] text-slate-400 block font-semibold">TOTAL</span>
            <span className="font-extrabold text-sm text-slate-900 dark:text-white">
              {expertJobs.length}
            </span>
          </div>

          <div className="p-2 rounded-xl bg-emerald-50 dark:bg-emerald-950/40 border border-emerald-200 dark:border-emerald-800">
            <span className="text-[10px] text-emerald-600 block font-semibold">COMPLETED</span>
            <span className="font-extrabold text-sm text-emerald-700 dark:text-emerald-300">
              {completed}
            </span>
          </div>

          <div className="p-2 rounded-xl bg-blue-50 dark:bg-blue-950/40 border border-blue-200 dark:border-blue-800">
            <span className="text-[10px] text-blue-600 block font-semibold">ACTIVE</span>
            <span className="font-extrabold text-sm text-blue-700 dark:text-blue-300">
              {processing}
            </span>
          </div>

          <div className="p-2 rounded-xl bg-rose-50 dark:bg-rose-950/40 border border-rose-200 dark:border-rose-800">
            <span className="text-[10px] text-rose-600 block font-semibold">CANCELLED</span>
            <span className="font-extrabold text-sm text-rose-700 dark:text-rose-300">
              {cancelled}
            </span>
          </div>
        </div>

        {/* List */}
        <div className="p-4 sm:p-5 overflow-y-auto space-y-3 flex-1 text-xs">
          {expertJobs.length === 0 ? (
            <div className="p-8 text-center text-slate-400">
              No tasks have been assigned to this expert yet.
            </div>
          ) : (
            expertJobs.map((job) => {
              const dateStr = new Date(job.createdAt).toLocaleDateString('en-IN', {
                day: '2-digit',
                month: 'short',
                hour: '2-digit',
                minute: '2-digit',
              });

              return (
                <div
                  key={job.id}
                  className="p-3 rounded-xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-850 space-y-1.5"
                >
                  <div className="flex items-center justify-between">
                    <span className="font-bold text-slate-900 dark:text-white">
                      #{job.id.toString().slice(-4)} • {job.customerName}
                    </span>
                    <span
                      className={`text-[10px] font-bold px-2 py-0.5 rounded-full ${
                        job.status === 'COMPLETED'
                          ? 'bg-emerald-100 text-emerald-800 dark:bg-emerald-950 dark:text-emerald-300'
                          : job.status === 'PROCESSING'
                          ? 'bg-blue-100 text-blue-800 dark:bg-blue-950 dark:text-blue-300'
                          : 'bg-rose-100 text-rose-800 dark:bg-rose-950 dark:text-rose-300'
                      }`}
                    >
                      {job.status}
                    </span>
                  </div>

                  <p className="text-slate-600 dark:text-slate-400">
                    {job.serviceType} • 📞 +91 {job.customerPhone}
                  </p>
                  <p className="text-slate-400 text-[11px]">{dateStr}</p>

                  {job.ratingGiven && (
                    <div className="flex items-center gap-1 text-amber-500 font-bold pt-1">
                      <Star className="w-3.5 h-3.5 fill-amber-400" />
                      <span>{job.ratingGiven}/5</span>
                      {job.reviewFeedback && (
                        <span className="text-slate-500 font-normal italic truncate ml-1">
                          "{job.reviewFeedback}"
                        </span>
                      )}
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
