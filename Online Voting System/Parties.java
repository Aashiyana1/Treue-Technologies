import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Parties {
    public static final int GREEN_PARTY=0;
    public static final int JUSTICE_PARTY=1;
    public static final int NEW_PARTY=2;
    public static final int OLD_PARTY=3;

    public static final Map<Integer,Integer> partyMap=new HashMap<>();
    static{
        partyMap.put(GREEN_PARTY, 0);
        partyMap.put(JUSTICE_PARTY, 0);
        partyMap.put(NEW_PARTY, 0);
        partyMap.put(OLD_PARTY, 0);

    }

  
    public static final Map<String,String> voterList=new HashMap<>();

   
}
