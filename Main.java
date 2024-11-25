public class Main
{
    // Hash tables using separate chaining
    private static final SeparateChainingHashST<Integer, String> dict1 = new SeparateChainingHashST<>(1000, true);
    private static final SeparateChainingHashST<Integer, String> dict2 = new SeparateChainingHashST<>(1000, false);

    // Hash tables using linear probing
    private static final LinearProbingHashST<Integer, String> dict3 = new LinearProbingHashST<>(20000, true);
    private static final LinearProbingHashST<Integer, String> dict4 = new LinearProbingHashST<>(20000, false);

    // Check if the password is strong or not
    public static boolean checkPassword(String password)
    {
        // Check the password length
        if (password.length() < 8) { return false; }

        // Check if password is in dictionary
        if (dict3.contains(password)) { return false; }

        // Check if password is a dictionary word followed by a digit
        String withoutLastChar = password.substring(0, password.length() - 1);
        if ((Character.isDigit(password.charAt(password.length() - 1))) && (dict3.contains(withoutLastChar))){
            return false;
        }

        return true;
    }

    // Display the status of password
    public static void displayPasswordStatus(String password)
    {
        if (checkPassword(password)) StdOut.println("\"" + password + "\" is a strong password");
        else StdOut.println("\"" + password + "\" is not a strong password");
    }

    public static void main (String[] args)
    {
        int i = 0;
        while (!StdIn.isEmpty())
        {
            // Read the words
            String word = StdIn.readLine();

            // Insert words into different types of dictionaries
            dict1.put(++i, word);
            dict2.put(++i, word);
            dict3.put(++i, word);
            dict4.put(++i, word);
        }

        // Display the cost of the search
        StdOut.println("Dictionary 1's cost of search = " + dict1.getComparisons());
        StdOut.println("Dictionary 2's cost of search = " + dict2.getComparisons());
        StdOut.println("Dictionary 3's cost of search = " + dict3.getComparisons());
        StdOut.println("Dictionary 4's cost of search = " + dict4.getComparisons());

        StdOut.println();

        // Display the results of the following passwords
        displayPasswordStatus(args[0]);
        displayPasswordStatus("account8");
        displayPasswordStatus("accountability");
        displayPasswordStatus("9a$D#qW7!uX&Lv3zT");
        displayPasswordStatus("B@k45*W!c$Y7#zR9P");
        displayPasswordStatus("X$8vQ!mW#3Dz&Yr4K5");
    }
}