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
                    ,object.getString("openClassId")));
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
        for(ProcessList processList:processLists){
            System.out.println(processList);
        }
        return processLists;
    }
    public static List<child_DIrectory> child_dIrectoryList(String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
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
        JSONObject jsonObject = JSONObject.fromObject(str);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("cellList"));
        List<CellByTopicId> cellByTopicIdList = new ArrayList<>();
        for(Object o:jsonArray){
            JSONObject jsonObject1 = JSONObject.fromObject(o);
            JSONArray jsonArray1 = JSONArray.fromObject(jsonObject1.get("childNodeList"));
            List<CBT_childNodeList> childNodeLists = new ArrayList<>();
            for(Object o1:jsonArray1){
                JSONObject jsonObject2 = JSONObject.fromObject(o1);
                childNodeLists.add(new CBT_childNodeList(jsonObject2.getString("Id"),jsonObject2.getString("categoryName")
                        ,jsonObject2.getString("cellContent"),jsonObject2.getString("cellName"),jsonObject2.getString("courseOpenId")
                        ,jsonObject2.getString("parentId"),jsonObject2.getString("resourceUrl"),jsonObject2.getInt("stuCellFourCount")
                        ,jsonObject2.getInt("stuCellFourPercent")));
                System.out.println("ccc"+childNodeLists);
            }
            cellByTopicIdList.add(new CellByTopicId(jsonObject1.getString("Id"),jsonObject1.getString("categoryName")
                    ,jsonObject1.getString("cellContent"),jsonObject1.getString("cellName"),childNodeLists,jsonObject1.getString("courseOpenId")
                    ,jsonObject1.getString("parentId"),jsonObject1.getInt("sortOrder"),jsonObject1.getString("topicId")));

        }



        return cellByTopicIdList;
    }
}
