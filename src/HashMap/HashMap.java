public class HashMap<T, U> {
    private Object[] keyArray;
    private Object[] valueArray;
    private Object[] keyCellar;
    private Object[] valueCellar;

    public HashMap(int ArraySize, int CellarSize){
    }

    public boolean put(T key, U value){
        Integer index = hash(key);
       node curr = table[index];
        if(curr==null) {
           table[index] = new node(value, key);
        }
        else {
            while(curr.next!=null) {
                if(curr.key==key) {
                    break;
                }
                curr = curr.next;
            }
            if(curr.key==key)
                curr.value=value;
            else {
                curr.next = new node(value, key);
                curr.next.prev = curr;
            }
        }
        double itotal = 0;
        for(node pass : table) {
            itotal += CountChain(pass);
        }
        itotal = itotal/capacity;
        
        if(itotal>loadFactor) {
            DynamicHashMap hold = new DynamicHashMap(capacity*2, loadFactor);
            for(node pass: table) {
                curr = pass;
                while(curr!=null) {
                    hold.put(curr.key, curr.value);
                    curr = curr.next;
                }
            }
            this.table = hold.table;
            capacity = capacity*2;
        }
        
        for(node iter: table) {
            node hold=iter;
            while(hold!=null) {
                if(hold.value==value) {
                    if(hold.prev==null) {
                        return null;
                    }
                    else {
                        return hold.prev.value;
                    }
                }
                hold = hold.next;
            }
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public U get(T key){
        Integer index  = hash(key);
       node curr = table[index];
       
       while(curr!=null) {
           if(curr.key==key)
               return curr.value;
           else
               curr = curr.next;
       }
       return null;
    }

    @SuppressWarnings("unchecked")
    // public HashMap<T,U> rehash(int ArraySize, int CellarSize){
        
    // }

    // public int arrayHash(T key){
    
    // }

    // public int cellarHash(T key){
    
    // }

    // // (i , cellarHash , keyCellar size ) 
    // public static int linearProbing(int i, int hashValue, int modVal){

    // }

    // public static int quadraticProbing(int i, int hashValue, int modVal){
        
    // }

    // public int count(){

    // }

    // public boolean isContained(T key, U value){
        
    // }

    public Object[] getKeyArray(){
        return keyArray;
    }

    public Object[] getKeyCellar(){
        return keyCellar;
    }

    public Object[] getValueArray(){
        return valueArray;
    }

    public Object[] getValueCellar(){
        return valueCellar;
    }

    //Its not advised to change this *wink wink*
    private int convertTtoInt(T key){
        if(key == null)
            return -1;
        return key.hashCode();
    }
}
