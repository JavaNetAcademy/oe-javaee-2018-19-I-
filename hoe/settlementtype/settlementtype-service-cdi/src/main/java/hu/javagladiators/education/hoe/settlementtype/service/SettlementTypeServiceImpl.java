package hu.javagladiators.education.hoe.settlementtype.service;

import hu.javagladiators.education.hoe.base.dao.interfaces.BaseDao;
import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.hoe.base.service.interfaces.BaseService;
import hu.javagladiators.education.hoe.settlementtype.dao.models.SettlementType;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;


@ApplicationScoped
public class SettlementTypeServiceImpl implements BaseService<SettlementType>{
    private static final Logger log = Logger.getLogger("SettlementTypeDaoImpl");

    @Inject
    protected BaseDao<SettlementType> dao;

    public SettlementTypeServiceImpl() {log.info("create new instance");}
   
    @Override
    public List<SettlementType> getAll() {return dao.getAll();}

    @Override
    public SettlementType getById(long pId) {return dao.getById(pId);}


    @Override
    public SettlementType create(SettlementType pData) throws BusinessException {
        try{dao.getByName(pData.getName());}
        catch(NoResultException e){return dao.create(pData);}
        throw new BusinessException();
    }

    @Override
    public SettlementType modify(long pId, SettlementType pData) throws BusinessException {
        try{
            dao.getByName(pData.getName());
            throw new BusinessException();
        }
        catch(NoResultException e){
            SettlementType settlementtype = dao.getById(pId);
            if(pData.getName()!=null) settlementtype.setName(pData.getName());
            if(pData.getDescription()!=null) settlementtype.setDescription(pData.getDescription());
            return dao.modify(settlementtype);
        }
    }

    @Override
    public SettlementType delete(long pId) {
        return dao.delete(pId);
    }
    
}
