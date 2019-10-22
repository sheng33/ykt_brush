package Until;

import com.github.kevinsawicki.http.HttpRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Map;

public class Post {
    public static JSONArray send_Body(Map<String,String>headers, Map<String, String> data, String url) throws ParseException, IOException {
        headers.put("Accept","application/json, text/javascript, */*; q=0.01");
        headers.put("Connection","keep-alive");
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Sec-Fetch-Mode","cors");
        headers.put("X-Requested-With","XMLHttpRequest");
        headers.put("Sec-Fetch-Site","same-origin");
        HttpRequest reques = HttpRequest.post(url)
                .headers(headers)
                .form(data);
        String str = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, "utf-8");
        reques.receive(ps);
        str = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        JSONArray jsonArray = JSONArray.fromObject(str);
        System.out.println("send_Body:"+jsonArray.toString());
        return jsonArray;
    }
    public static JSONObject send_Body_Object(Map<String,String>headers, Map<String, String> data, String url) throws ParseException, IOException {
        headers.put("Accept","application/json, text/javascript, */*; q=0.01");
        headers.put("Connection","keep-alive");
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Sec-Fetch-Mode","cors");
        headers.put("X-Requested-With","XMLHttpRequest");
        headers.put("Sec-Fetch-Site","same-origin");
        HttpRequest reques = HttpRequest.post(url)
                .headers(headers)
                .form(data);
        String str = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, "utf-8");
        reques.receive(ps);
        str = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        JSONObject jsonArray = JSONObject.fromObject(str);
        return jsonArray;
    }

    public static JSONObject send_Object(Map<String,String>headers, String url) throws ParseException, IOException {
        headers.put("Accept","application/json, text/javascript, */*; q=0.01");
        headers.put("Connection","keep-alive");
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Sec-Fetch-Mode","cors");
        headers.put("X-Requested-With","XMLHttpRequest");
        headers.put("Sec-Fetch-Site","same-origin");
        HttpRequest reques = HttpRequest.post(url)
                .headers(headers);
        String str = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, "utf-8");
        reques.receive(ps);
        str = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        JSONObject jsonArray = JSONObject.fromObject(str);
        System.out.println("send_Object："+jsonArray.toString());
        return jsonArray;
    }
    public static JSONArray send(Map<String,String>headers, String url) throws ParseException, IOException {
        headers.put("Accept","application/json, text/javascript, */*; q=0.01");
        headers.put("Connection","keep-alive");
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Sec-Fetch-Mode","cors");
        headers.put("X-Requested-With","XMLHttpRequest");
        headers.put("Sec-Fetch-Site","same-origin");
        HttpRequest reques = HttpRequest.post(url)
                .headers(headers);
        String str = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, "utf-8");
        reques.receive(ps);
        str = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        JSONArray jsonArray = JSONArray.fromObject(str);
        return jsonArray;
    }
}
