package org.sample.capstone.service.impl;
import java.util.List;

import org.sample.capstone.entity.AssertDetail;
import org.sample.capstone.repository.AssertDetailRepository;
import org.sample.capstone.service.api.AssertDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class AssertDetailServiceImpl implements AssertDetailService {


    private AssertDetailRepository assertDetailsRepository;


    @Autowired
    public AssertDetailServiceImpl(AssertDetailRepository assertDetailsRepository) {
        setAssertDetailsRepository(assertDetailsRepository);
    }

    public AssertDetailRepository getAssertDetailsRepository() {
        return assertDetailsRepository;
    }


    public void setAssertDetailsRepository(AssertDetailRepository assertDetailsRepository) {
        this.assertDetailsRepository = assertDetailsRepository;
    }


    @Transactional
    public void delete(AssertDetail assertDetails) {
        getAssertDetailsRepository().delete(assertDetails);
    }


    @Transactional
    public List<AssertDetail> save(Iterable<AssertDetail> entities) {
        return getAssertDetailsRepository().saveAll(entities);
    }

    @Transactional
    public void delete(Iterable<Long> ids) {
        List<AssertDetail> toDelete = getAssertDetailsRepository().findAllById(ids);
        getAssertDetailsRepository().deleteInBatch(toDelete);
    }

    @Transactional
    public AssertDetail save(AssertDetail entity) {
        return getAssertDetailsRepository().save(entity);
    }

    public AssertDetail findOne(Long id) {
        return getAssertDetailsRepository().getOne(id);
    }


    public AssertDetail findOneForUpdate(Long id) {
        return getAssertDetailsRepository().getOne(id);
    }


    public List<AssertDetail> findAll(Iterable<Long> ids) {
        return getAssertDetailsRepository().findAllById(ids);
    }

    public List<AssertDetail> findAll() {
        return getAssertDetailsRepository().findAll();
    }

    public long count() {
        return getAssertDetailsRepository().count();
    }


    public Page<AssertDetail> findAll( Pageable pageable) {
        return getAssertDetailsRepository().findAll(pageable);
    }

    public Class<AssertDetail> getEntityType() {
        return AssertDetail.class;
    }

    public Class<Long> getIdType() {
        return Long.class;
    }
}
