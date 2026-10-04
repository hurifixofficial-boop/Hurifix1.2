import { CustomerJob, Expert, ExpertCategory } from '../types';

export interface BackupData {
  jobs: CustomerJob[];
  experts: Expert[];
  categories: ExpertCategory[];
}

export const BackupRestoreHelper = {
  createBackupJson(
    jobs: CustomerJob[],
    experts: Expert[],
    categories: ExpertCategory[]
  ): string {
    const root = {
      app: 'Hurifix',
      version: 4,
      timestamp: Date.now(),
      categories: categories.map((cat) => ({
        id: cat.id,
        name: cat.name,
        isDefault: cat.isDefault,
        createdAt: cat.createdAt,
      })),
      experts: experts.map((exp) => ({
        id: exp.id,
        name: exp.name,
        phone: exp.phone,
        category: exp.category,
        address: exp.address,
        latitude: exp.latitude,
        longitude: exp.longitude,
        isAvailable: exp.isAvailable,
        rating: exp.rating,
        ratingSum: exp.ratingSum,
        totalRatingsCount: exp.totalRatingsCount,
        completedJobsCount: exp.completedJobsCount,
        cancelledJobsCount: exp.cancelledJobsCount,
        createdAt: exp.createdAt,
      })),
      jobs: jobs.map((job) => ({
        id: job.id,
        customerName: job.customerName,
        customerPhone: job.customerPhone,
        serviceType: job.serviceType,
        issueDescription: job.issueDescription,
        address: job.address,
        latitude: job.latitude,
        longitude: job.longitude,
        status: job.status,
        assignedExpertId: job.assignedExpertId ?? null,
        assignedExpertName: job.assignedExpertName ?? null,
        assignedExpertPhone: job.assignedExpertPhone ?? null,
        distanceKmAtDispatch: job.distanceKmAtDispatch ?? null,
        ratingGiven: job.ratingGiven ?? null,
        reviewFeedback: job.reviewFeedback ?? null,
        createdAt: job.createdAt,
        completedAt: job.completedAt ?? null,
        isExpertNotified: job.isExpertNotified,
        isCustomerNotifiedOnAssign: job.isCustomerNotifiedOnAssign,
        isCustomerNotifiedOnCompletion: job.isCustomerNotifiedOnCompletion,
        assignMessageLaterDismissedAt: job.assignMessageLaterDismissedAt ?? null,
      })),
    };

    return JSON.stringify(root, null, 2);
  },

  exportAndDownloadBackup(
    jobs: CustomerJob[],
    experts: Expert[],
    categories: ExpertCategory[]
  ) {
    const jsonString = this.createBackupJson(jobs, experts, categories);
    const dateStr = new Date().toISOString().replace(/[:.]/g, '-').slice(0, 16);
    const fileName = `Hurifix_DriveBackup_${dateStr}.json`;

    const blob = new Blob([jsonString], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = fileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    URL.revokeObjectURL(url);
  },

  async parseBackupJson(file: File): Promise<BackupData> {
    const text = await file.text();
    const root = JSON.parse(text);

    const categories: ExpertCategory[] = (root.categories || []).map((c: any) => ({
      id: Number(c.id) || Date.now(),
      name: String(c.name || '').trim(),
      isDefault: Boolean(c.isDefault),
      createdAt: Number(c.createdAt) || Date.now(),
    }));

    const experts: Expert[] = (root.experts || []).map((e: any) => ({
      id: Number(e.id) || Date.now(),
      name: String(e.name || ''),
      phone: String(e.phone || ''),
      category: String(e.category || 'General'),
      address: String(e.address || ''),
      latitude: Number(e.latitude) || 28.57,
      longitude: Number(e.longitude) || 77.32,
      isAvailable: e.isAvailable !== false,
      rating: Number(e.rating) || 4.8,
      ratingSum: Number(e.ratingSum) || 4.8,
      totalRatingsCount: Number(e.totalRatingsCount) || 1,
      completedJobsCount: Number(e.completedJobsCount) || 0,
      cancelledJobsCount: Number(e.cancelledJobsCount) || 0,
      isWelcomeMessageSent: Boolean(e.isWelcomeMessageSent),
      isDeleted: false,
      createdAt: Number(e.createdAt) || Date.now(),
    }));

    const jobs: CustomerJob[] = (root.jobs || []).map((j: any) => ({
      id: Number(j.id) || Date.now(),
      customerName: String(j.customerName || 'Customer'),
      customerPhone: String(j.customerPhone || ''),
      serviceType: String(j.serviceType || 'General Repair'),
      issueDescription: String(j.issueDescription || ''),
      address: String(j.address || ''),
      latitude: Number(j.latitude) || 28.57,
      longitude: Number(j.longitude) || 77.32,
      status: j.status || 'PENDING',
      assignedExpertId: j.assignedExpertId != null ? Number(j.assignedExpertId) : null,
      assignedExpertName: j.assignedExpertName ? String(j.assignedExpertName) : null,
      assignedExpertPhone: j.assignedExpertPhone ? String(j.assignedExpertPhone) : null,
      distanceKmAtDispatch: j.distanceKmAtDispatch != null ? Number(j.distanceKmAtDispatch) : null,
      ratingGiven: j.ratingGiven != null ? Number(j.ratingGiven) : null,
      reviewFeedback: j.reviewFeedback ? String(j.reviewFeedback) : null,
      createdAt: Number(j.createdAt) || Date.now(),
      completedAt: j.completedAt != null ? Number(j.completedAt) : null,
      isExpertNotified: Boolean(j.isExpertNotified),
      isCustomerNotifiedOnAssign: Boolean(j.isCustomerNotifiedOnAssign),
      isCustomerNotifiedOnCompletion: Boolean(j.isCustomerNotifiedOnCompletion),
      assignMessageLaterDismissedAt:
        j.assignMessageLaterDismissedAt != null ? Number(j.assignMessageLaterDismissedAt) : null,
      isDeleted: false,
    }));

    return { jobs, experts, categories };
  },
};
