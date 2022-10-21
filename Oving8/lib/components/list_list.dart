
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


      /*
      children: const <Widget>[
        Card(child: ListTile(title: Text('One-line ListTile'))),
        Card(
          child: ListTile(
            leading: FlutterLogo(),
            title: Text('One-line with leading widget'),
          ),
        ),
        Card(
          child: ListTile(
            title: Text('One-line with trailing widget'),
            trailing: Icon(Icons.more_vert),
          ),
        ),
        Card(
          child: ListTile(
            leading: FlutterLogo(),
            title: Text('One-line with both widgets'),
            trailing: Icon(Icons.more_vert),
          ),
        ),
        Card(
          child: ListTile(
            title: Text('One-line dense ListTile'),
            dense: true,
          ),
        ),
        Card(
          child: ListTile(
            leading: FlutterLogo(size: 56.0),
            title: Text('Two-line ListTile'),
            subtitle: Text('Here is a second line'),
            trailing: Icon(Icons.more_vert),
          ),
        ),
        Card(
          child: ListTile(
            leading: FlutterLogo(size: 72.0),
            title: Text('Three-line ListTile'),
            subtitle: Text(
                'A sufficiently long subtitle warrants three lines.'
            ),
            trailing: Icon(Icons.more_vert),
            isThreeLine: true,
          ),
        ),

      ],
       */
    );
  }
}
