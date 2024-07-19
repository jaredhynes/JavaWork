package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile("restock.in");
        StdOut.setFile("restock.out");
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
            if (addOrRestock.equalsIgnoreCase("restock")) {
                int readRestockID = StdIn.readInt();
                int readRestockAmount = StdIn.readInt();
                createdWarehouse.restockProduct(readRestockID, readRestockAmount);
            }
        
        }
        StdOut.println(createdWarehouse);
    
	// Uset his file to test restock
    }
}
