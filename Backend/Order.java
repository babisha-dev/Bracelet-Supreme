

public class Order {
    private int id;
    private int productId;
    private String customerName;
    private String productName;
    private String customerEmail;
    private String customerPhoneNo;
    private String address;
    private int quantity;
    private double totalPrice;
    private String status;
    private String orderDate;

    public Order(){

    }

    public Order(int id, int productId, String customerName, String productName, String customerEmail,
          String customerPhoneNo, String address, int quantity, double totalPrice, String status, String orderDate
    ){
        this.id=id;
        this.productId=productId;
        this.customerName=customerName;
        this.productName=productName;
        this.customerEmail=customerEmail;
        this.customerPhoneNo=customerPhoneNo;
        this.address=address;
        this.quantity=quantity;
        this.totalPrice=totalPrice;
        this.status=status;
        this.orderDate=orderDate;
    }

     public Order( int productId, String customerName, String productName, String customerEmail,
          String customerPhoneNo, String address, int quantity, double totalPrice, String status, String orderDate
    ){
       
        this.productId=productId;
        this.customerName=customerName;
        this.productName=productName;
        this.customerEmail=customerEmail;
        this.customerPhoneNo=customerPhoneNo;
        this.address=address;
        this.quantity=quantity;
        this.totalPrice=totalPrice;
        this.status=status;
        this.orderDate=orderDate;
    }

public int getId(){
    return id;
}
public int getProductId(){
    return productId;
}
public String getCustomerName(){
    return customerName;
}
public String getProductName(){
    return productName;
}
public String getCustomerEmail(){
    return customerEmail;
}
public String getCustomerPhoneNo(){
    return customerPhoneNo;
}
public String getAddress(){
    return address;
}
public int getQuantity(){
    return quantity;
}
public double getTotalPrice(){
    return totalPrice;
}
public String getStatus(){
    return status;
}
public String getOrderDate(){
    return orderDate;
}

public void setId(int id){
    this.id=id;
}
public void setProductId(int productId){
    this.productId=productId;
}
public void setCustomerName(String customerName){
    this.customerName=customerName;
}
public void setProductName(String productName){
    this.productName=productName;
}
public void setCustomerEmail(String customerEmail){
    this.customerEmail=customerEmail;
}
public void setCustomerPhoneNo(String customerPhoneNo){
    this.customerPhoneNo=customerPhoneNo;
}
public void setAddress(String address){
    this.address=address;
}
public void setQuantity(int quantity){
    this.quantity=quantity;
}
public void setTotalPrice(Double totalPrice){
    this.totalPrice=totalPrice;
}
public void setStatus(String status){
    this.status=status;
}
public void setOrderDate(String orderDate){
    this.orderDate=orderDate;
}
    
}