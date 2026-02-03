package com.juandavyc.clients.clients;

import com.intuit.karate.junit5.Karate;

public class ClientsRunner {

    @Karate.Test
    Karate getUsers() {
        return Karate.run("clients").relativeTo(getClass());
    }

}
