package repository;

import model.CookingClass;
import model.Subscription;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SubscriptionRepositoryFile extends SubscriptionRepositoryInMemory {
    private String FileName;
    private CookingClassRepo aRepo;
    private static int idGenerator=1;

    public SubscriptionRepositoryFile(String s, CookingClassRepo aRepo) {
        this.FileName = s;
        this.aRepo=aRepo;
        ReadFromFile();
    }

    private static int getNextId(){
        return idGenerator++;
    }

    private void ReadFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            String line=br.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator, starting from 0");
            }
            while((line = br.readLine())!=null) {
                String[] el = line.split(";");
                if (el.length != 4) {
                    System.err.println("Line is not valid." + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(el[0]);
                    int fId = Integer.parseInt(el[3]);
                    CookingClass f = aRepo.findById(fId);
                    Subscription o = new Subscription(id, el[1], el[2], f);
                    super.add(o);
                }
                catch(NumberFormatException nr) {
                    System.err.println("Id not valid." + el[0]+ nr); }
            }
        }
        catch(IOException ex) {
            throw new RepositoryException("Error" + ex); }
    }

    private void writeToFile()
    {
        try(PrintWriter pw = new PrintWriter(FileName))
        {
            pw.println(idGenerator);
            for(Subscription obj: findAll())
            {
                String str = obj.getId() + ";" + obj.getTname() + ";" + obj.getPhone() +";" + obj.getAddress()+ ";" + obj.getF();
                pw.println(str);
            }
        }catch(IOException ex) { throw new RepositoryException("error" + ex);
        }
    }

    @Override
    public Subscription add(Subscription m){
        m.setId(getNextId());
        super.add(m);
        writeToFile();
        return m;
    }

    @Override
    public void delete(Subscription m)
    {
        try  {
            super.delete(m);
            writeToFile();
        }
        catch(RuntimeException ex){throw new RepositoryException(ex);}
    }

    @Override
    public void update(Integer id, Subscription m)
    {
        try {
            super.update(id,m);
            writeToFile();
        }
        catch(RuntimeException ex) {throw new RepositoryException(ex);}
    }
}