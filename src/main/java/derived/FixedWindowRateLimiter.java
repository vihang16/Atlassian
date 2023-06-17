package main.java.derived;

import main.java.base.RateLimiter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int timeSpan=600;
    private final int limit=2;

    private Map<Integer, List<Long>> resourceAccessListMap = new HashMap<>();

    public Map<Integer, List<Long>> getResourceAccessListMap() {
        return resourceAccessListMap;
    }


    @Override
    public boolean rateLimit(int customerId) {
        boolean result = false;
        if(resourceAccessListMap.containsKey(customerId)){

             result = validateAndAddIfResourceCanBeAccessed(resourceAccessListMap, customerId);

        }else{
            List<Long> accessList = new LinkedList<>();
            accessList.add(Instant.now().getEpochSecond());
            resourceAccessListMap.put(customerId, accessList);
            result = true;
        }
        return result;
    }

    private boolean validateAndAddIfResourceCanBeAccessed(Map<Integer, List<Long>> resourceAccessListMap, int customerId) {
        List<Long> accessTimes = resourceAccessListMap.get(customerId);
        boolean flag = false;
        if(accessTimes.size() < limit){
            accessTimes.add(Instant.now().getEpochSecond());
            flag= true;
        } else if ( resourceAccessListMap.get(customerId).get(0)< Instant.now().getEpochSecond()-Instant.now().minus(timeSpan, ChronoUnit.SECONDS).getEpochSecond()) {

            while(!accessTimes.isEmpty() && accessTimes.get(0)< Instant.now().getEpochSecond()-Instant.now().minus(timeSpan, ChronoUnit.SECONDS).getEpochSecond()){
                accessTimes.remove(0);
            }
            accessTimes.add(Instant.now().getEpochSecond());
            flag= true;
        }
        return flag;
    }
}
