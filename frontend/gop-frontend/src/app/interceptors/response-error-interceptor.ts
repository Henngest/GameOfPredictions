import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable()
export class ResponseErrorInterceptor implements HttpInterceptor {
  constructor(
    private router: Router
  ) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (!error.url?.endsWith("/api/auth/login") && !error.url?.endsWith("/api/auth/register")) {
          this.router.navigateByUrl(`/error?status=${error.status}&statusText=${error.statusText}&errorMessage=${error.error.error ? error.error.error : error.error}`);
        }
        return throwError(() => error);
      })
    );
  }
}
