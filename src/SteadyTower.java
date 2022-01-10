import java.util.Vector;
import java.util.Random;

public class SteadyTower {

    public static void main(String args[]) {
        int maxHeight;
        Vector<Box> box = new Vector<>();
        box = Builder(30);
        System.out.println("List of 30 boxes");
        System.out.println("\t    [L] [W] [H]");
        for (Box i : box) {
            System.out.println("box \t[" + i.height + "," + i.length + "," + i.width + "]");
        }
        maxHeight = BottomUp(box, box.size());
        System.out.println("\n-----------------------------------------\n");
        System.out.println("\tThe Highest Tower Is =  " + maxHeight+"m\n");
        System.out.println("-----------------------------------------\n");
    }

    public static int BottomUp(Vector<Box> boxes, int numOfBoxes) {
        int max = 0;
        Vector<Integer> totalHeight = new Vector<>();
        totalHeight.add(boxes.get(0).height);
        for (int i = 1; i < numOfBoxes; i++) {
            totalHeight.add(boxes.get(i).height);
            for (int j = 0; j < i; j++) {
                if (boxes.get(j).width > boxes.get(i).width) {
                    if (totalHeight.get(j) + boxes.get(i).height > totalHeight.get(i)) {
                        totalHeight.set(i, boxes.get(j).height + boxes.get(i).height);
                    }
                }
            }
        }
        for (int height : totalHeight) {
            max = Math.max(max, height);
        }
        return max;
    }

    public static Vector<Box> Builder(int numOfBoxes) {
        Random rand = new Random();
        Vector<Box> box = new Vector<>();
        for (int i = 0; i < numOfBoxes; i++) {
            box.add(new Box(rand.nextInt(200),
                    rand.nextInt(200),
                    rand.nextInt(200)));
        }
        return box;
    }//making vector of random boxes.

    public static Vector<Box> Sort(Vector<Box> boxes) {
        Box temp;
        for (int i = 0; i < boxes.size() - 1; i++) {
            for (int j = 0; j < boxes.size() - 1 - i; j++) {
                if (boxes.get(j).length > boxes.get(j + 1).length) {
                    temp = boxes.elementAt(j);
                    boxes.set(j, boxes.get(j + 1));
                    boxes.set(j + 1, temp);
                }
            }
        }
        return boxes;
    }//sort the boxes by the length (efficiency of O(n^2)).

    public static class Box {

        public int height;
        public int width;
        public int length;

        public Box(int height, int width, int length) {
            this.height = height;
            this.width = width;
            this.length = length;
        }

        public boolean Bigger(Box box) {
            return (this.length > box.length);
        }
    }
}
