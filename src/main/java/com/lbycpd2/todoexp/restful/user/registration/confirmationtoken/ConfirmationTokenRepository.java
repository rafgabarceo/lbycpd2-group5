package com.lbycpd2.todoexp.restful.user.registration.confirmationtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String confirmationToken);

}
