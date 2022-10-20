import 'package:oving8/models/task.dart';
import 'package:json_annotation/json_annotation.dart';
part 'task_list.g.dart';

@JsonSerializable()
class TaskList{
  String name;
  List<Task> tasks;

  TaskList(this.name, this.tasks);


  factory TaskList.fromJson(Map<String, dynamic> json) => _$TaskListFromJson(json);

  Map<String, dynamic> toJson() => _$TaskListToJson(this);

  @override
  String toString() {
    return 'TaskList{name: $name, tasks: $tasks}';
  }
}