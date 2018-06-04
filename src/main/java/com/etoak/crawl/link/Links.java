package com.etoak.crawl.link;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
 
/*
*  Link��Ҫ����;
*  1: �洢�Ѿ����ʹ���URL·�� �� �����ʵ�URL ·��;
*
*
* */
public class Links {
 
    //�ѷ��ʵ� url ����  �Ѿ����ʹ��� ��Ҫ���� �������ظ��� ʹ��set����֤���ظ�;
    private static Set visitedUrlSet = new HashSet();
 
    //�����ʵ� url ����  �����ʵ���Ҫ���� 1:�涨����˳��;2:��֤���ṩ�ظ��Ĵ����ʵ�ַ;
    private static LinkedList unVisitedUrlQueue = new LinkedList();
 
    //����Ѿ����ʵ� URL ��Ŀ
    public static int getVisitedUrlNum() {
        return visitedUrlSet.size();
    }
 
    //��ӵ����ʹ��� URL
    public static void addVisitedUrlSet(String url) {
        visitedUrlSet.add(url);
    }
 
    //�Ƴ����ʹ��� URL
    public static void removeVisitedUrlSet(String url) {
        visitedUrlSet.remove(url);
    }
 
 
 
    //��� �����ʵ� url ����
    public static LinkedList getUnVisitedUrlQueue() {
        return unVisitedUrlQueue;
    }
 
    // ��ӵ������ʵļ�����  ��֤ÿ�� URL ֻ������һ��
    public static void addUnvisitedUrlQueue(String url) {
        if (url != null && !url.trim().equals("")  && !visitedUrlSet.contains(url)  && !unVisitedUrlQueue.contains(url)){
            unVisitedUrlQueue.add(url);
        }
    }
 
    //ɾ�� �����ʵ�url
    public static Object removeHeadOfUnVisitedUrlQueue() {
        return unVisitedUrlQueue.removeFirst();
    }
 
    //�ж�δ���ʵ� URL �������Ƿ�Ϊ��
    public static boolean unVisitedUrlQueueIsEmpty() {
        return unVisitedUrlQueue.isEmpty();
    }
 
}
