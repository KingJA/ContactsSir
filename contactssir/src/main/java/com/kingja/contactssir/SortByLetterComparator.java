package com.kingja.contactssir;

import java.util.Comparator;

/**
 * Description:TODO
 * Create Time:2017/4/25 15:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SortByLetterComparator implements Comparator<FirstLetter> {
    @Override
    public int compare(FirstLetter o1, FirstLetter o2) {
        return o1.getFirstLetter().compareTo(o2.getFirstLetter());
    }
}
