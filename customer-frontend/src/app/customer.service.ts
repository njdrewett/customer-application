import { Injectable } from '@angular/core';
import { Customer } from './customer'
import { CreateCustomer } from './create.customer'
import { UpdateCustomer } from './update.customer'
import { CreateCustomerResponse } from './create.customer.response'
import { UpdateCustomerResponse } from './update.customer.response'
import { environment } from 'src/environments/environment'
import { Observable } from 'rxjs';
import { HttpClient } from "@angular/common/http"

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private customerRootUrl = environment.apiBaseUrl;
  private customerApiUrl = '/api/v1/customers';
  private customerUrl = this.customerRootUrl+this.customerApiUrl

  constructor(private http: HttpClient) { }

  public getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.customerUrl}`)
  }

  public getCustomersByLastName(lastName: string): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.customerUrl}/query?lastName=`+lastName)
  }

  public createCustomer(createCustomer: CreateCustomer): Observable<CreateCustomerResponse> {
    return this.http.post<CreateCustomerResponse>(`${this.customerUrl}`, createCustomer)
  }

  public updateCustomer(updateCustomer: UpdateCustomer): Observable<UpdateCustomerResponse> {
    return this.http.post<UpdateCustomerResponse>(`${this.customerUrl}/update`, updateCustomer)
  }

  public deleteCustomer(customerId: number): Observable<void> {
    return this.http.delete<void>(`${this.customerUrl}/${customerId}`)
  }
}
