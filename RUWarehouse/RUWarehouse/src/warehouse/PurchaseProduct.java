package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
        StdIn.setFile("purchaseproduct.in");
        StdOut.setFile("purchaseproduct.out");

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
            if (addOrRestock.equalsIgnoreCase("purchase")) {
                int readDay = StdIn.readInt();
                int readPurchaseID = StdIn.readInt();
                int readAmountPurchased = StdIn.readInt();
                createdWarehouse.purchaseProduct(readPurchaseID, readDay, readAmountPurchased);
            }
        
        }
        StdOut.println(createdWarehouse);
	// Use this file to test purchaseProduct
    }
}
