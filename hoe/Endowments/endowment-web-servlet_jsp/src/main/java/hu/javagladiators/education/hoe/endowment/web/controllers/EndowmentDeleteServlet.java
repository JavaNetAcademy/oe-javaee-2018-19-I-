package hu.javagladiators.education.hoe.endowment.web.controllers;

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
@WebServlet(name = "EndowmentDeleteServlet", urlPatterns = {"/endowment/delete"})
public class EndowmentDeleteServlet extends HttpServlet{
    @Inject
    private BaseService<Endowment> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        if(req.getParameter("id")!=null){
            long id=Long.parseLong(req.getParameter("id"));
            service.delete(id);
        }
        getServletContext().getRequestDispatcher("/endowment/main").include(req, resp);
    }
}
