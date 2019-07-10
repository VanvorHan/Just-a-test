
import java.util.ArrayList;
import java.util.Scanner;
public class AeroDB {
    public static int idCounter = 0;
    private static final String HELP =
        "BYE   clear database and exit\n"+
        "HELP  display this help message\n"+
        "\n"+
        "LIST KEYS       displays all keys in current state\n"+
        "LIST ENTRIES    displays all entries in current state\n"+
        "LIST SNAPSHOTS  displays all snapshots in the database\n"+
        "\n"+
        "GET <key>    displays entry values\n"+
        "DEL <key>    deletes entry from current state\n"+
        "PURGE <key>  deletes entry from current state and snapshots\n"+
        "\n"+
        "SET <key> <value ...>     sets entry values\n"+
        "PUSH <key> <value ...>    pushes values to the front\n"+
        "APPEND <key> <value ...>  appends values to the back\n"+
        "\n"+
        "PICK <key> <index>   displays value at index\n"+
        "PLUCK <key> <index>  displays and removes value at index\n"+
        "POP <key>            displays and removes the front value\n"+
        "\n"+
        "DROP <id>      deletes snapshot\n"+
        "ROLLBACK <id>  restores to snapshot and deletes newer snapshots\n"+
        "CHECKOUT <id>  replaces current state with a copy of snapshot\n"+
        "SNAPSHOT       saves the current state as a snapshot\n"+
        "\n"+
        "MIN <key>  displays minimum value\n"+
        "MAX <key>  displays maximum value\n"+
        "SUM <key>  displays sum of values\n"+
        "LEN <key>  displays number of values\n"+
        "\n"+
        "REV <key>   reverses order of values\n"+
        "UNIQ <key>  removes repeated adjacent values\n"+
        "SORT <key>  sorts values in ascending order\n"+
        "\n"+
        "DIFF <key> <key ...>   displays set difference of values in keys\n"+
        "INTER <key> <key ...>  displays set intersection of values in keys\n"+
        "UNION <key> <key ...>  displays set union of values in keys"+
        "CARTPROD <key> <key ...>  displays set union of values in keys";
    
    enum commandStr
    {
        HELP,LIST,SET,GET,PURGE,PUSH,APPEND,PICK,PLUCK,POP,DROP,ROLLBACK,CHECKOUT,SNAPSHOT,
        MIN,MAX,SUM,LEN,REV,UNIQ,SORT,DIFF,INTER,UNION,DEL
    }
    public static void bye() {
        System.out.println("bye");
    }
    
    public static void help() {
        System.out.println(HELP);
    }
    
   /** 
   public boolean isHasKey(String key)
    {
        if(keyList.contains(key))                                     
        return true;
    }
   **/
   
    public static void main(String[] args) {
        //Your main method here
        Entry entry = new Entry();
        
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        //split string to array which store splitted command.
        String[] splited = new String[20];
        splited = command.split("\\s+");
        
        int counter = 0;int value = 0;int index = 0;
        boolean verifyKeys = true;
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        while(!splited[0].equalsIgnoreCase("BYE")){
            switch(commandStr.valueOf(splited[0])){
            
            case HELP:
            help();
            break;
            
            
            case LIST:
            if(splited[1].equalsIgnoreCase("KEYS"))
            entry.printkeyList();
            if(splited[1].equalsIgnoreCase("ENTRIES"))
            entry.printEntries();
            if(splited[1].equalsIgnoreCase("SNAPSHOTS"))
            entry.printSnapshots();
            break;
            
            case SET:
            //ArrayList<String> array= new ArrayList();
            for(int i = 2 ; i < splited.length;i++)
            
            {
                value = Integer.parseInt(splited[i]);
                entry.SET(value,splited[1]);
            }
            if(splited[0].equals("SET"))
            System.out.println("ok");
            break;
            
            case GET:
            System.out.println(entry.GET(splited[1]));
            break;
            //entry.testPrint(splited[1]);
            
            case DEL:
            entry.DEL(splited[1]);
            break;
            
            case PUSH:
            value = Integer.parseInt(splited[2]);
            entry.PUSH(splited[1],value);
            break;
            
            case APPEND:
            for(int i = 2 ; i < splited.length;i++)
            
            {
                value = Integer.parseInt(splited[i]);
                entry.APPEND(splited[1],value);
            }
            break;
            
            case PICK:
            index = Integer.parseInt(splited[2]);
            entry.PICK(splited[1],index);
            break;
            
            case PLUCK:
            index = Integer.parseInt(splited[2]);
            entry.PLUCK(splited[1],index);
            break;
            
            case POP:
            entry.POP(splited[1]);
            break;
            
            case SNAPSHOT:
            idCounter++;
            Snapshot snapshot = new Snapshot(snapshotID(idCounter),entry.returnEntries());
            //snapshot(id,entries)
            System.out.println("save as "+snapshotID(idCounter));
            entry.addSnapshotInList(snapshot);
            break;
            
            case DROP:
            entry.DROP(splited[1]);
            break;
            
            case ROLLBACK:
            entry.ROLLBACK(splited[1]);
            break;
            
            case MIN:
            useArithmetic(1,splited[1],entry);
            break;
            
            case MAX:
            useArithmetic(2,splited[1],entry);
            break;
            
            case SUM:
            useArithmetic(3,splited[1],entry);
            break;
            
            case LEN:
            useArithmetic(4,splited[1],entry);
            break;
            
            case REV:
            entry.REV(splited[1]);
            break;
            
            case UNIQ:
            entry.UNIQ(splited[1]);
            break;
            
            case SORT:
            entry.SORT(splited[1]);
            break;
            
            
            case INTER:
            /*
            if(valueSet(list,splited,verifyKeys,entry)==null)
            {
                System.out.println("no such keys");
            }
            else
            
            {*/
                for(int i = 1 ; i < splited.length;i++)
              {
                 if(splited.length==2)
                 {
                     System.out.println("Please type at least 2 keys.");
                     break;
                 }
                 /*
                 if(entry.INTER(splited[splited.length-1],splited.length-1)==null)
                 {
                     System.out.println("Plase Check your command.");
                     break;
                 }
                 */
                 if(entry.INTER(splited[1],1)!=null)
                 //continue;
                 
                 list = entry.INTER(splited[i],i);
                 
                 //if(i==splited.length-1)
                 System.out.println(list);  
                 //list = entry.INTER(splited[i],i);
              }
              
              //entry.clearSet();
              //entry.clearInter();
              list.clear();
              break;
            //}
            
            
            case DIFF:
            
            /*
            if(valueSet(list,splited,verifyKeys,entry)==null)
            System.out.println("no such keys");
            else
            {
                list = valueSet(list,splited,verifyKeys,entry);
                list.clear();
                for(int i = 1 ; i < splited.length;i++)
                {
                   entry.DIFF(splited[i]);
                   if(i==splited.length-1)
                   list = entry.DIFF(splited[i]);
                }
                System.out.println(list);
                entry.clearSet();
                list.clear();
            }
            */
            
            break;
            
            
            case UNION:
            //ArrayList<Integer> list = new ArrayList<Integer>();
            if(valueSet(list,splited,verifyKeys,entry)==null)
            System.out.println("no such keys");
            else
            {
                list=valueSet(list,splited,verifyKeys,entry);
                System.out.println(list);
                entry.clearSet();
                list.clear();
            }
            
            break;
            
            default:
            System.out.println("Please type in a correct command,or you can type \"BYE\"to quit this.");
            break;
        
        }
        command = in.nextLine();
        splited = command.split("\\s+");
        if(command.equals("BYE"))
        {
            bye();
            break;
        }
        }
        
    }
    public static ArrayList valueSet(ArrayList list,String[] splited,boolean verifyKeys,Entry entry)
    {
        for(int i = 1 ; i < splited.length;i++)
            {
                if(!entry.containKey(splited[i]))
                {
                    verifyKeys = false;
                }
            }
            if(verifyKeys)
            {
                for(int i = 1 ; i < splited.length;i++)
              {
                 entry.UNION(splited[i]);
                 if(i==splited.length-1)
                 list = entry.UNION(splited[i]);
              }
              return list;
            }
            else
            return null;
    }
    public static String snapshotID(int counter)
    {
        return String.valueOf(counter);
    }
    
    public static void useArithmetic(int name,String key,Entry entry)
    {
        //if(entry.arithmetic(name,key)<0)
        //System.out.println("no such key");
        //Xelse
        System.out.println(entry.arithmetic(name,key));
    }
}
