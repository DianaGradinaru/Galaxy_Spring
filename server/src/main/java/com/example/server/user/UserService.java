package com.example.server.user;

import com.example.server.registration.token.ConfirmationToken;
import com.example.server.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    public void addUser(UserModelDTO userModelDTO) {
        UserModel userModelToBeSaved = convertToModel(userModelDTO);
        userRepository.save(userModelToBeSaved);
    }

    public String signUpUser(UserModel user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("Email already taken.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableAppUser(email);
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
