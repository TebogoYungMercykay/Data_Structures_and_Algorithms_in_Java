import java.util.Random;

// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
// SkipList class
public class SkipList<T extends Comparable<T>> {

    private int maxLevel;
    private SkipListNode<T>[] root;
    private int[] powers;
    // Do not change the seed. This is used to generate the same values every run
    private Random randomGenerator = new Random(123456);

    public SkipList(int maxLevel){
        this.maxLevel = maxLevel;
        // Initialising the root array.
        this.root = new SkipListNode[this.maxLevel];
        for(int i_counter = 0; i_counter < this.maxLevel; i_counter += 1){
            this.root[i_counter] = null;
        }
        // Initialising the powers array
        this.powers = new int[this.maxLevel];
        this.powers[this.maxLevel - 1] = (2 << (this.maxLevel - 1)) - 1;
		for (int i_counter = this.maxLevel - 2, j_counter = 0; i_counter > -1; i_counter -= 1, j_counter += 1){
			this.powers[i_counter] = this.powers[i_counter + 1] - (2 << j_counter);
        }
    }

    public int chooseLevel(){
        int i_counter = 1;
        int randomNumber = Math.abs(this.randomGenerator.nextInt()) % this.powers[this.maxLevel - 1] + 1;

        for( ; i_counter < this.maxLevel; i_counter += 1){
            if(randomNumber < this.powers[i_counter]){
                return i_counter - 1;
            }
        }
        return i_counter - 1;
    }

    public boolean isEmpty(){
        return (this.root[0] == null);
    }

    public void insert(T key){
        SkipListNode<T>[] tempCurrentNode = new SkipListNode[this.maxLevel];
		SkipListNode<T>[] tempPreviousNode = new SkipListNode[this.maxLevel];
		tempCurrentNode[this.maxLevel - 1] = this.root[this.maxLevel - 1];
		tempPreviousNode[this.maxLevel - 1] = null;

        // Preparing poiters for the new node's insertion process
		for(int i_counter = this.maxLevel - 1; i_counter > -1; i_counter -= 1){
            // Runs for as long as the current key we on is strictly less of equal to the new key
			while(tempCurrentNode[i_counter] != null && tempCurrentNode[i_counter].key.compareTo(key) <= 0){
				if(true){
					tempPreviousNode[i_counter] = tempCurrentNode[i_counter];
					tempCurrentNode[i_counter] = tempCurrentNode[i_counter].next[i_counter];
                }
			}
            // Updating prev and current nodes
			if(i_counter > 0){
				if(tempPreviousNode[i_counter] == null){
					tempCurrentNode[i_counter - 1] = this.root[i_counter - 1];
					tempPreviousNode[i_counter - 1] = null;
				}
                else{
                    tempCurrentNode[i_counter - 1] = tempPreviousNode[i_counter].next[i_counter - 1];
                    tempPreviousNode[i_counter - 1] = tempPreviousNode[i_counter];
                }
            }
		}

        // Inserting the new Node.
		int insertLevel = chooseLevel();
		SkipListNode<T> insertedNode = new SkipListNode<>(key, insertLevel + 1);

		for(int i_counter = 1; i_counter <= (insertLevel+1); i_counter += 1){
			insertedNode.next[i_counter - 1] = tempCurrentNode[i_counter - 1];

			if(tempPreviousNode[i_counter - 1] == null){
				this.root[i_counter - 1] = insertedNode;
            }
			else{
				tempPreviousNode[i_counter - 1].next[i_counter - 1] = insertedNode;
			}
		}
    }

    public SkipListNode<T> search(T key){
        int level = 0;
        // Finding the first root node which is not null
		for(level = this.maxLevel - 1; level >= 0 && this.root[level] == null; level -= 1);
		SkipListNode<T> tempCurrentNode  = this.root[level];
		SkipListNode<T> tempPreviousNode  = this.root[level];

		while(true){
			if(tempCurrentNode.key.compareTo(key) == 0){
                return tempCurrentNode;
            }
			else if(key.compareTo(tempCurrentNode.key) < 0){
                // Searching from the left hand side
				if(level == 0){
					return null;
				}
				else if(tempCurrentNode == (this.root[level])){
					tempCurrentNode = this.root[--level];
				}
				else{
					tempCurrentNode = tempPreviousNode.next[--level];
				}
			}
			else{
                // Searching from the right hand side.
                tempPreviousNode = tempCurrentNode;
                if(tempCurrentNode.next[level] != null){
                    tempCurrentNode = tempCurrentNode.next[level];
                }
                else{
                    for(level--; level >= 0 && tempCurrentNode.next[level] == null; level -= 1);

                    if(level >= 0){
                        tempCurrentNode = tempCurrentNode.next[level];
                    }
                    else{
                        return null;
                    }
                }
            }
		}
    }

    @Override
    public String toString(){
        // Extracting the num of nodes on each level using a helper function...
		String levels[] = new String[this.maxLevel];
        for(int i_counter = 0; i_counter < levels.length; i_counter +=1){
            levels[i_counter] = "[Lvl " + i_counter + "]";
        }
        // Creating the full string for the SkipList object
        for(int i_counter = 0; i_counter < 1; i_counter += 1){
            SkipListNode<T> ptr = this.root[i_counter];
            while(ptr != null){
                for(int j_counter = 0; j_counter < this.maxLevel && ptr != null; j_counter += 1){
                    if(j_counter < ptr.next.length){
						levels[j_counter] += "->" + ptr.toString();
                    }
                    else{
                        levels[j_counter] += "--" + ptr.emptyString();
                    }
                }
                ptr = ptr.next[i_counter];
            }
        }
		// Removing the excess characters
		for(int i_counter = 0; i_counter < this.maxLevel; i_counter += 1){
            String tempStr1 = levels[i_counter];
            for(int kounter = tempStr1.length() - 1; kounter >= 0; kounter--){
				if(tempStr1.charAt(kounter) == ']'){
					levels[i_counter] = tempStr1.substring(0, kounter+1);
					break;
				}
			}
        }
		// Concatenation
        String tempStr = "";
        for(int i_counter = this.maxLevel - 1; i_counter >= 0; i_counter -= 1){
            tempStr += levels[i_counter];
            if(i_counter > 0){
                tempStr += "\n";
            }
        }
        return tempStr;
    }

    public boolean delete(T key){
        if(this.root == null || this.root[0] == null){
            return false;
        }
        SkipListNode<T> searchNode = this.search(key);
        if(searchNode == null){
            return false;
        }
        else{
            for(int i_counter = 0; i_counter < this.maxLevel; i_counter += 1){
                SkipListNode<T> tempCurrentNode = this.root[i_counter], tempPreviousNode = null;
                while (tempCurrentNode != null && tempCurrentNode != searchNode){
                    tempPreviousNode = tempCurrentNode;
                    tempCurrentNode = tempCurrentNode.next[i_counter];
                }
                if(tempPreviousNode == null){
                    if(tempCurrentNode != null){
                        this.root[i_counter] = tempCurrentNode.next[i_counter];
                    }
                    else{
                        this.root[i_counter] = tempCurrentNode;
                    }
                }
                else{
                    if(tempCurrentNode != null){
                        tempPreviousNode.next[i_counter] = tempCurrentNode.next[i_counter];
                    }
                    else{
                        tempPreviousNode.next[i_counter] = tempCurrentNode;
                    }
                }
            }
        }
        return true;
    }

    public void printSearchPath(T key){
        if(this.root == null || this.root[0] == null){
            return;
        }
        SkipListNode<T> searchNode = this.search(key);
        if(searchNode == null){
            return;
        }
        else{
            int level = 0;
            // Finding the first root node which is not null
            for(level = this.maxLevel - 1; level >= 0 && this.root[level] == null; level -= 1);
            SkipListNode<T> tempCurrentNode  = this.root[level];
            SkipListNode<T> tempPreviousNode  = this.root[level];
            String tempPath = "";

            tempPath += tempCurrentNode.toString();
            while(true){
                if(tempCurrentNode.key.compareTo(key) == 0){
                    System.out.println(tempPath);
                    return;
                }
                else if(key.compareTo(tempCurrentNode.key) < 0){
                    // Searching from the left hand side
                    if(level == 0){
                        return;
                    }
                    else if(tempCurrentNode == (this.root[level])){
                        tempCurrentNode = this.root[--level];
                        tempPath += tempCurrentNode.toString();
                    }
                    else{
                        tempCurrentNode = tempPreviousNode.next[--level];
                        tempPath += tempCurrentNode.toString();
                    }
                }
                else{
                    // Searching from the right hand side.
                    tempPreviousNode = tempCurrentNode;
                    if(tempCurrentNode.next[level] != null){
                        tempCurrentNode = tempCurrentNode.next[level];
                        tempPath += tempCurrentNode.toString();
                    }
                    else{
                        for(level--;level >= 0 && tempCurrentNode.next[level] == null; level -= 1);
                        if(level >= 0){
                            tempCurrentNode = tempCurrentNode.next[level];
                            tempPath += tempCurrentNode.toString();
                        }
                        else{
                            return;
                        }
                    }
                }
		    }
        }
    }
}