package com.example.techshop.controller;

import com.example.techshop.model.OrderDetails;
import com.example.techshop.model.Product;
import com.example.techshop.service.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddToCartServlet", value = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private final DAO dao = new DAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productID = Integer.parseInt(req.getParameter("productID"));
        Product product = dao.selectProduct(productID);

        HttpSession session = req.getSession();
        Object object = session.getAttribute("cart");
        Map<Integer, OrderDetails> cart;

        if (object == null) {

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProduct(product);
            orderDetails.setQuantity(1);
            orderDetails.setPrice(product.getPrice());

            cart = new HashMap<>();
            cart.put(productID, orderDetails);

            session.setAttribute("cart", cart);
        } else {
            cart = (Map<Integer, OrderDetails>) object;
            OrderDetails orderDetails = cart.get(productID);

            if (orderDetails == null) {
                orderDetails = new OrderDetails();
                orderDetails.setPrice(product.getPrice());
                orderDetails.setProduct(product);
                orderDetails.setQuantity(1);
                cart.put(productID, orderDetails);
            } else if (product.getQuantity() == orderDetails.getQuantity()) {
                List<Product> products = dao.selectActiveProducts();
                req.setAttribute("products", products);
                req.setAttribute("productID", productID);

                product.setStatus(false);
                dao.updateProduct(productID, product);

                req.getRequestDispatcher("web/product.jsp").forward(req, resp);
            } else {
                orderDetails.setQuantity(orderDetails.getQuantity() + 1);
            }
            session.setAttribute("cart", cart);
            session.setAttribute("cartItemCount", cart.size());
        }
        List<Product> products = dao.selectActiveProducts();
        req.setAttribute("products", products);
        req.setAttribute("message", "Add to cart success");
        req.setAttribute("alertType", "success");
        req.setAttribute("userAction", true);
        req.getRequestDispatcher("web/product.jsp").forward(req, resp);
    }
}
