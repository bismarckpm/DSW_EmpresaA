import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contestar-encuesta',
  templateUrl: './contestar-encuesta.component.html',
  styleUrls: ['./contestar-encuesta.component.css']
})
export class ContestarEncuestaComponent implements OnInit {

  preguntas = [{type: "name", description: "Cual es tu nombre?", isHidden: false},
  {type: "email", description: "Cual es tu correo?", isHidden: true},
  {type: "message", description: "Cual es tu mensaje al mundo?", isHidden: true}]
  constructor() { }

  ngOnInit(): void {
  }

}
