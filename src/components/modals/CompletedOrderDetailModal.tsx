import React from 'react';
import {
  X,
  CheckCircle2,
  Clock,
  ShieldCheck,
  ShieldAlert,
  Phone,
  User,
  Wrench,
  MapPin,
  Star,
  Share2,
  Copy,
  Navigation,
} from 'lucide-react';
import { CustomerJob } from '../../types';
import { WhatsAppHelper } from '../../utils/whatsAppHelper';

interface CompletedOrderDetailModalProps {
  job: CustomerJob | null;
  onClose: () => void;
}

export const CompletedOrderDetailModal: React.FC<CompletedOrderDetailModalProps> = ({
  job,
  onClose,
}) => {
  if (!job) return null;

  const createdDateStr = new Date(job.createdAt).toLocaleString('en-IN', {
    day: '2-digit',
    month: 'short',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    hour12: true,
  });

  const completedDateStr = job.completedAt
    ? new Date(job.completedAt).toLocaleString('en-IN', {
        day: '2-digit',
        month: 'short',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        hour12: true,
      })
    : 'Recorded';

  const completedTime = job.completedAt || job.createdAt;
  const daysPassed = Math.floor((Date.now() - completedTime) / 86400000);
  const isWarrantyValid = daysPassed <= 10;
  const daysRemaining = Math.max(0, 10 - daysPassed);

  const calculateDuration = (created: number, completed?: number | null) => {
    const end = completed || Date.now();
    const diffMs = Math.max(0, end - created);
    const diffMins = Math.floor(diffMs / 60000);
    const hours = Math.floor(diffMins / 60);
    const mins = diffMins % 60;
    if (hours === 0) return `${mins} mins`;
    return `${hours} hr ${mins} mins`;
  };

  const receiptSummaryText = `📄 *HURIFIX SERVICE RECEIPT & SUMMARY*
━━━━━━━━━━━━━━━━━━━━
Order ID: #${job.id}
Status: COMPLETED ✅
Customer: ${job.customerName} (📞 +91 ${job.customerPhone})
Service: ${job.serviceType}
Assigned Expert: ${job.assignedExpertName || 'N/A'} (📞 +91 ${job.assignedExpertPhone || 'N/A'})
Address: ${job.address}
Duration: ${calculateDuration(job.createdAt, job.completedAt)}
Rating Given: ${job.ratingGiven ? `${job.ratingGiven}/5 Stars` : 'N/A'}
Feedback: "${job.reviewFeedback || 'Great service'}"
Warranty: 10-Day Hurifix Service Guarantee
━━━━━━━━━━━━━━━━━━━━
- Team Hurifix | Many Problems | One Solution`;

  const handleShareReceipt = () => {
    WhatsAppHelper.openWhatsAppDirectMessage(job.customerPhone, receiptSummaryText);
  };

  const handleCopyReceipt = async () => {
    const success = await WhatsAppHelper.copyToClipboard(receiptSummaryText);
    if (success) alert('Service receipt copied to clipboard!');
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-xl bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 overflow-hidden z-10 max-h-[90vh] flex flex-col">
        {/* Header */}
        <div className="p-4 sm:p-5 border-b border-slate-100 dark:border-slate-800 flex items-start justify-between bg-emerald-50/50 dark:bg-emerald-950/20">
          <div className="flex items-center gap-3">
            <div className="w-11 h-11 rounded-full bg-emerald-100 dark:bg-emerald-950 text-emerald-600 dark:text-emerald-400 flex items-center justify-center">
              <CheckCircle2 className="w-6 h-6" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h3 className="text-lg font-bold text-slate-900 dark:text-white">
                  Order #{job.id.toString().slice(-4)} — Completed
                </h3>
              </div>
              <p className="text-xs text-slate-500 dark:text-slate-400">
                Complete Work History & Service Summary
              </p>
            </div>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Content */}
        <div className="p-4 sm:p-5 overflow-y-auto space-y-4 flex-1 text-xs sm:text-sm">
          {/* Key Metrics Row: Duration + Warranty */}
          <div className="grid grid-cols-2 gap-3">
            <div className="p-3 rounded-xl bg-slate-50 dark:bg-slate-800/60 border border-slate-200 dark:border-slate-700">
              <div className="flex items-center gap-1.5 text-xs text-slate-500 mb-1">
                <Clock className="w-3.5 h-3.5 text-blue-600" />
                <span>Task Duration</span>
              </div>
              <span className="font-bold text-slate-900 dark:text-white text-sm">
                ⏱ {calculateDuration(job.createdAt, job.completedAt)}
              </span>
            </div>

            <div
              className={`p-3 rounded-xl border ${
                isWarrantyValid
                  ? 'bg-emerald-50/60 dark:bg-emerald-950/40 border-emerald-200 dark:border-emerald-800'
                  : 'bg-rose-50/60 dark:bg-rose-950/40 border-rose-200 dark:border-rose-800'
              }`}
            >
              <div className="flex items-center gap-1.5 text-xs text-slate-500 mb-1">
                {isWarrantyValid ? (
                  <ShieldCheck className="w-3.5 h-3.5 text-emerald-600" />
                ) : (
                  <ShieldAlert className="w-3.5 h-3.5 text-rose-600" />
                )}
                <span>10-Day Warranty</span>
              </div>
              <span
                className={`font-bold text-xs sm:text-sm ${
                  isWarrantyValid
                    ? 'text-emerald-700 dark:text-emerald-300'
                    : 'text-rose-700 dark:text-rose-300'
                }`}
              >
                {isWarrantyValid ? `Active (${daysRemaining} Days Left)` : `Expired (${daysPassed} days ago)`}
              </span>
            </div>
          </div>

          {/* Time Records */}
          <div className="p-3.5 rounded-xl bg-slate-50 dark:bg-slate-800/60 border border-slate-200 dark:border-slate-700 space-y-1.5">
            <div className="flex items-center justify-between text-xs">
              <span className="text-slate-500">Order Placed / Dispatched:</span>
              <span className="font-semibold text-slate-800 dark:text-slate-200">{createdDateStr}</span>
            </div>
            <div className="flex items-center justify-between text-xs">
              <span className="text-slate-500">Job Finished & Completed:</span>
              <span className="font-semibold text-slate-800 dark:text-slate-200">{completedDateStr}</span>
            </div>
          </div>

          {/* Customer Details */}
          <div className="p-3.5 rounded-xl border border-slate-200 dark:border-slate-700 space-y-2">
            <div className="flex items-center justify-between">
              <span className="text-xs font-bold text-blue-600 uppercase tracking-wider">
                Customer Details
              </span>
              <button
                onClick={() => WhatsAppHelper.openDialer(job.customerPhone)}
                className="px-2.5 py-1 rounded-lg border border-slate-200 dark:border-slate-700 text-xs font-semibold flex items-center gap-1 hover:bg-slate-50"
              >
                <Phone className="w-3 h-3 text-blue-600" />
                Call Customer
              </button>
            </div>
            <p className="font-bold text-slate-900 dark:text-white">
              {job.customerName} <span className="font-normal text-slate-500">(📞 +91 {job.customerPhone})</span>
            </p>
            <p className="text-xs text-slate-600 dark:text-slate-300 flex items-start gap-1.5">
              <MapPin className="w-3.5 h-3.5 text-slate-400 shrink-0 mt-0.5" />
              <span>{job.address || 'Address not specified'}</span>
            </p>
          </div>

          {/* Expert Details */}
          {job.assignedExpertName && (
            <div className="p-3.5 rounded-xl border border-slate-200 dark:border-slate-700 space-y-2">
              <div className="flex items-center justify-between">
                <span className="text-xs font-bold text-blue-600 uppercase tracking-wider">
                  Assigned Expert
                </span>
                {job.assignedExpertPhone && (
                  <button
                    onClick={() => WhatsAppHelper.openDialer(job.assignedExpertPhone!)}
                    className="px-2.5 py-1 rounded-lg border border-slate-200 dark:border-slate-700 text-xs font-semibold flex items-center gap-1 hover:bg-slate-50"
                  >
                    <Phone className="w-3 h-3 text-blue-600" />
                    Call Expert
                  </button>
                )}
              </div>
              <p className="font-bold text-slate-900 dark:text-white">
                {job.assignedExpertName}{' '}
                {job.assignedExpertPhone && (
                  <span className="font-normal text-slate-500">(📞 +91 {job.assignedExpertPhone})</span>
                )}
              </p>
            </div>
          )}

          {/* Service & Problem Description */}
          <div className="p-3.5 rounded-xl bg-slate-50 dark:bg-slate-800/60 border border-slate-200 dark:border-slate-700 space-y-1">
            <span className="text-xs font-bold text-blue-600 uppercase tracking-wider block">
              Service Task
            </span>
            <p className="font-semibold text-slate-900 dark:text-white">{job.serviceType}</p>
            {job.issueDescription && (
              <p className="text-xs text-slate-600 dark:text-slate-300">
                "{job.issueDescription}"
              </p>
            )}
          </div>

          {/* Review & Feedback */}
          {job.ratingGiven != null && (
            <div className="p-3.5 rounded-xl bg-amber-50/70 dark:bg-amber-950/30 border border-amber-200 dark:border-amber-900 space-y-1.5">
              <span className="text-xs font-bold text-amber-700 dark:text-amber-400 uppercase tracking-wider block">
                Customer Rating & Feedback
              </span>
              <div className="flex items-center gap-1.5 text-amber-500">
                {[1, 2, 3, 4, 5].map((star) => (
                  <Star
                    key={star}
                    className={`w-4 h-4 ${
                      star <= job.ratingGiven! ? 'fill-amber-400' : 'text-slate-300'
                    }`}
                  />
                ))}
                <span className="font-bold text-xs text-amber-800 dark:text-amber-300 ml-1">
                  ({job.ratingGiven}/5 Stars)
                </span>
              </div>
              {job.reviewFeedback && (
                <p className="text-xs text-slate-700 dark:text-slate-300 italic">
                  "{job.reviewFeedback}"
                </p>
              )}
            </div>
          )}
        </div>

        {/* Footer Actions */}
        <div className="p-4 border-t border-slate-100 dark:border-slate-800 flex flex-wrap items-center justify-between gap-2">
          <div className="flex items-center gap-2">
            <button
              onClick={handleShareReceipt}
              className="px-3.5 py-2 rounded-xl bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-xs inline-flex items-center gap-1.5 shadow-2xs cursor-pointer"
            >
              <Share2 className="w-3.5 h-3.5" />
              Share Summary via WhatsApp
            </button>

            <button
              onClick={handleCopyReceipt}
              className="p-2 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-600 dark:text-slate-300 hover:bg-slate-50 text-xs inline-flex items-center gap-1"
              title="Copy Summary"
            >
              <Copy className="w-3.5 h-3.5" />
            </button>
          </div>

          <button
            onClick={onClose}
            className="px-4 py-2 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-300 text-xs font-bold hover:bg-slate-50"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
};
