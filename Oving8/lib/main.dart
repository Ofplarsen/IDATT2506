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
  TaskList currentList = TaskList("New TaskList", []);
  List<TaskList> taskLists = [TaskList("Remember", [Task("Do school", true), Task("Never give up", false), Task("Laugh", false)]),
    TaskList("Todo", [Task("Train", true), Task("Walk", false), Task("Jog", false)]),
    TaskList("Shopping List", [Task("Milk", true), Task("Coco", false), Task("Pizza", false)])];

  var focusNode = FocusNode();
  final _textController = TextEditingController();

  @override
  void initState(){
    super.initState();
    currentList = taskLists[0];
    WidgetsBinding.instance.addPostFrameCallback((_){
      _getLastList();
    });

    WidgetsBinding.instance.addPostFrameCallback((_){
      _asyncFunc();
    });

  }

  _addList(TaskList list){
    if(taskLists.any((element) => element.name == list.name)){
      return;
    }
    setState(() {
      taskLists.add(list);
    });
  }

  refresh(TaskList list) {
    setState(() {
      currentList = list;
      _refresh();
    });
  }

  _refresh(){
    setState(() {
      currentList.tasks.sort((a, b) {
        if(a.done){
          return 1;
        }
        return -1;
      });
    });
  }

  Future<TaskList> _getLastList() async{
    final prefs = await SharedPreferences.getInstance();
    TaskList lastList = prefs.get('list') as TaskList ?? TaskList("New TaskList", []);
    return lastList;
  }

  _asyncFunc() async{
    FileManager fileManager = FileManager();
    Task task1 = Task("Test1", true);
    Task task2 = Task("Test2", false);
    Task task3 = Task("Test3", false);
    List<Task> tasks = [task1, task2, task3];
    TaskList taskList = TaskList("Test list", tasks);
    await fileManager.writeTaskList(taskList);
    String json = await fileManager.readTaskList(taskList.fileName);
    print(json);
  }

  String _newListName = "";

  addTask(String name){
    setState(() {
      currentList.tasks.add(Task(name, false));
    });
  }

  @override
  void dispose() {
    super.dispose();
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
                  onSubmitted: (value) {
                    addTask(value);
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
                Expanded(child: ListTasks(taskList: currentList, notifyParent: refresh)),
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
/*
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.green,
          title: const Text("Flutter WOOO"),
        ),
        floatingActionButton: FloatingActionButton(
          child: Icon(Icons.add),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (_) => AboutPage(),
              ),
            );
          },
        ),
        bottomNavigationBar: BottomNavigationBar(
          items: const [
            BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: 'Home'
            ),
            BottomNavigationBarItem(
                icon: Icon(Icons.business),
                label: 'Business'
            ),
            BottomNavigationBarItem(
                icon: Icon(Icons.school),
                label: 'School'
            )
          ],
        ),
        drawer: Drawer(
          child: Text("Yo")
        ),
        body: Center(
          child: Text('$count')
          ),
      ),
    );
  }

 */
}
