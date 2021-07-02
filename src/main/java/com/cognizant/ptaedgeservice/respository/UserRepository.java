package com.cognizant.ptaedgeservice.respository;

import com.cognizant.ptaedgeservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
}
