import { Component, OnInit } from '@angular/core';
import { Subscription } from '../../../entities/classes/subscription';
import { SubscriptionService } from '../../../providers/services/subscription.service';

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

}
