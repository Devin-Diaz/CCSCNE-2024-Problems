package group_chat_problem;

/**
 * Represents a member of the group chat, including details like name, timezone, and temperature unit preference.
 */
public class Member {
    public String name;
    public int timezone;
    public String temperatureUnit;

    public Member(String name, int timezone, String temperatureUnit) {
        this.name = name;
        this.timezone = timezone;
        this.temperatureUnit = temperatureUnit;
    }
}
