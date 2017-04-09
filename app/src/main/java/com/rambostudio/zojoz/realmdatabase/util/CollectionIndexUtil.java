package com.rambostudio.zojoz.realmdatabase.util;

import java.util.Collection;
import java.util.List;

/**
 * Created by rambo on 9/4/2560.
 */

public class CollectionIndexUtil {
    //prevent arrayindexoutofbounds exception
    public static boolean isAvailableData(Collection<?> list, int holderAdapterPosition) {
        return null != list && !list.isEmpty() && holderAdapterPosition >= 0 && list.size() > holderAdapterPosition;
    }

    public static int getSize(List<?> list){
        int size = 0;
        if(null != list && !list.isEmpty()){
            size = list.size();
        }
        return size;
    }
}
