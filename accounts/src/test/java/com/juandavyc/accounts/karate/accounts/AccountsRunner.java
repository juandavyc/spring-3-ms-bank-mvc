package com.juandavyc.accounts.karate.accounts;

import com.intuit.karate.junit5.Karate;

public class AccountsRunner {

    @Karate.Test
    Karate getUsers() {
        return Karate.run("accounts").relativeTo(getClass());
    }

}
