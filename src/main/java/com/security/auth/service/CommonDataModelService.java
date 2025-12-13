package com.security.auth.service;

import com.security.auth.model.CommonDataModel;

public interface CommonDataModelService<T extends CommonDataModel> {
    T save(T commonDataModel);
}
