import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './homepage/home/home.component';
import { LoginComponent } from './auth-components/login/login.component';
import { SignupComponent } from './auth-components/signup/signup.component';
import { QuestionsComponent } from './page-components/questions/questions.component';
import { AnswersComponent } from './page-components/answers/answers.component';
import { AddQuestionsComponent } from './page-components/add-questions/add-questions.component';
import { AddAnswersComponent } from './page-components/add-answers/add-answers.component';
import { AdminViewComponent } from './page-components/admin-view/admin-view.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'questions', component: QuestionsComponent},
  {path: 'answers', component: AnswersComponent},
  {path: 'add-questions', component: AddQuestionsComponent},
  {path: 'add-answers', component: AddAnswersComponent},
  {path: 'admin', component: AdminViewComponent},
  {path: 'users', component: UsersComponent},
  {path: '**', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
