package org.sample.capstone.web;
import javax.validation.Valid;

import org.sample.capstone.entity.AssertDetail;
import org.sample.capstone.exception.NotFoundException;
import org.sample.capstone.service.api.AssertDetailService;
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
@RequestMapping(value = "/assertdetails/{assertDetail}", name = "AssertDetailItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssertDetailItemJsonController {


    private AssertDetailService assertDetailsService;


    @Autowired
    public AssertDetailItemJsonController(AssertDetailService assertDetailsService) {
        this.assertDetailsService = assertDetailsService;
    }

    public AssertDetailService getAssertDetailsService() {
        return assertDetailsService;
    }

    public void setAssertDetailsService(AssertDetailService assertDetailsService) {
        this.assertDetailsService = assertDetailsService;
    }

    @ModelAttribute
    public AssertDetail getAssertDetails(@PathVariable("assertDetail") Long id) {
        AssertDetail assertDetails = assertDetailsService.findOne(id);
        if (assertDetails == null) {
            throw new NotFoundException(String.format("AssertDetails with identifier '%s' not found", id));
        }
        return assertDetails;
    }


    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute AssertDetail assertDetails) {
        return ResponseEntity.ok(assertDetails);
    }


    public static UriComponents showURI(AssertDetail assertDetails) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(AssertDetailItemJsonController.class).show(assertDetails)).buildAndExpand(assertDetails.getId()).encode();
    }


    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute AssertDetail storedAssertDetails, @Valid @RequestBody AssertDetail assertDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        assertDetails.setId(storedAssertDetails.getId());
        getAssertDetailsService().save(assertDetails);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute AssertDetail assertDetails) {
        getAssertDetailsService().delete(assertDetails);
        return ResponseEntity.ok().build();
    }
}
