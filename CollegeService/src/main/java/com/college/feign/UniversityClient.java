package com.college.feign;

import com.college.dto.UniversityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UNIVERSITY" )
public interface UniversityClient {
    @GetMapping("/api/university/{id}")
    UniversityDto getUniversityById(@PathVariable("id") int id);
}
