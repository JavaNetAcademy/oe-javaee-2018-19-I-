package hu.javagladiators.education.hoe.settlementtype.web.controllers;

import hu.javagladiators.education.hoe.base.service.interfaces.BaseService;
import hu.javagladiators.education.hoe.settlementtype.dao.models.SettlementType;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SettlementTypeDeleteServlet", urlPatterns = {"/settlementtype/delete"})
public class SettlementTypeDeleteServlet extends HttpServlet{

    @Inject
    private BaseService<SettlementType> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        if(req.getParameter("id")!=null){
            long id=Long.parseLong(req.getParameter("id"));
            service.delete(id);
        }
        getServletContext().getRequestDispatcher("/settlementtype/main").include(req, resp);
    }
    
    
    
}
