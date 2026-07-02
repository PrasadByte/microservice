package com.college.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollegeDto {
    private String collegeName;

    private String address;

    private String collegeCode;

    private Long universityId;
}
