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
    static String cookieStr = "";
    static int xunhuan = 0;
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
        System.out.print("输入cookie:");
        Scanner sc = new Scanner(System.in);
        cookieStr = sc.nextLine();
        headers.put("Cookie",cookieStr);
        System.out.println("欢迎您："+getUserInfo());
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
            JSONObject course_Json = Post.send_Object(headers,resource.getString("getLearnningCourseList"));


            //解析Json数据创建得到课程数据
            try{
                if(course_Json.get("code").equals(1)){
                    courseList = JsonObject_course.getObject_Course(course_Json.toString());
                    int temp_count = 0;
                    int temp_number = 0;
                    System.out.println("课程列表：");
                    for(Course course:courseList){
                        String str = "Id:"+temp_count+"\t"+"课程名："+course.getCourseName().replace(" ","").replace("1","一");
                        temp_number = str.getBytes("GBK").length;
//                        System.out.println("length:"+temp_number);
//                        temp_number = str.getBytes("UTF-8").length;
//                        System.out.println("length:"+temp_number);
                        while ((50-temp_number++)>0){
                            str+=" ";
                        }
                        System.out.printf(str);
                        str = "任课老师:"+course.getAssisTeacherName();
                        temp_number = str.getBytes("GBK").length;
//                        System.out.println("length:"+temp_number);
//                        temp_number = str.getBytes("UTF-8").length;
//                        System.out.println("length:"+temp_number);
                        while ((25-temp_number++)>0){
                            str+=' ';
                        }
                        System.out.printf(str);
                        System.out.printf("完成度:%d\n",course.getProcess());
                        temp_count++;
                    }
                    System.out.println("\n强迫症的我居然对不齐。。。难受");
                    System.out.print("\n请选择课程名(输入Id):");
                    course = sc.nextInt();
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
                for(int topByMouduleCount =0;topByMouduleCount<topicByModuleIdList.size();topByMouduleCount++){
                    data.clear();
                    data.put("courseOpenId",courseList.get(course).getCourseOpenId());
                    data.put("openClassId",courseList.get(course).getOpenClassId());
                    data.put("topicId",topicByModuleIdList.get(topByMouduleCount).getId());
                    JSONObject jsonObject2 = getCellByTopicId(resource.getString("getCellByTopicId"),data);
                    cellByTopicIdList = JsonObject_course.getObject_CellByTopicId(jsonObject2.toString());
                    for(int cellCount=0;cellCount<cellByTopicIdList.size();cellCount++){
//                    System.out.println("bbb:"+cellByTopicIdList.get(cellCount).getChildNodeLists());
                        if(cellByTopicIdList.get(cellCount).getChildNodeLists()==null){
                            for(int childCount=0;childCount<cellByTopicIdList.size();childCount++){
                                if(cellByTopicIdList.get(cellCount).getStuCellPercent() == 100||cellByTopicIdList.get(cellCount).getCategoryName().equals("文档")
                                ||cellByTopicIdList.get(cellCount).getCategoryName().equals("链接")){
//                            System.out.println("完成:"+cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getCellName());
                                    continue;
                                }

                                boolean pd = true;
                                while(pd) {
                                    pd = funtion1(processCount, cellCount, childCount);
                                }
                            }
                        }else{
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
                }



            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            driver.close();
        }
    }
    public static String getUserInfo() throws IOException, ParseException {
        JSONObject jsonObject = Post.send_Body_Object(headers,data,resource.getString("getUserInfo"));
        return jsonObject.get("disPlayName").toString();
    };
    public static boolean funtion1(int processCount,int cellCount,int childCount) throws IOException, ParseException, InterruptedException {
        data.clear();
        data.put("courseOpenId",courseList.get(course).getCourseOpenId());
        data.put("openClassId",courseList.get(course).getOpenClassId());
        data.put("cellId",cellByTopicIdList.get(cellCount).getId());
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
        System.out.println("当前执行:"+cellByTopicIdList.get(cellCount).getCellName());

        if(cellByTopicIdList.get(cellCount).getCategoryName().equals("视频")){
            return brush_video(processCount,cellCount,childCount);
        }else if(cellByTopicIdList.get(cellCount).getCategoryName().equals("office文档")||
                cellByTopicIdList.get(cellCount).getCategoryName().equals("链接")||
                cellByTopicIdList.get(cellCount).getCategoryName().equals("文档")){
            return false;
        }else {
            System.out.println("文件类型"+cellByTopicIdList.get(cellCount).getCategoryName());
            return  brush_ppt(processCount,cellCount,childCount);
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
        System.out.println("当前执行:"+cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getCellName());
        if(cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getCategoryName().equals("视频")){
            return brush_video(processCount,cellCount,childCount);
        }else if(cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).equals("office文档")||
                cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).equals("链接")||
                cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).equals("文档")){
            return false;
        }else {
            return  brush_ppt(processCount,cellCount,childCount);
        }
    }
    public static int getPercent(int processCount,int cellCount,int childCount) throws IOException, ParseException {
        data.clear();
        data.put("courseOpenId",courseList.get(course).getCourseOpenId());
        data.put("openClassId",courseList.get(course).getOpenClassId());
        if(cellByTopicIdList.get(cellCount).getCategoryName().equals("子节点")){
            data.put("cellId",cellByTopicIdList.get(cellCount).getChildNodeLists().get(childCount).getId());
        }else {
            data.put("cellId",cellByTopicIdList.get(cellCount).getId());
        }
        data.put("flag","s");
        data.put("moduleId",processLists.get(0).getModuleLists().get(processCount).getId());
//                      for(String key:data.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
//                         String value = data.get(key).toString();//
//                         System.out.println(key+":"+value);
//                      }
        JSONObject jsonObject3 = viewDirectory(resource.getString("viewDirectory"),data);
        return jsonObject3.getInt("cellPercent");
    }
    public static Boolean brush_ppt(int processCount,int cellCount,int childCount) throws IOException, ParseException {
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
                    System.out.println("正在执行...当前完成度:"+getPercent(processCount, cellCount, childCount));
                    xunhuan++;
                    if(xunhuan>=20){
                        return false;
                    }
                    continue;
                }else {
                    if(i==j&&i==child_dIrectoryList.get(0).getPageCount()){
                        System.out.println("已完成:"+child_dIrectoryList.get(0).getCellName()+"  完成度:"+getPercent(processCount, cellCount, childCount));
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
        double number = (int)(1+Math.random()*(10-1+1))+0.554707;
        while(number<child_dIrectoryList.get(0).getAudioVideoLong()){
            Thread.sleep(500);
            number+= 10+Double.valueOf(String.valueOf(new Random().nextDouble()).substring(0,8));
            data.put("studyNewlyTime",String.valueOf(number));
            jsonObject4 = stuProcessCellLog(resource.getString("stuProcessCellLog"),data);
            if (number > child_dIrectoryList.get(0).getAudioVideoLong()){
                number = child_dIrectoryList.get(0).getAudioVideoLong();
            }
//            System.out.println(jsonObject4.toString()+number);
            if((jsonObject4.getString("code").equals("-1"))&&number >= child_dIrectoryList.get(0).getAudioVideoLong()){
                System.out.println("正在执行...当前完成度:"+getPercent(processCount, cellCount, childCount));
                xunhuan++;
                if(xunhuan>=20){
                    number = (int)(1+Math.random()*(20-1+1))+0.666407;
                    if (xunhuan >=30){
                        return false;
                    }
                }
                if(getPercent(processCount, cellCount, childCount) == 100){
                    System.out.println("已完成度:"+100);
                    return false;
                }
                return true;
            }else {
                if(number >= child_dIrectoryList.get(0).getAudioVideoLong()){
                    if(getPercent(processCount, cellCount, childCount) == 100){
                        System.out.println("已完成度:"+100);
                        return false;
                    }

                }
            }
        }
        return true;
    }
    public static JSONObject getProcessList(String url,Map<String,String> data) throws IOException, ParseException {
        JSONObject jsonObject = Post.send_Body_Object(headers,data,url);
//        System.out.println("getProcessList："+jsonObject.toString());
        return jsonObject;
    }
    public static JSONObject getTopicByModuled(String url,Map<String,String> data) throws IOException, ParseException {
        JSONObject jsonObject = Post.send_Body_Object(headers,data,url);
//        System.out.println("getTopicByModuled："+jsonObject.toString());
        return jsonObject;
    }
    public static JSONObject getCellByTopicId(String url,Map<String,String> data) throws IOException, ParseException {
        JSONObject jsonObject = Post.send_Body_Object(headers,data,url);
//        System.out.println("getCellByTopicId："+jsonObject.toString());
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
