package org.sample.capstone.web;
import java.util.Collection;

import javax.validation.Valid;

import org.sample.capstone.entity.AssertDetail;
import org.sample.capstone.service.api.AssertDetailService;
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
@RequestMapping(value = "/assertdetails", name = "AssertDetailCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssertDetailCollectionJsonController {


    private AssertDetailService assertDetailsService;

    @Autowired
    public AssertDetailCollectionJsonController(AssertDetailService assertDetailsService) {
        this.assertDetailsService = assertDetailsService;
    }

    public AssertDetailService getAssertDetailsService() {
        return assertDetailsService;
    }

    public void setAssertDetailsService(AssertDetailService assertDetailsService) {
        this.assertDetailsService = assertDetailsService;
    }

    @GetMapping(name = "list")
    public ResponseEntity<Page<AssertDetail>> list(Pageable pageable) {
        Page<AssertDetail> assertDetailss = getAssertDetailsService().findAll( pageable);
        return ResponseEntity.ok(assertDetailss);
    }


    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(AssertDetailCollectionJsonController.class).list(null)).build().encode();
    }

    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody AssertDetail assertDetails, BindingResult result) {
        if (assertDetails.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        AssertDetail newAssertDetails = getAssertDetailsService().save(assertDetails);
        UriComponents showURI = AssertDetailItemJsonController.showURI(newAssertDetails);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<AssertDetail> assertDetailss, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getAssertDetailsService().save(assertDetailss);
        return ResponseEntity.created(listURI().toUri()).build();
    }


    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<AssertDetail> assertDetailss, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getAssertDetailsService().save(assertDetailss);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        getAssertDetailsService().delete(ids);
        return ResponseEntity.ok().build();
    }
}
