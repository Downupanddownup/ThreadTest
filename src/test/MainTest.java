package test;

import com.flf.ManyThreadTest;
import com.flf.PerformanceTest;
import com.flf.StatisticalTime;
import com.sort.MySort;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Main Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class MainTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private int forNum = 100000;

    private class Performace implements PerformanceTest {
        @Override
        public void performance() {
            for (int i = 0; i < 10; ++i) {
//            System.out.println("哈哈"+i);
                int a = 0;
            }
        }
    }

    private class StringSplicing implements PerformanceTest {
        @Override
        public void performance() {
            String s = "";
            for (int i = 0; i < LETTERS.length()*forNum; ++i) {
                s += LETTERS.charAt(i%LETTERS.length());
            }
            System.out.println(s);
        }
    }

    private class StringBuilderSplicing implements PerformanceTest {
        @Override
        public void performance() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < LETTERS.length()*forNum; ++i) {
                builder.append(LETTERS.charAt(i%LETTERS.length()));
            }
            System.out.println(builder.toString());
        }
    }

    private class StringBufferSplicing implements PerformanceTest {
        @Override
        public void performance() {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < LETTERS.length()*forNum; i++) {
                buffer.append(LETTERS.charAt(i%LETTERS.length()));
            }
            System.out.println(buffer.toString());
        }
    }

    private class StringBuilderAccuracy implements PerformanceTest{
        StringBuilder builder = new StringBuilder("0000011111");
        @Override
        public void performance() {
            for (int i = 0; i < 101; i++) {
                builder.reverse();
            }
            System.out.println(builder.toString());
        }
    }

    private class StringBufferAccuracy implements PerformanceTest{
        StringBuffer buffer = new StringBuffer("0000011111");
        @Override
        public void performance() {
            for (int i = 0; i < 101; i++) {
                buffer.reverse();
            }
            System.out.println(buffer.toString());
        }
    }

    @Test
    public void testMain() throws Exception {

        StatisticalTime time = new StatisticalTime();
//    time.testFiveTimes(new Performace(),"你好");
        ManyThreadTest threadTest = new ManyThreadTest(new Performace());
        time.testFiveTimes(threadTest, "结果");
//    int a = System.in.read();
//    System.out.println(a);
    }

    @Test
    public void testString() {
        forNum = 100000;
        StatisticalTime time = new StatisticalTime();
//        time.testFiveTimes(new StringSplicing(),"String");
        time.testFiveTimes(new StringBuilderSplicing(),"StringBuilder");
        time.testFiveTimes(new StringBufferSplicing(),"StringBufffer");
    }



    @Test
    public void testAccuracy(){
        forNum = 1;
        StatisticalTime time = new StatisticalTime(1);
        StringBufferAccuracy test = new StringBufferAccuracy();
        String name = "StringBuffer";
        ManyThreadTest threadTest = new ManyThreadTest(test,50);
        time.testFiveTimes(threadTest,name);
        System.out.println("rseult:" + test.buffer.toString());

        /*forNum = 1;
        StatisticalTime time = new StatisticalTime(1);
        StringBuilderAccuracy test = new StringBuilderAccuracy();
        String name = "StringBuilder";
        ManyThreadTest threadTest = new ManyThreadTest(test,50);
        time.testFiveTimes(threadTest,name);
        System.out.println("rseult:" + test.builder.toString());*/

    }

    private List<Integer> getArray(int n){
        Random random = new Random();
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            int idx = 0;
            do {
                idx = random.nextInt(n);
            } while (array[idx] != null);
            array[idx] = i;
        }
        return Arrays.asList(array);
    }

    private List<Integer> copy(List<Integer> list){
        List<Integer> temp = new ArrayList<>();
        for (Integer item : list) {
            Integer t = new Integer(item);
            temp.add(t);
        }
        return temp;
    }

    private void sort(List list,int type){
        String name = "冒泡排序";
        switch(type) {
            case MySort.SORT_BUBBLE :
                break;
            case MySort.SORT_SIMPLE :
                name = "简单排序";
                break;
            case MySort.SORT_INSERT :
                name = "插入排序";
                break;
            case MySort.SORT_QUICK :
                name = "快速排序";
                break;

            default :
                break;
        }
        List<Integer> tempList = copy(list);
//        System.out.println(name + "排序前："+tempList);
        MySort.sort(tempList,(a,b) -> (Integer)a - (Integer) b,MySort.SORT_BUBBLE);
//        System.out.println(name + "排序后："+tempList);
    }

    @Test
    public void sort(){
        int n = 100000;
        StatisticalTime time = new StatisticalTime(1);


//        time.testFiveTimes(() -> getArray(n),"生成时间");
        List<Integer> list = getArray(n);

//        time.testFiveTimes(() -> sort(list,MySort.SORT_BUBBLE),"冒泡排序");
//
//        time.testFiveTimes(() -> sort(list,MySort.SORT_SIMPLE),"简单排序");
//
//        time.testFiveTimes(() -> sort(list,MySort.SORT_INSERT),"插入排序");
//
        time.testFiveTimes(new ManyThreadTest(() -> sort(list,MySort.SORT_QUICK)),"快速排序");
    }

    @Test
    public void test(){
        List list = Arrays.asList(new Object[]{1,2,3});
        List temp = list.subList(0,list.size());
        if(list == temp){
            System.out.println(true);
        }
    }



}
