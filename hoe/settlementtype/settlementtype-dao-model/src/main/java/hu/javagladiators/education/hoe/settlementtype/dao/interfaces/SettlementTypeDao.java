package hu.javagladiators.education.hoe.settlementtype.dao.interfaces;

import hu.javagladiators.education.hoe.settlementtype.dao.models.SettlementType;
import java.util.List;

public interface SettlementTypeDao {
    public List<SettlementType> getAll();
    public SettlementType getById(long pId);
    public SettlementType getByName(String pName);
    public SettlementType create(SettlementType pData);
    public SettlementType modify(long pId,SettlementType pData);
    public SettlementType delete(long pId);
}
