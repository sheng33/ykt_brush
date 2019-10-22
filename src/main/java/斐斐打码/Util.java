package 斐斐打码;

import java.io.*;
import java.security.MessageDigest;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class Util {
    public static  class HttpResp{
        public int      ret_code;
        public double   cust_val;
        public String   err_msg;
        public String   req_id;
        public String   rsp_data;
        public String   pred_resl;
    }

    // 为避免引入复杂的json包，这里简单实现一个，只用来能解析从网络的回包中的指定字段的内容
    public static class JsonHelper{
        public String json;
        public int next_idx;
        public JsonHelper(String json){
            this.json       = json;
            this.next_idx   = 0;
        }
        public void Skip(){
            while( next_idx < json.length()){
                char c = json.charAt( next_idx);
                if( (c<=32) || (c=='\\')){
                    next_idx ++;
                }else
                    break;
            }
        }
        public String NextSToken( ) {
            //int start = next_idx;
            String ret = "";
            while(next_idx < json.length()){
                char c = json.charAt(next_idx);
                if( c== '\"')
                    break;
                if( (c=='\\') && (next_idx+1<json.length())){
                    if(json.charAt(next_idx+1) == '\\')
                    {
                        ret += '\\';
                        next_idx += 2;
                        continue;
                    }
                    if( json.charAt(next_idx+1) == '\"'){
                        ret += '\"';
                        next_idx += 2;
                        continue;
                    }
                }
                ret += c;
                next_idx ++;
            }
            return ret;
        }
        public String NextNToken(){
            String ret = "";
            while(next_idx < json.length()){
                char c = json.charAt(next_idx);
                if ( c == '\\'){
                    next_idx++;
                    continue;
                }
                if((c <'0' || c>'9')&& c != '.'){
                    // not number
                    break;
                }
                ret += c;
                next_idx ++;
            }
            return ret;
        }
        public void Key2Val(HttpResp rsp, String key, String val){
            if ( key.equals("RetCode")){
                rsp.ret_code    = Integer.parseInt( val);
            }else if( key.equals("ErrMsg")){
                rsp.err_msg     = val;
            }else if( key.equals("RequestId")){
                rsp.req_id      = val;
            }else if( key.equals("RspData")){
                rsp.rsp_data    = val;
            }else if( key.equals("result")){
                rsp.pred_resl   = val;
            }else if( key.equals( "cust_val")){
                rsp.cust_val    = Double.parseDouble(val);
            }
        }
        public void Parse(HttpResp rsp ){
            //rsp.ret_code    = -1;
            next_idx = 0;
            String key = "";
            String sval = "";
            for( next_idx = 0; next_idx < json.length(); ){
                Skip();
                char c = json.charAt(next_idx);
                switch( c){
                    case ':':
                    case ',':
                        break;
                    case '[':
                    case '{':
                    case '}':
                    case ']':
                        // not support here
                        break;
                    case '\"':
                        next_idx++;
                        sval = NextSToken();
                        Skip();
                        if (next_idx >= json.length())
                            break;
                        if( json.charAt(next_idx+1) == ':'){
                            key = sval;
                            next_idx ++;
                            continue;
                        }
                        // key to val
                        Key2Val(rsp, key, sval);
                        key     = "";
                        break;
                    case '+':
                    case '-':
                    case '.':
                    case '0': case '1': case '2':
                    case '3': case '4': case '5':
                    case '6': case '7': case '8':
                    case '9':
                        // is number
                        sval    = NextNToken();
                        // key to val
                        Key2Val(rsp, key, sval);
                        key     = "";
                        break;
                    case 'n':
                    case 'N':
                        sval    = json.substring(next_idx, 4).toLowerCase();
                        if( !sval.equals("null")){
                            //error
                            break;
                        }
                        sval = "";
                        next_idx += 4;
                        // key to val
                        Key2Val(rsp, key, sval);
                        key     = "";
                        break;
                    default:
                        break;
                }
                next_idx ++;
            }
        }
    }
    public static String ToHex( byte[] arr){
        StringBuffer md5str = new StringBuffer();
        int digital;
        for (int i = 0; i < arr.length; i++) {
            digital = arr[i];
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toLowerCase();
    }
    public static String CalcMd5( String src){
        String md5str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = src.getBytes();
            byte[] buff = md.digest(input);
            md5str = ToHex(buff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }
    public static String CalcBase64(byte[] data){
        String s = "";
        if (data != null) {
            s   = Base64.getEncoder().encodeToString( data);
        }
        return s;
    }
    public static String CalcSign(String id, String key, String tm){
        String chk1 = CalcMd5( tm + key);
        String sum  = CalcMd5( id + tm + chk1);
        //System.out.printf("userid: %s key: %s tm: %s chk1: %s chk2: %s\n", id, key, tm, chk1, sum);
        return sum;
    }
    public static HttpResp ParseHttpResp(String resl){
        HttpResp resp   = new HttpResp();
        resp.ret_code   = -1;
        JsonHelper json = new JsonHelper(resl);
        json.Parse(resp);
        if( !resp.rsp_data.isEmpty() ){
            JsonHelper rjson = new JsonHelper( resp.rsp_data);
            rjson.Parse(resp);
        }
        return resp;
    }
    public static byte[] ReadBinaryFile(String file_name) throws IOException{
        InputStream in = null;
        BufferedInputStream buffer = null;
        DataInputStream dataIn = null;
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        byte[] bArray = null;
        try{
            in = new FileInputStream(file_name);
            buffer = new BufferedInputStream(in);
            dataIn = new DataInputStream(buffer);
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            byte[] buf = new byte[1024];
            while (true) {
                int len = dataIn.read(buf);
                if (len < 0)
                    break;
                dos.write(buf, 0, len);
            }
            bArray = bos.toByteArray();
        }catch( Exception e){
            return bArray;
        }finally {
            if (in != null)
                in.close();
            if (dataIn != null)
                dataIn.close();
            if (buffer != null)
                buffer.close();
            if (bos != null)
                bos.close();
            if (dos != null)
                dos.close();
        }
        return bArray;
    }
    public static String MFPost(URL url,byte[] img_data,String stm,String pd_id,String sign,String app_id,String asign,String pred_type) throws Exception {
    	String boundary = "--" + Util.CalcMd5(stm);
        String boundarybytes_string = "--" + boundary + "\r\n";
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setConnectTimeout(30000);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(true);
        con.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + boundary);
        OutputStream out    = con.getOutputStream();
        String item_string  = boundarybytes_string + "Content-Disposition: form-data;name=\"";
        String param_string = item_string + "user_id\"\r\n\r\n" + pd_id + "\r\n"
        		+ item_string + "timestamp\"\r\n\r\n" + stm + "\r\n" 
        		+ item_string + "sign\"\r\n\r\n" + sign + "\r\n"
        		+ item_string + "predict_type\"\r\n\r\n" + pred_type + "\r\n"
        		+ item_string + "up_type\"\r\n\r\nmt\r\n";
        if(!app_id.isEmpty()){
            param_string  += item_string + "appid\"\r\n\r\n" + app_id + "\r\n"
        		+ item_string + "asign\"\r\n\r\n" + asign + "\r\n";
        }
        String file_strig = item_string + "img_data\";filename=\"image.jpg\"\r\nContent-Type: image/jpg\r\n\r\n";
        String end_string = "\r\n--" + boundary + "--\r\n";
        out.write(param_string.getBytes("UTF-8"));
        out.write(file_strig.getBytes("UTF-8"));
        out.write(img_data);
        out.write(end_string.getBytes("UTF-8"));
        // System.out.println(out.toString());
        out.flush();
        out.close();
 
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String temp;
        while((temp = br.readLine()) != null) {
        	buffer.append(temp);
        	buffer.append("\n");
        }
        return buffer.toString().trim();
	}
    public static byte[] GetUrlImage(String str_url) throws IOException {
    	URL url = new URL(str_url);
    	URLConnection con = url.openConnection();
		con.setConnectTimeout(30000);
		InputStream is = con.getInputStream();
		byte[] buff = new byte[128];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int rc = 0;
		while((rc = is.read(buff, 0, 128)) > 0 ) {
			baos.write(buff,0,rc);
		}
		byte[] img_data = baos.toByteArray();
		return img_data;
	}
    public static String HttpPost(String url, String params){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
