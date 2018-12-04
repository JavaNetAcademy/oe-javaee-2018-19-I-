package hu.javagladiators.education.hoe.endowment.web.controllers;

import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.hoe.base.service.interfaces.BaseService;
import hu.javagladiators.education.hoe.endowment.dao.models.Endowment;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Katona Vera
 */
@WebServlet(name = "EndowmentServlet", urlPatterns = {"/endowment/main"})
public class EndowmentServlet extends HttpServlet{
    @Inject
    private BaseService<Endowment> service;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        req.setAttribute("items", service.getAll());
        getServletContext().getRequestDispatcher("/endowment/getall.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Endowment endowment = new Endowment();
        endowment.setName(req.getParameter("name"));
        endowment.setType(req.getParameter("type"));
        endowment.setDesc(req.getParameter("desc"));
        try{
            service.create(endowment);
            doGet(req, resp);
        }
        catch(BusinessException e){
            getServletContext().getRequestDispatcher("/endowment/error/error.jsp").include(req, resp);
        }
    }
}
