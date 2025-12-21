package com.security.auth.service;

import com.security.auth.model.CommonDataModel;
import com.security.auth.repository.CommonJPARepository;
import org.springframework.stereotype.Service;

import java.util.Date;

public class AbstractCDMService<T extends CommonDataModel> implements CommonDataModelService<T> {

    protected CommonJPARepository<T, String> repository;

    public AbstractCDMService(CommonJPARepository<T, String> repository) {
        this.repository = repository;
    }

    public  CommonJPARepository<T, String> getRepository() {
        return repository;
    }

    @Override
    public T save(T cdmObj) {
        String hash = String.valueOf(cdmObj.hashCode());
        T byHash = repository.findByHash(hash);
        if(byHash != null){
            return byHash;
        }
        cdmObj.setVersion(0);
        cdmObj.setCreationTime(new Date());
        cdmObj.setLastModifiedTime(new Date());
        cdmObj.setHash(hash);
        return repository.save(cdmObj);
    }
}
