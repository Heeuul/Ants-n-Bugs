# Ants n Bugs

### 2D simulation of predator and prey.

### Creatures:

1. Ants(prey):
   - Move: Move randomly any direction every time step unless all directions occupied.
   - Breed: Add 1 at any direction every 3 steps if survives at unless all directions occupied.
2. Bugs(predator):
   - Move: Same as ant, unless there is nearby ant then move to replace ant.
   - Breed: Same as ant, except every 8 steps.
   - Starve: Removed after 3 steps not eating any ant.

### World:

- Square-shaped (equal width & height).
- Enclosed so creatures can't get out.
- 1 cell 1 creature.
- Initialise with 5 bugs 100 ants.

### Time:

- Simulated in time steps.
- Creatures perform some action every step.
- Bugs move before ant.
- Press ENTER for next time step.

### Code:

- Implement GUI.
- public class Organism:
  - Encapsulate basic data common to both creatures.
  - Have overridden method 'move' that is defined derived classes of Ant and Bug.
- May need additional data structures to track moved creatures.
- Design program using atleast 1 design pattern.
- UML diagram.
- Comment for purpose and person wrote.
- Properly indented.
