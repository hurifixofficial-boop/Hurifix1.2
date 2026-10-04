import React, { useState, useMemo } from 'react';
import { X, Navigation, Phone, Star, CheckCircle, Clock, MapPin } from 'lucide-react';
import { CustomerJob, RankedExpert } from '../../types';
import { LocationHelper } from '../../utils/locationHelper';
import { WhatsAppHelper } from '../../utils/whatsAppHelper';

interface NearestExpertsModalProps {
  job: CustomerJob | null;
  rankedExperts: RankedExpert[];
  onClose: () => void;
  onAssign: (ranked: RankedExpert) => void;
}

export const NearestExpertsModal: React.FC<NearestExpertsModalProps> = ({
  job,
  rankedExperts,
  onClose,
  onAssign,
}) => {
  const [filterCategory, setFilterCategory] = useState<string>('MATCH');

  const filtered = useMemo(() => {
    if (!job) return rankedExperts;
    if (filterCategory === 'MATCH') {
      const jobService = job.serviceType.toLowerCase();
      const matched = rankedExperts.filter(
        (r) =>
          r.expert.category.toLowerCase().includes(jobService) ||
          jobService.includes(r.expert.category.toLowerCase()) ||
          r.expert.category.toLowerCase().includes('all-rounder')
      );
      return matched.length > 0 ? matched : rankedExperts;
    }
    return rankedExperts;
  }, [job, rankedExperts, filterCategory]);

  if (!job) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-xl bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 overflow-hidden z-10 max-h-[90vh] flex flex-col">
        {/* Header */}
        <div className="p-4 sm:p-5 border-b border-slate-100 dark:border-slate-800 flex items-start justify-between">
          <div>
            <div className="flex items-center gap-2">
              <span className="text-xl">📍</span>
              <h3 className="text-base sm:text-lg font-bold text-slate-900 dark:text-white">
                Nearest Experts for #{job.id.toString().slice(-4)}
              </h3>
            </div>
            <p className="text-xs text-slate-500 dark:text-slate-400 mt-0.5">
              Customer: <span className="font-semibold text-slate-800 dark:text-slate-200">{job.customerName}</span> • Service: <span className="font-semibold text-blue-600 dark:text-blue-400">{job.serviceType}</span>
            </p>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Filter Toggle */}
        <div className="px-4 sm:px-5 py-2.5 bg-slate-50 dark:bg-slate-800/50 border-b border-slate-100 dark:border-slate-800 flex items-center justify-between text-xs">
          <span className="text-slate-500 font-medium">Filter by category:</span>
          <div className="flex items-center gap-1.5">
            <button
              onClick={() => setFilterCategory('MATCH')}
              className={`px-2.5 py-1 rounded-lg font-semibold transition-colors cursor-pointer ${
                filterCategory === 'MATCH'
                  ? 'bg-blue-600 text-white'
                  : 'bg-white dark:bg-slate-700 text-slate-600 dark:text-slate-300'
              }`}
            >
              Matching Service
            </button>
            <button
              onClick={() => setFilterCategory('ALL')}
              className={`px-2.5 py-1 rounded-lg font-semibold transition-colors cursor-pointer ${
                filterCategory === 'ALL'
                  ? 'bg-blue-600 text-white'
                  : 'bg-white dark:bg-slate-700 text-slate-600 dark:text-slate-300'
              }`}
            >
              All Experts ({rankedExperts.length})
            </button>
          </div>
        </div>

        {/* List */}
        <div className="p-4 sm:p-5 overflow-y-auto space-y-3 flex-1">
          {filtered.length === 0 ? (
            <div className="p-8 text-center text-slate-400">
              No available experts found nearby.
            </div>
          ) : (
            filtered.map((item) => {
              const { expert, distanceKm, travelTimeMinutes } = item;
              const formattedDist = LocationHelper.formatDistance(distanceKm);

              return (
                <div
                  key={expert.id}
                  className="p-3.5 rounded-xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-850 hover:border-blue-400 dark:hover:border-blue-700 transition-all flex flex-col sm:flex-row sm:items-center justify-between gap-3 shadow-2xs"
                >
                  <div className="space-y-1">
                    <div className="flex items-center gap-2">
                      <h4 className="font-bold text-sm text-slate-900 dark:text-white">
                        {expert.name}
                      </h4>
                      <span className="text-[10px] font-bold px-2 py-0.2 rounded-full bg-blue-50 dark:bg-blue-950 text-blue-700 dark:text-blue-300 border border-blue-200 dark:border-blue-800">
                        {expert.category}
                      </span>
                    </div>

                    <div className="flex flex-wrap items-center gap-2 text-xs text-slate-500">
                      <span className="font-semibold text-slate-700 dark:text-slate-300">
                        📞 +91 {expert.phone}
                      </span>
                      <span>•</span>
                      <span className="inline-flex items-center gap-0.5 text-amber-500 font-bold">
                        <Star className="w-3.5 h-3.5 fill-amber-400" />
                        {expert.rating.toFixed(1)}
                      </span>
                    </div>

                    {/* Distance & Travel time pill */}
                    <div className="flex items-center gap-2 pt-1">
                      <span className="inline-flex items-center gap-1 text-[11px] font-bold text-blue-700 dark:text-blue-400 bg-blue-50 dark:bg-blue-950/60 px-2 py-0.5 rounded-md border border-blue-200 dark:border-blue-800">
                        <Navigation className="w-3 h-3" />
                        {formattedDist} away
                      </span>
                      <span className="inline-flex items-center gap-1 text-[11px] font-semibold text-slate-600 dark:text-slate-400">
                        <Clock className="w-3 h-3 text-slate-400" />
                        ~{travelTimeMinutes} mins travel
                      </span>
                    </div>
                  </div>

                  <div className="flex items-center gap-2 self-end sm:self-center shrink-0">
                    <button
                      onClick={() => WhatsAppHelper.openDialer(expert.phone)}
                      className="p-2 rounded-xl border border-slate-200 dark:border-slate-700 text-slate-600 dark:text-slate-300 hover:bg-slate-50"
                      title="Call Expert"
                    >
                      <Phone className="w-4 h-4 text-blue-600" />
                    </button>

                    <button
                      onClick={() => onAssign(item)}
                      className="py-2 px-3.5 rounded-xl bg-blue-600 hover:bg-blue-700 text-white font-bold text-xs flex items-center gap-1.5 shadow-2xs transition-colors cursor-pointer"
                    >
                      <CheckCircle className="w-3.5 h-3.5" />
                      Assign Expert
                    </button>
                  </div>
                </div>
              );
            })
          )}
        </div>
      </div>
    </div>
  );
};
