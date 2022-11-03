package com.corsarineri.portale.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.corsarineri.portale.model.Poi;

public class MapSorter {
	public static Map<Poi, Integer> sortByValue(Map<Poi, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Poi, Integer>> list =
                new LinkedList<Map.Entry<Poi, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Poi, Integer>>() {
            public int compare(Map.Entry<Poi, Integer> o1,
                               Map.Entry<Poi, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Poi, Integer> sortedMap = new LinkedHashMap<Poi, Integer>();
        for (Map.Entry<Poi, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
