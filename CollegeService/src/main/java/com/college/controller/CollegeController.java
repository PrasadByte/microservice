package com.college.controller;

import com.college.dto.CollegeDto;
import com.college.dto.response.CollegeResponse;
import com.college.service.CollegeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/colleges")
public class CollegeController {
    private final CollegeService  collegeService;


    @PostMapping
    public ResponseEntity<CollegeDto>inserCollege(@RequestBody CollegeDto college) {

         CollegeDto c = collegeService.createCollege(college);
        return new  ResponseEntity<>(c,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CollegeDto>>getAllColleges(){
        List<CollegeDto> colleges = collegeService.getAllColleges();
        return new ResponseEntity<>(colleges, HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<CollegeDto> getCollege(@PathVariable String name){
        collegeService.getCollegeByName(name);
        return new ResponseEntity<>(collegeService.getCollegeByName(name), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCollege(@PathVariable int id){
        collegeService.deleteCollege(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CollegeDto> getCollegeById(@PathVariable int id) {
        CollegeDto collegeDto = collegeService.getCollegeById(id);
        return new ResponseEntity<>(collegeDto, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CollegeDto> updateCollege(@PathVariable int id, @RequestBody CollegeDto collegeDto) {
        CollegeDto updatedCollege = collegeService.updateCollege(id, collegeDto);
        return new ResponseEntity<>(updatedCollege, HttpStatus.OK);
    }

    @GetMapping("/{id}/university")
    public ResponseEntity<CollegeResponse> getCollegeWithUniversity(@PathVariable int id){

        return ResponseEntity.ok(
                collegeService.getCollegewithUniversity(id)
        );

    }
}





