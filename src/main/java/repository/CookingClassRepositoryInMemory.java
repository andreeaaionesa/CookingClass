package repository;

import model.CookingClass;

import java.util.List;
import java.util.stream.Collectors;

public class CookingClassRepositoryInMemory extends AbstractRepository< Integer, CookingClass> implements CookingClassRepo {

    @Override
    public List<CookingClass> findAllcookingclasses() {
        return getAll().stream().collect(Collectors.toList());
    }
    @Override
    public List<CookingClass> findByName(String name) {
        return getAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
