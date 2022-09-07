package com.latebloomers.MovieInMine.model.repository;

import com.latebloomers.MovieInMine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

// JPARepository가 기본적인 CRUD 함수를 들고 있다.
// @Repository라는 어노테이션이 없어도 IoC가 된다. 이유는 JpaRepository를 상속했기 때문에 Bean으로 등록가능
public interface UserRepository extends JpaRepository<User, Integer> {


    // select * from user where username= 1? 실행
    // JPA Query methods 참고
    public User findByUsername(String username);
}
