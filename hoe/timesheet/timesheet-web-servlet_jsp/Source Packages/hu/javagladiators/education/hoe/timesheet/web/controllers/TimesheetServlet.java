/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.timesheet.dao.model.Timesheet;
import hu.javagladiators.education.timesheet.service.cdi.TimesheetServiceImpl;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KTengely
 */
@WebServlet(name = "TimesheetServlet", urlPatterns = {"/timesheet/main"})
public class TimesheetServlet extends HttpServlet{
    @Inject
    private TimesheetServiceImpl service;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); 
        req.setAttribute("items", service.getAll());
        getServletContext().getRequestDispatcher("/timesheet/index.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Timesheet t = new Timesheet();
        String dateFrom = req.getParameter("dateFrom");
        String dateTo = req.getParameter("description");
        String salary = req.getParameter("salary");

         SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        try {
            t.setDateFrom(parser.parse(dateFrom));
            t.setDateTo(parser.parse(dateTo));
            t.setSalary(Integer.parseInt(salary));

        } catch (ParseException ex) {
            Logger.getLogger(TimesheetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try{
            service.create(t);
            doGet(req, resp);
        }
        catch(BusinessException e){
            getServletContext().getRequestDispatcher("/timesheet/error/error.jsp").include(req, resp);
        }
    }
}