package com.example.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserModelDTO userModelDTO) {
        UserModel userModelToBeSaved = convertToModel(userModelDTO);
        userRepository.save(userModelToBeSaved);
    }

    public List<UserModelDTO> findAll() {
        List<UserModel> allUsers = userRepository.findAll();
        return adapt(allUsers);

    }

    private List<UserModelDTO> adapt(List<UserModel> allUsers) {
        ArrayList<UserModelDTO> userModelDTOS = new ArrayList<>();
        for (UserModel user : allUsers) {
            userModelDTOS.add(convertToDTO(user));
        }
        return userModelDTOS;
    }


    private UserModel convertToModel(UserModelDTO userModelDTO) {
        return UserModel.builder()
                .username(userModelDTO.getUsername())
                .email(userModelDTO.getEmail())
                .build();
    }

    private UserModelDTO convertToDTO(UserModel userModel) {
        return UserModelDTO.builder()
                .userid(userModel.getUserid())
                .username(userModel.getUsername())
                .email(userModel.getEmail())
                .build();
    }


}
