import java.awt.Font;

class InteractiveFloorVisualizer{
  public static void main(String[] args) {
    int N = 10;
    // turn on animation mode
    StdDraw.show(0);

    // set background, leave margin for writing
    StdDraw.setXscale(-0.5, N+0.5);
    StdDraw.setYscale(-0.5, N+0.5);
    StdDraw.clear();
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.filledSquare(N/2.0, N/2.0, N/2.0);
    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
  }
}
