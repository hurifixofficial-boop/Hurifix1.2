import React, { useState, useEffect } from 'react';
import {
  Menu,
  RotateCw,
  ArrowLeft,
  FileText,
  ClipboardList,
  Wrench,
  CheckCircle2,
  X,
} from 'lucide-react';
import { useDispatchContext } from './context/DispatchContext';
import { SessionManager } from './utils/sessionManager';
import { WhatsAppHelper } from './utils/whatsAppHelper';
import { CustomerJob, Expert, ExpertCategory, RankedExpert } from './types';

// Components
import { NavigationDrawer } from './components/NavigationDrawer';
import { DispatchOrderForm } from './components/DispatchOrderForm';
import { OrdersList } from './components/OrdersList';
import { ExpertsTab } from './components/ExpertsTab';
import { AuthScreen } from './components/AuthScreen';

// Modals
import { AddExpertModal } from './components/modals/AddExpertModal';
import { WhatsAppLeadParserModal } from './components/modals/WhatsAppLeadParserModal';
import { SaveChoiceModal } from './components/modals/SaveChoiceModal';
import { NearestExpertsModal } from './components/modals/NearestExpertsModal';
import { AssignExpertWhatsAppModal } from './components/modals/AssignExpertWhatsAppModal';
import { AssignCustomerWhatsAppModal } from './components/modals/AssignCustomerWhatsAppModal';
import { CustomerCompletionWhatsAppModal } from './components/modals/CustomerCompletionWhatsAppModal';
import { ReviewModal } from './components/modals/ReviewModal';
import { CompletedOrderDetailModal } from './components/modals/CompletedOrderDetailModal';
import { ExpertsRankingModal } from './components/modals/ExpertsRankingModal';
import { MonthlyAnalyticsModal } from './components/modals/MonthlyAnalyticsModal';
import { ExpertWorkHistoryModal } from './components/modals/ExpertWorkHistoryModal';
import { RecycleBinModal } from './components/modals/RecycleBinModal';
import { EditAdminProfileModal } from './components/modals/EditAdminProfileModal';
import { EditCustomerOrderModal } from './components/modals/EditCustomerOrderModal';
import { OrderLongPressMenuModal } from './components/modals/OrderLongPressMenuModal';
import { UniversalDeleteModal } from './components/modals/UniversalDeleteModal';
import { CustomDateRangeModal } from './components/modals/CustomDateRangeModal';
import { SendWelcomeExpertModal } from './components/modals/SendWelcomeExpertModal';
import { AddNewCategoryModal } from './components/modals/AddNewCategoryModal';

export const App: React.FC = () => {
  const {
    allJobs,
    allExperts,
    allCategories,
    deletedJobs,
    deletedExperts,
    currentMainTab,
    currentCustomerSubTab,
    statusMessage,
    activeJobForNearestExperts,
    isRefreshing,
    selectMainTab,
    selectCustomerSubTab,
    selectOrderStatusTab,
    clearStatusMessage,
    refreshAllData,
    openFindNearestExperts,
    closeFindNearestExperts,
    getNearestExpertsForJob,
    assignExpertToJob,
    unassignExpert,
    updateJob,
    completeOrCancelJobWithReview,
    updateExpertNotified,
    updateCustomerNotifiedOnAssign,
    updateCustomerNotifiedOnCompletion,
    markMessageLaterDismissed,
    deleteJob,
    restoreJobFromRecycleBin,
    deleteJobPermanently,
    saveNewExpert,
    updateExpert,
    deleteExpert,
    restoreExpertFromRecycleBin,
    deleteExpertPermanently,
    updateWelcomeMessageSent,
    addNewCategory,
    deleteCategory,
    emptyRecycleBin,
    onUserLoggedIn,
    onUserLoggedOut,
  } = useDispatchContext();

  const [isLoggedIn, setIsLoggedIn] = useState(() => SessionManager.isLoggedIn());
  const [isDarkMode, setIsDarkMode] = useState(() => SessionManager.isDarkModeEnabled());

  // Navigation drawer state
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  // Modals state
  const [isAddExpertModalOpen, setIsAddExpertModalOpen] = useState(false);
  const [expertToEdit, setExpertToEdit] = useState<Expert | null>(null);
  const [isWhatsAppParserOpen, setIsWhatsAppParserOpen] = useState(false);
  const [saveChoiceJob, setSaveChoiceJob] = useState<CustomerJob | null>(null);
  const [isRankingModalOpen, setIsRankingModalOpen] = useState(false);
  const [isMonthlyAnalyticsModalOpen, setIsMonthlyAnalyticsModalOpen] = useState(false);
  const [completedDetailJob, setCompletedDetailJob] = useState<CustomerJob | null>(null);
  const [reviewTarget, setReviewTarget] = useState<{ job: CustomerJob; isCompleted: boolean } | null>(null);

  // WhatsApp flow modals
  const [assignExpertWhatsApp, setAssignExpertWhatsApp] = useState<{
    job: CustomerJob;
    ranked: RankedExpert;
  } | null>(null);
  const [assignCustomerWhatsApp, setAssignCustomerWhatsApp] = useState<{
    job: CustomerJob;
    ranked: RankedExpert;
    estTime: string;
  } | null>(null);
  const [completionCustomerWhatsApp, setCompletionCustomerWhatsApp] = useState<CustomerJob | null>(null);
  const [welcomeExpert, setWelcomeExpert] = useState<Expert | null>(null);

  // Profile & Order edit modals
  const [isAdminProfileModalOpen, setIsAdminProfileModalOpen] = useState(false);
  const [longPressJob, setLongPressJob] = useState<CustomerJob | null>(null);
  const [editingCustomerJob, setEditingCustomerJob] = useState<CustomerJob | null>(null);

  // Delete confirmation
  const [deleteTarget, setDeleteTarget] = useState<{
    type: 'job' | 'expert' | 'category';
    item: any;
    title: string;
    message: string;
  } | null>(null);

  // Category & History modals
  const [isAddNewCategoryModalOpen, setIsAddNewCategoryModalOpen] = useState(false);
  const [expertForWorkHistory, setExpertForWorkHistory] = useState<Expert | null>(null);
  const [isRecycleBinModalOpen, setIsRecycleBinModalOpen] = useState(false);
  const [isCustomDateModalOpen, setIsCustomDateModalOpen] = useState(false);
  const [customStartDate, setCustomStartDate] = useState<number | null>(null);
  const [customEndDate, setCustomEndDate] = useState<number | null>(null);

  // Sync dark mode class
  useEffect(() => {
    if (isDarkMode) {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
    SessionManager.setDarkModeEnabled(isDarkMode);
  }, [isDarkMode]);

  // Auto clear toast
  useEffect(() => {
    if (statusMessage) {
      const timer = setTimeout(() => {
        clearStatusMessage();
      }, 3500);
      return () => clearTimeout(timer);
    }
  }, [statusMessage, clearStatusMessage]);

  const handleToggleDarkMode = () => {
    setIsDarkMode((prev) => !prev);
  };

  const handleLoginSuccess = (name: string) => {
    setIsLoggedIn(true);
    onUserLoggedIn(SessionManager.getUserPhone());
  };

  const handleLogout = () => {
    SessionManager.logout();
    setIsLoggedIn(false);
    onUserLoggedOut();
  };

  if (!isLoggedIn) {
    return <AuthScreen onLoginSuccess={handleLoginSuccess} />;
  }

  const categoryNames = allCategories.map((c) => c.name);

  return (
    <div className="min-h-screen bg-slate-50 dark:bg-slate-950 text-slate-900 dark:text-slate-100 flex flex-col font-sans antialiased transition-colors">
      {/* Top App Bar */}
      <header className="sticky top-0 z-40 bg-white/95 dark:bg-slate-900/95 backdrop-blur-md border-b border-slate-200 dark:border-slate-800 shadow-2xs">
        <div className="max-w-5xl mx-auto px-4 h-15 flex items-center justify-between">
          <div className="flex items-center gap-3">
            {currentMainTab === 'EXPERTS' ? (
              <button
                onClick={() => selectMainTab('CUSTOMER_ORDERS')}
                className="p-2 rounded-xl text-slate-600 dark:text-slate-300 hover:bg-slate-100 dark:hover:bg-slate-800 transition-colors cursor-pointer"
                title="Back to Orders"
              >
                <ArrowLeft className="w-5 h-5" />
              </button>
            ) : (
              <button
                onClick={() => setIsDrawerOpen(true)}
                className="p-2 rounded-xl text-blue-600 dark:text-blue-400 hover:bg-blue-50 dark:hover:bg-slate-800 transition-colors cursor-pointer"
                title="Open Navigation Menu"
              >
                <Menu className="w-5 h-5" />
              </button>
            )}

            <div className="flex items-center gap-2.5">
              <img
                src="/hurifix_logo.svg"
                alt="Hurifix"
                className="w-8 h-8 rounded-lg object-contain shadow-2xs border border-amber-200 dark:border-slate-700 bg-white"
              />
              <div>
                <h1 className="text-base sm:text-lg font-bold text-slate-900 dark:text-white leading-tight">
                  {currentMainTab === 'EXPERTS'
                    ? `Manage Experts (${allExperts.length})`
                    : 'Hurifix'}
                </h1>
                {currentMainTab !== 'EXPERTS' && (
                  <p className="text-[10px] font-semibold text-amber-600 dark:text-amber-400 leading-none">
                    Many Problems | One Solution
                  </p>
                )}
              </div>
            </div>
          </div>

          <div className="flex items-center gap-2">
            <button
              onClick={refreshAllData}
              disabled={isRefreshing}
              className={`p-2 rounded-xl text-blue-600 dark:text-blue-400 hover:bg-blue-50 dark:hover:bg-slate-800 transition-colors cursor-pointer ${
                isRefreshing ? 'animate-spin opacity-70' : ''
              }`}
              title="Refresh Data"
            >
              <RotateCw className="w-5 h-5" />
            </button>
          </div>
        </div>

        {/* Sub-tabs for Customer Orders: Dispatch Order vs Orders */}
        {currentMainTab === 'CUSTOMER_ORDERS' && (
          <div className="border-t border-slate-100 dark:border-slate-800 bg-slate-50/70 dark:bg-slate-900/60">
            <div className="max-w-5xl mx-auto px-4 flex">
              <button
                onClick={() => selectCustomerSubTab('DISPATCH_ORDER')}
                className={`flex-1 py-2.5 text-xs sm:text-sm font-bold border-b-2 transition-all flex items-center justify-center gap-2 cursor-pointer ${
                  currentCustomerSubTab === 'DISPATCH_ORDER'
                    ? 'border-blue-600 text-blue-600 dark:text-blue-400 bg-white dark:bg-slate-850'
                    : 'border-transparent text-slate-500 hover:text-slate-800 dark:hover:text-slate-300'
                }`}
              >
                <FileText className="w-4 h-4" />
                Dispatch Order
              </button>

              <button
                onClick={() => selectCustomerSubTab('ORDERS')}
                className={`flex-1 py-2.5 text-xs sm:text-sm font-bold border-b-2 transition-all flex items-center justify-center gap-2 cursor-pointer ${
                  currentCustomerSubTab === 'ORDERS'
                    ? 'border-blue-600 text-blue-600 dark:text-blue-400 bg-white dark:bg-slate-850'
                    : 'border-transparent text-slate-500 hover:text-slate-800 dark:hover:text-slate-300'
                }`}
              >
                <ClipboardList className="w-4 h-4" />
                Orders ({allJobs.length})
              </button>
            </div>
          </div>
        )}
      </header>

      {/* Main Content Area */}
      <main className="flex-1">
        {currentMainTab === 'CUSTOMER_ORDERS' ? (
          currentCustomerSubTab === 'DISPATCH_ORDER' ? (
            <DispatchOrderForm
              onOpenWhatsAppParser={() => setIsWhatsAppParserOpen(true)}
              onOrderSaved={(savedJob) => setSaveChoiceJob(savedJob)}
            />
          ) : (
            <OrdersList
              onOpenNearestExperts={(job) => openFindNearestExperts(job)}
              onCompleteAction={(job) => setCompletionCustomerWhatsApp(job)}
              onCancelAction={(job) => setReviewTarget({ job, isCompleted: false })}
              onShowCompletedDetail={(job) => setCompletedDetailJob(job)}
              onDeleteJob={(job) =>
                setDeleteTarget({
                  type: 'job',
                  item: job,
                  title: `Move Order #${job.id.toString().slice(-4)} to Recycle Bin?`,
                  message: `Order for "${job.customerName}" will be kept safely in the Recycle Bin for 30 days. You can restore it anytime.`,
                })
              }
              onLongPressOrder={(job) => setLongPressJob(job)}
              onOpenCustomDateModal={() => setIsCustomDateModalOpen(true)}
              customStartDate={customStartDate}
              customEndDate={customEndDate}
            />
          )
        ) : (
          <ExpertsTab
            onAddExpert={() => {
              setExpertToEdit(null);
              setIsAddExpertModalOpen(true);
            }}
            onEditExpert={(expert) => {
              setExpertToEdit(expert);
              setIsAddExpertModalOpen(true);
            }}
            onDeleteExpert={(expert) =>
              setDeleteTarget({
                type: 'expert',
                item: expert,
                title: `Move Expert "${expert.name}" to Recycle Bin?`,
                message: `Expert "${expert.name}" (${expert.category}) will be moved to the Recycle Bin and kept safely for 30 days.`,
              })
            }
            onViewWorkHistory={(expert) => setExpertForWorkHistory(expert)}
            onSendWelcome={(expert) => setWelcomeExpert(expert)}
            onAddNewCategory={() => setIsAddNewCategoryModalOpen(true)}
            onDeleteCategory={(cat) =>
              setDeleteTarget({
                type: 'category',
                item: cat,
                title: `Delete Category "${cat.name}"?`,
                message: `Are you sure you want to delete category "${cat.name}"? Existing partner experts will remain intact.`,
              })
            }
          />
        )}
      </main>

      {/* Floating Status Notification / Toast */}
      {statusMessage && (
        <div className="fixed bottom-6 left-1/2 -translate-x-1/2 z-50 max-w-md w-[90%] bg-slate-900 dark:bg-white text-white dark:text-slate-900 px-4 py-3 rounded-2xl shadow-2xl flex items-center justify-between gap-3 text-xs sm:text-sm font-semibold animate-in fade-in slide-in-from-bottom-4">
          <div className="flex items-center gap-2 min-w-0">
            <CheckCircle2 className="w-4 h-4 text-emerald-400 dark:text-emerald-600 shrink-0" />
            <span className="truncate">{statusMessage}</span>
          </div>
          <button onClick={clearStatusMessage} className="text-slate-400 hover:text-white dark:hover:text-slate-900">
            <X className="w-4 h-4" />
          </button>
        </div>
      )}

      {/* Navigation Drawer */}
      <NavigationDrawer
        isOpen={isDrawerOpen}
        onClose={() => setIsDrawerOpen(false)}
        onOpenAdminProfile={() => setIsAdminProfileModalOpen(true)}
        onOpenRanking={() => setIsRankingModalOpen(true)}
        onOpenMonthlyAnalytics={() => setIsMonthlyAnalyticsModalOpen(true)}
        onOpenRecycleBin={() => setIsRecycleBinModalOpen(true)}
        isDarkMode={isDarkMode}
        onToggleDarkMode={handleToggleDarkMode}
        onLogout={handleLogout}
      />

      {/* Modals & Dialogs */}
      <AddExpertModal
        isOpen={isAddExpertModalOpen}
        onClose={() => {
          setIsAddExpertModalOpen(false);
          setExpertToEdit(null);
        }}
        initialExpert={expertToEdit}
        availableCategories={categoryNames}
        onAddNewCategory={(name) => addNewCategory(name)}
        onSave={(data) => {
          if (expertToEdit) {
            updateExpert({ ...expertToEdit, ...data });
          } else {
            const saved = saveNewExpert(data);
            setWelcomeExpert(saved);
          }
        }}
      />

      <WhatsAppLeadParserModal
        isOpen={isWhatsAppParserOpen}
        onClose={() => setIsWhatsAppParserOpen(false)}
        onParseText={(text) => {
          const { parseAndFillFromWhatsAppText } = useDispatchContext();
          parseAndFillFromWhatsAppText(text);
        }}
      />

      <SaveChoiceModal
        job={saveChoiceJob}
        onFindNearest={(job) => {
          setSaveChoiceJob(null);
          openFindNearestExperts(job);
        }}
        onAssignLater={() => {
          setSaveChoiceJob(null);
          selectCustomerSubTab('ORDERS');
          selectOrderStatusTab('PENDING');
        }}
      />

      {activeJobForNearestExperts && (
        <NearestExpertsModal
          job={activeJobForNearestExperts}
          rankedExperts={getNearestExpertsForJob(activeJobForNearestExperts)}
          onClose={closeFindNearestExperts}
          onAssign={(ranked) => {
            const targetJob = activeJobForNearestExperts;
            assignExpertToJob(targetJob, ranked);
            // Prompt to send WhatsApp to expert (does NOT open WhatsApp automatically)
            setAssignExpertWhatsApp({ job: targetJob, ranked });
          }}
        />
      )}

      {/* Confirmation to send WhatsApp to assigned expert */}
      <AssignExpertWhatsAppModal
        data={assignExpertWhatsApp}
        onSendWhatsApp={() => {
          if (assignExpertWhatsApp) {
            const { job, ranked } = assignExpertWhatsApp;
            const msg = WhatsAppHelper.createDispatchMessage(ranked.expert, job);
            WhatsAppHelper.openWhatsAppDirectMessage(ranked.expert.phone, msg);
            updateExpertNotified(job.id, true);
            const estTime = WhatsAppHelper.calculateEstimatedArrivalTimeWithBuffer(ranked.distanceKm);
            setAssignExpertWhatsApp(null);
            setAssignCustomerWhatsApp({ job, ranked, estTime });
          }
        }}
        onLater={() => {
          if (assignExpertWhatsApp) {
            const { job, ranked } = assignExpertWhatsApp;
            updateExpertNotified(job.id, false);
            markMessageLaterDismissed(job.id);
            const estTime = WhatsAppHelper.calculateEstimatedArrivalTimeWithBuffer(ranked.distanceKm);
            setAssignExpertWhatsApp(null);
            setAssignCustomerWhatsApp({ job, ranked, estTime });
          }
        }}
      />

      {/* Customer WhatsApp notification on assignment with +30 min buffer */}
      <AssignCustomerWhatsAppModal
        data={assignCustomerWhatsApp}
        onSendWhatsApp={() => {
          if (assignCustomerWhatsApp) {
            const { job, ranked, estTime } = assignCustomerWhatsApp;
            const msg = WhatsAppHelper.createCustomerAssignmentNotificationMessage(
              job.customerName,
              ranked.expert.name,
              ranked.expert.phone,
              job.serviceType,
              estTime
            );
            WhatsAppHelper.openWhatsAppDirectMessage(job.customerPhone, msg);
            updateCustomerNotifiedOnAssign(job.id, true);
            setAssignCustomerWhatsApp(null);
          }
        }}
        onLater={() => {
          if (assignCustomerWhatsApp) {
            updateCustomerNotifiedOnAssign(assignCustomerWhatsApp.job.id, false);
            setAssignCustomerWhatsApp(null);
          }
        }}
      />

      {/* Customer WhatsApp notification on order completion */}
      <CustomerCompletionWhatsAppModal
        job={completionCustomerWhatsApp}
        onSendWhatsApp={() => {
          if (completionCustomerWhatsApp) {
            const msg = WhatsAppHelper.createCompletionCustomerMessage(
              completionCustomerWhatsApp.customerName
            );
            WhatsAppHelper.openWhatsAppDirectMessage(completionCustomerWhatsApp.customerPhone, msg);
            updateCustomerNotifiedOnCompletion(completionCustomerWhatsApp.id, true);
            const target = completionCustomerWhatsApp;
            setCompletionCustomerWhatsApp(null);
            setReviewTarget({ job: target, isCompleted: true });
          }
        }}
        onLater={() => {
          if (completionCustomerWhatsApp) {
            updateCustomerNotifiedOnCompletion(completionCustomerWhatsApp.id, false);
            const target = completionCustomerWhatsApp;
            setCompletionCustomerWhatsApp(null);
            setReviewTarget({ job: target, isCompleted: true });
          }
        }}
      />

      <ReviewModal
        target={reviewTarget}
        onClose={() => setReviewTarget(null)}
        onConfirm={(rating, feedback) => {
          if (reviewTarget) {
            completeOrCancelJobWithReview(
              reviewTarget.job,
              reviewTarget.isCompleted,
              rating,
              feedback
            );
            setReviewTarget(null);
          }
        }}
      />

      <CompletedOrderDetailModal
        job={completedDetailJob}
        onClose={() => setCompletedDetailJob(null)}
      />

      <ExpertsRankingModal
        isOpen={isRankingModalOpen}
        onClose={() => setIsRankingModalOpen(false)}
        experts={allExperts}
      />

      <MonthlyAnalyticsModal
        isOpen={isMonthlyAnalyticsModalOpen}
        onClose={() => setIsMonthlyAnalyticsModalOpen(false)}
        jobs={allJobs}
      />

      <ExpertWorkHistoryModal
        expert={expertForWorkHistory}
        allJobs={allJobs}
        onClose={() => setExpertForWorkHistory(null)}
      />

      <RecycleBinModal
        isOpen={isRecycleBinModalOpen}
        onClose={() => setIsRecycleBinModalOpen(false)}
        deletedJobs={deletedJobs}
        deletedExperts={deletedExperts}
        onRestoreJob={restoreJobFromRecycleBin}
        onDeleteJobPermanently={deleteJobPermanently}
        onRestoreExpert={restoreExpertFromRecycleBin}
        onDeleteExpertPermanently={deleteExpertPermanently}
        onEmptyRecycleBin={emptyRecycleBin}
      />

      <EditAdminProfileModal
        isOpen={isAdminProfileModalOpen}
        onClose={() => setIsAdminProfileModalOpen(false)}
        onSaved={() => {
          // forces re-render if profile changed
        }}
      />

      <OrderLongPressMenuModal
        job={longPressJob}
        onClose={() => setLongPressJob(null)}
        onEditDetails={(job) => setEditingCustomerJob(job)}
        onUnassignExpert={(job) => unassignExpert(job)}
      />

      <EditCustomerOrderModal
        job={editingCustomerJob}
        availableCategories={categoryNames}
        onClose={() => setEditingCustomerJob(null)}
        onSave={(updated) => updateJob(updated)}
      />

      <UniversalDeleteModal
        isOpen={!!deleteTarget}
        title={deleteTarget?.title || 'Confirm Deletion'}
        message={deleteTarget?.message || 'Are you sure you want to proceed?'}
        onConfirm={() => {
          if (!deleteTarget) return;
          if (deleteTarget.type === 'job') {
            deleteJob(deleteTarget.item);
          } else if (deleteTarget.type === 'expert') {
            deleteExpert(deleteTarget.item);
          } else if (deleteTarget.type === 'category') {
            deleteCategory(deleteTarget.item);
          }
          setDeleteTarget(null);
        }}
        onClose={() => setDeleteTarget(null)}
      />

      <CustomDateRangeModal
        isOpen={isCustomDateModalOpen}
        onClose={() => setIsCustomDateModalOpen(false)}
        onApplyRange={(start, end) => {
          setCustomStartDate(start);
          setCustomEndDate(end);
        }}
        currentStartDate={customStartDate}
        currentEndDate={customEndDate}
      />

      <SendWelcomeExpertModal
        expert={welcomeExpert}
        onClose={() => setWelcomeExpert(null)}
        onSend={(msg) => {
          if (welcomeExpert) {
            WhatsAppHelper.openWhatsAppDirectMessage(welcomeExpert.phone, msg);
            updateWelcomeMessageSent(welcomeExpert.id, true);
            setWelcomeExpert(null);
          }
        }}
      />

      <AddNewCategoryModal
        isOpen={isAddNewCategoryModalOpen}
        onClose={() => setIsAddNewCategoryModalOpen(false)}
        onAddCategory={(name) => addNewCategory(name)}
      />
    </div>
  );
};
