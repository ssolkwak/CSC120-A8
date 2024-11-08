
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a Person which implements the interface Contract
 */
class Person implements Contract{

    private String name; 
    private Number ballRadius;
    private int xPosition;
    private int yPosition;
    ArrayList<String> inventory;
    SortedMap<String, String> actionHistory;
    
    /**
     * Constructor
     * @param name name of the person
     * @param radius the radius of the ball the person has
     */
    public Person(String name, Number radius){
        this.name = name;
        if (radius.doubleValue() >= 0){
            this.ballRadius = radius;
        } else {
            throw new RuntimeException("The radius cannot be negative.");
        }
        this.xPosition = 0;
        this.yPosition = 0;
        this.inventory = new ArrayList<>();
        this.actionHistory = new TreeMap<>();
    }
    
    /**
     * The person adds the item to their inventory
     */
    public void grab(String item){
        this.inventory.add(item);
        System.out.println(this.name + " grabbed " + item);
        this.actionHistory.put("grab", item);
    }

    /**
     * The person drops the item from their inventory
     */
    public String drop(String item){
        if(this.inventory.contains(item)){
            this.inventory.remove(item);
            System.out.println(this.name + " dropped " + item);
            this.actionHistory.put("drop", item);
            return item;
        } else {
            throw new RuntimeException("The item is not in the invetory.");
        }
        
    }
    
    /**
     * Prints a statement that the item is examined by the person
     */
    public void examine(String item){
        System.out.println(this.name + " examined " + item);
        this.actionHistory.put("examine", item);
    }
    
    /**
     * Prints the statement that the item is used by the person
     */
    public void use(String item){
        System.out.println(this.name + " examined " + item);
        this.actionHistory.put("examine", item);
    }
    
    /**
     * The person's position on xy coordinate change in the directions parallel to the x and y axis
     */
    public boolean walk(String direction){
        if (direction.toLowerCase().equals("east")){
            this.xPosition += 1;
        } else if (direction.toLowerCase().equals("west")){
            this.xPosition -= 1;
        } else if (direction.toLowerCase().equals("north")){
            this.yPosition += 1;
        } else if (direction.toLowerCase().equals("south")){
            this.yPosition -= 1;
        } else {
            throw new RuntimeException("This direction is invalid.");
        }
        this.actionHistory.put("walk", direction);
        return true;
    }

    /**
     * The person's position on xy coordinate change in any directions even if not parallel to the x and y axis
     */
    public boolean fly(int x, int y){
        this.xPosition += x;
        this.yPosition += y;
        this.actionHistory.put("fly", x + " " + y);
        return true;
    }
    
    /**
     * The radius of the person's ball is decreased 
     */
    public Number shrink(){
        if (this.ballRadius.doubleValue() >= 0.1){
            this.ballRadius.equals(ballRadius.doubleValue() - 0.1);
            this.actionHistory.put("shrink", null);
            return this.ballRadius;
        } else {
            throw new RuntimeException("The ball cannot be shrunken more.");
        }
    }
    
    /** 
     * The radius of the person's ball is increased
     */
    public Number grow(){
        this.ballRadius.equals(ballRadius.doubleValue() + 0.1);
        this.actionHistory.put("grow", null);
        return this.ballRadius;
    }
    
    /**
     * Prints the statement that the person rested
     */
    public void rest(){
        System.out.println(this.name + " rested ");
        this.actionHistory.put("rest", null);
    }
    
    /**
     * Undo the last executed action if it can be undone
     */
    public void undo(){
        if (!actionHistory.isEmpty()){
            String lastValue = actionHistory.get(actionHistory.lastKey());
            switch (actionHistory.lastKey()){
                case "grab":
                    this.drop(lastValue.toString());
                case "drop":
                    this.grab(lastValue.toString());
                case "examine":
                    throw new RuntimeException("This action cannot be undone");
                case "use":
                    throw new RuntimeException("This action cannot be undone");
                case "walk":
                    switch (lastValue){
                        case "north":
                            this.walk("south");
                        case "south":
                            this.walk("north");
                        case "east":
                            this.walk("west");
                        case "west":
                            this.walk("east");
                    }
                case "fly":
                    String[] xy = lastValue.split(" ");
                    int x = -Integer.valueOf(xy[0]);
                    int y = -Integer.valueOf(xy[1]);
                    this.fly(x,y);
                case "shrink":
                    this.grow();
                case "grow":
                    this.shrink();
                case "rest":
                    throw new RuntimeException("This action cannot be undone");
            }
        } else {
            throw new RuntimeException("There is no action to undo.");
        }
    }
}