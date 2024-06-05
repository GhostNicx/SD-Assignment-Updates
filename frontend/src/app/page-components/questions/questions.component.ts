import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { format } from 'date-fns';
import { Router } from '@angular/router';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {
  currentUser: any;
  questions: any[] = [];
  isUpdating = false;
  updateData = {
    question_id: null,
    question: '',
    title: ''
  };
  searchTag: string = ''

  constructor(private snackBar: MatSnackBar, private router: Router) {}

  ngOnInit(): void {
      this.getActiveUser();
      debugger
      if (this.currentUser && this.currentUser.cnp) {
        this.fetchQuestions();
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


  fetchQuestions(): void {
    console.log('Fetching questions...');
    fetch('http://localhost:8080/question/getAll', {
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
      this.questions = data; // Store data to variable
    })
    .catch(error => {
      console.error('There was a problem with the fetch operation:', error);
    });
  }

  searchByTag(): void {
    console.log('Searching questions by tag...');
    fetch(`http://localhost:8080/question/findByTag/${this.searchTag}`, {
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
      this.questions = data; // Store data to variable
    })
    .catch(error => {
      console.error('There was a problem with the fetch operation:', error);
    });
  }

  upvoteQuestion(questionId: number): void {
    const question = this.questions.find(q => q.id === questionId);
    if(question.userId === this.currentUser.cnp){
      this.showSnackbar('You cannot upvote your own question.');
      return;
    }
    fetch(`http://localhost:8080/question/upvote/${questionId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      this.fetchQuestions(); // Refresh the questions list to update the scores
    })
    .catch(error => {
      console.error('There was a problem with the upvote operation:', error);
    });
  }

  downvoteQuestion(questionId: number): void {
    const question = this.questions.find(q => q.id === questionId);
    if(question.userId === this.currentUser.cnp){
          this.showSnackbar('You cannot upvote your own question.');
          return;
      }

    fetch(`http://localhost:8080/question/downvote/${questionId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      this.fetchQuestions(); // Refresh the questions list to update the scores
    })
    .catch(error => {
      console.error('There was a problem with the downvote operation:', error);
    });
  }

  startUpdateQuestion(question: any): void {
    this.isUpdating = true;
    this.updateData.question_id = question.id;
    this.updateData.question = question.question;
    this.updateData.title = question.title;
  }

  updateQuestion(): void {
      const payload = {
        question_id: this.updateData.question_id,
        question: this.updateData.question,
        title: this.updateData.title
      };

      fetch(`http://localhost:8080/question/updateByAuthor/${this.currentUser.cnp}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      })
      .then(response => {
          if (response.ok) {
            this.showSnackbar('Question updated successfully.');
            this.isUpdating = false;
            this.fetchQuestions();
          } else {
            this.showSnackbar('Failed to update question.');
          }
        })
        .catch(error => console.error('Error updating question:', error));

    }


  deleteQuestion(questionId: number): void {
      console.log(`Attempting to delete question with ID: ${questionId}`);
      fetch(`http://localhost:8080/question/deleteByAuthor/${questionId}?userId=${this.currentUser.cnp}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        },
      })
      .then(response => {
        console.log('Delete response received:', response);
        if (response.ok) {
          this.showSnackbar('Question deleted successfully.');
          this.fetchQuestions();
        } else {
          this.showSnackbar('Failed to delete question.');
          console.error('Failed to delete question. Status:', response.status, 'Text:', response.statusText);
        }
      })
      .catch(error => {
        console.error('Error deleting question:', error);
        this.showSnackbar('Error deleting question. Please try again.');
      });
  }


  cancelUpdate(): void {
    this.isUpdating = false;
    this.updateData = {
      question_id: null,
      question: '',
      title: ''
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
