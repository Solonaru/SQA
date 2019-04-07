export class Feedback {
    id: Number;
    userName: String;
    userEmail: String;
    subject: String;
    message: String;

    constructor(userName: String, userEmail: String, subject: String, message: String) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.subject = subject;
        this.message = message;
    }
}
