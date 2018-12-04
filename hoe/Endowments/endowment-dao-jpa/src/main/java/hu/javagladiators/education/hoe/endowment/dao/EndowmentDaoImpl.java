package hu.javagladiators.education.hoe.endowment.dao;

import hu.javagladiators.education.hoe.base.dao.interfaces.BaseDao;
import hu.javagladiators.education.hoe.endowment.dao.interfaces.EndowmentDao;
import hu.javagladiators.education.hoe.endowment.dao.models.Endowment;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@RequestScoped
public class EndowmentDaoImpl implements BaseDao<Endowment>{
    private static final Logger log = Logger.getLogger("SpeciesDaoImpl");

    private EntityManager em = Persistence.createEntityManagerFactory("hoe").createEntityManager();

    public EndowmentDaoImpl() {log.info("create new instance");}

    @Override
    public Endowment create(Endowment edata) {
        em.getTransaction().begin();
        em.persist(edata);
        em.getTransaction().commit();
        return edata;
    }  

    @Override
    public List<Endowment> getAll() {
        return em.createNamedQuery("Endowment.all").getResultList();
    }

    @Override
    public Endowment getById(long eId) {
        return em.find(Endowment.class, eId);
    }

    @Override
    public Endowment getByName(String pName) {
        return em.createNamedQuery("Endowment.name",Endowment.class)
                .setParameter("name", pName)
                .getSingleResult();
    }

    @Override
    public Endowment modify(Endowment pData) {
        em.getTransaction().begin();
        em.merge(pData);
        em.getTransaction().commit();
        return pData;
    }

    @Override
    public Endowment delete(long pId) {
        Endowment species = getById(pId);
        em.getTransaction().begin();
        em.remove(species);
        em.getTransaction().commit();
        return species;
    }
}
