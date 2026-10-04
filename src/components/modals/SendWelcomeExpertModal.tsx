import React, { useMemo } from 'react';
import { X, Send, Sparkles } from 'lucide-react';
import { Expert } from '../../types';
import { WhatsAppHelper } from '../../utils/whatsAppHelper';

interface SendWelcomeExpertModalProps {
  expert: Expert | null;
  onClose: () => void;
  onSend: (messageText: string) => void;
}

export const SendWelcomeExpertModal: React.FC<SendWelcomeExpertModalProps> = ({
  expert,
  onClose,
  onSend,
}) => {
  if (!expert) return null;

  const welcomeMessage = useMemo(() => {
    return WhatsAppHelper.createNewExpertWelcomeMessage(expert.name);
  }, [expert]);

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-lg bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 p-5 sm:p-6 z-10 max-h-[90vh] flex flex-col">
        <div className="flex items-center justify-between pb-3 border-b border-slate-100 dark:border-slate-800">
          <div className="flex items-center gap-2">
            <span className="p-2 rounded-xl bg-amber-100 dark:bg-amber-950 text-amber-600">
              <Sparkles className="w-5 h-5" />
            </span>
            <div>
              <h3 className="text-base font-bold text-slate-900 dark:text-white">
                Welcome New Hurifix Expert
              </h3>
              <p className="text-xs text-slate-500">
                Partner: <strong>{expert.name}</strong> (📞 +91 {expert.phone})
              </p>
            </div>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        <div className="my-4 p-3.5 rounded-xl bg-slate-50 dark:bg-slate-800/60 border border-slate-200 dark:border-slate-700 text-xs font-mono text-slate-800 dark:text-slate-200 whitespace-pre-line overflow-y-auto max-h-56">
          {welcomeMessage}
        </div>

        <div className="flex items-center justify-end gap-2 pt-2">
          <button
            type="button"
            onClick={onClose}
            className="px-4 py-2 rounded-xl border border-slate-300 dark:border-slate-700 text-xs font-bold"
          >
            Skip For Now
          </button>
          <button
            type="button"
            onClick={() => onSend(welcomeMessage)}
            className="px-5 py-2 rounded-xl bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-xs inline-flex items-center gap-1.5 shadow-xs cursor-pointer"
          >
            <Send className="w-4 h-4" />
            Send Welcome on WhatsApp
          </button>
        </div>
      </div>
    </div>
  );
};
