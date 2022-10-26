
import 'dart:io';
import 'dart:ui';
import 'package:oving8/components/list_list.dart';
import 'package:oving8/managers/FileManager.dart';
import 'package:oving8/models/task.dart';
import 'package:oving8/models/task_list.dart';
import 'package:shared_preferences/shared_preferences.dart';


import 'about.dart';
import 'package:flutter/material.dart';

import 'components/list_tasks.dart';
import 'create_list.dart';


void main() {
  runApp(const MaterialApp(
    title: "App",
    home: MyApp(),
  ));
}
class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  TaskList currentList = TaskList("", []);
  List<TaskList> taskLists = [];

  final FileManager _fileManager = FileManager();

  var focusNode = FocusNode();
  final _textController = TextEditingController();

  @override
  void initState(){
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_){
      _importLists();
    });
    WidgetsBinding.instance.addPostFrameCallback((_){
      _initLastList();
    });
  }

  _addList(TaskList list){
    if(list.name.isEmpty){
      return;
    }
    if(taskLists.any((element) => element.fileName.trim() == list.fileName.trim())){
      return;
    }
    setState(() {
      taskLists.add(list);
      currentList = list;
    });
  }

  refresh(TaskList list) {
    WidgetsBinding.instance.addPostFrameCallback((_){
      _setLastList(list);
    });
    setState(() {
      currentList = list;
      _refresh();
    });
  }

  _refresh() {
    setState(() {
      currentList.tasks.sort((a, b) {
        if(a.done){
          return 1;
        }
        return -1;
      });
      _save();
    });

  }

  _initLastList() async{

    final prefs = await SharedPreferences.getInstance();
    try{
      String filename = prefs.get('list') as String;
      print('Importing last list $filename');
      var list = await _fileManager.readTaskList(filename);
      setState(() {
        currentList = list;
      });
    }catch(e){
      print(e);
    }

  }

  _importLists() async{
    var t =  await _fileManager.getTaskLists();
    setState(() {
      if (t.isEmpty){
        taskLists = [TaskList("Remember", [Task("Do school", true), Task("Never give up", false), Task("Laugh", false)]),
      TaskList("Todo", [Task("Train", true), Task("Walk", false), Task("Jog", false)]),
      TaskList("Shopping List", [Task("Milk", true), Task("Coco", false), Task("Pizza", false)])];
      }else{
        taskLists = t;
      }
    });
  }

  String _newListName = "";

  _addTask(String name){
    if(name.isEmpty){
      return;
    }
    setState(() {
      currentList.tasks.add(Task(name, false));
    });
  }

  @override
  void dispose() {
    super.dispose();
  }

  _save() async {
    print("Saving");
    for (var tl in taskLists){
      await _fileManager.writeTaskList(tl);
    }
  }

  _setLastList(TaskList list) async{
    final prefs = await SharedPreferences.getInstance();
    print('Setting last file as: ${list.fileName}');
    await prefs.setString('list', list.fileName);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.green,
          title: const Text("Øving 8"),
        ),
        floatingActionButton: FloatingActionButton(
          child: Icon(Icons.add),
          onPressed: () => showDialog<String>(
          context: context,
          builder: (BuildContext context) => AlertDialog(
            title: const Text('Add new List'),
            content: TextField(
              decoration: const InputDecoration(
                border: OutlineInputBorder(),
                hintText: 'Name of new list',
              ),
              onChanged: (value){
                _newListName = value;
              },
            ),
            actions: <Widget>[
              TextButton(
                onPressed: () => Navigator.pop(context, 'Cancel'),
                child: const Text('Cancel'),
              ),
              TextButton(
                onPressed: () {
                  _addList(TaskList(_newListName,[]));
                  _refresh();
                  Navigator.pop(context, 'Save');
                },
                child: const Text('Save'),
              ),
            ],
            ),
          ),
        ),
        body: Center(
            child: Column(

              children: [
                Card(
                  child: ListTile(
                    title: Text('${currentList.name}', style: TextStyle(fontSize: 24),),
                  ),
                ),
                TextField(
                  controller: _textController,
                  focusNode: focusNode,
                  enabled: currentList.name.isNotEmpty,
                  onSubmitted: (value) {
                    _addTask(value);
                    _refresh();
                    _textController.clear();
                    focusNode.requestFocus();
                  },
                  decoration: InputDecoration(
                    border: const OutlineInputBorder(),
                    hintText: 'New Task',
                    suffixIcon: IconButton(
                      onPressed: _textController.clear,
                      icon: const Icon(Icons.clear),
                    ),
                  ),
                ),
                Expanded(child: ListTasks(taskList: currentList, notifyParent: _refresh)),
              ],
            )
        ),
        drawer: Drawer(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                const SizedBox(
                  height : 80,
                  child  : DrawerHeader(
                      decoration: BoxDecoration(color: Colors.green),
                      margin : EdgeInsets.zero,
                      padding: EdgeInsets.zero,
                      child  : Center(
                          child: Text('Lists',
                              style: TextStyle(color: Colors.white, fontSize: 24)
                          )
                      )
                  ),
                ),
                Flexible(
                  child: ListTaskList(taskList: taskLists, currentList: currentList, notifyParent: refresh),
                ),
              ],
            )
        ),
      ),
    );
  }
}
