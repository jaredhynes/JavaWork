package warehouse;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {
    public static void main(String[] args) {
        StdIn.setFile("addproduct.in");
        StdOut.setFile("addproduct.out");

        Warehouse createdWarehouse = new Warehouse();
        int numberOfProducts = StdIn.readInt();
        // first line in addproduct.in is a number
        for (int i = 0; i < numberOfProducts; i++) {
            int readDay = StdIn.readInt();
            int readID = StdIn.readInt();
            String readName = StdIn.readString();
            int readStockNumber = StdIn.readInt();
            int readDemand = StdIn.readInt();

            createdWarehouse.addProduct(readID, readName, readStockNumber, readDay, readDemand);
        }

        // Warehouse already has a toString no need to worry abt that
        StdOut.println(createdWarehouse);
	// Use this file to test addProduct
    }

}
