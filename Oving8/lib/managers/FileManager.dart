import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:oving8/models/Task.dart';
import 'package:oving8/models/TaskList.dart';
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
    String json  = JsonEncoder(tasks) as String;
    return file.writeAsString(json);
  }
}