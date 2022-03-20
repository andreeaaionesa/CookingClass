package services;

import model.CookingClass;
import model.Subscription;
import repository.CookingClassRepo;
import repository.RepositoryException;
import repository.SubscriptionRepo;

import java.util.List;

public class Service{

    public CookingClassRepo ar;

    private SubscriptionRepo br;

    public Service(CookingClassRepo ar, SubscriptionRepo br) {
        this.ar = ar;
        this.br = br;
    }

    public int addF(String name, String type, String price, String startig_date, int max_n) throws ServicesException{
        try {
            CookingClass f = new CookingClass(name, type, price, startig_date,max_n);
            CookingClass newf = ar.add(f);
            return newf.getId();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding cake"+ex);
        }
    }

    public List<CookingClass> getFByName(String name){
        return ar.findByName(name);
    }

    public List<Subscription> getSubscriptionByCooking(String name) {
        return br.filterByF(name);
    }

    public void addTeam(String tname, String phone, String address, CookingClass f) throws ServicesException{
        try {
            Subscription en = new Subscription(tname, phone, address ,f);
            ar.update(f.getId(), f);
            br.add(en);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding registration"+er);
        }
    }
}

