package course.labs.single;

public class Lab1 {

    static float distance(int x1, int y1, int x2, int y2) {
        return (float)Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

    static void part1(int size) {
        for (int i = 1; i < size+1; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print('x');
            }
            System.out.println();
        }
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(' ');
            }
            for (int j = i; j < size; j++) {
                System.out.print('x');
            }
            System.out.println();
        }
    }

    static void part2(final int size, final float thickness) {
        boolean not_edited = true;
        for (int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                if (Math.abs(r-c) < thickness && Math.abs(r-(size-c)) < thickness) {
                    System.out.print("X ");
                } else if (Math.abs(r-c) < thickness) {
                    System.out.print("\\ ");
                } else if (Math.abs(r-(size-c)) < thickness) {
                    System.out.print("/ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    static void part3 (final int size, final float thickness) {
        int centerX = size / 2;
        int centerY = size / 2;
        boolean not_edited = true;
        for (int r = 0; r < size; r++) {
            int circle_fix = 0;
            for (int c = 0; c < size; c++) {
                float dist1 = distance(centerX, centerY, c, r);
                if (Math.abs(r - c) < thickness && Math.abs(r - (size - c)) < thickness) {
                    System.out.print("X ");
                } else if (Math.abs(r - c) < thickness) {
                    System.out.print("\\ ");
                } else if (Math.abs(r - (size - c)) < thickness) {
                    System.out.print("/ ");
                } else if ((int)dist1 < (int)(size*.25+thickness) && (int)dist1 > (int)(size*.25-thickness)) {
                    System.out.print("0 ");
                } else if ((int)dist1 < (int)(size*.35f+thickness) && (int)dist1 > (int)(size*.35f-thickness)) {
                    System.out.print("0 ");
                } else if ((int)dist1 < (int)(size*.45f+thickness) && (int)dist1 > (int)(size*.45f-thickness)) {
                    System.out.print("0 ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int size = 40;
        float thick = 1.5f;
        part1(size);
        part2(size, thick);
        part3(size, thick);
    }

}
