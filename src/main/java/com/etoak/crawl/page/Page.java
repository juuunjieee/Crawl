package com.etoak.crawl.page;

import com.etoak.crawl.util.CharsetDetector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;

/*
* page
*   1: �����ȡ������Ӧ���������;
* */
public class Page {

    private byte[] content ;
    private String html ;  //��ҳԴ���ַ���
    private Document doc  ;//��ҳDom�ĵ�
    private String charset ;//�ַ�����
    private String url ;//url·��
    private String contentType ;// ��������


    public Page(byte[] content , String url , String contentType){
        this.content = content ;
        this.url = url ;
        this.contentType = contentType ;
    }

    public String getCharset() {
        return charset;
    }
    public String getUrl(){return url ;}
    public String getContentType(){ return contentType ;}
    public byte[] getContent(){ return content ;}

    /**
     * ������ҳ��Դ���ַ���
     *
     * @return ��ҳ��Դ���ַ���
     */
    public String getHtml() {
        if (html != null) {
            return html;
        }
        if (content == null) {
            return null;
        }
        if(charset==null){
            charset = CharsetDetector.guessEncoding(content); // �����������²� �ַ�����
        }
        try {
            this.html = new String(content, charset);
            return html;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /*
    *  �õ��ĵ�
    * */
    public Document getDoc(){
        if (doc != null) {
            return doc;
        }
        try {
            this.doc = Jsoup.parse(getHtml(), url);
            return doc;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
