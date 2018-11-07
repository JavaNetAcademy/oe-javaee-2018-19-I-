/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.education.hoe.vehicle.web.controllers;

import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.hoe.base.service.interfaces.BaseService;
import hu.javagladiators.education.hoe.vehicle.dao.models.Vehicle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VehicleModServlet", urlPatterns = {"/vehiclemod"})
public class VehicleModServlet extends HttpServlet {
    
    @Inject
    private BaseService<Vehicle> vehicleService;

    public static final String APPContextName = "vehicle";
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        getServletContext().setAttribute(APPContextName, new ArrayList<Vehicle>());
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
            
            request.setAttribute("vehicle", vehicleService.getAll());
            request.getRequestDispatcher("vehicle.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
                    Vehicle modVehicle = new Vehicle();
            modVehicle.setName(request.getParameter("modname"));
            modVehicle.setDescription(request.getParameter("moddesc"));
            try{
            vehicleService.modify(Long.parseLong(request.getParameter("modid")),modVehicle);
            doGet(request, response);
            }catch(BusinessException e){
                e.printStackTrace();
                getServletContext().getRequestDispatcher("/vehicle/error/error.jsp").include(request, response);
            }
    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Modify vehicle instances";
    }// </editor-fold>

}
