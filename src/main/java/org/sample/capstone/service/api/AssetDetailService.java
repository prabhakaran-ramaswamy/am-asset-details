package org.sample.capstone.service.api;
import java.util.List;

import org.sample.capstone.entity.AssetDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AssetDetailService {


    public abstract AssetDetail findOne(Long id);


    public abstract void delete(AssetDetail assetDetails);


    public abstract List<AssetDetail> save(Iterable<AssetDetail> entities);


    public abstract void delete(Iterable<Long> ids);


    public abstract AssetDetail save(AssetDetail entity);


    public abstract AssetDetail findOneForUpdate(Long id);


    public abstract List<AssetDetail> findAll(Iterable<Long> ids);


    public abstract List<AssetDetail> findAll();


    public abstract long count();


    public abstract Page<AssetDetail> findAll( Pageable pageable);

}
