package hu.javagladiators.education.hoe.species.service;

import hu.javagladiators.education.hoe.base.dao.interfaces.BaseDao;
import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.hoe.base.service.interfaces.BaseService;
import hu.javagladiators.education.hoe.species.dao.models.Species;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @author krisztian
 */
@ApplicationScoped
public class SpeciesServiceImpl implements BaseService<Species>{
    private static final Logger log = Logger.getLogger("SpeciesDaoImpl");

    @Inject
    protected BaseDao<Species> dao;

    public SpeciesServiceImpl() {log.info("create new instance");}
   
    @Override
    public List<Species> getAll() {return dao.getAll();}

    @Override
    public Species getById(long pId) {return dao.getById(pId);}


    @Override
    public Species create(Species pData) throws BusinessException {
        try{dao.getByName(pData.getName());}
        catch(NoResultException e){return dao.create(pData);}
        throw new BusinessException();
    }

    @Override
    public Species modify(long pId, Species pData) throws BusinessException {
        try{
            dao.getByName(pData.getName());
            throw new BusinessException();
        }
        catch(NoResultException e){
            Species species = dao.getById(pId);
            if(pData.getName()!=null) species.setName(pData.getName());
            if(pData.getDescription()!=null) species.setDescription(pData.getDescription());
            return dao.modify(species);
        }
    }

    @Override
    public Species delete(long pId) {
        return dao.delete(pId);
    }
    
}
