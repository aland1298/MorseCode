package edu.ilstu;

import java.util.Objects;

public class MorseTree {
    private Node root = new Node();

    /**
     * Adds a character to the tree
     *
     * @param data - the data
     * @param code - the morse code
     */
    public void addChar(char data, String code) {
        addCharHelper(root, data, code);
    }

    /**
     * Recursively finds the next tree to add a node
     */
    private void addCharHelper(Node current, char data, String code) {
        if(code.equals("")) return;

        switch (code.charAt(0)) {
            // Goes left
            case '•' -> {
                if(current.getLeft() != null) {
                    addCharHelper(
                            current.getLeft().root,
                            data,
                            code.substring(1)
                    );
                }
                else {
                    current.setLeft(new MorseTree());
                    current.getLeft().root.setData(data);
                }
            }
            // Goes right
            case '-' -> {
                if(current.getRight() != null) {
                    addCharHelper(
                            current.getRight().root,
                            data,
                            code.substring(1)
                    );
                }
                else {
                    current.setRight(new MorseTree());
                    current.getRight().root.setData(data);
                }
            }
        }
    }

    /**
     * Decodes the given morse code into human readable text
     *
     * @param code - the word in morse code
     * @return string - the decoded morse code
     */
    public String decode(String code) {
        String[] s = code.split(" ");

        StringBuilder word = new StringBuilder();
        for(String e: s) {
            word.append(decodeHelper(e, root));
        }

        return word.toString();
    }

    /**
     * Finds the character in the tree from the morse code given for that character
     *
     * @param code - the morse code
     * @param node - the parent node of the tree
     * @return char - the char found
     */
    private char decodeHelper(String code, Node node) {
        if(code.equals("")) return node.getData();

        switch (code.charAt(0)) {
            // Go left
            case '•' -> {
                return decodeHelper(code.substring(1), node.getLeft().root);
            }
            // Go right
            case '-' -> {
                return decodeHelper(code.substring(1), node.getRight().root);
            }
        }

        return ' ';
    }

    public static class Node {
        private char data;
        private MorseTree left;
        private MorseTree right;

        /**
         * Default constructor initializes local private variables to default
         * values
         */
        public Node() {
            setData(' ');
            setLeft(null);
            setRight(null);
        }

        /**
         * Gets the data of this node.
         *
         * @return data - the data of this node
         */
        public char getData() {
            return data;
        }

        /**
         * Sets the data of this node.
         *
         * @param data - the data to set
         */
        public void setData(char data) {
            this.data = data;
        }

        /**
         * Gets the left tree
         *
         * @return left - the left tree
         */
        public MorseTree getLeft() {
            return left;
        }

        /**
         * Sets the left tree
         *
         * @param left - the left tree
         */
        public void setLeft(MorseTree left) {
            this.left = left;
        }

        /**
         * Gets the right tree
         *
         * @return right - the right tree
         */
        public MorseTree getRight() {
            return right;
        }

        /**
         * Sets the right tree
         *
         * @param right - the right tree
         */
        public void setRight(MorseTree right) {
            this.right = right;
        }
    }
}
