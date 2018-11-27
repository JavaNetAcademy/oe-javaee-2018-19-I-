
package hu.javagladiators.education.petskill.service.cdi;

import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.hoe.base.service.interfaces.BaseService;
import hu.javagladiators.education.petskill.dao.jpa.PetSkillImpl;
import hu.javagladiators.education.petskill.dao.model.models.PetSkill;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;


public class PetSkillServiceImpl implements BaseService<PetSkill>{

    @Inject
    private PetSkillImpl dao;
    
    @Override
    public List<PetSkill> getAll() {
        return this.dao.getAll();
    }

    @Override
    public PetSkill getById(long pId) {
         return this.dao.getById(pId);
    }

    @Override
    public PetSkill create(PetSkill pData) throws BusinessException {
        try {
            this.dao.getByName(pData.getName());
        } 
        catch (NoResultException nre) {
            return this.dao.create(pData);
        }
        throw new BusinessException();
    }

    @Override
    public PetSkill modify(long pId, PetSkill pData) throws BusinessException {
        try {
            this.dao.getById(pId);
            throw new BusinessException();
        }
        catch (NoResultException e) {
            PetSkill ps = this.dao.getById(pId);
            if (pData.getName() != null) ps.setName(pData.getName());
            if (pData.getDescription() != null) 
                ps.setDescription(pData.getDescription());
            if (pData.getRequiredLevel() != 0)
                ps.setRequiredLevel(pData.getRequiredLevel());
            
            return this.dao.update(ps);
        }
    }

    @Override
    public PetSkill delete(long pId) {
        return this.dao.delete(pId);
    }   
}
