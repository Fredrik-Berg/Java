package sudoku;

public class Test {
        public static void main(String[] args) {
                Sudoku sod2 = new Sudoku();
                sod2.setValue(0, 2, 8);
        		sod2.setValue(0, 5, 9);
        		sod2.setValue(0, 7, 6);
        		sod2.setValue(0, 8, 2);
        		sod2.setValue(1, 8, 5);
        		sod2.setValue(2, 0, 1);
        		sod2.setValue(2, 2, 2);
        		sod2.setValue(2, 3, 5);
        		sod2.setValue(3, 3, 2);
        		sod2.setValue(3, 4, 1);
        		sod2.setValue(3, 7, 9);
        		sod2.setValue(4, 1, 5);
        		sod2.setValue(4, 1, 5);
        		sod2.setValue(4, 6, 6);
        		sod2.setValue(5, 0, 6);
        		sod2.setValue(5, 7, 2);
        		sod2.setValue(5, 8, 8);
        		sod2.setValue(6, 0, 4);
        		sod2.setValue(6, 1, 1);
        		sod2.setValue(6, 3, 6);
        		sod2.setValue(6, 5, 8);
        		sod2.setValue(7, 0, 8);
        		sod2.setValue(7, 1, 6);
        		sod2.setValue(7, 4, 3);
        		sod2.setValue(7, 6, 1);
        		sod2.setValue(8, 6, 4);
                System.out.println(sod2.solve());
                System.out.println(sod2);
        }
}