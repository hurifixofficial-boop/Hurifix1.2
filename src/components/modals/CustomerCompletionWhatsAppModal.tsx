import React, { useMemo } from 'react';
import { Send, Clock, CheckCircle2 } from 'lucide-react';
import { CustomerJob } from '../../types';
import { WhatsAppHelper } from '../../utils/whatsAppHelper';

interface CustomerCompletionWhatsAppModalProps {
  job: CustomerJob | null;
  onSendWhatsApp: () => void;
  onLater: () => void;
}

export const CustomerCompletionWhatsAppModal: React.FC<CustomerCompletionWhatsAppModalProps> = ({
  job,
  onSendWhatsApp,
  onLater,
}) => {
  if (!job) return null;

  const previewMessage = useMemo(() => {
    return WhatsAppHelper.createCompletionCustomerMessage(job.customerName);
  }, [job]);

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onLater} />
      <div className="relative w-full max-w-lg bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 p-5 sm:p-6 z-10 max-h-[90vh] flex flex-col">
        <div className="flex items-center gap-3 mb-3">
          <div className="w-12 h-12 rounded-xl bg-emerald-100 dark:bg-emerald-950 text-emerald-600 dark:text-emerald-400 flex items-center justify-center shrink-0">
            <CheckCircle2 className="w-6 h-6" />
          </div>
          <div>
            <h3 className="text-base font-bold text-slate-900 dark:text-white">
              Send Completion Message to Customer?
            </h3>
            <p className="text-xs text-slate-500 dark:text-slate-400">
              Thank <strong>{job.customerName}</strong> and share feedback request & Instagram link
            </p>
          </div>
        </div>

        {/* Message Preview */}
        <div className="flex-1 overflow-y-auto my-3 p-3.5 rounded-xl bg-slate-50 dark:bg-slate-800/60 border border-slate-200 dark:border-slate-700 text-xs font-mono text-slate-800 dark:text-slate-200 whitespace-pre-line">
          {previewMessage}
        </div>

        <div className="space-y-2 pt-2">
          <button
            onClick={onSendWhatsApp}
            className="w-full py-2.5 px-4 rounded-xl bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-xs sm:text-sm flex items-center justify-center gap-2 shadow-xs transition-colors cursor-pointer"
          >
            <Send className="w-4 h-4" />
            Send Completion WhatsApp to Customer
          </button>

          <button
            onClick={onLater}
            className="w-full py-2 px-4 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-300 hover:bg-slate-50 dark:hover:bg-slate-800 font-semibold text-xs flex items-center justify-center gap-1.5 transition-colors cursor-pointer"
          >
            <Clock className="w-3.5 h-3.5 text-slate-400" />
            Skip Message (Proceed to Rating)
          </button>
        </div>
      </div>
    </div>
  );
};
