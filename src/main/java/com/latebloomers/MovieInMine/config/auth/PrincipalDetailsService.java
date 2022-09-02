package com.latebloomers.MovieInMine.config.auth;

import com.latebloomers.MovieInMine.model.User;
import com.latebloomers.MovieInMine.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl을 /login으로 설정했기 때문에
// login 요청이 오면 자동으로 UserDetailsService 타입의 SpringBean 을 찾아 loadUserByUsername 메소드가 실행된다.
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if (userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
