package repository;
import model.CookingClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CookingClassRepositoryFile extends CookingClassRepositoryInMemory {
    private String FileName;
    private static int idGenerator=1;
    public CookingClassRepositoryFile(String s) {
        this.FileName = s;
        ReadFromFile();
    }

    private void ReadFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            //String line = null;
            String line=br.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid value for idGenerator, starting from 0");
            }
            while((line = br.readLine())!=null) {
                String[] el = line.split(";");
                if (el.length != 6) {
                    System.err.println("Line is not valid." + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(el[0]);
                    int d = Integer.parseInt(el[5]);

                    CookingClass obj = new CookingClass(id, el[1], el[2], el[3], el[4],d);
                    super.add(obj);
                } catch(NumberFormatException nr) {
                    System.err.println("Id not valid." + el[0]); }
            }
        }
        catch(IOException ex) {
            throw new RepositoryException("Error reading" + ex); }
    }

    private void writeToFile()
    {
        try(PrintWriter pw = new PrintWriter(FileName))
        {
            pw.println(idGenerator);
            for(CookingClass obj: findAll())
            {
                String str = obj.getId() + ";" + obj.getName() + ";" + obj.getType() +";" + obj.getPrice() + ";" + obj.getStarting_date() + ";" + obj.getMax_n();
                pw.println(str);
            }
        }catch(IOException ex) { throw new RepositoryException("error" + ex);
        }

    }

    @Override
    public CookingClass add(CookingClass f){
        f.setId(getNextId());
        super.add(f);
        writeToFile();
        return f;
    }

    private static int getNextId(){
        return idGenerator++;
    }

    @Override
    public void delete(CookingClass m)
    {
        try  {
            super.delete(m);
            writeToFile();
        }
        catch(RuntimeException ex){throw new RepositoryException(ex);}
    }

    @Override
    public void update(Integer id, CookingClass f)
    {
        try {
            super.update(id,f);
            writeToFile();
        }
        catch(RuntimeException ex) {throw new RepositoryException(ex);}
    }
}
