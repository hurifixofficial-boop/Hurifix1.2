import React, { useState, useEffect } from 'react';
import { X, User, Phone, Wrench, MapPin, Navigation, Crosshair } from 'lucide-react';
import { Expert } from '../../types';
import { LocationHelper } from '../../utils/locationHelper';

interface AddExpertModalProps {
  isOpen: boolean;
  onClose: () => void;
  initialExpert?: Expert | null;
  availableCategories: string[];
  onAddNewCategory: (name: string) => void;
  onSave: (expertData: Omit<Expert, 'id' | 'createdAt' | 'isDeleted'>) => void;
}

export const AddExpertModal: React.FC<AddExpertModalProps> = ({
  isOpen,
  onClose,
  initialExpert,
  availableCategories,
  onAddNewCategory,
  onSave,
}) => {
  const [name, setName] = useState('');
  const [phone, setPhone] = useState('');
  const [category, setCategory] = useState('Electrician');
  const [address, setAddress] = useState('');
  const [rawLocation, setRawLocation] = useState('28.5708, 77.3261');
  const [latitude, setLatitude] = useState(28.5708);
  const [longitude, setLongitude] = useState(77.3261);
  const [isAvailable, setIsAvailable] = useState(true);
  const [newCatInput, setNewCatInput] = useState('');
  const [isAddingNewCat, setIsAddingNewCat] = useState(false);

  useEffect(() => {
    if (initialExpert) {
      setName(initialExpert.name);
      setPhone(initialExpert.phone);
      setCategory(initialExpert.category);
      setAddress(initialExpert.address);
      setLatitude(initialExpert.latitude);
      setLongitude(initialExpert.longitude);
      setRawLocation(`${initialExpert.latitude}, ${initialExpert.longitude}`);
      setIsAvailable(initialExpert.isAvailable);
    } else {
      setName('');
      setPhone('');
      setCategory(availableCategories[0] || 'Electrician');
      setAddress('');
      setLatitude(28.5708);
      setLongitude(77.3261);
      setRawLocation('28.5708, 77.3261');
      setIsAvailable(true);
    }
  }, [initialExpert, availableCategories, isOpen]);

  const handleLocationChange = (val: string) => {
    setRawLocation(val);
    const parsed = LocationHelper.parseCoordinatesFromText(val);
    if (parsed) {
      setLatitude(parsed[0]);
      setLongitude(parsed[1]);
    }
  };

  const handleFetchGps = async () => {
    try {
      const loc = await LocationHelper.fetchCurrentGps();
      setLatitude(loc.latitude);
      setLongitude(loc.longitude);
      setRawLocation(`${loc.latitude.toFixed(4)}, ${loc.longitude.toFixed(4)}`);
    } catch (e: any) {
      alert(e.message || 'GPS failed');
    }
  };

  const handleCreateCategory = (e: React.FormEvent) => {
    e.preventDefault();
    if (!newCatInput.trim()) return;
    onAddNewCategory(newCatInput.trim());
    setCategory(newCatInput.trim());
    setNewCatInput('');
    setIsAddingNewCat(false);
  };

  const cleanPhone = phone.replace(/[^0-9]/g, '').slice(0, 10);
  const isPhoneValid = cleanPhone.length === 10;
  const canSave = name.trim().length > 0 && isPhoneValid && category.trim().length > 0;

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!canSave) return;

    onSave({
      name: name.trim(),
      phone: cleanPhone,
      category,
      address: address.trim(),
      latitude,
      longitude,
      isAvailable,
      rating: initialExpert ? initialExpert.rating : 4.8,
      ratingSum: initialExpert ? initialExpert.ratingSum : 4.8,
      totalRatingsCount: initialExpert ? initialExpert.totalRatingsCount : 1,
      completedJobsCount: initialExpert ? initialExpert.completedJobsCount : 0,
      cancelledJobsCount: initialExpert ? initialExpert.cancelledJobsCount : 0,
      isWelcomeMessageSent: initialExpert ? initialExpert.isWelcomeMessageSent : false,
    });
    onClose();
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-lg bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 overflow-hidden z-10 max-h-[90vh] flex flex-col">
        {/* Header */}
        <div className="p-4 sm:p-5 border-b border-slate-100 dark:border-slate-800 flex items-center justify-between">
          <div>
            <h3 className="text-lg font-bold text-slate-900 dark:text-white">
              {initialExpert ? 'Edit Partner Expert' : 'Register New Partner Expert'}
            </h3>
            <p className="text-xs text-slate-500 dark:text-slate-400">
              Provide technician details and service category
            </p>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Form Body */}
        <form onSubmit={handleSubmit} className="p-4 sm:p-5 space-y-4 overflow-y-auto flex-1">
          {/* Name */}
          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Expert Name <span className="text-rose-500">*</span>
            </label>
            <div className="relative">
              <User className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="e.g. Ramesh Chandra"
                required
                className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm focus:ring-2 focus:ring-blue-500/20"
              />
            </div>
          </div>

          {/* Phone */}
          <div>
            <div className="flex items-center justify-between mb-1">
              <label className="block text-xs font-bold text-slate-700 dark:text-slate-300">
                Mobile Number (10 Digits) <span className="text-rose-500">*</span>
              </label>
              <span className={`text-[11px] font-semibold ${phone && !isPhoneValid ? 'text-rose-500' : 'text-slate-400'}`}>
                {cleanPhone.length}/10 digits
              </span>
            </div>
            <div className="relative">
              <Phone className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
              <input
                type="tel"
                value={phone}
                onChange={(e) => setPhone(e.target.value.replace(/[^0-9]/g, '').slice(0, 10))}
                placeholder="10-digit mobile number"
                required
                className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm focus:ring-2 focus:ring-blue-500/20"
              />
            </div>
          </div>

          {/* Category */}
          <div>
            <div className="flex items-center justify-between mb-1">
              <label className="block text-xs font-bold text-slate-700 dark:text-slate-300">
                Service Category <span className="text-rose-500">*</span>
              </label>
              <button
                type="button"
                onClick={() => setIsAddingNewCat(!isAddingNewCat)}
                className="text-xs font-semibold text-blue-600 hover:underline"
              >
                {isAddingNewCat ? 'Select existing' : '+ Add new category'}
              </button>
            </div>

            {isAddingNewCat ? (
              <div className="flex items-center gap-2">
                <input
                  type="text"
                  value={newCatInput}
                  onChange={(e) => setNewCatInput(e.target.value)}
                  placeholder="New category name..."
                  className="flex-1 px-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm"
                />
                <button
                  type="button"
                  onClick={handleCreateCategory}
                  className="px-3 py-2 rounded-xl bg-blue-600 text-white font-bold text-xs"
                >
                  Add
                </button>
              </div>
            ) : (
              <div className="relative">
                <Wrench className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
                <select
                  value={category}
                  onChange={(e) => setCategory(e.target.value)}
                  className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm appearance-none"
                >
                  {availableCategories.map((cat) => (
                    <option key={cat} value={cat}>
                      {cat}
                    </option>
                  ))}
                </select>
              </div>
            )}
          </div>

          {/* Address */}
          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Base Address / Area
            </label>
            <div className="relative">
              <MapPin className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
              <input
                type="text"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                placeholder="e.g. Sector 18, Noida"
                className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm focus:ring-2 focus:ring-blue-500/20"
              />
            </div>
          </div>

          {/* Location Coordinates / Map Link */}
          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Coordinates or Google Maps Link
            </label>
            <div className="flex items-center gap-2">
              <div className="relative flex-1">
                <Navigation className="absolute inset-y-0 left-3 my-auto w-4 h-4 text-slate-400" />
                <input
                  type="text"
                  value={rawLocation}
                  onChange={(e) => handleLocationChange(e.target.value)}
                  placeholder="28.5708, 77.3261 or link"
                  className="w-full pl-9 pr-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm"
                />
              </div>
              <button
                type="button"
                onClick={handleFetchGps}
                title="Current GPS"
                className="p-2.5 rounded-xl border border-blue-200 dark:border-blue-800 bg-blue-50 text-blue-600"
              >
                <Crosshair className="w-4 h-4" />
              </button>
            </div>
          </div>

          {/* Availability Toggle */}
          <div className="flex items-center justify-between p-3 rounded-xl bg-slate-50 dark:bg-slate-800/60 border border-slate-200 dark:border-slate-700">
            <div>
              <span className="text-sm font-bold block">Currently Available</span>
              <span className="text-xs text-slate-500">Show in dispatch suggestions</span>
            </div>
            <button
              type="button"
              onClick={() => setIsAvailable(!isAvailable)}
              className={`relative inline-flex h-6 w-11 shrink-0 rounded-full border-2 border-transparent transition-colors ${
                isAvailable ? 'bg-emerald-600' : 'bg-slate-300 dark:bg-slate-700'
              }`}
            >
              <span
                className={`inline-block h-5 w-5 transform rounded-full bg-white transition ${
                  isAvailable ? 'translate-x-5' : 'translate-x-0'
                }`}
              />
            </button>
          </div>

          {/* Buttons */}
          <div className="pt-2 flex items-center justify-end gap-2">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 rounded-xl border border-slate-300 dark:border-slate-700 text-xs font-bold text-slate-700 dark:text-slate-300 hover:bg-slate-50"
            >
              Cancel
            </button>
            <button
              type="submit"
              disabled={!canSave}
              className={`px-5 py-2 rounded-xl text-xs font-bold text-white shadow-xs ${
                canSave ? 'bg-blue-600 hover:bg-blue-700' : 'bg-slate-300 cursor-not-allowed'
              }`}
            >
              {initialExpert ? 'Update Expert' : 'Save Expert'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
