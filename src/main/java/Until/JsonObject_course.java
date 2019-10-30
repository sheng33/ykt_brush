package Until;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import po.*;

import java.util.ArrayList;
import java.util.List;

public class JsonObject_course {
    public static List<Course> getObject_Course(String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("courseList"));
        List<Course> courseList = new ArrayList<>();
        for (Object o:jsonArray){
            JSONObject object = JSONObject.fromObject(o);
            courseList.add(new Course(object.getString("Id"),object.getString("courseOpenId"),object.getString("assistTeacherName") ,object.getString("courseCode"),object.getString("courseName")
                    ,object.getString("openClassId"),object.getInt("process")));
        }
        return courseList;
    }
    public static List<ProcessList> getObject_ProcessList(String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        List<ProcessList> processLists = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(JSONObject.fromObject(jsonObject.get("progress")).get("moduleList"));
        List<moduleList> moduleLists = new ArrayList<>();
        for (Object o:jsonArray)
        {
            JSONObject temp_Json = JSONObject.fromObject(o);
            moduleLists.add(new moduleList(temp_Json.getString("id"),temp_Json.getString("name")
                    ,temp_Json.getInt("sortOrder"),temp_Json.getInt("percent")));
        }

        processLists.add(new ProcessList(jsonObject.getString("courseOpenId"),jsonObject.getString("openClassId")
                ,jsonObject.getString("openCourseCellCount"),jsonObject.getInt("stuStudyCourseOpenCellCount"),moduleLists));

        return processLists;
    }
    public static List<child_DIrectory> child_dIrectoryList(String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
//        System.out.println("打印:"+jsonObject);
        List<child_DIrectory> child_dIrectories = new ArrayList<>();
        child_dIrectories.add(new child_DIrectory(jsonObject.getString("courseOpenId"),jsonObject.getDouble("audioVideoLong"),jsonObject.getString("courseName")
                ,jsonObject.getString("openClassId"),jsonObject.getString("moduleId"),jsonObject.getString("topicId")
                ,jsonObject.getString("cellId"),jsonObject.getString("cellName"),jsonObject.getString("categoryName")
                ,jsonObject.getInt("pageCount"),jsonObject.getString("cellLogId"),jsonObject.getInt("stuCellViewTime")
                ,jsonObject.getInt("stuCellPicCount"),jsonObject.getInt("stuStudyNewlyTime")
                ,jsonObject.getInt("stuStudyNewlyPicCount"),jsonObject.getInt("cellPercent")
                ,jsonObject.getString("guIdToken")));
        return child_dIrectories;
    }
    public static List<TopicByModuleId> getObject_TopicByModuleId(String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("topicList"));
        List<TopicByModuleId> topicByModuleIdList = new ArrayList<>();
        for(Object o:jsonArray){
            JSONObject jsonObject1 = JSONObject.fromObject(o);
            topicByModuleIdList.add(new TopicByModuleId(jsonObject1.getString("id"),jsonObject1.getString("name")
                    ,jsonObject1.getInt("sortOrder")));
        }
        return topicByModuleIdList;
    }
    public static List<CellByTopicId> getObject_CellByTopicId(String str){
//        getCellByTopicId：{"code":1,"courseOpenId":"mw5bamaqwz9joeadu54zqg","openClassId":"rtbdamaq87nmrhieqgenmq","cellList":[{"Id":"fk9camaq6ojbcvoh3umrsa","cellType":4,"isGJS":1,"parentId":"fk9camaq7y1pk7wfjevptw","courseOpenId":"mw5bamaqwz9joeadu54zqg","topicId":"fk9camaq7y1pk7wfjevptw","categoryName":"子节点","cellName":"【任务】 安装与配置DHCP服务器","resourceUrl":"","externalLinkUrl":"","cellContent":null,"sortOrder":1,"isAllowDownLoad":false,"childNodeList":[{"Id":"fk9camaqz5rc0z2aizhuq","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq6ojbcvoh3umrsa","categoryName":"ppt","cellName":"9.1.ppt","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@D887D27426E6FC3F77A5428C67DBB109.ppt","externalLinkUrl":"","cellContent":null,"upCellId":"0","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaq8ltpxxhmtpfv7g","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq6ojbcvoh3umrsa","categoryName":"ppt","cellName":"9.2.ppt","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@E7E1858BD871CC15995F425EDFCB7514.ppt","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqz5rc0z2aizhuq","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaqdp5gqzmplb60q","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq6ojbcvoh3umrsa","categoryName":"ppt","cellName":"9.3.ppt","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@C8060CDB1CEBBFA329BD3033673E7D96.ppt","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaq8ltpxxhmtpfv7g","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaqkq1ctyrpr8y0la","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq6ojbcvoh3umrsa","categoryName":"ppt","cellName":"9.4.ppt","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@E1E0DDA8DA526F9A2179598CC4530EAB.ppt","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqdp5gqzmplb60q","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaqmztox83rkmycva","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq6ojbcvoh3umrsa","categoryName":"视频","cellName":"微课9-1 安装与启动DHCP服务.mp4","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@469629ECAAD9E6690E46A57E8B4E29F5.mp4","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqkq1ctyrpr8y0la","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaq5jpozl7upxrna","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq6ojbcvoh3umrsa","categoryName":"视频","cellName":"微课9-2 配置DHCP服务器.mp4","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@5A5FD49CBB9EBF7FB78E93540FD7BBE5.mp4","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqmztox83rkmycva","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaqupfhmy0l9airbg","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq6ojbcvoh3umrsa","categoryName":"视频","cellName":"微课9-3 配置Windows的DHCP客户端.mp4","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@469071083E273A89F5ED90E8E21C531C.mp4","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaq5jpozl7upxrna","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaqg7pai3cn7zbq","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq6ojbcvoh3umrsa","categoryName":"视频","cellName":"微课9-4 配置Linux的DHCP客户端.mp4","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@ABD606B54F12135F64AFC9C68232FC9A.mp4","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqupfhmy0l9airbg","stuCellFourCount":1,"stuCellFourPercent":100}],"upCellId":"-1","stuCellCount":0,"stuCellPercent":0},{"Id":"fk9camaq7rfphpykv6zoxw","cellType":4,"isGJS":1,"parentId":"fk9camaq7y1pk7wfjevptw","courseOpenId":"mw5bamaqwz9joeadu54zqg","topicId":"fk9camaq7y1pk7wfjevptw","categoryName":"子节点","cellName":"子任务1  安装与启动DHCP服务","resourceUrl":"","externalLinkUrl":"","cellContent":null,"sortOrder":2,"isAllowDownLoad":false,"childNodeList":[{"Id":"fk9camaqr5batz7bzbmvw","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq7rfphpykv6zoxw","categoryName":"ppt","cellName":"9.1.ppt","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@D887D27426E6FC3F77A5428C67DBB109.ppt","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqg7pai3cn7zbq","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaqtprneg4ggr9ssq","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq7rfphpykv6zoxw","categoryName":"视频","cellName":"微课9-1 安装与启动DHCP服务.mp4","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@469629ECAAD9E6690E46A57E8B4E29F5.mp4","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqr5batz7bzbmvw","stuCellFourCount":1,"stuCellFourPercent":100}],"upCellId":"-1","stuCellCount":0,"stuCellPercent":0},{"Id":"fk9camaq56flwumbpjlbzq","cellType":4,"isGJS":1,"parentId":"fk9camaq7y1pk7wfjevptw","courseOpenId":"mw5bamaqwz9joeadu54zqg","topicId":"fk9camaq7y1pk7wfjevptw","categoryName":"子节点","cellName":"子任务2  配置DHCP服务器","resourceUrl":"","externalLinkUrl":"","cellContent":null,"sortOrder":3,"isAllowDownLoad":false,"childNodeList":[{"Id":"fk9camaq8rdng8kklojww","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq56flwumbpjlbzq","categoryName":"ppt","cellName":"9.2.ppt","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@E7E1858BD871CC15995F425EDFCB7514.ppt","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqtprneg4ggr9ssq","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaqxppojmb1ltcttw","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaq56flwumbpjlbzq","categoryName":"视频","cellName":"微课9-2 配置DHCP服务器.mp4","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@5A5FD49CBB9EBF7FB78E93540FD7BBE5.mp4","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaq8rdng8kklojww","stuCellFourCount":1,"stuCellFourPercent":100}],"upCellId":"-1","stuCellCount":0,"stuCellPercent":0},{"Id":"fk9camaqmlxnlb5ovmah4g","cellType":4,"isGJS":1,"parentId":"fk9camaq7y1pk7wfjevptw","courseOpenId":"mw5bamaqwz9joeadu54zqg","topicId":"fk9camaq7y1pk7wfjevptw","categoryName":"子节点","cellName":"子任务3  配置Windows的DHCP客户端","resourceUrl":"","externalLinkUrl":"","cellContent":null,"sortOrder":4,"isAllowDownLoad":false,"childNodeList":[{"Id":"fk9camaqr5vnhwaql214uq","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaqmlxnlb5ovmah4g","categoryName":"ppt","cellName":"9.3.ppt","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@C8060CDB1CEBBFA329BD3033673E7D96.ppt","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqxppojmb1ltcttw","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaqp5vl4xmqsyp0dg","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaqmlxnlb5ovmah4g","categoryName":"视频","cellName":"微课9-3 配置Windows的DHCP客户端.mp4","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@469071083E273A89F5ED90E8E21C531C.mp4","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqr5vnhwaql214uq","stuCellFourCount":1,"stuCellFourPercent":100}],"upCellId":"-1","stuCellCount":0,"stuCellPercent":0},{"Id":"fk9camaqlpvogja8fsvudg","cellType":4,"isGJS":1,"parentId":"fk9camaq7y1pk7wfjevptw","courseOpenId":"mw5bamaqwz9joeadu54zqg","topicId":"fk9camaq7y1pk7wfjevptw","categoryName":"子节点","cellName":"子任务4  配置Linux的DHCP客户端","resourceUrl":"","externalLinkUrl":"","cellContent":null,"sortOrder":5,"isAllowDownLoad":false,"childNodeList":[{"Id":"fk9camaqaa9o67nm3jarmg","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaqlpvogja8fsvudg","categoryName":"ppt","cellName":"9.4.ppt","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@E1E0DDA8DA526F9A2179598CC4530EAB.ppt","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqp5vl4xmqsyp0dg","stuCellFourCount":1,"stuCellFourPercent":100},{"Id":"fk9camaqc6lg7lhcjfh5q","cellType":1,"isGJS":1,"isAllowDownLoad":false,"parentId":"fk9camaqlpvogja8fsvudg","categoryName":"视频","cellName":"微课9-4 配置Linux的DHCP客户端.mp4","courseOpenId":"mw5bamaqwz9joeadu54zqg","resourceUrl":"doc/g@ABD606B54F12135F64AFC9C68232FC9A.mp4","externalLinkUrl":"","cellContent":null,"upCellId":"fk9camaqaa9o67nm3jarmg","stuCellFourCount":1,"stuCellFourPercent":100}],"upCellId":"-1","stuCellCount":0,"stuCellPercent":0}]}
//        getCellByTopicId：{"code":1,"courseOpenId":"1mupaesntknpjtqfkzcdsq","openClassId":"jnmmab6qwznhwppoefxkdq","cellList":[{"Id":"8zmuagepf7npbxztwvh8pa","cellType":1,"isGJS":0,"parentId":"ykfxajyotjdalvlnplvmnq","courseOpenId":"1mupaesntknpjtqfkzcdsq","topicId":"ykfxajyotjdalvlnplvmnq","categoryName":"视频","cellName":"职业、职业生涯与职业生涯规划","resourceUrl":"ssykt/g@E492538FF84C394CF5FEDA8905090BC2.mp4","externalLinkUrl":"","cellContent":null,"sortOrder":1,"isAllowDownLoad":false,"childNodeList":[],"upCellId":"0","stuCellCount":0,"stuCellPercent":0},{"Id":"nkvangpxyriq09fyw1vgq","cellType":1,"isGJS":0,"parentId":"ykfxajyotjdalvlnplvmnq","courseOpenId":"1mupaesntknpjtqfkzcdsq","topicId":"ykfxajyotjdalvlnplvmnq","categoryName":"ppt","cellName":"职业、职业生涯与职业生涯规划","resourceUrl":"ssykt/g@0F72453143907A65E9AEE8B216C642DF.ppt","externalLinkUrl":"","cellContent":null,"sortOrder":2,"isAllowDownLoad":false,"childNodeList":[],"upCellId":"8zmuagepf7npbxztwvh8pa","stuCellCount":0,"stuCellPercent":0},{"Id":"pzpoac6qqztbqasgx2hxa","cellType":1,"isGJS":0,"parentId":"ykfxajyotjdalvlnplvmnq","courseOpenId":"1mupaesntknpjtqfkzcdsq","topicId":"ykfxajyotjdalvlnplvmnq","categoryName":"视频","cellName":"青春绽放，梦想起飞","resourceUrl":"ssykt/g@29AE6523E01C5A1A6CB709A0EA777A5E.flv","externalLinkUrl":"","cellContent":null,"sortOrder":3,"isAllowDownLoad":false,"childNodeList":[],"upCellId":"nkvangpxyriq09fyw1vgq","stuCellCount":0,"stuCellPercent":0}]}
        JSONObject jsonObject = JSONObject.fromObject(str);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("cellList"));
        List<CellByTopicId> cellByTopicIdList = new ArrayList<>();
        JSONObject jsonObject1 = JSONObject.fromObject(jsonArray.get(0));
        if(jsonObject1.get("categoryName").equals("子节点")){
            for(Object o:jsonArray){
                jsonObject1 = JSONObject.fromObject(o);
                JSONArray jsonArray1 = JSONArray.fromObject(jsonObject1.get("childNodeList"));
                List<CBT_childNodeList> childNodeLists = new ArrayList<>();
                for(Object o1:jsonArray1){
                    JSONObject jsonObject2 = JSONObject.fromObject(o1);
                    childNodeLists.add(new CBT_childNodeList(jsonObject2.getString("Id"),jsonObject2.getString("categoryName")
                            ,jsonObject2.getString("cellContent"),jsonObject2.getString("cellName"),jsonObject2.getString("courseOpenId")
                            ,jsonObject2.getString("parentId"),jsonObject2.getString("resourceUrl"),jsonObject2.getInt("stuCellFourCount")
                            ,jsonObject2.getInt("stuCellFourPercent")));
                }
                cellByTopicIdList.add(new CellByTopicId(jsonObject1.getString("Id"),jsonObject1.getString("categoryName")
                        ,jsonObject1.getString("cellContent"),jsonObject1.getString("cellName"),childNodeLists,jsonObject1.getString("courseOpenId")
                        ,jsonObject1.getString("parentId"),jsonObject1.getInt("sortOrder"),jsonObject1.getString("topicId"),jsonObject1.getInt("stuCellPercent")));
            }
        }else{
            for (Object o:jsonArray){
                jsonObject1 = JSONObject.fromObject(o);
                cellByTopicIdList.add(new CellByTopicId(jsonObject1.getString("Id"),jsonObject1.getString("categoryName")
                        ,jsonObject1.getString("cellContent"),jsonObject1.getString("cellName"),jsonObject1.getString("courseOpenId")
                        ,jsonObject1.getString("parentId"),jsonObject1.getInt("sortOrder"),jsonObject1.getString("topicId"),jsonObject1.getInt("stuCellPercent")));
            }
        }




        return cellByTopicIdList;
    }
}
