package com.aiplus.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiplus.user.dto.AccountUpdateRequest;
import com.aiplus.user.exceptions.UserNotFoundException;
import com.aiplus.user.factory.AccountFactory;
import com.aiplus.user.model.Account;
import com.aiplus.user.model.DeveloperAccount;
import com.aiplus.user.model.User;
import com.aiplus.user.repository.AccountRepository;
import com.aiplus.user.repository.UserRepository;
import com.aiplus.user.service.AccountService;
import com.aiplus.user.service.strategy.AccountUpdateStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountFactory accountFactory;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private final List<AccountUpdateStrategy> strategies;

    @Override
    public Account findByUser(User user) {
        return accountRepository.findById(user.getAccount().getId())
                .orElseThrow(() -> new UserNotFoundException("Account not found for user: " + user.getEmail()));

    }

    @Override
    public Account createAccount(User user) {
        return accountFactory.createAccountForUser(user);
    }

    /**
     * Updates the user's account by delegating to the correct strategy.
     *
     * @param user    the authenticated user
     * @param request the update request with new values
     * @return the updated account
     */
    @Override
    public Account updateAccount(User user, AccountUpdateRequest request) {
        User managedUser = userRepository.getReferenceById(user.getId());
        return strategies.stream().filter(s -> s.supports(user)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No strategy for user role"))
                .update(managedUser, request);
    }

    @Override
    public Account updateDockerCredentials(User user, String dockerUsername, String dockerPat) {
        DeveloperAccount account = (DeveloperAccount) findByUser(user);
        if (account == null) {
            throw new UserNotFoundException("Account not found for user: " + user.getEmail());
        }

        account.setDockerUsername(dockerUsername);
        account.setDockerPat(dockerPat);

        return accountRepository.save(account);
    }

}
