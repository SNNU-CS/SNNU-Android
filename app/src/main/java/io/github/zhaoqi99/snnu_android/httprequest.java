package io.github.zhaoqi99.snnu_android;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public  class httprequest {
    private static String session_id = null;

    public static String httpRequest(String requrl) {
        String result = null;// 请求返回的字符串
        try {
            URL url = new URL(requrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (session_id != null) con.setRequestProperty("Cookie", session_id);//设置sessionid
            InputStream is = con.getInputStream();
            String cookieval = con.getHeaderField("Set-Cookie");
            if (cookieval != null) {
                session_id = cookieval.substring(0, cookieval.indexOf(";"));//获取sessionid
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) > 0) {
            bos.write(buffer, 0, len);
            }
            bos.flush();
            is.close();
            result = bos.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
