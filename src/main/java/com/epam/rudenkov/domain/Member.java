package com.epam.rudenkov.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Member")
public class Member implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "fname", nullable = false)
    private String fname;
    @Column(name = "lname", nullable = false)
    private String lname;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requester")
    private Set<Friendship> friendRequests = new HashSet<Friendship>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "friend")
    private Set<Friendship> friends = new HashSet<Friendship>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Set<Friendship> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Set<Friendship> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public Set<Friendship> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friendship> friends) {
        this.friends = friends;
    }
}
