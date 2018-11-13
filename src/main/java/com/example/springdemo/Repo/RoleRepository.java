package com.example.springdemo.Repo;

import com.example.springdemo.Entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
