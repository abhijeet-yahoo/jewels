package com.jiyasoft.jewelplus.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiyasoft.jewelplus.domain.admin.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
