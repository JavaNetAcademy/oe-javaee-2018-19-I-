package hu.javagladiators.education.hoe.endowments.service;

import hu.javagladiators.education.hoe.base.dao.interfaces.BaseDao;
import hu.javagladiators.education.hoe.base.service.exceptions.BusinessException;
import hu.javagladiators.education.hoe.base.service.interfaces.BaseService;
import hu.javagladiators.education.hoe.endowment.dao.models.Endowment;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@ApplicationScoped
public class EndowmentServiceImpl implements BaseService<Endowment>{
    private static final Logger log = Logger.getLogger("SpeciesDaoImpl");

    @Inject
    protected BaseDao<Endowment> dao;

    public EndowmentServiceImpl() {log.info("create new instance");}
    
    @Override
    public List<Endowment> getAll() {
        return dao.getAll();
    }

    @Override
    public Endowment getById(long pId) {
        return dao.getById(pId);
    }

    @Override
    public Endowment create(Endowment pData) throws BusinessException {
        try{dao.getByName(pData.getName());}
        catch(NoResultException e){return dao.create(pData);}
        throw new BusinessException();
    }

    @Override
    public Endowment modify(long pId, Endowment pData) throws BusinessException {
        try{
            dao.getByName(pData.getName());
            throw new BusinessException();
        }
        catch(NoResultException e){
            Endowment endowment = dao.getById(pId);
            if(pData.getName()!=null) endowment.setName(pData.getName());
            if(pData.getType()!=null) endowment.setType(pData.getType());
            if(pData.getDesc()!=null) endowment.setDesc(pData.getDesc());
            return dao.modify(endowment);
        }
    }

    @Override
    public Endowment delete(long pId) {
        return dao.delete(pId);
    }
    
}
