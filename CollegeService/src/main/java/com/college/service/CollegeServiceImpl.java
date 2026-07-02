package com.college.service;

import com.college.dto.CollegeDto;
import com.college.dto.UniversityDto;
import com.college.dto.response.CollegeResponse;
import com.college.feign.UniversityClient;
import com.college.model.College;
import com.college.repository.CollegeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CollegeServiceImpl implements CollegeService {
    private final CollegeRepository collegeRepository;
    private final UniversityClient universityClient;

    @Override
    public CollegeDto createCollege(CollegeDto dto) {
        College college = new College();
        college.setCollegeName(dto.getCollegeName());
        college.setCollegeCode(dto.getCollegeCode());
        college.setAddress(dto.getAddress());
        college.setUniversityId(dto.getUniversityId());
        College savedCollege = collegeRepository.save(college);
        log.info("College with id {} has been saved", savedCollege.getCollegeName());
        CollegeDto res = new CollegeDto();

        res.setCollegeName(savedCollege.getCollegeName());
        res.setCollegeCode(savedCollege.getCollegeCode());
        res.setAddress(savedCollege.getAddress());
        res.setUniversityId(savedCollege.getUniversityId());

        return res;
    }

    @Override
    public CollegeDto updateCollege(int id, CollegeDto dto) {
        College collegeb = collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found with id: " + id));

        collegeb.setCollegeName(dto.getCollegeName());
        collegeb.setCollegeCode(dto.getCollegeCode());
        collegeb.setAddress(dto.getAddress());
        collegeb.setUniversityId(dto.getUniversityId());

        College saved = collegeRepository.save(collegeb);

        return CollegeDto.builder()
                .collegeName(saved.getCollegeName())
                .collegeCode(saved.getCollegeCode())
                .address(saved.getAddress())
                .universityId(saved.getUniversityId())
                .build();
    }

    @Override
    public CollegeDto getCollegeById(int id) {

        College c = collegeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("College not found with id: " + id));
        return CollegeDto.builder()
                .collegeName(c.getCollegeName())
                .collegeCode(c.getCollegeCode())
                .address(c.getAddress())
                .universityId(c.getUniversityId())
                .build();
    }

    @Override
    public void deleteCollege(int id) {
        College college = collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found with id: " + id));
        collegeRepository.delete(college);
    }

    @Override
    public List<CollegeDto> getAllColleges() {
        return collegeRepository.findAll().stream().map(c -> CollegeDto.builder()
                .collegeName(c.getCollegeName())
                .collegeCode(c.getCollegeCode())
                .address(c.getAddress())
                .universityId(c.getUniversityId())
                .build()).toList();
    }

    @Override
    public CollegeDto getCollegeByName(String name) {
        College college = collegeRepository.findByCollegeName(name)
                .orElseThrow(() -> new RuntimeException("College not found with name: " + name));
        return CollegeDto.builder()
                .collegeName(college.getCollegeName())
                .collegeCode(college.getCollegeCode())
                .address(college.getAddress())
                .universityId(college.getUniversityId())
                .build();

    }
    @Override
    public CollegeResponse getCollegewithUniversity(int id){

        College college = collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found with id: " + id));

        UniversityDto university =
                universityClient.getUniversityById(
                        college.getUniversityId().intValue());

        return CollegeResponse.builder()
                .id(college.getId())
                .collegeName(college.getCollegeName())
                .address(college.getAddress())
                .collegeCode(college.getCollegeCode())
                .university(university)
                .build();

    }


}