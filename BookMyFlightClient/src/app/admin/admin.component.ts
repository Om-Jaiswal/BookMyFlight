import { Component } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  activeTabIndex: number = 0;

  onTabChange(event: any): void {
    this.activeTabIndex = event.index;
  }

  isActiveTab(index: number): boolean {
    return this.activeTabIndex === index;
  }

}
