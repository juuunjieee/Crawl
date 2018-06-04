package com.etoak.crawl.util;

import com.etoak.crawl.page.Page;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*  ������Ҫ�� ������Щ�Ѿ����ʹ����ļ�*/
public class FileTool {

    private static String dirPath;


    /**
     * getMethod.getResponseHeader("Content-Type").getValue()
     * ���� URL ����ҳ����������Ҫ�������ҳ���ļ�����ȥ�� URL �еķ��ļ����ַ�
     */
    private static String getFileNameByUrl(String url, String contentType) {
        //ȥ�� http://
        url = url.substring(7);
        //text/html ����
        if (contentType.indexOf("html") != -1) {
            url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
            return url;
        }
        //�� application/pdf ����
        else {
            return url.replaceAll("[\\?/:*|<>\"]", "_") + "." +
                    contentType.substring(contentType.lastIndexOf("/") + 1);
        }
    }

    /*
    *  ����Ŀ¼
    * */
    private static void mkdir() {
        if (dirPath == null) {
            dirPath = Class.class.getClass().getResource("/").getPath() + "temp\\";
        }
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
    }

    /**
     * ������ҳ�ֽ����鵽�����ļ���filePath ΪҪ������ļ�����Ե�ַ
     */

    public static void saveToLocal(Page page) {
        mkdir();
        String fileName =  getFileNameByUrl(page.getUrl(), page.getContentType()) ;
        String filePath = dirPath + fileName ;
        byte[] data = page.getContent();
        try {
            //Files.lines(Paths.get("D:\\jd.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
            for (int i = 0; i < data.length; i++) {
                out.write(data[i]);
            }
            out.flush();
            out.close();
            System.out.println("�ļ���"+ fileName + "�Ѿ����洢��"+ filePath  );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
