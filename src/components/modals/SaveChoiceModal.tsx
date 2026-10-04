import React from 'react';
import { CheckCircle2, Search, Clock } from 'lucide-react';
import { CustomerJob } from '../../types';

interface SaveChoiceModalProps {
  job: CustomerJob | null;
  onFindNearest: (job: CustomerJob) => void;
  onAssignLater: () => void;
}

export const SaveChoiceModal: React.FC<SaveChoiceModalProps> = ({
  job,
  onFindNearest,
  onAssignLater,
}) => {
  if (!job) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onAssignLater} />
      <div className="relative w-full max-w-md bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 p-5 sm:p-6 z-10 text-center">
        <div className="w-14 h-14 mx-auto rounded-full bg-emerald-100 dark:bg-emerald-950 text-emerald-600 dark:text-emerald-400 flex items-center justify-center mb-3">
          <CheckCircle2 className="w-8 h-8" />
        </div>

        <h3 className="text-lg font-bold text-slate-900 dark:text-white">
          New Customer Order Saved!
        </h3>
        <p className="text-xs text-slate-500 dark:text-slate-400 mt-1">
          Order for <strong className="text-slate-700 dark:text-slate-200">"{job.customerName}"</strong> has been saved.
          Would you like to find nearest experts now or assign later?
        </p>

        <div className="mt-5 space-y-2.5">
          <button
            onClick={() => onFindNearest(job)}
            className="w-full py-3 px-4 rounded-xl bg-blue-600 hover:bg-blue-700 text-white font-bold text-sm flex items-center justify-center gap-2 shadow-xs transition-colors cursor-pointer"
          >
            <Search className="w-4 h-4" />
            1. Find Nearest Experts Now
          </button>

          <button
            onClick={onAssignLater}
            className="w-full py-2.5 px-4 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-300 hover:bg-slate-50 dark:hover:bg-slate-800 font-semibold text-xs flex items-center justify-center gap-1.5 transition-colors cursor-pointer"
          >
            <Clock className="w-4 h-4 text-slate-400" />
            2. Assign Later (Move to Pending)
          </button>
        </div>
      </div>
    </div>
  );
};
