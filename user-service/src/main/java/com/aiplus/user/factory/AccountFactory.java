package com.aiplus.user.factory;

import org.springframework.stereotype.Component;

import com.aiplus.user.model.Account;
import com.aiplus.user.model.AdminAccount;
import com.aiplus.user.model.ClientAccount;
import com.aiplus.user.model.DeveloperAccount;
import com.aiplus.user.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountFactory {

    public Account createAccountForUser(User user) {
        log.info("Creating account for user: {} with role: {}", user.getUsername(), user.getRole().name());
        switch (user.getRole().name()) {
            case "DEVELOPER" -> {
                DeveloperAccount developerAccount = new DeveloperAccount();
                developerAccount.setUser(user);
                log.info("Created DeveloperAccount for user: {}", user.getUsername());
                return developerAccount;
            }
            case "ADMIN" -> {
                AdminAccount adminAccount = new AdminAccount();
                adminAccount.setUser(user);
                log.info("Created AdminAccount for user: {}", user.getUsername());
                return adminAccount;
            }
            case "CLIENT" -> {
                ClientAccount clientAccount = new ClientAccount();
                clientAccount.setUser(user);
                log.info("Created ClientAccount for user: {}", user.getUsername());
                return clientAccount;
            }

            default -> throw new IllegalArgumentException("Unknown role : " + user.getRole());

        }

    }

}
