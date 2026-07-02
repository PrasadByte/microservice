package com.college.service;

import com.college.dto.CollegeDto;
import com.college.dto.response.CollegeResponse;

import java.util.List;

public interface CollegeService {
    CollegeDto createCollege(CollegeDto dto);

    CollegeDto updateCollege(int id,CollegeDto dto);
    CollegeDto getCollegeById(int id);
    void deleteCollege(int id);
    List<CollegeDto> getAllColleges();
    CollegeDto getCollegeByName(String name);
    CollegeResponse getCollegewithUniversity(int id);


}
