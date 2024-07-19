package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile("deleteproduct.in");
        StdOut.setFile("deleteproduct.out");
        Warehouse createdWarehouse = new Warehouse();
        int numberOfQueries = StdIn.readInt();
        for (int i = 0; i < numberOfQueries; i++) {
            String addOrRestock = StdIn.readString();
            if (addOrRestock.equalsIgnoreCase("add")) {
                int readDay = StdIn.readInt();
                int readProductID = StdIn.readInt();
                String readName = StdIn.readString();
                int readStock = StdIn.readInt();
                int readDemand = StdIn.readInt();
                createdWarehouse.addProduct(readProductID, readName, readStock, readDay, readDemand);
            }
            if (addOrRestock.equalsIgnoreCase("delete")) {
                int readDeleteID = StdIn.readInt();
                createdWarehouse.deleteProduct(readDeleteID);
            }
        
        }
        StdOut.println(createdWarehouse);
	// Use this file to test deleteProduct
    }
}
