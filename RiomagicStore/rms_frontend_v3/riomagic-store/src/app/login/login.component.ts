import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email = ''; // Valor inicial agregado
  password = '';

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
    this.password = '';
  }

  login(): void {
    this.loginService.login(this.email, this.password).subscribe(
      response => {
        // Lógica para manejar la respuesta del servidor
        console.log(response);
        // Redirigir al componente deseado
        this.router.navigate(['/dashboard']);
      },
      error => {
        // Lógica para manejar errores de autenticación
        console.log(error);
      }
    );
  }
}