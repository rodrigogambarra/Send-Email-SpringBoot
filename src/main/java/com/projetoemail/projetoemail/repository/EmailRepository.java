package com.projetoemail.projetoemail.repository;

import com.projetoemail.projetoemail.model.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, Long> {
    List<Email> findAll();
}
