import { Customer } from './customer';

export class Subscription {
    id: Number;
    type: String;
    description: String;
    subscriptionsCount: Number;
    customers: Customer[];
}
