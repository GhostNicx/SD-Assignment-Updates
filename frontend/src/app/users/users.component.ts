import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { format } from 'date-fns';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  currentUser: any;
  users: any[] = [];

  constructor(private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.getActiveUser();
    if (this.currentUser && this.currentUser.cnp) {
      this.fetchUsers();
    } else {
      this.showSnackbar('User not logged in. Redirecting to login page.');
      // this.router.navigate(['/login']);
    }
  }

  getActiveUser(): void {
    if (typeof localStorage !== 'undefined') {
      const userJson = localStorage.getItem('user');
      if (userJson) {
        this.currentUser = JSON.parse(userJson);
      } else {
        this.currentUser = {};
      }
    } else {
      console.warn('LocalStorage is not available.');
      this.currentUser = {};
    }
  }

  fetchUsers(): void {
    console.log('Fetching users...');
    fetch('http://localhost:8080/user/getAll', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        console.log('Response received:', response);
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Data received:', data);
        this.users = data; // Store data to variable
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
      });
  }

  updateUserRole(form: any): void {
    const { cnp, role } = form.value;
    console.log('Updating user role...');
    fetch(`http://localhost:8080/user/updateRole/${cnp}?newRole=${role}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        console.log('Response received:', response);
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Data received:', data);
        this.showSnackbar('User role updated successfully.');
        this.fetchUsers();
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
        this.showSnackbar('Failed to update user role.');
      });
  }

  isAdmin(): boolean {
    return this.currentUser && this.currentUser.role === 'ADMIN';
  }

  formatedDate(date: string): string {
    return format(new Date(date), 'MMMM dd, yyyy');
  }

  private showSnackbar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000
    });
  }
}
