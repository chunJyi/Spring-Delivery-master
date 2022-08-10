package com.jdc.spring.delivery.repo;

import com.jdc.spring.delivery.entiity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<Profile, Long> {

	Profile findByAccountEmail(String email);
}
