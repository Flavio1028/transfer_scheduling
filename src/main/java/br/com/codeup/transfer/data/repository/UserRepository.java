package br.com.codeup.transfer.data.repository;

import br.com.codeup.transfer.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDocumentNumber(@Param("documentNumber") String documentNumber);

}