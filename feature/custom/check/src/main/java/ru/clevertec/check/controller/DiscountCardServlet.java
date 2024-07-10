package ru.clevertec.check.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.exception.NotFoundException;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.repository.DB.DiscountCardRepositoryImpl;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.impl.DiscountCardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/discountcards")
public class DiscountCardServlet extends HttpServlet {
    private DiscountCardService discountCardService;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        this.discountCardService = new DiscountCardServiceImpl(new DiscountCardRepositoryImpl());
        this.objectMapper = new ObjectMapper();
    }

    //   http://localhost:8080/discountcards?id=1
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        DiscountCard discountCard = null;
        try {
            discountCard = discountCardService.getById(id);
        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException | NotFoundException e) {
            resp.setStatus(Integer.parseInt(e.getMessage()));
        }
        objectMapper.writeValue(resp.getWriter(), discountCard);
    }

    //   http://localhost:8080/discountcards?id=1
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            if (discountCardService.delete(id)) {
                resp.setStatus(200);
            } else resp.setStatus(404);
        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException | NotFoundException e) {
            resp.setStatus(Integer.parseInt(e.getMessage()));
        }
    }

//    //http://localhost:8080/discountcards
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException {
//        String res = req.getAttribute("body").toString();
//        DiscountCard discountCard = objectMapper.readValue(res, DiscountCard.class);
//        try {
//            discountCardService.create(discountCard);
//        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException | NotFoundException e) {
//            resp.setStatus(Integer.parseInt(e.getMessage()));
//        }
//        resp.setStatus(200);
//    }
//
//    //http://localhost:8080/discountcards?id=1
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException {
//        String res = req.getAttribute("body").toString();
//        DiscountCard discountCard = objectMapper.readValue(res, DiscountCard.class);
//        discountCard.setId(Integer.parseInt(req.getParameter("id")));
//        try {
//            discountCardService.update(discountCard);
//        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException | NotFoundException e) {
//            resp.setStatus(Integer.parseInt(e.getMessage()));
//        }
//        resp.setStatus(200);
//    }
}