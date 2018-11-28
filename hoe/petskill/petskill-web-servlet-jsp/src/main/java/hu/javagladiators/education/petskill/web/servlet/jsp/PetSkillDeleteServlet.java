
package hu.javagladiators.education.petskill.web.servlet.jsp;

import hu.javagladiators.education.petskill.service.cdi.PetSkillServiceImpl;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "PetSkillDeleteServlet", urlPatterns = 
        {"/petskill/delete"})
public class PetSkillDeleteServlet extends HttpServlet {

    @Inject
    private PetSkillServiceImpl service;
    
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            this.service.delete(id);
        }
    }
}
