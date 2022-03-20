package repository;

import model.Subscription;

import java.util.List;

public interface SubscriptionRepo extends Repository<Integer, Subscription> {
    List<Subscription> filterByF(String name);
}
