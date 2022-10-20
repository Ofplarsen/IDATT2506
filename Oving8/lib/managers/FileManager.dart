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

  Future<File> get _localFile async {
    final path = await _localPath;
    return File('$path/task_lists.txt');
  }

  Future<File> writeTaskList(TaskList tasks) async {
    final file = await _localFile;

    // Write the file
    String json  = jsonEncode(tasks);
    return file.writeAsString(json);
  }

  Future<String> readTaskList() async {
    final file = await _localFile;
    String json = await file.readAsString();
    Map<String, dynamic> userMap = jsonDecode(json);
    var tasklist = TaskList.fromJson(userMap);
    return tasklist.toString();
  }
}