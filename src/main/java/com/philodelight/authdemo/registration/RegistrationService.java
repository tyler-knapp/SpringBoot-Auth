package com.philodelight.authdemo.registration;

import com.philodelight.authdemo.appuser.AppUser;
import com.philodelight.authdemo.appuser.AppUserRole;
import com.philodelight.authdemo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email not Valid");
        }
        
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                        )
        );
    }
}
