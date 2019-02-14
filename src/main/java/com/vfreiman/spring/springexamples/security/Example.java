package com.vfreiman.spring.springexamples.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Example {

    private void main() {
        UserDetailsService uds = new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                return getUserDetails(s, "pass", new HashSet<String>() {
                    {
                        add("ROLE_USER");
                    }
                });
            }
        };

        UserDetails john = uds.loadUserByUsername("John");

        final String MODE_THREADLOCAL = SecurityContextHolder.MODE_THREADLOCAL;
        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication authentication = securityContext.getAuthentication();
        authentication.getPrincipal();
        authentication.getDetails();
        authentication.getAuthorities();
        authentication.getCredentials();
        authentication.isAuthenticated();


    }
    public static void main(String[] args) {
        new Example().main();
    }

    public UserDetails getUserDetails(String username, String password, Collection<? extends String> roles) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {

                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                roles.forEach(role -> {
                    grantedAuthorities.add(new GrantedAuthority() {
                        @Override
                        public String getAuthority() {
                            return role;
                        }
                    });
                });

                return grantedAuthorities;
            }

            @Override
            public String getPassword() {
                return String.format("{%s}%s", "bcrypt", new BCryptPasswordEncoder().encode(password));
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
