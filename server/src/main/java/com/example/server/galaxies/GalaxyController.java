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

//    @PostMapping
//    public void addGalaxy(@RequestBody GalaxyModelDTO galaxyModelDTO) {
//        galaxyService.addGalaxy(galaxyModelDTO);
//
//    }

//    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public GalaxyModel addGalaxy(@RequestBody GalaxyModel galaxyModel) {
//        galaxyModel.setUsername(userService.findUserById(galaxyModel.getSent_by_id()));
        galaxyModel.setUser(UserModel.builder().build()); //??
        galaxyService.addGalaxy(galaxyModel);
        return galaxyService.findLast();
    }

//    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<GalaxyModelDTO> getAllGalaxies(){
        return galaxyService.findAll();
    }

//    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/last")
    public GalaxyModel getAllGalaxies1(){
        return galaxyService.findLast();
    }
}
