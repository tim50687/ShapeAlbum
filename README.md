Shapes Photo Album
==================

*   **Author**: Jen Ting, Huang
*   **Semester**: Spring 2023
*   **Languages Used**: Java

Table of contents
-----------------

*   [About The Project](#about-the-project)
*   [Architectural Approach](#architectural-approach)
*   [Design Pattern](#design-pattern)
*   [Getting Started](#getting-started)

About The Project
-----------------

The Shapes Photo Album is an application that allows users to create photo models from shapes. The main functions of the photo model include putting shapes on the canvas, changing the size, location, and color of the shapes, and taking snapshots of the current status on the canvas.

This project is divided into two parts:

*   Part 1: Implementing the model functionality for the shape model.
*   Part 2: Implementing the view and controller functionality for the shape model.

Architectural Approach
----------------------

For this project, we have chosen the `MVC` (Model-View-Controller) pattern in software design, which is commonly used to implement user interfaces, data, and controlling logic. It emphasizes a separation between the software's business logic and display.

In part 1 of the shape model, we have implemented the model part, which focuses on implementing the functionality for the shape model. The model holds all the data, state, and application logic.

Design Pattern
--------------

We have chosen two design patterns for this project:

1.  Creational Design Pattern: `Factory Method Pattern`
    *   Single responsibility: The logic for creating shapes is isolated in one place, rather than being scattered throughout the codebase.
2.  Behavioral Design Pattern: `Command Design Pattern`
    *   Single Responsibility Principle: By separating the responsibilities of the invoker, command, and receiver, it makes it easier to modify or extend the application without affecting other parts of the system.
    *   Open-Closed Principle: Extensions to add a new command are easy and can be done without changing the existing code.
    *   Supporting Undo Functionality: The Command pattern is particularly useful for implementing undo/redo functionality, allowing you to store a history of executed commands and easily revert or redo actions as needed.
    *   Enabling Task Scheduling: Using the Command pattern, you can easily schedule tasks to be executed at different times, and even reorder or remove tasks from the queue as needed.

Getting Started
---------------

To play with the code, run `Main.java`.

### Basic Implementations

1.  Create a canvas:
    
    java
    
    ```java
    Canvas canvas = new Canvas(300, 200);
    ```
    
    Since we have only implemented one canvas so far, we are using a traditional approach to create the canvas. However, we will use the builder, a creational design pattern, to implement it in the future.
    
2.  Create a shape (Using Factory Design Pattern):
    
    java
    
    ```java
    ShapeFactory factory = new ShapeFactory();
    Coordinate coordinate = new Coordinate(0, 0);
    Color red = new Color(255, 0, 0);
    Circle circle = factory.getShape("circle", "C", coordinate, red, 10);
    ```
    
    Use Factory to create a shape.
    
3.  Play with different commands (Using Command Design):
    
    For example:
    
    *   Put shape on the canvas
    
    java
    
    ```java
    PutShape putShape = new PutShape(canvas, "circle", "C", circle);
    Invoker invoker = new Invoker(putShape);
    invoker.press();
    ```
    
    *   Resize the shape on canvas
    
    java
    
    ```java
    ChangeCircleSize changeCircleSize = new ChangeCircleSize(canvas, "circle", "C", 20);
    Invoker invoker
    ```