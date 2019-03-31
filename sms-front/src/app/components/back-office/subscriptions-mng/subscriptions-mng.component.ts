import { Component, OnInit } from '@angular/core';
import { Subscription } from '../../../entities/classes/subscription';
import { SubscriptionService } from '../../../providers/services/subscription.service';
import { Observable, of } from "rxjs";
import { Customer } from '../../../entities/classes/customer';

@Component({
  selector: 'app-subscriptions-mng',
  templateUrl: './subscriptions-mng.component.html',
  styleUrls: ['./subscriptions-mng.component.css']
})
export class SubscriptionsMngComponent implements OnInit {

  subscription: Subscription;
  subscriptions: Subscription[];

  constructor(private subscriptionService: SubscriptionService) { }

  ngOnInit() {
    this.populateSubscriptions();
  }

  populateSubscriptions() {
    this.subscriptionService.getActiveSubscriptions().subscribe(data => { this.subscriptions = data; });
  }

  getSubscribers(subscription: Subscription): Customer[] {
    return subscription.customers;
  }

  onAdd() {
    this.subscription = new Subscription();
  }

  onUpdate(subscription: Subscription) {
    this.subscription = subscription;
  }

  onDelete(subscription: Subscription) {
    this.subscription = subscription;
  }

  onConfirmDelete() {
    this.subscriptionService.deleteSubscription(this.subscription).subscribe(data => { location.reload(); });
  }

  onSubmit() {
    if (this.subscription.id != undefined) {
      this.updateSubscription();
    } else {
      this.insertSubscription();
    }
  }

  insertSubscription() {
    this.subscriptionService.insertSubscription(this.subscription).subscribe(date => { location.reload(); });
  }

  updateSubscription() {
    this.subscriptionService.updateSubscription(this.subscription).subscribe();
  }

  private setting = {
    element: {
      dynamicDownload: null as HTMLElement
    }
  }

  downloadTxt(subscription: Subscription) {
    var text = "";
    for (var subscriber of Array.from(this.getSubscribers(subscription).values())) {
      text += subscriber.name + ", " + subscriber.email + "\r\n";
    }
    this.dyanmicDownloadByHtmlTag({
      fileName: 'Subscribers',
      text: text
    });
  }

  private dyanmicDownloadByHtmlTag(arg: {
    fileName: string,
    text: string
  }) {
    if (!this.setting.element.dynamicDownload) {
      this.setting.element.dynamicDownload = document.createElement('a');
    }

    const element = this.setting.element.dynamicDownload;

    var textToSend = encodeURIComponent(arg.text);
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + textToSend);
    element.setAttribute('download', arg.fileName);

    var event = new MouseEvent("click");
    element.dispatchEvent(event);
  }

}
