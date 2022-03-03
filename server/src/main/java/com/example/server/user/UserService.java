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

    public String findUserById(Long id) {
        ArrayList<UserModelDTO> allUsers = new ArrayList<>();
        for (UserModelDTO user : allUsers) {
            if (user.getUserid() == id) {
                return user.getUsername();
            }
        }
        return "";
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
                .password(userModelDTO.getPassword())
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
