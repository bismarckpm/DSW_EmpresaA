import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() {}

  public isAuthenticated(): boolean {
    console.log("auten")
    const token = localStorage.getItem('identity');

    
    if (!token) {

      return false;

    }
     
    return true;

  }

}
