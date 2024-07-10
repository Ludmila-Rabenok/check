package ru.clevertec.check.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.exception.NotFoundException;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.repository.DB.ProductRepositoryImpl;
import ru.clevertec.check.service.ProductService;
import ru.clevertec.check.service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() {
        this.productService = new ProductServiceImpl(new ProductRepositoryImpl());
    }

    //   http://localhost:8080/products?id=1
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = null;
        try {
            product = productService.getById(id);
        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException | NotFoundException e) {
            resp.setStatus(Integer.parseInt(e.getMessage()));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), product);
    }

    //   http://localhost:8080/products?id=1
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            if (productService.delete(id)) {
                resp.setStatus(200);
            } else resp.setStatus(404);
        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException | NotFoundException e) {
            resp.setStatus(Integer.parseInt(e.getMessage()));
        }
    }

//    //http://localhost:8080/products
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String res = req.getAttribute("body").toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        Product product = objectMapper.readValue(res, Product.class);
//        try {
//            productService.create(product);
//        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException | NotFoundException e) {
//            resp.setStatus(Integer.parseInt(e.getMessage()));
//        }
//        resp.setStatus(200);
//    }
//
//    //http://localhost:8080/products?id=1
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException {
//        String res = req.getAttribute("body").toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        Product product = objectMapper.readValue(res, Product.class);
//        product.setId(Integer.parseInt(req.getParameter("id")));
//        try {
//            productService.update(product);
//        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException | NotFoundException e) {
//            resp.setStatus(Integer.parseInt(e.getMessage()));
//        }resp.setStatus(200);
//    }
}
