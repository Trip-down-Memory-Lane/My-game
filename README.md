# My game version 2.2
########################################

# Game frame - Java.swing multithreaded
-----------------------------------------

# Game state
-----------------------------------------
 Maze: frame, outline, walls all in one class.
 User: Moving Hero sprite ( foreground / background );
     - Sprint that increases movent speed by 50% for 3s. CD: 10s;
 AI: chasing hero sprite ( foreground / backgroudn );
 Graphics: Custom skins ( Amy desig )

 Win condition: Cross the maze;
 Lose condition: get captured by badGuy.
 
 Known bugs:
 1) AI sometimes stumbles and gets stuck on small passages and the outline.
 2) Foreground / background loading inproperly sometimes.


********************************************************************
** TO IMPLEMENT
********************************************************************
[1)] Marginalize and generalise classes for reuse in further levels

 2) Create actuall menu:
     - Implement mouse listener;
     - Actual buttons;
     - More suitable graphics;
     - Game settings;

 3) Maximizeable frame:
     - Read resolution from OS;
     - Background fill;

 4) Objects that make the game more interesting:
     - obstacles, like traps
     - buffs that help the hero
 
