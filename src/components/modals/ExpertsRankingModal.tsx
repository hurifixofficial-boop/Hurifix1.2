import React, { useMemo } from 'react';
import { X, Trophy, Star, CheckCircle, Award } from 'lucide-react';
import { Expert } from '../../types';

interface ExpertsRankingModalProps {
  isOpen: boolean;
  onClose: () => void;
  experts: Expert[];
}

export const ExpertsRankingModal: React.FC<ExpertsRankingModalProps> = ({
  isOpen,
  onClose,
  experts,
}) => {
  const ranked = useMemo(() => {
    return [...experts].sort((a, b) => {
      if (b.rating !== a.rating) {
        return b.rating - a.rating;
      }
      return b.completedJobsCount - a.completedJobsCount;
    });
  }, [experts]);

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs" onClick={onClose} />
      <div className="relative w-full max-w-lg bg-white dark:bg-slate-900 rounded-2xl shadow-2xl border border-slate-200 dark:border-slate-800 overflow-hidden z-10 max-h-[85vh] flex flex-col">
        {/* Header */}
        <div className="p-4 sm:p-5 border-b border-slate-100 dark:border-slate-800 flex items-start justify-between bg-amber-50/50 dark:bg-amber-950/20">
          <div className="flex items-center gap-3">
            <div className="w-11 h-11 rounded-full bg-amber-100 dark:bg-amber-950 text-amber-600 dark:text-amber-400 flex items-center justify-center shrink-0">
              <Trophy className="w-6 h-6" />
            </div>
            <div>
              <h3 className="text-base sm:text-lg font-bold text-slate-900 dark:text-white">
                Experts Leaderboard & Ranking
              </h3>
              <p className="text-xs text-slate-500 dark:text-slate-400">
                Ranked by customer ratings and completed task volume
              </p>
            </div>
          </div>
          <button onClick={onClose} className="p-1 rounded-lg text-slate-400 hover:text-slate-600">
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* List */}
        <div className="p-4 sm:p-5 overflow-y-auto space-y-2.5 flex-1">
          {ranked.length === 0 ? (
            <div className="p-8 text-center text-slate-400">No experts found.</div>
          ) : (
            ranked.map((expert, idx) => {
              const rank = idx + 1;
              const isGold = rank === 1;
              const isSilver = rank === 2;
              const isBronze = rank === 3;

              return (
                <div
                  key={expert.id}
                  className={`p-3 rounded-xl border flex items-center justify-between gap-3 ${
                    isGold
                      ? 'bg-amber-50/60 dark:bg-amber-950/30 border-amber-300 dark:border-amber-800'
                      : isSilver
                      ? 'bg-slate-50 dark:bg-slate-800/60 border-slate-300 dark:border-slate-700'
                      : isBronze
                      ? 'bg-orange-50/40 dark:bg-orange-950/20 border-orange-200 dark:border-orange-800'
                      : 'bg-white dark:bg-slate-850 border-slate-200 dark:border-slate-800'
                  }`}
                >
                  <div className="flex items-center gap-3 min-w-0">
                    {/* Rank Badge */}
                    <div
                      className={`w-8 h-8 rounded-full flex items-center justify-center font-bold text-xs shrink-0 ${
                        isGold
                          ? 'bg-amber-400 text-slate-950 shadow-xs'
                          : isSilver
                          ? 'bg-slate-300 dark:bg-slate-600 text-slate-900 dark:text-white'
                          : isBronze
                          ? 'bg-amber-600 text-white'
                          : 'bg-slate-100 dark:bg-slate-800 text-slate-500'
                      }`}
                    >
                      {isGold ? '🥇' : isSilver ? '🥈' : isBronze ? '🥉' : rank}
                    </div>

                    <div className="min-w-0">
                      <div className="flex items-center gap-1.5 truncate">
                        <h4 className="font-bold text-sm text-slate-900 dark:text-white truncate">
                          {expert.name}
                        </h4>
                        <span className="text-[10px] font-bold px-1.5 py-0.2 rounded-full bg-blue-50 dark:bg-blue-950 text-blue-600 dark:text-blue-400 shrink-0">
                          {expert.category}
                        </span>
                      </div>
                      <p className="text-xs text-slate-500 dark:text-slate-400">
                        {expert.completedJobsCount} jobs completed • {expert.cancelledJobsCount} cancelled
                      </p>
                    </div>
                  </div>

                  {/* Rating */}
                  <div className="text-right shrink-0">
                    <div className="inline-flex items-center gap-1 font-bold text-sm text-amber-600 dark:text-amber-400">
                      <Star className="w-4 h-4 fill-amber-400 text-amber-400" />
                      {expert.rating.toFixed(1)}
                    </div>
                    <p className="text-[10px] text-slate-400">
                      {expert.totalRatingsCount} reviews
                    </p>
                  </div>
                </div>
              );
            })
          )}
        </div>

        {/* Footer */}
        <div className="p-4 border-t border-slate-100 dark:border-slate-800 flex justify-end">
          <button
            onClick={onClose}
            className="px-5 py-2 rounded-xl bg-slate-900 dark:bg-white text-white dark:text-slate-900 font-bold text-xs"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
};
