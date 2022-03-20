package model;

public class Subscription implements Identifiable <Integer>
{ private int Id;
  private String tname, phone, address;
  private CookingClass f;

  public Subscription(int id, String phone, String address, CookingClass f)
  {
      this.Id=0;
      this.tname=" ";
      this.phone=" ";
      this.address=" ";
      this.f=new CookingClass();
  }

  public Subscription(int Id, String tname, String phone, String address, CookingClass f)
  {
      this.Id=Id;
      this.tname=tname;
      this.phone=phone;
      this.address=address;
      this.f=f;
  }

    public Subscription(String tname, String phone, String address, CookingClass f)
    {

        this.tname=tname;
        this.phone=phone;
        this.address=address;
        this.f=f;
    }

    public Integer getId()
    { return Id; }

    public String getTname()
    { return tname; }

    public String getPhone()
    { return phone;}

    public String getAddress()
    {return address;}

    public CookingClass getF()
    {return f;}

    public void setId(Integer Id)
    {this.Id=Id;}

    public void setTname(String tname)
    {this.tname=tname;}

    public void setPhone(String phone)
    {this.phone=phone;}

    public void setAddress(String address)
    {this.address=address;}

    public void setF(CookingClass f)
    {this.f=f;}

    public String toString(){
        String str = Id + "," + tname +","+ phone + "," + address+ ','+ f;
        return str;

    }







}
