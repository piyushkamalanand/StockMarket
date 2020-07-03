package com.wiley.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wiley.dto.LoginDTO;
import com.wiley.models.Users;
@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
	
	@Query(value="INSERT INTO users(user_name,password,email) VALUES(:uName,:password,:email)",nativeQuery=true)
	@Transactional
	@Modifying
    public int insertUser(@Param("uName")String userName,@Param("password")String password,@Param("email")String email);
	
	@Query(value="DELETE FROM users where user_id=:uId",nativeQuery=true)
	@Transactional
	@Modifying
	public int deleteUser(@Param("uId")int userId);
	
	@Query(value="UPDATE users SET password=:password WHERE email=:email",nativeQuery=true)
	@Transactional
	@Modifying
	public int updateUser(@Param("email")String email,@Param("password")String password);
	

	@Query("SELECT new com.wiley.dto.LoginDTO(l.userName,l.password,l.userId) FROM Users l ")
	public List<LoginDTO> findAllUsers();
}
