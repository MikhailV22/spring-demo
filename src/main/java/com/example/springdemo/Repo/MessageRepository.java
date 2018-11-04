package com.example.springdemo.Repo;

import com.example.springdemo.Entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
