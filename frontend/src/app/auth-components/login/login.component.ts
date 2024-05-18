import { Component } from '@angular/core';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = ''; 
  password: string = '';

  constructor(private router: Router) {} 

  backend_data: any;  // Variable to store data from backend

  onSubmit() {
    const body= {
      username : this.username,
      password: this.password
    };
    
    fetch( 'http://localhost:8080/user/login', {
      method: 'POST', 
      headers: {
        'Content-Type': 'application/json'   },
      body: JSON.stringify(body)
    })
      .then(response => {
        // Check if request was successful
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        // Parse response as JSON
        return response.json();
      })
      .then(data => {
        // Handle response data
        this.backend_data = data; // Store data to variable
        console.log('Data:', data);
        if(data == 200){
          localStorage.setItem('user', JSON.stringify({username: this.username, password: this.password}));
          this.router.navigate(['/questions'], {replaceUrl: true});
        }else{
          alert('Invalid username or password');
        }
        console.log('Response:', data);
      })
      .catch(error => {
        // Handle errors
        console.error('There was a problem with the fetch operation:', error);
      });
    console.log('Username: ' + this.username);
    console.log('Password ' + this.password);
  }
}