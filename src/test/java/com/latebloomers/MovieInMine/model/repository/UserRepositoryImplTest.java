package com.latebloomers.MovieInMine.model.repository;

import com.latebloomers.MovieInMine.model.Party;
import com.latebloomers.MovieInMine.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class UserRepositoryImplTest {

    @Autowired UserRepository userRepository;

    @Test
    public void testUser() throws Exception{

        //given
        User user = new User();
        user.setUsername("userA");
        user.setNickname("AA");

        //when
        User user1 = userRepository.save(user);
        Optional<User> findUser = userRepository.findById(user1.getId());
        User findUserByUsername = userRepository.findByUsername(user.getUsername());

        //then
        findUser.ifPresent(selectUser ->
        {
            Assertions.assertThat(selectUser.getId()).isEqualTo(user.getId());
            Assertions.assertThat(selectUser.getUsername()).isEqualTo(user.getUsername());
//            System.out.println(findUser.toString());
//            System.out.println(selectUser.get);
        });

//        System.out.println(findUser.get());
        System.out.println(findUserByUsername.toString());
    }

    @Test
    public void testFailFindUserTest(){

        //given

        //when
        Optional<User> optionalUser = userRepository.findById(99);

        //then
        optionalUser.ifPresentOrElse(user -> System.out.println("have User that Id is 99"),
                () -> log.error("dont have User that Id is 99"));

    }

    @Test
    public void testTeamAndUser() throws Exception {
        //given
        Party party = new Party();
        party.setPartyName("PartyA");

        User user = new User();
        user.setUsername("userA");
        user.setNickname("AA");

        //when
        
    }

}