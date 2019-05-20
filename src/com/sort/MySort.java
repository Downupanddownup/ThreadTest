package com.sort;

import java.util.Comparator;
import java.util.List;

public class MySort {

    public static final int SORT_BUBBLE = 0;
    public static final int SORT_SIMPLE = 1;
    public static final int SORT_INSERT = 2;
    public static final int SORT_QUICK = 3;

    public static <E> void sort(List<E> list, Comparator comparator){
        sort(list,comparator,SORT_QUICK);
    }

    public static <E> void sort(List<E> list,Comparator comparator,int type){
        if(list == null || comparator == null){
            return;
        }
        switch(type) {
            case SORT_BUBBLE :
                sortBubble(list,comparator);
                break;
            case SORT_SIMPLE :
                sortSimple(list,comparator);
                break;
            case SORT_INSERT :
                sortInsert(list,comparator);
                break;
            case SORT_QUICK :
                sortQuick(list,0,list.size(),comparator);
                break;

            default :
                break;
        }
    }

    private static <E> void sortQuick(List<E> list,int start,int end,Comparator comparator){
        if(end - start < 2){
            return;
        }
        int i = start;
        int j = end - 1;
        E sentry = list.get(start);
        while (i < j){
            for (; j > i; j--) {
                if(comparator.compare(list.get(j),sentry) < 0){
                    list.set(i,list.get(j));
                    break;
                }
            }
            for (; i < j; i++) {
                if(comparator.compare(list.get(i),sentry) > 0){
                    list.set(j,list.get(i));
                    break;
                }
            }
        }
        list.set(i,sentry);
        sortQuick(list,start,i,comparator);
        sortQuick(list,i+1,end,comparator);
    }

    private static <E> void sortInsert(List<E> list,Comparator comparator){
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if(comparator.compare(list.get(i),list.get(j)) < 0){
                    E temp = list.get(i);
                    for (int k = i; k > j; k--) {
                        list.set(k,list.get(k-1));
                    }
                    list.set(j,temp);
                }
            }
        }
    }

    private static <E> void sortSimple(List<E> list,Comparator comparator){
        for (int i = 0; i < list.size(); i++) {
            E min = list.get(i);
            int minIdx = i;
            for (int j = i+1; j < list.size(); j++) {
                if(comparator.compare(min,list.get(j)) > 0){
                    min = list.get(j);
                    minIdx = j;
                }
            }
            if(minIdx > i){
                list.set(minIdx,list.get(i));
                list.set(i,min);
            }
        }
    }

    private static <E> void sortBubble(List<E> list,Comparator comparator){
        for (int i = 0; i < list.size(); i++) {
            boolean move = false;
            for (int j = 0; j < list.size()-i-1; j++) {
                if (comparator.compare(list.get(j),list.get(j+1)) > 0) {
                    E temp = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
                    move = true;
                }
            }
            if (!move){
                return;
            }
        }
    }

}
