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
@WebServlet(name = "EndowmentModifyServlet", urlPatterns = {"/endowment/modify"})
public class EndowmentModifyServlet extends HttpServlet{
    private static final String MODIFYID="modifypetsid";

    @Inject
    private BaseService<Endowment> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        if(req.getParameter("id")!=null){
            long id=Long.parseLong(req.getParameter("id"));
            req.setAttribute("item",service.getById(id));
            req.getSession().setAttribute(MODIFYID,id);
        }
        getServletContext().getRequestDispatcher("/endowment/modify.jsp").include(req, resp);
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        Endowment endowment = new Endowment();
        endowment.setName(req.getParameter("name"));
        endowment.setType(req.getParameter("type"));
        endowment.setDesc(req.getParameter("desc"));
        try{
            service.modify(Long.parseLong(""+req.getSession().getAttribute(MODIFYID)),endowment);
            req.setAttribute("items", service.getAll());
            getServletContext().getRequestDispatcher("/endowment/index.jsp").include(req, resp);
        }
        catch(BusinessException e){
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/endowment/error/error.jsp").include(req, resp);
        }
    } 
}
