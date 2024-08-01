import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class BrowserStorageService {
  private  storage: Storage | null;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    this.storage = isPlatformBrowser(this.platformId) ? localStorage : null;
  }

  getItem(key: string): string | null {
    return this.storage ? this.storage.getItem(key) : null;
  }

  setItem(key: string, value: string | null): void {
    if (this.storage) {
      if (value === null) {
        this.storage.removeItem(key);
        return;
      }
      this.storage.setItem(key, value);
    }
  }

}
