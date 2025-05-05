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

        // 第一阶段输出
        String result = replaceSameChar(input, true);
        System.out.println("第一阶段输出结果： " + result);

        System.out.println();

        // 第二阶段输出
        result = replaceSameChar(input, false);
        System.out.println("第二阶段输出结果：" + result);
    }

    /**
     * 替换多个连续的字符，并输出最终结果
     *
     * @param str     原字符串
     * @param delFlag 是否删除，true 表示第一阶段，false 表示第二阶段
     * @return 替换后的字符串
     */
    private String replaceSameChar(String str, boolean delFlag) {
        System.out.println("-> " + str);
        char[] charArray = str.toCharArray();

        // ( 递归终止条件一 ) 如果字符串长度小于 3，那么直接返回就行
        if (charArray.length < 3) {
            return str;
        }

//        String regx = "^[a-z]*$";
//        if (!str.matches(regx)) {
//            return "输入字符串不符合正则表达式";
//        }

        // 定义两个指针，i 指针固定在某个索引，然后由 j 指针依次读取，如果读取到的字符与 i 指针所指的字符一致，则计数器 count 加一
        for (int i = 0; i < charArray.length; i++) {
            int count = 0;
            for (int j = i; j < charArray.length; j++) {

                // 双指针指向的元素不一致，直接跳出这个循环
                if (charArray[j] != charArray[i]) {
                    // 若计数器大于 3 ，则进行字符删除或者替换
                    if (count >= 3) {
                        String newStr;
                        // 如果是第一阶段，则删除截取前后字符串即可
                        if (delFlag) {
                            newStr = str.substring(0, i) + str.substring(j);
                        } else {
                            // 如果是第二阶段，就需要截取前后字符串，同时将连续字符替换成前一个字母，组成新的字符串，对于 a 字符要进行置空处理
                            char c = 0;
                            if (charArray[i] != 'a') {
                                c = (char) (charArray[i] - 1);
                            }
                            newStr = str.substring(0, i) + c + str.substring(j);
                        }
                        // 将新字符串转换成新的字符数组继续进行遍历
                        charArray = newStr.toCharArray();
                    }
                    break;
                }

                count += 1;
            }
        }

        // ( 递归终止条件二 ) 如果经过处理的字符串跟输入时的字符串一致，就表示已经没有连续字符了，直接返回
        String newCharStr = String.valueOf(charArray);
        if (newCharStr.equals(str)) {
            return newCharStr;
        }

        // 用递归来进行替换
        return replaceSameChar(newCharStr, delFlag);
    }

}
