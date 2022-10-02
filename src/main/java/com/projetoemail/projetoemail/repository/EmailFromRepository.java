package com.projetoemail.projetoemail.repository;

import com.projetoemail.projetoemail.model.EmailFrom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmailFromRepository extends CrudRepository<EmailFrom, Long> {

    Optional<EmailFrom> findById(Long id);

    EmailFrom findByEmail(String email);

    List<EmailFrom> findAll();
}