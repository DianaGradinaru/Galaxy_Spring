package com.example.server.galaxies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/galaxies")
public class GalaxyController {

    @Autowired
    private GalaxyService galaxyService;

//    @PostMapping
//    public void addGalaxy(@RequestBody GalaxyModelDTO galaxyModelDTO) {
//        galaxyService.addGalaxy(galaxyModelDTO);
//
//    }

    @PostMapping
    public void addGalaxy(@RequestBody GalaxyModel galaxyModel) {
        galaxyService.addGalaxy(galaxyModel);
    }
}
