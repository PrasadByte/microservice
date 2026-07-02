package com.college.dto.response;

import com.college.dto.UniversityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollegeResponse {
    private int id;

    private String collegeName;

    private String address;

    private String collegeCode;

    private UniversityDto university;
}
