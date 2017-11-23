import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    int size;
    int state = 0;
    double T, S;
    Solution A;
    Solution B;
    Composition composition;
    MyPainter painter;
    int count = 0, tlr;

    public Main (double T, double S, double C1, double C2, double C3, Solution a, Solution b, MyPainter mp, int size) {
        this.T = T; this.S = S;
        this.size = size;
        composition = new Composition(2*size,size);
        composition.C[0] = C1; composition.C[1] = C2; composition.C[2] = C3;
        A = a; B = b;
        painter = mp;
        if (T <= S) {
            painter.maxTemp = S;
        } else {
            painter.maxTemp = T;
        }
    }

    public void run() {
        A.grid[0][0] = S;
        A.grid[2*size-1][size-1] = T;
        A.grid[999][499] = T;
        A.grid[150][150] = T;
        A.grid[100][200] = T;
        A.grid[100][100] = T;
        A.grid[50][50] = T;
        A.grid[150][50] = T;
        A.grid[250][50] = T;
        A.grid[350][50] = T;
        A.grid[450][50] = T;
        A.grid[550][50] = T;
        A.grid[650][50] = T;
        A.grid[750][50] = T;
        A.grid[850][50] = T;
        A.grid[950][50] = T;


        TemperatureSolver tp = new TemperatureSolver(0,0,2*size,size,composition,this);
        ForkJoinPool pool = new ForkJoinPool();
        do {
            pool.invoke(tp);
                if (state == 0) {
                    painter.paintSolution(B);

                    tlr = ThreadLocalRandom.current().nextInt(1,100);
                    if (tlr < 2) {
                        B.grid[0][0] = S;
                        B.grid[2 * size - 1][size - 1] = T;
                        B.grid[999][499] = T;
                        B.grid[150][150] = T;
                        B.grid[100][200] = T;
                        B.grid[100][100] = T;
                        B.grid[50][50] = T;
                        B.grid[150][50] = T;
                        B.grid[250][50] = T;
                        B.grid[350][50] = T;
                        B.grid[450][50] = T;
                        B.grid[550][50] = T;
                        B.grid[650][50] = T;
                        B.grid[750][50] = T;
                        B.grid[850][50] = T;
                        B.grid[950][50] = T;
                    }

                } else {
                    painter.paintSolution(A);

                    tlr = ThreadLocalRandom.current().nextInt(1,100);
                    if (tlr < 2) {
                        A.grid[0][0] = S;
                        A.grid[2 * size - 1][size - 1] = T;
                        A.grid[999][499] = T;
                        A.grid[150][150] = T;
                        A.grid[100][200] = T;
                        A.grid[100][100] = T;
                        A.grid[50][50] = T;
                        A.grid[150][50] = T;
                        A.grid[250][50] = T;
                        A.grid[350][50] = T;
                        A.grid[450][50] = T;
                        A.grid[550][50] = T;
                        A.grid[650][50] = T;
                        A.grid[750][50] = T;
                        A.grid[850][50] = T;
                        A.grid[950][50] = T;
                    }
                }
            if (state == 0) {
                state = 1;
            } else {
                state = 0;
            }
            tp = new TemperatureSolver(0,0,2*size,size,composition,this);
            count++;
            System.out.println(count);
        } while (!convergence(B,A) && count < 2000);
        System.out.println("I finished running!");

        if (state == 0) {
            painter.paintSolution(B);
        } else {
            painter.paintSolution(A);
        }
    }

    public Boolean convergence(Solution t0, Solution t1) {
        double diff = 0;
        for (int i = 0; i < t0.width; i++) {
            for (int j = 0; j < t0.height; j++) {
                diff = diff + Math.abs(t0.grid[i][j] - t1.grid[i][j]);
            }
        }
        if (diff < 0.001) {
            return true;
        } else if (diff > 300000) {
            return true;
        }
        System.out.println(diff);
        return false;
    }
}
