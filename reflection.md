Use this file to record your reflection on this assignment.

- Tell me about your class! What does it represent?
The Person class represents a person with multiple attributes that could get manipulated with the methods in the class, implementing the interface Contract.

- What additional methods (if any) did you implement alongside those listed in the interface?
    I implemented a constructor for Person so it can create the object that could perform the implemented methods. It would have attributes name for identification, ballRadius to be manipulated by shrink() and grow(), x and y positions to keep track of the person's position, an inventory that would grab() and drop() items, and actionHistory to keep track of the executed actions in case of undoing them. 

- What worked, what didn't, what advice would you give someone taking this course in the future?
    The undo method undoes the last conducted action but does not undo the second to last action when called twice. It would undo the very last action twice. It might be improved if stacks is utilized. Instead of having items as String we could create another class for Item so it holds more information that could be printed or manipulated by examine() and use() methods. 