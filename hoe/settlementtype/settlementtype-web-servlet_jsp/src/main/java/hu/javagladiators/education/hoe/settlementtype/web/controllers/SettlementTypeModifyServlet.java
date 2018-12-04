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


@WebServlet(name = "SettlementTypeModifyServlet", urlPatterns = {"/settlementtype/modify"})
public class SettlementTypeModifyServlet extends HttpServlet{
    private static final String MODIFYID="modifysettlementtypeid";

    @Inject
    private BaseService<SettlementType> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        if(req.getParameter("id")!=null){
            long id=Long.parseLong(req.getParameter("id"));
            req.setAttribute("item",service.getById(id));
            req.getSession().setAttribute(MODIFYID,id);
        }
        getServletContext().getRequestDispatcher("/settlementtype/modify.jsp").include(req, resp);
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        SettlementType settlementtype = new SettlementType();
        settlementtype.setName(req.getParameter("name"));
        settlementtype.setDescription(req.getParameter("description"));
        try{
            service.modify(Long.parseLong(""+req.getSession().getAttribute(MODIFYID)),settlementtype);
            req.setAttribute("items", service.getAll());
            getServletContext().getRequestDispatcher("/settlementtype/index.jsp").include(req, resp);
        }
        catch(BusinessException e){
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/settlementtype/error/error.jsp").include(req, resp);
        }
        
    }
    
    
}
