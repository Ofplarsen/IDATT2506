
import 'dart:core';

import 'package:flutter/material.dart';
import 'package:oving8/models/task.dart';
import 'package:oving8/models/task_list.dart';

class ListTaskList extends StatefulWidget {

  const ListTaskList({Key? key}) : super(key: key);

  @override
  State<ListTaskList> createState() => _ListTaskListState();
}

class _ListTaskListState extends State<ListTaskList> {

  List<TaskList> taskList = [TaskList("New TaskList", [Task("Test1", true), Task("Test2", false), Task("Test3", false)]),
    TaskList("Todo", [Task("Test1", true), Task("Test2", false), Task("Test3", false)]),
    TaskList("Shopping List", [Task("Test1", true), Task("Test2", false), Task("Test3", false)])];

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      // Let the ListView know how many items it needs to build.
      itemCount: taskList.length,
      // Provide a builder function. This is where the magic happens.
      // Convert each item into a widget based on the type of item it is.
      itemBuilder: (context, index) {
        final item = taskList[index].name;

        return Card(
            child: InkWell(
              onTap: () {
                print("tapped");
                Navigator.pop(context);
              },
              child: ListTile(
                leading: Icon(Icons.list),
                title: Text('$item'),
              ),
            ),
        );
      },
    );
  }
}
