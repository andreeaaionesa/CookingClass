package model;

public class CookingClass implements Identifiable <Integer>
{ private int Id,max_n;
  private String name, type, price,starting_date;
  public CookingClass(){
      Id=0;
      this.name =" ";
      this.type =" ";
      this.price =" ";
      this.starting_date =" ";
      this.max_n=0;
  }
    public CookingClass(int Id, String name, String type, String price, String starting_date, int max_n )
    {
        this.Id=Id;
        this.name=name;
        this.type=type;
        this.price=price;
        this.starting_date=starting_date;
        this.max_n=max_n;
    }
    public CookingClass(String name, String type, String price, String starting_date, int max_n  )
    {
        this.name=name;
        this.type=type;
        this.price=price;
        this.starting_date=starting_date;
        this.max_n=max_n;
    }
    public Integer getId()
    {return Id;}

    public String getName()
    { return name; }

    public String getType()
    {return type;}

    public String getPrice()
    {return price;}

    public String getStarting_date()
    {return starting_date;}

    public Integer getMax_n()
    {return max_n;}


    public void setId(Integer Id)
    { this.Id=Id; }

    public void setName(String name)
    { this.name=name; }

    public void setType(String type)
    {this.type=type;}

    public void setStarting_date(String starting_date)
    {this.starting_date=starting_date;}

    public void setMax_n(Integer max_n)
    { this.max_n=max_n; }

    public String toString()
    {String str = Id + "," + name + "," + type + "," + price + "," + starting_date + "," + max_n;
    return  str;}
}
