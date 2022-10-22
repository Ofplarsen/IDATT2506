import 'package:flutter/material.dart';
import 'package:oving8/models/task.dart';
import 'package:oving8/models/task_list.dart';


class ListTasks extends StatefulWidget {
  const ListTasks({Key? key, required this.taskList, required this.notifyParent}) : super(key: key);

  final Function(TaskList) notifyParent;
  final TaskList taskList;

  @override
  State<ListTasks> createState() => _ListTasksState();
}

class _ListTasksState extends State<ListTasks> {


  @override
  Widget build(BuildContext context) {
    Color getColor(Set<MaterialState> states) {
      const Set<MaterialState> interactiveStates = <MaterialState>{
        MaterialState.pressed,
        MaterialState.hovered,
        MaterialState.focused,
      };
      if (states.any(interactiveStates.contains)) {
        return Colors.blue;
      }
      return Colors.red;
    }



    return ListView.builder(
      // Let the ListView know how many items it needs to build.
      itemCount: widget.taskList.tasks.length,
      // Provide a builder function. This is where the magic happens.
      // Convert each item into a widget based on the type of item it is.
      itemBuilder: (context, index) {
        final item = widget.taskList.tasks[index];
        final name = widget.taskList.tasks[index].taskName;
        return Card(
          child: InkWell(
            onTap: () {
              setState(() {
                item.done = !item.done;
              });
            },
            child: ListTile(
              leading: Icon(Icons.task),
              title: Text('$name'),
              trailing: Checkbox(
                checkColor: Colors.white,
                fillColor: MaterialStateProperty.resolveWith(getColor),
                value: item.done,
                onChanged: (bool? value) {
                  setState(() {
                    item.done = !item.done;
                  });
                },
              ),
            ),
          ),
        );
      },
    );
  }
}
