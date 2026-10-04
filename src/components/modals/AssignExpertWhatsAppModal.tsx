import React from 'react';
import { Send, Clock, MessageSquare } from 'lucide-react';
import { CustomerJob, RankedExpert } from '../../types';

interface AssignExpertWhatsAppModalProps {
  data: { job: CustomerJob; ranked: RankedExpert } | null;
  onSendWhatsApp: () => void;
  onLater: () => void;
}

export const AssignExpertWhatsAppModal: React.FC<AssignExpertWhatsAppModalProps> = ({
  data,
  onSendWhatsApp,
  onLater,
}) => {
  if (!data) return null;
  const { job, ranked } = data;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onLater} />
      <div className="relative w-full max-w-md bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 p-5 sm:p-6 z-10">
        <div className="flex items-center gap-3 mb-3">
          <div className="w-12 h-12 rounded-xl bg-emerald-100 dark:bg-emerald-950 text-emerald-600 dark:text-emerald-400 flex items-center justify-center shrink-0">
            <MessageSquare className="w-6 h-6" />
          </div>
          <div>
            <h3 className="text-base font-bold text-slate-900 dark:text-white">
              Send Dispatch Message to Expert?
            </h3>
            <p className="text-xs text-slate-500 dark:text-slate-400">
              Task assigned to <strong>{ranked.expert.name}</strong>
            </p>
          </div>
        </div>

        <p className="text-xs text-slate-600 dark:text-slate-300 bg-slate-50 dark:bg-slate-800/60 p-3 rounded-xl border border-slate-200 dark:border-slate-700">
          Would you like to open WhatsApp now with the pre-filled dispatch order details (customer name, contact, problem, and Google Maps pin) for <strong>{ranked.expert.name}</strong>?
        </p>

        <div className="mt-5 space-y-2">
          <button
            onClick={onSendWhatsApp}
            className="w-full py-2.5 px-4 rounded-xl bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-xs sm:text-sm flex items-center justify-center gap-2 shadow-xs transition-colors cursor-pointer"
          >
            <Send className="w-4 h-4" />
            Yes, Send on WhatsApp Now
          </button>

          <button
            onClick={onLater}
            className="w-full py-2 px-4 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-300 hover:bg-slate-50 dark:hover:bg-slate-800 font-semibold text-xs flex items-center justify-center gap-1.5 transition-colors cursor-pointer"
          >
            <Clock className="w-3.5 h-3.5 text-slate-400" />
            Send Later (Save Assignment)
          </button>
        </div>
      </div>
    </div>
  );
};
