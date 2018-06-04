package com.etoak.crawl.main;

import com.etoak.crawl.link.LinkFilter;
import com.etoak.crawl.link.Links;
import com.etoak.crawl.page.Page;
import com.etoak.crawl.page.PageParserTool;
import com.etoak.crawl.page.RequestAndResponseTool;
import com.etoak.crawl.util.FileTool;
import org.jsoup.select.Elements;
 
import java.util.Set;
 
public class MyCrawler {
 
    /**
     * ʹ�����ӳ�ʼ�� URL ����
     *
     * @param seeds ���� URL
     * @return
     */
    private void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++){
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }
 
 
    /**
     * ץȡ����
     *
     * @param seeds
     * @return
     */
    public void crawling(String[] seeds) {
 
        //��ʼ�� URL ����
        initCrawlerWithSeeds(seeds);
 
        //�������������ȡ�� http://www.baidu.com ��ͷ������
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if (url.startsWith("http://www.baidu.com"))
                    return true;
                else
                    return false;
            }
        };
 
        //ѭ����������ץȡ�����Ӳ�����ץȡ����ҳ������ 1000
        while (!Links.unVisitedUrlQueueIsEmpty()  && Links.getVisitedUrlNum() <= 1000) {
 
            //�ȴӴ����ʵ�������ȡ����һ����
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null){
                continue;
            }
 
            //����URL�õ�page;
            Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);
 
            //��page���д��� ����DOM��ĳ����ǩ
            Elements es = PageParserTool.select(page,"a");
            if(!es.isEmpty()){
                System.out.println("���潫��ӡ����a��ǩ�� ");
                System.out.println(es);
            }
 
            //�������ļ�
            FileTool.saveToLocal(page);
 
            //���Ѿ����ʹ������ӷ����ѷ��ʵ������У�
            Links.addVisitedUrlSet(visitUrl);
 
            //�õ�������
            Set<String> links = PageParserTool.getLinks(page,"img");
            for (String link : links) {
                Links.addUnvisitedUrlQueue(link);
                System.out.println("������ȡ·��: " + link);
            }
        }
    }
 
 
    //main �������
    public static void main(String[] args) {
        MyCrawler crawler = new MyCrawler();
        crawler.crawling(new String[]{"http://www.baidu.com"});
    }
}
