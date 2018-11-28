
package hu.javagladiators.education.petskill.dao.jpa;

import hu.javagladiators.education.petskill.dao.model.interfaces.PetSkillDaoInterface;
import hu.javagladiators.education.petskill.dao.model.models.PetSkill;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class PetSkillImpl implements PetSkillDaoInterface{
    
    private EntityManager manager;

    public PetSkillImpl() {
        this.manager = Persistence.createEntityManagerFactory("hoe").
                createEntityManager();
    }

    @Override
    public PetSkill create(PetSkill skill) {
        this.manager.getTransaction().begin();
        this.manager.persist(skill);
        this.manager.getTransaction().commit();
        
        return skill;
    }

    @Override
    public List<PetSkill> getAll() {
        return this.manager.createQuery("SELECT d FROM PetSkill d")
                .getResultList();
    }

    @Override
    public PetSkill getById(long id) {
        return (PetSkill) this.manager.createQuery(
                "SELECT d FROM PetSkill d WHERE d.id=" + id).getSingleResult();
                
    }

    @Override
    public PetSkill update(PetSkill skill) {
        this.manager.getTransaction().begin();
        this.manager.merge(skill);
        this.manager.getTransaction().commit();
        
        return skill;
    }

    @Override
    public PetSkill delete(long id) {
        PetSkill skill = this.getById(id);
        
        this.manager.getTransaction().begin();
        this.manager.remove(skill);
        this.manager.getTransaction().commit();
        
        return skill;
    }

    @Override
    public PetSkill getByName(String name) {
        return (PetSkill) this.manager
                .createQuery("SELECT d FROM PetSkill d WHERE d.name=" + name)
                .getSingleResult();
    }
}
