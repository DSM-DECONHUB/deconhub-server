package com.example.deconhubserver.global.security.auth;

import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.facade.UserFacade;
import com.example.deconhubserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        User user = userFacade.getUserByAccountId(accountId);
        return new AuthDetails(user);
    }
}
