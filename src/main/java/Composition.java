import java.util.concurrent.ThreadLocalRandom;

public class Composition {
    int width, height;
    double C[] = new double[3];
    Cell grid[][];

    public Composition(int w, int h) {
        width = w;
        height = h;
        grid = new Cell[w][h];
        initComposition();
    }

    private void initComposition() {
        double c1, c2, c3, total;
        int variation1, variation2 = 0, c;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                c1 = 33.3; c2 = 33.3; c3 = 33.3;
                grid[i][j] = new Cell();

                c1 += ThreadLocalRandom.current().nextInt(-12,13);
                c2 += ThreadLocalRandom.current().nextInt(-12,13);
                c3 += ThreadLocalRandom.current().nextInt(-12,13);
                total = c1 + c2 + c3;
                grid[i][j].percents[0] = c1/total;
                grid[i][j].percents[1] = c2/total;
                grid[i][j].percents[2] = c3/total;

//                variation1 = ThreadLocalRandom.current().nextInt(8,58);
//                c = ThreadLocalRandom.current().nextInt(1,3);
//                switch (c) {
//                    case 1:
//                        c1 = variation1;
//                        c = ThreadLocalRandom.current().nextInt(2,3);
//                        if (variation1 < 42) {
//                            variation2 = ThreadLocalRandom.current().nextInt(8,58);
//                        } else {
//                            variation2 = ThreadLocalRandom.current().nextInt(8,100-variation1);
//                        }
//                        switch (c) {
//                            case 2:
//                                c2 += variation2;
//                                c3 += 25 - variation1 - variation2;
//                                break;
//                            case 3:
//                                c2 += 25 - variation1 - variation2;
//                                c3 += variation2;
//                                break;
//                        }
//                        break;
//                    case 2:
//                        c2 += variation1;
//                        c = ThreadLocalRandom.current().nextInt(1,2);
//                        if (c == 2) c = 3;
//                        variation2 = ThreadLocalRandom.current().nextInt(0,25-variation1);
//                        switch (c) {
//                            case 1:
//                                c1 += variation2;
//                                c3 += 25 - variation1 - variation2;
//                                break;
//                            case 3:
//                                c1 += 25 - variation1 - variation2;
//                                c3 += variation2;
//                                break;
//                        }
//                        break;
//                    case 3:
//                        c3 += variation1;
//                        c = ThreadLocalRandom.current().nextInt(1,2);
//                        variation2 = ThreadLocalRandom.current().nextInt(0,25-variation1);
//                        switch (c) {
//                            case 1:
//                                c1 += variation2;
//                                c2 += 25 - variation1 - variation2;
//                                break;
//                            case 2:
//                                c1 += 25 - variation1 - variation2;
//                                c2 += variation2;
//                                break;
//                        }
//                        break;
//                }
//                System.out.println(variation1);
//                System.out.println(variation2);
//                System.out.println(25 - variation1 - variation2);

//                grid[i][j].percents[0] = c1/total;
//                grid[i][j].percents[1] = c2/total;
//                grid[i][j].percents[2] = c3/total;
//                System.out.println(grid[i][j].percents[0]);
//                System.out.println(grid[i][j].percents[1]);
//                System.out.println(grid[i][j].percents[2]);
//                System.out.println("=========");
            }
        }
    }
}
