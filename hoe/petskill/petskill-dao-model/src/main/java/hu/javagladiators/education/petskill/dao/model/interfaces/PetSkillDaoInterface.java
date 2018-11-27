
package hu.javagladiators.education.petskill.dao.model.interfaces;

import hu.javagladiators.education.petskill.dao.model.models.PetSkill;
import java.util.List;


public interface PetSkillDaoInterface {
    
    PetSkill create(PetSkill skill);
    List<PetSkill> getAll();
    PetSkill getById(long id);
    PetSkill getByName(String name);
    PetSkill update(PetSkill skill);
    PetSkill delete(long id);
}
