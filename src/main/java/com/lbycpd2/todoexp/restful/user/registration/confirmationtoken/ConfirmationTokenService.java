package com.lbycpd2.todoexp.restful.user.registration.confirmationtoken;

import com.lbycpd2.todoexp.restful.user.User;
import com.lbycpd2.todoexp.restful.user.UserRepository;
import com.lbycpd2.todoexp.restful.user.exceptions.ConfirmationTokenNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }

    public void deleteConfirmationToken(String id){
        confirmationTokenRepository.deleteById(id);
    }

    public Optional<ConfirmationToken> getConfirmationToken(String token) throws ConfirmationTokenNotFoundException {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
        if(confirmationToken.isEmpty()){
            throw new ConfirmationTokenNotFoundException("Confirmation token " + token + " not found");
        }

        return confirmationToken;
    }

    public void confirmUser(ConfirmationToken confirmationToken){
        User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        deleteConfirmationToken(confirmationToken.getId());
    }
}
