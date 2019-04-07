import { AccountType } from '../enums/account-type';

export class User {
    id: Number;
    username: String;
    password: String;
    accountType: AccountType;
}