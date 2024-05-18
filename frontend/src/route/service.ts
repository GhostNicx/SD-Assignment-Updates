// route.service.ts
import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class RouteService {
    currentRoute: string = ''; // Assign an initial value to the 'currentRoute' property.

    constructor(private router: Router) {
        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.currentRoute = event.url;
            }
        });
    }
}
