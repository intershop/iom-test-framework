package com.intershop.oms.test.servicehandler.omsdb.v1;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("deprecation")
public class LockHolderTest
{
    private ExecutorService es = Executors.newFixedThreadPool(4);

    @Test
    public void testReadRead() throws Exception
    {
        int lockId = 1;
        int timeout = 20;
        assertTrue(LockHolder.acquireReadLock(lockId, timeout));
        CompletableFuture<Boolean> secondLock = CompletableFuture.supplyAsync(
                        () -> LockHolder.acquireReadLock(lockId, timeout), es);
        assertTrue(secondLock.get());
    }

    @Test
    public void testReadWrite() throws Exception
    {
        int lockId = 2;
        int timeout = 2;
        assertTrue(LockHolder.acquireReadLock(lockId, timeout));
        CompletableFuture<Boolean> secondLock = CompletableFuture.supplyAsync(
                        () -> LockHolder.acquireWriteLock(lockId, timeout), es);
        assertFalse(secondLock.get());
        LockHolder.releaseReadLock(lockId);
        secondLock = CompletableFuture.supplyAsync(() -> LockHolder.acquireWriteLock(lockId, timeout));
        assertTrue(secondLock.get());
    }

    @Test
    public void testWriteRead() throws Exception
    {
        int lockId = 3;
        int timeout = 2;
        assertTrue(LockHolder.acquireWriteLock(lockId, timeout));
        CompletableFuture<Boolean> secondLock = CompletableFuture.supplyAsync(
                        () -> LockHolder.acquireReadLock(lockId, timeout), es);
        assertFalse(secondLock.get());
        LockHolder.releaseWriteLock(lockId);
        secondLock = CompletableFuture.supplyAsync(() -> LockHolder.acquireReadLock(lockId, timeout), es);
        assertTrue(secondLock.get());
    }

    @Test
    public void testWriteWriteTimeout() throws Exception
    {
        int lockId = 4;
        int timeoutA = 2;
        int timeoutB = 5;

        assertTrue(LockHolder.acquireWriteLock(lockId, timeoutA));
        CompletableFuture<Boolean> secondLock = CompletableFuture.supplyAsync(
                        () -> LockHolder.acquireWriteLock(lockId, timeoutA), es);
        assertFalse(secondLock.get());
        secondLock = CompletableFuture.supplyAsync(() -> LockHolder.acquireWriteLock(lockId, timeoutB));
        LockHolder.releaseWriteLock(lockId);
        assertTrue(secondLock.get());
    }
}