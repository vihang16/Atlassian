package test.java;


import main.java.FileObject;
import main.java.Main;
import main.java.Result;

import main.java.base.RateLimiter;
import main.java.derived.FixedWindowRateLimiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/*
file1.txt (size: 100)
file2.txt (size: 200) in collection "collection1"
file3.txt (size: 200) in collection "collection1"
file4.txt (size: 300) in collection "collection2"
file5.txt (size: 10)
 */
public class MainTest {

    @Test
    public void testBaseScenario(){
        List<FileObject> fileObjectList = List.of(
                new FileObject("file1","", 100), new FileObject("file2","collection1",200),
                new FileObject("file3", "collection2", 200), new FileObject("file4","collection2", 300),
                new FileObject("file5", "",10));
        Main m = new Main();
        Result result = m.generateTotalSize(fileObjectList,1);
        assertEquals(810, result.totalSize);
        assertEquals(List.of("collection2"), result.collectionName);


    }

    @Test
    public void testBseSlidingWindowValue(){
        RateLimiter rateLimiter = new FixedWindowRateLimiter();
        assertTrue(rateLimiter.rateLimit(1));
        assertTrue(rateLimiter.rateLimit(1));
        assertTrue(rateLimiter.rateLimit(2));
        assertFalse(rateLimiter.rateLimit(1));
        assertTrue(rateLimiter.rateLimit(3));


    }





}
