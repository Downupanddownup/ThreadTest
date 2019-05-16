package com.flf;

public class StatisticalTime {
    private long testOne(PerformanceTest test){
        long start = System.currentTimeMillis();
        test.performance();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public void testFiveTimes(PerformanceTest test,String identify){
        long sum = 0;
        long[] times = new long[5];
        for (int i = 0; i < 5; i++) {
            times[i] = testOne(test);
            sum += times[i];
        }
        double avg = sum/5.0;

        System.out.print(identify + "，平均时间（ms）:"+avg);
        for (long l : times) {
            System.out.print(","+l);
        }
        System.out.println();
    }
}
