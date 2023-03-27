package com.ergun.videos.dataAccess;

import com.ergun.videos.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Integer> {
    List<Video> findByTitleContainingIgnoreCase(String name);

}
