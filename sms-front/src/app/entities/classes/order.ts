import { OrderStatus } from '../enums/order-status'

export class Order {
    id: Number;
    date: Date;
    status: OrderStatus;
}