package by.borisevich.hotel.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

public class Guest implements Serializable {
    private int guestId;
    private String passport;
    private String telephoneNumber;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int user;

    public Guest(ResultSet resultSet) {
        try {
            this.guestId = resultSet.getInt(1);
            this.passport = resultSet.getString(2);
            this.telephoneNumber = resultSet.getString(3);
            this.firstName = resultSet.getString(4);
            this.lastName = resultSet.getString(5);
            this.birthDate = resultSet.getDate(6);
            this.user = resultSet.getInt(7);
        }catch (SQLException e){

        }
    }

    public Guest(int guestId, String passport, String telephoneNumber, String firstName, String lastName, Date birthDate, int user) {
        this.guestId = guestId;
        this.passport = passport;
        this.telephoneNumber = telephoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.user = user;
    }

    public Guest(String passport, String telephoneNumber, String firstName, String lastName, Date birthDate, int user) {
        this.guestId = guestId;
        this.passport = passport;
        this.telephoneNumber = telephoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.user = user;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
