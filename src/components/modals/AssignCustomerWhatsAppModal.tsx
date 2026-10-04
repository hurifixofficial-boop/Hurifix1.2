import React, { useMemo } from 'react';
import { Send, Clock, UserCheck } from 'lucide-react';
import { CustomerJob, RankedExpert } from '../../types';
import { WhatsAppHelper } from '../../utils/whatsAppHelper';

interface AssignCustomerWhatsAppModalProps {
  data: { job: CustomerJob; ranked: RankedExpert; estTime: string } | null;
  onSendWhatsApp: () => void;
  onLater: () => void;
}

export const AssignCustomerWhatsAppModal: React.FC<AssignCustomerWhatsAppModalProps> = ({
  data,
  onSendWhatsApp,
  onLater,
}) => {
  if (!data) return null;
  const { job, ranked, estTime } = data;

  const previewMessage = useMemo(() => {
    return WhatsAppHelper.createCustomerAssignmentNotificationMessage(
      job.customerName,
      ranked.expert.name,
      ranked.expert.phone,
      job.serviceType,
      estTime
    );
  }, [job, ranked, estTime]);

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onLater} />
      <div className="relative w-full max-w-lg bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 p-5 sm:p-6 z-10 max-h-[90vh] flex flex-col">
        <div className="flex items-center gap-3 mb-3">
          <div className="w-12 h-12 rounded-xl bg-blue-100 dark:bg-blue-950 text-blue-600 dark:text-blue-400 flex items-center justify-center shrink-0">
            <UserCheck className="w-6 h-6" />
          </div>
          <div>
            <h3 className="text-base font-bold text-slate-900 dark:text-white">
              Notify Customer on WhatsApp?
            </h3>
            <p className="text-xs text-slate-500 dark:text-slate-400">
              Inform <strong>{job.customerName}</strong> about assigned expert & arrival time
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
            className="w-full py-2.5 px-4 rounded-xl bg-blue-600 hover:bg-blue-700 text-white font-bold text-xs sm:text-sm flex items-center justify-center gap-2 shadow-xs transition-colors cursor-pointer"
          >
            <Send className="w-4 h-4" />
            Send WhatsApp Notification to Customer
          </button>

          <button
            onClick={onLater}
            className="w-full py-2 px-4 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-300 hover:bg-slate-50 dark:hover:bg-slate-800 font-semibold text-xs flex items-center justify-center gap-1.5 transition-colors cursor-pointer"
          >
            <Clock className="w-3.5 h-3.5 text-slate-400" />
            Skip / Later
          </button>
        </div>
      </div>
    </div>
  );
};
