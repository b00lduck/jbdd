package com.nigames.jbdd.service.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Random password generator for new user passwords.
 *
 * @author Daniel
 */
@Component
public class RandomPasswordGenerator {

    private static final int BITS = 32;
    private static final int RADIX = 36;

    private final transient SecureRandom random = new SecureRandom();

    /**
     * Create weak new password.
     *
     * @return new password
     */
    public String getRandomPassword() {
        return new BigInteger(BITS, random).toString(RADIX);
    }

}
