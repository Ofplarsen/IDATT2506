import 'package:flutter/material.dart';

class CreateList extends StatelessWidget {
  const CreateList({super.key});

  @override
  Widget build(BuildContext context) {
    const appTitle = 'Øving 8';

    return MaterialApp(
      title: appTitle,
      home: Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.green,
          title: const Text(appTitle),

        ),
        body: const MyCustomForm(),
      ),
    );
  }
}


class MyCustomForm extends StatelessWidget {
  const MyCustomForm({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: const <Widget>[
        Padding(
          padding: EdgeInsets.symmetric(horizontal: 8, vertical: 16),
          child: TextField(
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              hintText: 'Name of new list',
            ),
          ),
        ),
      ],
    );
  }
}