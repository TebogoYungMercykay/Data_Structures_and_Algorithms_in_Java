import java.util.ArrayList;

public class GraphDB {
    private ArrayList<User> users = new ArrayList<>();

    public User addUser(String userName, int ID){
        int i = 0;
        while (i < this.users.size())
        {
            if (users.get(i).userID == ID){return this.users.get(i);}
            i++;
        }
        this.users.add(new User(userName, ID));
        User u = this.users.get(i);
        return u;
    }

    public User getUser(int userID){
        int i = 0;
        while (i < this.users.size())
        {
            if (users.get(i).userID == userID){return this.users.get(i);}
            i++;
        }
        User u = null;
        return u;
    }

    public User getUser(String userName){
        int i = 0;
        while (i < this.users.size())
        {
            if (userName == this.users.get(i).userName){return this.users.get(i);}
            i++;
        }
        User u = null;
        return u;
    }

    public Relationship addFriendship(int frienteeID, int friendedID, double relationshipValue){
        Relationship usernull = null;
        if (getUser(frienteeID) == null){return usernull;}
        else if (getUser(friendedID) == null){return usernull;}
        usernull = new Relationship (getUser(frienteeID), getUser(friendedID), relationshipValue);
        User a = getUser(frienteeID), b = getUser(friendedID);
        return a.addFriend(b, relationshipValue);
    }

    public User[][] clusterUsers(){
        
        int i = 0;
        while (i < users.size())
        {
            int c = -121;
            users.get(i).setDeg(0, users.get(i).getFriends().length, c);
            i++;
        }
        Object obj = users.clone();
        ArrayList<User> copy = (ArrayList<User>)obj;
        boolean currState = false;
        i = 0;
        while (currState == copy.isEmpty())
        {
            User u = copy.get(i);
            for (i = i + 1; i < copy.size(); i = i + 1)
                if (u.getDeg()[0] < copy.get(i).getDeg()[0]){u = copy.get(i);}
            i = -1;
            for (i = i + 1; i < copy.size(); i = i + 1)
                if (copy.get(i).getDeg()[1] > u.getDeg()[1])
                    if (u.getDeg()[0] == copy.get(i).getDeg()[0]){u = copy.get(i);}
            u.setDeg(u.getDeg()[0], u.getDeg()[1], 0);

            ArrayList<Integer> colour = new ArrayList<Integer>();
            for (Relationship rU: u.friends)
            {
                User u1 = rU.friendA;
                if (u1 == u)
                    u1 = rU.friendB;
                colour.add(u1.getDeg()[2]);
            }

            while (true)
            {
                if (colour.contains(u.getDeg()[2]) == currState)
                    break;
                u.setDeg(u.getDeg()[0], u.getDeg()[1], u.getDeg()[2] + 1);
            }

            for (Relationship rU: u.friends)
            {
                User u1 = rU.friendA;
                if (u1 == u)
                    u1 = rU.friendB;
                boolean currState1 = false;
                for (Relationship u1RU: u1.friends)
                {
                    User u2 = u1RU.friendA;
                    if (u2 == u1)
                        u2 = u1RU.friendB;
                    // System.out.println(u.userName +"  " + u2.userName);
                    if (u2.getDeg()[2] == u.getDeg()[2] && u != u2){currState1 = true;}
                }
                u1.setDeg(u1.getDeg()[0], u1.getDeg()[1]-1, u1.getDeg()[2]);
                if (!currState1)
                    u1.setDeg(u1.getDeg()[0] + 1, u1.getDeg()[1]-1, u1.getDeg()[2]);
            }
            copy.remove(u);
            i = 0;
        }

        obj = users.clone();
        copy = (ArrayList<User>)obj;
        int size = 0;
        while (currState == copy.isEmpty())
        {
            User u = copy.remove(i);
            if (u.getDeg()[2] > size)
                size = u.getDeg()[2];
        }

        User[][] colourUser = new User[1+size][];

        obj = users.clone();
        copy = (ArrayList<User>)obj;

        
        i = 0;
        while (i <= size)
        {
            ArrayList<User> u = new ArrayList<User>();
            int j = 0;
            while (j < copy.size())
            {
                if (copy.get(j).getDeg()[2] == i)
                    u.add(copy.get(j));
                j++;
            }
            colourUser[i++] = u.toArray(new User[0]);

            int k = 0;
            while (k < colourUser[i-1].length - 1){
                int l = colourUser[i-1].length - 1;
                while (l > k){
                    int m = l - 1;
                    User a = colourUser[i-1][l], b = colourUser[i-1][m];
                    if (a.userID < b.userID){
                        colourUser[i-1][l] = b;
                        colourUser[i-1][m] = a;
                    }
                    l = l - 1;
                }
                k = k + 1;
            }
        }

        return colourUser;
    }

    public Relationship[] minSpanningTree(){
        User u = null;
        int i = 0;
        ArrayList<Relationship> user = new ArrayList<>();
        while (i < users.size())
        {
            u = users.get(i);
            int j = 0;
            while (j < u.friends.size())
            {
                if (!user.contains(u.getFriends()[j])){user.add(u.getFriends()[j]);}
                j++;
            }
            i++;
        }

        /* FROM TEXT BOOK
        public void bubblesort(Object[] data) {
            for (int i = 0; i < data.length-1; i++)
                for (int j = data.length-1; j > i; --j)
                    if (((Comparable)data[j]).compareTo(data[j-1]) < 0)
                        swap(data,j,j-1);
        }
        */
        int s = 1;
        ArrayList<Relationship> f = new ArrayList<>();
        i = 0;
        while (i < user.size() - 1)
        {
            int j = user.size() - 1;
            while (j > i)
            {
                int k = j - 1;
                Relationship a = user.get(j), b = user.get(k);
                if (a.friendshipValue < b.friendshipValue)
                {
                    user.set(j,b);
                    user.set(k,a);
                }
                j = j - 1;
            }
            i = i + 1;
        }

        while (s <= user.size())
        {
            Relationship a = user.remove(s-s+0);
            a.setVisit(true);
            clear();
            if (cycleDetectionDFS(a.friendA, false,null)){a.setVisit(false);}
            else f.add(a);
        }
        return f.toArray(new Relationship[0]);
    }

    public User[] getUsersAtDistance(User fromUser, int distance){
        User u = null;
        int dist = 0;
        for (int i = 0; i < users.size(); i++)
        {
            dist = users.get(i).currDist + 502140;
            u = users.get(i);
            u.currDist = dist;
        }
        ArrayList<User> user = new ArrayList<>();
        dist = -1;
        dist++;
        fromUser.currDist = dist;
        distance(fromUser);
        dist = distance;
        for (int i = 0; i < users.size(); i++)
        {
            dist = users.get(i).currDist;
            u = users.get(i);
            if (dist != distance){}
            else user.add(u);
        }
        return user.toArray(new User[0]);
    }

    protected void distance(User currUser, boolean currState, int i)
    {
        int distance = 0;
        distance++;
        User userFriend = currUser.getFriends()[i].friendB;
        if (userFriend == currUser)
            userFriend = currUser.getFriends()[i].friendA;
        distance += currUser.currDist;
        if (distance < userFriend.currDist)
        {
            userFriend.currDist = distance;
            distance(userFriend);
        }
    }

    protected void distance(User currUser)
    {
        int i = 0;
        while (i < currUser.friends.size())
        {
            User userFriend = currUser.getFriends()[i].friendB;
            if (userFriend == currUser){userFriend = currUser.getFriends()[i].friendA;}
            distance(currUser,true,i);
            i++;
        }
    }

    private boolean cycleDetectionDFS(User v, boolean visit ,User prev)
    {
        v.setVisit(!visit);
        boolean currState;
        int i = 0;
        currState = visit;

        while (i < v.friends.size())
        {
            if (v.getFriends()[i].getVisit())
            {
                User u = v.getFriends()[i].friendA;
                if (u == v){u = v.getFriends()[i].friendB;}

                if (u.getVisit() == visit)
                {
                    currState = cycleDetectionDFS(u, visit,v);
                }
                else if (prev != u)
                {
                    return !visit;
                }
            }
            i++;
        }
        v.setVisit(visit);
        return currState;
    }

    private void clear()
    {
        int i = 0;
        while (i < users.size())
        {
            User u = users.get(i);
            u.clear();
            i++;
        }
    }
}
