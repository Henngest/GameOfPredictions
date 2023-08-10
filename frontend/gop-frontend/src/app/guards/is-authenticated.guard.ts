import {inject, Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, CanActivateFn} from '@angular/router';
import {AuthenticationService} from "../services/authentication.service";

@Injectable({
  providedIn: 'root',
})
class AuthenticatedGuard {
  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | UrlTree {
    const token = this.authenticationService.getToken();

    if (!token) {
      const errorMessage = "You must login in order to proceed!";
      return this.router.parseUrl(`/login?errorMessage=${errorMessage}`);
    }

    return true;
  }
}

export const IsAuthenticatedGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree => {
  return inject(AuthenticatedGuard).canActivate(route, state);
}
