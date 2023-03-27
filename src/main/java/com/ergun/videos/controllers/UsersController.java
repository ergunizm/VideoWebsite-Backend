package com.ergun.videos.controllers;

import com.ergun.videos.business.UserService;
import com.ergun.videos.entities.User;
import com.ergun.videos.entities.Video;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {
    private UserService userService;
    @PostMapping("/login")
    public User login(@RequestBody Map<String ,String> json){
        return userService.login(json.get("username"),json.get("password"));
    }

    @PostMapping()
    public void addUser(@Valid @RequestBody User user){
        userService.add(user);
    }
    @PostMapping("/fav")
    public void addFavorite(@RequestBody int videoId){
        userService.addVideoToUser(videoId);
    }
    @DeleteMapping("/fav")
    public void deleteFavorite(@RequestBody int videoId){
        userService.deleteVideoFromUser(videoId);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return userService.getById(id);
    }
    @GetMapping("/fav={id}")
    public List<Video> getFavorites(@PathVariable int id){
        return userService.getFavoriteVideos(id) ;
    }
}
