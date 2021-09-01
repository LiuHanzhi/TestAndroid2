package com.example.tttt;

public class SubStringTest {


    public final static String str1_defualt = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB";

    public final static String str2_oaep = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDF5Mjuy12okNWjOeEihTIT5pUBWXIlkJcPkKi7NXCsfWb+QaL2Ki5JoFuFyh0LhlBJBNWY+rS0j/G3AqGT7azuVWLteNLI26b09Gtt88UvYAdv//6W6/xpZ6pGS+kv8a3arC9XGHIGnjPQMhJYn6o7EUT2+AT2A2SniUf0c8KFLwIDAQAB";

    public static void main(String[] args) {
        split(str1_defualt);
        split(str2_oaep);
    }

    public static void split(String str) {
        System.out.println(str.length());
        int mode = str.length() / 3;
        for (int i = 0; i < 3; i++) {
            int start = mode * i;
            int end = mode * (i + 1);
            if (end > str.length()) {
                end = str.length();
            }
            System.out.println(str.substring(start, end));
        }
    }
}
