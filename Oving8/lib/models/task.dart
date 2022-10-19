import 'package:json_annotation/json_annotation.dart';

part 'task.g.dart';


@JsonSerializable()
class Task{
  String taskName;
  bool done;

  Task(this.taskName, this.done);

  factory Task.fromJson(Map<String, dynamic> json) => _$TaskFromJson(json);

  Map<String, dynamic> toJson() => _$TaskFromJson(this);
}