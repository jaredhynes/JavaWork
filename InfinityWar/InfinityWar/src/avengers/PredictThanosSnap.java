package avengers;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes. 
 * Then, write into the output file a boolean (true or false) indicating if 
 * the graph is still connected.
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 *    1. seed (long): a seed for the random number generator
 *    2. p (int): number of people (vertices in the graph)
 *    2. p lines, each with p edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency matrix for
 * an undirected graph. 
 * 
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 * 
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) { 
 *     if (StdRandom.uniform() <= 0.5) { 
 *          delete vertex;
 *     }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 * 
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 * 
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, isConnected is true if the graph is connected,
 *   false otherwise):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(isConnected);
 * 
 * @author Yashas Ravi
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in predictthanossnap.out
*/

public class PredictThanosSnap {
	 
    public static void main (String[] args) {
 
        if ( args.length < 2 ) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }
        
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        StdRandom.setSeed(StdIn.readLong());
        int numOfNodes = StdIn.readInt();
        int copyOfNumOfNodes = numOfNodes;
        int numOfEdges = 0;
        int[][] adjacencyMatrix = new int[numOfNodes][numOfNodes];
        for (int i = 0; i <numOfNodes; i++) {
            for (int j = 0; j< numOfNodes; j++) {
                adjacencyMatrix[i][j] = StdIn.readInt();
            }
        }
       for (int i = 0; i < numOfNodes; i++) {
        if (StdRandom.uniform() <= 0.5) {
            // NEED TO TAKE AWAY ONE FROM THE NUMBER OF NODES.
            // But can't use the original one beacuse we need to access it later...
            copyOfNumOfNodes -= 1;
            for (int j = 0; j <numOfNodes; j++) {
                adjacencyMatrix[i][j] = 0;
                adjacencyMatrix[j][i] = 0;
            }
        } 
    }
        for (int i = 0; i< numOfNodes; i++) {
            for (int j = 0; j<numOfNodes; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    numOfEdges++;
                }
            }
        }
        numOfEdges /= 2;
        StdOut.print(copyOfNumOfNodes - numOfEdges <= 1);
    }
}