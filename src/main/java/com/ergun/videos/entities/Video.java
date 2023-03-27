package com.ergun.videos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "video")
    private String videoUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String imgUrl;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "video_user",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
