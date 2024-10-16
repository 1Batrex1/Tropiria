package pl.tropiria.backend.config.security;

import lombok.AllArgsConstructor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.tropiria.backend.account.Account;
import pl.tropiria.backend.account.AccountRepository;
import pl.tropiria.backend.role.Role;

import java.util.ArrayList;
import java.util.List;

import static pl.tropiria.backend.config.constants.ErrorsConstant.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class TropiriaUserDetails implements UserDetailsService {

    private final AccountRepository accountRepository;

    private static final Log logger = LogFactory.getLog(TropiriaUserDetails.class);




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Account> account = accountRepository.findByLoginAndActive(username);
        if (account.isEmpty())
        {
            throw new UsernameNotFoundException(USER_NOT_FOUND.MESSAGE);
        }
        return setUpUser(account.getFirst());


    }

    private UserDetails setUpUser(Account userAccount) {
        String userName = userAccount.getLogin();
        String password = userAccount.getPassword();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : userAccount.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(userName, password, authorities);
    }
}
