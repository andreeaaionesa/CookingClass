package repository;

import model.CookingClass;

import java.util.List;

public interface CookingClassRepo extends Repository<Integer, CookingClass>{
    List<CookingClass> findByName(String name);
    List<CookingClass> findAllcookingclasses();
}
