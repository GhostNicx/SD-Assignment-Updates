import { Component } from '@angular/core';
import { RouteService } from '../../../route/service'; // Add missing import statement

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'] // Fix the property name to 'styleUrls'
})
export class HomeComponent {
  constructor(public routeService: RouteService) {}

}
