package org.sample.capstone.service.api;
import java.util.List;

import org.sample.capstone.entity.AssetDetailView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AssetDetailService {


    public abstract AssetDetailView findOne(Long id);


    public abstract void delete(AssetDetailView assetDetailView);


    public abstract List<AssetDetailView> save(Iterable<AssetDetailView> assetDetailViews);


    public abstract void delete(Iterable<Long> ids);


    public abstract AssetDetailView save(AssetDetailView assetDetailView);


    public abstract AssetDetailView findOneForUpdate(Long id);


    public abstract List<AssetDetailView> findAll(Iterable<Long> ids);


    public abstract List<AssetDetailView> findAll();


    public abstract long count();


    public abstract Page<AssetDetailView> findAll( Pageable pageable);

}
