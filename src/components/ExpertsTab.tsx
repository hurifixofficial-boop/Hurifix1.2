import React, { useState, useMemo } from 'react';
import {
  Search,
  X,
  Plus,
  Phone,
  MessageCircle,
  MapPin,
  Star,
  Edit2,
  Trash2,
  History,
  Send,
  Navigation,
  CheckCircle,
  AlertCircle,
  ArrowUp,
} from 'lucide-react';
import { useDispatchContext } from '../context/DispatchContext';
import { Expert, ExpertCategory } from '../types';
import { WhatsAppHelper } from '../utils/whatsAppHelper';

interface ExpertsTabProps {
  onAddExpert: () => void;
  onEditExpert: (expert: Expert) => void;
  onDeleteExpert: (expert: Expert) => void;
  onViewWorkHistory: (expert: Expert) => void;
  onSendWelcome: (expert: Expert) => void;
  onAddNewCategory: () => void;
  onDeleteCategory: (category: ExpertCategory) => void;
}

export const ExpertsTab: React.FC<ExpertsTabProps> = ({
  onAddExpert,
  onEditExpert,
  onDeleteExpert,
  onViewWorkHistory,
  onSendWelcome,
  onAddNewCategory,
  onDeleteCategory,
}) => {
  const { allExperts, allCategories, updateExpert } = useDispatchContext();
  const [selectedCategoryTab, setSelectedCategoryTab] = useState<string>('All');
  const [searchQuery, setSearchQuery] = useState('');

  // Combined category tabs ensuring Electrician and Plumber always exist
  const displayCategories = useMemo(() => {
    const map = new Map<string, ExpertCategory>();
    map.set('Electrician', { id: 1, name: 'Electrician', isDefault: true, createdAt: 0 });
    map.set('Plumber', { id: 2, name: 'Plumber', isDefault: true, createdAt: 0 });
    allCategories.forEach((cat) => {
      map.set(cat.name, cat);
    });
    return Array.from(map.values());
  }, [allCategories]);

  const categoryTabs = useMemo(() => {
    return ['All', ...displayCategories.map((c) => c.name)];
  }, [displayCategories]);

  const filteredExperts = useMemo(() => {
    const query = searchQuery.trim().toLowerCase();
    return allExperts.filter((exp) => {
      const matchesCategory =
        selectedCategoryTab === 'All' ||
        exp.category.toLowerCase() === selectedCategoryTab.toLowerCase();
      if (!matchesCategory) return false;
      if (!query) return true;
      return (
        exp.name.toLowerCase().includes(query) ||
        exp.phone.includes(query) ||
        exp.address.toLowerCase().includes(query) ||
        exp.category.toLowerCase().includes(query)
      );
    });
  }, [allExperts, selectedCategoryTab, searchQuery]);

  const availableCount = allExperts.filter((e) => e.isAvailable).length;

  return (
    <div className="max-w-4xl mx-auto p-4 sm:p-6 pb-28">
      {/* Header Info */}
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-3 pb-3">
        <div>
          <h2 className="text-xl font-extrabold text-slate-900 dark:text-white">
            Partner Experts ({allExperts.length})
          </h2>
          <p className="text-xs text-slate-500 dark:text-slate-400 mt-0.5">
            <span className="font-bold text-emerald-600 dark:text-emerald-400">
              {availableCount} available
            </span>{' '}
            for instant customer dispatch
          </p>
        </div>

        <button
          onClick={onAddNewCategory}
          className="inline-flex items-center gap-1.5 px-3.5 py-2 rounded-xl text-xs font-bold border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-slate-800 dark:text-slate-200 hover:bg-slate-50 dark:hover:bg-slate-750 shadow-2xs self-start sm:self-auto cursor-pointer"
        >
          <Plus className="w-4 h-4 text-blue-600" />
          Add Category
        </button>
      </div>

      {/* Category Tabs */}
      <div className="flex items-center gap-2 overflow-x-auto pb-2 scrollbar-none">
        {categoryTabs.map((catName) => {
          const isSelected = selectedCategoryTab === catName;
          const count =
            catName === 'All'
              ? allExperts.length
              : allExperts.filter((e) => e.category.toLowerCase() === catName.toLowerCase()).length;
          const catEntity = displayCategories.find(
            (c) => c.name.toLowerCase() === catName.toLowerCase()
          );
          const isCustom =
            catEntity &&
            !catEntity.isDefault &&
            catEntity.name !== 'Electrician' &&
            catEntity.name !== 'Plumber';

          return (
            <div key={catName} className="relative group shrink-0">
              <button
                onClick={() => setSelectedCategoryTab(catName)}
                className={`flex items-center gap-1.5 px-3.5 py-2 rounded-xl text-xs sm:text-sm font-bold whitespace-nowrap transition-all border cursor-pointer ${
                  isSelected
                    ? 'bg-blue-600 text-white border-blue-600 shadow-xs'
                    : 'bg-white dark:bg-slate-800 text-slate-600 dark:text-slate-400 border-slate-200 dark:border-slate-700 hover:bg-slate-50'
                }`}
              >
                <span>{catName}</span>
                <span
                  className={`text-[11px] px-1.5 py-0.2 rounded-full ${
                    isSelected
                      ? 'bg-white/20 text-white'
                      : 'bg-slate-100 dark:bg-slate-700 text-slate-500'
                  }`}
                >
                  {count}
                </span>

                {isCustom && (
                  <button
                    onClick={(e) => {
                      e.stopPropagation();
                      onDeleteCategory(catEntity);
                    }}
                    title="Delete custom category"
                    className="ml-1 p-0.5 rounded-full hover:bg-rose-500 hover:text-white text-rose-400"
                  >
                    <X className="w-3 h-3" />
                  </button>
                )}
              </button>
            </div>
          );
        })}
      </div>

      {/* Search Input */}
      <div className="mt-3 relative">
        <div className="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
          <Search className="w-4 h-4" />
        </div>
        <input
          type="text"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          placeholder="Search by expert name, phone, area, or category..."
          className="w-full pl-10 pr-9 py-2.5 rounded-xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-900 text-slate-900 dark:text-white text-sm focus:outline-hidden focus:ring-2 focus:ring-blue-500/20 focus:border-blue-600 transition-all placeholder:text-slate-400"
        />
        {searchQuery && (
          <button
            onClick={() => setSearchQuery('')}
            className="absolute inset-y-0 right-0 pr-3 flex items-center text-slate-400 hover:text-slate-600"
          >
            <X className="w-4 h-4" />
          </button>
        )}
      </div>

      {/* Experts Grid / List */}
      <div className="mt-4 space-y-3.5">
        {filteredExperts.length === 0 ? (
          <div className="bg-white dark:bg-slate-900 rounded-2xl border border-slate-200 dark:border-slate-800 p-12 text-center">
            <span className="text-3xl block mb-2">🛠</span>
            <h3 className="font-bold text-slate-800 dark:text-slate-200 text-base">
              No partner experts found in "{selectedCategoryTab}"
            </h3>
            <p className="text-xs text-slate-500 dark:text-slate-400 mt-1 max-w-sm mx-auto">
              Add new technicians to this category to start assigning orders to them.
            </p>
            <button
              onClick={onAddExpert}
              className="mt-4 inline-flex items-center gap-1.5 px-4 py-2 rounded-xl text-xs font-bold bg-blue-600 text-white hover:bg-blue-700 transition-colors shadow-xs"
            >
              <Plus className="w-4 h-4" />
              Add First Expert
            </button>
          </div>
        ) : (
          filteredExperts.map((expert) => {
            return (
              <div
                key={expert.id}
                className="bg-white dark:bg-slate-900 rounded-2xl border border-slate-200 dark:border-slate-800 p-4 sm:p-5 shadow-xs hover:shadow-md transition-shadow relative"
              >
                {/* Header */}
                <div className="flex items-start justify-between gap-3">
                  <div>
                    <div className="flex items-center gap-2">
                      <h3 className="font-bold text-base text-slate-900 dark:text-white">
                        {expert.name}
                      </h3>
                      <span className="text-[11px] font-bold px-2.5 py-0.5 rounded-full bg-blue-50 dark:bg-blue-950 text-blue-700 dark:text-blue-300 border border-blue-200 dark:border-blue-800">
                        {expert.category}
                      </span>
                    </div>

                    <div className="flex flex-wrap items-center gap-2 text-xs text-slate-500 dark:text-slate-400 mt-1">
                      <span className="font-semibold text-slate-700 dark:text-slate-300">
                        📞 +91 {expert.phone}
                      </span>
                      <span>•</span>
                      {/* Rating */}
                      <span className="inline-flex items-center gap-1 font-bold text-amber-600 dark:text-amber-400">
                        <Star className="w-3.5 h-3.5 fill-amber-400 text-amber-400" />
                        {expert.rating.toFixed(1)}
                        <span className="text-[11px] font-normal text-slate-400">
                          ({expert.totalRatingsCount})
                        </span>
                      </span>
                    </div>
                  </div>

                  {/* Availability Toggle */}
                  <div className="flex items-center gap-2">
                    <span
                      className={`text-xs font-bold ${
                        expert.isAvailable
                          ? 'text-emerald-600 dark:text-emerald-400'
                          : 'text-slate-400'
                      }`}
                    >
                      {expert.isAvailable ? 'Available' : 'Busy / Off'}
                    </span>
                    <button
                      type="button"
                      onClick={() =>
                        updateExpert({ ...expert, isAvailable: !expert.isAvailable })
                      }
                      className={`relative inline-flex h-6 w-11 shrink-0 cursor-pointer rounded-full border-2 border-transparent transition-colors duration-200 ease-in-out focus:outline-hidden ${
                        expert.isAvailable ? 'bg-emerald-600' : 'bg-slate-300 dark:bg-slate-700'
                      }`}
                    >
                      <span
                        className={`pointer-events-none inline-block h-5 w-5 transform rounded-full bg-white shadow-sm ring-0 transition duration-200 ease-in-out ${
                          expert.isAvailable ? 'translate-x-5' : 'translate-x-0'
                        }`}
                      />
                    </button>
                  </div>
                </div>

                {/* Details */}
                <div className="mt-3 text-xs sm:text-sm space-y-1 text-slate-600 dark:text-slate-300">
                  <div className="flex items-start gap-1.5">
                    <MapPin className="w-4 h-4 text-slate-400 shrink-0 mt-0.5" />
                    <span>{expert.address || 'Address not registered'}</span>
                  </div>

                  <div className="flex items-center gap-4 text-xs pt-1">
                    <span className="font-semibold text-emerald-700 dark:text-emerald-400">
                      ✅ {expert.completedJobsCount} completed jobs
                    </span>
                    {expert.cancelledJobsCount > 0 && (
                      <span className="font-semibold text-rose-600 dark:text-rose-400">
                        ❌ {expert.cancelledJobsCount} cancelled
                      </span>
                    )}
                  </div>
                </div>

                {/* Actions Row */}
                <div className="mt-4 pt-3 border-t border-slate-100 dark:border-slate-800 flex flex-wrap items-center justify-between gap-2">
                  <div className="flex items-center gap-1.5">
                    {/* Call Button */}
                    <button
                      onClick={() => WhatsAppHelper.openDialer(expert.phone)}
                      className="px-3 py-1.5 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800 text-xs font-semibold flex items-center gap-1.5 cursor-pointer"
                    >
                      <Phone className="w-3.5 h-3.5 text-blue-600" />
                      Call
                    </button>

                    {/* WhatsApp Chat (WITHOUT message as explicitly requested) */}
                    <button
                      onClick={() => WhatsAppHelper.openWhatsAppChatWithoutMessage(expert.phone)}
                      className="px-3 py-1.5 rounded-xl border border-emerald-300 dark:border-emerald-800 bg-emerald-50/60 dark:bg-emerald-950/40 text-emerald-700 dark:text-emerald-300 hover:bg-emerald-100 text-xs font-semibold flex items-center gap-1.5 cursor-pointer"
                    >
                      <MessageCircle className="w-3.5 h-3.5 text-emerald-600" />
                      WhatsApp
                    </button>

                    {/* View on Map */}
                    <a
                      href={WhatsAppHelper.formatPhoneNumberForWhatsApp(expert.phone)}
                      onClick={(e) => {
                        e.preventDefault();
                        window.open(
                          `https://www.google.com/maps/search/?api=1&query=${expert.latitude},${expert.longitude}`,
                          '_blank'
                        );
                      }}
                      className="p-1.5 rounded-xl border border-slate-200 dark:border-slate-700 text-slate-600 dark:text-slate-400 hover:bg-slate-100 dark:hover:bg-slate-800"
                      title="View on Google Maps"
                    >
                      <Navigation className="w-4 h-4 text-sky-600" />
                    </a>
                  </div>

                  <div className="flex items-center gap-1.5">
                    {/* Send Welcome Message */}
                    <button
                      onClick={() => onSendWelcome(expert)}
                      className="p-1.5 rounded-xl text-slate-500 hover:text-emerald-600 hover:bg-emerald-50 dark:hover:bg-emerald-950/40"
                      title={expert.isWelcomeMessageSent ? 'Welcome sent (click to send again)' : 'Send Welcome Message'}
                    >
                      <Send className="w-4 h-4" />
                    </button>

                    {/* Work History */}
                    <button
                      onClick={() => onViewWorkHistory(expert)}
                      className="p-1.5 rounded-xl text-slate-500 hover:text-blue-600 hover:bg-blue-50 dark:hover:bg-blue-950/40"
                      title="View Work History"
                    >
                      <History className="w-4 h-4" />
                    </button>

                    {/* Edit Expert */}
                    <button
                      onClick={() => onEditExpert(expert)}
                      className="p-1.5 rounded-xl text-slate-500 hover:text-slate-800 dark:hover:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800"
                      title="Edit Expert"
                    >
                      <Edit2 className="w-4 h-4" />
                    </button>

                    {/* Delete Expert */}
                    <button
                      onClick={() => onDeleteExpert(expert)}
                      className="p-1.5 rounded-xl text-slate-400 hover:text-rose-600 hover:bg-rose-50 dark:hover:bg-rose-950/40"
                      title="Move to Recycle Bin"
                    >
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </div>
                </div>
              </div>
            );
          })
        )}
      </div>

      {/* Floating Add Expert Button */}
      <button
        onClick={onAddExpert}
        className="fixed bottom-6 right-6 z-30 inline-flex items-center gap-2 px-5 py-3.5 rounded-full bg-blue-600 hover:bg-blue-700 text-white font-bold text-sm shadow-xl hover:shadow-2xl transition-all cursor-pointer active:scale-95"
      >
        <Plus className="w-5 h-5" />
        Add Expert
      </button>
    </div>
  );
};
