import { UserSession } from '../types';

const STORAGE_PREFIX = 'hurifix_';
const KEY_IS_LOGGED_IN = `${STORAGE_PREFIX}is_logged_in`;
const KEY_USER_NAME = `${STORAGE_PREFIX}user_name`;
const KEY_USER_PHONE = `${STORAGE_PREFIX}user_phone`;
const KEY_USER_ROLE = `${STORAGE_PREFIX}user_role`;
const KEY_USER_PHOTO_URI = `${STORAGE_PREFIX}user_photo_uri`;
const KEY_DARK_MODE = `${STORAGE_PREFIX}dark_mode`;

export const SessionManager = {
  isLoggedIn(): boolean {
    return localStorage.getItem(KEY_IS_LOGGED_IN) === 'true';
  },

  getUserName(): string {
    return localStorage.getItem(KEY_USER_NAME) || 'Hurifix Partner';
  },

  getUserPhone(): string {
    return localStorage.getItem(KEY_USER_PHONE) || '';
  },

  getUserRole(): string {
    const phone = this.getUserPhone();
    return (
      (phone && localStorage.getItem(`${STORAGE_PREFIX}role_${phone}`)) ||
      localStorage.getItem(KEY_USER_ROLE) ||
      'Hurifix Partner & Operations'
    );
  },

  getUserPhotoUri(): string | null {
    const phone = this.getUserPhone();
    return (
      (phone && localStorage.getItem(`${STORAGE_PREFIX}photo_${phone}`)) ||
      localStorage.getItem(KEY_USER_PHOTO_URI) ||
      null
    );
  },

  isDarkModeEnabled(): boolean {
    return localStorage.getItem(KEY_DARK_MODE) === 'true';
  },

  setDarkModeEnabled(enabled: boolean) {
    localStorage.setItem(KEY_DARK_MODE, enabled ? 'true' : 'false');
  },

  updateAdminProfile(name: string, phone: string, role: string, photoUri?: string | null) {
    const cleanPhone = phone.replace(/[^0-9]/g, '') || this.getUserPhone();
    localStorage.setItem(KEY_USER_NAME, name.trim());
    localStorage.setItem(KEY_USER_PHONE, cleanPhone);
    localStorage.setItem(KEY_USER_ROLE, role.trim());
    if (photoUri !== undefined) {
      if (photoUri) {
        localStorage.setItem(KEY_USER_PHOTO_URI, photoUri);
        if (cleanPhone) localStorage.setItem(`${STORAGE_PREFIX}photo_${cleanPhone}`, photoUri);
      } else {
        localStorage.removeItem(KEY_USER_PHOTO_URI);
        if (cleanPhone) localStorage.removeItem(`${STORAGE_PREFIX}photo_${cleanPhone}`);
      }
    }
    if (cleanPhone) {
      localStorage.setItem(`${STORAGE_PREFIX}name_${cleanPhone}`, name.trim());
      localStorage.setItem(`${STORAGE_PREFIX}role_${cleanPhone}`, role.trim());
    }
  },

  login(phone: string, password: string): { success: boolean; message: string; name?: string } {
    const cleanPhone = phone.replace(/[^0-9]/g, '');
    if (cleanPhone.length < 10) {
      return { success: false, message: 'Kripya 10-digit mobile number enter karein' };
    }
    const storedPwd = localStorage.getItem(`${STORAGE_PREFIX}pwd_${cleanPhone}`);
    if (!storedPwd) {
      return {
        success: false,
        message: "Yeh mobile number registered nahi hai. Kripya 'Create New ID' se naya account banayein.",
      };
    }
    if (storedPwd !== password) {
      return { success: false, message: 'Galat password. Kripya sahi password enter karein.' };
    }

    const name = localStorage.getItem(`${STORAGE_PREFIX}name_${cleanPhone}`) || 'Hurifix Partner';
    const role =
      localStorage.getItem(`${STORAGE_PREFIX}role_${cleanPhone}`) ||
      'Hurifix Partner & Operations';
    const photo = localStorage.getItem(`${STORAGE_PREFIX}photo_${cleanPhone}`) || null;

    localStorage.setItem(KEY_IS_LOGGED_IN, 'true');
    localStorage.setItem(KEY_USER_PHONE, cleanPhone);
    localStorage.setItem(KEY_USER_NAME, name);
    localStorage.setItem(KEY_USER_ROLE, role);
    if (photo) localStorage.setItem(KEY_USER_PHOTO_URI, photo);

    return { success: true, message: 'Login successful', name };
  },

  register(name: string, phone: string, password: string): { success: boolean; message: string; name?: string } {
    const cleanPhone = phone.replace(/[^0-9]/g, '');
    if (cleanPhone.length < 10) {
      return { success: false, message: 'Kripya 10-digit mobile number enter karein' };
    }
    if (!name.trim()) {
      return { success: false, message: 'Kripya apna naam enter karein' };
    }
    if (password.length < 4) {
      return { success: false, message: 'Password kam se kam 4 aksharon ka hona chahiye' };
    }
    if (localStorage.getItem(`${STORAGE_PREFIX}pwd_${cleanPhone}`)) {
      return {
        success: false,
        message: 'Yeh mobile number pehle se registered hai. Kripya Login karein.',
      };
    }

    const defaultRole = 'Hurifix Partner & Operations';
    localStorage.setItem(`${STORAGE_PREFIX}pwd_${cleanPhone}`, password);
    localStorage.setItem(`${STORAGE_PREFIX}name_${cleanPhone}`, name.trim());
    localStorage.setItem(`${STORAGE_PREFIX}role_${cleanPhone}`, defaultRole);

    localStorage.setItem(KEY_IS_LOGGED_IN, 'true');
    localStorage.setItem(KEY_USER_PHONE, cleanPhone);
    localStorage.setItem(KEY_USER_NAME, name.trim());
    localStorage.setItem(KEY_USER_ROLE, defaultRole);
    localStorage.removeItem(KEY_USER_PHOTO_URI);

    return { success: true, message: 'Registration successful', name: name.trim() };
  },

  logout() {
    localStorage.setItem(KEY_IS_LOGGED_IN, 'false');
    localStorage.removeItem(KEY_USER_PHONE);
    localStorage.removeItem(KEY_USER_NAME);
    localStorage.removeItem(KEY_USER_ROLE);
    localStorage.removeItem(KEY_USER_PHOTO_URI);
  },

  getCurrentSession(): UserSession {
    return {
      isLoggedIn: this.isLoggedIn(),
      userName: this.getUserName(),
      userPhone: this.getUserPhone(),
      userRole: this.getUserRole(),
      userPhotoUri: this.getUserPhotoUri(),
      isDarkMode: this.isDarkModeEnabled(),
    };
  },
};
