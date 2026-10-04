import React, { useState } from 'react';
import { X, Trash2, RotateCcw, AlertTriangle } from 'lucide-react';
import { CustomerJob, Expert } from '../../types';

interface RecycleBinModalProps {
  isOpen: boolean;
  onClose: () => void;
  deletedJobs: CustomerJob[];
  deletedExperts: Expert[];
  onRestoreJob: (jobId: number) => void;
  onDeleteJobPermanently: (jobId: number) => void;
  onRestoreExpert: (expertId: number) => void;
  onDeleteExpertPermanently: (expertId: number) => void;
  onEmptyRecycleBin: () => void;
}

export const RecycleBinModal: React.FC<RecycleBinModalProps> = ({
  isOpen,
  onClose,
  deletedJobs,
  deletedExperts,
  onRestoreJob,
  onDeleteJobPermanently,
  onRestoreExpert,
  onDeleteExpertPermanently,
  onEmptyRecycleBin,
}) => {
  const [activeTab, setActiveTab] = useState<'JOBS' | 'EXPERTS'>('JOBS');

  if (!isOpen) return null;

  const totalCount = deletedJobs.length + deletedExperts.length;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-xl bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 overflow-hidden z-10 max-h-[85vh] flex flex-col">
        {/* Header */}
        <div className="p-4 sm:p-5 border-b border-slate-100 dark:border-slate-800 flex items-start justify-between bg-rose-50/50 dark:bg-rose-950/20">
          <div className="flex items-center gap-3">
            <div className="w-11 h-11 rounded-full bg-rose-100 dark:bg-rose-950 text-rose-600 dark:text-rose-400 flex items-center justify-center shrink-0">
              <Trash2 className="w-6 h-6" />
            </div>
            <div>
              <h3 className="text-base sm:text-lg font-bold text-slate-900 dark:text-white">
                Recycle Bin
              </h3>
              <p className="text-xs text-slate-500 dark:text-slate-400">
                Items are stored safely for 30 days before permanent deletion
              </p>
            </div>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Tab Switcher & Empty Button */}
        <div className="p-3 border-b border-slate-100 dark:border-slate-800 bg-slate-50 dark:bg-slate-850 flex items-center justify-between text-xs">
          <div className="flex items-center gap-2">
            <button
              onClick={() => setActiveTab('JOBS')}
              className={`px-3 py-1.5 rounded-xl font-bold transition-colors cursor-pointer ${
                activeTab === 'JOBS'
                  ? 'bg-blue-600 text-white shadow-2xs'
                  : 'bg-white dark:bg-slate-800 text-slate-600 dark:text-slate-300'
              }`}
            >
              Deleted Orders ({deletedJobs.length})
            </button>

            <button
              onClick={() => setActiveTab('EXPERTS')}
              className={`px-3 py-1.5 rounded-xl font-bold transition-colors cursor-pointer ${
                activeTab === 'EXPERTS'
                  ? 'bg-blue-600 text-white shadow-2xs'
                  : 'bg-white dark:bg-slate-800 text-slate-600 dark:text-slate-300'
              }`}
            >
              Deleted Experts ({deletedExperts.length})
            </button>
          </div>

          {totalCount > 0 && (
            <button
              onClick={() => {
                if (confirm('Are you sure you want to permanently clear all items in the Recycle Bin?')) {
                  onEmptyRecycleBin();
                }
              }}
              className="px-2.5 py-1 rounded-lg text-rose-600 dark:text-rose-400 font-bold hover:bg-rose-50 dark:hover:bg-rose-950/60"
            >
              Empty Bin
            </button>
          )}
        </div>

        {/* List Content */}
        <div className="p-4 sm:p-5 overflow-y-auto space-y-2.5 flex-1 text-xs">
          {activeTab === 'JOBS' ? (
            deletedJobs.length === 0 ? (
              <div className="p-8 text-center text-slate-400">
                Recycle Bin is empty. No deleted orders.
              </div>
            ) : (
              deletedJobs.map((job) => (
                <div
                  key={job.id}
                  className="p-3 rounded-xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-850 flex items-center justify-between gap-3"
                >
                  <div className="min-w-0">
                    <h4 className="font-bold text-slate-900 dark:text-white">
                      #{job.id.toString().slice(-4)} • {job.customerName}
                    </h4>
                    <p className="text-slate-500 truncate">
                      {job.serviceType} • 📞 {job.customerPhone}
                    </p>
                  </div>

                  <div className="flex items-center gap-1.5 shrink-0">
                    <button
                      onClick={() => onRestoreJob(job.id)}
                      className="px-2.5 py-1.5 rounded-lg bg-emerald-50 dark:bg-emerald-950 text-emerald-700 dark:text-emerald-300 font-bold flex items-center gap-1 hover:bg-emerald-100"
                      title="Restore Order"
                    >
                      <RotateCcw className="w-3.5 h-3.5" />
                      Restore
                    </button>

                    <button
                      onClick={() => onDeleteJobPermanently(job.id)}
                      className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50"
                      title="Delete Permanently"
                    >
                      <Trash2 className="w-3.5 h-3.5" />
                    </button>
                  </div>
                </div>
              ))
            )
          ) : deletedExperts.length === 0 ? (
            <div className="p-8 text-center text-slate-400">
              Recycle Bin is empty. No deleted experts.
            </div>
          ) : (
            deletedExperts.map((expert) => (
              <div
                key={expert.id}
                className="p-3 rounded-xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-850 flex items-center justify-between gap-3"
              >
                <div className="min-w-0">
                  <h4 className="font-bold text-slate-900 dark:text-white">
                    {expert.name} ({expert.category})
                  </h4>
                  <p className="text-slate-500 truncate">
                    📞 +91 {expert.phone}
                  </p>
                </div>

                <div className="flex items-center gap-1.5 shrink-0">
                  <button
                    onClick={() => onRestoreExpert(expert.id)}
                    className="px-2.5 py-1.5 rounded-lg bg-emerald-50 dark:bg-emerald-950 text-emerald-700 dark:text-emerald-300 font-bold flex items-center gap-1 hover:bg-emerald-100"
                    title="Restore Expert"
                  >
                    <RotateCcw className="w-3.5 h-3.5" />
                    Restore
                  </button>

                  <button
                    onClick={() => onDeleteExpertPermanently(expert.id)}
                    className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50"
                    title="Delete Permanently"
                  >
                    <Trash2 className="w-3.5 h-3.5" />
                  </button>
                </div>
              </div>
            ))
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
