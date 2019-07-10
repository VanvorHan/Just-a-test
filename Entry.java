import java.util.*;
public class Entry 
{
    private String key;
    private int values;
    private String  next;
    private String previous;
    private static ArrayList<String> keyList = new ArrayList();;
    private static HashMap<String,ArrayList> map = new HashMap<String,ArrayList>();;
    Set<Map.Entry<String,ArrayList>> entrySet = map.entrySet();
    private static ArrayList<Snapshot> snapshotList = new ArrayList<Snapshot>();
    private static ArrayList<Integer> valueSet = new ArrayList<Integer>();
    private static ArrayList<Integer> setDiff = new ArrayList<Integer>();
    private static ArrayList<Integer> setInter = new ArrayList<Integer>();
    private static ArrayList<Integer> listPrev = new ArrayList<Integer>();
    private static ArrayList<Integer> listNext = new ArrayList<Integer>();
    public Entry()
    {
       key = "";
       values = 0;
       next = "";
       previous = "";
       
    }
    /*
    public void createSnapshot()
    {
        Snapshot snapshot = new Snapshot(AeroDB.snapshotID(AeroDB.idCounter),returnEntries());
    }
    */
    public void clearInter()
    {
        setInter.clear();
        listPrev.clear();
        listNext.clear();
    }
    public void clearSet()
    {
        valueSet.clear();
    }
    public void printkeyList()
    {
        if(keyList.size()>0)
        System.out.println(keyList);
        else
        System.out.println("no keys");
    }
    public void printEntries()
    {
        if(entrySet.size()>0)
        {
            for(Map.Entry<String,ArrayList> entry : entrySet)
           {
              System.out.println(entry.getKey()+" "+entry.getValue()+"");
           }
        }
        else
        System.out.println("no entries");
        
    }
    public String returnEntries()
    {
        String stringSum = "";
        for(Map.Entry<String,ArrayList> entry : entrySet)
           {
              stringSum+=(entry.getKey()+" "+entry.getValue()+"\n");
           }
           return stringSum;
    }
    public void printSnapshots()
    {
        int i = 0;
        if(snapshotList.size()>0)
        {
            while(i<snapshotList.size())
            {
               System.out.println(snapshotList.get(i).getId());
               i++;
            }
        }
        else
        System.out.println("no snapshots");
    }
    /**
    public void addMapInSet(HashMap map)
    {
        if(!entrySet.contains(map))
        entrySet.add(map); 
    }
    **/
    public void addSnapshotInList(Snapshot snapshot)
    {
        snapshotList.add(snapshot);
    }
    public void addKeyInList(String key)
    {
        if(!keyList.contains(key))
        keyList.add(key);
    }
    public void putValuesToList(int values,ArrayList valueList)
    {
        valueList.add(values);
    }
    public void SET(int values,String key)
    {
        
        if(!map.containsKey(key))
        {
            addKeyInList(key);
            ArrayList<Integer> valueList = new ArrayList<Integer>();
            putValuesToList(values,valueList);
            map.put(key,valueList);
        }
        else
        putValuesToList(values,map.get(key));
        
    } 
    
    public String GET(String key)
    {
        if(containKey(key))
        return map.get(key).toString();
        else
        return "no such key";
    }
    
    public boolean containKey(String key)
    {
        return map.get(key)!=null;
    }
    
    public void DEL(String key)
    {
        map.remove(key);
        keyList.remove(key);
    }
    
    public void PUSH(String key,int value)
    {
        int index = map.get(key).indexOf(value);
        map.get(key).add(index-1,value);
        map.get(key).remove(index+1);
    }
    
    public void APPEND(String key, int value)
    {
        map.get(key).add(value);
    }
    
    public void PICK(String key,int index)
    {
        System.out.println(map.get(key).get(index));
    }
    
    public void PLUCK(String key,int index)
    {
        System.out.println(map.get(key).get(index));
        map.get(key).remove(index);
    }
    
    public void POP(String key)
    {
        System.out.println(map.get(key).get(0));
        map.get(key).remove(0);
    }
    
    public void DROP(String id)
    {
        int index = locateObject(snapshotList,id);
        if(index==-1)
        System.out.println("no such snapshot");
        else
        snapshotList.remove(index);
    }
    public void ROLLBACK(String id)
    {
        int index = locateObject(snapshotList,id);
        if(index==-1)
        System.out.println("no such snapshot");
        else
        {
            Snapshot snapshot = new Snapshot(AeroDB.snapshotID(AeroDB.idCounter),returnEntries());
            snapshotList.set(index,snapshot);
        }
    }
    public int locateObject(ArrayList<Snapshot> list,String id)
    {
      
      for(int i = 0; i<list.size();i++)
      {
          if(list.get(i).getId().equals(id))
          return i;
      }
      return -1;
    }
    public int arithmetic(int name,String key)
    {
        if(!containKey(key))
        return -1;
        else
        {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list = map.get(key);
            Iterator<Integer> it = list.iterator();
            int returnValue = 0;
            switch(name)
        {
            case 1:
            // return min value
            returnValue = list.get(0);
            while(it.hasNext())
            {
                int vlaue = it.next();
                if(vlaue<returnValue)
                returnValue = vlaue;
            }
            return returnValue;
            //break;
            
            case 2:
            // return max value
            returnValue = list.get(0);
            while(it.hasNext())
            {
                int vlaue = it.next();
                if(vlaue>returnValue)
                returnValue = vlaue;
            }
            return returnValue;
            //break;
            
            case 3:
            // return sum value
            while(it.hasNext())
            {
                returnValue += it.next();
            }
            return returnValue;
            //break;
            
            case 4:
            // return lenth value
            returnValue = list.size();
            return returnValue;
            //break;
            
            default:
            return -1;
        }
        }
    }
    public void REV(String key)
    {
        if(!containKey(key))
        System.out.println("no such key");
        else
        Collections.reverse(map.get(key));
    }
    public void UNIQ(String key)
    {
        if(!containKey(key))
        System.out.println("no such key");
        else
        {
            int index = 1;
            ArrayList<Integer> list = new ArrayList<Integer>();
            list = map.get(key);
            
            for(;index<list.size();index++)
            {
                if(list.get(index-1)==list.get(index))
                {
                    list.remove(index-1);
                    index--;
                }
                
            }
        }
    }
    public void SORT(String key)
    {
        if(!containKey(key))
        System.out.println("no such key");
        else
        Collections.sort(map.get(key));
    }
    
    public ArrayList UNION(String key)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list = deleteRepeated(key);
        
        for(int value: list)
        {
            if(!valueSet.contains(value))
            valueSet.add(value);
        }
        return valueSet;
    }
    
    public ArrayList DIFF(String key)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list = deleteRepeated(key);
        for(int value: valueSet)
        {
            if(!list.contains(value))
            setDiff.add(value);
        }
        return setDiff;
    }
    
    public ArrayList INTER(String key,int counter)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list = deleteRepeated(key);
        //if(listPrev.size()==0)
        if(counter==1)
        {
            setInter.clear();
            listPrev = list; 
            return null;
        }
        
        else if(counter==2)
        {
            listNext = list;
            /*
            for(int prev:listPrev)
            {
                for(int next:listNext)
                {
                    if(next==prev)
                    {
                        setInter.add(next);
                    }
                }
            }
            */
            for(int i=0;i<listPrev.size();i++)
            {
                for(int j=0;j<listNext.size();i++)
                {
                    
                }
            }
            return setInter;
        }
        else if(counter>2)
        {
            listNext = list;
            for(int value:setInter)
            {
                if(!listNext.contains(value))
                setInter.remove(value);
            }
            return setInter;
        }
        else
        return null;
        
    }
    
    public ArrayList deleteRepeated(String key)
    {
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        list = map.get(key);
        ArrayList<Integer> newList = new ArrayList<Integer>();    
        for(int value: list)
            {
                if(!newList.contains(value))
                {
                    newList.add(value);
                }
                }
        return newList;
        //return map.get(key);
    }
    
}
