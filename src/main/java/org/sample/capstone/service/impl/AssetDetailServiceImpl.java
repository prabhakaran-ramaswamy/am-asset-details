package org.sample.capstone.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.sample.capstone.entity.Account;
import org.sample.capstone.entity.AccountView;
import org.sample.capstone.entity.AssetDetail;
import org.sample.capstone.entity.AssetDetailView;
import org.sample.capstone.repository.AssetDetailRepository;
import org.sample.capstone.service.api.AssetDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AssetDetailServiceImpl implements AssetDetailService {

	@Autowired
	private AssetDetailRepository assetDetailsRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public void delete(AssetDetailView assetDetailView) {
		assetDetailsRepository.delete(convertToEntity(assetDetailView));
	}

	@Transactional
	public List<AssetDetailView> save(Iterable<AssetDetailView> assetDetailViews) {
		List<AssetDetail> entities = StreamSupport.stream(assetDetailViews.spliterator(), false)
				.map(this::convertToEntity).collect(Collectors.toList());
		List<AssetDetail> saveAll = assetDetailsRepository.saveAll(entities);
		return StreamSupport.stream(saveAll.spliterator(), false).map(this::convertToDto).collect(Collectors.toList());
	}

	@Transactional
	public void delete(Iterable<Long> ids) {
		List<AssetDetail> toDelete = assetDetailsRepository.findAllById(ids);
		assetDetailsRepository.deleteInBatch(toDelete);
	}

	@Transactional
	public AssetDetailView save(AssetDetailView assetDetailview) {
		AssetDetail assetDetail = assetDetailsRepository.save(convertToEntity(assetDetailview));
		return convertToDto(assetDetail);
	}

	public AssetDetailView findOne(Long id) {
		Optional<AssetDetail> findById = assetDetailsRepository.findById(id);
		return convertToDto(findById.get());
	}

	public AssetDetailView findOneForUpdate(Long id) {
		Optional<AssetDetail> findById = assetDetailsRepository.findById(id);
		return convertToDto(findById.get());
	}

	public List<AssetDetailView> findAll(Iterable<Long> ids) {
		List<AssetDetail> assetDetails = assetDetailsRepository.findAllById(ids);
		return StreamSupport.stream(assetDetails.spliterator(), false).map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public List<AssetDetailView> findAll() {
		List<AssetDetail> assetDetails = assetDetailsRepository.findAll();
		return StreamSupport.stream(assetDetails.spliterator(), false).map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public long count() {
		return assetDetailsRepository.count();
	}

	public Page<AssetDetailView> findAll(Pageable pageable) {
		Page<AssetDetail> findAll = assetDetailsRepository.findAll(pageable);
		List<AssetDetailView> assetDetailViews = StreamSupport.stream(findAll.spliterator(), false)
				.map(this::convertToDto).collect(Collectors.toList());
		return new PageImpl<>(assetDetailViews, findAll.getPageable(), assetDetailViews.size());
	}

	public Class<AssetDetailView> getEntityType() {
		return AssetDetailView.class;
	}

	public Class<Long> getIdType() {
		return Long.class;
	}

	private AssetDetailView convertToDto(AssetDetail assetDetail) {
		AssetDetailView assetDetailView = modelMapper.map(assetDetail, AssetDetailView.class);
		return assetDetailView;
	}

	private AssetDetail convertToEntity(AssetDetailView accountView) {
		AssetDetail assetDetail = modelMapper.map(accountView, AssetDetail.class);
		return assetDetail;
	}
}
