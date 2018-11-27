/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author T470
 */

import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.timesheet.dao.model.Timesheet;
import hu.javagladiators.education.timesheet.service.cdi.TimesheetServiceImpl;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TimesheetModifyServlet", urlPatterns = {"/timesheet/modify"})
public class TimesheetModifyServlet extends HttpServlet {
    private static final String MODIFYID="modifyvlid";

    @Inject
    private TimesheetServiceImpl service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        if(req.getParameter("id")!=null){
            long id=Long.parseLong(req.getParameter("id"));
            req.setAttribute("item",service.getById(id));
            req.getSession().setAttribute(MODIFYID,id);
        }
        getServletContext().getRequestDispatcher("/timesheet/modify.jsp").include(req, resp);
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        Timesheet t = new Timesheet();
        String dateFrom = req.getParameter("dateFrom");
        String dateTo = req.getParameter("description");
        String salary = req.getParameter("salary");
        
        try{
            service.modify(Long.parseLong(""+req.getSession().getAttribute(MODIFYID)),t);
            req.setAttribute("items", service.getAll());
            getServletContext().getRequestDispatcher("/timesheet/index.jsp").include(req, resp);
        }
        catch(BusinessException e){
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/timesheet/error/error.jsp").include(req, resp);
        }
    }
}