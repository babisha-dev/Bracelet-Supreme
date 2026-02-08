
public class Products {
    private int id;
    private String name;
    private String category;
    private double price;
    private String img;
  public Products() {
    }
    public Products(int id, String name, String category, double price, String img){
        this.id=id;
        this.name=name;
        this.category=category;
        this.price=price;
        this.img=img;

    }
     public Products(String name, String category, double price, String img){
        this.name=name;
        this.category=category;
        this.price=price;
        this.img=img;

    }

    public int getid(){
        return id;
    }
    public String getname(){
        return name;
    }
    public String getcategory(){
        return category;
    }
    public double getprice(){
        return price;
    }
  public String getimg(){
    return img;
  }

  public void setid(int id){
      this.id=id;
  }
 public void setname(String name){
  if(name==null || name.trim().isEmpty()){
    throw new IllegalArgumentException("Name cannot be empty");
  }
  this.name=name;
 }
 public void setcategory(String category){
    if(category==null || category.trim().isEmpty()){
        throw new IllegalArgumentException("Category cannot be empty ");
    }
    this.category=category;

 }
 public void setprice(double price){
    if(price<0){
        throw new IllegalArgumentException("Price cannot be negative");
    }
    this.price=price;
 }
 public void setimg(String img){
    
    this.img=img;

 }

    
}
