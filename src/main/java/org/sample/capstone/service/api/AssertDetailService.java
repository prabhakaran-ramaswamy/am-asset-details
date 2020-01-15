package org.sample.capstone.service.api;
import java.util.List;

import org.sample.capstone.entity.AssertDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AssertDetailService {


    public abstract AssertDetail findOne(Long id);


    public abstract void delete(AssertDetail assertDetails);


    public abstract List<AssertDetail> save(Iterable<AssertDetail> entities);


    public abstract void delete(Iterable<Long> ids);


    public abstract AssertDetail save(AssertDetail entity);


    public abstract AssertDetail findOneForUpdate(Long id);


    public abstract List<AssertDetail> findAll(Iterable<Long> ids);


    public abstract List<AssertDetail> findAll();


    public abstract long count();


    public abstract Page<AssertDetail> findAll( Pageable pageable);

}
