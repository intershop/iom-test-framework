package com.intershop.oms.test.servicehandler.omsdb.v1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Utility class to manage locking across parallelized Tests. It might be necessary to create read/write locks for some
 * tests to prevent them from impacting each other - e.g. due to a change of business configurations during a test case.
 * <br/><br/>
 * <b>NOTE:</b> Please try to use this class as little as possible. Test cases / specs should generally not impact
 * each other - this could lead to flaky tests and will probably slow down overall test execution due to the need to
 * wait for write locks.
 *
 * @deprecated <b>DO NOT USE!</b> There are resource locking mechanisms for both JUnit Jupiter and Spock that provide
 * the same functionality out of the box. Use those instead - this class only exists for legacy tests.
 */
@Deprecated
class LockHolder
{
    private static final ConcurrentHashMap<Integer, ReentrantReadWriteLock> locks = new ConcurrentHashMap<>();

    // prevent instantiating the class
    private LockHolder()
    {
    }

    /**
     * Acquire a read lock with the specified lockId
     *
     * @param lockId  id of the lock
     * @param timeout timeout in seconds to acquire the lock
     * @return true in case the lock has been acquired successfully, false otherwise (e.g. timeout)
     */
    public static boolean acquireReadLock(int lockId, int timeout)
    {
        try
        {
            return getLock(lockId).readLock().tryLock(timeout, TimeUnit.SECONDS);
        }
        catch(InterruptedException e)
        {
            throw new RuntimeException("issue while aquiring lock", e);
        }
    }

    /**
     * Try to release the read lock with the specified lockId
     *
     * @param lockId id of the lock
     */
    public static void releaseReadLock(int lockId)
    {
        // not sure whether this might lead to a runtime exception in some weird cases. there is no obvious way to
        // check for existing read locks...? In case there are exceptions we can probably work around with try/catch-all
        getLock(lockId).readLock().unlock();
    }

    /**
     * Acquire a write lock with the specified lockId
     *
     * @param lockId  id of the lock
     * @param timeout timeout in seconds to acquire the lock
     * @return true in case the lock has been acquired successfully, false otherwise (e.g. timeout)
     */
    public static boolean acquireWriteLock(int lockId, int timeout)
    {
        try
        {
            return getLock(lockId).writeLock().tryLock(timeout, TimeUnit.SECONDS);
        }
        catch(InterruptedException e)
        {
            throw new RuntimeException("issue while aquiring lock", e);
        }
    }

    /**
     * Try to release the write lock with the specified lockId
     *
     * @param lockId id of the lock
     */
    public static void releaseWriteLock(int lockId)
    {
        if (getLock(lockId).writeLock().isHeldByCurrentThread())
        {
            getLock(lockId).writeLock().unlock();
        }
    }

    private static ReentrantReadWriteLock getLock(int id)
    {
        return locks.computeIfAbsent(id, i -> new ReentrantReadWriteLock(true));
    }

}
