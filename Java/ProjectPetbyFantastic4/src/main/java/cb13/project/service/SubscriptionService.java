/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.service;

import cb13.project.entities.Subscription;
import java.util.List;

/**
 *
 * @author tsepe
 */
public interface SubscriptionService {


    Subscription saveSubscription(Subscription subscription);

    Subscription findById(Long subscriptionId);

    void deleteSubscriptionById(Long subscriptionId);

    List<Subscription> findAllSubscriptions();
}
