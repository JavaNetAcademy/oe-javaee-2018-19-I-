package hu.javagladiators.education.hoe.settlementtype.web.controllers;

import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.hoe.base.service.interfaces.BaseService;
import hu.javagladiators.education.hoe.settlementtype.dao.models.SettlementType;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SettlementTypeServlet", urlPatterns = {"/settlementtype/main"})
public class SettlementTypeServlet extends HttpServlet{

    @Inject
    private BaseService<SettlementType> service;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        req.setAttribute("items", service.getAll());
        getServletContext().getRequestDispatcher("/settlementtype/index.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SettlementType settlementtype = new SettlementType();
        settlementtype.setName(req.getParameter("name"));
        settlementtype.setDescription(req.getParameter("description"));
        try{
            service.create(settlementtype);
            doGet(req, resp);
        }
        catch(BusinessException e){
            getServletContext().getRequestDispatcher("/settlementtype/error/error.jsp").include(req, resp);
        }

    }
    
    
}
