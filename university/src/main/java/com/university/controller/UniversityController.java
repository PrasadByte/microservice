package com.university.controller;

import com.university.Service.UniversityService;
import com.university.dto.request.Createuniversity;
import com.university.dto.responce.GetUniversityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/university")
@RequiredArgsConstructor
public class UniversityController {
private final UniversityService universityService;


@PostMapping
public ResponseEntity<Createuniversity> insertUniversity(@RequestBody Createuniversity createuniversity){
    universityService.createUniversity(createuniversity);
    return ResponseEntity.status(HttpStatus.CREATED).body(createuniversity);
}


   @GetMapping
   public ResponseEntity<List<GetUniversityDTO>> getUniversity() {
       List<GetUniversityDTO> universities = universityService.getUniversity();
       return ResponseEntity.ok(universities);
   }
//this is comment
   @GetMapping("/{id}")
    public ResponseEntity<GetUniversityDTO> getUniversityById(@PathVariable int id){
       GetUniversityDTO universityById = universityService.getUniversityById(id);
       return ResponseEntity.ok(universityById);
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUniversityById(@PathVariable int id){
    universityService.deleteUniversity(id);
    return ResponseEntity.noContent().build();
   }
   @PutMapping("/{id}")
    public ResponseEntity<Createuniversity> updateuniversity(@PathVariable int id, @RequestBody Createuniversity createuniversity){
    universityService.updateUniversity(createuniversity, id);
    return ResponseEntity.ok(createuniversity);
   }

}
