export type JobStatusType = 'PENDING' | 'PROCESSING' | 'COMPLETED' | 'CANCELLED';

export interface CustomerJob {
  id: number;
  customerName: string;
  customerPhone: string;
  serviceType: string;
  issueDescription: string;
  address: string;
  latitude: number;
  longitude: number;
  status: JobStatusType;
  assignedExpertId?: number | null;
  assignedExpertName?: string | null;
  assignedExpertPhone?: string | null;
  distanceKmAtDispatch?: number | null;
  ratingGiven?: number | null;
  reviewFeedback?: string | null;
  createdAt: number;
  completedAt?: number | null;
  isExpertNotified: boolean;
  isCustomerNotifiedOnAssign: boolean;
  isCustomerNotifiedOnCompletion: boolean;
  assignMessageLaterDismissedAt?: number | null;
  isDeleted: boolean;
  deletedAt?: number | null;
}

export interface Expert {
  id: number;
  name: string;
  phone: string;
  category: string;
  address: string;
  latitude: number;
  longitude: number;
  isAvailable: boolean;
  rating: number;
  ratingSum: number;
  totalRatingsCount: number;
  completedJobsCount: number;
  cancelledJobsCount: number;
  isWelcomeMessageSent: boolean;
  isDeleted: boolean;
  deletedAt?: number | null;
  createdAt: number;
}

export interface RankedExpert {
  expert: Expert;
  distanceKm: number;
  travelTimeMinutes: number;
}

export interface ExpertCategory {
  id: number;
  name: string;
  isDefault: boolean;
  createdAt: number;
}

export interface UserSession {
  isLoggedIn: boolean;
  userName: string;
  userPhone: string;
  userRole: string;
  userPhotoUri?: string | null;
  isDarkMode: boolean;
}

export interface CustomerFormState {
  name: string;
  phone: string;
  serviceType: string;
  issueDescription: string;
  address: string;
  latitude: number;
  longitude: number;
  hasValidLocation: boolean;
  rawLocationInput: string;
}

export type MainTabType = 'CUSTOMER_ORDERS' | 'EXPERTS';
export type CustomerSubTabType = 'DISPATCH_ORDER' | 'ORDERS';
export type OrderStatusTabType = 'PENDING' | 'PROCESSING' | 'COMPLETED' | 'CANCELLED';
export type DateFilterType = 'ALL' | 'TODAY' | 'LAST_7_DAYS' | 'THIS_MONTH' | 'CUSTOM';

export interface MonthOrderStat {
  monthYearKey: string;
  displayMonth: string;
  totalOrders: number;
  completedOrders: number;
  cancelledOrders: number;
  processingOrders: number;
  pendingOrders: number;
}
