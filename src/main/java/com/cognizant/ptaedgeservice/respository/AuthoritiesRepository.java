package com.cognizant.ptaedgeservice.respository;

import com.cognizant.ptaedgeservice.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities,Integer> {
}
