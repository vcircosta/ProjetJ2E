package com.olympics.ticketing.repository;

import com.olympics.ticketing.model.Role;
import com.olympics.ticketing.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(UserRole name);
}
