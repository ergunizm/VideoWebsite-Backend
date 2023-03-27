package com.ergun.videos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "allow")
    private boolean allowNotification;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "video_user",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "video_id"))
    private List<Video> favorites= new ArrayList<>();

    public void addFavoriteVideo(Video video){
        if(favorites.isEmpty()){
            favorites = new ArrayList<>();
        }

        favorites.add(video);
        List<Video> videos = favorites;
        this.setFavorites(videos);
    }

    public void deleteFavoriteVideo(Video video){
        if(favorites.contains(video)){
            favorites.remove(video);
        }
    }
}