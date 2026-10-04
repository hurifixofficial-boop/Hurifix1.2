import React, { createContext, useContext, useState, useEffect, ReactNode } from 'react';
import {
  CustomerJob,
  Expert,
  ExpertCategory,
  RankedExpert,
  CustomerFormState,
  MainTabType,
  CustomerSubTabType,
  OrderStatusTabType,
  JobStatusType,
} from '../types';
import { LocationHelper } from '../utils/locationHelper';
import { SessionManager } from '../utils/sessionManager';

interface DispatchContextType {
  // State
  allJobs: CustomerJob[];
  allExperts: Expert[];
  allCategories: ExpertCategory[];
  deletedJobs: CustomerJob[];
  deletedExperts: Expert[];
  currentMainTab: MainTabType;
  currentCustomerSubTab: CustomerSubTabType;
  currentOrderStatusTab: OrderStatusTabType;
  customerForm: CustomerFormState;
  statusMessage: string | null;
  activeJobForNearestExperts: CustomerJob | null;
  isRefreshing: boolean;

  // Actions
  selectMainTab: (tab: MainTabType) => void;
  selectCustomerSubTab: (subTab: CustomerSubTabType) => void;
  selectOrderStatusTab: (statusTab: OrderStatusTabType) => void;
  clearStatusMessage: () => void;
  setStatusMessage: (msg: string) => void;
  refreshAllData: () => void;

  // Form actions
  updateFormName: (name: string) => void;
  updateFormPhone: (phone: string) => void;
  updateFormServiceType: (service: string) => void;
  updateFormIssue: (issue: string) => void;
  updateFormAddress: (address: string) => void;
  updateFormLocationInput: (input: string) => void;
  setFormCoordinates: (lat: number, lng: number, address?: string) => void;
  fetchCurrentGps: () => Promise<void>;
  parseAndFillFromWhatsAppText: (text: string) => void;
  resetCustomerForm: () => void;

  // Job operations
  saveCustomerOrder: (status?: JobStatusType) => CustomerJob;
  openFindNearestExperts: (job: CustomerJob) => void;
  closeFindNearestExperts: () => void;
  getNearestExpertsForJob: (job: CustomerJob) => RankedExpert[];
  assignExpertToJob: (job: CustomerJob, ranked: RankedExpert) => void;
  unassignExpert: (job: CustomerJob) => void;
  updateJob: (job: CustomerJob) => void;
  completeOrCancelJobWithReview: (
    job: CustomerJob,
    isCompleted: boolean,
    rating: number,
    feedback?: string | null
  ) => void;
  updateExpertNotified: (jobId: number, sent: boolean) => void;
  updateCustomerNotifiedOnAssign: (jobId: number, sent: boolean) => void;
  updateCustomerNotifiedOnCompletion: (jobId: number, sent: boolean) => void;
  markMessageLaterDismissed: (jobId: number) => void;
  deleteJob: (job: CustomerJob) => void;
  restoreJobFromRecycleBin: (jobId: number) => void;
  deleteJobPermanently: (jobId: number) => void;

  // Expert operations
  saveNewExpert: (expert: Omit<Expert, 'id' | 'createdAt' | 'isDeleted'>) => Expert;
  updateExpert: (expert: Expert) => void;
  deleteExpert: (expert: Expert) => void;
  restoreExpertFromRecycleBin: (expertId: number) => void;
  deleteExpertPermanently: (expertId: number) => void;
  updateWelcomeMessageSent: (expertId: number, sent: boolean) => void;

  // Category operations
  addNewCategory: (name: string) => void;
  deleteCategory: (category: ExpertCategory) => void;

  // Recycle bin operations
  emptyRecycleBin: () => void;

  // Backup & Restore
  restoreBackupData: (jobs: CustomerJob[], experts: Expert[], categories: ExpertCategory[]) => void;
  onUserLoggedIn: (phone: string) => void;
  onUserLoggedOut: () => void;
}

const DispatchContext = createContext<DispatchContextType | undefined>(undefined);

const INITIAL_FORM: CustomerFormState = {
  name: '',
  phone: '',
  serviceType: '',
  issueDescription: '',
  address: '',
  latitude: 28.5708,
  longitude: 77.3261,
  hasValidLocation: true,
  rawLocationInput: '28.5708, 77.3261',
};

const DEFAULT_CATEGORIES: ExpertCategory[] = [
  { id: 1, name: 'Electrician', isDefault: true, createdAt: Date.now() },
  { id: 2, name: 'Plumber', isDefault: true, createdAt: Date.now() },
];

const SAMPLE_EXPERTS: Expert[] = [
  {
    id: 101,
    name: 'Rajesh Kumar',
    phone: '9810123456',
    category: 'Electrician',
    address: 'Sector 18, Noida, Uttar Pradesh',
    latitude: 28.5705,
    longitude: 77.3255,
    isAvailable: true,
    rating: 4.9,
    ratingSum: 49,
    totalRatingsCount: 10,
    completedJobsCount: 14,
    cancelledJobsCount: 1,
    isWelcomeMessageSent: true,
    isDeleted: false,
    createdAt: Date.now() - 15 * 86400000,
  },
  {
    id: 102,
    name: 'Mohit Sharma',
    phone: '9820234567',
    category: 'Plumber',
    address: 'Atta Market, Sector 27, Noida',
    latitude: 28.5742,
    longitude: 77.3312,
    isAvailable: true,
    rating: 4.8,
    ratingSum: 38.4,
    totalRatingsCount: 8,
    completedJobsCount: 11,
    cancelledJobsCount: 0,
    isWelcomeMessageSent: true,
    isDeleted: false,
    createdAt: Date.now() - 20 * 86400000,
  },
  {
    id: 103,
    name: 'Amit Verma',
    phone: '9830345678',
    category: 'AC Repair',
    address: 'Sector 62, Noida, Uttar Pradesh',
    latitude: 28.628,
    longitude: 77.3649,
    isAvailable: true,
    rating: 4.7,
    ratingSum: 33,
    totalRatingsCount: 7,
    completedJobsCount: 9,
    cancelledJobsCount: 2,
    isWelcomeMessageSent: true,
    isDeleted: false,
    createdAt: Date.now() - 25 * 86400000,
  },
  {
    id: 104,
    name: 'Suresh Yadav',
    phone: '9840456789',
    category: 'Electrician',
    address: 'Sector 50, Noida',
    latitude: 28.578,
    longitude: 77.371,
    isAvailable: true,
    rating: 5.0,
    ratingSum: 25,
    totalRatingsCount: 5,
    completedJobsCount: 8,
    cancelledJobsCount: 0,
    isWelcomeMessageSent: true,
    isDeleted: false,
    createdAt: Date.now() - 10 * 86400000,
  },
];

const SAMPLE_JOBS: CustomerJob[] = [
  {
    id: 1001,
    customerName: 'Anil Gupta',
    customerPhone: '9871122334',
    serviceType: 'Electrician',
    issueDescription: 'Main MCB tripping repeatedly in kitchen and bedrooms.',
    address: 'Tower 4, Flat 502, Express View Apartments, Sector 18, Noida',
    latitude: 28.5718,
    longitude: 77.3275,
    status: 'PROCESSING',
    assignedExpertId: 101,
    assignedExpertName: 'Rajesh Kumar',
    assignedExpertPhone: '9810123456',
    distanceKmAtDispatch: 0.3,
    createdAt: Date.now() - 45 * 60000,
    isExpertNotified: true,
    isCustomerNotifiedOnAssign: true,
    isCustomerNotifiedOnCompletion: false,
    isDeleted: false,
  },
  {
    id: 1002,
    customerName: 'Pooja Mehra',
    customerPhone: '9899334455',
    serviceType: 'Plumber',
    issueDescription: 'Bathroom tap valve broken and heavy leakage from supply pipe.',
    address: 'House No 42, Sector 26, Noida',
    latitude: 28.575,
    longitude: 77.329,
    status: 'PENDING',
    createdAt: Date.now() - 20 * 60000,
    isExpertNotified: false,
    isCustomerNotifiedOnAssign: false,
    isCustomerNotifiedOnCompletion: false,
    isDeleted: false,
  },
  {
    id: 1003,
    customerName: 'Vikram Malhotra',
    customerPhone: '9811556677',
    serviceType: 'AC Repair',
    issueDescription: 'Split AC not blowing cold air, outdoor compressor humming.',
    address: 'Sector 55, Block B, Noida',
    latitude: 28.601,
    longitude: 77.352,
    status: 'COMPLETED',
    assignedExpertId: 103,
    assignedExpertName: 'Amit Verma',
    assignedExpertPhone: '9830345678',
    distanceKmAtDispatch: 3.2,
    ratingGiven: 5,
    reviewFeedback: 'Very professional, diagnosed gas leak and resolved quickly.',
    createdAt: Date.now() - 2 * 86400000,
    completedAt: Date.now() - 2 * 86400000 + 48 * 60000,
    isExpertNotified: true,
    isCustomerNotifiedOnAssign: true,
    isCustomerNotifiedOnCompletion: true,
    isDeleted: false,
  },
  {
    id: 1004,
    customerName: 'Deepak Joshi',
    customerPhone: '9711667788',
    serviceType: 'Electrician',
    issueDescription: 'Ceiling fan regulator sparking and not turning.',
    address: 'Sector 29, Brahmputra Market area, Noida',
    latitude: 28.568,
    longitude: 77.332,
    status: 'CANCELLED',
    assignedExpertId: 104,
    assignedExpertName: 'Suresh Yadav',
    assignedExpertPhone: '9840456789',
    distanceKmAtDispatch: 1.8,
    ratingGiven: 3,
    reviewFeedback: 'Customer rescheduled for next week due to personal travel.',
    createdAt: Date.now() - 4 * 86400000,
    completedAt: Date.now() - 4 * 86400000 + 35 * 60000,
    isExpertNotified: true,
    isCustomerNotifiedOnAssign: true,
    isCustomerNotifiedOnCompletion: false,
    isDeleted: false,
  },
];

export const DispatchProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [currentUserPhone, setCurrentUserPhone] = useState<string>(() => SessionManager.getUserPhone());

  const getStorageKey = (key: string) => {
    const phone = currentUserPhone || 'default';
    return `hurifix_${key}_${phone}`;
  };

  const [allJobs, setAllJobs] = useState<CustomerJob[]>(() => {
    const raw = localStorage.getItem(getStorageKey('jobs'));
    if (raw) {
      try {
        return JSON.parse(raw);
      } catch {}
    }
    return SAMPLE_JOBS;
  });

  const [allExperts, setAllExperts] = useState<Expert[]>(() => {
    const raw = localStorage.getItem(getStorageKey('experts'));
    if (raw) {
      try {
        return JSON.parse(raw);
      } catch {}
    }
    return SAMPLE_EXPERTS;
  });

  const [allCategories, setAllCategories] = useState<ExpertCategory[]>(() => {
    const raw = localStorage.getItem(getStorageKey('categories'));
    if (raw) {
      try {
        return JSON.parse(raw);
      } catch {}
    }
    return DEFAULT_CATEGORIES;
  });

  const [deletedJobs, setDeletedJobs] = useState<CustomerJob[]>(() => {
    const raw = localStorage.getItem(getStorageKey('deleted_jobs'));
    if (raw) {
      try {
        return JSON.parse(raw);
      } catch {}
    }
    return [];
  });

  const [deletedExperts, setDeletedExperts] = useState<Expert[]>(() => {
    const raw = localStorage.getItem(getStorageKey('deleted_experts'));
    if (raw) {
      try {
        return JSON.parse(raw);
      } catch {}
    }
    return [];
  });

  const [currentMainTab, setCurrentMainTab] = useState<MainTabType>('CUSTOMER_ORDERS');
  const [currentCustomerSubTab, setCurrentCustomerSubTab] = useState<CustomerSubTabType>('DISPATCH_ORDER');
  const [currentOrderStatusTab, setCurrentOrderStatusTab] = useState<OrderStatusTabType>('PENDING');
  const [customerForm, setCustomerForm] = useState<CustomerFormState>(INITIAL_FORM);
  const [statusMessage, setStatusMessage] = useState<string | null>(null);
  const [activeJobForNearestExperts, setActiveJobForNearestExperts] = useState<CustomerJob | null>(null);
  const [isRefreshing, setIsRefreshing] = useState(false);

  // Sync to storage
  useEffect(() => {
    localStorage.setItem(getStorageKey('jobs'), JSON.stringify(allJobs));
  }, [allJobs, currentUserPhone]);

  useEffect(() => {
    localStorage.setItem(getStorageKey('experts'), JSON.stringify(allExperts));
  }, [allExperts, currentUserPhone]);

  useEffect(() => {
    localStorage.setItem(getStorageKey('categories'), JSON.stringify(allCategories));
  }, [allCategories, currentUserPhone]);

  useEffect(() => {
    localStorage.setItem(getStorageKey('deleted_jobs'), JSON.stringify(deletedJobs));
  }, [deletedJobs, currentUserPhone]);

  useEffect(() => {
    localStorage.setItem(getStorageKey('deleted_experts'), JSON.stringify(deletedExperts));
  }, [deletedExperts, currentUserPhone]);

  // Purge recycle bin older than 30 days
  useEffect(() => {
    const thirtyDaysAgo = Date.now() - 30 * 24 * 60 * 60 * 1000;
    setDeletedJobs((prev) => prev.filter((j) => (j.deletedAt || 0) > thirtyDaysAgo));
    setDeletedExperts((prev) => prev.filter((e) => (e.deletedAt || 0) > thirtyDaysAgo));
  }, []);

  const onUserLoggedIn = (phone: string) => {
    const clean = phone.replace(/[^0-9]/g, '');
    setCurrentUserPhone(clean);
    // Reload state for this user
    const rawJobs = localStorage.getItem(`hurifix_jobs_${clean}`);
    const rawExperts = localStorage.getItem(`hurifix_experts_${clean}`);
    const rawCategories = localStorage.getItem(`hurifix_categories_${clean}`);
    const rawDeletedJobs = localStorage.getItem(`hurifix_deleted_jobs_${clean}`);
    const rawDeletedExperts = localStorage.getItem(`hurifix_deleted_experts_${clean}`);

    setAllJobs(rawJobs ? JSON.parse(rawJobs) : SAMPLE_JOBS);
    setAllExperts(rawExperts ? JSON.parse(rawExperts) : SAMPLE_EXPERTS);
    setAllCategories(rawCategories ? JSON.parse(rawCategories) : DEFAULT_CATEGORIES);
    setDeletedJobs(rawDeletedJobs ? JSON.parse(rawDeletedJobs) : []);
    setDeletedExperts(rawDeletedExperts ? JSON.parse(rawDeletedExperts) : []);

    setCustomerForm(INITIAL_FORM);
    setActiveJobForNearestExperts(null);
    setCurrentMainTab('CUSTOMER_ORDERS');
    setCurrentCustomerSubTab('DISPATCH_ORDER');
    setCurrentOrderStatusTab('PENDING');
  };

  const onUserLoggedOut = () => {
    setCurrentUserPhone('');
    setCustomerForm(INITIAL_FORM);
    setActiveJobForNearestExperts(null);
    setCurrentMainTab('CUSTOMER_ORDERS');
    setCurrentCustomerSubTab('DISPATCH_ORDER');
    setCurrentOrderStatusTab('PENDING');
  };

  const refreshAllData = () => {
    setIsRefreshing(true);
    setTimeout(() => {
      setIsRefreshing(false);
      setStatusMessage('Data refreshed!');
    }, 600);
  };

  const clearStatusMessage = () => setStatusMessage(null);

  // Form handling
  const updateFormName = (name: string) => setCustomerForm((p) => ({ ...p, name }));
  const updateFormPhone = (phone: string) => {
    const digits = phone.replace(/[^0-9]/g, '').slice(0, 10);
    setCustomerForm((p) => ({ ...p, phone: digits }));
  };
  const updateFormServiceType = (serviceType: string) =>
    setCustomerForm((p) => ({ ...p, serviceType }));
  const updateFormIssue = (issueDescription: string) =>
    setCustomerForm((p) => ({ ...p, issueDescription }));
  const updateFormAddress = (address: string) =>
    setCustomerForm((p) => ({ ...p, address }));
  const updateFormLocationInput = (input: string) => {
    const parsed = LocationHelper.parseCoordinatesFromText(input);
    if (parsed) {
      setCustomerForm((p) => ({
        ...p,
        rawLocationInput: input,
        latitude: parsed[0],
        longitude: parsed[1],
        hasValidLocation: true,
      }));
    } else {
      setCustomerForm((p) => ({
        ...p,
        rawLocationInput: input,
        hasValidLocation: false,
      }));
    }
  };
  const setFormCoordinates = (lat: number, lng: number, address?: string) => {
    setCustomerForm((p) => ({
      ...p,
      latitude: lat,
      longitude: lng,
      rawLocationInput: `${lat}, ${lng}`,
      hasValidLocation: true,
      address: address ?? p.address,
    }));
  };

  const fetchCurrentGps = async () => {
    try {
      const loc = await LocationHelper.fetchCurrentGps();
      setFormCoordinates(loc.latitude, loc.longitude);
      setStatusMessage(`GPS Location set: ${loc.latitude.toFixed(4)}, ${loc.longitude.toFixed(4)}`);
    } catch (e: any) {
      setStatusMessage(e.message || 'GPS Error');
    }
  };

  const parseAndFillFromWhatsAppText = (rawText: string) => {
    let detectedName = '';
    let detectedPhone = '';
    let detectedService = '';
    let detectedIssue = '';
    let detectedAddress = '';

    const phoneMatch = rawText.match(/(?:\+91|91|0)?([6-9]\d{9})/);
    if (phoneMatch) {
      detectedPhone = phoneMatch[1];
    }

    const parsedCoords = LocationHelper.parseCoordinatesFromText(rawText);
    const lower = rawText.toLowerCase();

    if (lower.includes('ac') || lower.includes('air conditioner') || lower.includes('cooling')) {
      detectedService = 'AC Service & Repair';
    } else if (
      lower.includes('fan') ||
      lower.includes('switch') ||
      lower.includes('electric') ||
      lower.includes('wiring') ||
      lower.includes('light')
    ) {
      detectedService = 'Electrician';
    } else if (lower.includes('fridge') || lower.includes('refrigerator')) {
      detectedService = 'Refrigerator Repair';
    } else if (
      lower.includes('plumb') ||
      lower.includes('tap') ||
      lower.includes('pipe') ||
      lower.includes('leak') ||
      lower.includes('tank')
    ) {
      detectedService = 'Plumber';
    } else if (lower.includes('wash') || lower.includes('machine')) {
      detectedService = 'Washing Machine Repair';
    }

    const lines = rawText.split('\n').map((l) => l.trim()).filter(Boolean);
    for (const line of lines) {
      const lineLower = line.toLowerCase();
      if (lineLower.startsWith('name:') || lineLower.startsWith('customer:')) {
        detectedName = line.substring(line.indexOf(':') + 1).trim();
      } else if (
        lineLower.startsWith('phone:') ||
        lineLower.startsWith('mobile:') ||
        lineLower.startsWith('number:')
      ) {
        if (!detectedPhone) {
          detectedPhone = line.substring(line.indexOf(':') + 1).replace(/[^0-9]/g, '');
        }
      } else if (lineLower.startsWith('address:') || lineLower.startsWith('location:')) {
        detectedAddress = line.substring(line.indexOf(':') + 1).trim();
      } else if (
        lineLower.startsWith('problem:') ||
        lineLower.startsWith('issue:') ||
        lineLower.startsWith('work:')
      ) {
        detectedIssue = line.substring(line.indexOf(':') + 1).trim();
      }
    }

    if (!detectedIssue) {
      detectedIssue = rawText.slice(0, 120);
    }

    setCustomerForm((p) => ({
      name: detectedName || p.name,
      phone: detectedPhone || p.phone,
      serviceType: detectedService || p.serviceType,
      issueDescription: detectedIssue,
      address: detectedAddress || p.address,
      latitude: parsedCoords ? parsedCoords[0] : p.latitude,
      longitude: parsedCoords ? parsedCoords[1] : p.longitude,
      rawLocationInput: parsedCoords
        ? `${parsedCoords[0]}, ${parsedCoords[1]}`
        : p.rawLocationInput,
      hasValidLocation: true,
    }));

    setStatusMessage('WhatsApp lead parsed successfully!');
  };

  const resetCustomerForm = () => setCustomerForm(INITIAL_FORM);

  // Job operations
  const saveCustomerOrder = (status: JobStatusType = 'PENDING'): CustomerJob => {
    const newJob: CustomerJob = {
      id: Date.now(),
      customerName: customerForm.name.trim() || 'Customer',
      customerPhone: customerForm.phone.trim(),
      serviceType: customerForm.serviceType.trim() || 'General Repair',
      issueDescription: customerForm.issueDescription.trim() || 'Service requested',
      address: customerForm.address.trim() || 'Address not specified',
      latitude: customerForm.latitude,
      longitude: customerForm.longitude,
      status,
      createdAt: Date.now(),
      isExpertNotified: false,
      isCustomerNotifiedOnAssign: false,
      isCustomerNotifiedOnCompletion: false,
      isDeleted: false,
    };

    setAllJobs((prev) => [newJob, ...prev]);
    resetCustomerForm();
    return newJob;
  };

  const openFindNearestExperts = (job: CustomerJob) => setActiveJobForNearestExperts(job);
  const closeFindNearestExperts = () => setActiveJobForNearestExperts(null);

  const getNearestExpertsForJob = (job: CustomerJob): RankedExpert[] => {
    return allExperts
      .map((expert) => {
        const distanceKm = LocationHelper.calculateDistanceKm(
          job.latitude,
          job.longitude,
          expert.latitude,
          expert.longitude
        );
        const travelTimeMinutes = LocationHelper.estimateTravelTimeMinutes(distanceKm);
        return { expert, distanceKm, travelTimeMinutes };
      })
      .sort((a, b) => a.distanceKm - b.distanceKm);
  };

  const assignExpertToJob = (job: CustomerJob, ranked: RankedExpert) => {
    setAllJobs((prev) =>
      prev.map((j) =>
        j.id === job.id
          ? {
              ...j,
              status: 'PROCESSING',
              assignedExpertId: ranked.expert.id,
              assignedExpertName: ranked.expert.name,
              assignedExpertPhone: ranked.expert.phone,
              distanceKmAtDispatch: ranked.distanceKm,
            }
          : j
      )
    );
    setActiveJobForNearestExperts(null);
    setCurrentMainTab('CUSTOMER_ORDERS');
    setCurrentCustomerSubTab('ORDERS');
    setCurrentOrderStatusTab('PROCESSING');
    setStatusMessage(`Expert ${ranked.expert.name} assigned! Moved to Processing.`);
  };

  const unassignExpert = (job: CustomerJob) => {
    setAllJobs((prev) =>
      prev.map((j) =>
        j.id === job.id
          ? {
              ...j,
              status: 'PENDING',
              assignedExpertId: null,
              assignedExpertName: null,
              assignedExpertPhone: null,
              distanceKmAtDispatch: null,
            }
          : j
      )
    );
    setCurrentOrderStatusTab('PENDING');
    setStatusMessage(`Expert unassigned. Order #${job.id} moved back to Pending.`);
  };

  const updateJob = (job: CustomerJob) => {
    setAllJobs((prev) => prev.map((j) => (j.id === job.id ? job : j)));
    setStatusMessage(`Order #${job.id} details updated.`);
  };

  const completeOrCancelJobWithReview = (
    job: CustomerJob,
    isCompleted: boolean,
    rating: number,
    feedback?: string | null
  ) => {
    const newStatus: JobStatusType = isCompleted ? 'COMPLETED' : 'CANCELLED';
    const now = Date.now();

    setAllJobs((prev) =>
      prev.map((j) =>
        j.id === job.id
          ? {
              ...j,
              status: newStatus,
              ratingGiven: rating,
              reviewFeedback: feedback ?? null,
              completedAt: now,
            }
          : j
      )
    );

    // Update expert rating metrics
    if (job.assignedExpertId) {
      setAllExperts((prev) =>
        prev.map((exp) => {
          if (exp.id === job.assignedExpertId) {
            const newCount = exp.totalRatingsCount + 1;
            const newSum = exp.ratingSum + rating;
            const newAvg = Math.round((newSum / newCount) * 10) / 10;
            return {
              ...exp,
              rating: newAvg,
              ratingSum: newSum,
              totalRatingsCount: newCount,
              completedJobsCount: isCompleted ? exp.completedJobsCount + 1 : exp.completedJobsCount,
              cancelledJobsCount: !isCompleted ? exp.cancelledJobsCount + 1 : exp.cancelledJobsCount,
            };
          }
          return exp;
        })
      );
    }

    setCurrentMainTab('CUSTOMER_ORDERS');
    setCurrentCustomerSubTab('ORDERS');
    setCurrentOrderStatusTab(isCompleted ? 'COMPLETED' : 'CANCELLED');
    setStatusMessage(
      isCompleted
        ? `Order #${job.id} marked Completed! Rating recorded.`
        : `Order #${job.id} Cancelled.`
    );
  };

  const updateExpertNotified = (jobId: number, sent: boolean) => {
    setAllJobs((prev) =>
      prev.map((j) => (j.id === jobId ? { ...j, isExpertNotified: sent } : j))
    );
  };

  const updateCustomerNotifiedOnAssign = (jobId: number, sent: boolean) => {
    setAllJobs((prev) =>
      prev.map((j) => (j.id === jobId ? { ...j, isCustomerNotifiedOnAssign: sent } : j))
    );
  };

  const updateCustomerNotifiedOnCompletion = (jobId: number, sent: boolean) => {
    setAllJobs((prev) =>
      prev.map((j) => (j.id === jobId ? { ...j, isCustomerNotifiedOnCompletion: sent } : j))
    );
  };

  const markMessageLaterDismissed = (jobId: number) => {
    setAllJobs((prev) =>
      prev.map((j) => (j.id === jobId ? { ...j, assignMessageLaterDismissedAt: Date.now() } : j))
    );
  };

  const deleteJob = (job: CustomerJob) => {
    const deletedJob: CustomerJob = { ...job, isDeleted: true, deletedAt: Date.now() };
    setAllJobs((prev) => prev.filter((j) => j.id !== job.id));
    setDeletedJobs((prev) => [deletedJob, ...prev]);
    setStatusMessage(`Order #${job.id} moved to Recycle Bin (Kept for 30 days).`);
  };

  const restoreJobFromRecycleBin = (jobId: number) => {
    const target = deletedJobs.find((j) => j.id === jobId);
    if (!target) return;
    const restored: CustomerJob = { ...target, isDeleted: false, deletedAt: null };
    setDeletedJobs((prev) => prev.filter((j) => j.id !== jobId));
    setAllJobs((prev) => [restored, ...prev]);
    setStatusMessage(`Order #${jobId} restored to active orders!`);
  };

  const deleteJobPermanently = (jobId: number) => {
    setDeletedJobs((prev) => prev.filter((j) => j.id !== jobId));
    setStatusMessage(`Order #${jobId} deleted permanently.`);
  };

  // Expert operations
  const saveNewExpert = (expertData: Omit<Expert, 'id' | 'createdAt' | 'isDeleted'>): Expert => {
    const newExpert: Expert = {
      ...expertData,
      id: Date.now(),
      createdAt: Date.now(),
      isDeleted: false,
    };
    setAllExperts((prev) => [newExpert, ...prev]);
    setStatusMessage(`Expert ${newExpert.name} added successfully!`);
    return newExpert;
  };

  const updateExpert = (expert: Expert) => {
    setAllExperts((prev) => prev.map((e) => (e.id === expert.id ? expert : e)));
    setStatusMessage(`Expert ${expert.name} updated!`);
  };

  const deleteExpert = (expert: Expert) => {
    const deletedExp: Expert = { ...expert, isDeleted: true, deletedAt: Date.now() };
    setAllExperts((prev) => prev.filter((e) => e.id !== expert.id));
    setDeletedExperts((prev) => [deletedExp, ...prev]);
    setStatusMessage(`Expert '${expert.name}' moved to Recycle Bin (Kept for 30 days).`);
  };

  const restoreExpertFromRecycleBin = (expertId: number) => {
    const target = deletedExperts.find((e) => e.id === expertId);
    if (!target) return;
    const restored: Expert = { ...target, isDeleted: false, deletedAt: null };
    setDeletedExperts((prev) => prev.filter((e) => e.id !== expertId));
    setAllExperts((prev) => [restored, ...prev]);
    setStatusMessage(`Expert '${restored.name}' restored to active directory!`);
  };

  const deleteExpertPermanently = (expertId: number) => {
    setDeletedExperts((prev) => prev.filter((e) => e.id !== expertId));
    setStatusMessage(`Expert deleted permanently.`);
  };

  const updateWelcomeMessageSent = (expertId: number, sent: boolean) => {
    setAllExperts((prev) =>
      prev.map((e) => (e.id === expertId ? { ...e, isWelcomeMessageSent: sent } : e))
    );
  };

  // Category operations
  const addNewCategory = (name: string) => {
    const trimmed = name.trim();
    if (!trimmed) return;
    if (allCategories.some((c) => c.name.toLowerCase() === trimmed.toLowerCase())) {
      setStatusMessage(`Category '${trimmed}' already exists.`);
      return;
    }
    const newCat: ExpertCategory = {
      id: Date.now(),
      name: trimmed,
      isDefault: false,
      createdAt: Date.now(),
    };
    setAllCategories((prev) => [...prev, newCat]);
    setStatusMessage(`Category '${trimmed}' created!`);
  };

  const deleteCategory = (category: ExpertCategory) => {
    if (category.isDefault || category.name === 'Electrician' || category.name === 'Plumber') {
      setStatusMessage(`Default category cannot be deleted.`);
      return;
    }
    setAllCategories((prev) => prev.filter((c) => c.id !== category.id));
    setStatusMessage(`Category '${category.name}' deleted.`);
  };

  const emptyRecycleBin = () => {
    setDeletedJobs([]);
    setDeletedExperts([]);
    setStatusMessage('Recycle bin emptied!');
  };

  const restoreBackupData = (
    jobs: CustomerJob[],
    experts: Expert[],
    categories: ExpertCategory[]
  ) => {
    if (jobs.length > 0) setAllJobs(jobs);
    if (experts.length > 0) setAllExperts(experts);
    if (categories.length > 0) setAllCategories(categories);
    setStatusMessage(
      `Backup successfully restored! (${jobs.length} orders, ${experts.length} experts)`
    );
  };

  return (
    <DispatchContext.Provider
      value={{
        allJobs,
        allExperts,
        allCategories,
        deletedJobs,
        deletedExperts,
        currentMainTab,
        currentCustomerSubTab,
        currentOrderStatusTab,
        customerForm,
        statusMessage,
        activeJobForNearestExperts,
        isRefreshing,

        selectMainTab: setCurrentMainTab,
        selectCustomerSubTab: setCurrentCustomerSubTab,
        selectOrderStatusTab: setCurrentOrderStatusTab,
        clearStatusMessage,
        setStatusMessage,
        refreshAllData,

        updateFormName,
        updateFormPhone,
        updateFormServiceType,
        updateFormIssue,
        updateFormAddress,
        updateFormLocationInput,
        setFormCoordinates,
        fetchCurrentGps,
        parseAndFillFromWhatsAppText,
        resetCustomerForm,

        saveCustomerOrder,
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
        restoreBackupData,
        onUserLoggedIn,
        onUserLoggedOut,
      }}
    >
      {children}
    </DispatchContext.Provider>
  );
};

export const useDispatchContext = () => {
  const context = useContext(DispatchContext);
  if (!context) {
    throw new Error('useDispatchContext must be used within a DispatchProvider');
  }
  return context;
};
