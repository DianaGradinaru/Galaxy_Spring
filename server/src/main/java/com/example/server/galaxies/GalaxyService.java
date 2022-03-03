package com.example.server.galaxies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GalaxyService {

    @Autowired
    private GalaxyRepository galaxyRepository;

//    public void addGalaxy(GalaxyModelDTO galaxyModelDTO) {
//        GalaxyModel galaxyModelToBeSaved = convertToGalaxyModel(galaxyModelDTO);
//        galaxyRepository.save(galaxyModelToBeSaved);
//
//    }

    public void addGalaxy(GalaxyModel galaxyModel) {
        GalaxyModel newGalaxyModel = new GalaxyModel();
        newGalaxyModel.setId(galaxyModel.getId());
        newGalaxyModel.setMessage(galaxyModel.getMessage());
        newGalaxyModel.setSent_by_id(galaxyModel.getSent_by_id());
        newGalaxyModel.setImage(galaxyModel.getImage());
        newGalaxyModel.setCreated_at(LocalDateTime.now());
        galaxyRepository.save(newGalaxyModel);
    }

    public List<GalaxyModelDTO> findAll() {
        List<GalaxyModel> allGalaxies = galaxyRepository.findAll();
        return adapt(allGalaxies);
    }

    private List<GalaxyModelDTO> adapt(List<GalaxyModel> allGalaxies) {
        ArrayList<GalaxyModelDTO> galaxyModelDTOS = new ArrayList<>();
        for (GalaxyModel galaxy : allGalaxies) {
            galaxyModelDTOS.add(covertToGalaxyDTOModel(galaxy));
        }
        return galaxyModelDTOS;
    }

    private GalaxyModel convertToGalaxyModel(GalaxyModelDTO galaxyModelDTO) {
        return GalaxyModel.builder()
                .sent_by_id(galaxyModelDTO.getSent_by_id())
                .message(galaxyModelDTO.getMessage())
                .image(galaxyModelDTO.getImage())
                .created_at(galaxyModelDTO.getCreated_at())
                .build();
    }

    private GalaxyModelDTO covertToGalaxyDTOModel(GalaxyModel galaxyModel) {
        return GalaxyModelDTO.builder()
                .id(galaxyModel.getId())
                .sent_by_id(galaxyModel.getSent_by_id())
                .message(galaxyModel.getMessage())
                .image(galaxyModel.getImage())
                .created_at(galaxyModel.getCreated_at())
                .build();
    }


}