package hu.javagladiators.education.hoe.materials.web.controllers;

import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.hoe.base.service.interfaces.BaseService;
import hu.javagladiators.education.hoe.materials.dao.models.Materials;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author krisztian
 */
@WebServlet(name = "MaterialsModifyServlet", urlPatterns = {"/materials/modify"})
public class MaterialsModifyServlet extends HttpServlet{
    private static final String MODIFYID="modifymaterialsid";

    @Inject
    private BaseService<Materials> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        if(req.getParameter("id")!=null){
            long id=Long.parseLong(req.getParameter("id"));
            req.setAttribute("item",service.getById(id));
            req.getSession().setAttribute(MODIFYID,id);
        }
        getServletContext().getRequestDispatcher("/materials/modify.jsp").include(req, resp);
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        Materials materials = new Materials();
        materials.setName(req.getParameter("name"));
        materials.setDescription(req.getParameter("description"));
        try{
            service.modify(Long.parseLong(""+req.getSession().getAttribute(MODIFYID)),materials);
            req.setAttribute("items", service.getAll());
            getServletContext().getRequestDispatcher("/materials/index.jsp").include(req, resp);
        }
        catch(BusinessException e){
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/materials/error/error.jsp").include(req, resp);
        }
        
    }
    
    
}
