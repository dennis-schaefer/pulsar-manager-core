package de.schaeferd.pulsar.manager.security;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@AnonymousAllowed
public class UserInfoService
{
    public UserInfo getUserInfo()
    {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        final List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new UserInfo(auth.getName(), authorities);
    }
}
