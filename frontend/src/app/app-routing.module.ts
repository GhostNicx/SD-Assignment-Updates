import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './homepage/home/home.component';
import { LoginComponent } from './auth-components/login/login.component';
import { SignupComponent } from './auth-components/signup/signup.component';
import { QuestionsComponent } from './page-components/questions/questions.component';
import { AnswersComponent } from './page-components/answers/answers.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'questions', component: QuestionsComponent},
  {path: 'answers', component: AnswersComponent},
  {path: '**', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
