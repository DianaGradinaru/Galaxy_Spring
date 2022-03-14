package com.example.server.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {


    @Autowired
    private UserRepository userRepository;

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
            if (Objects.equals(user.getUserid(), id)) {
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


    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }
}
