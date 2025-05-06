package com.aiden;

import org.junit.jupiter.api.Test;

/**
 * @author aiden
 * @create 2025-05-05 14:22
 * @description
 */
public class DemoTest {

    /**
     * For a given string that only contains alphabet characters a-z, if 3 or more consecutive characters are identical,
     * remove them from the string. Repeat this process until there is no more than 3 identical characters sitting besides
     * each other. Example:
     * Input:   aabcccbbad
     * Output:
     *          -> aabbbad
     *          -> aaad
     *          -> d
     * <p>
     * # Stage 2 - advanced requirement
     * Instead of removing the consecutively identical characters, replace them with a single character that comes before
     * it alphabetically.
     * Example:
     *          ccc -> b
     *          bbb -> a
     * Input:   abcccbad
     * Output:
     *          -> abbbad,      ccc is replaced by b
     *          -> aaad,        bbb is replaced by a
     *          -> d
     */
    @Test
    public void test() {
//        String input = "abcdefghijklmnopqrstuvwxyz";
        String input = "aabccccbbad";

        // The first stage outputs the results
        String result = replaceSameChar(input, true);
        System.out.println("The first stage outputs the results： " + result);

        System.out.println();

        // The second stage outputs the results
        result = replaceSameChar(input, false);
        System.out.println("The second stage outputs the results：" + result);
    }

    /**
     * Replace multiple consecutive characters and output the final result
     *
     * @param str     Original string
     * @param delFlag Whether to delete or not, true indicates the first stage and false indicates the second stage
     * @return Replaced string
     */
    public String replaceSameChar(String str, boolean delFlag) {
        System.out.println("-> " + str);

        String regx = "^[a-z]*$";
        if (!str.matches(regx)) {
            return "The input string contains characters that are not lowercase letters, please re-enter them!";
        }

        char[] charArray = str.toCharArray();

        // ( Recursive Termination Condition 1 ) If the string length is less than 3, then it can be returned directly
        if (charArray.length < 3) {
            return str;
        }

        // Define two pointers, the i pointer is fixed to a certain index, and then read by the j pointer in turn,
        // and if the read character matches the character pointed by the i pointer, the counter count is added by one.
        for (int i = 0; i < charArray.length; i++) {
            int count = 0;
            for (int j = i; j < charArray.length; j++) {

                // The elements pointed to by the two pointers are inconsistent, jumping out of the loop
                if (charArray[j] != charArray[i]) {
                    // If the counter is greater than 3, the characters are removed or replaced
                    if (count >= 3) {
                        String newStr;
                        // If it is the first stage, it is sufficient to delete the strings before and after the interception
                        if (delFlag) {
                            newStr = str.substring(0, i) + str.substring(j);
                        } else {
                            // In the second stage, you need to truncate the string before and after, and replace the consecutive
                            // characters with the previous letter to form a new string, and the A character needs to be left blank
                            char c = 0;
                            if (charArray[i] != 'a') {
                                c = (char) (charArray[i] - 1);
                            }
                            newStr = str.substring(0, i) + c + str.substring(j);
                        }
                        // Convert the new string to a new character array to continue traversing
                        charArray = newStr.toCharArray();
                    }
                    break;
                }

                count += 1;
            }
        }

        // ( Recursive Termination Condition 2 ) If the processed string is the same as the input string,
        // it means that there are no consecutive characters and is returned directly
        String newCharStr = String.valueOf(charArray);
        if (newCharStr.equals(str)) {
            return newCharStr;
        }

        // Replace with recursion
        return replaceSameChar(newCharStr, delFlag);
    }

}
