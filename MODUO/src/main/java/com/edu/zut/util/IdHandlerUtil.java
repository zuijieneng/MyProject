package com.edu.zut.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IdHandlerUtil {
    public static Integer getTheLastId(List<String> getmessidlist){
        MyCompartor idCompartor=new MyCompartor();
        getmessidlist.sort(idCompartor);
        Collections.reverse(getmessidlist);
        String LastId = getmessidlist.get(0);
        Integer id=Integer.parseInt(LastId.substring(LastId.indexOf("_") + 1))+1;
        return id;
    }
}

class MyCompartor implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return Integer.parseInt(o1.substring(o1.indexOf("_") + 1))-Integer.parseInt(o2.substring(o2.indexOf("_") + 1));
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
}
