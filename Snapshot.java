public class Snapshot {
    private String id;
    private String entries;
    //private ArrayList<String> list;
    //private HashMap<String,ArrayList> map;
    //private ArrayList<String> KeyList;
    //private Set<Map.Entry<String,ArrayList>> entrySet;
    //consurtor 
    public Snapshot()
    {
       id = "";
       entries = "";
       //ArrayList<String> keyList = new ArrayList();
       //HashMap<String,ArrayList> map = new HashMap<String,ArrayList>();
       //Set<Map.Entry<String,ArrayList>> entrySet = map.entrySet();
    }
    
    public Snapshot(String id,String entries)
    {
        this.id = id;
        this.entries = entries;
    }
    
    //public void set
    //id
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getId()
    {
        return this.id;
    }
    
    public void setEntries(String entries)
    {
        this.entries = entries;
    }
    
    public String getEntries()
    {
        return this.entries;
    }
}