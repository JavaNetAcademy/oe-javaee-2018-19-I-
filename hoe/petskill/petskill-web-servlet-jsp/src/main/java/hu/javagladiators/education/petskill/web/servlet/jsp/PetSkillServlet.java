
package hu.javagladiators.education.petskill.web.servlet.jsp;

import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.petskill.dao.model.models.PetSkill;
import hu.javagladiators.education.petskill.service.cdi.PetSkillServiceImpl;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "PetSkillServlet", urlPatterns = {"/petskill/main"})
public class PetSkillServlet extends HttpServlet {

    @Inject
    private PetSkillServiceImpl service;
    
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        request.setAttribute("items", this.service.getAll());
            this.getServletContext().getRequestDispatcher("index.jsp")
                    .include(request, response);     
    }

    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        PetSkill skill = new PetSkill();
        skill.setName(request.getParameter("skillName"));
        skill.setDescription(request.getParameter("description"));
        skill.setRequiredLevel(Integer.parseInt(
                request.getParameter("requiredLvl")));
        
        try {
            this.service.create(skill);
            this.doGet(request, response);
        } catch (BusinessException ex) {
            request.setAttribute("exception", ex);
            this.getServletContext().getRequestDispatcher("error.jsp")
                    .include(request, response);
        }       
    }
}
