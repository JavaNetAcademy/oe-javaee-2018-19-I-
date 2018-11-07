
import hu.javagladiators.education.fegyver.dao.model.Fegyver;
import hu.javagladiators.education.fegyver.dao.model.FegyverDao;
import java.io.IOException;


//import hu.javanetacademy.datamodel.Fegyver;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import hu.javanetacademy.dal.FegyverInterface;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tnyarsik
 */

@WebServlet(name = "FegyverTorolServlet", urlPatterns = {"/fegyver/torol"})

public class FegyverTorolServlet extends HttpServlet {
     @Inject private FegyverDao fegyverdao;
       
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
        
        Fegyver akt = fegyverdao.keresFegyver(Long.parseLong(request.getParameter("id")));
        fegyverdao.torolFegyver(akt);
        
        response.sendRedirect(request.getContextPath() + "/fegyverLista");
    }

 
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Kiv�lasztott fegyver t�rl�se";
    }// </editor-fold>
}
