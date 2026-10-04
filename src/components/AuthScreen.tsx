import React, { useState } from 'react';
import { User, Phone, Lock, Eye, EyeOff, CheckCircle2, AlertCircle } from 'lucide-react';
import { SessionManager } from '../utils/sessionManager';

interface AuthScreenProps {
  onLoginSuccess: (name: string) => void;
}

export const AuthScreen: React.FC<AuthScreenProps> = ({ onLoginSuccess }) => {
  const [isRegisterMode, setIsRegisterMode] = useState(false);
  const [name, setName] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setErrorMessage(null);
    setSuccessMessage(null);

    if (isRegisterMode) {
      const result = SessionManager.register(name, phone, password);
      if (result.success) {
        setSuccessMessage('Registration successful! Logging in...');
        setTimeout(() => {
          onLoginSuccess(result.name || name.trim());
        }, 300);
      } else {
        setErrorMessage(result.message);
      }
    } else {
      const result = SessionManager.login(phone, password);
      if (result.success) {
        setSuccessMessage('Login successful!');
        setTimeout(() => {
          onLoginSuccess(result.name || 'Hurifix Partner');
        }, 300);
      } else {
        setErrorMessage(result.message);
      }
    }
  };

  return (
    <div className="min-h-screen w-full flex items-center justify-center p-4 bg-slate-50 dark:bg-slate-950">
      <div className="w-full max-w-md bg-white dark:bg-slate-900 rounded-3xl shadow-xl border border-slate-200 dark:border-slate-800 p-6 sm:p-8">
        {/* Logo & Branding */}
        <div className="text-center mb-6">
          <img
            src="/hurifix_logo.svg"
            alt="Hurifix"
            className="w-20 h-20 mx-auto rounded-2xl object-contain shadow-sm border border-amber-200 dark:border-slate-700 bg-white mb-3"
          />
          <h1 className="text-2xl font-black text-slate-900 dark:text-white tracking-tight">
            Hurifix
          </h1>
          <p className="text-xs font-bold text-amber-600 dark:text-amber-400 uppercase tracking-wider mt-0.5">
            Many Problems | One Solution
          </p>
        </div>

        {/* Tab switch */}
        <div className="grid grid-cols-2 p-1 rounded-xl bg-slate-100 dark:bg-slate-800 mb-6">
          <button
            type="button"
            onClick={() => {
              setIsRegisterMode(false);
              setErrorMessage(null);
              setSuccessMessage(null);
            }}
            className={`py-2 text-xs font-bold rounded-lg transition-all cursor-pointer ${
              !isRegisterMode
                ? 'bg-white dark:bg-slate-700 text-slate-900 dark:text-white shadow-2xs'
                : 'text-slate-500 hover:text-slate-800'
            }`}
          >
            Login
          </button>
          <button
            type="button"
            onClick={() => {
              setIsRegisterMode(true);
              setErrorMessage(null);
              setSuccessMessage(null);
            }}
            className={`py-2 text-xs font-bold rounded-lg transition-all cursor-pointer ${
              isRegisterMode
                ? 'bg-white dark:bg-slate-700 text-slate-900 dark:text-white shadow-2xs'
                : 'text-slate-500 hover:text-slate-800'
            }`}
          >
            Create New ID
          </button>
        </div>

        {/* Messages */}
        {errorMessage && (
          <div className="mb-4 p-3 rounded-xl bg-rose-50 dark:bg-rose-950/60 border border-rose-200 dark:border-rose-900 text-rose-700 dark:text-rose-300 text-xs flex items-center gap-2">
            <AlertCircle className="w-4 h-4 shrink-0" />
            <span>{errorMessage}</span>
          </div>
        )}

        {successMessage && (
          <div className="mb-4 p-3 rounded-xl bg-emerald-50 dark:bg-emerald-950/60 border border-emerald-200 dark:border-emerald-900 text-emerald-700 dark:text-emerald-300 text-xs flex items-center gap-2">
            <CheckCircle2 className="w-4 h-4 shrink-0" />
            <span>{successMessage}</span>
          </div>
        )}

        {/* Form */}
        <form onSubmit={handleSubmit} className="space-y-4">
          {isRegisterMode && (
            <div>
              <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
                Aapka Naam (Full Name) <span className="text-rose-500">*</span>
              </label>
              <div className="relative">
                <User className="absolute inset-y-0 left-3.5 my-auto w-4 h-4 text-slate-400" />
                <input
                  type="text"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  placeholder="e.g. Ramesh Kumar"
                  required={isRegisterMode}
                  className="w-full pl-10 pr-3 py-2.5 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm focus:ring-2 focus:ring-blue-500/20"
                />
              </div>
            </div>
          )}

          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Mobile Number (10 Digits) <span className="text-rose-500">*</span>
            </label>
            <div className="relative">
              <Phone className="absolute inset-y-0 left-3.5 my-auto w-4 h-4 text-slate-400" />
              <input
                type="tel"
                value={phone}
                onChange={(e) => setPhone(e.target.value.replace(/[^0-9]/g, '').slice(0, 10))}
                placeholder="10-digit mobile number"
                maxLength={10}
                required
                className="w-full pl-10 pr-3 py-2.5 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm focus:ring-2 focus:ring-blue-500/20"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 dark:text-slate-300 mb-1">
              Password <span className="text-rose-500">*</span>
            </label>
            <div className="relative">
              <Lock className="absolute inset-y-0 left-3.5 my-auto w-4 h-4 text-slate-400" />
              <input
                type={passwordVisible ? 'text' : 'password'}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Password (minimum 4 characters)"
                required
                className="w-full pl-10 pr-10 py-2.5 rounded-xl border border-slate-300 dark:border-slate-700 bg-white dark:bg-slate-800 text-sm focus:ring-2 focus:ring-blue-500/20"
              />
              <button
                type="button"
                onClick={() => setPasswordVisible(!passwordVisible)}
                className="absolute inset-y-0 right-3 flex items-center text-slate-400 hover:text-slate-600"
              >
                {passwordVisible ? <EyeOff className="w-4 h-4" /> : <Eye className="w-4 h-4" />}
              </button>
            </div>
          </div>

          <button
            type="submit"
            className="w-full py-3.5 rounded-xl bg-blue-600 hover:bg-blue-700 text-white font-bold text-sm shadow-md transition-all cursor-pointer mt-2"
          >
            {isRegisterMode ? 'Register & Open Hurifix' : 'Login to Hurifix'}
          </button>
        </form>

        <p className="text-center text-[11px] text-slate-400 dark:text-slate-500 mt-6">
          Hurifix Dispatch & Field Operations System • Official Partner App
        </p>
      </div>
    </div>
  );
};
