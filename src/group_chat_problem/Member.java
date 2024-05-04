package group_chat_problem;

public class Member {
    public String name;
    public int timezone;
    public String temperatureUnit;

    public Member(String name, int timezone, String temperatureUnit) {
        this.name = name;
        this.timezone = timezone;
        this.temperatureUnit = temperatureUnit;
    }

    public String toString() {
        return name + ";" + timezone + ";" + temperatureUnit;
    }

}
