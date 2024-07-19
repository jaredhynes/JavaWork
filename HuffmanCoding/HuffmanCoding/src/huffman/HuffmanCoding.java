package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;


    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() {
        int[] arr = new int[128];
        sortedCharFreqList = new ArrayList<>();
        double probOcc;
        int differentCharacters = 0;
        int totalReadCharacters = 0;
        StdIn.setFile(fileName);
        while (StdIn.hasNextChar()) {
            char c = StdIn.readChar();
            totalReadCharacters++;
            arr[c]++;
            // Once there is one element in the array, show that there is a different character.
            // arr[c] uses the character as an index. It will change it into its ASCII value.
            if (arr[c] == 1){
                // Different characters is needed to make handle the special case of only 1.
                differentCharacters++;
                }
            }
        if (differentCharacters == 1) {
            int index = 0;
            // Loops through the arr and sees when you hit the index that is the 1
            // distinct character.
            while (arr[index] == 0) {
                index+=1;
            }
            // inc index because we found it at the index where it is not equal to 0.
            index++;
            char fakeOccurence = (char) index;
            sortedCharFreqList.add(new CharFreq(fakeOccurence, 0.00));
        }
        for (int i = 0; i<128; i++) {
            if (arr[i] > 0) {
                char character = (char) i;
                probOcc = (double) arr[i] / totalReadCharacters;
                sortedCharFreqList.add(new CharFreq(character, probOcc));
            }
        }
        Collections.sort(sortedCharFreqList);
        }

	/* Your code goes here */

    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() {
        Queue<TreeNode> source = new Queue<TreeNode>();
        Queue<TreeNode> target = new Queue<TreeNode>();
        for (CharFreq charFreq : sortedCharFreqList) {
            source.enqueue(new TreeNode(charFreq, null, null));
        }
        while (source.size() + target.size() > 1) {
            TreeNode firstNode = getSmallestNode(source, target);
            TreeNode secondNode = getSmallestNode(source, target);
            CharFreq mergedProb = new CharFreq('\0', firstNode.getData().getProbOcc() + secondNode.getData().getProbOcc());
            TreeNode parent = new TreeNode(mergedProb, firstNode, secondNode);
            target.enqueue(parent);
            }
      huffmanRoot = target.peek();
	/* Your code goes here */
    }
    private TreeNode getSmallestNode(Queue<TreeNode> queue1, Queue<TreeNode> queue2) {
        if (queue1.isEmpty()) {
            return queue2.dequeue();
        }
        if (queue2.isEmpty()) {
            return queue1.dequeue();
        }
        return (queue1.peek().getData().getProbOcc() <= queue2.peek().getData().getProbOcc()) ? queue1.dequeue() : queue2.dequeue();
        
    }

    /**
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */
    public void makeEncodings() {
        // No need for a new array, one is already initialized
        encodings = new String[128];
        // Need some way to differentiate between going left using .getLeft and .getRight...

        encodingsHelper(huffmanRoot.getLeft(), null, "0");
        encodingsHelper(huffmanRoot.getRight(), null, "1");
        // System.out.println(Arrays.toString(encodings));
	/* Your code goes here */
    }

    private void encodingsHelper(TreeNode current, Character c, String s) {
        // you don't have a character until you reach a leaf node
        if (c != null) {
            // This line is using the character as the index like before, and setting it equal to the string.
            encodings[(int) c] = s;
            return;
        }
        if (current.getLeft() == null) {
            // This is how you know you are at a leaf node.
            encodingsHelper(current, current.getData().getCharacter(), s);
        }
        else {
            encodingsHelper(current.getLeft(), c, s + "0");
            encodingsHelper(current.getRight(), c, s + "1");
        }
    }

    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) {
        StdIn.setFile(fileName);
        String combined = "";
        // Remember, encodings is a String array where its like [null, null... , 0, 10, null, etc.]
        while (StdIn.hasNextChar()) {
            char nextCharacter = StdIn.readChar();
            combined += encodings[(int) nextCharacter];
        }
        writeBitString(encodedFile, combined);
	/* Your code goes here */
    }
    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) {
        StdOut.setFile(decodedFile);
        // Need a way to keep track of the current node, the string that was already read.
        // Also need to make a string to keep track of what is being decoded in total.
        TreeNode current = huffmanRoot;
        String readString = readBitString(encodedFile);
        String combinedString = "";
        // Need to loop through the entirety of the decoded file string thingy.
        for (int i = 0; i< readString.length(); i++) {
            char character = readString.charAt(i);
            if (character == '0') {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
            if ((current.getLeft() == null)) {
                combinedString += current.getData().getCharacter();
                current = huffmanRoot;
            }
        }
       StdOut.print(combinedString.toString());
	/* Your code goes here */
    }

    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
