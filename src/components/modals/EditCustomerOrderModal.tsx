import React, { useState, useEffect } from 'react';
import { X, User, Phone, Wrench, FileQuestion, MapPin, Navigation } from 'lucide-react';
import { CustomerJob } from '../../types';
import { LocationHelper } from '../../utils/locationHelper';

interface EditCustomerOrderModalProps {
  job: CustomerJob | null;
  availableCategories: string[];
  onClose: () => void;
  onSave: (updated: CustomerJob) => void;
}

export const EditCustomerOrderModal: React.FC<EditCustomerOrderModalProps> = ({
  job,
  availableCategories,
  onClose,
  onSave,
}) => {
  const [name, setName] = useState('');
  const [phone, setPhone] = useState('');
  const [serviceType, setServiceType] = useState('');
  const [issueDescription, setIssueDescription] = useState('');
  const [address, setAddress] = useState('');
  const [rawLocation, setRawLocation] = useState('');
  const [latitude, setLatitude] = useState(28.5708);
  const [longitude, setLongitude] = useState(77.3261);

  useEffect(() => {
    if (job) {
      setName(job.customerName);
      setPhone(job.customerPhone);
      setServiceType(job.serviceType);
      setIssueDescription(job.issueDescription || '');
      setAddress(job.address || '');
      setLatitude(job.latitude);
      setLongitude(job.longitude);
      setRawLocation(`${job.latitude}, ${job.longitude}`);
    }
  }, [job]);

  if (!job) return null;

  const handleLocationChange = (val: string) => {
    setRawLocation(val);
    const parsed = LocationHelper.parseCoordinatesFromText(val);
    if (parsed) {
      setLatitude(parsed[0]);
      setLongitude(parsed[1]);
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!name.trim()) return;

    onSave({
      ...job,
      customerName: name.trim(),
      customerPhone: phone.trim(),
      serviceType: serviceType.trim() || 'General Repair',
      issueDescription: issueDescription.trim(),
      address: address.trim(),
      latitude,
      longitude,
    });
    onClose();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-lg bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 overflow-hidden z-10 max-h-[90vh] flex flex-col">
        <div className="p-4 sm:p-5 border-b border-slate-100 dark:border-slate-800 flex items-center justify-between">
          <h3 className="text-base font-bold text-slate-900 dark:text-white">
            Edit Order #{job.id.toString().slice(-4)} Details
          </h3>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        <form onSubmit={handleSubmit} className="p-4 sm:p-5 space-y-4 overflow-y-auto flex-1">
          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Customer Name
            </label>
            <div className="relative">
              <User className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
                className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Mobile Number
            </label>
            <div className="relative">
              <Phone className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
              <input
                type="tel"
                value={phone}
                onChange={(e) => setPhone(e.target.value.replace(/[^0-9]/g, '').slice(0, 10))}
                className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Service Category
            </label>
            <div className="relative">
              <Wrench className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
              <input
                type="text"
                value={serviceType}
                onChange={(e) => setServiceType(e.target.value)}
                className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Problem Description
            </label>
            <div className="relative">
              <FileQuestion className="absolute top-2.5 left-3 w-4 h-4 text-slate-400" />
              <textarea
                value={issueDescription}
                onChange={(e) => setIssueDescription(e.target.value)}
                rows={3}
                className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm resize-none"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Address
            </label>
            <div className="relative">
              <MapPin className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
              <input
                type="text"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Location Coordinates
            </label>
            <div className="relative">
              <Navigation className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
              <input
                type="text"
                value={rawLocation}
                onChange={(e) => handleLocationChange(e.target.value)}
                className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm"
              />
            </div>
          </div>

          <div className="flex items-center justify-end gap-2 pt-2">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 rounded-xl border border-slate-300 dark:border-slate-700 text-xs font-bold"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-5 py-2 rounded-xl bg-blue-600 hover:bg-blue-700 text-white font-bold text-xs shadow-xs"
            >
              Update Order
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
