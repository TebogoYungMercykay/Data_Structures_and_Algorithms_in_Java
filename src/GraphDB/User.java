import java.util.ArrayList;

public class User {
    String userName;
    int userID;
    ArrayList<Relationship> friends = new ArrayList<>();

    @Override
    public String toString() {
        return userName + "[" + userID + "]";
    }
    
    public User(String userName, int userID){
        this.userName = userName;
        this.userID = userID;
        int size = 3;
        deg = new int[size];
    }

    public Relationship[] getFriends(){
        return friends.toArray(new Relationship[0]);
    }

    public Relationship addFriend(User friend, double friendshipValue){
        if (friend == null)
            return null;

        Relationship newfriend = new Relationship(this, friend, friendshipValue);
        int i = 0;
        while (i < this.getFriends().length)
        {
            if (newfriend.friendB == this.getFriends()[i].friendB || newfriend.friendB == this.getFriends()[i].friendA){return this.getFriends()[i];}
            i++;
        }
        this.addFriend(newfriend);
        newfriend.friendB.addFriend(newfriend);
        return newfriend;
    }
    private boolean visit;
    public void addFriend(Relationship relationship){
        this.friends.add(relationship);
    }
    int currDist;
    public void setVisit(boolean v)
    {
        this.visit = v;
    }

    public void clear()
    {
        visit = false;
    }

    public boolean getVisit()
    {
        return visit;
    }

    protected int[] deg;
    public void setDeg(int s, int u, int c)
    {
        int i = 0;
        this.deg[i++] = s;
        this.deg[i++] = u;
        this.deg[i] = c;
    }

    public int[] getDeg()
    {
        return this.deg;
    }
}
