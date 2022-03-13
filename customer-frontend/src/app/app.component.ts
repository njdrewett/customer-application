import { Component, OnInit } from '@angular/core';
import { Customer } from './customer'
import { CreateCustomer } from './create.customer'
import { UpdateCustomer } from './update.customer'
import { CreateCustomerResponse } from './create.customer.response'
import { UpdateCustomerResponse } from './update.customer.response'
import { CustomerService } from './customer.service'
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { LOCALE_ID } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'customer-frontend';
  public customers: Customer[];
  public editCustomer: Customer | undefined;
  public editBirthdate: any | undefined;
  public deleteCustomer: Customer | undefined;

  constructor(private customerService: CustomerService) {
    this.customers = [];
  }

  ngOnInit() {
    this.getCustomers();
  }

  public getCustomers(): void {
    this.customerService.getCustomers().subscribe(
    (response: Customer[]) => {
      this.customers = response}
    ),
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  }

  public onOpenModal( mode:string, customer?:Customer):void {
    console.log("in OnOpenModal");
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle','modal');
    if(mode === 'add' ) {
      console.log("add clicked");
      button.setAttribute('data-target', '#addCustomerModal');
    } else if(mode === 'edit' ) {
      console.log("edit clicked");
      this.editCustomer = customer!;
      var datePipe = new DatePipe("en-gb");
      this.editBirthdate = datePipe.transform(customer!.dateOfBirth, 'dd/MM/yyyy');
      button.setAttribute('data-target', '#editCustomerModal');
    } else if(mode === 'delete' ) {
      this.deleteCustomer = customer;
      console.log("Delete clicked");
      button.setAttribute('data-target', '#deleteCustomerModal');
    }
    console.log("container" + container);
    container!.appendChild(button);
    button.click();
  }

  public onAddCustomer(addForm: NgForm): void {
  const addFormElement = document.getElementById('add-customer-form');
  addFormElement!.click();
    this.customerService.createCustomer(addForm.value)!.subscribe(
    (response:CreateCustomerResponse) => {
      console.log(response);
      this.getCustomers();
      addForm.reset();
    },
    (error: HttpErrorResponse) => {
      alert(error.message)
      }
    );
  }

  public onUpdateCustomer(customer: Customer): void {
    this.customerService.updateCustomer(customer)!.subscribe(
    (response:UpdateCustomerResponse) => {
      console.log(response);
      this.getCustomers();
    },
    (error: HttpErrorResponse) => {
      alert(error.message)
      }
    );
  }

  public onDeleteCustomer(customerId: number): void {
    this.customerService.deleteCustomer(customerId)!.subscribe(
    (response:void) => {
      console.log("delete customer" + customerId);
      this.getCustomers();
    },
    (error: HttpErrorResponse) => {
      alert(error.message)
      }
    );
  }

  public searchCustomers(key: string) : void {
     if(!key) {
      // do nothing
     } else {
     this.customerService.getCustomersByLastName(key)!.subscribe(
     (response:Customer[]) => {
       console.log("Find customer" + key);
       this.customers = response;
     },
     (error: HttpErrorResponse) => {
       alert(error.message)
       }
     );
   }
   }
}
