package org.sample.capstone.web;
import javax.validation.Valid;

import org.sample.capstone.entity.AssetDetail;
import org.sample.capstone.exception.NotFoundException;
import org.sample.capstone.service.api.AssetDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;


@RestController
@RequestMapping(value = "/assetdetails/{assetDetail}", name = "AssetDetailItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssetDetailItemJsonController {

	@Autowired
    private AssetDetailService assetDetailsService;

    @ModelAttribute
    public AssetDetail getAssetDetails(@PathVariable("assetDetail") Long id) {
        AssetDetail assetDetail= assetDetailsService.findOne(id);
        if (assetDetail == null) {
            throw new NotFoundException(String.format("AssetDetails with identifier '%s' not found", id));
        }
        return assetDetail;
    }

    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute AssetDetail assetDetail) {
        return ResponseEntity.ok(assetDetail);
    }


    public static UriComponents showURI(AssetDetail assetDetail) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(AssetDetailItemJsonController.class).show(assetDetail)).buildAndExpand(assetDetail.getId()).encode();
    }


    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute AssetDetail storedAsset, @Valid @RequestBody AssetDetail assetDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        assetDetails.setId(storedAsset.getId());
        assetDetailsService.save(assetDetails);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute AssetDetail assetDetails) {
        assetDetailsService.delete(assetDetails);
        return ResponseEntity.ok().build();
    }
}
