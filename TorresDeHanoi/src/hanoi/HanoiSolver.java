package hanoi;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class HanoiSolver {
    private int numDiscos;
    private Stack<Integer>[] torres;

    @SuppressWarnings("unchecked")
    public HanoiSolver(int numDiscos, int numTorres) {
        this.numDiscos = numDiscos;
        torres = new Stack[numTorres];
        for (int i = 0; i < numTorres; i++) {
            torres[i] = new Stack<>();
        }

        for (int i = numDiscos; i > 0; i--) {
            torres[0].push(i);
        }
    }

    public void resolver(int n, int origen, int destino, List<Integer> auxiliares, HanoiPanel panel) {
        if (n == 0) return;

        if (n == 1) {
            moverDisco(origen, destino, panel);
            return;
        }

        int r = auxiliares.size() + 2;

        if (r == 3) {
            int aux = auxiliares.get(0);
            resolver(n - 1, origen, aux, List.of(destino), panel);
            moverDisco(origen, destino, panel);
            resolver(n - 1, aux, destino, List.of(origen), panel);
        } else {
            int k = (int) Math.round(n - Math.sqrt(2 * n + 1)) + 1;

            int torreIntermedia = auxiliares.get(0);
            List<Integer> restoAuxiliares = new ArrayList<>(auxiliares);
            restoAuxiliares.remove(0);

            resolver(k, origen, torreIntermedia, restoAuxiliares, panel);
            resolver(n - k, origen, destino, restoAuxiliares, panel);
            resolver(k, torreIntermedia, destino, restoAuxiliares, panel);
        }
    }

    private void moverDisco(int origen, int destino, HanoiPanel panel) {
        if (!torres[origen].isEmpty()) {
            int disco = torres[origen].pop();
            if (!torres[destino].isEmpty() && torres[destino].peek() < disco) {
                torres[origen].push(disco);
                return;
            }

            torres[destino].push(disco);
            panel.actualizarTorres(torres);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public Stack<Integer>[] getTorres() {
        return torres;
    }
}
