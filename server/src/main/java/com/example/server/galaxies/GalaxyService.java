package com.example.server.galaxies;

import com.example.server.user.UserModel;
import com.example.server.user.UserModelDTO;
import com.example.server.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GalaxyService {

    @Autowired
    private GalaxyRepository galaxyRepository;
    private UserService userService;

    public void addGalaxy(GalaxyModel galaxyModel) {
        GalaxyModel newGalaxyModel = new GalaxyModel();
        newGalaxyModel.setId(galaxyModel.getId());
        newGalaxyModel.setUser(galaxyModel.getUser());
        newGalaxyModel.setMessage(galaxyModel.getMessage());
        newGalaxyModel.setImage(galaxyModel.getImage());
        newGalaxyModel.setCreated_at(LocalDateTime.now());
        galaxyRepository.save(newGalaxyModel);
    }

    public List<GalaxyModelDTO> findAll() {
        List<GalaxyModel> allGalaxies = galaxyRepository.findAll();
        return adapt(allGalaxies);
    }

    public GalaxyModel findLast() {
        List<GalaxyModel> allGalaxies = galaxyRepository.findAll();
        GalaxyModel last = allGalaxies.get(allGalaxies.size() - 1);
        return last;
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
                .user(galaxyModelDTO.getUserModel())
                .message(galaxyModelDTO.getMessage())
                .image(galaxyModelDTO.getImage())
                .created_at(galaxyModelDTO.getCreated_at())
                .build();
    }

    private GalaxyModelDTO covertToGalaxyDTOModel(GalaxyModel galaxyModel) {
        return GalaxyModelDTO.builder()
                .id(galaxyModel.getId())
                .userModel(galaxyModel.getUser())
                .message(galaxyModel.getMessage())
                .image(galaxyModel.getImage())
                .created_at(galaxyModel.getCreated_at())
                .build();
    }


}
