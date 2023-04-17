# Shapes Photo Album Part 1

* **Author**: Jen Ting, Huang
* **Semester** Spring 2023
* **Languages Used**: Java

## Table of contents

* [About The Project](#about-the-project)
* [Architectural Approach](#Architectural-Approach)
* [Design Pattern](#technologies)
* [Getting Started](#getting-started)

## About The Project

In this project, I will build an application that helps to create a simple "photo model" from
shapes. The main functions of the photo model are:

* `Put shape function`: Put the shape on the canvas.
* `Change shape size function`: Change the size of the shape on the canvas.
* `Change shape location function`: Change the location of the shape on the canvas.
* `Change shape color function`: Change the color of the shape on the canvas.
* `Take snapshot function`: Take a snapshot of the current status on the canvas.

## Architectural Approach

I have chosen the `MVC` (model-View-Controller) pattern in software design, which is commonly used
to implement user interfaces, data, and controlling logic. It emphasizes a separation between the
software's business logic and display.

For part 1 of the shape model, I will implement the model part, which focuses on implementing the
functionality for the shape model. The model holds all the data, state, and application logic.

## Design Pattern

1. For the creational design pattern, I have chosen the `Factory Method Pattern`. It adheres to
   the `Single Responsibility Principle`.

    * Single responsibility: The logic for creating shapes is isolated in one place, rather than
      being scattered throughout the codebase.
2. For the Behavioral Design Pattern, I have chosen the `Command Design Pattern`. It has several
   benefits, including:

    * `Single Responsibility Principle`: By separating the responsibilities of the invoker, command,
      and receiver, it makes it easier to modify or extend the application without affecting other
      parts of the system.
    * `Open-Closed Principle`: Extensions to add a new command are easy and can be done without
      changing the existing code.
    * `Supporting Undo Functionality`: The Command pattern is particularly useful for implementing
      undo/redo functionality, allowing you to store a history of executed commands and easily
      revert or redo actions as needed.
    * `Enabling Task Scheduling`: Using the Command pattern, you can easily schedule tasks to be
      executed at different times, and even reorder or remove tasks from the queue as needed.

## Getting Started

You can play with the code in the `Main.java` file. I will introduce some basic implementations in
the following code.

1. Create a canvas
    ```java=
    canvas = new Canvas(300, 200);
    ```
   Since I have only implemented one canvas so far, I am using a traditional approach to create the
   canvas. However, I will use the builder, a creational design pattern, to implement it in the
   future.

2. Create a shape (Using Factory Design Pattern)
    ```java=
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    ```
   Use Factory to create a shape.

3. Play with different Command (Using Command Design)

   For example:
    - Put shape on the canvas
    ```java=
    // 1st step. Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // 2nd step. Invoke Command
    invoker = new Invoker(putShape);
    // 3rd step. Do the command
    invoker.press();
    ```
    - Resize the shape on canvas
    ```java=
    // 1st step. Make command
    changeCircleSize = new ChangeCircleSize(canvas1, "circle", "C", 20);
    // 2nd step. Invoke Command
    invoker = new Invoker(changeCircleSize);
    // 3rd step. Do the command
    invoker.press();
    ```
    - Take snapshot of the canvas
    ```java=
    // 1st step. Make command
    takeSnapshot = new SnapShotCanvas(canvas, "First snapshot");
    // 2nd step. Invoke Command
    invoker = new Invoker(takeSnapshot);
    // 3rd step. Do the command
    invoker.press();
    ```
    - Print out all of the snapshots
    ```java=
    // 1st step. Make command
    showSnapshots = new PrintSnapshots(canvas1);
    // 2nd step. Invoke Command
    invoker = new Invoker(showSnapshots);
    // 3rd step. Do the command
    invoker.press();
    ```

   ### Undo feature:
    ```java=
    invoker.pressUndo(); // Simply using pressUndo() to undo the last action
    ```
   So far, I have only implemented the logic to undo the most recent action performed, meaning that
   users can only undo the last action that was taken. However, in the next week, I plan to extend
   this functionality to allow users to undo a sequence of actions.