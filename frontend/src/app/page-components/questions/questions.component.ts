import { Component, OnInit } from '@angular/core';
import { format} from 'date-fns';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {
  crtUser: any;
  questions: any[] = [];

  ngOnInit(): void {
    if (typeof localStorage !== 'undefined') {
      this.crtUser = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user') as string) : {};
    } else {
      // Handle the case where localStorage is not available
      this.crtUser = {}; // Or any other default value
    }

    fetch( 'http://localhost:8080/question/getAll', {
      method: 'GET', 
      headers: {
        'Content-Type': 'application/json'   },
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
      this.questions = data; // Store data to variable
      console.log('Data:', data);
    })
    .catch(error => {
      // Handle errors
      console.error('There was a problem with the fetch operation:', error);
    });

  }

  toggleAnswer(question: any): void {
    question.showAnswer = !question.showAnswer;
  }

  formatedDate(date: string): string {
    return format(new Date(date), 'MMMM dd, yyyy');
  }
}
