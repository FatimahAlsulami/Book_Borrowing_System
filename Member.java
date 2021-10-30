package book_borrowing_system;


public class Member {

    private String name;
    private int id;
    private String type;
    static int currMemberIndex = 0;

    public Member() {
    }

    public Member(String name, int id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static int getCurrMemberIndex() {
        return currMemberIndex;
    }

    public static void incCurrMemberIndex() {
        Member.currMemberIndex++;
    }

}
