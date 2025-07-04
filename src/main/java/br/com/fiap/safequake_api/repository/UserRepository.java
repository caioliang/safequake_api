package br.com.fiap.safequake_api.repository;

import br.com.fiap.safequake_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);
    boolean existsByEmail(String email);
    

}