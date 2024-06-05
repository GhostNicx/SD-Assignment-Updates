import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-questions',
  templateUrl: './add-questions.component.html',
  styleUrls: ['./add-questions.component.css']
})
export class AddQuestionsComponent implements OnInit {
  formData = {
    user_id: '',
    title: '',
    question: '',
    tag: ''
  };

  constructor(private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.getActiveUser();
  }

  getActiveUser(): void {
    if (typeof localStorage !== 'undefined') {
      const activeUserJson = localStorage.getItem('user');
      if (activeUserJson) {
        const activeUser = JSON.parse(activeUserJson);
        if (activeUser.cnp) {
          this.formData.user_id = activeUser.cnp;
        } else {
          this.showSnackbar('User ID (cnp) is missing. Please login to ask a question.');
        }
      } else {
        this.showSnackbar('User data is missing. Please login to ask a question.');
      }
    } else {
      this.showSnackbar('Local storage is not available. Please use a different browser or enable local storage for this website.');
    }
  }

  addQuestion(): void {
    if (!this.formData.user_id) {
      this.showSnackbar('Please login to ask a question.');
      return;
    }

    const payload = {
      user_id: this.formData.user_id,
      title: this.formData.title,
      question: this.formData.question,
      tag: this.formData.tag
    };

    fetch('http://localhost:8080/question/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload)
    })
      .then(response => {
        if (response.ok) {
          this.showSnackbar('Question added successfully.');
          this.formData.title = '';
          this.formData.question = '';
        } else {
          this.showSnackbar('Failed to add question.');
        }
      })
      .catch(error => {
        console.error('There was an error with the fetch operation:', error);
        this.showSnackbar('An error occurred while adding the question.');
      });
  }

  private showSnackbar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000
    });
  }
}
