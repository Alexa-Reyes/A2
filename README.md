# N-Body Simulation for OOP Class

![Simulation Demo](https://media.giphy.com/media/l3vR85PnGsBwu1PFK/giphy.gif)

## Overview

This project demonstrates an n-body simulation. The simulation visualizes the chaotic and intricate orbits resulting from gravitational interactions between multiple bodies.

## Features

- Real-time simulation of n-body gravitational interactions.
- Configurable simulation settings.
- Visual representation with customizable body colors and trails.
- Interactive GUI with body position and velocity updates.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- An IDE such as IntelliJ IDEA, NetBeans, or Visual Studio Code.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/nbodysimulation.git
   ```

2. Open the project in your preferred IDE.

3. Ensure that the required libraries are properly included in your project settings either using Maven (see [pom.xml](https://github.com/Santius0/nbodysim/blob/main/pom.xml) for more details) or via the included jar files found in [lib](https://github.com/Santius0/nbodysim/tree/main/lib).

### Running the Simulation

To run the simulation, execute the `Demo` class. This can typically be done by right-clicking on the `Demo` class in your IDE and selecting "Run".

**Note: You have to finish `SimulationSettings.java` for the demo to work!**

### Example Code

Here's a snippet of the main `Demo` class to get you started:

```java
import NBobodySimulation.OrbitalBody;
import NBobodySimulation.Simulation;
import NBobodySimulation.SimulationSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Demo extends JFrame {

    Simulation simulation = new Simulation();
    JTextArea textArea = new JTextArea();

    public Demo() {
        setTitle("Demo");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(simulation.getPanel());
        add(mainPanel);
        setVisible(true);

        textArea.setEditable(false);
        mainPanel.add(textArea, BorderLayout.SOUTH);

        ArrayList<OrbitalBody> bodies = new ArrayList<>();
        bodies.add(new OrbitalBody(new double[]{100, 100}, new double[]{30, 0}, 1, Color.RED));
        bodies.add(new OrbitalBody(new double[]{-100, -100}, new double[]{-30, 0}, 1, Color.BLUE));
        bodies.add(new OrbitalBody(new double[]{0, 0}, new double[]{0, 1}, 1, Color.GREEN));

        SimulationSettings settings = new SimulationSettings(bodies);
        settings.setInfinite(true);
        settings.setShowCenterOfGravity(false);
        settings.setBackgroundColor(Color.WHITE);
        settings.setShowTrail(true);

        simulation.configure(settings);
        simulation.start();

        new Timer(100, e -> updateTextArea(bodies)).start();
    }

    private void updateTextArea(java.util.List<OrbitalBody> bodies) {
        StringBuilder sb = new StringBuilder();
        for (OrbitalBody body : bodies) {
            sb.append(body.toString()).append("\n");
        }
        textArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        new Demo();
    }
}
```

## Customization

You can customize the simulation by modifying the `SimulationSettings` object (which you have to implement) in the `Demo` class. Options include:

- `setInfinite(boolean infinite)`: Set to `true` for an infinite simulation.
- `setShowCenterOfGravity(boolean show)`: Toggle visibility of the center of gravity.
- `setBackgroundColor(Color color)`: Set the background color of the simulation.
- `setShowTrail(boolean show)`: Toggle trails behind the bodies.

etc. See assignment document for full list.

## Inspired By
The [3-body problem](https://en.wikipedia.org/wiki/Three-body_problem) in classical mechanics.

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/Santius0/nbodysim/blob/main/LICENSE) file for details.

---

Enjoy your simulation and happy coding!