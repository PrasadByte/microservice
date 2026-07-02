package com.university.dto.request;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Createuniversity {
    private String name;
    private String address;
    private int universityCode;

}

