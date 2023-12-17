package CK;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    class Node {
        Node[] child;
        boolean isEndWord;
        String meaning;

        Node() {
//            26 char in alphabet and 27th is for <space>
            child = new Node[27];
            isEndWord = false;
            meaning = "";
        }
    }

    private Node root = null;

    public Trie() {
        this.root = new Node();
    }

    public Node getRoot() {
        return root;
    }


    //    insert word en with meaning vi into trie
    public void insert(String en, String vi) {
        Node p = root;
        for (int i = 0; i < en.length(); ++i) {
            int x = charToInt(en.charAt(i));
            if (x < 0 || x >= 27) continue;
            if (p.child[x] == null)
                p.child[x] = new Node();
            p = p.child[x];
        }
        p.isEndWord = true;
        p.meaning = vi;
    }

    //    check string en in dictionary or not
    public boolean checkSpell(String en) {
        Node p = root;
        for (int i = 0; i < en.length(); ++i) {
            int x = charToInt(en.charAt(i));
            if (p.child[x] == null)
                p.child[x] = new Node();
            p = p.child[x];
        }
        return p.isEndWord;
    }

    // Xóa từ từ điển
    public void delete(String en) {
        delete(root, en, 0);
    }

    private boolean delete(Node current, String en, int depth) {
        int childArraySize = current.child.length;
        if (depth == en.length()) {
            if (!current.isEndWord) {
                return false;
            }
            current.isEndWord = false;
            return isNodeEmpty(current);
        }

        int index = charToInt(en.charAt(depth));
        if (current.child[index] == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(current.child[index], en, depth + 1);
        if (shouldDeleteCurrentNode) {
            current.child[index] = null;
            return isNodeEmpty(current);
        }

        return false;
    }

    // Kiểm tra xem nút có nút con hay không
    private boolean isNodeEmpty(Node node) {
        for (Node childNode : node.child) {
            if (childNode != null) {
                return false;
            }
        }
        return true;
    }


    //    return meaning of word en, if not found return "not found"
    public String translate(String en) {
        Node p = root;
        for (int i = 0; i < en.length(); ++i) {
            int x = charToInt(en.charAt(i));
            if (p.child[x] == null)
                p.child[x] = new Node();
            p = p.child[x];
        }
        if (p.isEndWord)
            return p.meaning;
        return "not found";
    }


    //    print all en word in lexicographically order
    public void print() {
        print(this.getRoot(), "");
    }

    private void print(Node curr, String str) {
        if (curr.isEndWord)
            System.out.println(str + ": " + curr.meaning);
        for (int i = 0; i < 27; ++i) {
            if (curr.child[i] != null) {
                char ch = intToChar(i);
                print(curr.child[i], str + ch);
            }
        }
    }

    //    suggest 20 word has common prefix with string prefix
    public List<Pair<String, String>> suggest(String prefix) {
        List<Pair<String, String>> list = new ArrayList<>();
        Node p = endNode(prefix);
        getCandidates(p, prefix, list);
        return list;
    }

    public Node endNode(String prefix) {
        Node p = root;
        for (int i = 0; i < prefix.length(); ++i) {
            int x = charToInt(prefix.charAt(i));
            if (p.child[x] == null)
                p.child[x] = new Node();
            p = p.child[x];
        }
        return p;
    }

    private void getCandidates(Node p, String str, List<Pair<String, String>> list) {
        if (p.isEndWord) {
            list.add(new Pair<>(str, p.meaning));
        }
        if (list.size() > 10)
            return;
        for (int i = 0; i < 27; ++i) {
            if (p.child[i] != null) {
                char ch = intToChar(i);
                getCandidates(p.child[i], str + ch, list);
            }
        }
    }

    private int charToInt(char c) {
        if (c == ' ')
            return 26;
        return (int) (c - 'a');
    }

    private char intToChar(int x) {
        if (x == 26)
            return (char) (' ');
        return (char) (x + 'a');
    }
}