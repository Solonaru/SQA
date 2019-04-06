import { Address } from './address';
import { Subscription } from './subscription';

export class Customer {
    id: Number;
    username: String;
    password: String;
    name: String;
    email: String;
    phoneNumber: String;
    address: Address;
    deliveryAddresses: Address[];
    subscriptions: Subscription[];
}
