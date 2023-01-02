package com.thisvsthat.javavsdotnet.repository;

import com.thisvsthat.javavsdotnet.model.BirthModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthRepository extends CrudRepository<BirthModel, Long> {
}
