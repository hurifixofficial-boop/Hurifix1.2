export const LocationHelper = {
  /**
   * Calculates distance in kilometers between two coordinates using the Haversine formula.
   */
  calculateDistanceKm(lat1: number, lon1: number, lat2: number, lon2: number): number {
    const r = 6371.0; // Earth radius in km
    const dLat = ((lat2 - lat1) * Math.PI) / 180;
    const dLon = ((lon2 - lon1) * Math.PI) / 180;
    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos((lat1 * Math.PI) / 180) *
        Math.cos((lat2 * Math.PI) / 180) *
        Math.sin(dLon / 2) *
        Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return r * c;
  },

  /**
   * Estimates travel time in minutes based on urban technician bike travel (average 24 km/h + 3 min buffer).
   */
  estimateTravelTimeMinutes(distanceKm: number): number {
    if (distanceKm <= 0.2) return 2;
    const avgSpeedKmh = 24.0;
    const travelHours = distanceKm / avgSpeedKmh;
    const minutes = Math.floor(travelHours * 60) + 3;
    return Math.max(3, minutes);
  },

  /**
   * Formats distance nicely (e.g. "850 m" or "2.4 km").
   */
  formatDistance(distanceKm: number): string {
    if (distanceKm < 1.0) {
      const meters = Math.round(distanceKm * 1000);
      return `${meters} m`;
    }
    return `${distanceKm.toFixed(1)} km`;
  },

  /**
   * Generates a direct Google Maps search link.
   */
  createGoogleMapsUrl(latitude: number, longitude: number): string {
    return `https://www.google.com/maps/search/?api=1&query=${latitude},${longitude}`;
  },

  /**
   * Smart parser to extract Latitude and Longitude from raw text, coordinates, or Google Maps URL.
   */
  parseCoordinatesFromText(input: string): [number, number] | null {
    if (!input || !input.trim()) return null;
    const text = input.trim();

    // 1. Check for lat/lng query in URL e.g. query=lat,lng or q=lat,lng
    const urlQueryMatch = text.match(/[?&](?:q|query|ll)=([+-]?\d+\.\d+),([+-]?\d+\.\d+)/);
    if (urlQueryMatch) {
      const lat = parseFloat(urlQueryMatch[1]);
      const lng = parseFloat(urlQueryMatch[2]);
      if (!isNaN(lat) && !isNaN(lng)) return [lat, lng];
    }

    // 2. Check for @lat,lng in google maps URLs
    const atMatch = text.match(/@([+-]?\d+\.\d+),([+-]?\d+\.\d+)/);
    if (atMatch) {
      const lat = parseFloat(atMatch[1]);
      const lng = parseFloat(atMatch[2]);
      if (!isNaN(lat) && !isNaN(lng)) return [lat, lng];
    }

    // 2b. Check for !3d<lat>!4d<lng> format in web Google Maps URLs
    const d3d4Match = text.match(/!3d([+-]?\d+\.\d+)!4d([+-]?\d+\.\d+)/);
    if (d3d4Match) {
      const lat = parseFloat(d3d4Match[1]);
      const lng = parseFloat(d3d4Match[2]);
      if (!isNaN(lat) && !isNaN(lng)) return [lat, lng];
    }

    // 2c. Check for destination/daddr in navigation links
    const destMatch = text.match(/(?:destination|daddr|saddr)=([+-]?\d+\.\d+),([+-]?\d+\.\d+)/);
    if (destMatch) {
      const lat = parseFloat(destMatch[1]);
      const lng = parseFloat(destMatch[2]);
      if (!isNaN(lat) && !isNaN(lng)) return [lat, lng];
    }

    // 3. Check for standard "28.1234, 77.5678" or "28.1234 77.5678"
    const generalMatch = text.match(/([+-]?\d{1,2}\.\d{2,10})\s*[,|\s]\s*([+-]?\d{1,3}\.\d{2,10})/);
    if (generalMatch) {
      const lat = parseFloat(generalMatch[1]);
      const lng = parseFloat(generalMatch[2]);
      if (!isNaN(lat) && !isNaN(lng) && lat >= -90 && lat <= 90 && lng >= -180 && lng <= 180) {
        return [lat, lng];
      }
    }

    return null;
  },

  /**
   * Browser GPS fetch
   */
  async fetchCurrentGps(): Promise<{ latitude: number; longitude: number }> {
    return new Promise((resolve, reject) => {
      if (!navigator.geolocation) {
        reject(new Error('Geolocation is not supported by your browser'));
        return;
      }
      navigator.geolocation.getCurrentPosition(
        (position) => {
          resolve({
            latitude: position.coords.latitude,
            longitude: position.coords.longitude,
          });
        },
        (error) => {
          reject(new Error(error.message || 'Could not fetch current GPS location'));
        },
        { enableHighAccuracy: true, timeout: 10000, maximumAge: 0 }
      );
    });
  },
};
