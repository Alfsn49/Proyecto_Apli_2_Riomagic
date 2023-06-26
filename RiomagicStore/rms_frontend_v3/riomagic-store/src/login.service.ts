import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseUrl = 'http://localhost:9090/api';

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<string> {
    const body = new FormData();
    body.append('email', email);
    body.append('password', password);

    return this.http.post<string>(`${this.baseUrl}/login`, body);
  }
}
