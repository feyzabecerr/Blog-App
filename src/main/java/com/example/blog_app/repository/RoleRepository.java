package com.example.blog_app.repository;

import com.example.blog_app.model.ERole;
import com.example.blog_app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findRoleByName(ERole name);
}
