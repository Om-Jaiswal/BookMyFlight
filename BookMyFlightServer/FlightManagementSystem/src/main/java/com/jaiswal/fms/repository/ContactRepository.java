package com.jaiswal.fms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.fms.model.Contact;

@Repository
public interface ContactRepository extends MongoRepository<Contact, Long> {

}
