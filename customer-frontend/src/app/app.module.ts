import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';

import { LOCALE_ID } from '@angular/core';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ { provide: LOCALE_ID, useValue: "en-gb" }],
  bootstrap: [AppComponent]
})
export class AppModule { }
