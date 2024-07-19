package avengers;


/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
	
    // Wormhole generators are vertices
    // Wormholes are the edges
    // Vertex 0 is the source, and vertex n-1 is the destination.

    public static void main (String [] args) {
    	
         if ( args.length < 2 ) {
             StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
             return;
         }
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

     
        // read how many generators there are - then need to create 3 arrays (2 single D 1 Two D)
        int numOfGenerators = StdIn.readInt();

        int[] generatorValues = new int[numOfGenerators];
        int[][] edges = new int[numOfGenerators][numOfGenerators];
        // this one needs to be a double because of the inputs
        double[] functionalityValues = new double[numOfGenerators];

        for (int i = 0; i<numOfGenerators; i++) {
            generatorValues[i] = StdIn.readInt();
            functionalityValues[i] = StdIn.readDouble();
        }

        for (int i = 0; i < numOfGenerators; i++) {
            for (int j = 0; j < numOfGenerators; j++) {
                double dividor = functionalityValues[i] * functionalityValues[j];
                // because you are dividing by doubles, need to cast it as an int
                edges[i][j] = (int)(StdIn.readInt() / (dividor));
            }
        }
        int[] min = dijkstrasAlgorithm(edges);
        StdOut.print(min[numOfGenerators - 1]);


    	// WRITE YOUR CODE HERE

    }
    // Going to eventually need a way to find out which one is minimum. 

    private static int[] dijkstrasAlgorithm(int[][] edgeValues) {
        int n = edgeValues.length;
        int[] minCost = new int[n];
        boolean[] checked = new boolean[n];


       for (int i = 1; i < minCost.length; i++) {
        minCost[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < minCost.length; i++) {
            int current = findNodeWithMinimumCost(minCost, checked);
            checked[current] = true;

            for (int j = 0; j < n; j++) {
                int sum = minCost[current] + edgeValues[current][j];
                if ((!checked[j] && sum < minCost[j]) && edgeValues[current][j] != 0) {
                    minCost[j] = sum;
                }
            }

        }
        
        return minCost;
    }

    private static int findNodeWithMinimumCost(int[] minCost, boolean[] checked) {
        int min = Integer.MAX_VALUE;
        int minNodeIndex = -1;

        for (int i = 0; i<minCost.length; i++) {
            if ((minCost[i] < min) && !checked[i]) {
                min = minCost[i];
                minNodeIndex = i;
            }
        }
        return minNodeIndex;
    }
    
}
