import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { format } from 'date-fns';
import { Router } from '@angular/router';

@Component({
  selector: 'app-answers',
  templateUrl: './answers.component.html',
  styleUrls: ['./answers.component.css']
})
export class AnswersComponent implements OnInit {
  currentUser: any;
  answers: any[] = [];
  isUpdating = false;
  updateData = {
    answer_id: null,
    answer: ''
  };

  constructor(private snackBar: MatSnackBar, private router: Router) {}

  ngOnInit(): void {
    this.getActiveUser();
    if (this.currentUser && this.currentUser.cnp) {
      this.fetchAnswers();
    } else {
      this.showSnackbar('User not logged in. Redirecting to login page.');
      //this.router.navigate(['/login']);
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

  fetchAnswers(): void {
    console.log('Fetching answers...');
    fetch('http://localhost:8080/answer/getAll', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      },
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
      this.answers = data; // Store data to variable
    })
    .catch(error => {
      console.error('There was a problem with the fetch operation:', error);
    });
  }

  upvoteAnswer(answerId: number): void {
    const answer = this.answers.find(a => a.id === answerId);
    if(answer.userId === this.currentUser.cnp){
      this.showSnackbar('You cannot upvote your own answer.');
      return;
    }
    fetch(`http://localhost:8080/answer/upvote/${answerId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      this.fetchAnswers(); // Refresh the answers list to update the scores
    })
    .catch(error => {
      console.error('There was a problem with the upvote operation:', error);
    });
  }

  downvoteAnswer(answerId: number): void {
    const answer = this.answers.find(a => a.id === answerId);
    if(answer.userId === this.currentUser.cnp){
      this.showSnackbar('You cannot downvote your own answer.');
      return;
    }
    fetch(`http://localhost:8080/answer/downvote/${answerId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      this.fetchAnswers(); // Refresh the answers list to update the scores
    })
    .catch(error => {
      console.error('There was a problem with the downvote operation:', error);
    });
  }

  startUpdateAnswer(answer: any): void {
    this.isUpdating = true;
    this.updateData.answer_id = answer.id;
    this.updateData.answer = answer.answer;
  }

  updateAnswer(): void {
    const payload = {
      answer_id: this.updateData.answer_id,
      answer: this.updateData.answer
    };

    fetch(`http://localhost:8080/answer/updateByAuthor/${this.currentUser.cnp}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })
    .then(response => {
      if (response.ok) {
        this.showSnackbar('Answer updated successfully.');
        this.isUpdating = false;
        this.fetchAnswers();
      } else {
        this.showSnackbar('Failed to update answer.');
      }
    })
    .catch(error => console.error('Error updating answer:', error));
  }

  deleteAnswer(answerId: number): void {
    console.log(`Attempting to delete answer with ID: ${answerId}`);
    fetch(`http://localhost:8080/answer/deleteByAuthor/${answerId}?userId=${this.currentUser.cnp}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      },
    })
    .then(response => {
      console.log('Delete response received:', response);
      if (response.ok) {
        this.showSnackbar('Answer deleted successfully.');
        this.fetchAnswers();
      } else {
        this.showSnackbar('Failed to delete answer.');
        console.error('Failed to delete answer. Status:', response.status, 'Text:', response.statusText);
      }
    })
    .catch(error => {
      console.error('Error deleting answer:', error);
      this.showSnackbar('Error deleting answer. Please try again.');
    });
  }

  cancelUpdate(): void {
    this.isUpdating = false;
    this.updateData = {
      answer_id: null,
      answer: ''
    };
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
