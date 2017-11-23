import java.util.concurrent.RecursiveAction;

public class TemperatureSolver extends RecursiveAction {
    static int THRESHOLD = GUI.size/16;
    int x, y, height, width;
    public Solution cs;
    Composition composition;
    TemperatureSolver tl, tr, bl, br;
    Main main;

    TemperatureSolver(int x, int y, int width, int height, Composition comp, Main m) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        if (m.state == 0) {
            cs = m.A;
        } else {
            cs = m.B;
        }
        composition = comp;
        main = m;
    }

    @Override
    protected void compute() {

        if (height <= THRESHOLD) {
            double newTemp, temp;

            for (int i = x; i < x + width; i++) {
                for (int j = y; j < y + height; j++) {
                    temp = 0;
                    for (int k = 0; k < 3; k++) {
                        newTemp = 0;
                        //perform calculation for each cell
                        if (i == 0 && j == 0) { //Top left corner
                            newTemp = newTemp
                                    + cs.grid[i+1][j] * composition.grid[i+1][j].percents[k]
                                    + cs.grid[i][j+1] * composition.grid[i][j+1].percents[k]
                                    + cs.grid[i][j] * composition.grid[i][j].percents[k];
                            newTemp = newTemp/3;
                        } else if (i == 0 && j == GUI.size-1) { //Bottom left corner
                            newTemp = newTemp
                                    + cs.grid[i+1][j] * composition.grid[i+1][j].percents[k]
                                    + cs.grid[i][j-1] * composition.grid[i][j-1].percents[k]
                                    + cs.grid[i][j] * composition.grid[i][j].percents[k];
                            newTemp = newTemp/3;
                        } else if (i == GUI.size*2-1 && j == 0) { //Top right corner
                            newTemp = newTemp
                                    + cs.grid[i-1][j] * composition.grid[i-1][j].percents[k]
                                    + cs.grid[i][j+1] * composition.grid[i][j+1].percents[k]
                                    + cs.grid[i][j] * composition.grid[i][j].percents[k];
                            newTemp = newTemp/3;
                        } else if (i == GUI.size*2-1 && j == GUI.size-1) { //Bottom right corner
                            newTemp = newTemp
                                    + cs.grid[i-1][j] * composition.grid[i-1][j].percents[k]
                                    + cs.grid[i][j-1] * composition.grid[i][j-1].percents[k]
                                    + cs.grid[i][j] * composition.grid[i][j].percents[k];
                            newTemp = newTemp/3;
                        } else if (i == 0) { //Left side
                            newTemp = newTemp
                                    + cs.grid[i+1][j] * composition.grid[i+1][j].percents[k]
                                    + cs.grid[i][j-1] * composition.grid[i][j-1].percents[k]
                                    + cs.grid[i][j+1] * composition.grid[i][j+1].percents[k]
                                    + cs.grid[i][j] * composition.grid[i][j].percents[k];
                            newTemp = newTemp/4;
                        } else if (i == GUI.size*2-1) { //Right side
                            newTemp = newTemp
                                    + cs.grid[i-1][j] * composition.grid[i-1][j].percents[k]
                                    + cs.grid[i][j-1] * composition.grid[i][j-1].percents[k]
                                    + cs.grid[i][j+1] * composition.grid[i][j+1].percents[k]
                                    + cs.grid[i][j] * composition.grid[i][j].percents[k];
                            newTemp = newTemp/4;
                        } else if (j == 0) { //Top side
                            newTemp = newTemp
                                    + cs.grid[i-1][j] * composition.grid[i-1][j].percents[k]
                                    + cs.grid[i+1][j] * composition.grid[i+1][j].percents[k]
                                    + cs.grid[i][j+1] * composition.grid[i][j+1].percents[k]
                                    + cs.grid[i][j] * composition.grid[i][j].percents[k];
                            newTemp = newTemp/4;
                        } else if (j == GUI.size-1) { //Bottom side
                            newTemp = newTemp
                                    + cs.grid[i-1][j] * composition.grid[i-1][j].percents[k]
                                    + cs.grid[i+1][j] * composition.grid[i+1][j].percents[k]
                                    + cs.grid[i][j-1] * composition.grid[i][j-1].percents[k]
                                    + cs.grid[i][j] * composition.grid[i][j].percents[k];
                            newTemp = newTemp/4;
                        } else {
                            newTemp = newTemp
                                    + cs.grid[i-1][j] * composition.grid[i-1][j].percents[k]
                                    + cs.grid[i+1][j] * composition.grid[i+1][j].percents[k]
                                    + cs.grid[i][j-1] * composition.grid[i][j-1].percents[k]
                                    + cs.grid[i][j+1] * composition.grid[i][j+1].percents[k]
                                    + cs.grid[i][j] * composition.grid[i][j].percents[k];
                            newTemp = newTemp/5;
                        }
                        temp = temp + newTemp * composition.C[k];
                    }
                    if (main.state == 0) {
                        main.B.grid[i][j] = temp;
                    } else {
                        main.A.grid[i][j] = temp;
                    }
                }
            }
        } else {
            //fork problem into smaller partitions
            tl = new TemperatureSolver(x, y, width/2 + (width % 2), height/2 + (height % 2), composition, main); //top left quadrant
            tr = new TemperatureSolver(x+width/2, y, width/2 + (width % 2), height/2 + (height % 2), composition, main);
            bl = new TemperatureSolver(x, y+height/2, width/2 + (width % 2), height/2 + (height % 2), composition, main);
            br = new TemperatureSolver(x+width/2, y+height/2, width/2 + (width % 2), height/2 + (height % 2), composition, main);
            invokeAll(tl,tr,bl,br);
        }
    }
}
