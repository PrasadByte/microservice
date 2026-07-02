package com.university.Service;

import com.university.dto.request.Createuniversity;
import com.university.dto.responce.GetUniversityDTO;
import com.university.entity.University;
import com.university.exception.UniversityNotFound;
import com.university.repository.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class UniversityService {
   private final UniversityRepository universityRepository;
   private final ModelMapper modelMapper;

   public Createuniversity createUniversity(Createuniversity createuniversity){
       University university = modelMapper.map(createuniversity, University.class);
      University savedUniversity =  universityRepository.save(university);
      return modelMapper.map(savedUniversity, Createuniversity.class);
   }
   public Createuniversity createuniversity2(Createuniversity createuniversity){
       University university= new University();
       university.setName(createuniversity.getName());
       university.setAddress(createuniversity.getAddress());
       university.setUniversityCode(createuniversity.getUniversityCode());
       University saveduniversity = universityRepository.save(university);
       Createuniversity res = new  Createuniversity();
       res.setName(saveduniversity.getName());
       res.setAddress(saveduniversity.getAddress());
       res.setUniversityCode(saveduniversity.getUniversityCode());
       return res;
   }

   public Createuniversity createuniversity3(Createuniversity createuniversity){
       University  university= University.builder().universityCode(createuniversity.getUniversityCode())
               .name(createuniversity.getName()).address(createuniversity.getAddress()).build();
        University savedUniversity = universityRepository.save(university);
        return Createuniversity.builder()
                .universityCode(savedUniversity.getUniversityCode())
                .name(savedUniversity.getName())
                .address(savedUniversity.getAddress())
                .build();
   }

public List<GetUniversityDTO> getUniversity() {
            return universityRepository.findAll().stream().map(u -> {
                GetUniversityDTO university = new GetUniversityDTO();
                university.setId(u.getId());  // Added: Map the ID
                university.setName(u.getName());
                university.setAddress(u.getAddress());
                // university.setUniversityCode(u.getUniversityCode());  // Still commented; uncomment if you add this field to GetUniversityDTO
                return university;  // Added: Return the mapped object
            }).toList();
        }
public List<GetUniversityDTO>getUniversity2() {
return   universityRepository.findAll().stream().map( u -> modelMapper.map(u, GetUniversityDTO.class)).toList();
}
public Createuniversity updateUniversity(Createuniversity createuniversity, int id){
      University update = universityRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("University not found" + id));
update.setUniversityCode(createuniversity.getUniversityCode());
update.setName(createuniversity.getName());
update.setAddress(createuniversity.getAddress());
University savedUniversity =  universityRepository.save(update);
return modelMapper.map(savedUniversity, Createuniversity.class);

   }
    public GetUniversityDTO getUniversityById(int id){
     University finduniversity =   universityRepository.findById(id).orElseThrow(()-> new UniversityNotFound("University not found" + id));
     return modelMapper.map(finduniversity, GetUniversityDTO.class);
    }

    public void  deleteUniversity(int id){
        University university = universityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("University not found" + id));
        universityRepository.delete(university);
    }
    



}
