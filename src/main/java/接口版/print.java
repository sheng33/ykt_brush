package 接口版;

import po.*;

import java.util.List;

public class print {
    public static void prints(List<Course> courseList){
        for(Course course:courseList){
            System.out.println("course："+course);
        }
        System.out.println("\n\n\n\n");
    }
    public static void prints(List<Course> courseList, List<ProcessList> processListList){
        for(Course course:courseList){
            System.out.println("course："+course);
        }
        System.out.println("\n\n\n\n");
        for(ProcessList processList:processListList){
            System.out.println("course："+processList);
        }
        System.out.println("\n\n\n\n");
    }
    public static void prints(List<Course> courseList, List<ProcessList> processListList, List<TopicByModuleId> topicByModuleIdList){
        for(Course course:courseList){
            System.out.println("course："+course);
        }
        System.out.println("\n\n\n\n");
        for(ProcessList processList:processListList){
            System.out.println("processList："+processList);
        }
        System.out.println("\n\n\n\n");
        for(TopicByModuleId topicByModuleId:topicByModuleIdList){
            System.out.println("topicByModuleId："+topicByModuleId);
        }
    }
    public static void prints(List<Course> courseList, List<ProcessList> processListList, List<TopicByModuleId> topicByModuleIdList, List<CellByTopicId>cellByTopicIdList){
        for(Course course:courseList){
            System.out.println("course："+course);
        }
        System.out.println("\n\n\n\n");
        for(ProcessList processList:processListList){
            System.out.println("processList："+processList);
        }
        System.out.println("\n\n\n\n");
        for(TopicByModuleId topicByModuleId:topicByModuleIdList){
            System.out.println("topicByModuleId："+topicByModuleId);
        }
        System.out.println("\n\n\n\n");
        for(CellByTopicId cellByTopicId:cellByTopicIdList){
            System.out.println("cellByTopicId："+cellByTopicId);
        }
    }
    public static void prints(List<Course> courseList, List<ProcessList> processListList, List<TopicByModuleId> topicByModuleIdList, List<CellByTopicId>cellByTopicIdList, List<child_DIrectory> child_dIrectoryList){
        for(Course course:courseList){
            System.out.println("course："+course);
        }
        System.out.println("\n\n\n\n");
        for(ProcessList processList:processListList){
            System.out.println("processList："+processList);
        }
        System.out.println("\n\n\n\n");
        for(TopicByModuleId topicByModuleId:topicByModuleIdList){
            System.out.println("topicByModuleId："+topicByModuleId);
        }
        System.out.println("\n\n\n\n");
        for(CellByTopicId cellByTopicId:cellByTopicIdList){
            System.out.println("cellByTopicId："+cellByTopicId);
        }
        System.out.println("\n\n\n\n");
        for(child_DIrectory child_dIrectory:child_dIrectoryList){
            System.out.println("child_dIrectory："+child_dIrectory);
        }
    }
}
