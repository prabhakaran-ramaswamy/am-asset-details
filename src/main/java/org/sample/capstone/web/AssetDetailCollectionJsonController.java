package org.sample.capstone.web;

import java.util.Collection;

import javax.validation.Valid;

import org.sample.capstone.entity.AssetDetailView;
import org.sample.capstone.service.api.AssetDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
@RequestMapping(value = "/assetdetails", name = "AssetDetailCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssetDetailCollectionJsonController {

	@Autowired
	private AssetDetailService assetDetailsService;

	@GetMapping(name = "list")
	public ResponseEntity<Page<AssetDetailView>> list(Pageable pageable) {
		Page<AssetDetailView> assetDetailss = assetDetailsService.findAll(pageable);
		return ResponseEntity.ok(assetDetailss);
	}

	public static UriComponents listURI() {
		return MvcUriComponentsBuilder
				.fromMethodCall(MvcUriComponentsBuilder.on(AssetDetailCollectionJsonController.class).list(null))
				.build().encode();
	}

	@PostMapping(name = "create")
	public ResponseEntity<?> create(@Valid @RequestBody AssetDetailView AssetDetailView, BindingResult result) {
		if (AssetDetailView.getId() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}
		AssetDetailView newAssetDetails = assetDetailsService.save(AssetDetailView);
		UriComponents showURI = AssetDetailItemJsonController
				.showURI(newAssetDetails);
		return ResponseEntity.created(showURI.toUri()).build();
	}

	@PostMapping(value = "/batch", name = "createBatch")
	public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<AssetDetailView> assets,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}
		assetDetailsService.save(assets);
		return ResponseEntity.created(listURI().toUri()).build();
	}

	@PutMapping(value = "/batch", name = "updateBatch")
	public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<AssetDetailView> assets,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}
		assetDetailsService.save(assets);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
	public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
		assetDetailsService.delete(ids);
		return ResponseEntity.ok().build();
	}
}
