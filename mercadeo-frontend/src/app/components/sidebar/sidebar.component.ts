import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  openNav() {
    let bar = document.getElementById('mySidebar');
    let main = document.getElementById('main');
    if (bar) {
      bar.style.width = '250px';
    }
    if (main) {
      main.style.marginLeft = '250px';
    }
  }

  closeNav() {
    let bar = document.getElementById('mySidebar');
    let main = document.getElementById('main');
    if (bar) {
      bar.style.width = '0';
    }
    if (main) {
      main.style.marginLeft = '0';
    }
  }

}
