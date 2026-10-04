import { CustomerJob, Expert } from '../types';
import { LocationHelper } from './locationHelper';

export const WhatsAppHelper = {
  /**
   * Cleans phone number for WhatsApp URL (adds Indian country code 91 if 10-digit number).
   */
  formatPhoneNumberForWhatsApp(phone: string): string {
    const digitsOnly = phone.replace(/[^0-9]/g, '');
    if (digitsOnly.length === 10) {
      return `91${digitsOnly}`;
    }
    if (digitsOnly.length === 11 && digitsOnly.startsWith('0')) {
      return `91${digitsOnly.substring(1)}`;
    }
    if (digitsOnly.length === 12 && digitsOnly.startsWith('91')) {
      return digitsOnly;
    }
    return digitsOnly;
  },

  /**
   * Cleans phone number for tel: links (never parenthesized).
   */
  sanitizePhoneNumberForDialer(phone: string): string {
    const digitsOnly = phone.replace(/[^0-9]/g, '');
    const clean10 = digitsOnly.length === 10 ? digitsOnly : digitsOnly.slice(-10);
    return clean10.length === 10 ? `+91${clean10}` : `+91${digitsOnly}`;
  },

  /**
   * Calculates estimated arrival time:
   * Requirement: Estimated time MUST BE 30 minutes MORE than calculated distance time.
   */
  calculateEstimatedArrivalTimeWithBuffer(distanceKm: number): string {
    const baseTravelMinutes = LocationHelper.estimateTravelTimeMinutes(distanceKm);
    const totalWithBuffer = baseTravelMinutes + 30; // +30 minutes buffer
    if (totalWithBuffer >= 60) {
      const hours = Math.floor(totalWithBuffer / 60);
      const mins = totalWithBuffer % 60;
      return mins === 0 ? `${hours} Hour (Approx)` : `${hours} Hr ${mins} Mins (Approx)`;
    }
    return `${totalWithBuffer} Minutes (Approx)`;
  },

  /**
   * Message sent to customer informing them of the assigned expert, phone, and estimated time (+30 min buffer).
   */
  createCustomerAssignmentNotificationMessage(
    customerName: string,
    expertName: string,
    expertPhone: string,
    serviceType: string,
    estimatedTimeText: string
  ): string {
    const displayName = customerName.trim() || 'Customer';
    return `🛠 *HURIFIX SERVICE UPDATE* 🛠
━━━━━━━━━━━━━━━━━━━━
Dear *${displayName}* ✨,

An expert technician has been assigned to your service request:

👨‍🔧 *Expert Name:* ${expertName}
📞 *Contact Number:* ${expertPhone}
⏳ *Estimated Time of Arrival:* ${estimatedTimeText}
🛠 *Service Required:* ${serviceType}

Our technician will arrive at your address shortly. Please keep your phone reachable.
━━━━━━━━━━━━━━━━━━━━
- *Team Hurifix*
_Many Problems | One Solution_`;
  },

  /**
   * Professional message sent to customer upon order completion:
   * Dear [Customer Name] ✨, followed by review prompt, feedback call alert, and Instagram link.
   */
  createCompletionCustomerMessage(customerName: string): string {
    const displayName = customerName.trim() || 'Customer';
    return `Dear ${displayName} ✨,

Your work has been successfully completed! 🎉 We hope we met your expectations and provided a great experience. 💯

One of our representatives will call you shortly for your valuable feedback—please do share your experience with us! 📞

If you have any questions or need assistance, feel free to reach out to us anytime. 📞💬

Stay Connected! 🚀
Follow us on Instagram for future updates, offers, and exclusive services:
👇
https://www.instagram.com/hurifix_official?stkn=MzUzMW9xOTh2eTJ4

Thank you for choosing us! Have a great day ahead! 😊🙏`;
  },

  /**
   * Builds the complete dispatch message for the expert with customer details and Google Maps location.
   */
  createDispatchMessage(expert: Expert, customer: CustomerJob): string {
    const mapsUrl = LocationHelper.createGoogleMapsUrl(customer.latitude, customer.longitude);
    return `🔧 *HURIFIX DISPATCH ORDER* 🔧
━━━━━━━━━━━━━━━━━━━━
Hello *${expert.name}*, a new service task has been dispatched to you:

👤 *Customer Name:* ${customer.customerName}
📞 *Customer Phone:* ${customer.customerPhone}
🛠 *Service Required:* ${customer.serviceType}
📝 *Problem Description:* ${customer.issueDescription}
📍 *Address:* ${customer.address}

🗺 *Google Maps Location:*
${mapsUrl}
━━━━━━━━━━━━━━━━━━━━
- *Hurifix Operations Team*`;
  },

  /**
   * Welcome message for newly registered Hurifix Expert.
   */
  createNewExpertWelcomeMessage(expertName: string): string {
    return `Hello ${expertName}, 👋
Welcome to Hurifix! 🎉
Aap ab hamare Official Business Partner ban chuke hain. Hum aapke sath kaam karne ke liye bohot excited hain! 🛠️🚀
Hum aapko jald hi WhatsApp par ek message bhejenge jisme aapko hamara poora work process aur guidelines achhe se samjha di jayengi. 📲
Hurifix family se judne ke liye aapka bohot dhanyawad! Have a great day! 😊🙏`;
  },

  /**
   * Opens WhatsApp with a pre-filled message.
   */
  openWhatsAppDirectMessage(phoneNumber: string, message: string) {
    const formatted = this.formatPhoneNumberForWhatsApp(phoneNumber);
    const encoded = encodeURIComponent(message);
    const url = `https://wa.me/${formatted}?text=${encoded}`;
    window.open(url, '_blank', 'noopener,noreferrer');
  },

  /**
   * Opens WhatsApp chat directly WITHOUT pre-filled message.
   */
  openWhatsAppChatWithoutMessage(phoneNumber: string) {
    const formatted = this.formatPhoneNumberForWhatsApp(phoneNumber);
    const url = `https://wa.me/${formatted}`;
    window.open(url, '_blank', 'noopener,noreferrer');
  },

  /**
   * Opens dialer.
   */
  openDialer(phoneNumber: string) {
    const sanitized = this.sanitizePhoneNumberForDialer(phoneNumber);
    window.location.href = `tel:${sanitized}`;
  },

  /**
   * Copies text to clipboard.
   */
  async copyToClipboard(text: string): Promise<boolean> {
    try {
      if (navigator.clipboard && navigator.clipboard.writeText) {
        await navigator.clipboard.writeText(text);
        return true;
      }
      const textArea = document.createElement('textarea');
      textArea.value = text;
      document.body.appendChild(textArea);
      textArea.select();
      document.execCommand('copy');
      document.body.removeChild(textArea);
      return true;
    } catch {
      return false;
    }
  },
};
