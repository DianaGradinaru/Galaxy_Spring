package com.example.server.galaxies;

import com.example.server.user.UserModel;
import com.example.server.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/galaxies")
public class GalaxyController {

    @Autowired
    private GalaxyService galaxyService;

    @Autowired
    private UserService userService;


    @PostMapping
    public GalaxyModel addGalaxy(@RequestBody GalaxyModel galaxyModel) {
        galaxyService.addGalaxy(galaxyModel);
        return galaxyService.findLast();
    }

    @GetMapping
    public List<GalaxyModelDTO> getAllGalaxies(){
        return galaxyService.findAll();
    }

    @GetMapping("/last")
    public GalaxyModel getAllGalaxies1(){
        return galaxyService.findLast();
    }
}
