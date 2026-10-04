import React from 'react';
import {
  X,
  Edit2,
  FileText,
  ClipboardList,
  Wrench,
  Trophy,
  BarChart3,
  CloudUpload,
  FolderInput,
  Trash2,
  Moon,
  Sun,
  LogOut,
} from 'lucide-react';
import { useDispatchContext } from '../context/DispatchContext';
import { SessionManager } from '../utils/sessionManager';
import { BackupRestoreHelper } from '../utils/backupRestoreHelper';

interface NavigationDrawerProps {
  isOpen: boolean;
  onClose: () => void;
  onOpenAdminProfile: () => void;
  onOpenRanking: () => void;
  onOpenMonthlyAnalytics: () => void;
  onOpenRecycleBin: () => void;
  isDarkMode: boolean;
  onToggleDarkMode: () => void;
  onLogout: () => void;
}

export const NavigationDrawer: React.FC<NavigationDrawerProps> = ({
  isOpen,
  onClose,
  onOpenAdminProfile,
  onOpenRanking,
  onOpenMonthlyAnalytics,
  onOpenRecycleBin,
  isDarkMode,
  onToggleDarkMode,
  onLogout,
}) => {
  const {
    allJobs,
    allExperts,
    allCategories,
    deletedJobs,
    deletedExperts,
    currentMainTab,
    currentCustomerSubTab,
    selectMainTab,
    selectCustomerSubTab,
    restoreBackupData,
    setStatusMessage,
  } = useDispatchContext();

  const session = SessionManager.getCurrentSession();
  const totalDeleted = deletedJobs.length + deletedExperts.length;

  const handleBackupToDrive = () => {
    BackupRestoreHelper.exportAndDownloadBackup(allJobs, allExperts, allCategories);
    setStatusMessage('Backup file downloaded! You can upload it to Google Drive.');
    onClose();
  };

  const handleRestoreFromFile = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;
    BackupRestoreHelper.parseBackupJson(file)
      .then((data) => {
        restoreBackupData(data.jobs, data.experts, data.categories);
        onClose();
      })
      .catch((err) => {
        setStatusMessage(`Restore error: ${err.message || 'Invalid backup JSON file'}`);
      });
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-50 flex">
      {/* Backdrop */}
      <div
        className="fixed inset-0 bg-slate-900/60 backdrop-blur-xs transition-opacity"
        onClick={onClose}
      />

      {/* Drawer Content */}
      <aside className="relative flex flex-col w-[320px] max-w-[85vw] h-full bg-white dark:bg-slate-900 text-slate-800 dark:text-slate-100 shadow-2xl z-10 overflow-y-auto">
        {/* Header */}
        <div className="p-5 border-b border-slate-100 dark:border-slate-800 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <img
              src="/hurifix_logo.svg"
              alt="Hurifix"
              className="w-12 h-12 rounded-xl object-contain shadow-xs border border-amber-200 dark:border-slate-700 bg-white"
            />
            <div>
              <h1 className="text-xl font-extrabold tracking-tight text-slate-900 dark:text-white">
                Hurifix
              </h1>
              <p className="text-xs font-semibold text-amber-600 dark:text-amber-400">
                Many Problems | One Solution
              </p>
            </div>
          </div>
          <button
            onClick={onClose}
            className="p-1.5 rounded-lg text-slate-400 hover:text-slate-600 dark:hover:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Admin Profile Card */}
        <div className="p-4 mx-4 mt-4 rounded-xl bg-blue-50/70 dark:bg-blue-950/40 border border-blue-200/80 dark:border-blue-900/60">
          <div className="flex items-center gap-3">
            <div className="w-11 h-11 rounded-full bg-blue-600 text-white flex items-center justify-center font-bold text-lg overflow-hidden shrink-0 border-2 border-white dark:border-slate-800 shadow-xs">
              {session.userPhotoUri ? (
                <img
                  src={session.userPhotoUri}
                  alt={session.userName}
                  className="w-full h-full object-cover"
                />
              ) : (
                session.userName.charAt(0).toUpperCase()
              )}
            </div>
            <div className="min-w-0 flex-1">
              <h3 className="font-bold text-sm text-slate-900 dark:text-white truncate">
                {session.userName}
              </h3>
              <p className="text-xs text-slate-500 dark:text-slate-400">
                📞 +91 {session.userPhone || 'Not set'}
              </p>
            </div>
          </div>

          <div className="mt-2.5 flex items-center justify-between">
            <span className="inline-block text-[11px] font-bold bg-blue-600 text-white px-2 py-0.5 rounded-md">
              👑 {session.userRole}
            </span>
            <button
              onClick={() => {
                onClose();
                onOpenAdminProfile();
              }}
              className="inline-flex items-center gap-1 text-xs font-semibold text-blue-600 dark:text-blue-400 hover:underline"
            >
              <Edit2 className="w-3 h-3" />
              Edit
            </button>
          </div>
        </div>

        {/* Navigation Sections */}
        <div className="flex-1 px-4 py-4 space-y-5">
          {/* Section 1 */}
          <div>
            <div className="px-2 mb-1.5 text-[11px] font-bold tracking-wider text-blue-600 dark:text-blue-400 uppercase">
              OPERATIONS & ORDERS
            </div>
            <nav className="space-y-1">
              <button
                onClick={() => {
                  selectMainTab('CUSTOMER_ORDERS');
                  selectCustomerSubTab('DISPATCH_ORDER');
                  onClose();
                }}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-lg text-sm font-semibold transition-colors ${
                  currentMainTab === 'CUSTOMER_ORDERS' &&
                  currentCustomerSubTab === 'DISPATCH_ORDER'
                    ? 'bg-blue-600 text-white shadow-xs'
                    : 'text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800'
                }`}
              >
                <FileText className="w-4 h-4" />
                Dispatch New Order
              </button>

              <button
                onClick={() => {
                  selectMainTab('CUSTOMER_ORDERS');
                  selectCustomerSubTab('ORDERS');
                  onClose();
                }}
                className={`w-full flex items-center justify-between px-3 py-2.5 rounded-lg text-sm font-semibold transition-colors ${
                  currentMainTab === 'CUSTOMER_ORDERS' && currentCustomerSubTab === 'ORDERS'
                    ? 'bg-blue-600 text-white shadow-xs'
                    : 'text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800'
                }`}
              >
                <span className="flex items-center gap-3">
                  <ClipboardList className="w-4 h-4" />
                  Customer Orders
                </span>
                <span
                  className={`text-xs px-2 py-0.5 rounded-full ${
                    currentMainTab === 'CUSTOMER_ORDERS' && currentCustomerSubTab === 'ORDERS'
                      ? 'bg-blue-700 text-white'
                      : 'bg-slate-100 dark:bg-slate-800 text-slate-600 dark:text-slate-300'
                  }`}
                >
                  {allJobs.length}
                </span>
              </button>

              <button
                onClick={() => {
                  selectMainTab('EXPERTS');
                  onClose();
                }}
                className={`w-full flex items-center justify-between px-3 py-2.5 rounded-lg text-sm font-semibold transition-colors ${
                  currentMainTab === 'EXPERTS'
                    ? 'bg-blue-600 text-white shadow-xs'
                    : 'text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800'
                }`}
              >
                <span className="flex items-center gap-3">
                  <Wrench className="w-4 h-4" />
                  Manage Experts
                </span>
                <span
                  className={`text-xs px-2 py-0.5 rounded-full ${
                    currentMainTab === 'EXPERTS'
                      ? 'bg-blue-700 text-white'
                      : 'bg-slate-100 dark:bg-slate-800 text-slate-600 dark:text-slate-300'
                  }`}
                >
                  {allExperts.length}
                </span>
              </button>
            </nav>
          </div>

          {/* Section 2 */}
          <div className="pt-2 border-t border-slate-100 dark:border-slate-800">
            <div className="px-2 mb-1.5 text-[11px] font-bold tracking-wider text-blue-600 dark:text-blue-400 uppercase">
              PERFORMANCE & INSIGHTS
            </div>
            <nav className="space-y-1">
              <button
                onClick={() => {
                  onClose();
                  onOpenRanking();
                }}
                className="w-full flex items-center gap-3 px-3 py-2.5 rounded-lg text-sm font-semibold text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800"
              >
                <Trophy className="w-4 h-4 text-amber-500" />
                Experts Leaderboard
              </button>

              <button
                onClick={() => {
                  onClose();
                  onOpenMonthlyAnalytics();
                }}
                className="w-full flex items-center gap-3 px-3 py-2.5 rounded-lg text-sm font-semibold text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800"
              >
                <BarChart3 className="w-4 h-4 text-emerald-500" />
                Monthly Reports & Analytics
              </button>
            </nav>
          </div>

          {/* Section 3 */}
          <div className="pt-2 border-t border-slate-100 dark:border-slate-800">
            <div className="px-2 mb-1.5 text-[11px] font-bold tracking-wider text-blue-600 dark:text-blue-400 uppercase">
              BACKUP & DATA MANAGEMENT
            </div>
            <nav className="space-y-1">
              <button
                onClick={handleBackupToDrive}
                className="w-full flex items-center gap-3 px-3 py-2.5 rounded-lg text-sm font-semibold text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800"
              >
                <CloudUpload className="w-4 h-4 text-sky-500" />
                Backup to Google Drive / File
              </button>

              <label className="w-full flex items-center gap-3 px-3 py-2.5 rounded-lg text-sm font-semibold text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800 cursor-pointer">
                <FolderInput className="w-4 h-4 text-indigo-500" />
                Restore from Drive / File
                <input
                  type="file"
                  accept=".json,application/json"
                  onChange={handleRestoreFromFile}
                  className="hidden"
                />
              </label>

              <button
                onClick={() => {
                  onClose();
                  onOpenRecycleBin();
                }}
                className="w-full flex items-center justify-between px-3 py-2.5 rounded-lg text-sm font-semibold text-slate-700 dark:text-slate-200 hover:bg-slate-100 dark:hover:bg-slate-800"
              >
                <span className="flex items-center gap-3">
                  <Trash2 className="w-4 h-4 text-rose-500" />
                  Recycle Bin
                </span>
                {totalDeleted > 0 && (
                  <span className="text-xs px-2 py-0.5 rounded-full bg-rose-100 dark:bg-rose-950 text-rose-600 dark:text-rose-400 font-bold">
                    {totalDeleted}
                  </span>
                )}
              </button>
            </nav>
          </div>

          {/* Section 4 */}
          <div className="pt-2 border-t border-slate-100 dark:border-slate-800">
            <div className="px-2 mb-1.5 text-[11px] font-bold tracking-wider text-blue-600 dark:text-blue-400 uppercase">
              PREFERENCES & SETTINGS
            </div>
            <div className="flex items-center justify-between px-3 py-2.5 rounded-lg bg-slate-50 dark:bg-slate-800/60 border border-slate-200 dark:border-slate-700/60">
              <span className="flex items-center gap-2.5 text-sm font-medium">
                {isDarkMode ? (
                  <Moon className="w-4 h-4 text-amber-400" />
                ) : (
                  <Sun className="w-4 h-4 text-amber-500" />
                )}
                {isDarkMode ? 'Dark Theme' : 'Light Theme'}
              </span>
              <button
                type="button"
                onClick={onToggleDarkMode}
                className={`relative inline-flex h-6 w-11 shrink-0 cursor-pointer rounded-full border-2 border-transparent transition-colors duration-200 ease-in-out focus:outline-hidden ${
                  isDarkMode ? 'bg-blue-600' : 'bg-slate-300'
                }`}
              >
                <span
                  className={`pointer-events-none inline-block h-5 w-5 transform rounded-full bg-white shadow-sm ring-0 transition duration-200 ease-in-out ${
                    isDarkMode ? 'translate-x-5' : 'translate-x-0'
                  }`}
                />
              </button>
            </div>
          </div>
        </div>

        {/* Footer with Logout */}
        <div className="p-4 border-t border-slate-100 dark:border-slate-800">
          <button
            onClick={() => {
              onClose();
              onLogout();
            }}
            className="w-full flex items-center justify-center gap-2 py-2.5 px-4 rounded-xl text-sm font-bold bg-rose-50 dark:bg-rose-950/50 text-rose-600 dark:text-rose-400 hover:bg-rose-100 dark:hover:bg-rose-900/60 transition-colors"
          >
            <LogOut className="w-4 h-4" />
            Log Out
          </button>
        </div>
      </aside>
    </div>
  );
};
