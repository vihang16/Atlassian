package main.java.base;

public interface RateLimiter {

    boolean rateLimit(int customerId);
}
