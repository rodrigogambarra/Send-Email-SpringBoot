package com.projetoemail.projetoemail.repository;

import com.projetoemail.projetoemail.model.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmailRepositoriy extends CrudRepository<Email, Long> {

    Optional<Email> findById(Long id);

    List<Email> findAll();
}