import 'dart:async';
import 'dart:convert';
import 'dart:io';
import 'package:json_annotation/json_annotation.dart';

import 'package:flutter/material.dart';
import 'package:oving8/models/task.dart';
import 'package:oving8/models/task_list.dart';

import 'package:path_provider/path_provider.dart';


class FileManager{

  Future<String> get _localPath async{
    var dir = await getApplicationDocumentsDirectory();
    return dir.path;
  }

  Future<File> _localFile(String list_name) async {
    final path = await _localPath;
    return File('$path/$list_name.txt');
  }

  Future<bool> deleteFile(String list_name) async {
    final file = await _localFile(list_name);
    try{
      await file.delete();
      return true;
    }catch(e){
      return false;
    }
  }

  Future <List<TaskList>?> getTaskLists() async{
    var dir = Directory(await _localPath);

    await for (var entity in
    dir.list(recursive: true, followLinks: false)) {
      print(entity.path);
    }
    return null;
  }

  Future<File> writeTaskList(TaskList tasks) async {
    final file = await _localFile(tasks.fileName);

    // Write the file
    String json  = jsonEncode(tasks);
    return file.writeAsString(json);
  }

  Future<String> readTaskList(String list_name) async {
    final file = await _localFile(list_name);
    String json = await file.readAsString();
    Map<String, dynamic> userMap = jsonDecode(json);
    var tasklist = TaskList.fromJson(userMap);
    return tasklist.toString();
  }
}