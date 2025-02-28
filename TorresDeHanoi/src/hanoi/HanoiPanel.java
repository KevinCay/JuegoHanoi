package hanoi;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class HanoiPanel extends JPanel{
    private Stack<Integer>[] torres;

    public HanoiPanel(Stack<Integer>[] torres) {
        this.torres = torres;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int baseY = height - 50;
        int torreWidth = 20;
        int discoHeight = 15;

        for (int i = 0; i < torres.length; i++) {
            int torreX = (width / torres.length) * i + (width / torres.length) / 2;
            g.fillRect(torreX - torreWidth / 2, baseY - 150, torreWidth, 150);

            Stack<Integer> torre = torres[i];
            int y = baseY - discoHeight;
            for (int disco : torre) {
                int discoWidth = 40 + disco * 20;
                g.fillRect(torreX - discoWidth / 2, y, discoWidth, discoHeight);
                y -= discoHeight;
            }
        }
    }

    public void actualizarTorres(Stack<Integer>[] nuevasTorres) {
        this.torres = nuevasTorres;
        repaint();
    }
}
