import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  username: string = ''; // Add initializer to 'username' property
  password: string = '';
  confirmPassword: string = '';
  email: string = '';

  constructor(private router: Router) {}

  onSubmit() {
    if (this.password !== this.confirmPassword) {
      alert('Passwords do not match');
      return;
    }

    const body = {
      username: this.username,
      password: this.password,
      email: this.email
    };

    fetch('http://localhost:8080/user/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(body)
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Data:', data);
        if(data == 200){
          alert('User created successfully');
          // Redirect to login page
          this.router.navigate(['/login']);
        }
        else{
          alert('Signup failed');
        }
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
      });

  }
}