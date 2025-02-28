package hanoi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HanoiFrame extends JFrame{
    private HanoiSolver solver;
    private HanoiPanel panel;
    private JButton resolverBtn;

    public HanoiFrame(int numDiscos, int numTorres) {
        solver = new HanoiSolver(numDiscos, numTorres);
        panel = new HanoiPanel(solver.getTorres());
        resolverBtn = new JButton("Resolver");

        resolverBtn.addActionListener(e -> {
            new Thread(() -> {
                List<Integer> auxiliares = new ArrayList<>();
                for (int i = 1; i < numTorres - 1; i++) {
                    auxiliares.add(i);
                }
                solver.resolver(numDiscos, 0, numTorres - 1, auxiliares, panel);
            }).start();
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(resolverBtn, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
