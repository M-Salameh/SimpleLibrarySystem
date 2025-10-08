package com.maids.LibrarySystem.Controllers;

import com.maids.LibrarySystem.Entities.ApprovedDocuments;
import com.maids.LibrarySystem.Entities.Book;
import com.maids.LibrarySystem.Entities.Patron;
import com.maids.LibrarySystem.IRepositries.IAdDocsRepo;
import com.maids.LibrarySystem.IRepositries.IPatronRepository;
import com.maids.LibrarySystem.Services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ap-doc")
@Validated
public class ApprovedDocumentsController {

    @Autowired
    private IAdDocsRepo docsRepo;

    @Autowired
    private IPatronRepository patronRepository;

    @Autowired
    PatronService patronService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDocById(@PathVariable Long id)
    {
        return ResponseEntity.ok(docsRepo.findById(id).orElse(null));
    }



    @Transactional
    @GetMapping("add/{patrid}/{tag}")
    public ResponseEntity<?> addDoc(@PathVariable("patrid") Long id , @PathVariable("tag") String tag)
    {
        Patron patron = patronService.getPatronById(id);
        ApprovedDocuments approvedDocuments = new ApprovedDocuments();
        approvedDocuments.setDocumentTag(tag);
        approvedDocuments.setPatron(patron);
        approvedDocuments = docsRepo.save(approvedDocuments);
        return ResponseEntity.ok(approvedDocuments);
    }
    @PostMapping("/test/aha")
    public ResponseEntity<?> revList(
            @RequestBody List<String> aha) {
        return ResponseEntity.ok( patronRepository.testRevList(aha , aha.size()));
    }
    @PostMapping("/set-creation-date/{id}")
    public ResponseEntity<?> setCreationDate(@PathVariable("id") ApprovedDocuments ad ,
                                             @RequestBody @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss") Date date) {
        ad.setCreationDate(date);
        docsRepo.save(ad);
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/get-creation-date/{id}")
    public ResponseEntity<?> getCreationDate(@PathVariable("id") ApprovedDocuments ad) {
        return ResponseEntity.ok(ad.getCreationDate());
    }

}
