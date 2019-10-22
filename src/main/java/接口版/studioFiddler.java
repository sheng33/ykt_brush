package 接口版;

import Until.ImgCode;
import Until.JsonObject_course;
import Until.Post;
import net.sf.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import po.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;



public class studioFiddler {
    //导入资源文件
    static ResourceBundle resource = ResourceBundle.getBundle("ZhiJiaoYunApi");
    static Map<String,String> headers = new HashMap<>();
    static Map<String,String> data = new LinkedHashMap<>();
    static List<Course> courseList = new ArrayList<>();
    static List<ProcessList> processLists = new ArrayList<>();
    static List<TopicByModuleId> topicByModuleIdList = new ArrayList<>();
    static List<CellByTopicId> cellByTopicIdList = new ArrayList<>();
    static List<child_DIrectory>  child_dIrectoryList = new ArrayList<>();
    //课程ID，修改此项，可选择需要刷的课程
    static int course = 0;
    /**
     *  登录方法
     * @param driver
     * @return cookieStr
     * @throws Exception
     */
    public static String login(WebDriver driver) throws Exception {
        String cookieStr = "";
        driver.get("https://zjy2.icve.com.cn/portal/login.html");
        WebElement userName = driver.findElement(By.xpath(".//*[@name='userName']"));
        userName.sendKeys("173053432");
        WebElement userPassword = driver.findElement(By.name("userPassword"));
        userPassword.sendKeys("123456ABCDa");
        //获得验证码
        String Code = ImgCode.getCode(driver);
//        System.out.println(Code);
        WebElement webCode = driver.findElement(By.name("photoCode"));
        webCode.sendKeys(Code);
        driver.findElement(By.id("btnLogin")).click();
        Thread.sleep(6000);
        Set<Cookie> cookie = driver.manage().getCookies();
        for(Cookie value:cookie){
            if(value.toJson().containsValue("auth")){
                cookieStr +="auth="+value.toJson().get("value")+";";
            }
            if(value.toJson().containsValue("Hm_lvt_a3821194625da04fcd588112be734f5b")){
                cookieStr +="Hm_lvt_a3821194625da04fcd588112be734f5b="+value.toJson().get("value")+";";
            }
            if(value.toJson().containsValue("Hm_lpvt_a3821194625da04fcd588112be734f5b")){
                cookieStr +="Hm_lpvt_a3821194625da04fcd588112be734f5b="+value.toJson().get("value")+";";
            }
            if(value.toJson().containsValue("acw_tc")){
                cookieStr +="acw_tc="+value.toJson().get("value")+";";
            }
            if(value.toJson().containsValue("verifycode")){
                cookieStr +="verifycode="+value.toJson().get("value")+";";
            }
            if(value.toJson().containsValue("_bl_uid")){
                cookieStr +="_bl_uid="+value.toJson().get("value")+";";
            }
        }
        return cookieStr;
    }
    public static void main(String[] args) throws Exception {
        //请求头部信息
//        System.setProperty("webdriver.chrome.driver","D:\\xt\\chromedriver.exe");
//        //无头参数
//        ChromeOptions chromeOptions=new ChromeOptions();
//        chromeOptions.addArguments("-headless");
//        //初始化浏览器名为driver
//        WebDriver driver = new ChromeDriver(chromeOptions);
        try {
            //登录并获取cookie值
//          String cookieStr = login(driver);
//          headers.put("Cookie",cookieStr);
            headers.put("Cookie","acw_tc=76b20fe515691515575704722e536f3505c105fc7cb8f4fc1d73a658d8ac4c; _bl_uid=8ykys0aFuhqwUscw4h193954b6Ue; jwplayer.captionLabel=Off; verifycode=9585B8A0D20E4EDB415AE8410375A840@637073278508988919; Hm_lvt_a3821194625da04fcd588112be734f5b=1571632455,1571646484,1571668401,1571702251; Hm_lpvt_a3821194625da04fcd588112be734f5b=1571702251; auth=01020643D4768256D708FE06538048D656D70801167400730073006E006100610069006F00730035006E0063006B006D00330066006B006E00690073006C00770000012F00FFF941D53E3DB0E59C4B3092E4AA68D0004DD73603; token=idadao6qkiznszquiioiog;");
//            System.out.println("打开:"+resource.getString("getLearnningCourseList"));
            JSONObject course_Json = Post.send_Object(headers,resource.getString("getLearnningCourseList"));
            //解析Json数据创建得到课程数据
            try{
                if(course_Json.get("code").equals(1)){
                    courseList = JsonObject_course.getObject_Course(course_Json.toString());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            data.put("courseOpenId",courseList.get(course).getCourseOpenId());
            data.put("openClassId",courseList.get(course).getOpenClassId());
            JSONObject jsonObject = getProcessList(resource.getString("getProcessList"),data);
            processLists = JsonObject_course.getObject_ProcessList(jsonObject.toString());

            for (int processCount = 0;processCount<processLists.get(0).getModuleLists().size();processCount++){
                data.clear();
                data.put("courseOpenId",courseList.get(course).getCourseOpenId());
                data.put("moduleId",processLists.get(0).getModuleLists().get(processCount).getId());
                JSONObject jsonObject1 = getTopicByModuled(resource.getString("getTopicByModuleId"),data);
                topicByModuleIdList = JsonObject_course.getObject_TopicByModuleId(jsonObject1.toString());

                data.clear();
                data.put("courseOpenId",courseList.get(course).getCourseOpenId());
                data.put("openClassId",courseList.get(course).getOpenClassId());
                data.put("topicId",topicByModuleIdList.get(0).getId());
                JSONObject jsonObject2 = getCellByTopicId(resource.getString("getCellByTopicId"),data);
                cellByTopicIdList = JsonObject_course.getObject_CellByTopicId(jsonObject2.toString());

                for(int cellCount=0;cellCount<cellByTopicIdList.size();cellCount++){
                    System.out.println("aaa:"+cellByTopicIdList.size());
                    System.out.println("bbb:"+cellByTopicIdList.get(cellCount).getChildNodeLists());
                    for(int childCount=0;childCount<cellByTopicIdList.get(cellCount).getChildNodeLists().size();childCount++){

                        if(cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getStuCellFourPercent() == 100){
//                            System.out.println("完成:"+cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getCellName());
                            continue;
                        }
                        boolean pd = true;
                        while(pd) {
                            pd = funtion(processCount, cellCount, childCount);
                        }
                    }
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            driver.close();
        }
    }
    public static boolean funtion(int processCount,int cellCount,int childCount) throws IOException, ParseException, InterruptedException {
        data.clear();
        data.put("courseOpenId",courseList.get(course).getCourseOpenId());
        data.put("openClassId",courseList.get(course).getOpenClassId());
        data.put("cellId",cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getId());
        data.put("flag","s");
        data.put("moduleId",processLists.get(0).getModuleLists().get(processCount).getId());
//                      for(String key:data.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
//                         String value = data.get(key).toString();//
//                         System.out.println(key+":"+value);
//                      }
        JSONObject jsonObject3 = viewDirectory(resource.getString("viewDirectory"),data);
        child_dIrectoryList = JsonObject_course.child_dIrectoryList(jsonObject3.toString());
//                      prints(courseList,processLists,topicByModuleIdList,cellByTopicIdList,child_dIrectoryList);
        Thread.sleep(1500);
        data.clear();
        data.put("courseOpenId",courseList.get(course).getCourseOpenId());
        data.put("openClassId",courseList.get(course).getOpenClassId());
        data.put("cellId",child_dIrectoryList.get(0).getCellId());
        data.put("cellLogId",child_dIrectoryList.get(0).getCellLogId());
//                      System.out.println("child_dIrectoryList:"+child_dIrectoryList.get(0));
//                      System.out.println("打开文档:"+child_dIrectoryList.get(0).getCellName());
        System.out.println("当前执行:"+cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount));
        if(cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getCategoryName().equals("视频")){
            return brush_video(processCount,cellCount,childCount);
        }else {
            return  brush_ppt();
        }
    }

    public static Boolean brush_ppt() throws IOException, ParseException {
        int j;
        JSONObject jsonObject4 = null;
        for (int i=1;i<=child_dIrectoryList.get(0).getPageCount();i++){
            data.put("picNum",String.valueOf(i));
            data.put("studyNewlyTime","0");
            for (j=0;j<=i;j++){
                data.put("studyNewlyPicNum",String.valueOf(j));
                data.put("token",String.valueOf(child_dIrectoryList.get(0).getGuIdToken()));
//                        for(String key:data.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
//                            String value = data.get(key).toString();
//                            System.out.println(key+":"+value);
//                        }
                jsonObject4 = stuProcessCellLog(resource.getString("stuProcessCellLog"),data);
                if((jsonObject4.getString("code").equals("-1"))&&i==j&&i==child_dIrectoryList.get(0).getPageCount()){
                    System.out.println(jsonObject4.toString()+": old:"+i+"\tnow:"+j);
                    System.out.println("失败重新开始!");
                    continue;
                }else {
                    if(i==j&&i==child_dIrectoryList.get(0).getPageCount()){
                        System.out.println(jsonObject4.toString()+": old:"+i+"\tnow:"+j);
                        System.out.println("完成:"+child_dIrectoryList.get(0).getCellName());
                        return false;
                    }

                }
            }
        }
        return true;
    }
    public static Boolean brush_video(int processCount,int cellCount,int childCount) throws IOException, ParseException, InterruptedException {
        int j;
        JSONObject jsonObject4 = null;
        data.put("picNum","0");
        data.put("studyNewlyPicNum","0");
        data.put("token",String.valueOf(child_dIrectoryList.get(0).getGuIdToken()));
        double number = (int)(1+Math.random()*(10-1+1))+0.654707;
        while(number<child_dIrectoryList.get(0).getAudioVideoLong()){
            Thread.sleep(500);
            number+= 10;
            data.put("studyNewlyTime",String.valueOf(number));
            jsonObject4 = stuProcessCellLog(resource.getString("stuProcessCellLog"),data);
            if (number > child_dIrectoryList.get(0).getAudioVideoLong()){
                number = child_dIrectoryList.get(0).getAudioVideoLong();
            }
            if((jsonObject4.getString("code").equals("-1"))&&number >= child_dIrectoryList.get(0).getAudioVideoLong()){
                System.out.println(jsonObject4.toString()+": Time:"+number);
                data.clear();
                data.put("courseOpenId",courseList.get(course).getCourseOpenId());
                data.put("openClassId",courseList.get(course).getOpenClassId());
                data.put("cellId",cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getId());
                data.put("flag","s");
                data.put("moduleId",processLists.get(0).getModuleLists().get(processCount).getId());
//                      for(String key:data.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
//                         String value = data.get(key).toString();//
//                         System.out.println(key+":"+value);
//                      }
                JSONObject jsonObject3 = viewDirectory(resource.getString("viewDirectory"),data);
                System.out.println("当前完成度:"+jsonObject3.getInt("cellPercent"));
                System.out.println("失败重新开始!");
                System.out.println(number);
                return true;
            }else {
                if(number >= child_dIrectoryList.get(0).getAudioVideoLong()){
                    System.out.println(jsonObject4.toString()+": Time:"+number);
                    data.clear();
                    data.put("courseOpenId",courseList.get(course).getCourseOpenId());
                    data.put("openClassId",courseList.get(course).getOpenClassId());
                    data.put("cellId",cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getId());
                    data.put("flag","s");
                    data.put("moduleId",processLists.get(0).getModuleLists().get(processCount).getId());
//                      for(String key:data.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
//                         String value = data.get(key).toString();//
//                         System.out.println(key+":"+value);
//                      }
                    JSONObject jsonObject3 = viewDirectory(resource.getString("viewDirectory"),data);
                    if(jsonObject3.getInt("cellPercent")==100){
                        System.out.println("已完成度:"+jsonObject3.getInt("cellPercent"));
                        return false;
                    }

                }
            }
        }
        return true;
    }
    public static JSONObject getProcessList(String url,Map<String,String> data) throws IOException, ParseException {
        JSONObject jsonObject = Post.send_Body_Object(headers,data,url);
        System.out.println("getProcessList："+jsonObject.toString());
        return jsonObject;
    }
    public static JSONObject getTopicByModuled(String url,Map<String,String> data) throws IOException, ParseException {
        JSONObject jsonObject = Post.send_Body_Object(headers,data,url);
        System.out.println("getTopicByModuled："+jsonObject.toString());
        return jsonObject;
    }
    public static JSONObject getCellByTopicId(String url,Map<String,String> data) throws IOException, ParseException {
        JSONObject jsonObject = Post.send_Body_Object(headers,data,url);
        System.out.println("getCellByTopicId："+jsonObject.toString());
        return jsonObject;
    }
    public static JSONObject viewDirectory(String url,Map<String,String> data) throws IOException, ParseException {
        JSONObject jsonObject = Post.send_Body_Object(headers,data,url);
//        System.out.println("viewDirectory："+jsonObject.toString());
        return jsonObject;
    }
    public static JSONObject stuProcessCellLog(String url,Map<String,String> data) throws IOException, ParseException {
        JSONObject jsonObject = Post.send_Body_Object(headers,data,url);
//        System.out.println("stuProcessCellLog："+jsonObject.toString());
        return jsonObject;
    }
}
