package com.ergun.videos.controllers;

import com.ergun.videos.business.EmailService;
import com.ergun.videos.business.UserService;
import com.ergun.videos.business.VideoService;
import com.ergun.videos.entities.Video;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class VideosController {
    private VideoService videoService;
    private UserService userService;
    private EmailService emailService;

    @GetMapping()
    public List<Video> getAllVideos(){
        return videoService.getAll();
    }

    @PostMapping()
    public void addVideo(@RequestBody Video video){
        /* Needed account to send emails
        for(User user : userService.getAllAllowed()){
            emailService.sendSimpleMessage(user.getEmail(),"New video is uploaded","Heyy");
        }*/
        videoService.save(video);
    }

    @PutMapping()
    public void updateVideo(@RequestBody Video video){
        videoService.save(video);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable int id ){
        videoService.delete(id);
    }

    @GetMapping("/{id}")
    public Video getSingleVideo(@PathVariable int id){
        return videoService.getById(id);
    }
    @GetMapping("/search={title}")
    public List<Video> getVideos(@PathVariable String title){
        return videoService.getByTitle(title);
    }
}
