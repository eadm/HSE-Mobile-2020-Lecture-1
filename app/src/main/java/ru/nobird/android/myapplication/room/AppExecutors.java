package ru.nobird.android.myapplication.room;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors
{
    private static class DiskIOThreadExecutor implements Executor
    {
        private final Executor mDiskIO;

        public DiskIOThreadExecutor()
        {
            mDiskIO = Executors.newSingleThreadExecutor();
        }

        @Override
        public void execute(@NonNull Runnable command)
        {
            mDiskIO.execute(command);
        }
    }

    private static class MainThreadExecutor implements Executor
    {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command)
        {
            mainThreadHandler.post(command);
        }
    }

    private static volatile AppExecutors INSTANCE;

    private final DiskIOThreadExecutor diskIo;
    private final MainThreadExecutor mainThread;

    private AppExecutors()
    {
        diskIo = new DiskIOThreadExecutor();
        mainThread = new MainThreadExecutor();
    }

    public static AppExecutors getInstance()
    {
        if(INSTANCE == null)
        {
            synchronized(AppExecutors.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new AppExecutors();
                }
            }
        }
        return INSTANCE;
    }

    public Executor diskIo()
    {
        return diskIo;
    }

    public Executor mainThread()
    {
        return mainThread;
    }
}
