package com.cybersoft.osahaneat.repository;

import com.cybersoft.osahaneat.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    public List<Users> findByEmailAndPassword(String email,String password);
}
