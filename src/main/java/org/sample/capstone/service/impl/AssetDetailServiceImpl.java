package org.sample.capstone.service.impl;
import java.util.List;

import org.sample.capstone.entity.AssetDetail;
import org.sample.capstone.repository.AssetDetailRepository;
import org.sample.capstone.service.api.AssetDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class AssetDetailServiceImpl implements AssetDetailService {

	@Autowired
    private AssetDetailRepository assetDetailsRepository;


    @Transactional
    public void delete(AssetDetail assetDetails) {
        assetDetailsRepository.delete(assetDetails);
    }


    @Transactional
    public List<AssetDetail> save(Iterable<AssetDetail> entities) {
        return assetDetailsRepository.saveAll(entities);
    }

    @Transactional
    public void delete(Iterable<Long> ids) {
        List<AssetDetail> toDelete = assetDetailsRepository.findAllById(ids);
        assetDetailsRepository.deleteInBatch(toDelete);
    }

    @Transactional
    public AssetDetail save(AssetDetail entity) {
        return assetDetailsRepository.save(entity);
    }

    public AssetDetail findOne(Long id) {
        return assetDetailsRepository.getOne(id);
    }


    public AssetDetail findOneForUpdate(Long id) {
        return assetDetailsRepository.getOne(id);
    }


    public List<AssetDetail> findAll(Iterable<Long> ids) {
        return assetDetailsRepository.findAllById(ids);
    }

    public List<AssetDetail> findAll() {
        return assetDetailsRepository.findAll();
    }

    public long count() {
        return assetDetailsRepository.count();
    }


    public Page<AssetDetail> findAll( Pageable pageable) {
        return assetDetailsRepository.findAll(pageable);
    }

    public Class<AssetDetail> getEntityType() {
        return AssetDetail.class;
    }

    public Class<Long> getIdType() {
        return Long.class;
    }
}
