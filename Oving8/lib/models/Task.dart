import 'package:json_annotation/json_annotation.dart';



@JsonSerializable()
class Task{
  String _taskName;
  bool _done;

  Task(this._taskName, this._done);

  factory Task.fromJson(Map<String, dynamic> json) => _$TaskFromJson(json);

  Map<String, dynamic> toJson() => _$TaskFromJson(this);
}