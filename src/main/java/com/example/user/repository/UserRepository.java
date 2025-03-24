package com.example.user.repository;

import com.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/24 下午9:43
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    public User findUserByEmail(String email);

}
