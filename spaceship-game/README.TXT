Project: zuul-bad
Authors: Michael Kölling and David J. Barnes

This project is part of the material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   Seventh edition
   David J. Barnes and Michael Kölling

This project is a simple framework for an adventure game. In this version,
it has a few rooms and the ability for a player to walk between these rooms.
That's all.

To start this application, create an instance of class "Game" and call its
"play" method.

This version of the game contains some very bad class design. It should NOT
be used as a basis for extending the project without fixing these design
problems. It serves as an example to discuss good and bad design (chapter 8
of the book).

Chapter 8 of the book contains a detailed description of the problems in this
project, and how to fix them.

The project 'zuul-better' contains a version of this project with better
designed class structure. It includes the fixes discussed in the book.

1. it's a text based adventure game that allows you to explore a university. The game
accepts three commands: go + direction allows you to exit a room and go to the next, 
help allows you to view the possible commands and quit ends the game.

2. Game allows the game to be run, prints out dialogue and processes commands. Parser collects player input and turns
it into commands when possible. Command words defines the valid commands and checks if commands
are valid. Commands are the instructions that allow you to progress in the game and Rooms are 
the places you can go.

9. the keySet() method returns a set of all of the keys of a hashmap
13. the currentRoom field of the Game object changes.
17. no, you would only need to change the commandWords class because everything after
references that