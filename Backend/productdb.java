
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class productdb {
    public static void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS products (\n" +
                     "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                     "  name TEXT,\n" +
                     "  category TEXT,\n" +
                     "  price REAL,\n" +
                     "  img TEXT\n" +  
                     ");";

        try(Connection con = DBconnection.getConnection();
            Statement stmt = con.createStatement()){
            stmt.execute(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void addProduct(Products p){
        String sql = "INSERT INTO products(name, category, price, img) VALUES(?,?,?,?)";  
        try(Connection con = DBconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, p.getname());
            ps.setString(2, p.getcategory());
            ps.setDouble(3, p.getprice());
            ps.setString(4, p.getimg());  
            
            ps.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteProduct(int id){
       String sql="DELETE FROM products WHERE id=?";
       try(Connection con=DBconnection.getConnection();
           PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,id);

            ps.executeUpdate();
            System.out.println("Product Deleted :"+id);
           }
           catch(Exception e){
            System.out.print("Error occured, Not Deleted");
            e.printStackTrace();
           }
    }

    public static void UpdateProduct(Products p){
      String sql= "UPDATE products SET name=?, category=? , price=?, img=? WHERE ID=?";
      try(Connection con=DBconnection.getConnection();
    PreparedStatement ps=con.prepareStatement(sql)){
      ps.setString(1,p.getname());
      ps.setString(2, p.getcategory());
      ps.setDouble(3, p.getprice() );
      ps.setString(4, p.getimg());
      ps.setInt(5, p.getid()); 

      ps.executeUpdate();
      System.out.println("Updated");

    }
    catch(Exception e)
{
  e.printStackTrace();
}    }
    public static List<Products> getAllProducts(){
        List<Products> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try(Connection con = DBconnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                list.add(new Products(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getString("img")  
                ));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}