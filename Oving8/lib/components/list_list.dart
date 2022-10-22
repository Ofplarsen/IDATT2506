
import 'dart:core';

import 'package:flutter/material.dart';
import 'package:oving8/models/task.dart';
import 'package:oving8/models/task_list.dart';

class ListTaskList extends StatefulWidget {

  ListTaskList({Key? key, required this.taskList, required this.currentList, required this.notifyParent}) : super(key: key);
  final Function(TaskList) notifyParent;

  final List<TaskList> taskList;
  TaskList currentList;

  @override
  State<ListTaskList> createState() => _ListTaskListState();
}

class _ListTaskListState extends State<ListTaskList> {



  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      // Let the ListView know how many items it needs to build.
      itemCount: widget.taskList.length,
      // Provide a builder function. This is where the magic happens.
      // Convert each item into a widget based on the type of item it is.
      itemBuilder: (context, index) {
        final item = widget.taskList[index];
        return Card(
            child: InkWell(
              onTap: () {
                setState(() {
                  widget.currentList = item;
                });
                widget.notifyParent(item);
                Navigator.pop(context, widget.currentList);
              },
              child: ListTile(
                leading: Icon(Icons.list),
                title: Text(item.name),
                trailing: IconButton(
                  icon: const Icon(Icons.delete),
                   onPressed: () => showDialog<String>(
                    context: context,
                    builder: (BuildContext context) => AlertDialog(
                      title: Text('Warning!'),
                      content: Text('Are you sure you want to delete: \n${item.name}'),
                      actions: <Widget>[
                        TextButton(
                          onPressed: (){
                            Navigator.pop(context);
                           },
                          child: const Text('Cancel'),
                        ),
                        TextButton(
                          onPressed: (){
                            if(widget.currentList == widget.taskList[index]) {
                              widget.notifyParent(TaskList("", []));
                            }
                            setState(() {
                              widget.taskList.removeAt(index);
                            });

                            Navigator.pop(context);
                          },
                          child: const Text('Delete'),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ),
        );
      },
    );
  }
}
