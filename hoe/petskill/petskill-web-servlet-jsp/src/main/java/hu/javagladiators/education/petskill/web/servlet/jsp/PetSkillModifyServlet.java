
package hu.javagladiators.education.petskill.web.servlet.jsp;

import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.petskill.dao.model.models.PetSkill;
import hu.javagladiators.education.petskill.service.cdi.PetSkillServiceImpl;
import java.io.IOException;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "PetSkillModifyServlet", 
        urlPatterns = {"/petskill/modify"})
public class PetSkillModifyServlet extends HttpServlet {
    private static final String MODIFY_ID = "modifyId";

    @Inject
    private PetSkillServiceImpl service;
    
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            if (request.getParameter("id") != null) {
                long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("item", this.service.getById(id));
                request.getSession().setAttribute(MODIFY_ID, id);
            }
            
            this.getServletContext().getRequestDispatcher("modify.jsp")
                    .include(request, response);
        } catch (NoResultException ex) {
            request.setAttribute("exception", ex);
            this.getServletContext().getRequestDispatcher("error.jsp")
                    .include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PetSkill skill = new PetSkill();
        skill.setId(Long.parseLong(request.getParameter(MODIFY_ID)));
        skill.setName(request.getParameter("skillName"));
        skill.setDescription(request.getParameter("description"));
        skill.setRequiredLevel(Integer.parseInt(
                request.getParameter("requiredLvl")));
        
        try {
            PetSkill current = (PetSkill) request.getAttribute("item");
            this.service.modify(current.getId(), skill);
            request.setAttribute("items", this.service.getAll());
            this.getServletContext().getRequestDispatcher("/index.jsp")
                    .include(request, response);
        } catch (BusinessException ex) {
            request.setAttribute("exception", ex);
            this.getServletContext().getRequestDispatcher("error.jsp")
                    .include(request, response);
        }
    }
}