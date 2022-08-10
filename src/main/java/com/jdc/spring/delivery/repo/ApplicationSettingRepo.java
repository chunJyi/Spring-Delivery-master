package com.jdc.spring.delivery.repo;

import com.jdc.spring.delivery.entiity.ApplicationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationSettingRepo extends JpaRepository<ApplicationSetting, Integer> {

}
