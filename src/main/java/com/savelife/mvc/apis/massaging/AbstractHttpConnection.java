package com.savelife.mvc.apis.massaging;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by anton on 02.08.16.
 * abstract http interface com.savelife.mvc.service.massaging.connection to send get and post requests.
 */
public interface AbstractHttpConnection<T extends String> {

    /*
    * send get by url and receiving the response and return it
    * */
    default String getByURL(final T urlAddress){
        URL url;
        HttpURLConnection connection;
        BufferedReader bufferedReader;

        String line;

        StringBuffer buffer = new StringBuffer();

        try {
            url = new URL(urlAddress);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = bufferedReader.readLine()) != null){
                buffer.append(line);
            }
            /* close input*/
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println("Incorrect url");
            buffer.append(e);
            e.printStackTrace();
        }

        return new String(buffer);
    }

    /*
    * send post by url with the content type, authorization key and json body
    * */
    default String postByURLJsonBody(final T url, final T key, final T jsonBody) throws UnsupportedEncodingException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        StringEntity body = new StringEntity(jsonBody);
        CloseableHttpResponse closeableHttpResponse;

        String response = null;

        try {
            post.addHeader(new BasicHeader("Content-Type","application/json"));
            post.addHeader(new BasicHeader("Authorization",key));
            post.setEntity(body);

            closeableHttpResponse = httpClient.execute(post);
            response = String.valueOf(closeableHttpResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
