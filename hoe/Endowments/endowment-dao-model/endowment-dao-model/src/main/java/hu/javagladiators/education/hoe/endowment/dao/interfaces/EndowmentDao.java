package hu.javagladiators.education.hoe.endowment.dao.interfaces;

import hu.javagladiators.education.hoe.endowment.dao.models.Endowment;
import java.util.List;

public interface EndowmentDao {    
    public Endowment create(Endowment pEndowment);
    public List<Endowment> getAll();
    public Endowment getById(long eId);
    public Endowment modify(long eId, Endowment eData);
    public Endowment delete(long eId);
    public Endowment getByName(String eName);    
}
