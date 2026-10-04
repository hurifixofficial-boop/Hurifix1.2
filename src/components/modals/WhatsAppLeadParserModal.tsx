import React, { useState } from 'react';
import { X, ClipboardPaste, Sparkles } from 'lucide-react';

interface WhatsAppLeadParserModalProps {
  isOpen: boolean;
  onClose: () => void;
  onParseText: (text: string) => void;
}

export const WhatsAppLeadParserModal: React.FC<WhatsAppLeadParserModalProps> = ({
  isOpen,
  onClose,
  onParseText,
}) => {
  const [rawText, setRawText] = useState('');

  if (!isOpen) return null;

  const handlePasteClipboard = async () => {
    try {
      const text = await navigator.clipboard.readText();
      setRawText(text);
    } catch {
      // ignore
    }
  };

  const handleParse = (e: React.FormEvent) => {
    e.preventDefault();
    if (!rawText.trim()) return;
    onParseText(rawText);
    onClose();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-lg bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 overflow-hidden z-10">
        <div className="p-4 sm:p-5 border-b border-slate-100 dark:border-slate-800 flex items-center justify-between">
          <div className="flex items-center gap-2">
            <span className="p-2 rounded-xl bg-emerald-50 dark:bg-emerald-950 text-emerald-600 dark:text-emerald-400">
              <ClipboardPaste className="w-5 h-5" />
            </span>
            <div>
              <h3 className="text-base font-bold text-slate-900 dark:text-white">
                WhatsApp Lead Quick Parser
              </h3>
              <p className="text-xs text-slate-500 dark:text-slate-400">
                Paste raw WhatsApp inquiry text to auto-fill the order form
              </p>
            </div>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        <form onSubmit={handleParse} className="p-4 sm:p-5 space-y-4">
          <div>
            <div className="flex items-center justify-between mb-1.5">
              <label className="block text-xs font-bold text-slate-700 dark:text-slate-300">
                Paste Customer WhatsApp Message:
              </label>
              <button
                type="button"
                onClick={handlePasteClipboard}
                className="text-xs font-semibold text-emerald-600 dark:text-emerald-400 hover:underline inline-flex items-center gap-1 cursor-pointer"
              >
                <ClipboardPaste className="w-3.5 h-3.5" />
                Paste from Clipboard
              </button>
            </div>
            <textarea
              value={rawText}
              onChange={(e) => setRawText(e.target.value)}
              rows={6}
              placeholder={`Example:\nCustomer: Rahul Sharma\nPhone: 9812345678\nService: AC repair cooling issue\nAddress: Sector 18, Noida`}
              className="w-full p-3 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-slate-900 dark:text-white text-sm focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-600 resize-none font-mono text-xs sm:text-sm"
              required
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
              disabled={!rawText.trim()}
              className="px-5 py-2 rounded-xl bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-xs inline-flex items-center gap-1.5 shadow-xs disabled:opacity-50 cursor-pointer"
            >
              <Sparkles className="w-4 h-4" />
              Parse & Fill Order
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
