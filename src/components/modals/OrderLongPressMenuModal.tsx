import React from 'react';
import { X, Edit2, UserMinus } from 'lucide-react';
import { CustomerJob } from '../../types';

interface OrderLongPressMenuModalProps {
  job: CustomerJob | null;
  onClose: () => void;
  onEditDetails: (job: CustomerJob) => void;
  onUnassignExpert: (job: CustomerJob) => void;
}

export const OrderLongPressMenuModal: React.FC<OrderLongPressMenuModalProps> = ({
  job,
  onClose,
  onEditDetails,
  onUnassignExpert,
}) => {
  if (!job) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-sm bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 p-4 sm:p-5 z-10 space-y-3">
        <div className="flex items-center justify-between pb-2 border-b border-slate-100 dark:border-slate-800">
          <div>
            <h3 className="text-sm font-bold text-slate-900 dark:text-white">
              Order #{job.id.toString().slice(-4)} Options
            </h3>
            <p className="text-xs text-slate-500">{job.customerName}</p>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-4 h-4" />
          </button>
        </div>

        <div className="space-y-2">
          <button
            onClick={() => {
              onEditDetails(job);
              onClose();
            }}
            className="w-full flex items-center gap-2.5 p-3 rounded-xl border border-slate-200 dark:border-slate-700 hover:bg-slate-50 dark:hover:bg-slate-800 text-xs sm:text-sm font-semibold text-slate-800 dark:text-slate-200 transition-colors"
          >
            <Edit2 className="w-4 h-4 text-blue-600" />
            Edit Customer & Order Details
          </button>

          {job.assignedExpertName && (
            <button
              onClick={() => {
                onUnassignExpert(job);
                onClose();
              }}
              className="w-full flex items-center gap-2.5 p-3 rounded-xl border border-amber-200 dark:border-amber-900/60 bg-amber-50/50 dark:bg-amber-950/30 hover:bg-amber-100 text-xs sm:text-sm font-semibold text-amber-800 dark:text-amber-300 transition-colors"
            >
              <UserMinus className="w-4 h-4 text-amber-600" />
              Unassign Expert ({job.assignedExpertName})
            </button>
          )}
        </div>
      </div>
    </div>
  );
};
