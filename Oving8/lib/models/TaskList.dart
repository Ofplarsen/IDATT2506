import 'package:oving8/models/Task.dart';
import 'package:json_annotation/json_annotation.dart';


@JsonSerializable()
class TaskList{
  String _name;
  List<Task> _tasks;

  TaskList(this._name, this._tasks);

  List<Task> get tasks => _tasks;

  String get name => _name;

  factory TaskList.fromJson(Map<String, dynamic> json) => _$TaskListFromJson(json);

  Map<String, dynamic> toJson() => _$TaskListFromJson(this);
}