package com.security.auth.service;

import com.security.auth.model.CommonDataModel;
import com.security.auth.repository.CommonJPARepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AbstractCDMService<T extends CommonDataModel> implements CommonDataModelService<T> {

    private final CommonJPARepository<T, String> repository;

    public AbstractCDMService(CommonJPARepository<T, String> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T cdmObj) {

        if(cdmObj.getHash() != null){
            T byHash = repository.findByHash(cdmObj.getHash());
            cdmObj.setId(byHash.getId());
            cdmObj.setVersion(byHash.getVersion()+1);
        }

        cdmObj.setCreationTime(new Date());
        cdmObj.setLastModifiedTime(new Date());
        return repository.save(cdmObj);
    }
}
