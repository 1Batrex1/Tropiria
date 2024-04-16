package pl.tropiria.backend.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tropiria.backend.account.Account;
import pl.tropiria.backend.account.AccountRepository;

import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.LOGIN;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AccountRepository accountRepository;

    @RequestMapping(LOGIN)
    public Account getUserDetailsAfterLogin(Authentication authentication){
        if (authentication == null){
            return null;
        }
        List<Account> accounts = accountRepository.findByLoginAndActive(authentication.getName());
        if(accounts.isEmpty()){
            return null;
        }
        return accounts.getFirst();
    }

}
