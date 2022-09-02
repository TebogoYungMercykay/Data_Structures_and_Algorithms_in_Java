public class Relationship {
    public User friendA;
    public User friendB;
    public double friendshipValue;

    @Override
    public String toString() {
        if(friendA.userID < friendB.userID)
            return friendA.toString() + "-(" + friendshipValue + ")->" + friendB.toString(); 
        else
            return friendB.toString() + "-(" + friendshipValue + ")->" + friendA.toString();
    } 

    public Relationship(User friendA, User friendB, double friendshipValue){
        this.friendA = friendA;
        this.friendB = friendB;
        this.friendshipValue = friendshipValue;
    }

    public boolean equals(Relationship relationship){
        if(relationship == null)
            return false;
        if(relationship.friendA == null || relationship.friendB == null)
            return false;

        if(relationship.friendA.userID == friendA.userID && relationship.friendB.userID == friendB.userID)
            return true;
        if(relationship.friendB.userID == friendA.userID && relationship.friendA.userID == friendB.userID)
            return true;
        return false;
    }

    private boolean visit;
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
}
