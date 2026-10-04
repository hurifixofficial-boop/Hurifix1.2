import React, { useState } from 'react';
import { X, Star } from 'lucide-react';
import { CustomerJob } from '../../types';

interface ReviewModalProps {
  target: { job: CustomerJob; isCompleted: boolean } | null;
  onClose: () => void;
  onConfirm: (rating: number, feedback: string | null) => void;
}

export const ReviewModal: React.FC<ReviewModalProps> = ({
  target,
  onClose,
  onConfirm,
}) => {
  if (!target) return null;
  const { job, isCompleted } = target;

  const [rating, setRating] = useState<number>(isCompleted ? 5 : 3);
  const [feedback, setFeedback] = useState<string>('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onConfirm(rating, feedback.trim() || null);
    onClose();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-md bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 p-5 sm:p-6 z-10">
        <div className="flex items-start justify-between pb-3 border-b border-slate-100 dark:border-slate-800">
          <div>
            <h3
              className={`text-lg font-bold ${
                isCompleted
                  ? 'text-emerald-600 dark:text-emerald-400'
                  : 'text-rose-600 dark:text-rose-400'
              }`}
            >
              {isCompleted ? 'Mark Order as Completed' : 'Cancel Order'}
            </h3>
            <p className="text-xs text-slate-500 dark:text-slate-400">
              Customer: <span className="font-semibold text-slate-800 dark:text-slate-200">{job.customerName}</span>
            </p>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        <form onSubmit={handleSubmit} className="mt-4 space-y-4">
          <div>
            <p className="text-xs text-slate-600 dark:text-slate-300 mb-2">
              {job.assignedExpertName
                ? `Submit rating and review for Expert '${job.assignedExpertName}':`
                : 'Select rating score for this service request:'}
            </p>

            {/* 5-Star Rating */}
            <div className="flex items-center justify-center gap-1.5 py-2">
              {[1, 2, 3, 4, 5].map((star) => (
                <button
                  type="button"
                  key={star}
                  onClick={() => setRating(star)}
                  className="p-1 text-amber-400 hover:scale-110 transition-transform cursor-pointer"
                >
                  <Star
                    className={`w-8 h-8 ${
                      star <= rating ? 'fill-amber-400 text-amber-400' : 'text-slate-300 dark:text-slate-600'
                    }`}
                  />
                </button>
              ))}
            </div>
            <p className="text-center text-xs font-bold text-amber-600 dark:text-amber-400">
              {rating} / 5 Stars
            </p>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              {isCompleted ? 'Customer Feedback / Notes (Optional)' : 'Reason for Cancellation *'}
            </label>
            <textarea
              value={feedback}
              onChange={(e) => setFeedback(e.target.value)}
              rows={3}
              placeholder={
                isCompleted
                  ? 'e.g. Excellent service, punctual and resolved within 30 minutes.'
                  : 'e.g. Customer cancelled due to unavailability, or duplicate request.'
              }
              required={!isCompleted}
              className="w-full p-3 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-xs sm:text-sm text-slate-900 dark:text-white focus:ring-2 focus:ring-blue-500/20 resize-none"
            />
          </div>

          <div className="flex items-center justify-end gap-2 pt-2">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 rounded-xl border border-slate-300 dark:border-slate-700 text-xs font-bold text-slate-700 dark:text-slate-300 hover:bg-slate-50"
            >
              Cancel
            </button>
            <button
              type="submit"
              className={`px-5 py-2 rounded-xl text-white font-bold text-xs shadow-xs cursor-pointer ${
                isCompleted
                  ? 'bg-emerald-600 hover:bg-emerald-700'
                  : 'bg-rose-600 hover:bg-rose-700'
              }`}
            >
              {isCompleted ? 'Confirm Complete' : 'Confirm Cancel'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
