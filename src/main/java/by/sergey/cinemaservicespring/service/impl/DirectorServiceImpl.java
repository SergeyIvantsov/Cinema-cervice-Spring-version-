package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.dto.DirectorDto;
import by.sergey.cinemaservicespring.entity.Director;
import by.sergey.cinemaservicespring.repository.DirectorRepository;
import by.sergey.cinemaservicespring.service.DirectorService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Override
    public Set<DirectorDto> getAll(){
        return directorRepository.findAll().stream()
                .map(ConverterUtil::convertDirector)
                .collect(Collectors.toSet());
    }


    @Override
    public DirectorDto save(DirectorDto directorDto) {
        Director saveDir = directorRepository.save(ConverterUtil.convertDirector(directorDto));
        DirectorDto addDirectorDto = ConverterUtil.convertDirector(saveDir);
        return addDirectorDto;
    }




}
