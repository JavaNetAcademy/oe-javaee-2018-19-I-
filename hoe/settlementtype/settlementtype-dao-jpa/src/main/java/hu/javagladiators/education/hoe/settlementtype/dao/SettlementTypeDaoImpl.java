package hu.javagladiators.education.hoe.settlementtype.dao;

import hu.javagladiators.education.hoe.base.dao.interfaces.BaseDao;
import hu.javagladiators.education.hoe.settlementtype.dao.models.SettlementType;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;


@RequestScoped
public class SettlementTypeDaoImpl implements BaseDao<SettlementType>{
    private static final Logger log = Logger.getLogger("SettlementTypeDaoImpl");

    private EntityManager em = Persistence.createEntityManagerFactory("hoe").createEntityManager();

    public SettlementTypeDaoImpl() {log.info("create new instance");}

    
    
    @Override
    public List<SettlementType> getAll() {
        return em.createNamedQuery("SettlementType.all").getResultList();
    }

    @Override
    public SettlementType getById(long pId) {
        return em.find(SettlementType.class, pId);
    }

    @Override
    public SettlementType getByName(String pName) {
        return em.createNamedQuery("SettlementType.name",SettlementType.class)
                .setParameter("name", pName)
                .getSingleResult();
    }

    @Override
    public SettlementType create(SettlementType pData) {
        em.getTransaction().begin();
        em.persist(pData);
        em.getTransaction().commit();
        return pData;
    }

    @Override
    public SettlementType modify(SettlementType pData) {
        em.getTransaction().begin();
        em.merge(pData);
        em.getTransaction().commit();
        return pData;
    }

    @Override
    public SettlementType delete(long pId) {
        SettlementType settlementtype = getById(pId);
        em.getTransaction().begin();
        em.remove(settlementtype);
        em.getTransaction().commit();
        return settlementtype;
    }
    
}
