import 'package:flutter/material.dart';
import 'package:oving8/models/task.dart';
import 'package:oving8/models/task_list.dart';


class ListTasks extends StatefulWidget {
  const ListTasks({Key? key}) : super(key: key);

  @override
  State<ListTasks> createState() => _ListTasksState();
}

class _ListTasksState extends State<ListTasks> {
  TaskList taskList = TaskList("New TaskList", [Task("Test1", true), Task("Test2", false), Task("Test3", false)]);

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      // Let the ListView know how many items it needs to build.
      itemCount: taskList.tasks.length,
      // Provide a builder function. This is where the magic happens.
      // Convert each item into a widget based on the type of item it is.
      itemBuilder: (context, index) {
        final item = taskList.tasks[index];

        return Card(
          child: InkWell(
            onTap: () {
              setState(() {
                item.done = !item.done;
              });
            },
            child: ListTile(
              leading: Icon(Icons.task),
              title: Text('$item'),
            ),
          ),
        );
      },
    );
  }
}
