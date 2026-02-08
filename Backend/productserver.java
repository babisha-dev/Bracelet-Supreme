
import com.sun.net.httpserver.*;
import com.google.gson.Gson;

import java.net.InetSocketAddress;
import java.util.List;

public class productserver {

    static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {

        productdb.createTable();
        Orderdb.createTable(); 

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/products", exchange -> {

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("POST".equals(exchange.getRequestMethod())) {
                String body = new String(exchange.getRequestBody().readAllBytes());
                Products p = gson.fromJson(body, Products.class);
                productdb.addProduct(p);

                exchange.sendResponseHeaders(201, 0);
                exchange.getResponseBody().close();
            }
            if("PUT".equals(exchange.getRequestMethod())){
                String body=new String(exchange.getRequestBody().readAllBytes());
                Products p=gson.fromJson(body, Products.class);
                productdb.UpdateProduct(p);
                exchange.sendResponseHeaders(200, 0);
                exchange.getResponseBody().close();

            }
            if ("GET".equals(exchange.getRequestMethod())) {
                List<Products> products = productdb.getAllProducts();
                String json = gson.toJson(products);

                exchange.sendResponseHeaders(200, json.length());
                exchange.getResponseBody().write(json.getBytes());
                exchange.close();
            }
            if("DELETE".equals(exchange.getRequestMethod())){
                String body=new String(exchange.getRequestBody().readAllBytes());
                Products p=gson.fromJson(body, Products.class);
                int ProductId=p.getid();

                System.out.println("Deleting product");
                productdb.deleteProduct(ProductId);
                exchange.sendResponseHeaders(200, 0);
                exchange.getResponseBody().close();
                
            }

        });
        server.createContext("/api/orders", exchange ->{
               exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
               exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
               exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "content-type");

               if("OPTIONS".equals(exchange.getRequestMethod())){
                exchange.sendResponseHeaders(204, -1);
                return;
               }
        
        if ("POST".equals(exchange.getRequestMethod())) {
                String body = new String(exchange.getRequestBody().readAllBytes());
                Order order = gson.fromJson(body, Order.class);
                Orderdb.addOrder(order);

                String response = "{\"success\": true, \"message\": \"Order placed successfully\"}";
                exchange.sendResponseHeaders(201, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            }

          
            if ("GET".equals(exchange.getRequestMethod())) {
                List<Order> orders = Orderdb.getAllOrders();
                String json = gson.toJson(orders);

                exchange.sendResponseHeaders(200, json.length());
                exchange.getResponseBody().write(json.getBytes());
                exchange.close();
            }

            
            if ("PUT".equals(exchange.getRequestMethod())) {
                String body = new String(exchange.getRequestBody().readAllBytes());
                Order order = gson.fromJson(body, Order.class);
                Orderdb.UpdateOrderStatus(order.getId(), order.getStatus());

                String response = "{\"success\": true, \"message\": \"Order status updated\"}";
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            }

           
            if ("DELETE".equals(exchange.getRequestMethod())) {
                String body = new String(exchange.getRequestBody().readAllBytes());
                Order order = gson.fromJson(body, Order.class);
                Orderdb.deleteOrder(order.getId());

                String response = "{\"success\": true, \"message\": \"Order deleted\"}";
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            }

        });
 

        server.start();
        System.out.println(" Server running on 8080");
    }
}

