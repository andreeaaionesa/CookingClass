package repository;

import model.Subscription;

import java.util.List;
import java.util.stream.Collectors;


public class SubscriptionRepositoryInMemory extends AbstractRepository<Integer, Subscription> implements SubscriptionRepo {
    @Override
    public List<Subscription> filterByF(String name) {
        return getAll().stream().filter(x->x.getF().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
