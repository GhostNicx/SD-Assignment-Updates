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
        if (data.status === 200) {
          // Assuming the backend returns the user data with the cnp
          const userData = {
            username: this.username,
            password: this.password,
            cnp: data.cnp, // Assuming the backend returns the cnp in the response
            role: data.role
          };
          localStorage.setItem('user', JSON.stringify(userData));
          alert('User created successfully');
          this.router.navigate(['/login']);
        } else {
          alert('Signup failed');
        }
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
      });
  }
}
