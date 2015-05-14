/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject.entity.items;

import edu.chl.Game.model.gameobject.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Rasmus
 */
public class ItemMap implements Map{
    
    private ArrayList keys;
    private ArrayList values;
    
    public ItemMap(){
        keys = new ArrayList();
        values = new ArrayList<Item>();
    }

    @Override
    public int size() {
       return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.contains(value);
    }

    @Override
    public Object get(Object key) {
        int i = keys.indexOf(key);
        if (i == -1){
         return null;   
        }
        
        return values.get(i);
    }

    @Override
    public Object put(Object key, Object value) {
        //temporary
        
      for (int i = 0; i < keys.size(); i++) {
          Object old = keys.get(i);

          /* Does the key already exist? */
          if (((Comparable) key).compareTo(keys.get(i)) == 0) {
            keys.set(i, value);
            return old;
          }

          /*
           * Did we just go past where to put it? i.e., keep keys in sorted order.
           */
          if (((Comparable) key).compareTo(keys.get(i)) == +1) {
            int where = i > 0 ? i - 1 : 0;
            keys.add(where, key);
            values.add(where, value);
            return null;
          }
        }

        // Else it goes at the end.
        keys.add(key);
        values.add(value);
        return null;
    }

    @Override
    public Object remove(Object key) {
        int i = keys.indexOf(key);
        if (i == -1){
          return null;
        }
        Object old = values.get(i);
        keys.remove(i);
        values.remove(i);
        return old;
    }

    @Override
    public void putAll(Map m) {
        Iterator keysIter = m.keySet().iterator();
        while (keysIter.hasNext()) {
          Object k = keysIter.next();
          Object v = m.get(k);
          put(k, v);
        }
    }

    @Override
    public void clear() {
        values.clear();
        keys.clear();
    }

    @Override
    public Set keySet() {
        return new TreeSet(keys);
    }

    @Override
    public Collection values() {
        return values;
    }

    @Override
    public Set entrySet() {
        return this.entrySet();
    }
    
}
