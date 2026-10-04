import React, { useState, useMemo } from 'react';
import {
  Search,
  X,
  Calendar,
  Phone,
  Clock,
  ShieldCheck,
  ShieldAlert,
  Star,
  MapPin,
  FileText,
  Trash2,
  MoreVertical,
  CheckCircle2,
  XCircle,
  Eye,
  Send,
  ArrowUp,
  UserCheck,
} from 'lucide-react';
import { useDispatchContext } from '../context/DispatchContext';
import { CustomerJob, OrderStatusTabType, DateFilterType } from '../types';
import { WhatsAppHelper } from '../utils/whatsAppHelper';

interface OrdersListProps {
  onOpenNearestExperts: (job: CustomerJob) => void;
  onCompleteAction: (job: CustomerJob) => void;
  onCancelAction: (job: CustomerJob) => void;
  onShowCompletedDetail: (job: CustomerJob) => void;
  onDeleteJob: (job: CustomerJob) => void;
  onLongPressOrder: (job: CustomerJob) => void;
  onOpenCustomDateModal: () => void;
  customStartDate: number | null;
  customEndDate: number | null;
}

export const OrdersList: React.FC<OrdersListProps> = ({
  onOpenNearestExperts,
  onCompleteAction,
  onCancelAction,
  onShowCompletedDetail,
  onDeleteJob,
  onLongPressOrder,
  onOpenCustomDateModal,
  customStartDate,
  customEndDate,
}) => {
  const {
    allJobs,
    currentOrderStatusTab,
    selectOrderStatusTab,
    updateExpertNotified,
    updateCustomerNotifiedOnAssign,
    updateCustomerNotifiedOnCompletion,
  } = useDispatchContext();

  const [searchQuery, setSearchQuery] = useState('');
  const [selectedDateFilter, setSelectedDateFilter] = useState<DateFilterType>('ALL');

  const statusTabs: { id: OrderStatusTabType; label: string; color: string; bg: string; border: string }[] = [
    {
      id: 'PENDING',
      label: 'Pending',
      color: 'text-amber-800 dark:text-amber-300',
      bg: 'bg-amber-100 dark:bg-amber-950/80',
      border: 'border-amber-400 dark:border-amber-700',
    },
    {
      id: 'PROCESSING',
      label: 'Processing',
      color: 'text-blue-800 dark:text-blue-300',
      bg: 'bg-blue-100 dark:bg-blue-950/80',
      border: 'border-blue-400 dark:border-blue-700',
    },
    {
      id: 'COMPLETED',
      label: 'Completed',
      color: 'text-emerald-800 dark:text-emerald-300',
      bg: 'bg-emerald-100 dark:bg-emerald-950/80',
      border: 'border-emerald-400 dark:border-emerald-700',
    },
    {
      id: 'CANCELLED',
      label: 'Cancelled',
      color: 'text-rose-800 dark:text-rose-300',
      bg: 'bg-rose-100 dark:bg-rose-950/80',
      border: 'border-rose-400 dark:border-rose-700',
    },
  ];

  const matchesDate = (timestamp: number): boolean => {
    const now = new Date();
    if (selectedDateFilter === 'TODAY') {
      const todayStart = new Date(now.getFullYear(), now.getMonth(), now.getDate()).getTime();
      return timestamp >= todayStart;
    }
    if (selectedDateFilter === 'LAST_7_DAYS') {
      const sevenDaysAgo = Date.now() - 7 * 86400000;
      return timestamp >= sevenDaysAgo;
    }
    if (selectedDateFilter === 'THIS_MONTH') {
      const monthStart = new Date(now.getFullYear(), now.getMonth(), 1).getTime();
      return timestamp >= monthStart;
    }
    if (selectedDateFilter === 'CUSTOM') {
      if (customStartDate && customEndDate) {
        return timestamp >= customStartDate && timestamp <= customEndDate;
      }
      return true;
    }
    return true;
  };

  const filteredJobs = useMemo(() => {
    const query = searchQuery.trim().toLowerCase();
    return allJobs.filter((job) => {
      if (job.status !== currentOrderStatusTab) return false;
      if (!matchesDate(job.createdAt)) return false;
      if (!query) return true;
      return (
        job.customerName.toLowerCase().includes(query) ||
        job.customerPhone.includes(query) ||
        job.serviceType.toLowerCase().includes(query) ||
        (job.assignedExpertName && job.assignedExpertName.toLowerCase().includes(query))
      );
    });
  }, [allJobs, currentOrderStatusTab, searchQuery, selectedDateFilter, customStartDate, customEndDate]);

  const calculateDuration = (created: number, completed?: number | null) => {
    const end = completed || Date.now();
    const diffMs = Math.max(0, end - created);
    const diffMins = Math.floor(diffMs / 60000);
    const hours = Math.floor(diffMins / 60);
    const mins = diffMins % 60;
    if (hours === 0) return `${mins} mins`;
    return `${hours} hr ${mins} mins`;
  };

  return (
    <div className="max-w-4xl mx-auto p-4 sm:p-6 pb-28">
      {/* 4 Status Filter Tabs */}
      <div className="flex items-center gap-2 overflow-x-auto pb-2 scrollbar-none">
        {statusTabs.map((tab) => {
          const count = allJobs.filter((j) => j.status === tab.id).length;
          const isSelected = currentOrderStatusTab === tab.id;
          return (
            <button
              key={tab.id}
              onClick={() => selectOrderStatusTab(tab.id)}
              className={`flex items-center gap-2 px-4 py-2 rounded-xl text-xs sm:text-sm font-bold whitespace-nowrap transition-all border shrink-0 cursor-pointer ${
                isSelected
                  ? `${tab.bg} ${tab.color} ${tab.border} shadow-xs`
                  : 'bg-white dark:bg-slate-800 text-slate-600 dark:text-slate-400 border-slate-200 dark:border-slate-700 hover:bg-slate-50 dark:hover:bg-slate-750'
              }`}
            >
              <span>{tab.label}</span>
              <span
                className={`text-[11px] px-1.5 py-0.5 rounded-full ${
                  isSelected
                    ? 'bg-black/10 dark:bg-white/20'
                    : 'bg-slate-100 dark:bg-slate-700 text-slate-500 dark:text-slate-400'
                }`}
              >
                {count}
              </span>
            </button>
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
          placeholder="Search by name, mobile, service, or expert..."
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

      {/* Date Filter Chips */}
      <div className="mt-2.5 flex items-center gap-1.5 overflow-x-auto pb-1 scrollbar-none">
        {(['ALL', 'TODAY', 'LAST_7_DAYS', 'THIS_MONTH'] as DateFilterType[]).map((filter) => {
          const labels: Record<DateFilterType, string> = {
            ALL: 'All Time',
            TODAY: 'Today',
            LAST_7_DAYS: 'Last 7 Days',
            THIS_MONTH: 'This Month',
            CUSTOM: 'Custom',
          };
          return (
            <button
              key={filter}
              onClick={() => setSelectedDateFilter(filter)}
              className={`px-3 py-1 rounded-lg text-xs font-semibold whitespace-nowrap transition-colors cursor-pointer ${
                selectedDateFilter === filter
                  ? 'bg-slate-900 dark:bg-white text-white dark:text-slate-900 shadow-2xs'
                  : 'bg-slate-100 dark:bg-slate-800 text-slate-600 dark:text-slate-300 hover:bg-slate-200 dark:hover:bg-slate-700'
              }`}
            >
              {labels[filter]}
            </button>
          );
        })}

        {/* Custom Range Button */}
        <button
          onClick={() => {
            setSelectedDateFilter('CUSTOM');
            onOpenCustomDateModal();
          }}
          className={`flex items-center gap-1 px-3 py-1 rounded-lg text-xs font-semibold whitespace-nowrap transition-colors cursor-pointer ${
            selectedDateFilter === 'CUSTOM'
              ? 'bg-blue-600 text-white shadow-2xs'
              : 'bg-slate-100 dark:bg-slate-800 text-slate-600 dark:text-slate-300 hover:bg-slate-200 dark:hover:bg-slate-700'
          }`}
        >
          <Calendar className="w-3.5 h-3.5" />
          {selectedDateFilter === 'CUSTOM' && customStartDate && customEndDate
            ? `${new Date(customStartDate).toLocaleDateString('en-IN', {
                day: '2-digit',
                month: 'short',
              })} - ${new Date(customEndDate).toLocaleDateString('en-IN', {
                day: '2-digit',
                month: 'short',
              })}`
            : 'Custom Range'}
        </button>
      </div>

      {/* Orders List Section */}
      <div className="mt-4 space-y-3.5">
        {filteredJobs.length === 0 ? (
          <div className="bg-white dark:bg-slate-900 rounded-2xl border border-slate-200 dark:border-slate-800 p-12 text-center">
            <span className="text-3xl block mb-2">
              {currentOrderStatusTab === 'PENDING'
                ? '⏳'
                : currentOrderStatusTab === 'PROCESSING'
                ? '⚙️'
                : currentOrderStatusTab === 'COMPLETED'
                ? '✅'
                : '❌'}
            </span>
            <h3 className="font-bold text-slate-800 dark:text-slate-200 text-base">
              {searchQuery || selectedDateFilter !== 'ALL'
                ? 'No matching records found'
                : currentOrderStatusTab === 'PENDING'
                ? 'No pending orders found'
                : currentOrderStatusTab === 'PROCESSING'
                ? 'No orders currently in processing'
                : currentOrderStatusTab === 'COMPLETED'
                ? 'No completed orders found'
                : 'No cancelled orders found'}
            </h3>
            <p className="text-xs text-slate-500 dark:text-slate-400 mt-1 max-w-sm mx-auto">
              {currentOrderStatusTab === 'PENDING'
                ? 'Dispatch new customer orders from the "Dispatch Order" tab to see them appear here.'
                : 'Manage and update order statuses as technicians carry out assignments.'}
            </p>
          </div>
        ) : (
          filteredJobs.map((job) => {
            const createdDateStr = new Date(job.createdAt).toLocaleString('en-IN', {
              day: '2-digit',
              month: 'short',
              hour: '2-digit',
              minute: '2-digit',
              hour12: true,
            });

            // 10-day warranty check for completed orders
            const completedTime = job.completedAt || job.createdAt;
            const daysPassed = Math.floor((Date.now() - completedTime) / 86400000);
            const isWarrantyValid = daysPassed <= 10;
            const daysRemaining = Math.max(0, 10 - daysPassed);

            return (
              <div
                key={job.id}
                className="bg-white dark:bg-slate-900 rounded-2xl border border-slate-200 dark:border-slate-800 p-4 sm:p-5 shadow-xs hover:shadow-md transition-shadow relative overflow-hidden"
              >
                {/* Card Header */}
                <div className="flex items-start justify-between gap-3">
                  <div>
                    <div className="flex items-center gap-2">
                      <h3 className="font-bold text-base text-slate-900 dark:text-white">
                        {job.customerName}
                      </h3>
                      <span className="text-xs text-slate-400 font-mono">#{job.id.toString().slice(-4)}</span>
                    </div>
                    <div className="flex flex-wrap items-center gap-2 text-xs text-slate-500 dark:text-slate-400 mt-0.5">
                      <span className="font-semibold text-slate-700 dark:text-slate-300">
                        📞 {job.customerPhone}
                      </span>
                      <span>•</span>
                      <span>{createdDateStr}</span>
                    </div>
                  </div>

                  <div className="flex items-center gap-1.5">
                    {/* Status Badge */}
                    <span
                      className={`text-[11px] font-bold px-2.5 py-0.5 rounded-full ${
                        job.status === 'PENDING'
                          ? 'bg-amber-100 text-amber-800 dark:bg-amber-950 dark:text-amber-300'
                          : job.status === 'PROCESSING'
                          ? 'bg-blue-100 text-blue-800 dark:bg-blue-950 dark:text-blue-300'
                          : job.status === 'COMPLETED'
                          ? 'bg-emerald-100 text-emerald-800 dark:bg-emerald-950 dark:text-emerald-300'
                          : 'bg-rose-100 text-rose-800 dark:bg-rose-950 dark:text-rose-300'
                      }`}
                    >
                      {job.status}
                    </span>

                    {/* Long press / edit menu button */}
                    <button
                      onClick={() => onLongPressOrder(job)}
                      title="Order Actions"
                      className="p-1 rounded-lg text-slate-400 hover:text-slate-700 dark:hover:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800"
                    >
                      <MoreVertical className="w-4 h-4" />
                    </button>
                  </div>
                </div>

                <div className="my-3 border-t border-slate-100 dark:border-slate-800" />

                {/* Details */}
                <div className="space-y-1.5 text-xs sm:text-sm">
                  <div className="flex items-start gap-2">
                    <span className="font-bold text-blue-600 dark:text-blue-400 shrink-0">
                      🛠 Service:
                    </span>
                    <span className="font-semibold text-slate-800 dark:text-slate-200">
                      {job.serviceType}
                    </span>
                  </div>

                  {job.issueDescription && (
                    <div className="flex items-start gap-2 text-slate-600 dark:text-slate-300">
                      <span className="font-semibold text-slate-400 shrink-0">📝 Problem:</span>
                      <span>{job.issueDescription}</span>
                    </div>
                  )}

                  <div className="flex items-start gap-2 text-slate-600 dark:text-slate-300">
                    <span className="font-semibold text-slate-400 shrink-0">📍 Address:</span>
                    <span>{job.address || 'Address not specified'}</span>
                  </div>
                </div>

                {/* Processing State: Assigned Expert Banner & WhatsApp Flow */}
                {job.status === 'PROCESSING' && job.assignedExpertName && (
                  <div className="mt-3.5 p-3.5 rounded-xl bg-blue-50/70 dark:bg-blue-950/40 border border-blue-200/80 dark:border-blue-900/60 space-y-2.5">
                    <div className="flex items-center justify-between">
                      <div>
                        <span className="text-[11px] font-bold uppercase tracking-wider text-blue-600 dark:text-blue-400">
                          Assigned Expert
                        </span>
                        <h4 className="font-bold text-sm text-slate-900 dark:text-white">
                          {job.assignedExpertName}
                        </h4>
                        {job.assignedExpertPhone && (
                          <p className="text-xs text-slate-500 dark:text-slate-400">
                            📞 {job.assignedExpertPhone}
                          </p>
                        )}
                      </div>

                      {job.assignedExpertPhone && (
                        <button
                          onClick={() => WhatsAppHelper.openDialer(job.assignedExpertPhone!)}
                          className="p-2 rounded-xl bg-white dark:bg-slate-800 text-blue-600 dark:text-blue-400 border border-blue-200 dark:border-blue-700 hover:bg-blue-50 shadow-2xs cursor-pointer"
                          title="Call Expert"
                        >
                          <Phone className="w-4 h-4" />
                        </button>
                      )}
                    </div>

                    <div className="border-t border-blue-200/60 dark:border-blue-900/40 pt-2.5 space-y-2">
                      {/* Expert WhatsApp status */}
                      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-1.5">
                        <span
                          className={`text-xs font-semibold px-2 py-0.5 rounded-md inline-flex items-center gap-1.5 self-start ${
                            job.isExpertNotified
                              ? 'bg-emerald-100 dark:bg-emerald-950 text-emerald-800 dark:text-emerald-300'
                              : 'bg-amber-100 dark:bg-amber-950 text-amber-800 dark:text-amber-300'
                          }`}
                        >
                          {job.isExpertNotified
                            ? '✅ Expert WhatsApp Sent'
                            : '⚠️ WhatsApp message not sent yet'}
                        </span>

                        <button
                          onClick={() => {
                            if (job.assignedExpertPhone) {
                              const expObj = {
                                id: job.assignedExpertId || 0,
                                name: job.assignedExpertName || 'Expert',
                                phone: job.assignedExpertPhone,
                                category: job.serviceType,
                                address: '',
                                latitude: 0,
                                longitude: 0,
                                isAvailable: true,
                                rating: 5,
                                ratingSum: 5,
                                totalRatingsCount: 1,
                                completedJobsCount: 0,
                                cancelledJobsCount: 0,
                                isWelcomeMessageSent: true,
                                isDeleted: false,
                                createdAt: Date.now(),
                              };
                              const msg = WhatsAppHelper.createDispatchMessage(expObj, job);
                              WhatsAppHelper.openWhatsAppDirectMessage(job.assignedExpertPhone, msg);
                              updateExpertNotified(job.id, true);
                            }
                          }}
                          className="text-xs font-bold text-blue-600 dark:text-blue-400 hover:underline flex items-center gap-1 cursor-pointer self-start sm:self-auto"
                        >
                          <Send className="w-3.5 h-3.5" />
                          {job.isExpertNotified ? 'Send Again' : 'Send to Expert'}
                        </button>
                      </div>

                      {/* Customer WhatsApp notification status */}
                      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-1.5">
                        <span
                          className={`text-xs font-semibold px-2 py-0.5 rounded-md inline-flex items-center gap-1.5 self-start ${
                            job.isCustomerNotifiedOnAssign
                              ? 'bg-emerald-100 dark:bg-emerald-950 text-emerald-800 dark:text-emerald-300'
                              : 'bg-amber-100 dark:bg-amber-950 text-amber-800 dark:text-amber-300'
                          }`}
                        >
                          {job.isCustomerNotifiedOnAssign
                            ? '✅ Customer WhatsApp Sent'
                            : '⚠️ Customer WhatsApp pending'}
                        </span>

                        <button
                          onClick={() => {
                            const estTime = WhatsAppHelper.calculateEstimatedArrivalTimeWithBuffer(
                              job.distanceKmAtDispatch || 2.5
                            );
                            const msg = WhatsAppHelper.createCustomerAssignmentNotificationMessage(
                              job.customerName,
                              job.assignedExpertName || 'Expert',
                              job.assignedExpertPhone || '',
                              job.serviceType,
                              estTime
                            );
                            WhatsAppHelper.openWhatsAppDirectMessage(job.customerPhone, msg);
                            updateCustomerNotifiedOnAssign(job.id, true);
                          }}
                          className="text-xs font-bold text-blue-600 dark:text-blue-400 hover:underline flex items-center gap-1 cursor-pointer self-start sm:self-auto"
                        >
                          <Send className="w-3.5 h-3.5" />
                          {job.isCustomerNotifiedOnAssign ? 'Send Again' : 'Send to Customer'}
                        </button>
                      </div>
                    </div>
                  </div>
                )}

                {/* Completed State: Warranty & Duration badges + Rating */}
                {job.status === 'COMPLETED' && (
                  <div className="mt-3.5 space-y-2.5">
                    <div className="flex flex-wrap items-center gap-2">
                      <span className="inline-flex items-center gap-1 px-2.5 py-1 rounded-lg text-xs font-bold bg-emerald-50 dark:bg-emerald-950/60 text-emerald-700 dark:text-emerald-300 border border-emerald-200 dark:border-emerald-800">
                        <Clock className="w-3.5 h-3.5" />
                        ⏱ {calculateDuration(job.createdAt, job.completedAt)}
                      </span>

                      <span
                        className={`inline-flex items-center gap-1 px-2.5 py-1 rounded-lg text-xs font-bold border ${
                          isWarrantyValid
                            ? 'bg-emerald-50 dark:bg-emerald-950/60 text-emerald-700 dark:text-emerald-300 border-emerald-200 dark:border-emerald-800'
                            : 'bg-rose-50 dark:bg-rose-950/60 text-rose-700 dark:text-rose-300 border-rose-200 dark:border-rose-800'
                        }`}
                      >
                        {isWarrantyValid ? (
                          <>
                            <ShieldCheck className="w-3.5 h-3.5 text-emerald-600" />
                            🛡️ 10-Day Warranty: {daysRemaining} Days Left
                          </>
                        ) : (
                          <>
                            <ShieldAlert className="w-3.5 h-3.5 text-rose-600" />
                            ⚠️ Warranty Expired ({daysPassed} days ago)
                          </>
                        )}
                      </span>
                    </div>

                    {/* Completion WhatsApp message status */}
                    <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-1.5 p-2 rounded-lg bg-slate-50 dark:bg-slate-800/60 border border-slate-200 dark:border-slate-700">
                      <span
                        className={`text-xs font-semibold px-2 py-0.5 rounded-md inline-flex items-center gap-1 self-start ${
                          job.isCustomerNotifiedOnCompletion
                            ? 'bg-emerald-100 dark:bg-emerald-950 text-emerald-800 dark:text-emerald-300'
                            : 'bg-amber-100 dark:bg-amber-950 text-amber-800 dark:text-amber-300'
                        }`}
                      >
                        {job.isCustomerNotifiedOnCompletion
                          ? '✅ Completion WhatsApp Sent'
                          : '⚠️ Completion WhatsApp pending'}
                      </span>

                      <button
                        onClick={() => {
                          const msg = WhatsAppHelper.createCompletionCustomerMessage(job.customerName);
                          WhatsAppHelper.openWhatsAppDirectMessage(job.customerPhone, msg);
                          updateCustomerNotifiedOnCompletion(job.id, true);
                        }}
                        className="text-xs font-bold text-blue-600 dark:text-blue-400 hover:underline flex items-center gap-1 cursor-pointer self-start sm:self-auto"
                      >
                        <Send className="w-3.5 h-3.5" />
                        {job.isCustomerNotifiedOnCompletion ? 'Send Again' : 'Send to Customer'}
                      </button>
                    </div>

                    {/* Rating Given */}
                    {job.ratingGiven != null && (
                      <div className="flex items-center gap-2 text-xs">
                        <span className="font-semibold text-slate-500">Rating:</span>
                        <div className="flex items-center text-amber-500">
                          {[1, 2, 3, 4, 5].map((star) => (
                            <Star
                              key={star}
                              className={`w-3.5 h-3.5 ${
                                star <= job.ratingGiven! ? 'fill-amber-400' : 'text-slate-300'
                              }`}
                            />
                          ))}
                        </div>
                        <span className="font-bold text-amber-700 dark:text-amber-400">
                          ({job.ratingGiven}/5)
                        </span>
                        {job.reviewFeedback && (
                          <span className="text-slate-600 dark:text-slate-400 italic truncate max-w-xs">
                            — "{job.reviewFeedback}"
                          </span>
                        )}
                      </div>
                    )}
                  </div>
                )}

                {/* Cancelled State Details */}
                {job.status === 'CANCELLED' && job.reviewFeedback && (
                  <div className="mt-3 p-2.5 rounded-xl bg-rose-50 dark:bg-rose-950/40 border border-rose-200 dark:border-rose-900 text-xs text-rose-800 dark:text-rose-300">
                    <span className="font-bold">Cancellation Reason:</span> {job.reviewFeedback}
                  </div>
                )}

                {/* Action Buttons Row */}
                <div className="mt-4 pt-3 border-t border-slate-100 dark:border-slate-800 flex items-center gap-2">
                  {job.status === 'PENDING' && (
                    <>
                      <button
                        onClick={() => onOpenNearestExperts(job)}
                        className="flex-1 py-2 px-3 rounded-xl bg-blue-600 hover:bg-blue-700 text-white font-bold text-xs sm:text-sm flex items-center justify-center gap-1.5 shadow-2xs transition-colors cursor-pointer"
                      >
                        <Search className="w-4 h-4" />
                        Find Nearest Experts
                      </button>

                      <button
                        onClick={() => WhatsAppHelper.openDialer(job.customerPhone)}
                        className="p-2 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800"
                        title="Call Customer"
                      >
                        <Phone className="w-4 h-4" />
                      </button>

                      <button
                        onClick={() => onDeleteJob(job)}
                        className="p-2 rounded-xl text-slate-400 hover:text-rose-600 hover:bg-rose-50 dark:hover:bg-rose-950/40"
                        title="Move to Recycle Bin"
                      >
                        <Trash2 className="w-4 h-4" />
                      </button>
                    </>
                  )}

                  {job.status === 'PROCESSING' && (
                    <>
                      <button
                        onClick={() => onCompleteAction(job)}
                        className="flex-1 py-2 px-3 rounded-xl bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-xs sm:text-sm flex items-center justify-center gap-1.5 shadow-2xs transition-colors cursor-pointer"
                      >
                        <CheckCircle2 className="w-4 h-4" />
                        Mark Complete
                      </button>

                      <button
                        onClick={() => onCancelAction(job)}
                        className="flex-1 py-2 px-3 rounded-xl border border-rose-300 dark:border-rose-800 text-rose-600 dark:text-rose-400 hover:bg-rose-50 dark:hover:bg-rose-950 font-bold text-xs sm:text-sm flex items-center justify-center gap-1.5 transition-colors cursor-pointer"
                      >
                        <XCircle className="w-4 h-4" />
                        Cancel Order
                      </button>
                    </>
                  )}

                  {job.status === 'COMPLETED' && (
                    <>
                      <button
                        onClick={() => onShowCompletedDetail(job)}
                        className="flex-1 py-2 px-3 rounded-xl bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-xs sm:text-sm flex items-center justify-center gap-1.5 shadow-2xs transition-colors cursor-pointer"
                      >
                        <Eye className="w-4 h-4" />
                        Full History & Receipt
                      </button>

                      <button
                        onClick={() => WhatsAppHelper.openDialer(job.customerPhone)}
                        className="px-3 py-2 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800 font-semibold text-xs flex items-center gap-1"
                      >
                        <Phone className="w-3.5 h-3.5" />
                        Call
                      </button>

                      <button
                        onClick={() => onDeleteJob(job)}
                        className="p-2 rounded-xl text-slate-400 hover:text-rose-600 hover:bg-rose-50 dark:hover:bg-rose-950/40"
                        title="Move to Recycle Bin"
                      >
                        <Trash2 className="w-4 h-4" />
                      </button>
                    </>
                  )}

                  {job.status === 'CANCELLED' && (
                    <>
                      <button
                        onClick={() => WhatsAppHelper.openDialer(job.customerPhone)}
                        className="flex-1 py-2 px-3 rounded-xl border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800 font-semibold text-xs sm:text-sm flex items-center justify-center gap-1.5"
                      >
                        <Phone className="w-4 h-4" />
                        Call Customer
                      </button>

                      <button
                        onClick={() => onDeleteJob(job)}
                        className="p-2 rounded-xl text-slate-400 hover:text-rose-600 hover:bg-rose-50 dark:hover:bg-rose-950/40"
                        title="Move to Recycle Bin"
                      >
                        <Trash2 className="w-4 h-4" />
                      </button>
                    </>
                  )}
                </div>
              </div>
            );
          })
        )}
      </div>
    </div>
  );
};
