import 'dart:io';
import 'dart:ui';
import 'package:oving8/managers/FileManager.dart';
import 'package:oving8/models/task.dart';
import 'package:oving8/models/task_list.dart';
import 'package:shared_preferences/shared_preferences.dart';


import 'about.dart';
import 'package:flutter/material.dart';

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
  int count = 0;

  @override
  void initState(){
    super.initState();


    WidgetsBinding.instance.addPostFrameCallback((_){
      _asyncFunc();
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
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => CreateList()),
            );
          },
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
