package com.etoak.crawl.page;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

public class RequestAndResponseTool {


    public static Page  sendRequstAndGetResponse(String url) {
        Page page = null;
        // 1.���� HttpClinet �������ò���
        HttpClient httpClient = new HttpClient();
        // ���� HTTP ���ӳ�ʱ 5s
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        // 2.���� GetMethod �������ò���
        GetMethod getMethod = new GetMethod(url);
        // ���� get ����ʱ 5s
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        // �����������Դ���
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 3.ִ�� HTTP GET ����
        try {
            int statusCode = httpClient.executeMethod(getMethod);
        // �жϷ��ʵ�״̬��
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + getMethod.getStatusLine());
            }
        // 4.���� HTTP ��Ӧ����
            byte[] responseBody = getMethod.getResponseBody();// ��ȡΪ�ֽ� ����
            String contentType = getMethod.getResponseHeader("Content-Type").getValue(); // �õ���ǰ��������
            page = new Page(responseBody,url,contentType); //��װ��Ϊҳ��
        } catch (HttpException e) {
        // �����������쳣��������Э�鲻�Ի��߷��ص�����������
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
        // ���������쳣
            e.printStackTrace();
        } finally {
        // �ͷ�����
            getMethod.releaseConnection();
        }
        return page;
    }
}
