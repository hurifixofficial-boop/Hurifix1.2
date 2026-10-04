import React, { useMemo } from 'react';
import {
  ClipboardPaste,
  Crosshair,
  User,
  Phone,
  Wrench,
  FileQuestion,
  MapPin,
  Navigation,
  CheckCircle2,
} from 'lucide-react';
import { useDispatchContext } from '../context/DispatchContext';
import { CustomerJob } from '../types';

interface DispatchOrderFormProps {
  onOpenWhatsAppParser: () => void;
  onOrderSaved: (job: CustomerJob) => void;
}

export const DispatchOrderForm: React.FC<DispatchOrderFormProps> = ({
  onOpenWhatsAppParser,
  onOrderSaved,
}) => {
  const {
    customerForm,
    allCategories,
    updateFormName,
    updateFormPhone,
    updateFormServiceType,
    updateFormIssue,
    updateFormAddress,
    updateFormLocationInput,
    fetchCurrentGps,
    saveCustomerOrder,
  } = useDispatchContext();

  const cleanPhone = customerForm.phone.replace(/[^0-9]/g, '').slice(0, 10);
  const isPhoneValid = cleanPhone.length === 10;

  // Matching Category Chips shown directly below as user types
  const matchingCategories = useMemo(() => {
    if (!customerForm.serviceType.trim()) return [];
    const set = new Set(['Electrician', 'Plumber', ...allCategories.map((c) => c.name)]);
    const query = customerForm.serviceType.trim().toLowerCase();
    return Array.from(set)
      .filter((cat) => cat.toLowerCase().includes(query) && cat.toLowerCase() !== query)
      .slice(0, 5);
  }, [customerForm.serviceType, allCategories]);

  const canSave =
    customerForm.name.trim().length > 0 &&
    isPhoneValid &&
    customerForm.serviceType.trim().length > 0 &&
    customerForm.hasValidLocation;

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!canSave) return;
    const savedJob = saveCustomerOrder('PENDING');
    onOrderSaved(savedJob);
  };

  return (
    <div className="max-w-2xl mx-auto p-4 sm:p-6 pb-24">
      <div className="bg-white dark:bg-slate-900 rounded-2xl shadow-sm border border-slate-200 dark:border-slate-800 p-5 sm:p-6">
        {/* Top Header */}
        <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-3 pb-4 border-b border-slate-100 dark:border-slate-800">
          <div>
            <h2 className="text-lg font-bold text-slate-900 dark:text-white flex items-center gap-2">
              <span className="text-xl">📝</span>
              New Customer Dispatch Order
            </h2>
            <p className="text-xs text-slate-500 dark:text-slate-400 mt-0.5">
              Fill in customer service request details and assign an expert
            </p>
          </div>

          <button
            type="button"
            onClick={onOpenWhatsAppParser}
            className="inline-flex items-center gap-2 px-3.5 py-2 rounded-xl text-xs font-bold bg-emerald-50 dark:bg-emerald-950/60 text-emerald-700 dark:text-emerald-300 border border-emerald-200 dark:border-emerald-800/80 hover:bg-emerald-100 dark:hover:bg-emerald-900/60 transition-colors shadow-2xs self-start sm:self-auto cursor-pointer"
          >
            <ClipboardPaste className="w-4 h-4 text-emerald-600 dark:text-emerald-400" />
            Paste WhatsApp Lead
          </button>
        </div>

        {/* Form Body */}
        <form onSubmit={handleSubmit} className="mt-5 space-y-4">
          {/* Customer Name */}
          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Customer Name <span className="text-rose-500">*</span>
            </label>
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
                <User className="w-4 h-4" />
              </div>
              <input
                type="text"
                value={customerForm.name}
                onChange={(e) => updateFormName(e.target.value)}
                placeholder="e.g. Rahul Sharma"
                required
                className="w-full pl-10 pr-3.5 py-2.5 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-slate-900 dark:text-white text-sm focus:outline-hidden focus:ring-2 focus:ring-blue-500/20 focus:border-blue-600 transition-all placeholder:text-slate-400"
              />
            </div>
          </div>

          {/* Customer Phone (Strict 10 Digits) */}
          <div>
            <div className="flex items-center justify-between mb-1">
              <label className="block text-xs font-bold text-slate-700 dark:text-slate-300">
                Customer Mobile Number (10 Digits) <span className="text-rose-500">*</span>
              </label>
              <span
                className={`text-[11px] font-semibold ${
                  customerForm.phone && !isPhoneValid
                    ? 'text-rose-500'
                    : 'text-slate-400 dark:text-slate-500'
                }`}
              >
                {cleanPhone.length}/10 digits
              </span>
            </div>
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
                <Phone className="w-4 h-4" />
              </div>
              <input
                type="tel"
                value={customerForm.phone}
                onChange={(e) => updateFormPhone(e.target.value)}
                placeholder="10-digit mobile number"
                maxLength={10}
                required
                className={`w-full pl-10 pr-3.5 py-2.5 rounded-xl border bg-white dark:bg-slate-800 text-slate-900 dark:text-white text-sm focus:outline-hidden focus:ring-2 transition-all placeholder:text-slate-400 ${
                  customerForm.phone && !isPhoneValid
                    ? 'border-rose-400 focus:ring-rose-500/20 focus:border-rose-500'
                    : 'border-slate-300 dark:border-slate-700 focus:ring-blue-500/20 focus:border-blue-600'
                }`}
              />
            </div>
          </div>

          {/* Service Type with suggestions */}
          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Service Type / Work Category <span className="text-rose-500">*</span>
            </label>
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
                <Wrench className="w-4 h-4" />
              </div>
              <input
                type="text"
                value={customerForm.serviceType}
                onChange={(e) => updateFormServiceType(e.target.value)}
                placeholder="e.g. Electrician, Plumber, AC Repair..."
                required
                className="w-full pl-10 pr-3.5 py-2.5 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-slate-900 dark:text-white text-sm focus:outline-hidden focus:ring-2 focus:ring-blue-500/20 focus:border-blue-600 transition-all placeholder:text-slate-400"
              />
            </div>

            {/* Suggestions Chips */}
            {matchingCategories.length > 0 && (
              <div className="flex flex-wrap items-center gap-1.5 mt-2">
                <span className="text-[11px] font-semibold text-slate-400">Suggestions:</span>
                {matchingCategories.map((catName) => (
                  <button
                    key={catName}
                    type="button"
                    onClick={() => updateFormServiceType(catName)}
                    className="inline-flex items-center gap-1 px-2.5 py-1 rounded-full text-xs font-semibold bg-blue-50 dark:bg-blue-950/60 text-blue-700 dark:text-blue-300 border border-blue-200 dark:border-blue-800 hover:bg-blue-100 transition-colors cursor-pointer"
                  >
                    💡 {catName}
                  </button>
                ))}
              </div>
            )}
          </div>

          {/* Problem Description (Optional) */}
          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Problem Description <span className="text-slate-400 font-normal">(Optional)</span>
            </label>
            <div className="relative">
              <div className="absolute top-3 left-3 flex items-center pointer-events-none text-slate-400">
                <FileQuestion className="w-4 h-4" />
              </div>
              <textarea
                value={customerForm.issueDescription}
                onChange={(e) => updateFormIssue(e.target.value)}
                rows={3}
                placeholder="e.g. Main switchboard tripping or tap leaking in kitchen"
                className="w-full pl-10 pr-3.5 py-2.5 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-slate-900 dark:text-white text-sm focus:outline-hidden focus:ring-2 focus:ring-blue-500/20 focus:border-blue-600 transition-all placeholder:text-slate-400 resize-none"
              />
            </div>
          </div>

          {/* Customer Address (Optional as explicitly requested) */}
          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Customer Address <span className="text-slate-400 font-normal">(Optional)</span>
            </label>
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
                <MapPin className="w-4 h-4" />
              </div>
              <input
                type="text"
                value={customerForm.address}
                onChange={(e) => updateFormAddress(e.target.value)}
                placeholder="e.g. Flat 304, Green Heights, Sector 18 (Optional)"
                className="w-full pl-10 pr-3.5 py-2.5 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-slate-900 dark:text-white text-sm focus:outline-hidden focus:ring-2 focus:ring-blue-500/20 focus:border-blue-600 transition-all placeholder:text-slate-400"
              />
            </div>
          </div>

          {/* Coordinates or Google Maps Link */}
          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Coordinates or Google Maps Link <span className="text-rose-500">*</span>
            </label>
            <div className="flex items-center gap-2">
              <div className="relative flex-1">
                <div className="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
                  <Navigation className="w-4 h-4" />
                </div>
                <input
                  type="text"
                  value={customerForm.rawLocationInput}
                  onChange={(e) => updateFormLocationInput(e.target.value)}
                  placeholder="Paste Google Maps link or Lat, Lng"
                  required
                  className={`w-full pl-10 pr-3.5 py-2.5 rounded-xl border bg-white dark:bg-slate-800 text-slate-900 dark:text-white text-sm focus:outline-hidden focus:ring-2 transition-all placeholder:text-slate-400 ${
                    !customerForm.hasValidLocation
                      ? 'border-rose-400 focus:ring-rose-500/20 focus:border-rose-500'
                      : 'border-slate-300 dark:border-slate-700 focus:ring-blue-500/20 focus:border-blue-600'
                  }`}
                />
              </div>

              {/* GPS Fetch Button */}
              <button
                type="button"
                onClick={fetchCurrentGps}
                title="Use Current Device GPS"
                className="p-3 rounded-xl bg-blue-50 dark:bg-blue-950/60 text-blue-600 dark:text-blue-400 border border-blue-200 dark:border-blue-800 hover:bg-blue-100 dark:hover:bg-blue-900/60 transition-colors shrink-0 shadow-2xs cursor-pointer"
              >
                <Crosshair className="w-5 h-5" />
              </button>
            </div>
            <p className="mt-1 text-[11px] text-slate-500 dark:text-slate-400">
              Format: <code>28.5708, 77.3261</code> or paste any Google Maps share link
            </p>
          </div>

          {/* Submit Button */}
          <div className="pt-3">
            <button
              type="submit"
              disabled={!canSave}
              className={`w-full py-3.5 px-4 rounded-xl text-base font-bold text-white transition-all shadow-md flex items-center justify-center gap-2 ${
                canSave
                  ? 'bg-blue-600 hover:bg-blue-700 active:scale-[0.99] cursor-pointer'
                  : 'bg-slate-300 dark:bg-slate-800 text-slate-500 dark:text-slate-600 cursor-not-allowed shadow-none'
              }`}
            >
              <CheckCircle2 className="w-5 h-5" />
              Save Customer Order
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
