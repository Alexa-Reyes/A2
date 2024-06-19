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
        setSize(1280, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout()); // <= Important! Panel holding sim visualiser needs a layout to enforce size
                                                           // BorderLayout works well as things added to it should use space available.

        simulation.getPanel().setSize(800, 800); // <= Minimum allowable panel size is 800 x 800.
        mainPanel.add(simulation.getPanel()); // <= Important! A panel on you UI window needs a reference to the simulation's panel
                                              // Note: If you re-instantiate simulation after doing this it will delete the panel being
                                              // referenced as well so nothing will show up on screen.
                                              // i.e if you do simulation = new Simulation() after this point the panel will lose
                                              // it's reference to the sim output window.
                                              // Recommendation: Instantiate once and use the configure method to update settings as shown in this demo.


        add(mainPanel);
        setVisible(true);                     // Important! Only configure sim after adding panel to UI and making that UI visible

        // configuring simple text area
        textArea.setEditable(false);
        mainPanel.add(textArea, BorderLayout.SOUTH);


        // This body configuration will create a simple chaotic 3-body orbit
        ArrayList<OrbitalBody> bodies = new ArrayList<>();
        // Chaotic 3-body orbit
        bodies.add(new OrbitalBody(new double[]{150, 50}, new double[]{-15, -30}, 3, Color.RED));
        bodies.add(new OrbitalBody(new double[]{50, -150}, new double[]{5, 40}, 3, Color.GREEN));
        bodies.add(new OrbitalBody(new double[]{-200, -50}, new double[]{10, -10}, 3, Color.BLUE));
        SimulationSettings settings = new SimulationSettings(bodies);
        settings.setInfinite(true);
        settings.setShowCenterOfGravity(false);
        settings.setBackgroundColor(Color.WHITE);
        settings.setShowTrail(true);
        // etc.

        simulation.configure(settings);     // <= Important! You must configure the simulation with a settings object before running it.
                                            // This configuration should only ever be called after you've added the sim's panel to your UI and
                                            // set your window to visible, so the internal graphics understands the windows shape when
                                            // setting up the visualisation
        simulation.start();

        new Timer(100, e -> updateTextArea(bodies)).start(); // The simulation runs on a separate thread, so on
                                                                   // this thread, we check every 100ms for changes the bodies.
                                                                   // The sim has a reference to the OrbitalBody asArrayList assigned to the settings object
                                                                   // and is updating them at their memory location, so we're just looking into those memory
                                                                   // locations to see what the most recent data looks like and updating the UI with it.
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
