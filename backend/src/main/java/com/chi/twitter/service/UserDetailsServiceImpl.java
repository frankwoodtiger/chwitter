package com.chi.twitter.service;

import com.chi.twitter.entity.Role;
import com.chi.twitter.entity.User;
import com.chi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
/*
    This is for 'failed to lazily initialize a collection of role: com.chi.twitter.entity.User.roles, could not initialize proxy - no Session'
    First, implement a spring transactionManager. Since we have @EnableTransactionManagement on database config class,
    we have transactional management support already. We just need to add an @Transactional annotation to the class that
    will fetch the entities. This will then start a db transaction for the duration of the method call allowing any lazy
    collection to be retrieved from the db as and when you try to use them.

    See: https://stackoverflow.com/questions/22821695/how-to-fix-hibernate-lazyinitializationexception-failed-to-lazily-initialize-a
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
                    accountNonLocked, getAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Collection<Role> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
