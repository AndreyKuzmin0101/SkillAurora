package ru.kpfu.itis.kuzmin.skillshare.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;
import ru.kpfu.itis.kuzmin.skillshare.repository.spring.UserSpringRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BaseUserDetailsService implements UserDetailsService {

    private final UserSpringRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser =  userRepository.findByUsername(username);


        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return new BaseUserDetails(user);
        }
        throw new UsernameNotFoundException("User with username = %s - not found".formatted(username));
    }
}
