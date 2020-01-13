package org.personal.salportfoliobackend.service;

import java.util.List;
import java.util.Optional;

import org.personal.salportfoliobackend.dao.CommonDao;
import org.personal.salportfoliobackend.domain.CommonDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public abstract class CommonServiceImpl<T extends CommonDomain, 
    D extends CommonDao<T>> implements CommonService<T> {

    @Autowired
    D dao;
    
    Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
    
    @Override
    public Optional<T> getById(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.warn("getById attempt with an empty id.");
            return Optional.ofNullable(null);
        }
        return dao.getById(id);
    }

    @Override
    public Optional<List<T>> getAll() {
        return dao.getAll();
    }

    @Override
    public int insert(T domain) {
        if (StringUtils.isEmpty(domain.getId())) {
            logger.warn("insert attempt with an empty id.");
            return 0;
        }
        return dao.insert(domain);
    }

    @Override
    public int update(T domain) {
        if (StringUtils.isEmpty(domain.getId())) {
            logger.warn("update attempt with an empty id.");
            return 0;
        }
        return dao.update(domain);
    }

    @Override
    public int deleteById(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.warn("delete attempt with an empty id.");
            return 0;
        }
        return dao.deleteById(id);
    }

}
