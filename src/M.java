import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
public class M {
    int len;
    String name;
    M(String name){
        this.name = name;
        this.len = 0;
    }
    class Node{
        int data;
        int pos;
        Node nextNode;
        Node(int data) {
            this.data = data;
            this.pos = len;
            this.nextNode = null;
        }
    }
    public Node first = null;
    public Node last = null;
    void addToEnd(int data) {
        len++;
        Node newNode = new Node(data);
        if (first == null) {
            first = newNode;
            last = newNode;
        }
        else {
            last.nextNode = newNode;
            last = newNode;
        }
    }
    void addInPos(int data, int posToAdd) {
        len++;
        Node current = first;
        int prevData = -1;
        while (current != null) {
            if (current.pos == posToAdd) {
                prevData = current.data;
                current.data = data;
            }
            else if (current.pos > posToAdd) {
                int temp = current.data;
                current.data = prevData;
                prevData = temp;
            }
            if (current.pos == len-1) {
                Node newNode = new Node(prevData);
                last.nextNode = newNode;
                last = newNode;
            }
            current = current.nextNode;
        }
    }
    void del(int posToDel) {
        Node current = first;
        int i = 0;
        len--;
        while (current != null) {
            i++;
            if (i >= posToDel && i <= len) {
                assert current.nextNode != null;
                current.data = current.nextNode.data;
            }
            if (i == len) {
                current.nextNode = null;
                last.data = current.data;
            }
            else {
                current = current.nextNode;
            }
        }
    }
    void show() {
        Node current = first;
        while (current != null) {
            System.out.print("Node " + current.pos + ": " + current.data + " | ");
            current = current.nextNode;
        }
        System.out.println();
    }
    void findAll(int val) {
        Node current = first;
        while (current != null) {
            if (current.data == val) System.out.println(val + " found at pos " + current.pos);
            current = current.nextNode;
        }
    }
    int findFirst(int val) {
        Node current = first;
        while (current != null) {
            if (current.data == val) return current.pos;
            current = current.nextNode;
        }
        return -1;
    }
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int playerChoice = 0;
        String player;
        M[] listsArr = new M[20];
        int lists = 0;
        while (playerChoice != 7) {
            System.out.println("CHOICES: ");
            System.out.println("1: Create a new list");
            System.out.println("2: Show all your lists");
            System.out.println("3: Add value to end of list");
            System.out.println("4: Add value to a specific position of list");
            System.out.println("5: Delete specific position from list");
            System.out.println("6: Find all positions of a specified value in list");
            System.out.println("7: Find first position of a specified value in list");
            System.out.println("8: Show list");
            System.out.println("9: End program");
            System.out.print("Enter choice here: ");
            player = input.next();
            try {
                playerChoice = Integer.parseInt(player);
                int num;
                int pos;
                String l;
                switch(playerChoice) {
                    case 1:
                        if (lists == 20) {
                            System.out.println("You have reached the maximum lists allowed!");
                        }
                        else {
                            System.out.print("What should the name of this list be? ");
                            String n = input.next();
                            listsArr[lists] = new M(n);
                            lists++;
                            System.out.println("Successfully created a new list: " + n);
                        }
                        break;
                    case 2:
                        for (int i = 0; i < lists; i++) {
                            System.out.println("List #" + (i+1) + ": " + listsArr[i].name);
                        }
                        break;
                    case 3:
                        System.out.print("What list should be used? ");
                        l = input.next();
                        if (findIndex(listsArr, l) != -1) {
                            System.out.print("What number should be added: ");
                            num = input.nextInt();
                            listsArr[findIndex(listsArr, l)].addToEnd(num);
                            System.out.println("Value " + num + " added to end of list.");
                        }
                        else {
                            System.out.println("That is not a valid list! Press 2 to see a list of your lists.");
                        }
                        break;
                    case 4:
                        System.out.print("What list should be used? ");
                        l = input.next();
                        if (findIndex(listsArr, l) != -1) {
                            System.out.print("What number should be added: ");
                            num = input.nextInt();
                            System.out.print("At what position? ");
                            pos = input.nextInt();
                            listsArr[findIndex(listsArr, l)].addInPos(num, pos);
                        }
                        else {
                            System.out.println("That is not a valid list! Press 2 to see a list of your lists.");
                        }
                        break;
                    case 5:
                        System.out.print("What list should be used? ");
                        l = input.next();
                        if (findIndex(listsArr, l) != -1) {
                            listsArr[findIndex(listsArr, l)].show();
                            System.out.print("What position should be deleted: ");
                            pos = input.nextInt();
                            listsArr[findIndex(listsArr, l)].del(pos);
                        }
                        else {
                            System.out.println("That is not a valid list! Press 2 to see a list of your lists.");
                        }
                        break;
                    case 6:
                        System.out.print("What list should be used? ");
                        l = input.next();
                        if (findIndex(listsArr, l) != -1) {
                            System.out.print("Enter value to find: ");
                            num = input.nextInt();
                            listsArr[findIndex(listsArr, l)].findAll(num);
                        }
                        else {
                            System.out.println("That is not a valid list! Press 2 to see a list of your lists.");
                        }
                        break;
                    case 7:
                        System.out.print("What list should be used? ");
                        l = input.next();
                        if (findIndex(listsArr, l) != -1) {
                            System.out.print("Enter value to find: ");
                            num = input.nextInt();
                            System.out.println(listsArr[findIndex(listsArr, l)].findFirst(num));
                        }
                        else {
                            System.out.println("That is not a valid list! Press 2 to see a list of your lists.");
                        }
                        break;
                    case 8:
                        System.out.print("What list should be used? ");
                        l = input.next();
                        if (findIndex(listsArr, l) != -1) {
                            listsArr[findIndex(listsArr, l)].show();
                        }
                        else {
                            System.out.println("That is not a valid list! Press 2 to see a list of your lists.");
                        }
                        break;
                    case 9:
                        System.exit(0);
                        break;
                }
                enter();
            }
            catch (NumberFormatException e) {
                System.out.print("That is not a valid input!");
            }
        }
    }
    static void enter() {
        System.out.print("Press ENTER to continue");
        input.nextLine();
        input.nextLine();
    }
    static int findIndex(M[] arr, String name) {
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(arr[i].name, name)) {
                return i;
            }
        }
        return -1;
    }
}
