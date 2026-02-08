import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Orderdb {
    public static void createTable(){
        String sql= "CREATE TABLE IF NOT EXISTS orders (\n" +
                     "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                     "  productId INTEGER,\n" +
                     "  productName TEXT,\n" +
                     "  customerName TEXT,\n" +
                     "  customerEmail TEXT,\n" +
                     "  customerPhoneNo TEXT,\n" +
                     "  address TEXT,\n" +
                     "  quantity INTEGER,\n" +
                     "  totalPrice REAL,\n" +
                     "  status TEXT,\n" +
                     "  orderDate TEXT\n" +
                     ");";

        try(Connection con=DBconnection.getConnection();
            Statement stmt= con.createStatement()){
                stmt.execute(sql);
                 System.out.println("Table created");
            }
            catch(Exception e){
                      e.printStackTrace();
            }
    }

   public static void addOrder(Order order){
    String sql="INSERT INTO orders(productId, productName, customerName, customerEmail, customerPhoneNo, address, quantity, totalPrice,status,orderDate) VALUES (?,?,?,?,?,?,?,?,?,?)";
    try(Connection con= DBconnection.getConnection();
    PreparedStatement ps=con.prepareStatement(sql)){
        ps.setInt(1, order.getProductId());
        ps.setString(2,order.getProductName());
        ps.setString(3, order.getCustomerName());
        ps.setString(4, order.getCustomerEmail());
        ps.setString(5, order.getCustomerPhoneNo());
        ps.setString(6, order.getAddress());
        ps.setInt(7, order.getQuantity());
        ps.setDouble(8, order.getTotalPrice());
        ps.setString(9, order.getStatus());
        ps.setString(10, order.getOrderDate());

ps.executeUpdate();
    }
catch(Exception e){
    e.printStackTrace();
}
   }

   public static  List<Order> getAllOrders(){
         List<Order> orders=new ArrayList<>();
         String sql="SELECT  * FROM orders ORDER BY id DESC";
         try(Connection con=DBconnection.getConnection();
           Statement stmt=con.createStatement();
        ResultSet rs= stmt.executeQuery(sql)){
            while(rs.next()){
                orders.add(new Order(
                rs.getInt("id"),
                    rs.getInt("productId"),
                  rs.getString("customerName"),
                    rs.getString("productName"),
                    rs.getString("customerEmail"),
                    rs.getString("customerPhoneNo"),
                    rs.getString("address"),
                    rs.getInt("quantity"),
                    rs.getDouble("totalPrice"),
                    rs.getString("status"),
                    rs.getString("orderDate")
                ));
            }
           }
           catch(Exception e){
            e.printStackTrace();
           }
           return orders;
   }

   public static void UpdateOrderStatus(int orderId, String status){
    String  sql="UPDATE  orders SET status=? WHERE id=?";   
    try(Connection con=DBconnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)
    ){
        ps.setString(1, status);
        ps.setInt(2,orderId);
       
        ps.executeUpdate();
         System.out.print("Order Updated");
    }
    catch(Exception e){
        e.printStackTrace();
    }
   }
   public static void deleteOrder(int id){
    String sql="DELETE FROM orders WHERE id=?";
    try(Connection con=DBconnection.getConnection();
      PreparedStatement ps=con.prepareStatement(sql)){
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.print("Deleted");
      }
      catch(Exception e){
        e.printStackTrace();
      }
   }

}
