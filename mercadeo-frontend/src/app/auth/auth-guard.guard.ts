import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../services/login.service';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(public auth: AuthService, public router: Router, private _loginService: LoginService
    ) {}
  
    canActivate(route: ActivatedRouteSnapshot): boolean {
      console.log('entre')
      const expectedRole = route.data.expectedRole;
      const token = localStorage.getItem('identity');
      const identity = JSON.parse(this._loginService.getIdentity());


      if (!this.auth.isAuthenticated() || !(expectedRole.indexOf(identity.idRol) !== -1) ) {


        if (identity.idRol = 1) 
        { 
          this.router.navigate(['/admin']); 
          return false
        } else if (identity.idRol = 2) 
        { 
          this.router.navigate(['/vistaEstudios']); 
          return false
        }  else if (identity.idRol = 3) 
        { 
          this.router.navigate(['/analista']); 
          return false
        } else if (identity.idRol = 4) 
        { 
          this.router.navigate(['/consultarestudioencuestado']); 
          return false
        } else {
          this.router.navigate(['/login']);
          return false;
      }

      }
      return true;
    }
  
}
